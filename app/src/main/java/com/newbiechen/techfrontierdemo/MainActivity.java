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
import android.view.View;

import com.newbiechen.techfrontierdemo.adapters.MenuItemAdapter;
import com.newbiechen.techfrontierdemo.base.BaseActivity;
import com.newbiechen.techfrontierdemo.beans.MenuItem;
import com.newbiechen.techfrontierdemo.fragemnt.AboutFragment;
import com.newbiechen.techfrontierdemo.fragemnt.ArticleBriefFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    //当app重新创建的时候的bundle
    private static final String BUNDLE_FRAGMENT = "current_fragment";

    private Toolbar mToolbar;
    private RecyclerView mRecyclerSlideMenu;
    private MenuItemAdapter mMenuItemAdapter;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ArticleBriefFragment mArticleBriefFragment;
    private AboutFragment mAboutFragment;
    //当前的Fragment
    private Fragment mCurrentFragment;

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);

        mToolbar = getViewById(R.id.toolbar);
        mDrawerLayout = getViewById(R.id.main_drawer);
        mRecyclerSlideMenu = getViewById(R.id.drawer_recycler_menu);
    }

    @Override
    protected void initWidget(Bundle savedInstanceState) {
        setUpDrawerToggle();
        setUpMenuItemAdapter();
        setUpFragments(savedInstanceState);
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

    private void setUpFragments(Bundle savedInstanceState){
        /**
         * 防止产生Fragment的重影
         */
        if (savedInstanceState == null){
            //自己创建
            mArticleBriefFragment = new ArticleBriefFragment();
            mAboutFragment = new AboutFragment();
            //将Fragment添加到FragmentManager中，并显示
            addFragment();
        }
        else {
            //当Activity重新创建的时候，从FragmentManager中获取Fragment，不要自己重新创建
            //否则会造成重影的问题
            mArticleBriefFragment = (ArticleBriefFragment) getSupportFragmentManager().
                    findFragmentByTag(ArticleBriefFragment.class.getSimpleName());
            mAboutFragment = (AboutFragment) getSupportFragmentManager().
                    findFragmentByTag(AboutFragment.class.getSimpleName());
            //当恢复的时候，当前显示的Fragment
            mCurrentFragment = getSupportFragmentManager().
                    findFragmentByTag(savedInstanceState.getString(BUNDLE_FRAGMENT));
        }
    }

    /**
     * 添加侧滑栏的菜单按钮
     */
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
                        changeFragment(mArticleBriefFragment);
                        //关闭DrawerLayout
                        mDrawerLayout.closeDrawers();
                        break;
                    case 1:
                        //将当前页面改为ArticleFragment
                        changeFragment(mAboutFragment);
                        mDrawerLayout.closeDrawers();
                        break;
                    case 2:
                        finish();
                        break;
                }
            }
        });

    }

    private void addFragment(){
        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_frame_content,mArticleBriefFragment,ArticleBriefFragment.class.getSimpleName())
                .add(R.id.main_frame_content,mAboutFragment,AboutFragment.class.getSimpleName())
                .hide(mAboutFragment)
                .commit();
        mCurrentFragment = mArticleBriefFragment;
    }

    /**
     * 切换Fragment
     * @param fragment
     */
    private void changeFragment(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .hide(mCurrentFragment)
                .show(fragment)
                .commit();
        mCurrentFragment = fragment;
    }

    @Override
    protected void processLogin(Bundle savedInstanceState) {
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // 在onRestoreInstanceState发生后，同步触发器状态.
        mDrawerToggle.syncState();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(BUNDLE_FRAGMENT,mCurrentFragment.getTag());
    }
}
