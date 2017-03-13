package com.enew.timetracker.old.others;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DB_Sqlite extends SQLiteOpenHelper {
    public static final String DBname = "data.db";
    public ArrayList arrayList2;
    public ArrayList arrayList1;
    public ArrayList arrayList0;


    public DB_Sqlite(Context context) {

        super(context, DBname, null, 1);

    }


    @Override
    public void onCreate(SQLiteDatabase Db) {
        Db.execSQL("create table mytable ( id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, email TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int nV) {
        db.execSQL("DROP TABLE IF EXISTS mytable");
        onCreate(db);
    }


    public boolean insertData(String h, String n0, String n1) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues ContentValues = new ContentValues();
        ContentValues.put("name", n0);
        ContentValues.put("email", n1);
        ContentValues.put("ID", h);

        long result_ = db.insert("mytable", null, ContentValues);

        return result_ != -1;

    }


    public ArrayList getAllrecords() {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT  * FROM mytable", null);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            String s1 = res.getString(0);
            String s2 = res.getString(1);
            String s3 = res.getString(2);
            arrayList.add(" " + s1 + " " + s2 + " " + s3);
            res.moveToNext();
        }
        return arrayList;
    }


    public ArrayList getCol_b() {

        arrayList2 = new ArrayList();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT  * FROM mytable", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            String s1 = res.getString(2);
            arrayList2.add("" + s1);
            res.moveToNext();
        }
        int totalElements = arrayList2.size();
        //Integer arrayLength = (Integer) arrayList2.le;
        return arrayList2;
    }


    public ArrayList getCol_c() {

        arrayList1 = new ArrayList();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT  * FROM mytable", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            String s1 = res.getString(1);
            arrayList1.add("" + s1);
            res.moveToNext();
        }
        int totalElements = arrayList1.size();
        //Integer arrayLength = (Integer) arrayList2.le;
        return arrayList1;
    }

    public ArrayList getCol_a() {

        arrayList0 = new ArrayList();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT  * FROM mytable", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            String s1 = res.getString(0);
            arrayList0.add("" + s1);
            res.moveToNext();
        }
        int totalElements = arrayList0.size();
        //Integer arrayLength = (Integer) arrayList2.le;
        return arrayList0;
    }




/*
    public String getEmployeeName(String empNo){

        Cursor cursor = null;

        String empName = "";

        try {
            cursor = db.rawQuery("SELECT name FROM mytable WHERE EmpNo=?", new String[] {empNo + ""});
            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                empName = cursor.getString(cursor.getColumnIndex("name"));
            }
            return empName;
        }finally {
            cursor.close();
        }
    }

*/


    public boolean update(String id, String n, String e) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues ContentValues = new ContentValues();
        ContentValues.put("name", n);
        ContentValues.put("email", e);
        ContentValues.put("ID", id);
        db.update("mytable", ContentValues, "id= ?", new String[]{id});
        //--- long result_ = db.insert("mytable",null,ContentValues);
        return true;
    }


    public Integer Delete_(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("mytable", "id= ?", new String[]{id});
    }

}
