package com.ztj.androidlib.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by zhoutianjie on 2018/8/28.
 */

public class GsonUtil {
    private Gson gson;

    private GsonUtil(){
        gson = new Gson();
    }

    private static class Inner{
        private static GsonUtil sInstance = new GsonUtil();
    }

    public static GsonUtil getInstance(){
        return Inner.sInstance;
    }

    public String GsonToString(Object object){
        return gson.toJson(object);
    }

    public <T> T GsonToBean(String gsonString,Class<T> tClass){
        return gson.fromJson(gsonString,tClass);
    }

    public <T> List<T> GsonToList(String gsonString,Class<T> tClass){
        return gson.fromJson(gsonString,new TypeToken<T>(){}.getType());
    }
}
