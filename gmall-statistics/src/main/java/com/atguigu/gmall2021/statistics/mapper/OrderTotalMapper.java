package com.atguigu.gmall2021.statistics.mapper;

import com.atguigu.gmall2021.statistics.bean.DtCount;
import com.atguigu.gmall2021.statistics.bean.NameValueData;
import com.atguigu.gmall2021.statistics.bean.QOrder;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Property;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface OrderTotalMapper {


    @Select("  SELECT   dt name,uv_count value   " +
            "  FROM    ads_vu_count  " +
            "  WHERE    dt>= #{startTime}  and dt<= #{endTime} and recent_days =7"  )
    public List<NameValueData> getOrderTotal(QOrder qOrder);

}
