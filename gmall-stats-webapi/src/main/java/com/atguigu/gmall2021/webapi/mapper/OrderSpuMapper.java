package com.atguigu.gmall2021.webapi.mapper;

import com.atguigu.gmall2021.webapi.bean.NameValueData;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface OrderSpuMapper {


    @Select("    SELECT  spu_name name,  SUM(order_count)    value " +
            " FROM     ads_order_spu_stats " +
            "  WHERE     dt= #{date}   AND  recent_days=#{days}    " +
            "  GROUP BY spu_id,spu_name   order by SUM(order_count) asc" +
            "  LIMIT #{topN} ")
    public List<NameValueData> getOrderCountBySpu(@Param("date")String date ,@RequestParam("days") int days, @RequestParam("topN") int topN);


    @Select("    SELECT  spu_name name,  SUM(order_amount)    value " +
            " FROM     ads_order_spu_stats " +
            "  WHERE     dt= #{date}   AND  recent_days=#{days}    " +
            "  GROUP BY spu_id,spu_name   order by SUM(order_amount) asc" +
            "  LIMIT #{topN} ")
    public List<NameValueData> getOrderAmountBySpu(@Param("date")String date ,@RequestParam("days") int days, @RequestParam("topN") int topN);

    @Select("SELECT  category3_name name,   SUM(order_count)    value" +
            "  FROM   ads_order_spu_stats " +
            "  WHERE    dt= #{date}   AND  recent_days=#{days}    " +
            "  GROUP BY category3_id ,category3_name ")
    public List<NameValueData> getOrderCountByCategory( @Param("date") String date, @Param("days") int days);

    @Select("  SELECT  tm_name name,  SUM(order_count)    value " +
            " FROM     ads_order_spu_stats " +
            "  WHERE   dt= #{date}   AND  recent_days=#{days}    " +
            "  GROUP BY tm_id,tm_name   order by SUM(order_count) asc" +
            "  LIMIT #{topN} ")
    public List<NameValueData> getOrderCountByTm(@Param("date")String date ,@RequestParam("days") int days, @RequestParam("topN") int topN);
}
