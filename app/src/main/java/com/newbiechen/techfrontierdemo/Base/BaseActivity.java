package com.newbiechen.techfrontierdemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.newbiechen.techfrontierdemo.R;

/**
 * Created by PC on 2016/9/8.
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected Toolbar mToolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateView();
        setUpToolBar();
        initWidget();
        initClick();
        processLogin(savedInstanceState);
    }

    /**
     * 关联Toolbar到Activity上
     */
    private void setUpToolBar(){
        mToolbar = getViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle(R.string.app_title);
    }
    /************************需要继承的抽象类************************************/
    /**
     * 初始化View
     */
    protected abstract void onCreateView();

    /**
     * 初始化零件
     */
    protected abstract void initWidget();
    /**
     * 初始化点击事件
     */
    protected abstract void initClick();
    /**
     * 逻辑使用区
     */
    protected abstract void processLogin(Bundle savedInstanceState);

    /**************************公共类*******************************************/
    public <VT> VT getViewById(int id){
        return (VT) findViewById(id);
    }
}
