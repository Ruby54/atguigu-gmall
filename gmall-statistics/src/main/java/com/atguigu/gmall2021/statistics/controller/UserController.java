package com.atguigu.gmall2021.statistics.controller;


import com.atguigu.gmall2021.statistics.bean.*;
import com.atguigu.gmall2021.statistics.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

@RestController
public class UserController {
    @Autowired
    private UserService userServicer;


    @RequestMapping(value ="getUserActionByDaysAndDt",produces = "application/json")
    @CrossOrigin
    public UserAction getUserActionByDaysAndDt(@RequestParam int days, @RequestParam String dt){
        System.out.println("---------------------");
        UserAction userActionByDaysAndDt = userServicer.getUserActionByDaysAndDt(days, dt);
        return userActionByDaysAndDt;
    }

    @RequestMapping(value ="getNewBuyerStatsByDaysAndDt",produces = "application/json")
    @CrossOrigin
     public NewBuyerStats getNewBuyerStatsByDaysAndDt(@RequestParam int days, @RequestParam String dt){
         System.out.println("---------------------");
         NewBuyerStats newBuyerStatsByDaysAndDt = userServicer.getNewBuyerStatsByDaysAndDt(days, dt);
         return newBuyerStatsByDaysAndDt;
     }


    @RequestMapping(value ="getUserChangeByDt",produces = "application/json")
    @CrossOrigin
     public UserChange getUserChangeByDt(@RequestParam String dt){
         System.out.println("---------------------");
         UserChange userChangeByDt = userServicer.getUserChangeByDt(dt);
         return userChangeByDt;
     }


    @RequestMapping(value ="getUserStatsByDaysAndDt",produces = "application/json")
    @CrossOrigin
     public UserStats getUserStatsByDaysAndDt(@RequestParam int days, @RequestParam String dt){
         System.out.println("---------------------");
         UserStats userStatsByDaysAndDt = userServicer.getUserStatsByDaysAndDt(days, dt);
         return userStatsByDaysAndDt;
     }



    @RequestMapping(value ="getUserRetentionByDt",produces = "application/json")
    @CrossOrigin
    public TreeMap<String, List<String>> getUserRetentionByDt(@RequestParam String dt){
        System.out.println("---------------------");
        List<UserRetention> userRetentionByDt = userServicer.getUserRetentionByDt(dt);
        TreeMap<String, List<String>> stringRetention = new TreeMap<>();
        for (int i = 0; i <userRetentionByDt.size() ; i++) {
            String strdate =new String(userRetentionByDt.get(i).getCreate_date());
            if(stringRetention.containsKey(strdate)){
                stringRetention.get(strdate).set(userRetentionByDt.get(i).getRetention_day(),userRetentionByDt.get(i).getRetention_rate()+"");
            }else{
                List<String> stringArryList = Arrays.asList(new String[8]);
                stringArryList.set(0,userRetentionByDt.get(i).getNew_user_count()+"");
                stringArryList.set(userRetentionByDt.get(i).getRetention_day(),userRetentionByDt.get(i).getRetention_rate()+"");
                stringRetention.put(strdate,stringArryList);
            }
        }
        return stringRetention;
    }

}
