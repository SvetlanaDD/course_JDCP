package ru.netology.javacore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private static final int PORT = 8989;
    private static final String HOST = "127.0.0.1";


    public static void main(String[] arg) {
        try (Socket clientSocket = new Socket(HOST, PORT);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true); // поток выхода
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) // поток
        {
            // отправляем запрос
            Task task = new Task(
//                    TypeTask.ADD, "First"
//                    TypeTask.ADD, "ASecond"
//                    TypeTask.REMOVE, "First"
//                    TypeTask.ADD, "AThird"
                    TypeTask.RESTORE
            );

            Gson gson = new GsonBuilder()
                    .create();
            String jsonTask = gson.toJson(task);
            out.println(jsonTask);

            // получаем ответ
            System.out.println(in.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

