package ru.netology.javacore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TodoServer {
    private Integer port;
    private Todos todos;
    private List<Task> tasksLog = new ArrayList<>();

    public TodoServer(int port, Todos todos) {
        this.port = port;
        this.todos = todos;
    }

    public void start() throws IOException {
        System.out.println("Starting server at " + port + "...");
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
                ) {
                    System.out.println("New connection accepted");

                    String jsonIn = in.readLine();
                    GsonBuilder builder = new GsonBuilder();
                    Gson gson = builder
                            .create();
                    Task task = gson.fromJson(jsonIn, Task.class);
                    if (task.type != TypeTask.RESTORE) {
                        tasksLog.add(task);
                    }

                    if (task.type == TypeTask.ADD) {
                        todos.addTask(task.task);
                    }
                    if (task.type == TypeTask.REMOVE) {
                        todos.removeTask(task.task);
                    }
                    if (task.type == TypeTask.RESTORE) {
                        if (tasksLog.get(tasksLog.size() - 1).type == TypeTask.ADD) {
                            todos.removeTask(tasksLog.get(tasksLog.size() - 1).task);
                            tasksLog.remove(tasksLog.size() - 1);
                        } else {
                            if (tasksLog.get(tasksLog.size() - 1).type == TypeTask.REMOVE) {
                                todos.addTask(tasksLog.get(tasksLog.size() - 1).task);
                                tasksLog.remove(tasksLog.size() - 1);
                            }
                        }
                    }
                    out.println(todos.getAllTasks());
                }
            }
        } catch (IOException e) {
            System.out.println("Server not started");
            e.printStackTrace();
        }
    }
}
