package com.qianyouba.qianyouba_v1.view.fragment;

import android.content.Context;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.qianyouba.qianyouba_v1.R;
import com.qianyouba.qianyouba_v1.base.BaseFragment;
import com.qianyouba.qianyouba_v1.model.adapter.TabAdapter;
import com.qianyouba.qianyouba_v1.presenter.HomePresenter;
import com.qianyouba.qianyouba_v1.view.HomeInfo;
import com.viewpagerindicator.TabPageIndicator;

/**
 * Created by Administrator on 2015/8/20 0020.
 */
public class HomeFragment extends BaseFragment{
    private TabPageIndicator mIndicator ;
    private ViewPager mViewPager ;
    private FragmentPagerAdapter mAdapter ;


    @Override
    public int bindLayout() {
        return R.layout.home_fragment_layout;
    }

    @Override
    public void initView(View view) {
        mIndicator = (TabPageIndicator) view.findViewById(R.id.id_indicator);
        mViewPager = (ViewPager) view.findViewById(R.id.id_pager);
        mAdapter = new TabAdapter(getFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mIndicator.setViewPager(mViewPager, 0);
    }

    @Override
    public void doBusiness(Context mContext) {

    }
}
