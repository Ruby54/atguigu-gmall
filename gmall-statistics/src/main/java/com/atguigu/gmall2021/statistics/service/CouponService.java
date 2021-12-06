package com.atguigu.gmall2021.statistics.service;

import com.atguigu.gmall2021.statistics.bean.AdsCouponStats;
import com.atguigu.gmall2021.statistics.bean.QCoupon;

import java.util.List;

/**
 * @program: gmall
 * @description:
 * @author: Zhao Yi
 * @create: 2021-12-06 22:08
 */
public interface CouponService {

    List<String> getCouponNameByStartDate(QCoupon qCoupon);

    List<AdsCouponStats> getCouponList(QCoupon qCoupon);
}
