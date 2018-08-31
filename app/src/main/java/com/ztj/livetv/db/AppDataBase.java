package com.ztj.livetv.db;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.ztj.androidlib.utils.AppExecutors;
import com.ztj.livetv.db.dao.GameTypeInfoDao;
import com.ztj.livetv.db.entity.GameTypeInfo;

import java.util.List;

/**
 * Created by zhoutianjie on 2018/8/28.
 */

@Database(entities = {GameTypeInfo.class},version = 1,exportSchema = false)
public abstract class AppDataBase extends RoomDatabase{

    public static final String DATABASE_NAME = "data-base";

    public abstract GameTypeInfoDao gameTypeInfoDao();

}
