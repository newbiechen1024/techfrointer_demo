package com.newbiechen.techfrontierdemo.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by PC on 2016/9/8.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateView();
        initWidget();
        initClick();
        processLogin(savedInstanceState);
    }


    /************************需要继承的抽象类************************************/
    /**
     * 初始化View
     */
    public abstract void onCreateView();

    /**
     * 初始化零件
     */
    public abstract void initWidget();
    /**
     * 初始化点击事件
     */
    public abstract void initClick();
    /**
     * 逻辑使用区
     */
    public abstract void processLogin(Bundle savedInstanceState);

    /**************************公共类*******************************************/
    public <VT> VT getViewById(int id){
        return (VT) findViewById(id);
    }
}
