package ru.netology.javacore;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TodosTests {
    Todos test = new Todos();
    @Test
    void addTask() {
        test.addTask("task1");
        Assertions.assertEquals(test.getAllTasks(), "task1");
    }

    @Test
    void removeTask() {
        test.addTask("task1");
        test.addTask("task2");
        test.addTask("task3");
        test.removeTask("task2");
        Assertions.assertEquals(test.getAllTasks(), "task1 task3");
    }

    @Test
    void getAllTasks() {
        test.addTask("task2");
        test.addTask("task3");
        test.addTask("task1");
        Assertions.assertEquals(test.getAllTasks(), "task1 task2 task3");
    }

}
