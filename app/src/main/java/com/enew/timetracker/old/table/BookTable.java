package com.enew.timetracker.old.table;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.enew.timetracker.old.Constants;
import com.enew.timetracker.old.model.Book;

import java.util.ArrayList;

/**
 * this class will handle all operation related to book table
 * Created by amar.d on 3/2/2015.
 */
public class BookTable extends SQLiteOpenHelper implements Table<Book> {

    private static final String TABLE_NAME = "book";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_ISBN_NUMBER = "isbn_number";
    private static final String KEY_CATEGORY_ID = "category_id";
    private static final String KEY_LEVEL_ID = "level_id";

    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + KEY_ID + "integer primary key autoincrement, "
            + KEY_NAME + " text not null unique," + KEY_ISBN_NUMBER + " INTEGER," + KEY_CATEGORY_ID + " INTEGER," + KEY_LEVEL_ID + " INTEGER)";


    public BookTable(final Context context) {
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

    /**
     * Add each new book record to book table
     *
     * @param book object to be added into db
     */
    @Override
    public long add(Book book) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        ContentValues contentValues = new ContentValues();
        long id = -1;
        if (book != null && !book.getName().isEmpty()) {
            contentValues.put(KEY_NAME, book.getName());
            contentValues.put(KEY_ISBN_NUMBER, book.getIsbnNumber());
            contentValues.put(KEY_CATEGORY_ID, book.getCategoryId());
            contentValues.put(KEY_LEVEL_ID, book.getLevelId());
            id = db.insertWithOnConflict(TABLE_NAME, null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
            if (id > 0) {
                book.setId((int) id);
            }
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
        return id;
    }


    @Override
    public long delete(Book book) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        long id = -1;
        if (book != null && book.getId() > 0) {
            id = db.delete(TABLE_NAME, KEY_ID + "=" + book.getId(), null);
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
        return id;
    }

    @Override
    public long update(Book book) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        ContentValues values = new ContentValues();
        long id = -1;
        if (book != null && book.getId() > 0 && !book.getName().isEmpty()) {
            values.put(KEY_NAME, book.getName());
            values.put(KEY_ISBN_NUMBER, book.getIsbnNumber());
            values.put(KEY_CATEGORY_ID, book.getCategoryId());
            values.put(KEY_LEVEL_ID, book.getLevelId());
            id = db.update(TABLE_NAME, values, KEY_ID + "=" + book.getId(), null);
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
        return id;
    }

    @Override
    public Book get(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        String selectQuery = "SELECT *FROM " + TABLE_NAME + " where " + KEY_ID + "=" + id;
        Cursor cursor = db.rawQuery(selectQuery, null);
        Book book = null;
        if (cursor != null && cursor.moveToFirst()) {
            book = new Book();
            book.setId(cursor.getInt(0));
            book.setName(cursor.getString(1));
            book.setIsbnNumber(cursor.getInt(2));
            book.setCategoryId(cursor.getInt(3));
            book.setLevelId(cursor.getInt(4));
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
        return book;
    }

    @Override
    public ArrayList<Book> getAll() {
        ArrayList<Book> books = new ArrayList<Book>();
        String selectQuery = "SELECT *FROM " + TABLE_NAME;
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Book book = new Book();
                book.setId(cursor.getInt(0));
                book.setName(cursor.getString(1));
                book.setIsbnNumber(cursor.getInt(2));
                book.setCategoryId(cursor.getInt(3));
                book.setLevelId(cursor.getInt(4));
                books.add(book);
            } while (cursor.moveToNext());
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        cursor.close();
        db.close();
        return books;
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
