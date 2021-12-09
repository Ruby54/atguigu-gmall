package com.atguigu.gmall2021.statistics.mapper;

import com.atguigu.gmall2021.statistics.bean.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Property;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface OrderTotalMapper {


    @Select("  SELECT   dt name,uv_count value   " +
            "  FROM    ads_vu_count  " +
            "  WHERE    dt>= #{startTime}  and dt<= #{endTime} and recent_days =7"  )
    public List<NameValueData> getOrderTotal(QOrder qOrder);

    @Select("select * from ads_trade_stats where dt = #{dt} and recent_days = #{days}")
    AdsTradeStats getTradeByDaysAndDt(int days, String dt);

    @Select("select province_name,order_count from ads_order_by_province where dt = #{dt} and recent_days = #{days} order by order_count ASC;")
    List<AdsOrderByProvince> getOrderProvinceCount(int days, String dt);

    @Select("select province_name,order_total_amount from ads_order_by_province where dt = #{dt} and recent_days = #{days} order by order_total_amount ASC;")
    List<AdsOrderByProvince> getOrderProvinceTotalAmount(int days, String dt);
}
