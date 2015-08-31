package com.qianyouba.qianyouba_v1.model.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.qianyouba.qianyouba_v1.base.BaseFragment;
import com.qianyouba.qianyouba_v1.view.fragment.GroupInfoListFragment;
import com.qianyouba.qianyouba_v1.view.fragment.ViewPointInfoListFragment;

/**
 * Created by Administrator on 2015/8/7 0007.
 */
public class TabAdapter extends FragmentPagerAdapter {
    public static final String[] TITLES = new String[]{"国内游", "境外游", "热点游","主题游","拼团"};

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        BaseFragment fragment;
        if(position==TITLES.length-1){
            fragment = GroupInfoListFragment.newInstance();
        }else{
            fragment = ViewPointInfoListFragment.newInstance(position);
        }
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position % TITLES.length];
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }
}
