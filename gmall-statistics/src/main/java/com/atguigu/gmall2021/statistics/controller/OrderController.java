package com.atguigu.gmall2021.statistics.controller;

import com.alibaba.fastjson.JSON;
import com.atguigu.gmall2021.statistics.bean.DtCount;
import com.atguigu.gmall2021.statistics.bean.Echart;
import com.atguigu.gmall2021.statistics.bean.NameValueData;
import com.atguigu.gmall2021.statistics.bean.QOrder;
import com.atguigu.gmall2021.statistics.service.OrderService;
import com.atguigu.gmall2021.statistics.utils.EchartsConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: gmall
 * @description: 订单统计
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;


    @RequestMapping(value ="orderTotal",produces = "application/json")
    @CrossOrigin
    public String orderTotal(@RequestBody QOrder qOrder) {
        String orderTotalMap = orderService.getOrderTotal(qOrder);
        return orderTotalMap;
    }
}
