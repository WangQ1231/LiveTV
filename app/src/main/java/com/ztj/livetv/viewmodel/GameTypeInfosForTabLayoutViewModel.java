package com.ztj.livetv.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.ztj.livetv.db.AppDataBase;
import com.ztj.livetv.db.entity.GameTypeInfo;
import com.ztj.livetv.persistence.DataRespository;

import java.util.List;

/**
 * Created by zhoutianjie on 2018/8/28.
 */

public class GameTypeInfosForTabLayoutViewModel extends ViewModel {
    private int gameId;
    private LiveData<List<GameTypeInfo>> mGameTypeInfosForTabLayout;
    private AppDataBase dataBase;

    public GameTypeInfosForTabLayoutViewModel(AppDataBase dataBase) {
        this.dataBase = dataBase;
        mGameTypeInfosForTabLayout = DataRespository.getsInstance(dataBase).getmGameTypeInfosForTabLayout();

    }

    public LiveData<List<GameTypeInfo>> getmGameTypeInfosForTabLayout() {
        return mGameTypeInfosForTabLayout;
    }
}
