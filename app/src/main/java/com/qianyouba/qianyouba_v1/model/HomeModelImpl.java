package com.qianyouba.qianyouba_v1.model;

import android.content.Context;
import android.view.SurfaceHolder;

import com.qianyouba.qianyouba_v1.model.entity.ViewInfo;
import com.qianyouba.qianyouba_v1.net.HttpRequestUtil;
import com.qianyouba.qianyouba_v1.net.RequestCode;
import com.qianyouba.qianyouba_v1.net.RequestUri;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/8/31 0031.
 */
public class HomeModelImpl implements HomeModel,HttpRequestUtil.HttpRequestListener{
    private Context context;
    private Callback listener;
    public HomeModelImpl(Context context,Callback listener){
        this.context = context;
        this.listener = listener;
    }

    @Override
    public void getInnerViews() {
        Map params = new HashMap();
        new HttpRequestUtil(context,this).stringRequest(RequestCode.GET_INNER_VIEWS, RequestUri.REQUEST_INNER_VIEWS, params);
    }

    @Override
    public void getOuterViews() {

    }

    @Override
    public void getHotViews() {

    }

    @Override
    public void getThemeViews() {

    }

    @Override
    public void getGroupViews() {

    }

    @Override
    public void onResponse(int requestCode, String data) {
        switch (requestCode){
            case RequestCode.GET_INNER_VIEWS:
                listener.callback(data);
                break;
        }
    }

    @Override
    public void onErrorResponse(int requestCode, String error) {

    }

    public interface Callback{
        void callback(String data);
    }


}
