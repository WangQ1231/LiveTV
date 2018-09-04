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

import com.google.gson.Gson;
import com.ztj.androidlib.bean.DouYu;
import com.ztj.androidlib.bean.GameType;
import com.ztj.androidlib.bean.GameTypes;
import com.ztj.androidlib.utils.CachePool;
import com.ztj.androidlib.utils.GsonUtil;
import com.ztj.androidlib.utils.OkhttpUtil;
import com.ztj.livetv.App;
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
    private final static String ALL_GAMETYPEINFO_KEY = "all_game_type_info";
    private final static String GAMTYPEINFO_FOR_TAB_KEY = "game_type_info_tab";

    /**
     * 所有游戏分类的显示内存缓存和本地缓存设置为1小时的过期时间，因为所有游戏分类不经常改变
     */
    private final static long ALL_GAMETYPE_OUT_TIME = 60*1000*60;

    private volatile static DataRespository sInstance = null;

    private MutableLiveData<List<GameTypeInfo>> mObserverAllGameTypeInfos;

    private MutableLiveData<List<GameTypeInfo>> mObserverForTabLayoutGameTypeInfos;


    private AppDataBase dataBase;

    private DataRespository(AppDataBase dataBase){
        Log.e("DataRespository","construct");
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

        //先从内存缓存拿
        List<GameTypeInfo> memoryCache = (List<GameTypeInfo>) CachePool.getInstance().getCache(ALL_GAMETYPEINFO_KEY);
        if(memoryCache!=null && memoryCache.size()!=0){
            Log.e("DataRespository","from memory cache");
            mObserverAllGameTypeInfos.setValue(memoryCache);
            return mObserverAllGameTypeInfos;
        }

        //内存缓存拿不到再从本地缓存拿
        List<GameTypeInfo> nativeCache = dataBase.gameTypeInfoDao().loadAllGameTypeinfos();
        if(nativeCache!=null && nativeCache.size()!=0 && !App.getAppInstance().getSharePreferenceUtil()
                .isTimeOut(ALL_GAMETYPE_OUT_TIME)){
            Log.e("DataRespository","from memory native");
            mObserverAllGameTypeInfos.setValue(memoryCache);
            CachePool.getInstance().putCache(ALL_GAMETYPEINFO_KEY,nativeCache,ALL_GAMETYPE_OUT_TIME);
            return mObserverAllGameTypeInfos;
        }

        //本地缓存拿不到，或者本地缓存超时，则再进行网络请求
        String url = DouYu.DOUYV_ROOMAPI_URL+"game";
        OkhttpUtil.getInstance().getAsyncResponse(url, new OkhttpUtil.AsyncCallback() {
            @Override
            public void onFailure(String errorMsg) {
                //请求失败
            }

            @Override
            public void onSuccess(String json) {
                Log.e("DataRespository","from network");
                GameTypes gameTypes = GsonUtil.getInstance().GsonToBean(json,GameTypes.class);
                List<GameTypeInfo> result = convertToGameTypeInfos(gameTypes);
                mObserverAllGameTypeInfos.postValue(result);

                if(result!=null){
                    CachePool.getInstance().putCache(ALL_GAMETYPEINFO_KEY,result,ALL_GAMETYPE_OUT_TIME);
                    dataBase.gameTypeInfoDao().insertAll(result);
                    App.getAppInstance().getSharePreferenceUtil().putCreateTime(System.currentTimeMillis());
                }
            }
        });

        return mObserverAllGameTypeInfos;

    }


    /**
     * 获取主界面TabLayout上面显示的游戏类型(选取经常读取的几个item作为标题)
     * @return
     */
    public LiveData<List<GameTypeInfo>> getmGameTypeInfosForTabLayout(){
        Log.e("DataRespository","getmGameTypeInfosForTabLayout");
        List<GameTypeInfo> memoryCache = (List<GameTypeInfo>) CachePool.getInstance().getCache(GAMTYPEINFO_FOR_TAB_KEY);
        if(memoryCache!=null && memoryCache.size()!=0){
            Log.e("DataRespository","tab content from memory cache");
            mObserverForTabLayoutGameTypeInfos.setValue(memoryCache);
            return mObserverForTabLayoutGameTypeInfos;
        }

        List<GameTypeInfo> nativeCache = dataBase.gameTypeInfoDao().loadAllGameTypeinfos();
        if(nativeCache!=null && nativeCache.size()>0 && !App.getAppInstance().getSharePreferenceUtil().isTimeOut(ALL_GAMETYPE_OUT_TIME)){
            Log.e("DataRespository","tab content from native native");
            List<GameTypeInfo> result = AlgorithmUtil.getKthofGameTypeInfo(nativeCache,GAME_TYPE_FOR_TABLAYOUT);
            mObserverForTabLayoutGameTypeInfos.setValue(result);
            CachePool.getInstance().putCache(GAMTYPEINFO_FOR_TAB_KEY,result);
            return mObserverForTabLayoutGameTypeInfos;
        }


        String url = DouYu.DOUYV_ROOMAPI_URL+"game";
        OkhttpUtil.getInstance().getAsyncResponse(url, new OkhttpUtil.AsyncCallback() {
            @Override
            public void onFailure(String errorMsg) {
                Log.e("DataRespository","tab content from network error");
            }

            @Override
            public void onSuccess(String json) {
                Log.e("DataRespository","tab content from network");
                GameTypes gameTypes = GsonUtil.getInstance().GsonToBean(json,GameTypes.class);
                List<GameTypeInfo> resultFormNet = convertToGameTypeInfos(gameTypes);
                List<GameTypeInfo> result = AlgorithmUtil.getKthofGameTypeInfo(resultFormNet,GAME_TYPE_FOR_TABLAYOUT);
                mObserverForTabLayoutGameTypeInfos.postValue(result);

                CachePool.getInstance().putCache(GAMTYPEINFO_FOR_TAB_KEY,result);
                dataBase.gameTypeInfoDao().insertAll(resultFormNet);
                App.getAppInstance().getSharePreferenceUtil().putCreateTime(System.currentTimeMillis());
            }
        });



        return mObserverForTabLayoutGameTypeInfos;
    }

    /**
     * 转换数据类型
     * @param gameAllTypes
     * @return
     */
    private List<GameTypeInfo> convertToGameTypeInfos(GameTypes gameAllTypes) {
        List<GameTypeInfo> result = new ArrayList<>();
        List<GameType> data = gameAllTypes.getData();
        for(GameType gameType:data){
            GameTypeInfo info = new GameTypeInfo();
            info.setGameId(gameType.getGameId());
            info.setGameTypeName(gameType.getGameName());
            info.setGameIcon(gameType.getGameIcon());
            info.setGameShortName(gameType.getGameShortName());
            info.setGameSrc(gameType.getGameSrc());
            info.setGameUrl(gameType.getGameUrl());
            info.setSelectCount(0);
            result.add(info);
        }
        return result;
    }


}
