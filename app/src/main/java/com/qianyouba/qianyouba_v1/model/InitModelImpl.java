package com.qianyouba.qianyouba_v1.model;

import android.content.Context;

import com.qianyouba.qianyouba_v1.net.HttpRequestUtil;
import com.qianyouba.qianyouba_v1.net.RequestCode;
import com.qianyouba.qianyouba_v1.net.RequestUri;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/8/20 0020.
 */
public class InitModelImpl implements InitModel ,HttpRequestUtil.HttpRequestListener{
    private Context context;
    private InitModelListener initModelListener;
    public InitModelImpl(Context context,InitModelListener initModelListener){
        this.context = context;
        this.initModelListener = initModelListener;
    }

    @Override
    public void onResponse(int requestCode, String data) {
        switch (requestCode){
            case RequestCode.CHECK_UPDATE:
                initModelListener.checkUpdateResult(data);
                break;
        }
    }

    @Override
    public void onErrorResponse(int requestCode, String error) {

    }

    public interface InitModelListener{
        void checkUpdateResult(String str);
    }
    @Override
    public void checkUpdate() {
        Map params = new HashMap();
        new HttpRequestUtil(context,this).stringRequest(RequestCode.CHECK_UPDATE, RequestUri.CHECK_UPDATE, params);
    }
}
