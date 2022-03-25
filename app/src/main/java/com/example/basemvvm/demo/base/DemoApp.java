package com.example.basemvvm.demo.base;

import android.app.Application;
import android.content.Context;

public class DemoApp extends Application {

    private static Context applicationContext;
    public static Context getAppContext() {
        return applicationContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        applicationContext = getApplicationContext();
    }


}
