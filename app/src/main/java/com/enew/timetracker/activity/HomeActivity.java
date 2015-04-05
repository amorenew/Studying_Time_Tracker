package com.enew.timetracker.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.enew.timetracker.R;
import com.enew.timetracker.database.model.Category;
import com.enew.timetracker.database.table.CategoryTable;

import java.util.ArrayList;


public class HomeActivity extends Activity {
    CategoryTable categoryTable = new CategoryTable(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        LinearLayout homeLayout = (LinearLayout) findViewById(R.id.homeLayout);
        homeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(HomeActivity.this, AddChapterActivity.class);
//                startActivity(intent);
                testCatergory();
                Log.d("", "");
            }
        });

//        ContentValues contentValues = new ContentValues();
//        contentValues.put(Constants.NAME, "User Experience");
//
//        getContentResolver().insert(BookProvider.CONTENT_URI, contentValues);

        //  testCatergory();
    }

    private void testCatergory() {
        Category d1 = new Category("o1");
        Category d2 = new Category("o2");
        Category d3 = new Category("o3");
        Category d4 = new Category("o4");
        Category d5 = new Category("o5");
        categoryTable.add(d1);
        long d2Id = categoryTable.add(d2);
        d2.setId(d2Id);
        long d3ID = categoryTable.add(d3);
        categoryTable.add(d4);
        categoryTable.add(d5);
        Category categoryDelete = new Category();
        categoryDelete.setId(d3ID);
        long y = categoryTable.add(new Category("omega"));
        Log.d("table category:", "category ID:" + y);
        d2.setName("poo2 renamed");
        categoryTable.update(d2);
        categoryTable.delete(categoryDelete);
        ArrayList<Category> categories = categoryTable.getAll();

        for (Category category : categories) {
            Log.d("table category:", "category ID:" + category.getId() + " Name:" + category.getName());
        }
    }
}
