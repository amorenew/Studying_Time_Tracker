package com.enew.timetracker.modules.category.presentation.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.enew.timetracker.R;
import com.enew.timetracker.commons.presentation.presenter.RecyclerItemClickListener;
import com.enew.timetracker.modules.category.models.HomeItemModel;
import com.enew.timetracker.modules.category.presentation.CategoryActivity;
import com.enew.timetracker.modules.category.presentation.LevelActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hamoda on 4/11/2017.
 */
public class HomeActivity extends AppCompatActivity implements RecyclerItemClickListener.OnItemClickListener {

    Context context;

    private List<HomeItemModel> homeItemModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        homeItemModel = new ArrayList<>();
        fillHomeItems();
        RecyclerView rvHome = (RecyclerView) findViewById(R.id.rvHome);
        rvHome.setLayoutManager(new LinearLayoutManager(this));
        HomeAdapter homeAdapter = new HomeAdapter(homeItemModel);
        rvHome.setAdapter(homeAdapter);
        rvHome.addOnItemTouchListener(new RecyclerItemClickListener(this, this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        //  Toast.makeText(this, "Click on " + id , Toast.LENGTH_SHORT).show();
        if (id == R.id.action_settings) {
            //   Intent i = new Intent(HomeActivity.this, CategoryActivity.class);
            //   startActivity(i);
            //   close this activity
            //  finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void fillHomeItems() {
        homeItemModel.add(new HomeItemModel(getString(R.string.category), R.drawable.c));
        homeItemModel.add(new HomeItemModel(getString(R.string.level), R.drawable.l));
        homeItemModel.add(new HomeItemModel(getString(R.string.books), R.drawable.b));
        homeItemModel.add(new HomeItemModel(getString(R.string.reports), R.drawable.r));
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case 0:
                startActivity(new Intent(HomeActivity.this, CategoryActivity.class));
                break;
            case 1:
                startActivity(new Intent(HomeActivity.this, LevelActivity.class));
                break;
            case 2:
                Toast.makeText(HomeActivity.this, "Books", Toast.LENGTH_LONG).show();
                break;
            case 3:
                Toast.makeText(HomeActivity.this, "Reports", Toast.LENGTH_LONG).show();
                break;
        }
        finish();
    }
}
