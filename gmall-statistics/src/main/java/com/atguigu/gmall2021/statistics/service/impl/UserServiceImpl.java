package com.atguigu.gmall2021.statistics.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.atguigu.gmall2021.statistics.bean.EchartData;
import com.atguigu.gmall2021.statistics.bean.NameValueData;
import com.atguigu.gmall2021.statistics.mapper.UserChangeMapper;
import com.atguigu.gmall2021.statistics.mapper.UserMapper;
import com.atguigu.gmall2021.statistics.mapper.UserNewBuyerStatsMapper;
import com.atguigu.gmall2021.statistics.mapper.UserStatsMapper;
import com.atguigu.gmall2021.statistics.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * @program: gmall
 * @description:
 * @author: Zhao Yi
 * @create: 2021-12-10 17:15
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserChangeMapper userChangeMapper;

    @Autowired
    private UserStatsMapper userStatsMapper;

    @Autowired
    private UserNewBuyerStatsMapper  userNewBuyerStatsMapper;

    @Override
    public String getUserActionConvert( int days, String dt) {

        Map<String, Long> userActionConvertMap = userMapper.getUserAction(days, dt);

        JSONArray xDataList = new JSONArray(Arrays.asList("浏览首页人数", "浏览商品详情页人数", "加入购物车人数", "下单人数", "支付人数"));
        JSONArray yDataList = new JSONArray();
        if (userActionConvertMap != null) {
            BigDecimal homeCount = BigDecimal.valueOf( userActionConvertMap.getOrDefault("home_count", 0L));
            BigDecimal detailCount = BigDecimal.valueOf(userActionConvertMap.getOrDefault("good_detail_count", 0L));
            BigDecimal cartCount = BigDecimal.valueOf(userActionConvertMap.getOrDefault("cart_count", 0L));
            BigDecimal orderCount = BigDecimal.valueOf(userActionConvertMap.getOrDefault("order_count", 0L));
            BigDecimal payCount = BigDecimal.valueOf(userActionConvertMap.getOrDefault("payment_count", 0L));

            //计算百分比
            yDataList.add(new NameValueData("浏览首页人数", BigDecimal.valueOf(100)));
            yDataList.add(new NameValueData("浏览商品详情页人数", detailCount.divide(homeCount, 3, RoundingMode.HALF_UP).movePointRight(2)));
            yDataList.add(new NameValueData("加入购物车人数", cartCount.divide(homeCount, 3, RoundingMode.HALF_UP).movePointRight(2)));
            yDataList.add(new NameValueData("下单人数", orderCount.divide(homeCount, 3, RoundingMode.HALF_UP).movePointRight(2)));
            yDataList.add(new NameValueData("支付人数", payCount.divide(homeCount, 3, RoundingMode.HALF_UP).movePointRight(2)));

        }
        return new EchartData(xDataList, yDataList).toString();
    }

    @Override
    public String getUseRetention(String dt) {
        List<Map> useRetentionList = userMapper.getUserRetention(dt);
        if(CollectionUtils.isEmpty(useRetentionList)){
            useRetentionList = new ArrayList<>();
        }
        return JSON.toJSONString(useRetentionList);
    }

    @Override
    public String getUserTotal( String dt,int days) {
        Map userTotalMap = new HashMap();
        Map userStatsMap = userStatsMapper.getUserStats(dt, days);
        Map userChangeMap = userChangeMapper.getUserChange (dt, days);
        Map userNewBuyerStatsMap = userNewBuyerStatsMapper.getNewBuyerStats(dt, days);
        if(userStatsMap!=null){
            userTotalMap.put("userTotal",userStatsMap.get("userTotal"));
            userTotalMap.put("activeUserTotal",userStatsMap.get("activeUserTotal"));
        }
        if(userChangeMap!=null){
            userTotalMap.put("userChurn",userChangeMap.get("userChurn"));
            userTotalMap.put("userBack",userChangeMap.get("userBack"));
        }

        if(userNewBuyerStatsMap!=null){
            userTotalMap.put("newUserCount",userNewBuyerStatsMap.get("newUserCount"));
            userTotalMap.put("newOrderUserCount",userNewBuyerStatsMap.get("newOrderUserCount"));
        }
        if (userStatsMap == null&&userChangeMap==null&&userNewBuyerStatsMap==null) {
            userTotalMap.put("userTotal", BigDecimal.ZERO);
            userTotalMap.put("activeUserTotal", BigDecimal.ZERO);
            userTotalMap.put("newUserCount", BigDecimal.ZERO);
            userTotalMap.put("newOrderUserCount", BigDecimal.ZERO);
            userTotalMap.put("userChurn", BigDecimal.ZERO);
            userTotalMap.put("userBack", BigDecimal.ZERO);
        }
//        }else if(userStatsMap!=null){
//            userTotalMap.put("userTotal",userStatsMap.get("userTotal"));
//            userTotalMap.put("activeUserTotal",userStatsMap.get("activeUserTotal"));
//        }
//        else if(userChangeMap!=null){
//            userTotalMap.put("userChurn",userChangeMap.get("userChurn"));
//            userTotalMap.put("userBack",userChangeMap.get("userBack"));
//        }
//        else if(userNewBuyerStatsMap!=null){
//            userTotalMap.put("newUserCount",userNewBuyerStatsMap.get("newUserCount"));
//            userTotalMap.put("newOrderUserCount",userNewBuyerStatsMap.get("newOrderUserCount"));
//        }
        return JSON.toJSONString(userTotalMap);
    }
}
