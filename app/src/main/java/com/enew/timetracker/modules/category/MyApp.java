package com.enew.timetracker.modules.category;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by TCIG_PC_54 on 3/12/2017.
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(new FlowConfig.Builder(this)
                .openDatabasesOnInit(true).build());
    }

    @Override
    public void onTerminate() {
        //destroy dbflow instance
        FlowManager.destroy();
        super.onTerminate();
    }
}
