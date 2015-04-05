package com.enew.timetracker.database.table;

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
public class CategoryTable extends SQLiteOpenHelper implements table<Category> {

    public static final String TABLE_NAME = "category_table";
    public static final String ID_KEY = "id";
    public static final String NAME_KEY = "name";
    public static final String createDatabase = "create table if not exists " +
            TABLE_NAME + " (" + ID_KEY + " integer primary key autoincrement, " + NAME_KEY + " text not null unique); ";

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
        db.execSQL("drop table " + TABLE_NAME);
        onCreate(db);
    }

    @Override
    public long add(Category category) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        ContentValues contentValues = new ContentValues();
        long id = -1;
        if (category != null && !category.getName().isEmpty()) {
            contentValues.put(NAME_KEY, category.getName());
            id = db.insertWithOnConflict(TABLE_NAME, null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
            if (id > 0) {
                category.setId(id);
            }
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
        return id;
    }

    @Override
    public long delete(Category category) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        long id = -1;
        if (category != null & category.getId() > 0) {
            id = db.delete(TABLE_NAME, ID_KEY + "=" + category.getId(), null);
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
        return id;
    }

    @Override
    public long update(Category category) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        ContentValues values = new ContentValues();
        long id = -1;
        if (category != null && category.getId() > 0 && !category.getName().isEmpty()) {
            values.put(NAME_KEY, category.getName());
            id = db.update(TABLE_NAME, values, ID_KEY + "=" + category.getId(), null);
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
        return id;
    }


    @Override
    public ArrayList<Category> getAll() {
        ArrayList<Category> categories = new ArrayList<Category>();
        String selectQuery = "SELECT *FROM " + TABLE_NAME;
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Category category = new Category();
                category.setId(cursor.getInt(0));
                category.setName(cursor.getString(1));
                categories.add(category);
            } while (cursor.moveToNext());
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        cursor.close();
        db.close();
        return categories;
    }

    @Override
    public int getCount() {
        String selectQuery = "SELECT *FROM " + TABLE_NAME;
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        int count = 0;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            count = cursor.getCount();
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        cursor.close();
        db.close();
        return count;
    }

}
