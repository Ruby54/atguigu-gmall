package com.atguigu.gmall2021.webapi.mapper;

import com.atguigu.gmall2021.webapi.bean.NameValueData;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface OrderProvinceMapper {

    @Select("    SELECT  province_name name,  SUM(order_count)    value " +
            " FROM     ads_order_by_province " +
            "  WHERE    dt= #{date}   AND  recent_days=#{days}   " +
            "  GROUP BY province_id,province_name  order by sum(order_count) asc ")
    public List<NameValueData> getOrderCountByProvince(@Param("date") String date,
                                                       @Param("days") int days );
}
