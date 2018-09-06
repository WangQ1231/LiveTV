package com.ztj.livetv.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.scwang.smartrefresh.header.WaterDropHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.ztj.androidlib.fragment.BaseFragment;
import com.ztj.livetv.R;
import com.ztj.livetv.databinding.FragmentContentBinding;

/**
 * 布局：recyclerView+刷新框架(自定义实现)
 * @author zhoutianjie
 * @date 2018/9/4
 */

public class ContentFragment extends BaseFragment {

    private FragmentContentBinding mBinding;
    private SmartRefreshLayout smartRefreshLayout;
    private RecyclerView recyclerView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_content,container,false);
        smartRefreshLayout = mBinding.contentRefresh;

        //初始化刷新框架 begin
        smartRefreshLayout.setPrimaryColorsId(R.color.colorPrimary);
        WaterDropHeader waterDropHeader = new WaterDropHeader(getContext());
        smartRefreshLayout.setRefreshHeader(waterDropHeader);
        BallPulseFooter ballPulseFooter = new BallPulseFooter(getContext());
        ballPulseFooter.setAnimatingColor(getResources().getColor(R.color.colorPrimary));
        smartRefreshLayout.setRefreshFooter(ballPulseFooter);
        //end

        recyclerView = mBinding.contentRecycler;
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
