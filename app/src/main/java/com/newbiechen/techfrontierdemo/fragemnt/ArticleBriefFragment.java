package com.newbiechen.techfrontierdemo.fragemnt;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.newbiechen.techfrontierdemo.ArticleInfoActivity;
import com.newbiechen.techfrontierdemo.dao.ArticleBriefDao;
import com.newbiechen.techfrontierdemo.httpUtils.HttpConnection;
import com.newbiechen.techfrontierdemo.httpUtils.ParseData.ArticleBriefParse;
import com.newbiechen.techfrontierdemo.R;
import com.newbiechen.techfrontierdemo.presenter.ArticleBriefPresenter;
import com.newbiechen.techfrontierdemo.presenter.view.ArticleBriefMvpView;
import com.newbiechen.techfrontierdemo.widget.AutoLoadingRecyclerView;
import com.newbiechen.techfrontierdemo.adapters.ArticleBriefAdapter;
import com.newbiechen.techfrontierdemo.base.BaseAdapter;
import com.newbiechen.techfrontierdemo.base.BaseFragment;
import com.newbiechen.techfrontierdemo.beans.ArticleBrief;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by PC on 2016/9/9.
 */
public class ArticleBriefFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,AutoLoadingRecyclerView.OnLoadMoreListener,ArticleBriefMvpView{
    private AutoLoadingRecyclerView mRecyclerView;
    private ArticleBriefAdapter mAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ArticleBriefPresenter mPresenter;

    @Override
    protected View onCreateContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_article_brief,container,false);
    }

    @Override
    protected void initView() {
        mSwipeRefreshLayout = getViewById(R.id.article_brief_swipe_refresh);
        mRecyclerView = getViewById(R.id.article_brief_recycler_content);
        mPresenter = new ArticleBriefPresenter(getContext());
    }

    @Override
    protected void initWidget() {
        mPresenter.attach(this);
        setUpRecyclerView();
    }

    private void setUpRecyclerView(){
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        mAdapter = new ArticleBriefAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initClick() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void itemClick(View view, int pos) {
                //跳转
                ArticleBrief articleBrief = mAdapter.getItem(pos);
                Intent intent = new Intent(getContext(), ArticleInfoActivity.class);
                intent.putExtra(ArticleInfoFragment.EXTRA_POST_ID,articleBrief.getPostId());
                startActivity(intent);
            }
        });
        mRecyclerView.setLoaderMoreListener(this);
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        //加载最新数据
        mPresenter.fetchLastData();
    }

    @Override
    public void onRefresh() {
        mPresenter.refreshData();
    }

    @Override
    public void onLoadMore() {
        mPresenter.loadData();
    }

    @Override
    public void showArticleBrievies(List<ArticleBrief> articleBriefList) {
        mAdapter.addItems(articleBriefList);
        mRecyclerView.setLoadMoreFinish(true);
    }

    @Override
    public void clearArticleBrievies() {
        mAdapter.removeItems();
    }

    @Override
    public void showLoading() {
        //自动加载动画
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });
    }

    @Override
    public void finishLoading() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detch();
    }
}
