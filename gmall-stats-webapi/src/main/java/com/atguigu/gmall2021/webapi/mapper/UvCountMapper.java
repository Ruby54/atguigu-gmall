package com.atguigu.gmall2021.webapi.mapper;

import com.atguigu.gmall2021.webapi.bean.DtCount;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UvCountMapper {

    @Select("select date_format(dt  ,'%m/%d') dt,uv_count ct from  ads_uv_count  where  DATEDIFF(CURDATE() ,dt) < #{days} ")
    public List<DtCount> getUvCountLastDays(int days);


}
