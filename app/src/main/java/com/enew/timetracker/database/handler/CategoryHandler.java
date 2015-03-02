package com.enew.timetracker.database.handler;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by amorenew on 2/25/2015.
 */
public class CategoryHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "tracker_database";
    public static final int VERSION = 1;
    public static final String TABLE_NAME = "category_table";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String createDatabase = "create table if not exists " +
            TABLE_NAME + " (" + ID + " integer primary key autoincrement, " + NAME + " text not null unique); ";
    private static CategoryHandler mCategoryHandler;

    public CategoryHandler(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    /**
     * return the same instance through out the class
     *
     * @param context of the class
     * @return single instance of the class throughout the app lifer cycle
     */
    public static CategoryHandler getInstance(final Context context) {
        if (mCategoryHandler == null)
            mCategoryHandler = new CategoryHandler(context);
        return mCategoryHandler;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createDatabase);
        db.execSQL(BookTable.CREATE_BOOK_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table " + TABLE_NAME);
    }

    public void addName(String... name) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        ContentValues contentValues = new ContentValues();

        for (int i = 0; i < name.length; i++) {
            contentValues.put(CategoryHandler.NAME, name[i]);
            db.insert(CategoryHandler.TABLE_NAME, null, contentValues);
        }
        db.setTransactionSuccessful();
        db.endTransaction();
    }
}
