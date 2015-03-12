package com.enew.timetracker.database.handler;

import android.content.ContentValues;
import android.content.Context;

import com.enew.timetracker.database.model.Book;

/**
 * this class will handle all operation related to book table
 * Created by amar.d on 3/2/2015.
 */
public class BookTable {

    private static final String TABLE_NAME = "book_table";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String CATEGORY_ID = "category_id";
    private static final String LEVEL_ID = "level_id";
    public static final String CREATE_BOOK_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + ID + "INTEGER PRIMARY KEY,"
            + NAME + "TEXT," + CATEGORY_ID + "INTEGER," + LEVEL_ID + "INTEGER)";
    private Context mContext;

    public BookTable(final Context context) {
        mContext = context;
    }

    /**
     * Add each new book record to book table
     *
     * @param book object to be added into db
     */
    private void addBook(final Book book) {
        final ContentValues contentValues = new ContentValues();
        contentValues.put(BookTable.ID, book.getId());
        contentValues.put(BookTable.NAME, book.getName());
        contentValues.put(BookTable.CATEGORY_ID, book.getCategory_id());
        contentValues.put(BookTable.LEVEL_ID, book.getLevel_id());

        CategoryHandler.getInstance(mContext).getWritableDatabase().beginTransaction();
        CategoryHandler.getInstance(mContext).getWritableDatabase().insertOrThrow(BookTable.TABLE_NAME, null, contentValues);
        CategoryHandler.getInstance(mContext).getWritableDatabase().setTransactionSuccessful();
        CategoryHandler.getInstance(mContext).getWritableDatabase().endTransaction();
    }

    /**
     * delete book
     *
     * @param bookId id of book is to be deleted
     */
    private void deleteBook(final int bookId) {
        CategoryHandler.getInstance(mContext).getWritableDatabase().beginTransaction();
        CategoryHandler.getInstance(mContext).getWritableDatabase().delete(BookTable.TABLE_NAME, BookTable.ID + " =?", new String[]{String.valueOf(bookId)});
        CategoryHandler.getInstance(mContext).getWritableDatabase().setTransactionSuccessful();
        CategoryHandler.getInstance(mContext).getWritableDatabase().endTransaction();
    }

    private void updateBookInfo() {
        //TODO update existing book info.
    }


}
