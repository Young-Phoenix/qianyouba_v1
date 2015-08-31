package com.qianyouba.qianyouba_v1.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.qianyouba.qianyouba_v1.base.BaseFragment;
import com.qianyouba.qianyouba_v1.presenter.HomePresenter;
import com.qianyouba.qianyouba_v1.view.HomeInfo;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/8/21 0021.
 */
public class ViewPointInfoListFragment extends BaseFragment implements HomeInfo{
    public static final String ARGUMENT = "argument";
    private static int viewPointType;
    private HomePresenter homePresenter;
    /**
     * 传入需要的参数，设置给arguments
     * @param type
     * @return
     */
    public static ViewPointInfoListFragment newInstance(int type) {
        viewPointType = type;
        ViewPointInfoListFragment fragment = new ViewPointInfoListFragment();
        return fragment;
    }
    @Override
    public int bindLayout() {
        return 0;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void doBusiness(Context mContext) {
        homePresenter = new HomePresenter(getContext());
        homePresenter.requestInnerViews();
    }

    @Override
    public void setInnerViewsResult(String data) {

    }
}
