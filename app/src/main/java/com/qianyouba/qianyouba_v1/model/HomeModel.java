package com.qianyouba.qianyouba_v1.model;

import com.qianyouba.qianyouba_v1.model.entity.ViewInfo;

import java.util.List;

/**
 * 首页数据初始化接口
 * Created by Administrator on 2015/8/31 0031.
 */
public interface HomeModel extends IModel {
    /**
     * 获取国内游列表
     * @return
     */
   void getInnerViews();
    /**
     * 获取境外游列表
     * @return
     */
    void getOuterViews();
    /**
     * 获取热点游列表
     * @return
     */
    void getHotViews();
    /**
     * 获取主题游列表
     * @return
     */
    void getThemeViews();

    /**
     * 获取拼团游列表
     * @return
     */
    void getGroupViews();

}
