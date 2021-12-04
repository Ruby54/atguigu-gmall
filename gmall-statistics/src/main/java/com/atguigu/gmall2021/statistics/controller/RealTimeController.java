package com.atguigu.gmall2021.statistics.controller;

import com.alibaba.fastjson.JSON;
import com.atguigu.gmall2021.statistics.bean.NameValueData;
import com.atguigu.gmall2021.statistics.service.RealTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program: gmall
 * @description: 实时统计Controller
 */
@RestController
public class RealTimeController {

    @Autowired
    private RealTimeService realTimeService;




}
