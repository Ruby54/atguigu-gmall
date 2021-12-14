package com.atguigu.gmall2021.statistics.mapper;


import com.atguigu.gmall2021.statistics.bean.*;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {


    //定义一个方法，用来从数据库中查询用户行为漏斗数据的数据
    @Select("select * from ads_user_action where dt = #{dt} and recent_days = #{days}")
    UserAction getUserActionByDaysAndDt(int days,String dt);

   //定义一个方法，用来从数据库中查询得到新增交易用户统计的数据
    @Select("select * from ads_new_buyer_stats where dt = #{dt} and recent_days = #{days}")
    NewBuyerStats getNewBuyerStatsByDaysAndDt(int days,String dt);

    //定义一个方法，用来从数据库中查询得到用户变动统计的数据
    @Select("select * from ads_user_change where dt = #{dt} ")
    UserChange getUserChangeByDt(String dt);

    //定义一个方法，用来从数据库中查询得到用户新增活跃统计的数据
    @Select("select * from ads_user_stats where dt = #{dt} and recent_days = #{days}")
    UserStats getUserStatsByDaysAndDt(int days,String dt);

    @Select("SELECT * FROM ads_user_retention where  dt <=  #{dt}  AND dt>= date_add(#{dt}, interval -7 day)")
    public List<UserRetention> getUserRetentionByDt(String dt);


}
