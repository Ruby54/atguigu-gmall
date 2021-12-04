package com.atguigu.gmall2021.statistics.controller;

import com.alibaba.fastjson.JSON;
import com.atguigu.gmall2021.statistics.bean.NameValueData;
import com.atguigu.gmall2021.statistics.service.TransactionAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: gmall
 * @description: 交易分析Controller
 */
@RestController
public class TransactionAnalysisController {

    @Autowired
    private TransactionAnalysisService transactionAnalysisService;
    /***
     * @description: 查询统计分析
     * @params:   [itemName, date, type]
     * @return: java.lang.String
     * @date: 2021/9/28
     */

    @RequestMapping("statsByItem")
    @CrossOrigin
    public String  statsByItem(@RequestParam("itemName") String itemName,
                               @RequestParam("date")String date,
                               @RequestParam("t") String type){

        List<NameValueData> dataList = transactionAnalysisService.getStatsWideByItem(itemName, date,type);
//        List<NameValueData> dataList=new ArrayList<>();
//        if("gender".equals(type)) {
//            Map genderMap = orderStatsEsService
//                    .getStatsWideByItem(itemName, date, "user_gender", 2);
//
//            if(genderMap!=null&&genderMap.size()>0){
//                Long femaleCount = (Long)genderMap.get("F");
//                Long  maleCount = (Long)genderMap.get("M");
//
//                dataList.add(new NameValueData("男",BigDecimal.valueOf(maleCount)));
//                dataList.add(new NameValueData("女",BigDecimal.valueOf(femaleCount)));
//            }
//        }else if("age".equals(type)){
//            Map<String,Long> statsMap = orderStatsEsService
//                    .getStatsWideByItem(itemName, date, "user_age", 100);
//            Long ageLT20=0L;
//            Long ageGTE20LTE29=0L;
//            Long ageGTE30=0L;
//            if(statsMap!=null&&statsMap.size()>0){
//                for (Map.Entry<String,Long> entry : statsMap.entrySet() ) {
//                    Integer age  = Integer.valueOf(entry.getKey());
//                    Long ageCount=entry.getValue();
//                    if(age<20){
//                        ageLT20+=ageCount;
//                    }else if (age>=30){
//                        ageGTE30+=ageCount;
//                    }else{
//                        ageGTE20LTE29+=ageCount;
//                    }
//                }
//
//                dataList.add(new NameValueData("20岁以下", BigDecimal.valueOf(ageLT20)));
//                dataList.add(new NameValueData("20岁至29岁",BigDecimal.valueOf(ageGTE20LTE29)));
//                dataList.add(new NameValueData("30岁及以上",BigDecimal.valueOf(ageGTE30)));
//            }
//        }

        return JSON.toJSONString(dataList);
    }
}
