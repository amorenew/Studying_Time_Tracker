/*
 * Copyright (c) 2017. Truiton (http://www.truiton.com/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 * Mohit Gupt (https://github.com/mohitgupt)
 *
 */
package com.enew.timetracker.old.others;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.enew.timetracker.R;

import java.util.ArrayList;


public class CardViewActivity extends AppCompatActivity {
    private static String LOG_TAG = "CardViewActivity";
    DB_Sqlite db = new DB_Sqlite(this);
    private Cursor cursor;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyRecyclerViewAdapter(getDataSet());
        mRecyclerView.setAdapter(mAdapter);
        // Code to Add an item with default animation
        //((MyRecyclerViewAdapter) mAdapter).addItem(obj, index);
        // Code to remove an item with default animation
        //((MyRecyclerViewAdapter) mAdapter).deleteItem(index);
    }


    @Override
    protected void onResume() {
        super.onResume();
        ((MyRecyclerViewAdapter) mAdapter).setOnItemClickListener(new MyRecyclerViewAdapter
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i(LOG_TAG, " Clicked on Item " + position);
            }
        });
    }

    public void Back(View view) {
        Intent i = new Intent(getApplicationContext(), home.class);
        startActivity(i);
    }

    private ArrayList<DataObject> getDataSet() {

        //private ArrayList<DB_Sqlite>getDataSet(){

        ArrayList results = new ArrayList<DataObject>();

        //int retval =99 ;
        //db.arrayList2.size()
        //getCol_b()
        //String aString =  db.getCol_b();
        //nums pages

        ArrayList aString = db.getCol_b();
        ArrayList bString = db.getCol_c();
        ArrayList cString = db.getCol_a();

        ArrayList f_String = db.getAllrecords();

        int size = aString.size();

        //String val = String.valueOf(db.arrayList2.size());
        // numbers book
        Toast.makeText(getApplicationContext(), "Size " + cString, Toast.LENGTH_LONG).show();
// اسم الكتاب
        // Toast.makeText(getApplicationContext(),"Size " + bString , Toast.LENGTH_LONG).show();
        // عدد صفحاته
        Toast.makeText(getApplicationContext(), "Size " + cString, Toast.LENGTH_LONG).show();
        // Toast.makeText(getApplicationContext()," "+ db.getCol_b(), Toast.LENGTH_LONG).show();
        //  ArrayList<String> listData = db.getAllrecords();
        //  ArrayList<String> listData = db.getAllrecords();
        //  ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listData);
        //  String id = cursor.getString(cursor.getColumnIndex(String.valueOf(0)));
        //  id is first column in db
        //  String id = cursor.getString( cursor.getColumnIndex("id") ); // id is column name in db
        //  Toast.makeText(getApplicationContext(), " " + id, Toast.LENGTH_LONG).show();
        //  results.add(arrayAdapter,arrayAdapter);

        for (int index = 0; index < size; index++) {

            DataObject obj = new DataObject("اسم الكتاب : " + bString.get(index), "عدد الصفحات : " + aString.get(index), "رقم الكتاب : " + cString.get(index));

            results.add(index, obj);
        }


        //  results.add(index, obj);
        return results;
    }
}
