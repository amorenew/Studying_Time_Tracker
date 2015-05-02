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

    public static final String TABLE_NAME = "category";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";

    public static final String CREATE_TABLE = "create table if not exists " +
            TABLE_NAME + " (" + KEY_ID + " integer primary key autoincrement, " + KEY_NAME + " text not null unique); ";

    public CategoryTable(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
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
            contentValues.put(KEY_NAME, category.getName());
            id = db.insertWithOnConflict(TABLE_NAME, null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
            if (id > 0) {
                category.setId((int) id);
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
        if (category != null && category.getId() > 0) {
            id = db.delete(TABLE_NAME, KEY_ID + "=" + category.getId(), null);
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
            values.put(KEY_NAME, category.getName());
            id = db.updateWithOnConflict(TABLE_NAME, values, KEY_ID + "=" + category.getId(), null, SQLiteDatabase.CONFLICT_IGNORE);
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
        return id;
    }

    @Override
    public Category get(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        String selectQuery = "SELECT *FROM " + TABLE_NAME + " where " + KEY_ID + "=" + id;
        Cursor cursor = db.rawQuery(selectQuery, null);
        Category category = null;
        if (cursor != null && cursor.moveToFirst()) {
            category = new Category();
            category.setId(cursor.getInt(0));
            category.setName(cursor.getString(1));
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
        return category;
    }


    @Override
    public ArrayList<Category> getAll() {
        ArrayList<Category> categories = new ArrayList<Category>();
        String selectQuery = "SELECT *FROM " + TABLE_NAME;
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null && cursor.moveToFirst()) {
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
        if (cursor != null && cursor.moveToFirst()) {
            count = cursor.getCount();
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        cursor.close();
        db.close();
        return count;
    }

}
