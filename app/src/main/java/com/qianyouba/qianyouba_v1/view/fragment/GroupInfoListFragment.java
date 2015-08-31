package com.qianyouba.qianyouba_v1.view.fragment;

import android.content.Context;
import android.view.View;

import com.qianyouba.qianyouba_v1.base.BaseFragment;

/**
 * Created by Administrator on 2015/8/21 0021.
 */
public class GroupInfoListFragment extends BaseFragment {
    /**
     * 传入需要的参数，设置给arguments
     * @return
     */
    public static ViewPointInfoListFragment newInstance() {
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

    }
}
