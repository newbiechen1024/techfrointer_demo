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
        onCreateView(savedInstanceState);
        setUpToolBar();
        initWidget(savedInstanceState);
        initClick();
        processLogin(savedInstanceState);
    }

    /**
     * 关联Toolbar到Activity上
     */
    private void setUpToolBar(){
        mToolbar = getViewById(R.id.toolbar);
        //将Toolbar关联到Activity,记得先将Activity自带的Toolbar取消掉
        setSupportActionBar(mToolbar);
        mToolbar.setTitle(R.string.app_title);
        //表示是否显示默认的home键
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    /************************需要继承的抽象类************************************/
    /**
     * 初始化View
     */
    protected abstract void onCreateView(Bundle savedInstanceState);

    /**
     * 初始化零件
     */
    protected abstract void initWidget(Bundle savedInstanceState);
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
