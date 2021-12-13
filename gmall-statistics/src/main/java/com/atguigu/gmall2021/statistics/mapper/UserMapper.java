package com.atguigu.gmall2021.statistics.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @program: gmall
 * @description:
 * @author: Zhao Yi
 * @create: 2021-12-10 17:23
 */
public interface UserMapper {

    @Select(" select   home_count,  good_detail_count , cart_count, order_count,  payment_count " +
            " from   ads_user_action " +
            "  WHERE        dt =#{dt} and recent_days= #{days}   ")
    Map<String, Long> getUserAction (@Param("days") int days, @Param("dt")String dt);

    @Select("SELECT      create_date , MAX( new_user_count ) new_user_count , " +
            "   SUM( IF(retention_day=1, retention_rate,0))  retention_rate_d1,   " +
            "    SUM( IF(retention_day=2, retention_rate,0))  retention_rate_d2,  " +
            "    SUM( IF(retention_day=3, retention_rate,0))  retention_rate_d3,  " +
            "    SUM( IF(retention_day=4, retention_rate,0))  retention_rate_d4,  " +
            "     SUM( IF(retention_day=5, retention_rate,0))  retention_rate_d5,    " +
            "    SUM( IF(retention_day=6, retention_rate,0))  retention_rate_d6,    " +
            "    SUM( IF(retention_day=7, retention_rate,0))  retention_rate_d7   " +
            "   FROM  ads_user_retention  where   dt=#{dt} " +
            "  group by create_date")
    List<Map> getUserRetention(@Param("dt")String dt);
}
