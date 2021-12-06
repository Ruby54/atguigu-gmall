package com.atguigu.gmall2021.statistics.service.impl;

import com.atguigu.gmall2021.statistics.bean.AdsCouponStats;
import com.atguigu.gmall2021.statistics.bean.QCoupon;
import com.atguigu.gmall2021.statistics.mapper.CouponMapper;
import com.atguigu.gmall2021.statistics.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: gmall
 * @description:优惠券统计实现类
 * @author: Zhao Yi
 * @create: 2021-12-06 22:08
 */
@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponMapper couponMapper;


    @Override
    public List<String> getCouponNameByStartDate(QCoupon qCoupon) {
        return couponMapper.getCouponNameByStartDate(qCoupon.getStartDate());
    }

    @Override
    public List<AdsCouponStats> getCouponList(QCoupon qCoupon) {
        return couponMapper.getCouponList(qCoupon);
    }
}
