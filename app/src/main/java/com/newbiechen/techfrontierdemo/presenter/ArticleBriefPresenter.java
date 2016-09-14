package com.newbiechen.techfrontierdemo.presenter;

import android.content.Context;
import android.util.Log;

import com.newbiechen.techfrontierdemo.beans.ArticleBrief;
import com.newbiechen.techfrontierdemo.dao.ArticleBriefDao;
import com.newbiechen.techfrontierdemo.httpUtils.HttpConnection;
import com.newbiechen.techfrontierdemo.httpUtils.ParseData.ArticleBriefParse;
import com.newbiechen.techfrontierdemo.presenter.base.BasePresenter;
import com.newbiechen.techfrontierdemo.presenter.view.ArticleBriefMvpView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 2016/9/14.
 * 1、第一次加载的时候（从本地加载，然后再从网络加载）
 * 2、加载数据
 */
public class ArticleBriefPresenter extends BasePresenter<ArticleBriefMvpView>{
    private static final int ORIGIN_COUNT = 20;
    private static final int ORIGIN_OFFSET = 0;
    private static final int ORIGIN_PAGE = 1;
    private final List<ArticleBrief> mArticleBriefList = new ArrayList<>();
    private ArticleBriefDao mDao;
    private int mPage = ORIGIN_PAGE;

    public ArticleBriefPresenter(Context context){
        super(context);
        mDao = new ArticleBriefDao(context);
    }

    public void fetchLastData(){
        /**
         * 1、加载数据中
         * 2、从数据库中获取数据，
         * 3、从网络中获取数据
         * 4、展示
         */
        //表示正在加载数据
        mView.showLoading();
        //从数据库中加载数据
        mArticleBriefList.addAll(mDao.getArticleBriefs(ORIGIN_COUNT,ORIGIN_OFFSET));
        //从网络中获取数据
        loadDataFromUrl(true);
    }

    public void refreshData(){
        mPage = 1;
        mView.showLoading();
        loadDataFromUrl(true);

    }

    public void loadData(){
        loadDataFromUrl(false);
    }

    private void loadDataFromUrl(final boolean isRefresh){
        mConnection.sendGetRequest(getUrl(), new ArticleBriefParse(), new HttpConnection.CallBack<List<ArticleBrief>>() {
            @Override
            public void callback(List<ArticleBrief> data) {
                //还要判断是否是刷新，如果刷新就删除View的数据，如果不是刷新，就是加载，那么就翻页
                if (isRefresh){
                    mView.clearArticleBrievies();
                    //数据加载完成
                    mView.finishLoading();
                }
                //加载从网络中获取的数据
                mView.showArticleBrievies(data);
                //翻页
                mPage += 1;
            }
        });

    }

    private URL getUrl(){
        URL url = null;
        try {
            url = new URL("http://www.devtf.cn/api/v1/?type=articles&page=" + mPage
                    + "&count=20&category=1");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
}
