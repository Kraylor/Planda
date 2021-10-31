package com.test.planda.models;

import java.util.ArrayList;

public class LoggedInUser {
    private static LoggedInUser loggedInUser;

    private String name;
    private String email;
    private String uid;
    private ArrayList<TaskModel> tasks;

    public LoggedInUser() {
        tasks = new ArrayList<>();
    }

    public static synchronized LoggedInUser getInstance() {
        if(loggedInUser == null) {
            loggedInUser = new LoggedInUser();
        }
        return loggedInUser;
    }

    public ArrayList<TaskModel> getTasks() {
        return tasks;
    }

    public void addTask(TaskModel task) {
        this.tasks.add(task);
    }

    public void setTasks(ArrayList<TaskModel> tasks) {
        this.tasks = tasks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
