package com.ztj.androidlib.utils;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by zhoutianjie on 2018/8/28.
 */

public class OkhttpUtil {

    private OkHttpClient client;
    private ExecutorService fixedPool;

    public OkhttpUtil() {
        client = new OkHttpClient();
        ThreadFactory defalltThreadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(@NonNull Runnable r) {
                return new Thread();
            }
        };
        fixedPool = new ThreadPoolExecutor(5,200,0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024),defalltThreadFactory,
                new ThreadPoolExecutor.AbortPolicy());
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

    public void getAsyncResponse(String url, final AsyncCallback callback){
        final Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    ResponseBody body = response.body();
                    if(body!=null){
                        String json = body.string();
                        callback.onSuccess(json);
                    }else {
                        callback.onFailure(response.message());
                    }
                }else{
                    callback.onFailure(response.message());
                }
            }
        });
    }

    public interface AsyncCallback{
        /**
         * 错误信息
         * @param errorMsg
         */
        void onFailure(String errorMsg);

        /**
         * 成功返回json
         * @param json
         */
        void onSuccess(String json);
    }
}
