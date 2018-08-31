package com.ztj.androidlib.bean;

import java.util.List;

/**
 * Created by zhoutianjie on 2018/8/28.
 */

public class GameTypes {

    private int error;

    private List<GameType> data;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public List<GameType> getData() {
        return data;
    }

    public void setData(List<GameType> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GameAllTypes{" +
                "error=" + error +
                ", data=" + data +
                '}';
    }
}
