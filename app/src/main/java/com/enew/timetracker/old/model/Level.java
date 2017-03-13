package com.enew.timetracker.old.model;

/**
 * Created by amorenew on 2/25/2015.
 */
public class Level {

    int id;
    String name;

    public Level() {
    }

    public Level(String name) {
        this.name = name;
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
}
