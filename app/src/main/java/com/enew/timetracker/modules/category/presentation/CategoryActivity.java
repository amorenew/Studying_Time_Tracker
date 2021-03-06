package com.enew.timetracker.modules.category.presentation;

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
import com.enew.timetracker.modules.category.models.CategoryModel;
import com.enew.timetracker.modules.category.presentation.presenter.CategoriesAdapter;

import org.apache.commons.lang3.builder.CompareToBuilder;

import java.util.Collections;
import java.util.Comparator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CategoryActivity extends BaseActivity implements AddItemFragment.AddListener, EditItemFragment.EditListener, RowClickListener<CategoryModel> {

    @BindView(R.id.rvResults)
    protected RecyclerView rvResults;

    Comparator<CategoryModel> CATEGORY_COMPARATOR = new Comparator<CategoryModel>() {
        @Override
        public int compare(CategoryModel a, CategoryModel b) {
            return new CompareToBuilder()
                    .append(a.getName(), b.getName())
                    .toComparison();
        }
    };
    private CategoriesAdapter categoriesAdapter;
    private CategoryModel currentEditingCategoryModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);

        addToolBar();
        addBackButtonWhite(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        setToolbarTitle("Category");

        rvResults.setItemAnimator(new DefaultItemAnimator());
        rvResults.addItemDecoration(new SpaceItemDecoration(0, 0, 0, 0));
        rvResults.setHasFixedSize(false);
        rvResults.setLayoutManager(new LinearLayoutManager(this));
        categoriesAdapter = new CategoriesAdapter(this, CATEGORY_COMPARATOR, this);
        Collections.sort(CategoryModel.getCategories(), CATEGORY_COMPARATOR);
        categoriesAdapter.edit().add(CategoryModel.getCategories()).commit();
        rvResults.setAdapter(categoriesAdapter);
    }

    @OnClick(R.id.addCategory)
    public void addCategory(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        AddItemFragment addItemFragment = new AddItemFragment();
        addItemFragment.show(fragmentManager, addItemFragment.getClass().getSimpleName());
    }

    @Override
    public void onAdd(String text) {
        if (text.isEmpty()) return;
        CategoryModel model = new CategoryModel();
        model.setName(text);
        model.save();
        categoriesAdapter.edit().add(model).commit();
        categoriesAdapter.notifyDataSetChanged();
    }

    @Override
    public void onEdit(String text) {
        if (text.isEmpty()) return;
        currentEditingCategoryModel.setName(text);
        currentEditingCategoryModel.update();
//        categoriesAdapter.edit().update(currentEditingCategoryModel).commit();
        categoriesAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(CategoryModel model) {
        Toast.makeText(this, "Click on " + model.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemMenuClick(CategoryModel model) {

    }

    @Override
    public void onItemMenuEditClick(CategoryModel model) {
        currentEditingCategoryModel = model;
        FragmentManager fragmentManager = getSupportFragmentManager();
        EditItemFragment editItemFragment = new EditItemFragment();
        editItemFragment.show(fragmentManager, editItemFragment.getClass().getSimpleName());
    }

    @Override
    public void onItemMenuDeleteClick(CategoryModel model) {
        model.delete();
        categoriesAdapter.edit().remove(model).commit();
        categoriesAdapter.notifyDataSetChanged();
    }


}
