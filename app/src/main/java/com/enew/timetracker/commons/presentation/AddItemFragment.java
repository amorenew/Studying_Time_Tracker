package com.enew.timetracker.commons.presentation;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.enew.timetracker.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddItemFragment extends DialogFragment {

    @BindView(R.id.etName)
    protected EditText etName;
    private AddListener addListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().setTitle("Adding Category");
        View view = inflater.inflate(R.layout.view_add_category, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.btnAdd)
    public void addCategory(View view) {
        addListener.onAdd(etName.getText().toString());
        dismiss();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            addListener = (AddListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement AddListener");
        }
    }

    // Container Activity must implement this interface
    public interface AddListener {
        void onAdd(String text);
    }
}
