package com.atguigu.gmall2021.webapi.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface ActivityStatsMapper {

    @Select("select activity_id,activity_name,order_count,order_original_amount,order_final_amount,reduce_amount,reduce_rate," +
            "rule_comment,spu_comment,tm_comment from ads_activity_stats where dt=#{dt} and activity_name like  concat('%',#{name} ,'%')  limit #{startNo},#{endNo} ")
    public List<Map> getActivityStats(@Param("name") String name , @Param("dt") String dt ,int startNo ,int endNo);

    @Select("select  count(*) from ads_activity_stats where dt=#{dt} and activity_name like  concat('%',#{name} ,'%')   ")
    public Long getActivityStatsTotal(@Param("name") String name , @Param("dt") String dt  );
}
