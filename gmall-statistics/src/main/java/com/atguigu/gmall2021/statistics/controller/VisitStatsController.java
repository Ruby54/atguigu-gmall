package com.atguigu.gmall2021.statistics.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.atguigu.gmall2021.statistics.service.VisitStatsService;
import com.atguigu2021.commons.module.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @program: gmall
 * @description: 流量统计controller
 * @author: Zhao Yi
 * @create: 2021-12-11 14:06
 */
@RestController
public class VisitStatsController {

    @Autowired
    private VisitStatsService visitStatsService;

//    @RequestMapping("newVisitStats")
//    @CrossOrigin
//    public List<JSONObject> getNewVisitStats(@RequestParam("days") int days, @RequestParam("dt") String dt) {
////        dt=getStatsDt(dt);
//        List<Map> statsMapList = visitStatsService.getNewVisitorStats(days, dt);
//
//
//        JSONObject pvJsonObj = new JSONObject();
//        pvJsonObj.put("field", "浏览量");
//        JSONObject uvJsonObj = new JSONObject();
//        uvJsonObj.put("field", "访客数量");
//        JSONObject bounceRateJsonObj = new JSONObject();
//        bounceRateJsonObj.put("field", "跳出率(%)");
//        JSONObject durPerSJsonObj = new JSONObject();
//        durPerSJsonObj.put("field", "平均每次访问停留时间");
//        JSONObject pvPerSJsonObj = new JSONObject();
//        pvPerSJsonObj.put("field", "平均每次访问页面数");
//
//        for (Map map : statsMapList) {
//            String newOldFlag = "old";
//            if (map.get("is_new").equals("1")) {
//                newOldFlag = "new";
//            }
//            pvJsonObj.put(newOldFlag, map.getOrDefault("pv", "0"));
//            uvJsonObj.put(newOldFlag, map.getOrDefault("uv", "0"));
//            bounceRateJsonObj.put(newOldFlag, map.getOrDefault("bounce_rate", "0"));
//            durPerSJsonObj.put(newOldFlag, map.getOrDefault("dur_per_session", "0"));
//            pvPerSJsonObj.put(newOldFlag, map.getOrDefault("pv_per_session", "0"));
//        }
//        List<JSONObject> dataList = new ArrayList<>();
//        dataList.add(pvJsonObj);
//        dataList.add(uvJsonObj);
//        dataList.add(bounceRateJsonObj);
//        dataList.add(durPerSJsonObj);
//        dataList.add(pvPerSJsonObj);
//
//        return dataList;
//    }


    @RequestMapping("getPagePath")
    @CrossOrigin
    public String getPagePath(@RequestParam("days") int days, @RequestParam("dt") String dt) {
        String pagePathString = visitStatsService.getPagePathCount(days, dt);

        return visitStatsService.getPagePathCount(days, dt);
    }

    @RequestMapping(value ="getTrafficStats",method = {RequestMethod.GET})
    @CrossOrigin
    public Response getTrafficStats(@RequestParam int days, @RequestParam String dt) {
        Response rep = Response.build();
        try {
            rep.success().setResult(visitStatsService.getTrafficStats(days, dt));
        } catch (Exception e) {
            rep.fail(e);
        }
        return rep;
    }
}


