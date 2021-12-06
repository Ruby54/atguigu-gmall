package com.atguigu.gmall2021.statistics.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: gmall
 * @description:
 * @author: Zhao Yi
 * @create: 2021-12-06 15:42
 */
@Data
@NoArgsConstructor
public class AdsActivityStats {

    /**
     * 统计日期
     */
    private String dt;

    /**
     * 活动ID
     */
    private String activity_id;

    /**
     * 活动名称
     */
    private String activity_name;

    /**
     * 活动开始日期
     */
    private String start_date;

    /**
     * 补贴率
     */
    private String reduce_rate;

}
