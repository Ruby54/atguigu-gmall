package com.atguigu.gmall2021.webapi.mapper;

import com.atguigu.gmall2021.webapi.bean.NameValueData;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface OrderTotalMapper {


    @Select("  SELECT    recent_days orderCount,uv_count orderUser   " +
            "  FROM      ads_vu_count  " +
            "  WHERE    dt= #{date}  and recent_days = #{days} "  )
    public Map getOrderTotal(@Param("date") String date, @Param("days") int days );

}
