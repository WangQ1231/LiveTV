package com.ztj.androidlib.utils;

import java.util.Date;

/**
 * Created by zhoutianjie on 2018/8/31.
 */

public class CacheItem {

    private Date createTime;
    private Object entity;

    private long timeOut;


    public CacheItem(Date createTime, Object entity,long timeOut) {
        this.createTime = createTime;
        this.entity = entity;
        this.timeOut = timeOut;
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

    public void setTimeOut(long timeOut) {
        this.timeOut = timeOut;
    }

    public boolean isTimeOut(){
        return (System.currentTimeMillis()-createTime.getTime())>timeOut;
    }
}
