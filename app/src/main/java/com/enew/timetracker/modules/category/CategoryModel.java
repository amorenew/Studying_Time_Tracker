package com.enew.timetracker.modules.category;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ConflictAction;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.annotation.Unique;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.ArrayList;

/**
 * Created by TCIG_PC_54 on 3/12/2017.
 */

@Table(database = TrackDatabase.class)
public class CategoryModel extends BaseModel implements SortedListAdapter.ViewModel {

    @PrimaryKey(autoincrement = true)
    private long id;
    @Column
    @Unique(onUniqueConflict = ConflictAction.IGNORE)
    private String name;

    public static ArrayList<CategoryModel> getCategories() {
        return (ArrayList<CategoryModel>) SQLite.select().from(CategoryModel.class).queryList();
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
        CategoryModel that = (CategoryModel) o;
        return getName() != null ? getName().equals(that.getName()) : that.getName() == null;
    }

    @Override
    public int hashCode() {
        return getName() != null ? getName().hashCode() : 0;
    }
}
