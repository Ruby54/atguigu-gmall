package com.atguigu.gmall2021.statistics.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAction {
    /*
  `dt` date NOT NULL COMMENT '统计日期',
  `recent_days` bigint(20) NOT NULL COMMENT '最近天数,1:最近1天,7:最近7天,30:最近30天',
  `home_count` bigint(20) DEFAULT NULL COMMENT '浏览首页人数',
  `good_detail_count` bigint(20) DEFAULT NULL COMMENT '浏览商品详情页人数',
  `cart_count` bigint(20) DEFAULT NULL COMMENT '加入购物车人数',
  `order_count` bigint(20) DEFAULT NULL COMMENT '下单人数',
  `payment_count` bigint(20) DEFAULT NULL COMMENT '支付人数',*/
    //封装数据 用来 封装查询得到的结果
    private String  dt;
    private int recent_days;
    private int home_count;
    private int good_detail_count;
    private int cart_count;
    private int order_count;
    private int payment_count;
}
