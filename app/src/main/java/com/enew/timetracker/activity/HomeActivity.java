package com.enew.timetracker.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.enew.timetracker.R;


public class HomeActivity extends Activity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    LinearLayout homeLayout=(LinearLayout) findViewById(R.id.homeLayout);
        homeLayout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(HomeActivity.this, AddChapterActivity.class);
            startActivity(intent);

        }
    });
    }
}
