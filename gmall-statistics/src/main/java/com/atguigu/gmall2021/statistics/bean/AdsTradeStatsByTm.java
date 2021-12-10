package com.atguigu.gmall2021.statistics.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: gmall
 * @description: 各品牌商品交易统计表
 * @author: Zhao Yi
 * @create: 2021-12-09 20:24
 */
@Data
@NoArgsConstructor
public class AdsTradeStatsByTm {

    /**
     * '统计日期'
     */
    private String dt;

    /**
     * 最近天数,1:最近1天,7:最近7天,30:最近30天
     */
    private int recent_days;

    /**
     * 品牌ID
     */
    private String tm_id;

    /**
     * 品牌名称
     */
    private String tm_name;

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

}
