package com.newbiechen.techfrontierdemo.DataBaseUtils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by PC on 2016/9/9.
 */
public class DbOpenHelper extends SQLiteOpenHelper {
    /**
     * 数据库的基础信息
     */
    private static final int VERSION = 1;
    private static final String DB_NAME = "frontier.db";
    /**
     * Table的名字
     */
    public static final String TABLE_ARTICLE_BRIEF = "article_brief";
    public static final String TABLE_ARTICLE_INFO = "article_info";
    /**
     * Article_brief表的字段
     */
    public static final String BRIEF_POST_ID_COL = "post_id";
    public static final String BRIEF_TITLE_COL = "title";
    public static final String BRIEF_AUTHOR_COL = "author";
    public static final String BRIEF_PUBLISH_TIME_COL = "publish_time";
    public static final String BRIEF_CATEGORY_COL = "category";
    /**
     * Article_info表的字段
     */
    public static final String INFO_POST_ID_COL = "post_id";
    public static final String INFO_CONTENT_COL = "content";
    /**
     * 创建表
     */
    private static final String CREATE_TABLE_ARTICLE_INFO = "CREATE TABLE "+
            TABLE_ARTICLE_INFO+ "("+
            INFO_POST_ID_COL+" TEXT PRIMARY KEY,"+
            INFO_CONTENT_COL+" TEXT NOT NULL,"+
            "FOREIGN KEY("+INFO_POST_ID_COL+")"+" REFERENCES "+TABLE_ARTICLE_BRIEF+"("+
            BRIEF_POST_ID_COL+"));";
    private static final String CREATE_TABLE_ARTICLE_BRIEF = "create table "+
            TABLE_ARTICLE_BRIEF+" ("+BRIEF_POST_ID_COL+" TEXT PRIMARY KEY,"+
            BRIEF_TITLE_COL+" TEXT NOT NULL,"+
            BRIEF_AUTHOR_COL+" TEXT NOT NULL,"+
            BRIEF_PUBLISH_TIME_COL +" TEXT NOT NULL,"+
            BRIEF_CATEGORY_COL+" TEXT NOT NULL);";

    public DbOpenHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_key = ON");
        db.execSQL(CREATE_TABLE_ARTICLE_BRIEF);
        db.execSQL(CREATE_TABLE_ARTICLE_INFO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //删除表
        db.execSQL("drop table "+TABLE_ARTICLE_BRIEF);
        db.execSQL("drop table"+TABLE_ARTICLE_INFO);
        //重新创建表
        onCreate(db);
    }
}
