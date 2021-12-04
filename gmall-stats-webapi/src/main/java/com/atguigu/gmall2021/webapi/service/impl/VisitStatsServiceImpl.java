package com.atguigu.gmall2021.webapi.service.impl;

import com.atguigu.gmall2021.webapi.bean.DtCount;
import com.atguigu.gmall2021.webapi.mapper.*;
import com.atguigu.gmall2021.webapi.service.VisitStatsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VisitStatsServiceImpl implements VisitStatsService {



    @Autowired
    VisitorMapper visitorMapper;


    @Autowired
    PagePathMapper pagePathMapper;




    @Override
    public List<DtCount> getPvCountLastDays(int days,String dt) {
        return visitorMapper.getPvCountLastDays(days,  dt);
    }

    @Override
    public List<DtCount> getUvCountLastDays(int days,String dt) {
        return  visitorMapper.getUvCountLastDays(days,  dt);
    }

    @Override
    public List<Map> getChannelVisitStats(int days,String dt,String groupby) {
        if("PV".equals(groupby)  ){
            return  visitorMapper.getChannelPvVisitStats(days,  dt ) ;
        }else if("BR".equals(groupby)  ) {
            return  visitorMapper.getChannelBounceRateVisitStats(days,  dt ) ;
        }
        return  new ArrayList<>();

    }


    @Override
    public List<Map> getNewVisitorStats(int days,String dt) {
        return  visitorMapper.getNewVisitorStats(days,dt);
    }

    public  Map  getVisitTotal(int days,String dt){
            return  visitorMapper.getVisitTotal(days,dt);
    }



    public List<Map> getPagePathCount(int days,String dt){
        return pagePathMapper.getPagePathCount(days,  dt);
    }
}
