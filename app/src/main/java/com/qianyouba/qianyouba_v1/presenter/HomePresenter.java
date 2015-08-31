package com.qianyouba.qianyouba_v1.presenter;

import android.content.Context;

import com.qianyouba.qianyouba_v1.model.HomeModel;
import com.qianyouba.qianyouba_v1.model.HomeModelImpl;
import com.qianyouba.qianyouba_v1.view.HomeInfo;
import com.qianyouba.qianyouba_v1.view.InitInfo;

/**
 * Created by Administrator on 2015/8/31 0031.
 */
public class HomePresenter implements HomeModelImpl.Callback {
    private HomeModel homeModel;
    private HomeInfo homeInfo;
    public HomePresenter(Context context){
        homeInfo = (HomeInfo)context;
        homeModel = new HomeModelImpl(context,this);
    }

    public void requestInnerViews(){
        homeModel.getInnerViews();
    }

    @Override
    public void callback(String data) {
        homeInfo.setInnerViewsResult(data);
    }
}
