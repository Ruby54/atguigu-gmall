package com.atguigu.gmall2021.webapi.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface CouponStatsMapper {

    @Select("select coupon_id,coupon_name,rule_name,get_count,order_count,expire_count,order_original_amount," +
            "order_final_amount,reduce_amount,round(reduce_rate,2) reduce_rate,spu_comment,tm_comment" +
            " from ads_coupon_stats where dt=#{dt} and coupon_name like  concat('%',#{name} ,'%')  limit #{startNo},#{endNo} ")
    public List<Map> getCouponStats(@Param("name") String name, @Param("dt") String dt, int startNo, int endNo);

    @Select("select  count(*) from ads_coupon_stats where dt=#{dt} and coupon_name like  concat('%',#{name} ,'%')   ")
    public Long getCouponStatsTotal(@Param("name") String name, @Param("dt") String dt);
}
