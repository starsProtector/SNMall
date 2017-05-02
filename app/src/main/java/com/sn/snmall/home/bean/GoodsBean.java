package com.sn.snmall.home.bean;

import java.io.Serializable;

/**
 * date:2017/2/3
 * author:易宸锋(dell)
 * function:商品详情的数据,因为要用intent传输数据,所以要序列化(真实开发可以用EventBus)
 */

public class GoodsBean implements Serializable {

    /**
     * cover_price : 159.00
     * figure : /1477984921265.jpg
     * name : 现货【一方尘寰】剑侠情缘三剑三七秀 干将莫邪 90橙武仿烧蓝复古对簪
     * product_id : 9356
     */
    //价格
    private String cover_price;
    //图片
    private String figure;
    //名称
    private String name;
    //商品ID
    private String product_id;

    //B.商品数量
    private int number=1;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCover_price() {
        return cover_price;
    }

    public void setCover_price(String cover_price) {
        this.cover_price = cover_price;
    }

    public String getFigure() {
        return figure;
    }

    public void setFigure(String figure) {
        this.figure = figure;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    //将数据打印出来
    @Override
    public String toString() {
        return "GoodsBean{" +
                "cover_price='" + cover_price + '\'' +
                ", figure='" + figure + '\'' +
                ", name='" + name + '\'' +
                ", product_id='" + product_id + '\'' +
                '}';
    }
}
