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
import com.enew.timetracker.modules.category.models.LevelModel;

import java.util.Comparator;

/**
 * Created by hamoda on 4/15/2017.
 */
public class LevelAdapter extends SortedListAdapter<LevelModel> {
    private Context context;
    private RowClickListener<LevelModel> rowClickListener;

    public LevelAdapter(Context context, Comparator<LevelModel> comparator,
                        RowClickListener<LevelModel> rowClickListener) {
        super(context, LevelModel.class, comparator);
        this.context = context;
        this.rowClickListener = rowClickListener;
    }


    @Override
    protected ViewHolder<? extends LevelModel> onCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_category, parent, false);
        return new LevelViewHolder(itemView);
    }

    @Override
    protected boolean areItemContentsTheSame(LevelModel oldItem, LevelModel newItem) {
        return oldItem.equals(newItem);
    }

    @Override
    protected boolean areItemsTheSame(LevelModel item1, LevelModel item2) {
        return item1 == item2;
    }


    @Override
    public void onBindViewHolder(ViewHolder<? extends LevelModel> viewHolder, int position) {

        super.onBindViewHolder(viewHolder, position);

        final LevelViewHolder holder = (LevelViewHolder) viewHolder;
        final LevelModel levelModel = getItem(position);

        holder.tvName.setText(levelModel.getName());

        holder.tvName.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                rowClickListener.onItemClick(levelModel);

            }

        });

        holder.subMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rowClickListener.onItemMenuClick(levelModel);
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
                                // rowClickListener.onItemMenuEditClick(categoryModel);
                                break;
                            case R.id.delete:
                                //rowClickListener.onItemMenuDeleteClick(categoryModel);
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



