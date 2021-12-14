package com.atguigu.gmall2021.statistics.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRetention {
    /*
    * `dt` date NOT NULL COMMENT '统计日期',
  `create_date` varchar(16) NOT NULL COMMENT '用户新增日期',
  `retention_day` int(20) NOT NULL COMMENT '截至当前日期留存天数',
  `retention_count` bigint(20) DEFAULT NULL COMMENT '留存用户数量',
  `new_user_count` bigint(20) DEFAULT NULL COMMENT '新增用户数量',
  `retention_rate` decimal(16,2) DEFAULT NULL COMMENT '留存率',*/
    //用来查询出来的数据并将数据返回给前端进行展示
    private String dt;
    private String create_date;
    private Integer retention_day;
    private Long retention_count;
    private Long new_user_count;
    private Double retention_rate;

}
