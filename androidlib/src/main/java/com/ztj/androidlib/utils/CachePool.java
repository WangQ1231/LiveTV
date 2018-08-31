package com.ztj.androidlib.utils;

import android.util.ArrayMap;

import java.util.Date;

/**
 * 自定义缓存池
 * @author zhoutianjie
 * @date 2018/8/31
 */

public class CachePool {

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





    /**
     * 缓存实体
     */
    private static class CacheItem{
        private Date createTime;
        private Object entity;


        public CacheItem(Date createTime, Object entity) {
            this.createTime = createTime;
            this.entity = entity;
        }

        public Date getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Date createTime) {
            this.createTime = createTime;
        }

        public Object getEntity() {
            return entity;
        }

        public void setEntity(Object entity) {
            this.entity = entity;
        }
    }
}
