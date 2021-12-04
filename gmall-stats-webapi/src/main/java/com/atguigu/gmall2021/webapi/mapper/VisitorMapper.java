package com.atguigu.gmall2021.webapi.mapper;

import com.atguigu.gmall2021.webapi.bean.DtCount;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface VisitorMapper {

    @Select("select  is_new,uv_count uv, page_count pv,   bounce_rate, ROUND( page_count / sv_count ,1) pv_per_session, avg_duration_sec   dur_per_session " +
            "from  ads_visit_stats  where  dt = #{dt} and recent_days= #{days}   ")
    public List<Map> getNewVisitorStats(@Param("days")int days, @Param("dt")String dt);


    @Select("  SELECT    SUM(IF(is_new='1',uv_count,0)) newCount,SUM( uv_count ) uvCount ,SUM( page_count) pvCount    " +
            "  FROM    ads_visit_stats  " +
            "  WHERE        dt =#{dt} and recent_days= #{days}    ")
    public  Map  getVisitTotal(@Param("days") int days, @Param("dt")String dt);



    @Select("select date_format(vs.dt  ,'%m/%d') dt,sum(uv_count) ct from  ads_visit_stats  vs where recent_days=1 and DATEDIFF(#{dt} ,vs.dt) < #{days}  group by dt ")
    public List<DtCount> getUvCountLastDays(@Param("days")int days, @Param("dt")String dt);


    @Select("select date_format(vs.dt  ,'%m/%d') dt,sum(page_count) ct from  ads_visit_stats  vs where recent_days=1 and DATEDIFF(#{dt} ,vs.dt) < #{days}  group by dt  ")
    public List<DtCount> getPvCountLastDays( @Param("days")int days, @Param("dt")String dt);

    @Select("  select channel,  sum( if(is_new='1', page_count ,0)) new,sum( if(is_new='0', page_count,0 )) old ,   " +
            " sum( if(is_new='1', page_count ,0))+sum( if(is_new='0', page_count,0 )) total " +
            "  from ads_visit_stats  WHERE   dt =#{dt} and recent_days= #{days} " +
            "  group by channel    order by total asc ")
    public List<Map> getChannelPvVisitStats(@Param("days")int days, @Param("dt")String dt);

    @Select("  select channel,   round(sum( if(is_new='1', bounce_count ,0))/ sum( if(is_new='1', sv_count ,0)) ,2)   new" +
            " , round(sum( if(is_new='0', bounce_count ,0))/ sum( if(is_new='0', sv_count ,0)) ,2)   old  ,  " +
            "  sum( if(is_new='1', bounce_count ,0))/ sum( if(is_new='1', sv_count ,0))+sum( if(is_new='0', bounce_count ,0))/ sum( if(is_new='0', sv_count ,0))  total"    +
            "  from ads_visit_stats  WHERE        dt =#{dt} and recent_days= #{days} " +
            "  group by channel   order by total asc ")
    public List<Map> getChannelBounceRateVisitStats(@Param("days")int days, @Param("dt")String dt);


}
