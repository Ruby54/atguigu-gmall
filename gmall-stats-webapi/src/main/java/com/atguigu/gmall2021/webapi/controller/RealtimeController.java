package com.atguigu.gmall2021.webapi.controller;


import com.alibaba.fastjson.JSON;
import com.atguigu.gmall2021.webapi.bean.NameValueData;
import com.atguigu.gmall2021.webapi.service.DauService;
import com.atguigu.gmall2021.webapi.service.OrderStatsEsService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class RealtimeController {








    @Autowired
    OrderStatsEsService orderStatsEsService;

    @Autowired
    DauService dauService;


    /*
    查询明细
     */
    @RequestMapping("detailByItem")
    @CrossOrigin
    public String getOrderWideByItem(@RequestParam("itemName") String itemName,
                                     @RequestParam("date")String date,
                                     @RequestParam("pageNo") Integer pageNo,
                                     @RequestParam("pageSize") Integer pageSize){

        Map map = orderStatsEsService.getOrderWideByItem(itemName,date,pageNo,pageSize);
        return JSON.toJSONString(map);
    }

    /*
     查询统计分析
    */


    @RequestMapping("statsByItem")
    @CrossOrigin
    public String  statsByItem(@RequestParam("itemName") String itemName,
                                 @RequestParam("date")String date,
                                    @RequestParam("t") String type){
        List<NameValueData> dataList=new ArrayList<>();
        if("gender".equals(type)) {
            Map genderMap = orderStatsEsService
                    .getStatsWideByItem(itemName, date, "user_gender", 2);

            if(genderMap!=null&&genderMap.size()>0){
                Long femaleCount = (Long)genderMap.get("F");
                Long  maleCount = (Long)genderMap.get("M");

                dataList.add(new NameValueData("男",BigDecimal.valueOf(maleCount)));
                dataList.add(new NameValueData("女",BigDecimal.valueOf(femaleCount)));
            }
        }else if("age".equals(type)){
            Map<String,Long> statsMap = orderStatsEsService
                    .getStatsWideByItem(itemName, date, "user_age", 100);
            Long ageLT20=0L;
            Long ageGTE20LTE29=0L;
            Long ageGTE30=0L;
            if(statsMap!=null&&statsMap.size()>0){
                for (Map.Entry<String,Long> entry : statsMap.entrySet() ) {
                    Integer age  = Integer.valueOf(entry.getKey());
                    Long ageCount=entry.getValue();
                    if(age<20){
                        ageLT20+=ageCount;
                    }else if (age>=30){
                        ageGTE30+=ageCount;
                    }else{
                        ageGTE20LTE29+=ageCount;
                    }
                }

                dataList.add(new NameValueData("20岁以下",BigDecimal.valueOf(ageLT20)));
                dataList.add(new NameValueData("20岁至29岁",BigDecimal.valueOf(ageGTE20LTE29)));
                dataList.add(new NameValueData("30岁及以上",BigDecimal.valueOf(ageGTE30)));
            }
        }
        return JSON.toJSONString(dataList);
    }


    private String getYd(String td){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = simpleDateFormat.parse(td);
            Date ydDate = DateUtils.addDays(date, -1);
            return simpleDateFormat.format(ydDate) ;
        } catch (ParseException e) {
            e.printStackTrace();
            throw  new RuntimeException("日期转换失败");
        }

    }





    @RequestMapping("/dauRealtime")
    @CrossOrigin
    public String getDauRealtime(@RequestParam("td") String td){
        Long dauTotal=dauService.getDauTotal(td); //通过 td 从es中查询
        if(dauTotal==null){
            dauTotal=0L;
        }
        Map dauTdMap=dauService.getDauHourCount(td);//通过 td  从es中查询
        //时间减一天
        String yd = getYd(td);
        Map dauYdMap=dauService.getDauHourCount(yd);//通过 td-1天  从es中查询

        Map resultMap=new HashMap();
        resultMap.put("dauTotal",dauTotal);
        resultMap.put("dauTd",dauTdMap);
        resultMap.put("dauYd",dauYdMap);
        return JSON.toJSONString(resultMap);
    }
}
