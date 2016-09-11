package com.newbiechen.techfrontierdemo.fragemnt;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.newbiechen.techfrontierdemo.R;
import com.newbiechen.techfrontierdemo.base.BaseFragment;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by PC on 2016/9/9.
 */
public class ArticleInfoFragment extends BaseFragment {
    public static final String EXTRA_POST_ID = "post_id";

    private WebView mWebView;
    private ProgressBar mProgressBar;
    @Override
    protected View onCreateContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_article_info,container,false);
    }

    @Override
    protected void initView() {
        mWebView = getViewById(R.id.article_info_wv_content);
        mProgressBar = getViewById(R.id.article_info_pb);
    }

    @Override
    protected void initWidget() {
        //初始化WebView
        mWebView.getSettings().setJavaScriptEnabled(true);
    }

    @Override
    protected void initClick() {
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //是否允许缩放
                mWebView.getSettings().setBuiltInZoomControls(true);
                view.loadUrl(url);
                return true;
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                mProgressBar.setVisibility(View.VISIBLE);
                mProgressBar.setProgress(newProgress);
                if (mProgressBar.getProgress() == 100){
                    mProgressBar.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        Intent intent = getActivity().getIntent();
        String post_id = intent.getStringExtra(EXTRA_POST_ID);
        String url = getUrlPath(post_id);
        mWebView.loadUrl(url);
    }

    private String getUrlPath(String post_id){
        return "http://www.devtf.cn/?p="+post_id;
    }
}
