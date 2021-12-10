package com.atguigu.gmall2021.statistics.mapper;

import com.atguigu.gmall2021.statistics.bean.AdsTradeStatsByCate;
import com.atguigu.gmall2021.statistics.bean.AdsTradeStatsByTm;
import com.atguigu.gmall2021.statistics.bean.NameValueData;
import com.atguigu.gmall2021.statistics.bean.QOrder;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @program: gmall
 * @description:
 * @author: Zhao Yi
 * @create: 2021-12-09 19:16
 */
public interface GoodsMapper {

    @Select("  SELECT   tm_name name,order_repeat_rate value   " +
            "  FROM    ads_repeat_purchase_by_tm  " +
            "  WHERE    recent_days= #{recentDays}  and dt= #{curDate} limit #{showNum}"  )
    List<NameValueData> getTmRepeat(int recentDays, String curDate, int showNum);

    @Select("  SELECT   *   " +
            "  FROM    ads_trade_stats_by_tm  " +
            "  WHERE    recent_days= #{recentDays}  and dt= #{curDate}"  )
    List<AdsTradeStatsByTm> getTmTradeStats(int recentDays, String curDate);

    @Select("  SELECT   *   " +
            "  FROM    ads_trade_stats_by_cate  " +
            "  WHERE    recent_days= #{recentDays}  and dt= #{curDate} group by category1_id"  )
    List<AdsTradeStatsByCate> getCategory1List(int recentDays, String curDate);


    @Select("  SELECT   category2_id,category2_name,sum(order_count) orderCountTotal   " +
            "  FROM    ads_trade_stats_by_cate  " +
            "  WHERE    recent_days= #{recentDays}  and dt= #{curDate} and category1_id =#{category1_id} group by category2_id"  )
    List<AdsTradeStatsByCate> getCategory2List(int recentDays, String curDate,String  category1_id);

    @Select("  SELECT   category3_name name,order_count value" +
            "  FROM    ads_trade_stats_by_cate  " +
            "  WHERE    recent_days= #{recentDays}  and dt= #{curDate} " +
            "and category1_id =#{category1_id} and category2_id=#{category2_id}"  )
    List<NameValueData> getCategory3List(int recentDays, String curDate,String  category1_id,String  category2_id);

    @Select("  SELECT   category1_id lable, category1_name name" +
            "  FROM    ads_sku_cart_num_top3_by_cate  " +
            "  WHERE   dt= #{curDate} group by category1_id" )
    List<NameValueData> getCategory1(String curDate);


    @Select("  SELECT   category2_id lable, category2_name name" +
            "  FROM    ads_sku_cart_num_top3_by_cate  " +
            "  WHERE   dt= #{curDate} and category1_id = #{category1_id} group by category2_id" )
    List<NameValueData> getCategory2(String curDate,String category1_id);

    @Select("  SELECT   category3_id lable, category3_name name" +
            "  FROM    ads_sku_cart_num_top3_by_cate  " +
            "  WHERE   dt= #{curDate} and category1_id = #{category1_id} and category2_id=#{category2_id}" +
            " group by category3_id" )
    List<NameValueData> getCategory3(String curDate,String category1_id,String category2_id);

    @Select("  SELECT   sku_name name, cart_num value" +
            "  FROM    ads_sku_cart_num_top3_by_cate  " +
            "  WHERE   dt= #{curDate} and category1_id = #{category1_id} and category2_id=#{category2_id} " +
            "and category3_id=#{category3_id} order by rk asc" )
    List<NameValueData>  getTmTopNData(String curDate,String category1_id,String category2_id,String category3_id);
}
