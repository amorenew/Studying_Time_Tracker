package com.enew.timetracker.database.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.provider.Settings;
import android.util.Log;

import com.enew.timetracker.database.Constants;
import com.enew.timetracker.database.handler.CategoryHelper;

import java.util.HashMap;

/**
 * Created by amorenew on 3/10/2015.
 */
public class TimeProvider extends ContentProvider {
    // fields for my content provider
    static final String AUTHORITY = "com.enew.timetracker.provider";
    static final String URL = "content://" + AUTHORITY + "/" + Constants.TABLE_CATEGORY;
    static final Uri CONTENT_URI = Uri.parse(URL);
    static final String SINGLE_RECORD_MIME_TYPE = "vnd.android.cursor.item/vnd.enew.timetracker.time";
    static final String MULTIPLE_RECORD_MIME_TYPE = "vnd.android.cursor.dir/vnd.enew.timetracker.time";

    // integer values used in content URI
    static final int CATEGORY = 1;
    static final int CATEGORY_NAME = 2;
    static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, Constants.TABLE_CATEGORY, CATEGORY);
        uriMatcher.addURI(AUTHORITY, Constants.TABLE_CATEGORY + "/#", CATEGORY_NAME);
    }

    // maps content URI "patterns" to the integer values that were set above
    // projection map for a query
    private static HashMap<String, String> BirthMap;
    CategoryHelper categoryHelper;
    private SQLiteDatabase database;

    @Override
    public boolean onCreate() {
        Context context = getContext();
        categoryHelper = new CategoryHelper(context);
        // permissions to be writable
        database = categoryHelper.getWritableDatabase();
        if (database == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        // the TABLE_NAME to query on
        queryBuilder.setTables(Constants.TABLE_CATEGORY);
        switch (uriMatcher.match(uri)) {
            // maps all database column names
            case CATEGORY:
                queryBuilder.setProjectionMap(BirthMap);
                break;
            case CATEGORY_NAME:
                queryBuilder.appendWhere(Constants.NAME + "=" + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        if (sortOrder == null || sortOrder == "") {
            // No sorting-> sort on names by default
            sortOrder = Constants.NAME;
        }
        Cursor cursor = queryBuilder.query(database, projection, selection,
                selectionArgs, null, null, sortOrder);

        /**
         * register to watch a content URI for changes
         */
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        String ret = getContext().getContentResolver().getType(Settings.System.CONTENT_URI);
        Log.d("content provider", "getType returning: " + ret);

        switch (uriMatcher.match(uri)) {
            // Get all  records
            case CATEGORY:
                return MULTIPLE_RECORD_MIME_TYPE;
            // Get a particular record
            case CATEGORY_NAME:
                return SINGLE_RECORD_MIME_TYPE;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
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
