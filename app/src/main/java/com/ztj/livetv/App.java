package com.ztj.livetv;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.ztj.livetv.db.AppDataBase;


import com.ztj.livetv.util.SharePreferenceUtil;
import com.ztj.livetv.util.dagger2.component.DaggerAppComponent;

import javax.inject.Inject;


/**
 *
 * @author zhoutianjie
 * @date 2018/8/28
 */

public class App extends Application {

    /**
     * 这边本来是想用Dagger2框架实例化一些工具类
     * 实现过程中发现Dagger2每次都要进行一个绑定,比如在Activity里面使用就要在Activity绑定
     * 如果在其他类中使用，就要在其他类中绑定。有些不方便，这边只是单纯的用单例来实例化也可以
     * 以上的问题也许可以通过Android.dagger 解决，没有仔细学习过这个库，后面有时间再去学习，但是就算是
     * Android.dagger 还是会需要添加inject来绑定。我的初衷是：不管在哪个类里面只要添加对应的注解，就能实例化
     * 看来是我理解错了。
     * Dagger2注解框架它的好处在于:
     * 相比于直接的实例化，我们在很多activity里面都用到了一个对象，如果要改它的构造，那肯定是一个很大的工程
     * 但是用了依赖注入，只需要在修改对象的实现方法就行了。
     *
     * 专业一点讲：对象的使用和对象的实现不耦合，你在使用它的地方就只管使用它，不用关心它的构造。
     */
    @Inject
    SharePreferenceUtil sharePreferenceUtil;

    private static App sInstance = null;
    private AppDataBase dataBase;
    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        initUtils();
        initRoom();

    }



    private void initUtils(){
        DaggerAppComponent.builder().application(this).build().inject(this);
    }

    private void initRoom() {
        if(dataBase != null) {
            return;
        }
        dataBase = Room.databaseBuilder(this,AppDataBase.class,AppDataBase.DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }


    public AppDataBase getDataBase(){
        return dataBase;
    }

    public SharePreferenceUtil getSharePreferenceUtil(){
        return sharePreferenceUtil;
    }

    public static App getAppInstance(){
        return sInstance;
    }

}
