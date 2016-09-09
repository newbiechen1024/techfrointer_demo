package com.newbiechen.techfrontierdemo.adapters;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.newbiechen.techfrontierdemo.R;
import com.newbiechen.techfrontierdemo.beans.MenuItem;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.RecyclerView.*;
/**
 * Created by PC on 2016/9/9.
 */
public class MenuItemAdapter extends Adapter<MenuItemAdapter.MenuItemViewHolder> {
    //菜单按钮的内容
    private static final int [] MENU_ICON = {R.drawable.article,R.drawable.about,R.drawable.exit};
    private static final int [] MENU_CONTENT = {R.string.article,R.string.about,R.string.exit};
    private final List<MenuItem> mMenuItemList = new ArrayList<>();
    private OnItemClickListener mItemClickListener;
    @Override
    public MenuItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_menu_item,
                parent,false);
        return new MenuItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MenuItemViewHolder holder, int position) {
        MenuItem menuItem = mMenuItemList.get(position);
        holder.ivIcon.setImageResource(menuItem.getIconId());
        holder.tvContent.setText(menuItem.getContentId());
        setUpOnClickListener(holder.itemView,position);
    }

    /**
     * 设置监听器
     * @param view
     * @param position
     */
    private void setUpOnClickListener(final View view, final int position){
        if (mItemClickListener != null){
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.itemClick(view,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mMenuItemList.size();
    }

    class MenuItemViewHolder extends ViewHolder{
        public ImageView ivIcon;
        public TextView tvContent;
        public MenuItemViewHolder(View itemView) {
            super(itemView);
            ivIcon = getViewById(itemView,R.id.menu_item_iv_icon);
            tvContent = getViewById(itemView,R.id.menu_item_tv_content);
            //设置点击后的背景颜色
            itemView.setBackgroundResource(R.drawable.recycler_click);
        }

        public <VT> VT getViewById(View view,int id){
            return (VT) view.findViewById(id);
        }
    }

    public interface OnItemClickListener{
        void itemClick(View view,int pos);
    }
    /*************************公共方法*************************/
    public void addMenuItems(List<MenuItem> menuItems ){
        mMenuItemList.addAll(menuItems);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mItemClickListener = listener;
    }
}
