package com.newbiechen.techfrontierdemo.beans;

/**
 * Created by PC on 2016/9/9.
 */
public class ArticleInfo {
    private String post_id;
    private String content;

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ArticleInfo{" +
                "content='" + content + '\'' +
                ", post_id='" + post_id + '\'' +
                '}';
    }
}
