package com.newbiechen.techfrontierdemo.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by PC on 2016/9/9.
 */
public final class ToastUtils {
    private static Context sContext;

    private ToastUtils(){
    }

    public static void init(Context context){
        sContext = context;
    }

    public static void makeText(String content,int time){
        Toast.makeText(sContext,content,time).show();
    }


}
