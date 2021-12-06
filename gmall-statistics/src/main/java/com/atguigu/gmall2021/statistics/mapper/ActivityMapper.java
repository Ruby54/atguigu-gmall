package com.atguigu.gmall2021.statistics.mapper;

import com.atguigu.gmall2021.statistics.bean.AdsActivityStats;
import com.atguigu.gmall2021.statistics.bean.QActivity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @program: gmall
 * @description:
 * @author: Zhao Yi
 * @create: 2021-12-04 16:11
 */
public interface ActivityMapper {

    @Select("  SELECT  activity_name from ads_activity_stats where dt=#{dt}"  )
    List<String> getActivityNameByDt(@Param("dt") String dt);

    @Select("  SELECT  * from ads_activity_stats where dt=#{selectDay} and activity_name=#{activityName}"  )
    List<AdsActivityStats> getActivityList(QActivity qActivity);
}
