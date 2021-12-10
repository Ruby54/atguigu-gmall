package com.atguigu.gmall2021.statistics.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.atguigu.gmall2021.statistics.bean.AdsTradeStatsByTm;
import com.atguigu.gmall2021.statistics.bean.NameValueData;

import java.util.List;
import java.util.Map;

/**
 * @program: gmall
 * @description: 商品统计service
 * @author: Zhao Yi
 * @create: 2021-12-09 16:04
 */
public interface GoodsService {

    String getTmRepeat(int recentDays, String curDate, int showNum);

    List<AdsTradeStatsByTm> getTmTradeStats(int recentDays, String curDate);

    List<JSONObject> getCateTradeStats(int recentDays, String curDate);

    List<NameValueData> getCategory1( String curDate);

    List<NameValueData> getCategory2( String curDate,String category1_id);

    List<NameValueData> getCategory3( String curDate,String category1_id,String category2_id);

    String getTmTopNData(String curDate, String category1_id,String category2_id,String category3_id);
}

