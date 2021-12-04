package com.atguigu.gmall2021.webapi.service;



import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


public interface UserStatsService {

    public Map<String, Long> getUserActionConvert(int days ,String dt ) ;


    public Map<String, BigDecimal> getOrderUserStats(int days,String dt);

    public Map getUserTotal( int days ,String dt );

    public List<Map> getUseRetention(String dt);

}
