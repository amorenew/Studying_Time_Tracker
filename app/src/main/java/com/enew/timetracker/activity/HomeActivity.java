package com.enew.timetracker.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import com.enew.timetracker.R;
import com.enew.timetracker.database.model.Category;
import com.enew.timetracker.database.table.CategoryTable;


public class HomeActivity extends Activity implements OnClickListener {
    CategoryTable categoryTable = new CategoryTable(this);

    Button btnCategory, btnLevel, btnBook, btnChapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        LinearLayout homeLayout = (LinearLayout) findViewById(R.id.homeLayout);
        btnCategory = (Button) findViewById(R.id.btnCategory);
        btnLevel = (Button) findViewById(R.id.btnLevel);
        btnBook = (Button) findViewById(R.id.btnBook);
        btnChapter = (Button) findViewById(R.id.btnChapter);
        homeLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AddChapterActivity.class);
                startActivity(intent);
//                testCatergory();
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
        CategoryTable categoryTable = new CategoryTable(this);
        Category category1 = new Category("UX");
        Category category2 = new Category("OOP");
        Category category3 = new Category("UML");
        Category category4 = new Category("UI");
        categoryTable.add(category2);
        categoryTable.add(category3);
        categoryTable.add(category1);
        long id = categoryTable.add(category4);
        categoryTable.delete(category2);
        category3.setName("UML2");
        categoryTable.update(category3);
        Category testCategory = categoryTable.get((int) id);
        for (Category category : categoryTable.getAll()) {
            Log.d("table category:", "category ID:" + category.getId() + " Name:" + category.getName());
        }
        if (testCategory != null)
            Log.d("table category:", "category Name:" + testCategory.getName());

    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btnCategory:
                intent = new Intent(HomeActivity.this, AddChapterActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btnLevel:
                intent = new Intent(HomeActivity.this, AddChapterActivity.class);
                startActivity(intent);
                break;
            case R.id.btnBook:
                intent = new Intent(HomeActivity.this, AddChapterActivity.class);
                startActivity(intent);
                break;
            case R.id.btnChapter:
                intent = new Intent(HomeActivity.this, AddChapterActivity.class);
                startActivity(intent);
                break;
        }
    }
}
