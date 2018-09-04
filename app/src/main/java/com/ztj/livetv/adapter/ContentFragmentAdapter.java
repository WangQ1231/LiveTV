package com.ztj.livetv.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ztj.livetv.fragment.ContentFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoutianjie on 2018/9/4.
 */

public class ContentFragmentAdapter extends FragmentPagerAdapter {

    private List<ContentFragment> mData;

    public ContentFragmentAdapter(FragmentManager fm,List<ContentFragment> mList) {
        super(fm);
        mData = new ArrayList<>();
        if(mList!=null && !mList.isEmpty()){
            mData.addAll(mList);
        }
    }

    @Override
    public Fragment getItem(int position) {
        if(!mData.isEmpty() && position>=0 && position<mData.size()){
            return mData.get(position);
        }
        return null;
    }

    @Override
    public int getCount() {
        return mData.size();
    }
}
