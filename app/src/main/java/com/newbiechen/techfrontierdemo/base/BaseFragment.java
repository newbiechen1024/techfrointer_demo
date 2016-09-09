package com.newbiechen.techfrontierdemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.newbiechen.techfrontierdemo.HttpUtils.HttpConnection;

/**
 * Created by PC on 2016/9/9.
 */
public abstract class BaseFragment extends Fragment {
    protected View view ;
    protected HttpConnection mConnection = HttpConnection.getInstance();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = onCreateContentView(inflater,container,savedInstanceState);
        initView();
        initWidget();
        initClick();
        processLogic(savedInstanceState);
        return view;
    }

    public <VT> VT getViewById(int id){
        return (VT) view.findViewById(id);
    }

    protected abstract View onCreateContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);
    protected abstract void initView();
    protected abstract void initWidget();
    protected abstract void initClick();
    protected abstract void processLogic(Bundle savedInstanceState);

}
