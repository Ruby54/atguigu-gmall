package com.atguigu.gmall2021.report.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @program: gmall
 * @description:查询类以及返回
 */
@Data
@NoArgsConstructor
public class QCustomQuery {
    //查询sql
    private String sql;

    //数据源类型
    private String typeMetaData;

    //返回map
    private List<Map<String, Object>> queryMap;

    //返回头信息list
    private List<TableHeader> tableHeaderList;


}
