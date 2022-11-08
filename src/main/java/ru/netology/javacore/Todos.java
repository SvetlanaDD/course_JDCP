package ru.netology.javacore;

import java.util.*;

public class Todos {
    private static final Integer maxCountTask = 7;
    private List<String> taskList;

    public Todos() {
        this.taskList = new ArrayList<>();
    }

    public void addTask(String task) {
        if (taskList.size() <= maxCountTask) {
            this.taskList.add(task);
        }
    }

    public void removeTask(String task) {
        this.taskList.remove(task);
    }

    public String getAllTasks() {
        List<String> newTaskList = this.taskList;
        Collections.sort(newTaskList);
        StringBuilder allTask = new StringBuilder();
        newTaskList.forEach((task) -> {
            if (newTaskList.lastIndexOf(task) == newTaskList.size() - 1) {
                allTask.append(task);
            } else {
                allTask.append(task + " ");
            }
        });
        return allTask.toString();
    }
}
