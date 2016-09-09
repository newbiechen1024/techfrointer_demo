package com.newbiechen.techfrontierdemo;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.newbiechen.techfrontierdemo.Base.BaseActivity;

public class MainActivity extends BaseActivity {
    private Toolbar mToolbar;
    private RecyclerView mRecyclerSlideMenu;
    private DrawerLayout mDrawerLayout;
    private FrameLayout mFrameLayout;
    @Override
    public void onCreateView() {
        setContentView(R.layout.activity_main);

        mToolbar = getViewById(R.id.main_toolbar);
        mDrawerLayout = getViewById(R.id.main_drawer);
        mRecyclerSlideMenu = getViewById(R.id.drawer_recycler_menu);
        mFrameLayout = getViewById(R.id.main_frame_content);


    }

    @Override
    public void initWidget() {


    }

    @Override
    public void initClick() {

    }

    @Override
    public void processLogin(Bundle savedInstanceState) {

    }
}
