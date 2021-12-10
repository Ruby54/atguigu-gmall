package com.atguigu.gmall2021.statistics.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.atguigu.gmall2021.statistics.service.GoodsService;
import com.atguigu2021.commons.module.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @program: gmall
 * @description:商品统计
 * @author: Zhao Yi
 * @create: 2021-12-09 16:03
 */
@RestController
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 最近7/30日各品牌复购率
     * @param recentDays
     * @param curDate
     * @param showNum
     * @return
     */
    @RequestMapping(value ="getTmRepeat",method = {RequestMethod.GET})
    @CrossOrigin
    public String getTmRepeat(@RequestParam int recentDays, @RequestParam String curDate,@RequestParam int showNum){
        Response rep = Response.build();
        try{
            rep.success().setResult(goodsService.getTmRepeat(recentDays,curDate,showNum));
        }catch (Exception e){
            rep.fail(e);
        }
        return goodsService.getTmRepeat(recentDays,curDate,showNum);
    }


    /**
     *各品牌商品交易统计
     * @param recentDays
     * @param curDate
     * @return
     */
    @RequestMapping(value ="getTmTradeStats",method = {RequestMethod.GET})
    @CrossOrigin
    public Response getTmTradeStats(@RequestParam int recentDays, @RequestParam String curDate){
        Response rep = Response.build();
        try{
            rep.success().setResult(goodsService.getTmTradeStats(recentDays,curDate));
        }catch (Exception e){
            rep.fail(e);
        }
        return rep;
    }

    /**
     * 各品类商品交易统计
     * @param recentDays
     * @param curDate
     * @return
     */
    @RequestMapping(value ="getCateTradeStats",method = {RequestMethod.GET})
    @CrossOrigin
    public Response getCateTradeStats(@RequestParam int recentDays, @RequestParam String curDate){
        Response rep = Response.build();
        try{
            rep.success().setResult(goodsService.getCateTradeStats(recentDays,curDate));
        }catch (Exception e){
            rep.fail(e);
        }
        return rep;
    }
    /**
     * 各分类商品购物车存量Top10:查询一级分类
     * @param curDate
     * @return
     */
    @RequestMapping(value ="getCategory1",method = {RequestMethod.GET})
    @CrossOrigin
    public Response getCategory1( @RequestParam String curDate){
        Response rep = Response.build();
        try{
            rep.success().setResult(goodsService.getCategory1(curDate));
        }catch (Exception e){
            rep.fail(e);
        }
        return rep;
    }

    /**
     * 各分类商品购物车存量Top10:查询二级分类
     * @param curDate
     * @param category1_id
     * @return
     */
    @RequestMapping(value ="getCategory2",method = {RequestMethod.GET})
    @CrossOrigin
    public Response getCategory2( @RequestParam String curDate,@RequestParam String category1_id){
        Response rep = Response.build();
        try{
            rep.success().setResult(goodsService.getCategory2(curDate,category1_id));
        }catch (Exception e){
            rep.fail(e);
        }
        return rep;
    }

    /**
     * 各分类商品购物车存量Top10:查询三级分类
     * @param curDate
     * @param category1_id
     * @param category2_id
     * @return
     */
    @RequestMapping(value ="getCategory3",method = {RequestMethod.GET})
    @CrossOrigin
    public Response getCategory3( @RequestParam String curDate,@RequestParam String category1_id,@RequestParam String category2_id){
        Response rep = Response.build();
        try{
            rep.success().setResult(goodsService.getCategory3(curDate,category1_id,category2_id));
        }catch (Exception e){
            rep.fail(e);
        }
        return rep;
    }

    @RequestMapping(value ="getTmTopNData",method = {RequestMethod.GET})
    @CrossOrigin
    public String getTmTopNData( @RequestParam String curDate,@RequestParam String category1_id,@RequestParam String category2_id,@RequestParam String category3_id){
        Response rep = Response.build();
        try{
            rep.success().setResult(goodsService.getTmTopNData(curDate,category1_id,category2_id,category3_id));
        }catch (Exception e){
            rep.fail(e);
        }
        return goodsService.getTmTopNData(curDate,category1_id,category2_id,category3_id);
    }

}
