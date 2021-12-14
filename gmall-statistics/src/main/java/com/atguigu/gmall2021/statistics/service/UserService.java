package com.atguigu.gmall2021.statistics.service;

import com.atguigu.gmall2021.statistics.bean.*;

import java.util.List;

public interface UserService {

    UserAction getUserActionByDaysAndDt(int days, String dt);

    NewBuyerStats getNewBuyerStatsByDaysAndDt(int days, String dt);

    UserChange getUserChangeByDt(String dt);

    UserStats getUserStatsByDaysAndDt(int days, String dt);

    public List<UserRetention> getUserRetentionByDt(String dt);
}
