package com.atguigu.gmall2021.statistics.service.impl;

import com.atguigu.gmall2021.statistics.bean.*;
import com.atguigu.gmall2021.statistics.mapper.OrderTotalMapper;
import com.atguigu.gmall2021.statistics.service.OrderService;
import com.atguigu.gmall2021.statistics.utils.EchartsConverter;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @program: gmall
 * @description: 订单管理实现类
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderTotalMapper orderTotalMapper;

    /***
     * @description: 获取订单的昨天、近7天以及近30天的订单数、GMV、下单人数
     * @params:   [days, dt]
     * @return: java.util.Map
     * @date: 2021/9/28
     */

    @Override
    public String getOrderTotal(QOrder qOrder) {
        //dt = getStatsDt(dt);
        qOrder.setStartTime(getStatsDt(qOrder.getStartTime()));
        qOrder.setEndTime(getStatsDt(qOrder.getEndTime()));
        List<NameValueData> userTotalMap = orderTotalMapper.getOrderTotal(qOrder);
        return EchartsConverter.converterFromNameValue(userTotalMap, false).toJSONString();
    }

    @Override
    public AdsTradeStats getTradeByDaysAndDt(int days, String dt) {

        return orderTotalMapper.getTradeByDaysAndDt(days,dt);
    }

    @Override
    public List<Map> getOrderProvinceData(int days, String dt,String orderSelect) {

        List<Map> orderProvinceList = new ArrayList<>();
        List<AdsOrderByProvince> orderProvinceDataList = new ArrayList<>();
        if(orderSelect.equals("订单数")){
            orderProvinceDataList = orderTotalMapper.getOrderProvinceCount(days, dt);
        }else{
            orderProvinceDataList = orderTotalMapper.getOrderProvinceTotalAmount(days, dt);
        }
        if(!CollectionUtils.isEmpty(orderProvinceDataList)){
            for(AdsOrderByProvince adsOrderByProvince : orderProvinceDataList){
                Map<String, String> orderProvinceMap = new HashMap<>();
                orderProvinceMap.put("name",adsOrderByProvince.getProvince_name());
                if(adsOrderByProvince.getOrder_count()!=0){
                    orderProvinceMap.put("value",Integer.toString(adsOrderByProvince.getOrder_count()));
                }else{
                    orderProvinceMap.put("value",adsOrderByProvince.getOrder_total_amount().toString());
                }
                orderProvinceList.add(orderProvinceMap);
            }
        }
        return orderProvinceList ;
    }

    /**
     * 通过当前日期获得统计日期
     * @param td
     * @return
     */
    private String getStatsDt(String td){
        String dateString = "";
        try{
            DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            Date parse = format1.parse(td);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            dateString = formatter.format(parse);

        }catch (Exception e){
            e.printStackTrace();
        }
        return dateString;
    }


}
