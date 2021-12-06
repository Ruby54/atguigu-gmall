package com.atguigu.gmall2021.statistics.mapper;

import com.atguigu.gmall2021.statistics.bean.AdsCouponStats;
import com.atguigu.gmall2021.statistics.bean.QCoupon;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @program: gmall
 * @description:
 * @author: Zhao Yi
 * @create: 2021-12-06 22:11
 */
public interface CouponMapper {

    @Select("  SELECT  coupon_name from ads_coupon_stats where start_date=#{startDate} group by coupon_id"  )
    List<String> getCouponNameByStartDate(@Param("startDate")String startDate);

    @Select("  SELECT  * from ads_coupon_stats where start_date=#{startDate} and coupon_name=#{couponName}"  )
    List<AdsCouponStats> getCouponList(QCoupon qCoupon);
}
