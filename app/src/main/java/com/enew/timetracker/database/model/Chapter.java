package com.enew.timetracker.database.model;

/**
 * Created by amorenew on 2/25/2015.
 */
public class Chapter {
    int id;
    String name;
    int number;
    int page_start;
    int page_end;
    int book_id;

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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPage_start() {
        return page_start;
    }

    public void setPage_start(int page_start) {
        this.page_start = page_start;
    }

    public int getPage_end() {
        return page_end;
    }

    public void setPage_end(int page_end) {
        this.page_end = page_end;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }
}
