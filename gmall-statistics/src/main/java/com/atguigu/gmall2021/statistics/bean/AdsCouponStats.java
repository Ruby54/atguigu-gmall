package com.atguigu.gmall2021.statistics.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: gmall
 * @description:
 * @author: Zhao Yi
 * @create: 2021-12-06 22:13
 */
@Data
@NoArgsConstructor
public class AdsCouponStats {

    /**
     *统计日期
     */
    private String dt;

    /**
     *优惠券ID
     */
    private String coupon_id;

    /**
     *优惠券名称
     */
    private String coupon_name;

    /**
     *发布日期
     */
    private String start_date;

    /**
     *优惠规则
     */
    private String rule_name;

    /**
     *补贴率
     */
    private String reduce_rate;
}
