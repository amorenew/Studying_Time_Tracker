package com.enew.timetracker.database.handler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.enew.timetracker.database.Constants;
import com.enew.timetracker.database.model.Category;

import java.util.ArrayList;

/**
 * Created by amorenew on 2/25/2015.
 */
public class CategoryTable extends SQLiteOpenHelper {

    public static final String CATEGORY_TABLE = "category_table";
    public static final String ID_KEY = "id";
    public static final String NAME_KEY = "name";
    public static final String createDatabase = "create table if not exists " +
            CATEGORY_TABLE + " (" + ID_KEY + " integer primary key autoincrement, " + NAME_KEY + " text not null unique); ";

    public CategoryTable(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(CategoryTable.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ". Old data will be destroyed");
        db.execSQL("drop table " + CATEGORY_TABLE);
        onCreate(db);
    }


    public long add(Category category) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        long id = -1;
        if (category != null && !category.getName().isEmpty()) {
            contentValues.put(NAME_KEY, category.getName());
            id = db.insert(CATEGORY_TABLE, null, contentValues);
//            id = db.insertWithOnConflict(Constants.TABLE_CATEGORY, null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
            if (id > 0) {
                category.setId(id);
            }
        }
        db.close();
        return id;
    }

    public long delete(long categoryId) {
        SQLiteDatabase db = getWritableDatabase();
        long id = db.delete(CATEGORY_TABLE, ID_KEY + "=" + categoryId, null);
        db.close();
        return id;
    }

    public long update(Category category) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        long id = -1;
        if (category != null && !category.getName().isEmpty()) {
            //values.put(ID_KEY, category.getId());
            values.put(NAME_KEY, category.getName());
            id = db.update(CATEGORY_TABLE, values, ID_KEY + "=" + category.getId(), null);
        }
        db.close();
        return id;
    }

    public ArrayList<Category> getCategories() {
        ArrayList<Category> categories = new ArrayList<Category>();
        String selectQuery = "SELECT *FROM " + CATEGORY_TABLE;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Category category = new Category();
                category.setId(cursor.getInt(0));
                category.setName(cursor.getString(1));
                categories.add(category);
            } while (cursor.moveToNext());
        }
        db.close();
        return categories;
    }

}
