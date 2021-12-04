package com.atguigu.gmall2021.webapi.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.Map;

public interface UserTotalMapper {


    @Select(" SELECT ads_user_all.userTotal   ,ads_user_all.orderUserTotal , " +
            "   new_user_count newUserCount, new_order_user_count newOrderUserCount ," +
            "   round( order_final_amount / order_user_count ,2) pct" +
            "   FROM  ads_user_total  LEFT JOIN " +
            "  (SELECT    dt,  new_user_count  userTotal,  new_order_user_count  orderUserTotal   FROM ads_user_total  WHERE  recent_days=0 AND dt= #{date}  " +
            "  )  `ads_user_all`  ON ads_user_all.dt=ads_user_total.dt " +
            "   WHERE  ads_user_total.dt= #{date}   AND ads_user_total.recent_days=#{days}  "   )
    public Map getUserTotal(@Param("date") String date,  @Param("days") int days);


    @Select(" select  sum(order_user_count) order_user_count ,sum(no_order_user_count) no_order_user_count  " +
            " from  ads_user_total " +
            "  where  dt= #{date}   AND  recent_days=#{days}    ")
    public Map<String, BigDecimal> getOrderUserStats( @Param("date") String date,  @Param("days") int days);
}
