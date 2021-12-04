package com.atguigu.gmall2021.webapi.bean;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EchartData {

    JSON xData;
    JSON yData;
    public String toString(){
        return JSON.toJSONString(this);
    }
}
