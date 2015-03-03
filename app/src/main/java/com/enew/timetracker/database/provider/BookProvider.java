package com.enew.timetracker.database.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.Settings;
import android.util.Log;

import com.enew.timetracker.database.handler.CategoryHandler;

/**
 * Created by amorenew on 2/27/2015.
 */
public class BookProvider extends ContentProvider {
    static final String AUTHORITY = "content://com.enew.timetracker.provider";
    public static final Uri CONTENT_URI = Uri.parse(AUTHORITY);
    static final String SINGLE_RECORD_MIME_TYPE = "vnd.android.cursor.item/vnd.enew.timetracker.time";
    static final String MULTIPLE_RECORD_MIME_TYPE = "vnd.android.cursor.dir/vnd.enew.timetracker.time";
    SQLiteDatabase db;
    CategoryHandler categoryHandler;
    @Override
    public boolean onCreate() {

        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Override
    public String getType(Uri uri) {
        String ret = getContext().getContentResolver().getType(Settings.System.CONTENT_URI);
        Log.d("content provider", "getType returning: " + ret);
        if (uri.getLastPathSegment() == null) {
            return SINGLE_RECORD_MIME_TYPE;
        } else {
            return MULTIPLE_RECORD_MIME_TYPE;
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
