package com.atguigu.gmall2021.webapi.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface UserRetentionMapper {


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
    public List<Map> getUserRetention(  @Param("dt")String dt);
}
