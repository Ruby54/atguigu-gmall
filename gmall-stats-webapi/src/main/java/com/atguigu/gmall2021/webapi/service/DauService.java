package com.atguigu.gmall2021.webapi.service;

import java.util.Map;

public interface DauService {

    //方法1  根据日期查询总数
    public  Long getDauTotal(String date);

    //方法2 根据日期查询当日分时明细
    public Map<String,Long> getDauHourCount(String date);
}
