package com.atguigu.gmall2021.report.service;

import com.atguigu.gmall2021.report.bean.QCustomQuery;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @program: gmall
 * @description:
 */
public interface CustomQueryService {
    /***
     * @description: 测试类
     * @params: []
     * @return: java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @date: 2021/9/28
     */

    List<Map<String, Object>> findList();

    /***
     * @description: 根据sql以及元数据组件查询结果，支持hive和presto查询
     * @params: [qCustomQuery]
     * @return: com.atguigu.gmall2021.report.bean.QCustomQuery
     * @date: 2021/9/28
     */

    QCustomQuery querySqlForType(QCustomQuery qCustomQuery) throws SQLException;
}
