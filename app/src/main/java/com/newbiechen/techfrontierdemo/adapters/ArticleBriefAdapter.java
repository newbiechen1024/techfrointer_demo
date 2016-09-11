package com.newbiechen.techfrontierdemo.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.newbiechen.techfrontierdemo.R;
import com.newbiechen.techfrontierdemo.base.BaseAdapter;
import com.newbiechen.techfrontierdemo.beans.ArticleBrief;

import static android.support.v7.widget.RecyclerView.*;

/**
 * Created by PC on 2016/9/9.
 */
public class ArticleBriefAdapter extends BaseAdapter<ArticleBrief,ArticleBriefAdapter.ArticleBriefViewHolder> {

    @Override
    public ArticleBriefViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.adapter_article_brief,
                parent,false
        );
        return new ArticleBriefViewHolder(view);
    }

    @Override
    public void setUpViewHolder(ArticleBriefViewHolder holder, int position) {
        //获取数据
        ArticleBrief articleBrief = mItemList.get(position);
        //填充数据
        holder.tvTitle.setText(articleBrief.getTitle());
        holder.tvAuthor.setText(articleBrief.getAuthor());
        holder.tvDate.setText(articleBrief.getPublishTime());
    }

    class ArticleBriefViewHolder extends ViewHolder{
        public TextView tvTitle;
        public TextView tvAuthor;
        public TextView tvDate;
        public ArticleBriefViewHolder(View itemView) {
            super(itemView);
            tvTitle = getViewById(itemView,R.id.article_brief_tv_title);
            tvAuthor = getViewById(itemView,R.id.article_brief_tv_author);
            tvDate = getViewById(itemView,R.id.article_brief_tv_date);
        }

        public <VT> VT getViewById(View view ,int id){
            return (VT) view.findViewById(id);
        }
    }
}
