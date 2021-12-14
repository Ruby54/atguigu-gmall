package com.atguigu.gmall2021.statistics.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserChange {
    /*
  `dt` varchar(16) NOT NULL COMMENT '统计日期',
  `user_churn_count` varchar(16) DEFAULT NULL COMMENT '流失用户数',
  `user_back_count` varchar(16) DEFAULT NULL COMMENT '回流用户数',*/
    //封装数据 用来 封装查询得到的结果
    private String  dt;
    private int user_churn_count;
    private int user_back_count;

}
