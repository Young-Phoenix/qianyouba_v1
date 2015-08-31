package com.qianyouba.qianyouba_v1.presenter;

import android.content.Context;

import com.qianyouba.qianyouba_v1.model.InitModel;
import com.qianyouba.qianyouba_v1.model.InitModelImpl;
import com.qianyouba.qianyouba_v1.view.InitInfo;

/**
 * Created by Administrator on 2015/8/20 0020.
 */
public class InitPresenter implements InitModelImpl.InitModelListener{
    private InitModel initModel;
    private InitInfo initInfo;
    public InitPresenter(Context context){
        initInfo = (InitInfo)context;
        initModel = new InitModelImpl(context,this);
    }

    public void checkUpdate(){
        initModel.checkUpdate();
    }

    @Override
    public void checkUpdateResult(String str) {
        initInfo.setInitResult(str);
    }
}
