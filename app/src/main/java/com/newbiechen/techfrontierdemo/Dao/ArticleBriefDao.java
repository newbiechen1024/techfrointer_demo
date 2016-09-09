package com.newbiechen.techfrontierdemo.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.newbiechen.techfrontierdemo.DataBaseUtils.DbOpenHelper;
import com.newbiechen.techfrontierdemo.beans.ArticleBrief;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 2016/9/9.
 */
public class ArticleBriefDao {
    private final SQLiteDatabase mDataBase;
    public ArticleBriefDao(Context context){
        mDataBase = new DbOpenHelper(context).getWritableDatabase();
    }

    public void addArticleBrief2Db(ArticleBrief articleBrief){
        ContentValues values = getBriefValues(articleBrief);
        mDataBase.insertWithOnConflict(DbOpenHelper.TABLE_ARTICLE_BRIEF,
                null,values,SQLiteDatabase.CONFLICT_REPLACE);
    }

    private ContentValues getBriefValues(ArticleBrief articleBrief){
        ContentValues values = new ContentValues();
        values.put(DbOpenHelper.BRIEF_POST_ID_COL,articleBrief.getPostId());
        values.put(DbOpenHelper.BRIEF_TITLE_COL,articleBrief.getTitle());
        values.put(DbOpenHelper.BRIEF_AUTHOR_COL,articleBrief.getAuthor());
        values.put(DbOpenHelper.BRIEF_CATEGORY_COL,articleBrief.getCategory());
        values.put(DbOpenHelper.BRIEF_PUBLISH_TIME_COL,articleBrief.getPublishTime());
        return values;
    }

    public void addArticleBriefs2Db(List<ArticleBrief> articleBriefList){
        for(ArticleBrief articleBrief : articleBriefList){
            addArticleBrief2Db(articleBrief);
        }
    }

    /**
     * 获取20条数据
     * @return
     */
    public List<ArticleBrief> getArticleBriefs(String count,String offset){
        List<ArticleBrief> articleBriefList = new ArrayList<>();
        Cursor cursor = mDataBase.query(DbOpenHelper.TABLE_ARTICLE_BRIEF,
                null,null,null,null,null,null,count+","+offset);
        //循环遍历数据
        while (cursor.moveToNext()){
            ArticleBrief brief = new ArticleBrief();
            brief.setPostId(cursor.getString(0));
            brief.setTitle(cursor.getString(1));
            brief.setAuthor(cursor.getString(2));
            brief.setPublishTime(cursor.getString(3));
            brief.setCategory(cursor.getString(4));
            articleBriefList.add(brief);
        }
        cursor.close();
        return articleBriefList;
    }
}
