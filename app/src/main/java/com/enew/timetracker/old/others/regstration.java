package com.enew.timetracker.old.others;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.enew.timetracker.R;


/**
 * Created by hamoda on 2/10/2017.
 */

public class regstration extends AppCompatActivity {

    DB_Sqlite db = new DB_Sqlite(this);
    EditText num_b, name_b, pages_book;
    //---ListView--lv;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        num_b = (EditText) findViewById(R.id.num_book);
        name_b = (EditText) findViewById(R.id.name_book);
        pages_book = (EditText) findViewById(R.id.pages_b);
        //lv = (ListView)findViewById(R.id.L_view);
        /// show();
    }

    public void add2(View view) {
        String n1 = num_b.getText().toString();
        String n2 = name_b.getText().toString();
        String h = pages_book.getText().toString();

        boolean result = db.insertData(n1, n2, h);

        if (result == true) {
            num_b.setText("");
            name_b.setText("");
            pages_book.setText("");
            //show();
            Toast.makeText(getApplicationContext(), "YES VAL", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "NON VAL", Toast.LENGTH_LONG).show();
        }
        //Intent i= new Intent(getApplicationContext(),MainActivity.class);
        Intent i = new Intent(getApplicationContext(), CardViewActivity.class);
        startActivity(i);
    }


/*
  public void goto_view(View view){
  Intent i= new Intent(getApplicationContext(),MainActivity.class);
   startActivity(i);
 }
    public void add2(View view){
        String s = num_b.getText().toString();
        String n = name_b.getText().toString();
        String h = pages_book.getText().toString();
        boolean result = db.insertData(s, n, h);
        if (result == true) {
            num_b.setText("");
            name_b.setText("");
            pages_book.setText("");
            //  show();
            Toast.makeText(getApplicationContext(), "YES VAL", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "NON VAL", Toast.LENGTH_LONG).show();
        }
    }
    */


}





