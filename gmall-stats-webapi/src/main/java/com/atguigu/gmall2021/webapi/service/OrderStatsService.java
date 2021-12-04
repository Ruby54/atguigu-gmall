package com.atguigu.gmall2021.webapi.service;

import com.atguigu.gmall2021.webapi.bean.NameValueData;
import com.atguigu.gmall2021.webapi.bean.OrderWide;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface OrderStatsService {


    public List<NameValueData> getOrderCountByProvince(  int days  ,String dt  );

    public List<NameValueData> getOrderCountByCategory(int days ,String dt );

    public List<NameValueData> getOrderCountBySpu(  int days,  int topN ,String dt );

    public List<NameValueData> getOrderCountByTm( int days,  int topN ,String dt );

    public Map getOrderTotal( int days   ,String dt  );

    public List<NameValueData> getRepeatRateByTm(int days, int topN,String dt);

    public List<NameValueData> getOrderAmountBySpu(int days, int topN,String dt);


}
