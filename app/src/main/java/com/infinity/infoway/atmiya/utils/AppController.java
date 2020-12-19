package com.infinity.infoway.atmiya.utils;

import android.app.Application;
import android.content.Context;

public class AppController extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        AppController.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return AppController.context;
    }

}
