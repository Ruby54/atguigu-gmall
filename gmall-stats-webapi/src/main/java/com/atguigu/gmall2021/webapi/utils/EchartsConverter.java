package com.atguigu.gmall2021.webapi.utils;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.gmall2021.webapi.bean.DtCount;
import com.atguigu.gmall2021.webapi.bean.NameValueData;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class EchartsConverter {


    public static JSONObject convertFromDtCount (List<DtCount> dtCountList  ){
       return   convertFromDtCount( dtCountList,  true  );
    }

    public static JSONObject convertFromDtCount (List<DtCount> dtCountList,Boolean isYNameXValue  ){
        List<String> xdata =new ArrayList<>();
        List<Long> ydata =new ArrayList<>();
        for (DtCount dtCount : dtCountList) {
            xdata.add(dtCount.getDt());
            ydata.add(dtCount.getCt() );
        }

        JSONObject jsonObject = new JSONObject();
        if(isYNameXValue){
            jsonObject.put("xData",xdata);
            jsonObject.put("yData",ydata);
        }else{
            jsonObject.put("yData",xdata);
            jsonObject.put("xData",ydata);
        }
        return jsonObject;

    }

    public static JSONObject converterFromNameValue(List<NameValueData> nameValueDataList  ){
        return   converterFromNameValue( nameValueDataList,  true  );
    }

    public static JSONObject converterFromNameValue(List<NameValueData> nameValueDataList, Boolean isYNameXValue  ) {
        List<String> nameList =new ArrayList<>();
        List<BigDecimal> valueList =new ArrayList<>();
        for (NameValueData nameValueData : nameValueDataList) {
            nameList.add(nameValueData.getName());
            valueList.add(nameValueData.getValue() );
        }

        JSONObject jsonObject = new JSONObject();
        if(isYNameXValue){
            jsonObject.put("xData",nameList);
            jsonObject.put("yData",valueList);
        }else{
            jsonObject.put("yData",nameList);
            jsonObject.put("xData",valueList);
        }
        return jsonObject;

    }
}
