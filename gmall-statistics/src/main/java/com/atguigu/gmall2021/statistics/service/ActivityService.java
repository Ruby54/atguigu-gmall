package com.atguigu.gmall2021.statistics.service;

import com.atguigu.gmall2021.statistics.bean.AdsActivityStats;
import com.atguigu.gmall2021.statistics.bean.QActivity;

import java.util.List;

/**
 * @program: gmall
 * @description: 活动统计
 * @author: Zhao Yi
 * @create: 2021-12-04 16:09
 */
public interface ActivityService {
    /**
     * 根据时间查询活动名称
     * @param qActivity
     * @return
     */
    List<String> getActivityNameByDt(QActivity qActivity);

    /**
     * 根据时间名称和dt查询所有
     * @param qActivity
     * @return
     */
    List<AdsActivityStats> getActivityList(QActivity qActivity);
}
