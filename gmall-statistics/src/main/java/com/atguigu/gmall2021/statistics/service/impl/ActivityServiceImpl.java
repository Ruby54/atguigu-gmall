package com.atguigu.gmall2021.statistics.service.impl;

import com.atguigu.gmall2021.statistics.bean.AdsActivityStats;
import com.atguigu.gmall2021.statistics.bean.QActivity;
import com.atguigu.gmall2021.statistics.mapper.ActivityMapper;
import com.atguigu.gmall2021.statistics.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: gmall
 * @description:
 * @author: Zhao Yi
 * @create: 2021-12-04 16:09
 */
@Service
public class ActivityServiceImpl  implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;


    @Override
    public List<String> getActivityNameByStartDate(QActivity qActivity) {
        return activityMapper.getActivityNameByStartDate(qActivity.getStartDate());
    }

    @Override
    public List<AdsActivityStats> getActivityList(QActivity qActivity) {
        return activityMapper.getActivityList(qActivity);
    }
}
