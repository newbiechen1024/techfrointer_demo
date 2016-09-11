package com.newbiechen.techfrontierdemo;

import android.app.Application;

import com.newbiechen.techfrontierdemo.httpUtils.HttpConnection;
import com.newbiechen.techfrontierdemo.utils.ToastUtils;

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
        ToastUtils.init(getApplicationContext());
    }
}
