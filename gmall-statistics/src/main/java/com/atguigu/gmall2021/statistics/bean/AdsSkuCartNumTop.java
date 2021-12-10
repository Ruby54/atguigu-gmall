package com.atguigu.gmall2021.statistics.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: gmall
 * @description:
 * @author: Zhao Yi
 * @create: 2021-12-10 11:46
 */
@Data
@NoArgsConstructor
public class AdsSkuCartNumTop {
    /**
     * '统计日期'
     */
    private String dt;

    /**
     * 一级分类id
     */
    private String category1_id;

    /**
     * 一级分类名称
     */
    private String category1_name;

    /**
     * 二级分类id
     */
    private String category2_id;

    /**
     * 二分类名称
     */
    private String category2_name;

    /**
     * 三级分类id
     */
    private String  category3_id;

    /**
     * 三级分类名称
     */
    private String category3_name;


    /**
     * 商品id
     */
    private String sku_id;

    /**
     * 商品名称
     */
    private String sku_name;

    /**
     * 购物车中商品数量
     */
    private int cart_num;

    /**
     * 排名
     */
    private int rk;
}
