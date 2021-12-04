package com.atguigu.gmall2021.webapi.service;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface ActivityStatsService {


    public Map getActivityStats( String name ,   String dt,int pageNo,int pageSize);

    public Map getCouponStats(String name, String dt,int pageNo,int pageSize) ;
}
