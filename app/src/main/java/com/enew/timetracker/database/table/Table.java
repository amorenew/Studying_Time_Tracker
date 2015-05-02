package com.enew.timetracker.database.table;

import java.util.ArrayList;

/**
 * Created by aabdelwahab on 4/5/2015.
 */
public interface Table<Object> {

    long add(Object object);

    long delete(Object object);

    long update(Object object);

    Object get(int id);

    ArrayList<Object> getAll();

    int getCount();

}
