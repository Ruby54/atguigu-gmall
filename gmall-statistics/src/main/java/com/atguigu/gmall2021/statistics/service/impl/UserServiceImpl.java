package com.atguigu.gmall2021.statistics.service.impl;

import com.atguigu.gmall2021.statistics.bean.*;
import com.atguigu.gmall2021.statistics.mapper.UserMapper;
import com.atguigu.gmall2021.statistics.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    public UserAction getUserActionByDaysAndDt(int days, String dt) {

        return userMapper.getUserActionByDaysAndDt(days,dt);
    }

    @Override
    public NewBuyerStats getNewBuyerStatsByDaysAndDt(int days, String dt) {
        return userMapper.getNewBuyerStatsByDaysAndDt(days,dt);
    }

    @Override
    public UserChange getUserChangeByDt(String dt) {

        return userMapper.getUserChangeByDt(dt);
    }

    @Override
    public UserStats getUserStatsByDaysAndDt(int days, String dt) {
        return userMapper.getUserStatsByDaysAndDt(days,dt);
    }

    @Override
    public List<UserRetention> getUserRetentionByDt(String dt) {
        List<UserRetention> userRetentionByDt = userMapper.getUserRetentionByDt(dt);
        return userRetentionByDt;
    }

}
