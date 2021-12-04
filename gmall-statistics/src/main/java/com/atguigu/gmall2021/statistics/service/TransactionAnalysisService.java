package com.atguigu.gmall2021.statistics.service;

import com.atguigu.gmall2021.statistics.bean.NameValueData;

import java.util.List;

/**
 * @program: gmall
 * @description: 交易分析Service
 */
public interface TransactionAnalysisService {

    List<NameValueData> getStatsWideByItem(String itemName, String date, String type);
}
