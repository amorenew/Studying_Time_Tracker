package com.enew.timetracker.modules.category.presentation.presenter;

import android.view.View;
import android.widget.TextView;

import com.enew.timetracker.R;
import com.enew.timetracker.commons.presentation.presenter.SortedListAdapter;
import com.enew.timetracker.modules.category.models.LevelModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hamoda on 4/15/2017.
 */

public class LevelViewHolder extends SortedListAdapter.ViewHolder<LevelModel> implements View.OnClickListener {

    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.subMenu)

    View subMenu;

// public android.view.View buttonViewOption;

    public LevelViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void performBind(LevelModel item) {

    }
}