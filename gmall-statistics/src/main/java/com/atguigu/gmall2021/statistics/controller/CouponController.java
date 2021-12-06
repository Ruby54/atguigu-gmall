package com.atguigu.gmall2021.statistics.controller;

import com.atguigu.gmall2021.statistics.bean.QActivity;
import com.atguigu.gmall2021.statistics.bean.QCoupon;
import com.atguigu.gmall2021.statistics.service.CouponService;
import com.atguigu2021.commons.module.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: gmall
 * @description:最近30天发布的优惠券的补贴率
 * @author: Zhao Yi
 * @create: 2021-12-06 17:54
 */
@RestController
public class CouponController {

    @Autowired
    private CouponService couponService;

    /**
     * 根据开始时间查询优惠券名称
     * @param qCoupon
     * @return
     */
    @RequestMapping(value ="getCouponNameByStartDate",method = {RequestMethod.POST})
    @CrossOrigin
    public List<String> getCouponNameByStartDate(@RequestBody QCoupon qCoupon) {
        List<String> couponNameList = couponService.getCouponNameByStartDate(qCoupon);
        return couponNameList;
    }

    /**
     * 根据开始时间和优惠券名称查询优惠券统计
     * @param qCoupon
     * @return
     */
    @RequestMapping(value ="getCouponList",method = {RequestMethod.POST})
    @ResponseBody
    @CrossOrigin
    public Response getCouponList(@RequestBody QCoupon qCoupon){
        Response rep = Response.build();
        try{
            rep.success().setResult(couponService.getCouponList(qCoupon));
        }catch (Exception e){
            rep.fail(e);
        }
        return rep;
    }

}
