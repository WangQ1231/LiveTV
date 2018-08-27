package com.ztj.androidlib.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by zhoutianjie on 2018/8/27.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVariables();
        initViews(savedInstanceState);
        loadData();
    }

    protected abstract void initVariables();
    protected abstract void initViews(Bundle savedInstanceState);
    protected abstract void loadData();

}
