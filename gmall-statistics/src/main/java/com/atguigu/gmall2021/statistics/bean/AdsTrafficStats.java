package com.atguigu.gmall2021.statistics.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @program: gmall
 * @description: 各渠道流量统计
 * @author: Zhao Yi
 * @create: 2021-12-11 14:56
 */
@Data
@NoArgsConstructor
public class AdsTrafficStats {
    /**
     * 统计日期
     */
    private String dt;

    private int recent_days;

    private String channel;

    private int uv_count;

    private int avg_duration_sec;

    private int sv_count;

    private BigDecimal bounce_rate;

//
//            `recent_days` bigint(20) NOT NULL COMMENT '最近天数,1:最近1天,7:最近7天,30:最近30天',
//            `channel` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '渠道',
//            `uv_count` bigint(20) NULL DEFAULT NULL COMMENT '访客人数',
//            `avg_duration_sec` bigint(20) NULL DEFAULT NULL COMMENT '会话平均停留时长，单位为秒',
//            `avg_page_count` bigint(20) NULL DEFAULT NULL COMMENT '会话平均浏览页面数',
//            `sv_count` bigint(20) NULL DEFAULT NULL COMMENT '会话数',
//            `bounce_rate
}
