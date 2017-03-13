package com.enew.timetracker.old.others;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.enew.timetracker.R;


/**
 * Created by hamoda on 3/4/2017.
 */


public class delete_book extends AppCompatActivity {

    DB_Sqlite db = new DB_Sqlite(this);
    EditText n_book;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_book);
        n_book = (EditText) findViewById(R.id.n_book);
    }


    public void delete_Book(View view) {
        String n1 = n_book.getText().toString();
        db.Delete_(n1);
        //Intent i= new Intent(getApplicationContext(),MainActivity.class);
        Intent i = new Intent(getApplicationContext(), CardViewActivity.class);
        startActivity(i);
    }


}

