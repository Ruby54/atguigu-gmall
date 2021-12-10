package com.atguigu.gmall2021.statistics.service.impl;

import com.atguigu.gmall2021.statistics.bean.NameValueData;
import com.atguigu.gmall2021.statistics.bean.OrderWide;
import com.atguigu.gmall2021.statistics.service.TransactionAnalysisService;
import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.core.search.aggregation.TermsAggregation;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: gmall
 * @description: 交易分析实现
 */
@Service
public class TransactionAnalysisServiceImpl implements TransactionAnalysisService {

    @Autowired
    JestClient jestClient;

    String INDEX_NAME_PREFIX="gmall0921_order_wide_";

    @Override
    public List<NameValueData> getStatsWideByItem(String itemName, String date, String type) {
        return null;
    }

//    @Override
//    public List<NameValueData> getStatsWideByItem(String itemName, String date, String type) {
//
//        List<NameValueData> dataList=new ArrayList<>();
//        //判断type类型，如果为gender是统计查询，为age则为明细查询
//        if("gender".equals(type)) {
//            Map genderMap = getStatsWideByItem(itemName, date, "user_gender", 2);
//
//            if(genderMap!=null&&genderMap.size()>0){
//                Long femaleCount = (Long)genderMap.get("F");
//                Long  maleCount = (Long)genderMap.get("M");
//
//                dataList.add(new NameValueData("男", BigDecimal.valueOf(maleCount)));
//                dataList.add(new NameValueData("女",BigDecimal.valueOf(femaleCount)));
//            }
//            //明细查询
//        }else if("age".equals(type)){
//            Map<String,Long> statsMap = getStatsWideByItem(itemName, date, "user_age", 100);
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
//                dataList.add(new NameValueData("20岁以下",BigDecimal.valueOf(ageLT20)));
//                dataList.add(new NameValueData("20岁至29岁",BigDecimal.valueOf(ageGTE20LTE29)));
//                dataList.add(new NameValueData("30岁及以上",BigDecimal.valueOf(ageGTE30)));
//            }
//        }
//        return dataList;
//    }

    /**
     * 明细查询
     */
    /**
    public Map getOrderWideByItem(String itemName, String date, Integer pageNo, Integer pageSize){
        Map orderMap=new HashMap();

        String indexName=INDEX_NAME_PREFIX+date;
        //组织查询条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //分页
        searchSourceBuilder.from((pageNo-1)*pageSize);
        searchSourceBuilder.size(pageSize);
        //高亮
        searchSourceBuilder.highlighter(new HighlightBuilder().field("sku_name"));
        //分词匹配
        searchSourceBuilder.query(new MatchQueryBuilder("sku_name",itemName)
                .operator(Operator.AND));
        //控制显示字段
        searchSourceBuilder.fetchSource(new String[]{"user_age",
                "user_gender","province_name",
                "sku_name","order_price","sku_num",
                "total_amount","create_time"},null);

        Search search = new Search.Builder(searchSourceBuilder.toString()).addIndex(indexName).build();
        try {
            SearchResult result = jestClient.execute(search);
            if(result!=null&&result.isSucceeded()){
                List<SearchResult.Hit<OrderWide, Void>> hits = result.getHits(OrderWide.class);
                List<OrderWide> orderWideList = new ArrayList();
                for (SearchResult.Hit<OrderWide, Void> hit : hits) {
                    OrderWide orderWide = hit.source;
                    //提取高亮字段
                    String sku_name = hit.highlight.get("sku_name").get(0);
                    orderWide.setSku_name(sku_name);
                    //转换性别
                    if("F".equals(orderWide.getUser_gender()) ){
                        orderWide.setUser_gender("女");
                    }else if("M".equals(orderWide.getUser_gender()) ) {
                        orderWide.setUser_gender("男");
                    }
                    orderWideList.add(hit.source);
                }
                orderMap.put("total",result.getTotal());
                orderMap.put("detail",orderWideList);
                return orderMap;
            }else{
                return  orderMap;
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw  new RuntimeException("es查询错误");
        }

    }*/



//    /**
//     * 统计查询
//     */
//    public Map getStatsWideByItem(String itemName, String date, String dimName, Integer groupSize ) {
//        Map genderMap=new HashMap();
//        //索引名称
//        String indexName=INDEX_NAME_PREFIX+date;
//        //组织查询条件
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//
//        searchSourceBuilder.query(new MatchQueryBuilder("sku_name",itemName)
//                .operator(Operator.AND));
//
//        String aggName="groupby_"+dimName;
//
//        TermsAggregationBuilder ageAggBuilder = AggregationBuilders.terms(aggName)
//                .field(dimName).size(groupSize);
//        searchSourceBuilder.aggregation(ageAggBuilder);
//
//        Search search = new Search.Builder(searchSourceBuilder.toString())
//                .addIndex(indexName).build();
//        try {
//            SearchResult result = jestClient.execute(search);
//            //处理返回结果
//            if(result!=null&&result.isSucceeded()){
//                List<TermsAggregation.Entry> buckets =
//                        result.getAggregations().getTermsAggregation(aggName).getBuckets();
//                for (TermsAggregation.Entry bucket : buckets) {
//                    genderMap.put(bucket.getKey(),bucket.getCount());
//                }
//                return genderMap;
//            }else{
//                return  genderMap;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw  new RuntimeException("es查询错误");
//        }
//
//    }
}
