package ru.netology.javacore;

public class Task {
    TypeTask type;
    String task;

    public Task(TypeTask type, String task) {
        this.type = type;
        this.task = task;
    }
    public Task(TypeTask type) {
        this.type = type;
    }
}

enum TypeTask
{
    ADD,
    REMOVE,
    RESTORE
}
