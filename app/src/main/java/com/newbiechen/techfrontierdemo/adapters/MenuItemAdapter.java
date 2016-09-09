package com.newbiechen.techfrontierdemo.adapters;

import android.view.LayoutInflater;
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

    public MenuItemAdapter() {
        //添加菜单的数据
        for (int i=0; i< MENU_ICON.length; ++i){
            MenuItem menuItem = new MenuItem();
            menuItem.setIconId(MENU_ICON[i]);
            menuItem.setContentId(MENU_CONTENT[i]);
            mMenuItemList.add(menuItem);
        }
    }

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
        }

        public <VT> VT getViewById(View view,int id){
            return (VT) view.findViewById(id);
        }
    }
}
