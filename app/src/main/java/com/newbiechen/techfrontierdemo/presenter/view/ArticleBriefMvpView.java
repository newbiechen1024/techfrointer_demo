package com.newbiechen.techfrontierdemo.presenter.view;

import com.newbiechen.techfrontierdemo.beans.ArticleBrief;
import com.newbiechen.techfrontierdemo.presenter.base.MvpView;

import java.util.List;

/**
 * Created by PC on 2016/9/14.
 */
public interface ArticleBriefMvpView extends MvpView {
    //添加数据到RecyclerView
    void showArticleBrievies(List<ArticleBrief> articleBriefList);
    //清空数据
    void clearArticleBrievies();
}
