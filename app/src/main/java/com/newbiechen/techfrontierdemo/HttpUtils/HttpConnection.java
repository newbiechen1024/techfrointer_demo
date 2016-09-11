package com.newbiechen.techfrontierdemo.httpUtils;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by PC on 2016/9/9.
 */
public class HttpConnection {
    private static final String TAG = "HttpConnection";

    private static HttpConnection sHttpConnection;
    private HttpConnection(){
    }


    public interface CallBack<T>{
        void callback(T data);
    }


    /**
     * 单例模式
     */
    public static void init(){
        if (sHttpConnection == null){
            sHttpConnection = new HttpConnection();
        }
    }

    public static HttpConnection getInstance(){
        if (sHttpConnection == null){
            init();
        }
        return sHttpConnection;
    }

    public <T> void sendGetRequest(final URL url, final Parse<T> parse, final CallBack<T> callBack){
        new AsyncTask<Void,Void,T>(){
            @Override
            protected T doInBackground(Void... params) {
                String response = null;
                HttpURLConnection connection = null;
                try {
                    //配置Connection，并连接到网络
                    connection = setUpConnection(url);
                    //获取数据
                    response = getData(connection.getInputStream());
                    Log.d(TAG,response);
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d(TAG,"连接超时");
                } finally {
                    connection.disconnect();
                }
                //解析数据
                return parse.parseData(response);
            }

            @Override
            protected void onPostExecute(T aVoid) {
                super.onPostExecute(aVoid);
                //回调。
                callBack.callback(aVoid);
            }
        }.execute();
    }

    private HttpURLConnection setUpConnection(URL url) throws IOException{
        //配置
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoInput(true);
        connection.setConnectTimeout(HttpConfig.TIME_OUT);
        connection.setReadTimeout(HttpConfig.READ_OUT);
        return connection;
    }


    private String getData(InputStream inputStream){
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream)
        );
        StringBuilder sb = new StringBuilder();
        String str = null;
        try {
            while ((str = reader.readLine()) != null){
                sb.append(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
}
