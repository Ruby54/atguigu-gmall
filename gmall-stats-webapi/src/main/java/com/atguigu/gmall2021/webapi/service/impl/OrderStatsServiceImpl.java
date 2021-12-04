package com.atguigu.gmall2021.webapi.service.impl;

import com.atguigu.gmall2021.webapi.bean.NameValueData;
import com.atguigu.gmall2021.webapi.mapper.*;
import com.atguigu.gmall2021.webapi.service.OrderStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderStatsServiceImpl implements OrderStatsService {
    @Autowired
    OrderProvinceMapper orderProvinceMapper;

    @Autowired
    OrderSpuMapper orderSpuMapper;


    @Autowired
    OrderTotalMapper orderTotalMapper;

    @Autowired
    OrderRepeatMapper orderRepeatMapper;


    @Override
    public List<NameValueData> getOrderCountByProvince(int days,String dt ) {
            return  orderProvinceMapper.getOrderCountByProvince(dt,days );
    }

    @Override
    public List<NameValueData> getOrderCountByCategory(int days,String dt) {
        return  orderSpuMapper.getOrderCountByCategory(dt,days  );
    }

    @Override
    public List<NameValueData> getOrderCountBySpu(int days, int topN,String dt) {
        return    orderSpuMapper.getOrderCountBySpu(dt,days,topN);
    }

    @Override
    public List<NameValueData> getOrderAmountBySpu(int days, int topN,String dt) {
        return    orderSpuMapper.getOrderAmountBySpu(dt,days,topN);
    }

    @Override
    public List<NameValueData> getOrderCountByTm(int days, int topN,String dt) {
        return orderSpuMapper.getOrderCountByTm(dt,days,topN);
    }

    @Override
    public List<NameValueData> getRepeatRateByTm(int days, int topN,String dt) {
        return    orderRepeatMapper.getRepeatRateByTm(dt,days,topN);
    }

    @Override
    public Map getOrderTotal(int days ,String dt) {
        return orderTotalMapper.getOrderTotal(dt,days );
    }


}
