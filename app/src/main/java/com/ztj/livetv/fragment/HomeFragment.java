package com.ztj.livetv.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.ztj.androidlib.fragment.BaseFragment;
import com.ztj.livetv.R;
import com.ztj.livetv.databinding.FragmentHomeBinding;

/**
 * DataBinding and Android architecture components
 * Created by zhoutianjie on 2018/8/27.
 */

public class HomeFragment extends BaseFragment {

    private FragmentHomeBinding mHomeBinding;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG,"onCreateView");
        mHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home,container,false);

        return mHomeBinding.getRoot();
    }


}
