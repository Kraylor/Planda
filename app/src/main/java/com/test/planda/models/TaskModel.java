package com.test.planda.models;

public class TaskModel {

    private String date;
    private String Description;
    private String name;

    public TaskModel(String date, String description, String name) {
        this.date = date;
        Description = description;
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
