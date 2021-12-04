package com.atguigu.gmall2021.report.controller;

import com.atguigu.gmall2021.report.bean.QCustomQuery;
import com.atguigu.gmall2021.report.service.CustomQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @program: gmall
 * @description:
 */
@RestController
public class CustomQueryController {

    @Autowired
    private CustomQueryService customQueryService;


    /***
     * @description: 自定义查询
     * @params: [qCustomQuery]
     * @return: com.atguigu.gmall2021.report.bean.QCustomQuery
     * @date: 2021/9/28
     */
    @RequestMapping(value = "querySql", produces = "application/json")
    @CrossOrigin
    public QCustomQuery querySqlForType(@RequestBody QCustomQuery qCustomQuery) throws Exception {
        return customQueryService.querySqlForType(qCustomQuery);
    }

    @RequestMapping("test")
    public List<Map<String, Object>> getVisitTotal() {
        return customQueryService.findList();

    }
}
