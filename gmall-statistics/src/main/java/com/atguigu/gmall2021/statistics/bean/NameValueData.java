package com.atguigu.gmall2021.statistics.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NameValueData {

    String name;
    BigDecimal value;

    String lable;


    public NameValueData(String name,BigDecimal value){
        this.name = name;
        this.value = value;
    }

}
