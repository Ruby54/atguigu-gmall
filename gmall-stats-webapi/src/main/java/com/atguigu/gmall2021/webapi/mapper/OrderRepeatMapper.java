package com.atguigu.gmall2021.webapi.mapper;

import com.atguigu.gmall2021.webapi.bean.NameValueData;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderRepeatMapper {


    @Select("    SELECT  tm_name name,  order_repeat_rate  value " +
            " FROM     ads_repeat_purchase " +
            "  WHERE    dt= #{date}   AND  recent_days=#{days}   " +
            "   order by   order_repeat_rate asc limit #{topN}   ")
    public List<NameValueData> getRepeatRateByTm(@Param("date") String date, @Param("days") int days,int topN) ;
}
