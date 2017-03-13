package com.enew.timetracker.modules.commons.database;

/**
 * Created by TCIG_PC_54 on 3/12/2017.
 */

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Database config class
 */
@Database(name = TrackDatabase.NAME, version = TrackDatabase.VERSION)
public class TrackDatabase {
    //database name without .db or .sqlite
    public static final String NAME = "TrackDatabase";
    //database version number
    public static final int VERSION = 1;

}