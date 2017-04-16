package com.enew.timetracker.modules.category.presentation.presenter;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.enew.timetracker.R;
import com.enew.timetracker.commons.presentation.presenter.RowClickListener;
import com.enew.timetracker.commons.presentation.presenter.SortedListAdapter;
import com.enew.timetracker.modules.category.models.CategoryModel;

import java.util.Comparator;

/**
 * Created by TCIG_PC_54 on 7/11/2016.
 */

public class CategoriesAdapter extends SortedListAdapter<CategoryModel> {

    private Context context;
    private RowClickListener<CategoryModel> rowClickListener;

    public CategoriesAdapter(Context context, Comparator<CategoryModel> comparator, RowClickListener<CategoryModel> rowClickListener) {
        super(context, CategoryModel.class, comparator);
        this.context = context;
        this.rowClickListener = rowClickListener;
    }

    @Override
    protected ViewHolder<? extends CategoryModel> onCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_category, parent, false);
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
        final CategoryViewHolder holder = (CategoryViewHolder) viewHolder;
        final CategoryModel categoryModel = getItem(position);
        holder.tvName.setText(categoryModel.getName());
        holder.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rowClickListener.onItemClick(categoryModel);
            }
        });
        holder.subMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rowClickListener.onItemMenuClick(categoryModel);
                final PopupMenu popup = new PopupMenu(context, holder.subMenu);
                //inflating menu from xml resource
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.options_menu, popup.getMenu());
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.edit:
                                rowClickListener.onItemMenuEditClick(categoryModel);
                                break;
                            case R.id.delete:
                                rowClickListener.onItemMenuDeleteClick(categoryModel);
                                break;
                        }
                        return false;
                    }
                });
                // displaying the popup
                popup.show();
            }
        });
    }
}


