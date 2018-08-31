package com.ztj.androidlib.utils;

import android.util.ArrayMap;

import java.util.Date;

/**
 * 自定义缓存池(考虑超时判断)
 * @author zhoutianjie
 * @date 2018/8/31
 */

public class CachePool {

    private static final long TIME_OUT = 60*1000;
    private ArrayMap<String,CacheItem> mPool;

    private CachePool(){
        mPool = new ArrayMap<>();
    }

    private static class Inner{
        private static CachePool INSTANCE = new CachePool();
    }

    public static CachePool getInstance(){
        return Inner.INSTANCE;
    }


//    public Object getCache(String key){
//
//
//    }

    public void putCache(String key,Object object){
        putCache(key,object,TIME_OUT);
    }

    public void putCache(String key,Object object,long timeOut){
        if(!mPool.containsKey(key)){
            CacheItem cacheItem = new CacheItem(new Date(),object,timeOut);
            mPool.put(key,cacheItem);
        }else{
            CacheItem cacheItem = mPool.get(key);
            cacheItem.setTimeOut(timeOut);
            cacheItem.setCreateTime(new Date());
            cacheItem.setEntity(object);
        }
    }

    public void clearCache(String key){

    }

    public void clearPool(){
        mPool.clear();
    }


}
