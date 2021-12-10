package com.atguigu.gmall2021.statistics.controller;

import com.alibaba.fastjson.JSON;
import com.atguigu.gmall2021.statistics.bean.DtCount;
import com.atguigu.gmall2021.statistics.bean.Echart;
import com.atguigu.gmall2021.statistics.bean.NameValueData;
import com.atguigu.gmall2021.statistics.bean.QOrder;
import com.atguigu.gmall2021.statistics.service.OrderService;
import com.atguigu.gmall2021.statistics.utils.EchartsConverter;
import com.atguigu2021.commons.module.Response;
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

    @RequestMapping(value ="getTradeByDaysAndDt",method = {RequestMethod.GET})
    @CrossOrigin
    public Response getTradeByDaysAndDt(@RequestParam int days, @RequestParam String dt){
        Response rep = Response.build();
        try{
            rep.success().setResult(orderService.getTradeByDaysAndDt(days,dt));
        }catch (Exception e){
            rep.fail(e);
        }
        return rep;
    }



    @RequestMapping(value ="getOrderProvinceData",method = {RequestMethod.GET})
    @CrossOrigin
    public Response getOrderProvinceData(@RequestParam int days, @RequestParam String dt,@RequestParam String orderSelect){
        Response rep = Response.build();
        try{
            rep.success().setResult(orderService.getOrderProvinceData(days,dt,orderSelect));
        }catch (Exception e){
            rep.fail(e);
        }
        return rep;
    }
}
