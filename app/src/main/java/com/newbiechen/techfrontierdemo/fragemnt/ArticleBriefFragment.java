package com.newbiechen.techfrontierdemo.fragemnt;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.newbiechen.techfrontierdemo.ArticleInfoActivity;
import com.newbiechen.techfrontierdemo.Dao.ArticleBriefDao;
import com.newbiechen.techfrontierdemo.HttpUtils.HttpConnection;
import com.newbiechen.techfrontierdemo.HttpUtils.ParseData.ArticleBriefParse;
import com.newbiechen.techfrontierdemo.R;
import com.newbiechen.techfrontierdemo.Widget.AutoLoadingRecyclerView;
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
public class ArticleBriefFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,AutoLoadingRecyclerView.OnLoadMoreListener{
    private AutoLoadingRecyclerView mRecyclerView;
    private ArticleBriefAdapter mAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ArticleBriefDao mBriefDao;
    private final Handler mHandler = new Handler();
    private int page = 1;

    @Override
    protected View onCreateContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_article_brief,container,false);
    }

    @Override
    protected void initView() {
        mSwipeRefreshLayout = getViewById(R.id.article_brief_swipe_refresh);
        mRecyclerView = getViewById(R.id.article_brief_recycler_content);
    }

    @Override
    public void onResume() {
        super.onResume();
        //先从数据库获取数据
        mAdapter.addItems(mBriefDao.getArticleBriefs("20","0"));
        //从网络获取数据
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                //自动滚动。
                mSwipeRefreshLayout.post(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(true);
                    }
                });
               //加载数据
                onRefresh();
            }
        });
    }

    @Override
    protected void initWidget() {
        mBriefDao = new ArticleBriefDao(getContext());
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
                Intent intent = new Intent(getContext(), ArticleInfoActivity.class);
                startActivity(intent);
            }
        });
        mRecyclerView.setLoaderMoreListener(this);
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
    }

    private URL getUrl(){
        URL url = null;
        try {
            url = new URL("http://www.devtf.cn/api/v1/?type=articles&page=" + page
                    + "&count=20&category=1");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    @Override
    public void onRefresh() {
        //获取第一页的内容
        page = 1;
        mConnection.sendGetRequest(getUrl(), new ArticleBriefParse(), new HttpConnection.CallBack<List<ArticleBrief>>() {
            @Override
            public void callback(List<ArticleBrief> data) {
                mAdapter.refreshItems(data);
                mBriefDao.addArticleBriefs2Db(data);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onLoadMore() {
        //获取加一页的内容
        page += 1;
        mConnection.sendGetRequest(getUrl(), new ArticleBriefParse(), new HttpConnection.CallBack<List<ArticleBrief>>() {
            @Override
            public void callback(List<ArticleBrief> data) {
                mAdapter.addItems(data);
                mBriefDao.addArticleBriefs2Db(data);
                mRecyclerView.setLoadMoreFinish(true);
            }
        });
    }
}
