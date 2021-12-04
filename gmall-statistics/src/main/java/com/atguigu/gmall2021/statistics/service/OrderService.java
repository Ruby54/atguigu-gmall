package com.atguigu.gmall2021.statistics.service;

import com.atguigu.gmall2021.statistics.bean.DtCount;
import com.atguigu.gmall2021.statistics.bean.NameValueData;
import com.atguigu.gmall2021.statistics.bean.QOrder;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * @program: gmall
 * @description:
 */
public interface OrderService {

    String getOrderTotal(QOrder qOrder) ;
}
