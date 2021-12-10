package com.atguigu.gmall2021.statistics.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: gmall
 * @description: 各品类商品交易统计
 * @author: Zhao Yi
 * @create: 2021-12-09 20:49
 */
@Data
@NoArgsConstructor
public class AdsTradeStatsByCate {

    /**
     * '统计日期'
     */
    private String dt;

    /**
     * 最近天数,1:最近1天,7:最近7天,30:最近30天
     */
    private int recent_days;

    /**
     * 一级分类id
     */
    private String category1_id;

    /**
     * 一级分类名称
     */
    private String category1_name;

    /**
     * 二级分类id
     */
    private String category2_id;

    /**
     * 二分类名称
     */
    private String category2_name;

    /**
     * 三级分类id
     */
    private String  category3_id;

    /**
     * 三级分类名称
     */
    private String category3_name;


    /**
     * 订单数
     */
    private int order_count;

    /**
     * 订单人数
     */
    private int order_user_count;

    /**
     * 退单数
     */
    private int order_refund_count;

    /**
     * 退单人数
     */
    private int order_refund_user_count;

    private int orderCountTotal;
}
