package com.atguigu.gmall2021.report.service.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.util.Map;


import com.atguigu.gmall2021.report.bean.QCustomQuery;
import com.atguigu.gmall2021.report.bean.TableHeader;
import com.atguigu.gmall2021.report.service.CustomQueryService;
import com.mysql.jdbc.StringUtils;
import org.apache.lucene.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * @program: gmall
 * @description
 */
@Service
public class CustomQueryServiceImpl implements CustomQueryService {
    @Autowired
    @Qualifier("prestoTemplate")
    private JdbcTemplate prestoTemplate;

    @Autowired
    @Qualifier("hiveTemplate")
    private JdbcTemplate hiveTemplate;

    /***
     * @description: 测试类
     * @params: []
     * @return: java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @date: 2021/9/28
     */

    @Override
    public List<Map<String, Object>> findList() {
        // TODO Auto-generated method stub
        return prestoTemplate.queryForList("SELECT id, name, age from student limit 5");

    }

    /***
     * @description: 根据sql以及元数据组件查询结果
     * @params: [qCustomQuery]
     * @return: com.atguigu.gmall2021.report.bean.QCustomQuery
     * @date: 2021/9/28
     */

    @Override
    public QCustomQuery querySqlForType(QCustomQuery qCustomQuery) throws SQLException {
        List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
        if (qCustomQuery != null && !StringUtils.isNullOrEmpty(qCustomQuery.getTypeMetaData())) {
            //判断元数据类型，来确定使用哪种JdbcDriver
            if (qCustomQuery.getTypeMetaData().equals("hive")) {
                maps = hiveTemplate.queryForList(qCustomQuery.getSql());
            } else if (qCustomQuery.getTypeMetaData().equals("presto")) {
                maps = prestoTemplate.queryForList(qCustomQuery.getSql());
            }
        }
        //将结果封装到QCustomQuery对象中
        QCustomQuery returnQCustomQuery = new QCustomQuery();
        returnQCustomQuery.setQueryMap(maps);
        returnQCustomQuery.setTableHeaderList(tableHeader(maps));
        return returnQCustomQuery;
    }


    /***
     * @description: 获取头信息
     * @params: [maps]
     * @return: java.util.List<com.atguigu.gmall2021.report.bean.TableHeader>
     * @date: 2021/9/28
     */

    public List<TableHeader> tableHeader(List<Map<String, Object>> maps) {
        List<TableHeader> tableHeaderList = new ArrayList<TableHeader>();
        if (!CollectionUtils.isEmpty(maps)) {
            Map<String, Object> result = maps.get(0);
            for (Map.Entry<String, Object> entry : result.entrySet()) {
                tableHeaderList.add(new TableHeader(entry.getKey(), entry.getKey()));
            }
        }
        return tableHeaderList;
    }

}
