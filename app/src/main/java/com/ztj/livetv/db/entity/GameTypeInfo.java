package com.ztj.livetv.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by zhoutianjie on 2018/8/28.
 */

@Entity(tableName = "gameTypeInfo")
public class GameTypeInfo {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private int gameId;

    private String gameTypeName;

    private String gameShortName;

    private String gameUrl;

    private String gameIcon;

    private String gameSrc;

    private int selectCount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getGameTypeName() {
        return gameTypeName;
    }

    public void setGameTypeName(String gameTypeName) {
        this.gameTypeName = gameTypeName;
    }

    public String getGameShortName() {
        return gameShortName;
    }

    public void setGameShortName(String gameShortName) {
        this.gameShortName = gameShortName;
    }

    public String getGameUrl() {
        return gameUrl;
    }

    public void setGameUrl(String gameUrl) {
        this.gameUrl = gameUrl;
    }

    public String getGameIcon() {
        return gameIcon;
    }

    public void setGameIcon(String gameIcon) {
        this.gameIcon = gameIcon;
    }

    public String getGameSrc() {
        return gameSrc;
    }

    public void setGameSrc(String gameSrc) {
        this.gameSrc = gameSrc;
    }

    public int getSelectCount() {
        return selectCount;
    }

    public void setSelectCount(int selectCount) {
        this.selectCount = selectCount;
    }

    @Override
    public String toString() {
        return "GameTypeInfo{" +
                "id=" + id +
                ", gameId=" + gameId +
                ", gameTypeName='" + gameTypeName + '\'' +
                ", gameShortName='" + gameShortName + '\'' +
                ", gameUrl='" + gameUrl + '\'' +
                ", gameIcon='" + gameIcon + '\'' +
                ", gameSrc='" + gameSrc + '\'' +
                ", selectCount=" + selectCount +
                '}';
    }
}
