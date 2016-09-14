package com.newbiechen.techfrontierdemo.presenter.base;

import android.content.Context;

import com.newbiechen.techfrontierdemo.httpUtils.HttpConnection;

/**
 * Created by PC on 2016/9/14.
 */
public class BasePresenter <T extends MvpView> {
    protected T mView;
    protected Context mContent;
    protected HttpConnection mConnection = HttpConnection.getInstance();
    public BasePresenter(Context context) {
        mContent = context;
    }

    /**
     * 关联Presenter与View
     * @param view
     */
    public void attach(T view){
        mView = view;
    }

    /**
     * 解除关联
     */
    public void detch(){
        mView = null;
    }
}
