package com.newbiechen.techfrontierdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.newbiechen.techfrontierdemo.base.BaseActivity;
import com.newbiechen.techfrontierdemo.base.BaseFragment;
import com.newbiechen.techfrontierdemo.fragemnt.ArticleInfoFragment;

/**
 * Created by PC on 2016/9/9.
 *
 */
public class ArticleInfoActivity extends BaseActivity {
    private ArticleInfoFragment mArticleInfoFragment;
    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_article_info);
    }

    @Override
    protected void initWidget(Bundle savedInstanceState) {
        mArticleInfoFragment = new ArticleInfoFragment();
    }

    @Override
    protected void initClick() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void processLogin(Bundle savedInstanceState) {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .add(R.id.article_info_frame_content,mArticleInfoFragment)
                .commit();
    }
}
