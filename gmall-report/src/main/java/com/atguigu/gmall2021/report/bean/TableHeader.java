package com.atguigu.gmall2021.report.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: gmall
 * @description:表头信息
 */
@Data
@NoArgsConstructor
public class TableHeader {

    private String label;

    private String property;

    public TableHeader(String label, String property) {
        this.property = property;
        this.label = label;
    }
}
