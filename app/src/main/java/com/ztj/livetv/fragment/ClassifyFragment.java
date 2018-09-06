package com.ztj.livetv.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.bumptech.glide.Glide;
import com.ztj.androidlib.fragment.BaseFragment;
import com.ztj.livetv.App;
import com.ztj.livetv.R;
import com.ztj.livetv.adapter.GameTypeAdapter;
import com.ztj.livetv.databinding.FragmentClassifyBinding;
import com.ztj.livetv.db.entity.GameTypeInfo;
import com.ztj.livetv.listener.OnItemGameTypeListener;
import com.ztj.livetv.viewmodel.ClassifyViewModel;

import java.util.List;

/**
 *
 * @author zhoutianjie
 * @date 2018/8/27
 */

public class ClassifyFragment extends BaseFragment implements OnItemGameTypeListener{

    private FragmentClassifyBinding mBinding;
    private RecyclerView recyclerView;
    private GameTypeAdapter adapter;
    private GridLayoutManager manager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manager = new GridLayoutManager(getContext(),4);
        adapter = new GameTypeAdapter(this,getContext(),null);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_classify,container,false);
        recyclerView = mBinding.classifyRecycler;
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ClassifyViewModel.Factory factory = new ClassifyViewModel.Factory(App.getAppInstance());
        ClassifyViewModel model = ViewModelProviders.of(this,factory)
                .get(ClassifyViewModel.class);
        subscribeToModel(model);
    }

    private void subscribeToModel(ClassifyViewModel model) {
        model.getGameTypeInfos().observe(this, new Observer<List<GameTypeInfo>>() {
            @Override
            public void onChanged(@Nullable List<GameTypeInfo> gameTypeInfos) {
                if(gameTypeInfos==null || gameTypeInfos.size()==0){
                    return;
                }
                updateUI(gameTypeInfos);
            }
        });
    }

    private void updateUI(List<GameTypeInfo> gameTypeInfos) {
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setGameTypeInfos(gameTypeInfos);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                Context context = getContext();
                if(context==null){
                    return;
                }
                if(newState == RecyclerView.SCROLL_STATE_IDLE){
                    Glide.with(context).resumeRequests();
                }else{
                    Glide.with(context).pauseRequests();
                }
            }
        });
    }


    @Override
    public void onclick(int gameId) {
        Log.e(TAG,""+gameId);
    }
}
