package com.example.a127107.p6taskmanager;

import java.io.Serializable;

/**
 * Created by 127107 on 30/5/2017.
 */

public class Task implements Serializable {
    private int id;
    private String name;
    private  String description;

    public Task(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        description = description;
    }
}
