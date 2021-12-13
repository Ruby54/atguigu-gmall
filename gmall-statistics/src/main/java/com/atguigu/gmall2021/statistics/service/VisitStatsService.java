package com.atguigu.gmall2021.statistics.service;

import com.atguigu.gmall2021.statistics.bean.AdsTrafficStats;

import java.util.List;

/**
 * @program: gmall
 * @description: 流量统计service
 * @author: Zhao Yi
 * @create: 2021-12-11 14:08
 */
public interface VisitStatsService {
    String getPagePathCount(int days, String dt);

    List<AdsTrafficStats> getTrafficStats(int days, String dt);
}
