package com.qianyouba.qianyouba_v1.model;

/**
 * 初始化时需要需要做的网络操作接口
 * Created by Administrator on 2015/8/20 0020.
 *
 */
public interface InitModel extends IModel{
    /**
     * 检查是否有软件更新
     */
    void checkUpdate();
}
