package com.ztj.livetv.persistence;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.util.ArrayMap;
import android.util.Log;
import android.util.SparseArray;

import com.ztj.androidlib.bean.GameType;
import com.ztj.livetv.db.AppDataBase;
import com.ztj.livetv.db.entity.GameTypeInfo;
import com.ztj.livetv.util.AlgorithmUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoutianjie on 2018/8/28.
 */

public class DataRespository {

    private final static int GAME_TYPE_FOR_TABLAYOUT = 8;
    private final static int ALL_GAMETYPEINFO_KEY = 0;
    private final static int GAMTYPEINFO_FOR_TAB = 1;

    private volatile static DataRespository sInstance = null;

    private MutableLiveData<List<GameTypeInfo>> mObserverAllGameTypeInfos;

    private MutableLiveData<List<GameTypeInfo>> mObserverForTabLayoutGameTypeInfos;


    private AppDataBase dataBase;

    private DataRespository(AppDataBase dataBase){
        this.dataBase = dataBase;
        mObserverAllGameTypeInfos = new MutableLiveData<>();
        mObserverForTabLayoutGameTypeInfos = new MutableLiveData<>();
    }

    public static DataRespository getsInstance(AppDataBase dataBase){
        if(sInstance == null){
            synchronized (DataRespository.class){
                if(sInstance == null){
                    sInstance = new DataRespository(dataBase);
                }
            }
        }
        return sInstance;
    }

    /**
     * 先从内存缓存中获取，再从本地获取，最后去请求网络
     * 获取所有游戏类型
     * @return
     */
    public LiveData<List<GameTypeInfo>> getGameTypeInfo(){

        //先从缓存拿

        //再从room拿


        return mObserverAllGameTypeInfos;

    }


    public LiveData<List<GameTypeInfo>> getmGameTypeInfosForTabLayout(){


        return mObserverForTabLayoutGameTypeInfos;
    }


}
