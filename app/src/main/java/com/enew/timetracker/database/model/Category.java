package com.enew.timetracker.database.model;

/**
 * Created by amorenew on 2/15/2015.
 */
public class Category {
    private long id;
    private String name;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
