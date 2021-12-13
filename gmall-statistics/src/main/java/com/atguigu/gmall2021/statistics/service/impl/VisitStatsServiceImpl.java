package com.atguigu.gmall2021.statistics.service.impl;

import com.alibaba.fastjson.JSON;
import com.atguigu.gmall2021.statistics.bean.AdsTrafficStats;
import com.atguigu.gmall2021.statistics.mapper.PagePathMapper;
import com.atguigu.gmall2021.statistics.mapper.VisitTrafficStatsMapper;
import com.atguigu.gmall2021.statistics.service.VisitStatsService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

/**
 * @program: gmall
 * @description: 流量统计实现类
 * @author: Zhao Yi
 * @create: 2021-12-11 14:09
 */
@Service
public class VisitStatsServiceImpl implements VisitStatsService {

    @Autowired
    private PagePathMapper pagePathMapper;

    @Autowired
    private VisitTrafficStatsMapper visitTrafficStatsMapper;

    @Override
    public String getPagePathCount(int days, String dt) {
        Map rsMap = new HashMap();
            List<Map> pagePathMapList = pagePathMapper.getPagePathCount(days, dt);
            if(CollectionUtils.isNotEmpty(pagePathMapList)){
                HashSet<String> nodeSet = new HashSet();
                List<Map> nodeMapList = new ArrayList<>();
                for (Map pagePathMap : pagePathMapList) {
                    String source = (String) pagePathMap.get("source");
                    String target = (String) pagePathMap.get("target");
                    nodeSet.add(source);
                    nodeSet.add(target);
                }
                for (String nodeName : nodeSet) {
                    Map<String, String> nodeMap = new HashMap<>();
                    nodeMap.put("name", nodeName);
                    nodeMapList.add(nodeMap);
                }
                rsMap.put("nodeData", nodeMapList);
                rsMap.put("linksData", pagePathMapList);
            }


            return JSON.toJSONString(rsMap);
    }

    @Override
    public List<AdsTrafficStats> getTrafficStats(int days, String dt) {

        return visitTrafficStatsMapper.getTrafficStats(days,dt);
    }
}

