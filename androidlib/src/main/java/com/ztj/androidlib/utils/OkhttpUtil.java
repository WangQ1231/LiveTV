package com.ztj.androidlib.utils;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zhoutianjie on 2018/8/28.
 */

public class OkhttpUtil {

    private OkHttpClient client;
    private ExecutorService fixedPool;

    public OkhttpUtil() {
        client = new OkHttpClient();
        fixedPool = Executors.newFixedThreadPool(10);
    }

    private static class InnerSingleTon{
        private static final OkhttpUtil Instance = new OkhttpUtil();
    }

    public static OkhttpUtil getInstance(){
        return InnerSingleTon.Instance;
    }

    /**
     * 同步请求
     * @param url
     * @return
     */
    public Response getSyncResponse(String url){
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 异步请求
     */
    public void getAsyncResponse(String url, Callback callback){
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
