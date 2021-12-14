package com.atguigu.gmall2021.statistics.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserStats {
    /*
  `dt` date NOT NULL COMMENT '统计日期',
  `recent_days` bigint(20) NOT NULL COMMENT '最近n日,1:最近1日,7:最近7日,30:最近30日',
  `new_user_count` bigint(20) DEFAULT NULL COMMENT '新增用户数',
  `active_user_count` bigint(20) DEFAULT NULL COMMENT '活跃用户数',*/

    //封装数据 用来 封装查询得到的结果
    private String dt;
    private int recent_days;
    private int new_user_count;
    private int active_user_count;


}
