package com.atguigu.gmall2021.statistics.controller;

import com.atguigu.gmall2021.statistics.bean.AdsActivityStats;
import com.atguigu.gmall2021.statistics.bean.QActivity;
import com.atguigu.gmall2021.statistics.bean.QOrder;
import com.atguigu.gmall2021.statistics.service.ActivityService;
import com.atguigu2021.commons.module.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: gmall
 * @description: 最近30天发布的活动的补贴率
 * @author: Zhao Yi
 * @create: 2021-12-04 16:03
 */
@RestController
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    /**
     * 根据开始时间查询活动名称
     * @param qActivity
     * @return
     */
    @RequestMapping(value ="getActivityNameByStartDate",method = {RequestMethod.POST})
    @CrossOrigin
    public List<String> getActivityNameByStartDate(@RequestBody QActivity qActivity) {
        List<String> activityNameList = activityService.getActivityNameByStartDate(qActivity);
        return activityNameList;
    }

    /**
     * 根据开始时间和活动名称查询所有活动统计
     * @param qActivity
     * @return
     */
    @RequestMapping(value ="getActivityList",method = {RequestMethod.POST})
    @ResponseBody
    @CrossOrigin
    public Response getActivityList(@RequestBody QActivity qActivity){
        Response rep = Response.build();
        try{
            rep.success().setResult(activityService.getActivityList(qActivity));
        }catch (Exception e){
            rep.fail(e);
        }
        return rep;
    }
}
