package com.atguigu.gmall2021.statistics.controller;

import com.alibaba.fastjson.JSON;
import com.atguigu.gmall2021.statistics.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: gmall
 * @description:用户主题
 * @author: Zhao Yi
 * @create: 2021-12-10 17:06
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(value ="getUserActionConvert",method = {RequestMethod.GET})
    @CrossOrigin
    public String getUserActionConvert(@RequestParam int recentDays, @RequestParam String curDate){
       return  userService.getUserActionConvert(recentDays,curDate);
    }


    @RequestMapping("getUserRetention")
    @CrossOrigin
    public String  getUserRetention(@RequestParam("dt") String dt){
        return userService.getUseRetention(dt);
    }

    @RequestMapping("getUserTotal")
    @CrossOrigin
    public String getUserTotal(@RequestParam("dt") String dt,@RequestParam("days") int days ) {
       String userTotal = userService.getUserTotal( dt,days);
        return userTotal;
    }
}
