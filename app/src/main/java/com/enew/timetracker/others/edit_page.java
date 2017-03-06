package com.enew.timetracker.others;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.enew.timetracker.R;


/**
 * Created by hamoda on 3/5/2017.
 */


public class edit_page extends AppCompatActivity {
    DB_Sqlite db = new DB_Sqlite(this);
    EditText num_b, name_b, pages_book;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_page);
        num_b = (EditText) findViewById(R.id.num_book);
        name_b = (EditText) findViewById(R.id.name_book);
        pages_book = (EditText) findViewById(R.id.pages_b);
    }


    public void edit_p(View view) {
        String n1 = num_b.getText().toString();
        String n2 = name_b.getText().toString();
        String h = pages_book.getText().toString();
        db.update(n1, n2, h);
        Intent i = new Intent(getApplicationContext(), CardViewActivity.class);
        startActivity(i);
    }


}
