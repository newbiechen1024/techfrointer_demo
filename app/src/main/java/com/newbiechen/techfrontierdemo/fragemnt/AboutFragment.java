package com.newbiechen.techfrontierdemo.fragemnt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.newbiechen.techfrontierdemo.R;
import com.newbiechen.techfrontierdemo.base.BaseFragment;

/**
 * Created by PC on 2016/9/9.
 */
public class AboutFragment extends BaseFragment {

    @Override
    protected View onCreateContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about,container,false);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initWidget() {

    }

    @Override
    protected void initClick() {

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }
}
