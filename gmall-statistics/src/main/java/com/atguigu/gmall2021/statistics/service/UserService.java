package com.atguigu.gmall2021.statistics.service;

import java.util.List;
import java.util.Map;

/**
 * @program: gmall
 * @description:
 * @author: Zhao Yi
 * @create: 2021-12-10 17:15
 */
public interface UserService {

    String getUserActionConvert(int recentDays, String curDate);

    String getUseRetention(String dt);

    String getUserTotal(String dt,int days);

}
