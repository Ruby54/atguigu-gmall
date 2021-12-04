package com.atguigu.gmall2021.statistics.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @program: gmall
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Echart {
    private List<String> xData;

    private List<String> yData;

}
