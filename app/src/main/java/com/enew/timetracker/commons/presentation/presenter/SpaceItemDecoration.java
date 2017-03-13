package com.enew.timetracker.commons.presentation.presenter;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by TCIG_PC_54 on 7/23/2016.
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    private final int mSpaceLeft, mSpaceTop, mSpaceRight, mSpaceBottom;

    public SpaceItemDecoration(int mSpaceLeft, int mSpaceTop, int mSpaceRight, int mSpaceBottom) {
        this.mSpaceTop = mSpaceTop;
        this.mSpaceBottom = mSpaceBottom;
        this.mSpaceLeft = mSpaceLeft;
        this.mSpaceRight = mSpaceRight;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        outRect.left = mSpaceLeft;
        outRect.top = mSpaceTop;
        outRect.right = mSpaceRight;
        outRect.bottom = mSpaceBottom;

    }
}