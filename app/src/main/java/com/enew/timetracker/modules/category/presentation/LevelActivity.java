package com.enew.timetracker.modules.category.presentation;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.enew.timetracker.R;
import com.enew.timetracker.commons.presentation.BaseActivity;
import com.enew.timetracker.commons.presentation.presenter.RowClickListener;
import com.enew.timetracker.commons.presentation.presenter.SpaceItemDecoration;
import com.enew.timetracker.modules.category.models.LevelModel;
import com.enew.timetracker.modules.category.presentation.presenter.LevelAdapter;

import org.apache.commons.lang3.builder.CompareToBuilder;

import java.util.Collections;
import java.util.Comparator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
//LevelActivity

/**
 * Created by hamoda on 4/15/2017.
 */

public class LevelActivity extends BaseActivity implements AddCategoryFragment.AddListener, RowClickListener<LevelModel> {

    @BindView(R.id.rv_level)
    protected RecyclerView rv_level;

    Comparator<LevelModel> CATEGORY_COMPARATOR = new Comparator<LevelModel>() {

        @Override
        public int compare(LevelModel a, LevelModel b) {
            return new CompareToBuilder().append(a.getName(), b.getName()).toComparison();
        }
    };

    private LevelAdapter levelAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_page);
        ButterKnife.bind(this);
        addToolBar();
        addBackButtonWhite(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(LevelActivity.this, "Back", Toast.LENGTH_SHORT).show();
            }
        });
        setToolbarTitle("Level");
        // Toast.makeText(CategoryActivity.this," " + CategoryModel.getCategories(),Toast.LENGTH_SHORT).show();
        rv_level.setItemAnimator(new DefaultItemAnimator());
        rv_level.addItemDecoration(new SpaceItemDecoration(0, 0, 0, 0));
        rv_level.setHasFixedSize(false);
        rv_level.setLayoutManager(new LinearLayoutManager(this));
        levelAdapter = new LevelAdapter(this, CATEGORY_COMPARATOR, this);
        Collections.sort(LevelModel.getCategories(), CATEGORY_COMPARATOR);
        levelAdapter.edit().add(LevelModel.getCategories()).commit();
        rv_level.setAdapter(levelAdapter);
    }


    @OnClick(R.id.add_level)
    public void addCategory(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        AddCategoryFragment addCategoryFragment = new AddCategoryFragment();
        addCategoryFragment.show(fragmentManager, addCategoryFragment.getClass().getSimpleName());
    }


    @Override
    public void onAdd(String text) {
        if (text.isEmpty()) return;
        LevelModel model = new LevelModel();
        model.setName(text);
        model.save();
        levelAdapter.edit().add(model).commit();
        levelAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(LevelModel model) {

        Toast.makeText(this, "Click on " + model.getName(), Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onItemMenuClick(LevelModel model) {


    }

    @Override
    public void onItemMenuEditClick(LevelModel model) {

        Toast.makeText(this, "Edit " + model.getName(), Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onItemMenuDeleteClick(LevelModel model) {
        model.delete();
        levelAdapter.edit().remove(model).commit();
        levelAdapter.notifyDataSetChanged();
    }
}
