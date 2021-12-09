package com.atguigu.gmall2021.statistics.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @program: gmall
 * @description:
 * @author: Zhao Yi
 * @create: 2021-12-07 17:20
 */
@Data
@NoArgsConstructor
public class AdsOrderByProvince {

    private String dt;

    private int recent_days;

    private String province_id;

    private String province_name;

    private String area_code;

    private String iso_code;

    private String iso_code_3166_2;

    private int order_count;

    private BigDecimal order_total_amount;

}
