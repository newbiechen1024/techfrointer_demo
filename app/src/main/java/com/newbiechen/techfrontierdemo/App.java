package com.newbiechen.techfrontierdemo;

import android.app.Application;

import com.newbiechen.techfrontierdemo.HttpUtils.HttpConnection;

/**
 * Created by PC on 2016/9/9.
 */
public class App extends Application {
    /**
     * 初始化工具类
     */
    @Override
    public void onCreate() {
        super.onCreate();
        HttpConnection.init();
    }
}
