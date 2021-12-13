package com.atguigu.gmall2021.statistics.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * @program: gmall
 * @description:
 * @author: Zhao Yi
 * @create: 2021-12-11 10:08
 */
public interface UserNewBuyerStatsMapper {

    @Select("select      new_order_user_count newUserCount,  new_payment_user_count newOrderUserCount  from ads_new_buyer_stats where   recent_days=#{days} AND dt= #{date} ")
    Map getNewBuyerStats(@Param("date")String dt , @Param("days") int days);
}
