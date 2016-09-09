package com.newbiechen.techfrontierdemo.beans;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

/**
 * Created by PC on 2016/9/9.
 */
public class MenuItem {
    private int iconId;
    private int text;


    public int getIconId() {
        return iconId;
    }

    public void setIconId(@DrawableRes int id) {
        this.iconId = id;
    }

    public int getContentId() {
        return text;
    }

    public void setContentId(@StringRes int id) {
        this.text = id;
    }
}
