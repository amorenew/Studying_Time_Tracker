package com.enew.timetracker.modules.category;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.enew.timetracker.R;

import java.util.Comparator;


/**
 * Created by TCIG_PC_54 on 7/11/2016.
 */

public class CategoriesAdapter extends SortedListAdapter<CategoryModel> {
    private Context context;

    public CategoriesAdapter(Context context, Comparator<CategoryModel> comparator) {
        super(context, CategoryModel.class, comparator);
        this.context = context;
    }

    @Override
    protected ViewHolder<? extends CategoryModel> onCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_category, parent, false);
        return new CategoryViewHolder(itemView);
    }

    @Override
    protected boolean areItemContentsTheSame(CategoryModel oldItem, CategoryModel newItem) {
        return oldItem.equals(newItem);
    }

    @Override
    protected boolean areItemsTheSame(CategoryModel item1, CategoryModel item2) {
        return item1 == item2;
    }

    @Override
    public void onBindViewHolder(ViewHolder<? extends CategoryModel> viewHolder, int position) {
        super.onBindViewHolder(viewHolder, position);
        CategoryViewHolder holder = (CategoryViewHolder) viewHolder;
        CategoryModel categoryModel = getItem(position);
        holder.tvName.setText(categoryModel.getName());
    }

}
