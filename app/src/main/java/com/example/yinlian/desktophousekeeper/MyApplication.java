package com.example.yinlian.desktophousekeeper;

import android.app.Application;

import com.socks.library.KLog;

/**
 * Created by Luozhimin on 2018-06-21.9:31
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        KLog.init(BuildConfig.LOG_DEBUG);
    }
}
