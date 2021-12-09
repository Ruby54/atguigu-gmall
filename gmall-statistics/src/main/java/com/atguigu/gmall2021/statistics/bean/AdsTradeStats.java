package com.atguigu.gmall2021.statistics.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: gmall
 * @description:
 * @author: Zhao Yi
 * @create: 2021-12-07 12:29
 */
@Data
@NoArgsConstructor
public class AdsTradeStats {


    private String dt;

    private int recent_days;
    private String order_total_amount;
    private int order_count;
    private int order_user_count;
    private int order_refund_count;
    private int order_refund_user_count;
}
