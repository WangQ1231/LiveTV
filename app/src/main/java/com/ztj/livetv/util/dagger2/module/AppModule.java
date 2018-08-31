package com.ztj.livetv.util.dagger2.module;

import android.app.Application;
import android.content.Context;

import com.ztj.livetv.util.SharePreferenceUtil;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zhoutianjie on 2018/8/31.
 */

@Module
public class AppModule {


    @Provides
    @Singleton
    SharePreferenceUtil providesSharepreferenceUtil(Application application){
        return new SharePreferenceUtil(application);
    }
}
