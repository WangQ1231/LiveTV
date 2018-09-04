package com.ztj.livetv.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.ztj.androidlib.bean.DouYu;
import com.ztj.androidlib.fragment.BaseFragment;
import com.ztj.livetv.App;
import com.ztj.livetv.R;
import com.ztj.livetv.adapter.ContentFragmentAdapter;
import com.ztj.livetv.databinding.FragmentHomeBinding;
import com.ztj.livetv.db.AppDataBase;
import com.ztj.livetv.db.entity.GameTypeInfo;
import com.ztj.livetv.persistence.DataRespository;
import com.ztj.livetv.viewmodel.GameTypeInfosForTabLayoutViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * DataBinding and Android architecture components
 *
 * @author zhoutianjie
 * @date 2018/8/27
 */

public class HomeFragment extends BaseFragment {

    private FragmentHomeBinding mHomeBinding;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<ContentFragment> contentFragments;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contentFragments = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG,"onCreateView");
        mHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home,container,false);
        tabLayout = mHomeBinding.tab;
        viewPager = mHomeBinding.contentVp;
        return mHomeBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        GameTypeInfosForTabLayoutViewModel.Factory factory = new GameTypeInfosForTabLayoutViewModel.Factory(App.getAppInstance());
        GameTypeInfosForTabLayoutViewModel model = ViewModelProviders.of(this,factory)
                                                        .get(GameTypeInfosForTabLayoutViewModel.class);
        subscribeToModel(model);
    }

    private void subscribeToModel(GameTypeInfosForTabLayoutViewModel model) {
        model.getmGameTypeInfosForTabLayout().observe(this, new Observer<List<GameTypeInfo>>() {
            @Override
            public void onChanged(@Nullable List<GameTypeInfo> gameTypeInfos) {

                if(gameTypeInfos==null || gameTypeInfos.size()==0) {
                    return;
                }

               for(GameTypeInfo gameTypeInfo :gameTypeInfos){
                   tabLayout.addTab(tabLayout.newTab());
                   ContentFragment fragment = new ContentFragment();
                   Bundle bundle = new Bundle();
                   bundle.putString(DouYu.GAME_NAME,gameTypeInfo.getGameShortName());
                   bundle.putInt(DouYu.GAME_ID,gameTypeInfo.getGameId());
                   fragment.setArguments(bundle);
                   contentFragments.add(fragment);
               }

                ContentFragmentAdapter adapter = new ContentFragmentAdapter(getFragmentManager(),contentFragments);
                viewPager.setAdapter(adapter);
                tabLayout.setupWithViewPager(viewPager);

               for(int i=0;i<gameTypeInfos.size();++i){
                   TabLayout.Tab tab = tabLayout.getTabAt(i);
                   if(tab!=null){
                       tab.setText(gameTypeInfos.get(i).getGameTypeName());
                   }
               }

            }
        });
    }
}
