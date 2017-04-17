package com.enew.timetracker.modules.category.models;

import com.enew.timetracker.commons.database.TrackDatabase;
import com.enew.timetracker.commons.presentation.presenter.SortedListAdapter;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ConflictAction;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.annotation.Unique;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.ArrayList;

/**
 * Created by hamoda on 4/15/2017.
 */

@Table(database = TrackDatabase.class)
public class LevelModel extends BaseModel implements SortedListAdapter.ViewModel {
    @PrimaryKey(autoincrement = true)
    private long id;
    @Column
    @Unique(onUniqueConflict = ConflictAction.IGNORE)
    private String name;

    public static ArrayList<LevelModel> getCategories() {

        return (ArrayList<LevelModel>) SQLite.select().from(LevelModel.class).queryList();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LevelModel that = (LevelModel) o;
        return getName() != null ? getName().equals(that.getName()) : that.getName() == null;
    }

    @Override
    public int hashCode() {
        return getName() != null ? getName().hashCode() : 0;
    }
}
