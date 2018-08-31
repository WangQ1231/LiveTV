package com.ztj.livetv.util;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;



/**
 *
 * @author zhoutianjie
 * @date 2018/8/31
 */

public class SharePreferenceUtil {

    private volatile static SharePreferenceUtil sInstance = null;
    private final static String LIVETV_SP = "live_tv";
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;


    public SharePreferenceUtil(Application application) {
        preferences = application.getApplicationContext().getSharedPreferences(LIVETV_SP, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void putLong(String key,long value){
        editor.putLong(key,value);
        editor.commit();
    }

    public long getLong(String key){
        return preferences.getLong(key,0);
    }

}
