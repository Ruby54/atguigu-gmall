package com.atguigu.gmall2021.webapi.service.impl;


import com.atguigu.gmall2021.webapi.mapper.*;
import com.atguigu.gmall2021.webapi.service.UserStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class UserStatsServiceImpl implements UserStatsService {

    @Autowired
    UserActionMapper userActionMapper;



    @Autowired
    UserTotalMapper userTotalMapper;

    @Autowired
    UserRetentionMapper userRetentionMapper;

    @Autowired
    UserChangeMapper userChangeMapper;

    @Override
    public Map<String, Long> getUserActionConvert(int days,String dt){
         return  userActionMapper.getUserAction(days,dt);
    }

    @Override
    public Map<String, BigDecimal> getOrderUserStats(int days,String dt){
        return  userTotalMapper.getOrderUserStats(   dt ,  days);
    }

    public Map getUserTotal(  int days,String dt){
        Map userTotalMap = userTotalMapper.getUserTotal(dt, days);
        Map userChangeMap =userChangeMapper.getUserChange (dt, days);
        if(userChangeMap!=null&&userTotalMap!=null){
            userTotalMap.put( "userChurn", userChangeMap.get("userChurn"));
            userTotalMap.put( "userBack", userChangeMap.get("userBack"));
        }
        return userTotalMap;
    }

    public List<Map> getUseRetention(String dt){
        return   userRetentionMapper.getUserRetention(dt) ;
    }
}
