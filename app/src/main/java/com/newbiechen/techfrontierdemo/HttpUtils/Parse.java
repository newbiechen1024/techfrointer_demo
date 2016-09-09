package com.newbiechen.techfrontierdemo.HttpUtils;

/**
 * Created by PC on 2016/9/9.
 */
public interface Parse<T> {
    T parseData(String response);
}
