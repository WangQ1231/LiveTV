package com.ztj.livetv.util.dagger2.component;

import android.app.Application;

import com.ztj.livetv.App;
import com.ztj.livetv.util.dagger2.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

/**
 * Created by zhoutianjie on 2018/8/31.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    @Component.Builder
    interface  Builder{

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    /**
     * 这里是一个坑 这里的绑定类型必须是子类App而不能填Application
     * 不然会出现注入事变的情形
     * @param application
     */
    void inject(App application);
}
