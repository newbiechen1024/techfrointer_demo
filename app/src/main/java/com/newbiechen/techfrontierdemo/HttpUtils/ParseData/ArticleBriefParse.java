package com.newbiechen.techfrontierdemo.httpUtils.ParseData;

import com.newbiechen.techfrontierdemo.httpUtils.Parse;
import com.newbiechen.techfrontierdemo.beans.ArticleBrief;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 2016/9/9.
 */
public class ArticleBriefParse implements Parse<List<ArticleBrief>> {

    @Override
    public List<ArticleBrief> parseData(String response) {
        List<ArticleBrief> articleBriefList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(response);
            for (int i=0; i<jsonArray.length(); ++i){
                JSONObject obj = jsonArray.getJSONObject(i);
                //解析数据
                ArticleBrief articleBrief = parseItem(obj);
                articleBriefList.add(articleBrief);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return articleBriefList;
    }

    private ArticleBrief parseItem(JSONObject obj){
        ArticleBrief articleBrief = new ArticleBrief();
        articleBrief.setTitle(obj.optString("title"));
        articleBrief.setAuthor(obj.optString("author"));
        articleBrief.setPostId(obj.optString("post_id"));
        articleBrief.setCategory(obj.optString("category"));
        articleBrief.setPublishTime(obj.optString("date"));
        return articleBrief;
    }

}
