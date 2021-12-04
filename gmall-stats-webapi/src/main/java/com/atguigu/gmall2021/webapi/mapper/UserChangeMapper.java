package com.atguigu.gmall2021.webapi.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

public interface UserChangeMapper {

    @Select("select      sum(user_churn_count)-sum(user_back_count) userChurn,  SUM(user_back_count) userBack  from ads_user_change where   dt >    SUBDATE(#{date} ,#{days}) ")
    public Map getUserChange(@Param("date")String date , @Param("days") int days);
}
