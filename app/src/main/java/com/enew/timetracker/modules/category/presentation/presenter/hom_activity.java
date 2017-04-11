package com.enew.timetracker.modules.category.presentation.presenter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.enew.timetracker.R;
import com.enew.timetracker.modules.category.models.HomeItemModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hamoda on 4/11/2017.
 */
public class hom_activity extends AppCompatActivity {
    Context context;
    private List<HomeItemModel> Home_Item_Model;
    private String[] mList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hom_activity);
        Home_Item_Model = new ArrayList<>();
        TitelMenue();
        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        LinearLayoutManager lm = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(lm);
        HomeAdapter adapter = new HomeAdapter(Home_Item_Model);
        rv.setAdapter(adapter);
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
        if (id == R.id.action_settings) {
            //Toast.makeText(this, "Click on " + id , Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void TitelMenue() {
        Home_Item_Model.add(new HomeItemModel("Category", R.drawable.c));
        Home_Item_Model.add(new HomeItemModel("Level", R.drawable.l));
        Home_Item_Model.add(new HomeItemModel("Box", R.drawable.b));
        Home_Item_Model.add(new HomeItemModel("Reports", R.drawable.r));
    }
}
