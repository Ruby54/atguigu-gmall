package com.atguigu.gmall2021.webapi.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.atguigu.gmall2021.webapi.bean.DtCount;
import com.atguigu.gmall2021.webapi.bean.EchartData;
import com.atguigu.gmall2021.webapi.bean.NameValueData;
import com.atguigu.gmall2021.webapi.service.ActivityStatsService;
import com.atguigu.gmall2021.webapi.service.OrderStatsService;
import com.atguigu.gmall2021.webapi.service.UserStatsService;
import com.atguigu.gmall2021.webapi.service.VisitStatsService;
import com.atguigu.gmall2021.webapi.utils.EchartsConverter;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class ApiController {

    @Autowired
    VisitStatsService visitStatsService;

    @Autowired
    UserStatsService userStatsService;

    @Autowired
    OrderStatsService orderStatsService;

    @Autowired
    ActivityStatsService activityStatsService;

    @RequestMapping(value = "pvCount", produces = "application/json")
    @CrossOrigin
    public JSONObject pvCount(@RequestParam("days") int days, @RequestParam("dt") String dt) {
        dt=getStatsDt(dt);
        List<DtCount> dtCountList = visitStatsService.getPvCountLastDays(days, dt);
        return EchartsConverter.convertFromDtCount(dtCountList);
    }

    @RequestMapping("uvCount")
    @CrossOrigin
    public JSONObject uvCount(@RequestParam("days") int days, @RequestParam("dt") String dt) {
        dt=getStatsDt(dt);
        List<DtCount> dtCountList = visitStatsService.getUvCountLastDays(days, dt);
        return EchartsConverter.convertFromDtCount(dtCountList);
    }


    @RequestMapping("visitTotal")
    @CrossOrigin
    public String getVisitTotal(@RequestParam("days") int days, @RequestParam("dt") String dt) {
        dt=getStatsDt(dt);
        Map statsMap = visitStatsService.getVisitTotal(days, dt);
        if (statsMap == null) {
            statsMap = new HashMap();
            statsMap.put("uvCount", BigDecimal.ZERO);
            statsMap.put("pvCount", BigDecimal.ZERO);
            statsMap.put("newCount", BigDecimal.ZERO);
        }
        return JSON.toJSONString(statsMap);

    }

    @RequestMapping("newVisitStats")
    @CrossOrigin
    public List<JSONObject> getNewVisitStats(@RequestParam("days") int days, @RequestParam("dt") String dt) {
        dt=getStatsDt(dt);
        List<Map> statsMapList = visitStatsService.getNewVisitorStats(days, dt);


        JSONObject pvJsonObj = new JSONObject();
        pvJsonObj.put("field", "浏览量");
        JSONObject uvJsonObj = new JSONObject();
        uvJsonObj.put("field", "访客数量");
        JSONObject bounceRateJsonObj = new JSONObject();
        bounceRateJsonObj.put("field", "跳出率(%)");
        JSONObject durPerSJsonObj = new JSONObject();
        durPerSJsonObj.put("field", "平均每次访问停留时间");
        JSONObject pvPerSJsonObj = new JSONObject();
        pvPerSJsonObj.put("field", "平均每次访问页面数");

        for (Map map : statsMapList) {
            String newOldFlag = "old";
            if (map.get("is_new").equals("1")) {
                newOldFlag = "new";
            }
            pvJsonObj.put(newOldFlag, map.getOrDefault("pv", "0"));
            uvJsonObj.put(newOldFlag, map.getOrDefault("uv", "0"));
            bounceRateJsonObj.put(newOldFlag, map.getOrDefault("bounce_rate", "0"));
            durPerSJsonObj.put(newOldFlag, map.getOrDefault("dur_per_session", "0"));
            pvPerSJsonObj.put(newOldFlag, map.getOrDefault("pv_per_session", "0"));
        }
        List<JSONObject> dataList = new ArrayList<>();
        dataList.add(pvJsonObj);
        dataList.add(uvJsonObj);
        dataList.add(bounceRateJsonObj);
        dataList.add(durPerSJsonObj);
        dataList.add(pvPerSJsonObj);

        return dataList;
    }


    /*
       xData:['渠道1', '渠道2', '渠道3', '渠道4', '渠道5' ],
       yData:{
          old: [ 23,21,311,341,4 ] ,
          new: [ 123,66,88,233,16 ]
         }
     */
    @RequestMapping("channel")
    @CrossOrigin
    public String getChannelPv(@RequestParam("days") int days, @RequestParam("t") String type, @RequestParam("dt") String dt) {
        dt=getStatsDt(dt);
        List<Map> statsMapList = visitStatsService.getChannelVisitStats(days, dt, type.toUpperCase());
        JSONArray xDataList = new JSONArray();

        JSONObject yDataMap = new JSONObject();
        JSONArray newDataList = new JSONArray();
        JSONArray oldDataList = new JSONArray();

        for (Map map : statsMapList) {
            String channel = (String) map.getOrDefault("channel", "");

            BigDecimal oldValue = (BigDecimal) map.getOrDefault("new", 0);

            BigDecimal newValue = (BigDecimal) map.getOrDefault("old", 0);
            newDataList.add(newValue);
            oldDataList.add(oldValue);
            xDataList.add(channel);
        }

        yDataMap.put("old", newDataList);
        yDataMap.put("new", oldDataList);


        return JSONObject.toJSONString(new EchartData(xDataList, yDataMap));
    }


    /*
      xData:['首页', '商品详情','加购物车' ,'订单','支付' ],
      yData:[
                {value: 200, name: '首页'},
                {value: 70, name: '商品详情'},
                {value: 60, name: '加购物车'},
                {value: 43, name: '订单'},
                {value: 33, name: '支付'}
            ]
    }
     */
    @RequestMapping("userActionConvert")
    @CrossOrigin
    public String getUserActionConvert(@RequestParam("days") int days, @RequestParam("dt") String dt) {
        dt=getStatsDt(dt);
        Map<String, Long> userActionConvertMap = userStatsService.getUserActionConvert(days, dt);
        JSONArray xDataList = new JSONArray(Arrays.asList("首页", "点击商品", "加购物车", "下单", "支付"));
        JSONArray yDataList = new JSONArray();
        if (userActionConvertMap != null) {
            BigDecimal homeCount = BigDecimal.valueOf( userActionConvertMap.getOrDefault("home_count", 0L));
            BigDecimal detailCount = BigDecimal.valueOf(userActionConvertMap.getOrDefault("good_detail_count", 0L));
            BigDecimal cartCount = BigDecimal.valueOf(userActionConvertMap.getOrDefault("cart_count", 0L));
            BigDecimal orderCount = BigDecimal.valueOf(userActionConvertMap.getOrDefault("order_count", 0L));
            BigDecimal payCount = BigDecimal.valueOf(userActionConvertMap.getOrDefault("payment_count", 0L));


            yDataList.add(new NameValueData("首页", BigDecimal.valueOf(100)));
            yDataList.add(new NameValueData("点击商品", detailCount.divide(homeCount, 3, RoundingMode.HALF_UP).movePointRight(2)));
            yDataList.add(new NameValueData("加购物车", cartCount.divide(homeCount, 3, RoundingMode.HALF_UP).movePointRight(2)));
            yDataList.add(new NameValueData("下单", orderCount.divide(homeCount, 3, RoundingMode.HALF_UP).movePointRight(2)));
            yDataList.add(new NameValueData("支付", payCount.divide(homeCount, 3, RoundingMode.HALF_UP).movePointRight(2)));

        }

        return new EchartData(xDataList, yDataList).toString();
    }

    /*
           xData:['渠道1', '渠道2', '渠道3', '渠道4', '渠道5' ],
           yData:[
                    {value: 40, name: '渠道1'},
                    {value: 33, name: '渠道2'},
                    {value: 28, name: '渠道3'},
                    {value: 22, name: '渠道4'},
                    {value: 20, name: '渠道5'}
                ]
         */
    @RequestMapping("userPaid")
    @CrossOrigin
    public String getUserPaid(@RequestParam("days") int days, @RequestParam("dt") String dt) {
        dt=getStatsDt(dt);
        Map<String, BigDecimal> statsMap = userStatsService.getOrderUserStats(days, dt);
        JSONArray xDataList = new JSONArray(Arrays.asList("未消费用户", "消费用户"));
        JSONArray yDataList = new JSONArray();
        if (statsMap != null) {
            BigDecimal orderUserCount = statsMap.getOrDefault("order_user_count", BigDecimal.ZERO);
            BigDecimal noOrderUserCount = statsMap.getOrDefault("no_order_user_count", BigDecimal.ZERO);

            yDataList.add(new NameValueData("未消费用户", noOrderUserCount));
            yDataList.add(new NameValueData("消费用户", orderUserCount));
        }

        return JSONObject.toJSONString(new EchartData(xDataList, yDataList));
    }


    /*
     xData: ["商品1", "商品2", "商品3"],
      yData: [55, 44, 21],
     */
    @RequestMapping("spu")
    @CrossOrigin
    public String spuTopN(@RequestParam("days") int days, @RequestParam("num") int num, @RequestParam("dt") String dt,@RequestParam("t") String type) {
        dt=getStatsDt(dt);
        List<NameValueData> nameValueDataList=null;
        if(type.equals("GMV")){
            nameValueDataList = orderStatsService.getOrderAmountBySpu(days, num, dt);
        }else if(type.equals("CT")){
            nameValueDataList = orderStatsService.getOrderCountBySpu(days, num, dt);
        }
        return EchartsConverter.converterFromNameValue(nameValueDataList, false).toJSONString();

    }

    /*
 xData: ["商品1", "商品2", "商品3"],
  yData: [55, 44, 21],
 */
    @RequestMapping("tmRepeat")
    @CrossOrigin
    public String getRepeatRateByTm(@RequestParam("days") int days, @RequestParam("num") int num, @RequestParam("dt") String dt) {
        dt=getStatsDt(dt);

        List<NameValueData> nameValueDataList = orderStatsService.getRepeatRateByTm(days, num, dt);
        return EchartsConverter.converterFromNameValue(nameValueDataList, false).toJSONString();

    }

    /*
     xData: ["品牌1", "品牌2", "品牌3"],
     yData: [55, 44, 21],
    */
    @RequestMapping("tm")
    @CrossOrigin
    public String tmTopN(@RequestParam("days") int days, @RequestParam("num") int num, @RequestParam("dt") String dt) {
        dt=getStatsDt(dt);
        List<NameValueData> nameValueDataList = orderStatsService.getOrderCountByTm(days, num, dt);
        return EchartsConverter.converterFromNameValue(nameValueDataList, false).toJSONString();

    }

    /*
     [
                 { name: "北京", value: 5000 },
                 { name: "内蒙古", value: 34443 }
      ]
      */
    @RequestMapping("area")
    @CrossOrigin
    public String areaOrder(@RequestParam("days") int days,
                            @RequestParam("dt") String dt) {
        dt=getStatsDt(dt);
        List<NameValueData> nameValueDataList =
                orderStatsService.getOrderCountByProvince(days, dt);
        return JSON.toJSONString(nameValueDataList);

    }


    /*
[
                {value: 1048, name: '品类1'},
                {value: 735, name: '品类2'},
                {value: 580, name: '品类3'},
                {value: 484, name: '品类4'},
                {value: 300, name: '其他'}
 ]
 */
    @RequestMapping("category")
    @CrossOrigin
    public String categoryOrder(@RequestParam("days") int days, @RequestParam("num") int num, @RequestParam("dt") String dt) {
        dt=getStatsDt(dt);
        List<NameValueData> orderCountByCategoryList = orderStatsService.getOrderCountByCategory(days, dt);
        orderCountByCategoryList.sort(new Comparator<NameValueData>() {
            @Override
            public int compare(NameValueData nv1, NameValueData nv2) {
                return nv2.getValue().compareTo(nv1.getValue());
            }
        });
        if (num < orderCountByCategoryList.size()) {
            List<NameValueData> mainDataList = orderCountByCategoryList.subList(0, num - 1);

            List<NameValueData> otherDataList = orderCountByCategoryList.subList(num - 1, orderCountByCategoryList.size());
            BigDecimal otherValue = BigDecimal.ZERO;
            for (NameValueData nameValueData : otherDataList) {
                otherValue.add(nameValueData.getValue());
            }
            mainDataList.add(new NameValueData("其他", otherValue));
            return JSON.toJSONString(mainDataList);
        } else {
            return JSON.toJSONString(orderCountByCategoryList);
        }


    }

    /*
     {
           orderCount:555,
           orderAmount:43431.00,
           orderUser:451
        }
     */
    @RequestMapping("orderTotal")
    @CrossOrigin
    public String orderTotal(@RequestParam("days") int days, @RequestParam("dt") String dt) {
        dt=getStatsDt(dt);
        Map orderTotalMap = orderStatsService.getOrderTotal(days, dt);
        if (orderTotalMap == null) {
            orderTotalMap = new HashMap();
            orderTotalMap.put("orderCount", BigDecimal.ZERO);
            orderTotalMap.put("orderAmount", BigDecimal.ZERO);
            orderTotalMap.put("orderUser", BigDecimal.ZERO);
        }
        return JSON.toJSONString(orderTotalMap);
    }


    /*
     nodeData:[{ name: 'a' },
         { name: 'b' },
         { name: 'a1' },
         { name: 'a2' },
         { name: 'b1' },
         { name: 'c' }],
      linksData:[{
            source: 'a',
            target: 'a1',
            value: 5
        }, {
            source: 'a',
            target: 'a2',
            value: 3
        }, {
            source: 'b',
            target: 'b1',
            value: 8
        }, {
            source: 'a',
            target: 'b1',
            value: 3
        }, {
            source: 'b1',
            target: 'a1',
            value: 1
        }, {
            source: 'b1',
            target: 'c',
            value: 2
        }]
     */
    @RequestMapping("pagePath")
    @CrossOrigin
    public String getPagePath(@RequestParam("days") int days, @RequestParam("dt") String dt) {
        dt=getStatsDt(dt);
        List<Map> pagePathMapList = visitStatsService.getPagePathCount(days, dt);
        HashSet<String> nodeSet = new HashSet();
        List<Map> nodeMapList = new ArrayList<>();
        for (Map pagePathMap : pagePathMapList) {
            String source = (String) pagePathMap.get("source");
            String target = (String) pagePathMap.get("target");
            nodeSet.add(source);
            nodeSet.add(target);
        }
        for (String nodeName : nodeSet) {
            Map<String, String> nodeMap = new HashMap<>();
            nodeMap.put("name", nodeName);
            nodeMapList.add(nodeMap);
        }
        Map rsMap = new HashMap();
        rsMap.put("nodeData", nodeMapList);
        rsMap.put("linksData", pagePathMapList);

        return JSON.toJSONString(rsMap);

    }

    @RequestMapping("userTotal")
    @CrossOrigin
    public String getUserTotal(@RequestParam("days") int days, @RequestParam("dt") String dt) {
        dt=getStatsDt(dt);
        Map userTotalMap = userStatsService.getUserTotal(days, dt);
        if (userTotalMap == null) {
            userTotalMap = new HashMap();
            userTotalMap.put("userTotal", BigDecimal.ZERO);
            userTotalMap.put("orderUserTotal", BigDecimal.ZERO);
            userTotalMap.put("newUserCount", BigDecimal.ZERO);
            userTotalMap.put("newOrderUserCount", BigDecimal.ZERO);
            userTotalMap.put("pct", BigDecimal.ZERO);
            userTotalMap.put("userChurn", BigDecimal.ZERO);
            userTotalMap.put("userChurn", BigDecimal.ZERO);
        }
        return JSON.toJSONString(userTotalMap);
    }

    @RequestMapping("userRetention")
    @CrossOrigin
    public String  getUserRetention(@RequestParam("dt") String dt){
        dt=getStatsDt(dt);
        List<Map> useRetentionList = userStatsService.getUseRetention(dt);

        return JSON.toJSONString(useRetentionList);
    }


    @RequestMapping("activity")
    @CrossOrigin
    public String  getActivity(@RequestParam("dt") String dt,@RequestParam("name") String name,
                                    @RequestParam("pageNo")int pageNo,@RequestParam("pageSize")int pageSize){
        dt=getStatsDt(dt);
        Map rsMap = activityStatsService.getActivityStats(name,dt,pageNo,pageSize);


        return JSON.toJSONString(rsMap);
    }


    @RequestMapping("coupon")
    @CrossOrigin
    public String  getUserCoupon(@RequestParam("dt") String dt,@RequestParam("name") String name,
                                    @RequestParam("pageNo")int pageNo,@RequestParam("pageSize")int pageSize){
        dt=getStatsDt(dt);
        Map rsMap = activityStatsService.getCouponStats(name,dt,pageNo,pageSize);


        return JSON.toJSONString(rsMap);
    }


    /**
     * 通过当前日期获得统计日期
     * @param td
     * @return
     */
    private String getStatsDt(String td){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = simpleDateFormat.parse(td);
            Date ydDate = DateUtils.addDays(date, -1);
            return simpleDateFormat.format(ydDate) ;
        } catch (ParseException e) {
            e.printStackTrace();

            throw  new RuntimeException("日期转换失败:"+td);
        }

    }
}
