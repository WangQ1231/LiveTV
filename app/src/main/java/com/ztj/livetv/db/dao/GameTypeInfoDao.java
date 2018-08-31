package com.ztj.livetv.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.ztj.livetv.db.entity.GameTypeInfo;

import java.util.List;

/**
 * Created by zhoutianjie on 2018/8/28.
 */

@Dao
public interface GameTypeInfoDao {
    @Query("SELECT * FROM gameTypeInfo")
    List<GameTypeInfo> loadAllGameTypeinfos();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<GameTypeInfo> gameTypeInfos);

    @Query("SELECT * FROM gameTypeInfo WHERE gameId = :gameId")
    GameTypeInfo loadGameTypeInfo(int gameId);

    @Update
    void updateGameTypeInfo(GameTypeInfo... gameTypeInfos);

    @Update
    void updateGameTypeInfo(List<GameTypeInfo> gameTypeInfos);

    @Delete
    void deleteGameTypeInfo(GameTypeInfo... gameTypeInfos);

    @Delete
    void deleteGameTypeInfo(List<GameTypeInfo> gameTypeInfos);

}
