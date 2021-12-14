package com.atguigu.gmall2021.statistics.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewBuyerStats {
 /* `dt` date NOT NULL COMMENT '统计日期',
  `recent_days` bigint(20) NOT NULL COMMENT '最近天数,1:最近1天,7:最近7天,30:最近30天',
  `new_order_user_count` bigint(20) DEFAULT NULL COMMENT '新增下单人数',
  `new_payment_user_count` bigint(20) DEFAULT NULL COMMENT '新增支付人数',*/
 //用来查询出来的数据并将数据返回给前端进行展示
 private String  dt;
 private int recent_days;
 private int new_order_user_count;
 private int new_payment_user_count;

}
