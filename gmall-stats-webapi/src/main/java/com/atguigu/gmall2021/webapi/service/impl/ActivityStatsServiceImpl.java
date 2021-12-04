package com.atguigu.gmall2021.webapi.service.impl;

import com.atguigu.gmall2021.webapi.mapper.ActivityStatsMapper;
import com.atguigu.gmall2021.webapi.mapper.CouponStatsMapper;
import com.atguigu.gmall2021.webapi.service.ActivityStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ActivityStatsServiceImpl implements ActivityStatsService {


    @Autowired
    ActivityStatsMapper activityStatsMapper;

    @Autowired
    CouponStatsMapper couponStatsMapper;

    @Override
    public Map getActivityStats(String name, String dt,int pageNo,int pageSize) {

       int startNo=(  pageNo-1)* pageSize;
       int endNo=startNo+pageSize;

        List<Map> detailList = activityStatsMapper.getActivityStats(name, dt, startNo, endNo);
        Long total = activityStatsMapper.getActivityStatsTotal(name, dt );

        Map rsMap= new HashMap<>();
        rsMap.put("detail",detailList);
        rsMap.put("total",total);
        return  rsMap;

    }


    @Override
    public Map getCouponStats(String name, String dt,int pageNo,int pageSize) {

        int startNo=(  pageNo-1)* pageSize;
        int endNo=startNo+pageSize;

        List<Map> detailList = couponStatsMapper.getCouponStats(name, dt, startNo, endNo);
        Long total = couponStatsMapper.getCouponStatsTotal(name, dt );

        Map rsMap= new HashMap<>();
        rsMap.put("detail",detailList);
        rsMap.put("total",total);
        return  rsMap;

    }
}
