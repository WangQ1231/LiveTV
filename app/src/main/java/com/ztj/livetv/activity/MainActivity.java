package com.ztj.livetv.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import com.ztj.androidlib.activity.BaseActivity;
import com.ztj.livetv.R;
import com.ztj.livetv.databinding.ActivityMainBinding;
import com.ztj.livetv.fragment.ClassifyFragment;
import com.ztj.livetv.fragment.FavoriteFragment;
import com.ztj.livetv.fragment.HomeFragment;
import com.ztj.livetv.fragment.MineFragment;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding mMainBinding;

    private Fragment mCurrentFragment;
    private FragmentManager manager;
    private SparseArray<Fragment> mFragments;

    private final String mIndicator[] = {"首页", "分类", "喜欢", "搜索"};

    private final int mTabImage[] = {R.drawable.tab_home_item,
            R.drawable.tab_classify_item,
            R.drawable.tab_heart_item,
            R.drawable.tab_user_item};


    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

        mMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        initFrgments();

        mMainBinding.bottomNavigationBr.addItem(new BottomNavigationItem(mTabImage[0],mIndicator[0]))
                .addItem(new BottomNavigationItem(mTabImage[1],mIndicator[1]))
                .addItem(new BottomNavigationItem(mTabImage[2],mIndicator[2]))
                .addItem(new BottomNavigationItem(mTabImage[3],mIndicator[3]))
                .setFirstSelectedPosition(0)
                .setMode(BottomNavigationBar.MODE_FIXED)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .setActiveColor(R.color.darkorange)
                .initialise();

        mMainBinding.bottomNavigationBr.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                Log.d(TAG,"onTabSelected "+position);
                showCurrentFragment(mFragments.get(position));
            }

            @Override
            public void onTabUnselected(int position) {
                Log.d(TAG,"onTabUnselected "+position);
            }

            @Override
            public void onTabReselected(int position) {
                Log.d(TAG,"onTabReselected "+position);

            }
        });

    }

    @Override
    protected void loadData() {

    }


    /**
     * 初始化Fragments
     */
    private void initFrgments(){
        mFragments = new SparseArray<>();
        manager = getSupportFragmentManager();
        HomeFragment homeFragment = new HomeFragment();
        mFragments.put(0,homeFragment);
        ClassifyFragment classifyFragment = new ClassifyFragment();
        mFragments.put(1,classifyFragment);
        FavoriteFragment favoriteFragment = new FavoriteFragment();
        mFragments.put(2,favoriteFragment);
        MineFragment mineFragment = new MineFragment();
        mFragments.put(3,mineFragment);

        FragmentTransaction mTransaction = manager.beginTransaction();
        mTransaction.add(R.id.real_tab_content,homeFragment)
                .show(homeFragment)
                .commit();
        mCurrentFragment = homeFragment;

    }

    /**
     * 改变选择Fragment
     * @param fragment
     */
    private void showCurrentFragment(Fragment fragment){
        FragmentTransaction transaction = manager.beginTransaction();
        if(fragment!=mCurrentFragment){
            transaction.hide(mCurrentFragment);
            mCurrentFragment = fragment;
            if(!fragment.isAdded()){
                transaction.add(R.id.real_tab_content,fragment)
                        .show(fragment)
                        .commit();
            }else{
                transaction.show(fragment).commit();
            }
        }
    }


}
