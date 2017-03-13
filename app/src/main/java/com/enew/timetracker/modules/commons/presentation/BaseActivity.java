package com.enew.timetracker.modules.commons.presentation;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.enew.timetracker.R;
import com.enew.timetracker.modules.commons.Util;

/**
 * Created by TCIG_PC_54 on 3/7/2017.
 */

public class BaseActivity extends AppCompatActivity {
    Toolbar toolbar;

    /**
     * set toolbar title
     *
     * @param title
     */
    public void setToolbarTitle(String title) {
        ((TextView) toolbar.findViewById(R.id.tvTitle)).setText(title);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // remove focus from edit text in start of activity
        findViewById(android.R.id.content).setFocusableInTouchMode(true);
    }

    protected void addToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Check if the version of Android is Lollipop or higher
//        showTransparentStatusBar();
    }

    protected void addBackButtonWhite(View.OnClickListener clickListener) {
        addToolBar();
        toolbar.setNavigationIcon(Util.getDrawableLocale(this, R.drawable.ic_back_white));
        toolbar.setNavigationOnClickListener(clickListener);
    }

    //handle focus and keyboard when click outside edit text
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }
}
