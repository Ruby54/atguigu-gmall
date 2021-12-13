package com.atguigu.gmall2021.statistics.service.impl;

import com.atguigu.gmall2021.statistics.bean.AdsActivityStats;
import com.atguigu.gmall2021.statistics.bean.QActivity;
import com.atguigu.gmall2021.statistics.mapper.ActivityMapper;
import com.atguigu.gmall2021.statistics.service.ActivityService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: gmall
 * @description:活动分析实现类
 * @author: Zhao Yi
 * @create: 2021-12-04 16:09
 */
@Service
public class ActivityServiceImpl  implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;


    @Override
    public List<String> getActivityNameByStartDate(QActivity qActivity) {
        List<String> nameList = new ArrayList<String>();
        if(StringUtils.isNotEmpty(qActivity.getStartDate())){
            nameList = activityMapper.getActivityNameByStartDate(qActivity.getStartDate());
        }
        return nameList;
    }

    @Override
    public List<AdsActivityStats> getActivityList(QActivity qActivity) {
        List<AdsActivityStats> activityList = new ArrayList<>();
        if(qActivity!=null){
            activityList = activityMapper.getActivityList(qActivity);
        }
        return activityList;
    }
}
