package com.enew.timetracker.modules.category;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.enew.timetracker.R;
import com.enew.timetracker.modules.BaseActivity;

public class CategoryActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        addToolBar();
        addBackButtonWhite(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CategoryActivity.this, "Back", Toast.LENGTH_SHORT).show();
            }
        });
        setToolbarTitle("Category");
    }
}
