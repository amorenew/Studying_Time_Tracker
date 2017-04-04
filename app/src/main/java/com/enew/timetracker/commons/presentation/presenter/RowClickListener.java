package com.enew.timetracker.commons.presentation.presenter;

/**
 * Created by amor on 4/4/2017.
 */

public interface RowClickListener<T> {

    void onItemClick(T model);

    void onItemMenuClick(T model);

    void onItemMenuEditClick(T model);

    void onItemMenuDeleteClick(T model);
}
