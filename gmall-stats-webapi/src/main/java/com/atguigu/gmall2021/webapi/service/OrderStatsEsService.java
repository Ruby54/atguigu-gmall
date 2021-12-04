package com.atguigu.gmall2021.webapi.service;

import com.atguigu.gmall2021.webapi.bean.NameValueData;

import java.util.List;
import java.util.Map;


public interface OrderStatsEsService {


    public Map getStatsWideByItem(String itemName, String date, String dimName, Integer groupSize );

    public Map getOrderWideByItem(String itemName, String date, Integer pageNo, Integer pageSize);




}
