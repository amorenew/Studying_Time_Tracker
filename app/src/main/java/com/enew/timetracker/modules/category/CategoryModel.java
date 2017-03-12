package com.enew.timetracker.modules.category;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.annotation.Unique;

/**
 * Created by TCIG_PC_54 on 3/12/2017.
 */

@Table(database = TrackDatabase.class)
public class CategoryModel {

    @PrimaryKey(autoincrement = true)
    private long id;
    @Column
    @Unique
    private String name;

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
