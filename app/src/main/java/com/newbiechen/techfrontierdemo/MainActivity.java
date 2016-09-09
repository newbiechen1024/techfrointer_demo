package com.newbiechen.techfrontierdemo;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.newbiechen.techfrontierdemo.adapters.MenuItemAdapter;
import com.newbiechen.techfrontierdemo.base.BaseActivity;
import com.newbiechen.techfrontierdemo.beans.MenuItem;
import com.newbiechen.techfrontierdemo.fragemnt.AboutFragment;
import com.newbiechen.techfrontierdemo.fragemnt.ArticleBriefFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class MainActivity extends BaseActivity {
    private Toolbar mToolbar;
    private RecyclerView mRecyclerSlideMenu;
    private MenuItemAdapter mMenuItemAdapter;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ArticleBriefFragment mArticleBriefFragment;
    private AboutFragment mAboutFragment;
    @Override
    protected void onCreateView() {
        setContentView(R.layout.activity_main);

        mToolbar = getViewById(R.id.toolbar);
        mDrawerLayout = getViewById(R.id.main_drawer);
        mRecyclerSlideMenu = getViewById(R.id.drawer_recycler_menu);
    }

    @Override
    protected void initWidget() {
        mArticleBriefFragment = new ArticleBriefFragment();
        mAboutFragment = new AboutFragment();
        setUpDrawerToggle();
        setUpMenuItemAdapter();
    }

    /**
     * 在ActionBar上设置DrawerLayout的打开关闭设定
     */
    private void setUpDrawerToggle(){
        /**知识点：toggle是ActionBar与DrawerLayout产生联系
         * 能够控制DrawerLayout的打开与关闭
         * */
        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,
                R.string.drawer_open,
                R.string.drawer_close);
        //表示是否显示默认的home键
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //表示home键是否可点击。
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerLayout.addDrawerListener(mDrawerToggle);

    }

    /**
     * 设置menuItemAdapter的内容。
     */
    private void setUpMenuItemAdapter(){
        mMenuItemAdapter = new MenuItemAdapter();
        //配置Recycler
        mRecyclerSlideMenu.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerSlideMenu.setAdapter(mMenuItemAdapter);
        addMenuItems();
    }

    private void addMenuItems(){
        //添加数据到
        List<MenuItem> menuItemList = new ArrayList<>();
        menuItemList.add(new MenuItem(R.drawable.article,R.string.article));
        menuItemList.add(new MenuItem(R.drawable.about,R.string.about));
        menuItemList.add(new MenuItem(R.drawable.exit,R.string.exit));
        mMenuItemAdapter.addMenuItems(menuItemList);
    }

    @Override
    protected void initClick() {
        //设置左上角的Home键的点击事件
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)){
                    mDrawerLayout.closeDrawers();
                }
                else {
                    mDrawerLayout.openDrawer(Gravity.LEFT);
                }
            }
        });
        //设置菜单点击的监听
        mMenuItemAdapter.setOnItemClickListener(new MenuItemAdapter.OnItemClickListener() {
            @Override
            public void itemClick(View view, int pos) {

                switch (pos){
                    case 0:
                        //将当前页面改为ArticleFragment
                        replaceFragment(mArticleBriefFragment);
                        //关闭DrawerLayout
                        mDrawerLayout.closeDrawers();
                        break;
                    case 1:
                        //将当前页面改为ArticleFragment
                        replaceFragment(mAboutFragment);
                        mDrawerLayout.closeDrawers();
                        break;
                    case 2:
                        finish();
                        break;
                }
            }
        });

    }

    private void replaceFragment(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.main_frame_content,fragment)
                .commit();
    }

    @Override
    protected void processLogin(Bundle savedInstanceState) {
        //设置内容显示的初始化Fragment
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .add(R.id.main_frame_content,mArticleBriefFragment)
                .commit();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // 在onRestoreInstanceState发生后，同步触发器状态.
        mDrawerToggle.syncState();
    }
}
