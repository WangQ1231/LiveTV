package com.ztj.livetv.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.ztj.livetv.App;
import com.ztj.livetv.db.entity.GameTypeInfo;
import com.ztj.livetv.persistence.DataRespository;

import java.util.List;

/**
 * Created by zhoutianjie on 2018/9/4.
 */

public class ClassifyViewModel extends ViewModel {

    private LiveData<List<GameTypeInfo>> gameTypeInfos;
    private App application;

    public ClassifyViewModel(App application) {
        this.application = application;
        gameTypeInfos = DataRespository.getsInstance(application.getDataBase()).getGameTypeInfo();
    }

    public LiveData<List<GameTypeInfo>> getGameTypeInfos() {
        return gameTypeInfos;
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
            return (T)new ClassifyViewModel(app);
        }
    }
}
