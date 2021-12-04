package com.atguigu.gmall2021.webapi.service;

import com.atguigu.gmall2021.webapi.bean.DtCount;
import com.atguigu.gmall2021.webapi.mapper.PvCountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface VisitStatsService {



    public List<DtCount> getPvCountLastDays(int days ,String dt );

    public List<DtCount> getUvCountLastDays(int days ,String dt );

    public List<Map> getNewVisitorStats(int days ,String dt );

    public List<Map> getChannelVisitStats(int days,String dt,String groupby);

    public  Map  getVisitTotal(int days ,String dt );

    public List<Map> getPagePathCount(int days ,String dt );


}
