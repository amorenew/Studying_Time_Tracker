package com.enew.timetracker.database.handler;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.enew.timetracker.database.Constants;

/**
 * Created by amorenew on 2/25/2015.
 */
public class CategoryHelper extends SQLiteOpenHelper {

    public static final String createDatabase = "create table if not exists " +
            Constants.TABLE_CATEGORY + " (" + Constants.ID + " integer primary key autoincrement, " + Constants.NAME + " text not null unique); ";


    public CategoryHelper(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(CategoryHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ". Old data will be destroyed");
        db.execSQL("drop table " + Constants.TABLE_CATEGORY);
        onCreate(db);
    }

    public void addName(String... name) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        ContentValues contentValues = new ContentValues();

        for (int i = 0; i < name.length; i++) {
            contentValues.put(Constants.NAME, name[i]);
            db.insert(Constants.TABLE_CATEGORY, null, contentValues);
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
    }
}
