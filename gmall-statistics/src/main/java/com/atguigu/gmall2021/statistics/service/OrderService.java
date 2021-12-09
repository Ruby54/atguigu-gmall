package com.atguigu.gmall2021.statistics.service;

import com.atguigu.gmall2021.statistics.bean.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * @program: gmall
 * @description:
 */
public interface OrderService {

    String getOrderTotal(QOrder qOrder) ;

    AdsTradeStats getTradeByDaysAndDt(int days, String dt);

    List<Map> getOrderProvinceData(int days, String dt,String orderSelect);

}
