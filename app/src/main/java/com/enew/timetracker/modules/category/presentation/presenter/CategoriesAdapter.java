package com.enew.timetracker.modules.category.presentation.presenter;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.enew.timetracker.R;
import com.enew.timetracker.commons.presentation.presenter.SortedListAdapter;
import com.enew.timetracker.modules.category.models.CategoryModel;

import java.util.Comparator;

/**
 * Created by TCIG_PC_54 on 7/11/2016.
 */

public class CategoriesAdapter extends SortedListAdapter<CategoryModel> {

    public TextView buttonViewOption;
    private Context context;

    public CategoriesAdapter(Context context, Comparator<CategoryModel> comparator) {
        super(context, CategoryModel.class, comparator);
        this.context = context;
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
        CategoryModel categoryModel = getItem(position);
        holder.tvName.setText(categoryModel.getName());

        holder.buttonViewOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context.getApplicationContext(), " Don Don Don ", Toast.LENGTH_SHORT).show();
                PopupMenu popup = new PopupMenu(context, holder.buttonViewOption);
                //inflating menu from xml resource

                popup.inflate(R.menu.options_menu);

                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu1:
                                //handle menu1 click
                                break;
                            case R.id.menu2:
                                //handle menu2 click
                                break;
                            case R.id.menu3:
                                //handle menu3 click
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


