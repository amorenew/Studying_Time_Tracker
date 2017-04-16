package com.enew.timetracker.modules.level.presentation;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.enew.timetracker.R;
import com.enew.timetracker.commons.presentation.AddItemFragment;
import com.enew.timetracker.commons.presentation.BaseActivity;
import com.enew.timetracker.commons.presentation.EditItemFragment;
import com.enew.timetracker.commons.presentation.presenter.RowClickListener;
import com.enew.timetracker.commons.presentation.presenter.SpaceItemDecoration;
import com.enew.timetracker.modules.level.models.LevelModel;
import com.enew.timetracker.modules.level.presentation.presenters.LevelAdapter;

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

public class LevelActivity extends BaseActivity implements AddItemFragment.AddListener, EditItemFragment.EditListener, RowClickListener<LevelModel> {

    @BindView(R.id.rv_level)
    protected RecyclerView rvLevel;

    Comparator<LevelModel> CATEGORY_COMPARATOR = new Comparator<LevelModel>() {

        @Override
        public int compare(LevelModel a, LevelModel b) {
            return new CompareToBuilder().append(a.getName(), b.getName()).toComparison();
        }
    };

    private LevelAdapter levelAdapter;
    private LevelModel currentEditingLevelModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_page);
        ButterKnife.bind(this);
        addToolBar();
        addBackButtonWhite(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        setToolbarTitle("Level");
        // Toast.makeText(CategoryActivity.this," " + CategoryModel.getCategories(),Toast.LENGTH_SHORT).show();
        rvLevel.setItemAnimator(new DefaultItemAnimator());
        rvLevel.addItemDecoration(new SpaceItemDecoration(0, 0, 0, 0));
        rvLevel.setHasFixedSize(false);
        rvLevel.setLayoutManager(new LinearLayoutManager(this));
        levelAdapter = new LevelAdapter(this, CATEGORY_COMPARATOR, this);
        Collections.sort(LevelModel.getCategories(), CATEGORY_COMPARATOR);
        levelAdapter.edit().add(LevelModel.getCategories()).commit();
        rvLevel.setAdapter(levelAdapter);
    }

    @OnClick(R.id.add_level)
    public void addCategory(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        AddItemFragment addItemFragment = new AddItemFragment();
        addItemFragment.show(fragmentManager, addItemFragment.getClass().getSimpleName());
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
    public void onEdit(String text) {
        if (text.isEmpty()) return;
        currentEditingLevelModel.setName(text);
        currentEditingLevelModel.update();
//        categoriesAdapter.edit().update(currentEditingCategoryModel).commit();
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
        currentEditingLevelModel = model;
        FragmentManager fragmentManager = getSupportFragmentManager();
        EditItemFragment editItemFragment = new EditItemFragment();
        editItemFragment.show(fragmentManager, editItemFragment.getClass().getSimpleName());
    }

    @Override
    public void onItemMenuDeleteClick(LevelModel model) {
        model.delete();
        levelAdapter.edit().remove(model).commit();
        levelAdapter.notifyDataSetChanged();
    }
}
