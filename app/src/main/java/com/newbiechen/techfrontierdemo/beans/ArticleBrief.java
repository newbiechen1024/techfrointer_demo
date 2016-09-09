package com.newbiechen.techfrontierdemo.beans;

/**
 * Created by PC on 2016/9/9.
 */
public class ArticleBrief {

    private String post_id;
    private String title;
    private String author;
    private String category;
    private String publishTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getPostId() {
        return post_id;
    }

    public void setPostId(String post_id) {
        this.post_id = post_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "ArticleInfo{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", publishTime=" + publishTime +
                '}';
    }
}
