package com.enew.timetracker.modules.category.presentation.presenter;

import android.view.View;
import android.widget.TextView;

import com.enew.timetracker.R;
import com.enew.timetracker.commons.presentation.presenter.SortedListAdapter;
import com.enew.timetracker.modules.category.models.CategoryModel;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by amor on 5/3/2016.
 */


public class CategoryViewHolder extends SortedListAdapter.ViewHolder<CategoryModel> implements View.OnClickListener {

    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.textViewOptions)
    View buttonViewOption;

    // public android.view.View buttonViewOption;

    public CategoryViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
        view.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    protected void performBind(CategoryModel item) {

    }
}