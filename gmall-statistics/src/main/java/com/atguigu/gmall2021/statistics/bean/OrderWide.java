package com.atguigu.gmall2021.statistics.bean;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderWide {

    Long user_age;
    String user_gender;
    String province_name;
    String sku_name;
    BigDecimal order_price;
    BigDecimal  sku_num;
    BigDecimal total_amount;
    String create_time;
}
