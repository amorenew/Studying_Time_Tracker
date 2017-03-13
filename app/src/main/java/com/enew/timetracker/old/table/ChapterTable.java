package com.enew.timetracker.old.table;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.enew.timetracker.old.Constants;
import com.enew.timetracker.old.model.Chapter;

import java.util.ArrayList;

/**
 * Created by amorenew on 2/25/2015.
 */
public class ChapterTable extends SQLiteOpenHelper implements Table<Chapter> {

    public static final String TABLE_NAME = "chapter";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_NUMBER = "number";
    public static final String KEY_PAGE_START = "page_start";
    public static final String KEY_PAGE_END = "page_end";
    public static final String KEY_BOOK_ID = "book_id";

    public static final String CREATE_TABLE = "create table if not exists " +
            TABLE_NAME + " (" + KEY_ID + " integer primary key autoincrement, " + KEY_NAME + " text not null unique," +
            KEY_NUMBER + " integer, " + KEY_PAGE_START + " integer," + KEY_PAGE_END + " integer, " + KEY_BOOK_ID + " integer ); ";

    public ChapterTable(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(ChapterTable.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ". Old data will be destroyed");
        db.execSQL("drop table " + TABLE_NAME);
        onCreate(db);
    }

    @Override
    public long add(Chapter chapter) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        ContentValues contentValues = new ContentValues();
        long id = -1;
        if (chapter != null && !chapter.getName().isEmpty()) {
            contentValues.put(KEY_NAME, chapter.getName());
            contentValues.put(KEY_NUMBER, chapter.getName());
            contentValues.put(KEY_PAGE_START, chapter.getName());
            contentValues.put(KEY_PAGE_END, chapter.getName());
            contentValues.put(KEY_BOOK_ID, chapter.getName());

            id = db.insertWithOnConflict(TABLE_NAME, null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
            if (id > 0) {
                chapter.setId((int) id);
            }
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
        return id;
    }

    @Override
    public long delete(Chapter chapter) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        long id = -1;
        if (chapter != null && chapter.getId() > 0) {
            id = db.delete(TABLE_NAME, KEY_ID + "=" + chapter.getId(), null);
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
        return id;
    }

    @Override
    public long update(Chapter chapter) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        ContentValues values = new ContentValues();
        long id = -1;
        if (chapter != null && chapter.getId() > 0 && !chapter.getName().isEmpty()) {
            values.put(KEY_NAME, chapter.getName());
            id = db.update(TABLE_NAME, values, KEY_ID + "=" + chapter.getId(), null);
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
        return id;
    }

    @Override
    public Chapter get(int id) {
        return null;
    }


    @Override
    public ArrayList<Chapter> getAll() {
        ArrayList<Chapter> chapters = new ArrayList<Chapter>();
        String selectQuery = "SELECT *FROM " + TABLE_NAME;
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Chapter chapter = new Chapter();
                chapter.setId(cursor.getInt(0));
                chapter.setName(cursor.getString(1));
                chapter.setNumber(cursor.getInt(2));
                chapter.setPage_start(cursor.getInt(3));
                chapter.setPage_end(cursor.getInt(4));
                chapter.setBook_id(cursor.getInt(5));
                chapters.add(chapter);
            } while (cursor.moveToNext());
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        cursor.close();
        db.close();
        return chapters;
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
