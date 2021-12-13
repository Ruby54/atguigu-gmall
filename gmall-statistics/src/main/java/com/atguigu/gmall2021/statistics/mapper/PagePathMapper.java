package com.atguigu.gmall2021.statistics.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @program: gmall
 * @description:
 * @author: Zhao Yi
 * @create: 2021-12-11 14:23
 */
public interface PagePathMapper {

    @Select(" SELECT  source,target , path_count  value FROM  ads_page_path " +
            " WHERE     dt =#{dt} and recent_days= #{days}     " )
    public List<Map> getPagePathCount(@Param("days") int days, @Param("dt")String dt);
}
