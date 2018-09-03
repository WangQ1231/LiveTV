package com.ztj.livetv.viewmodel;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.util.Log;

import com.ztj.livetv.App;
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
    private App application;

    public GameTypeInfosForTabLayoutViewModel(App application) {
        this.application = application;
        mGameTypeInfosForTabLayout = DataRespository.getsInstance(application.getDataBase()).getmGameTypeInfosForTabLayout();

    }

    public LiveData<List<GameTypeInfo>> getmGameTypeInfosForTabLayout() {
        return mGameTypeInfosForTabLayout;
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory{

        @NonNull
        private final App app;

        public Factory(@NonNull App app) {
            this.app = app;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T)new GameTypeInfosForTabLayoutViewModel(app);
        }
    }
}
