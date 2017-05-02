package com.sn.snmall.shoppingcart.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sn.snmall.app.MyApplication;
import com.sn.snmall.home.bean.GoodsBean;
import com.sn.snmall.utils.CacheUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * date:2017/5/2
 * author:易宸锋(dell)
 * function:商品购物车数据操作类,取:从本地拿到数据放到内存 存:把内存中的数据存到本地,增删改查
 * 一下的代码,很简单,就是把本地数据拿到我们的内存中
 * 1.创建CartStorage工具类
 * 2.单例模式
 * 3.上下文
 * 4.SparseArray
 * 5.从本地取数据
 * 6.把取到的数据添加到内存中,也就是添加到SparseArray.
 */

public class CartStorage {

    //A.使用单例模式,其构造方法里有上下文
    private static CartStorage instance;
    private Context mContext;

    //A.得到购物车的实例
    public static CartStorage getInstance(){
        if (instance  == null){
            instance= new CartStorage(MyApplication.getmContext());
        }
        return instance;
    }

    //B.相当于一个HashMap,也是存放一个键,一个值,但是他Android特有的类,而非java,他的效率也比HashMap更高,如果Android开发中有用到HashMap,根据条件可以改为SparseArray
    private final SparseArray<GoodsBean> mgoodsBeanSparseArray;


    //A.构造方法,进行数据的初始化操作,上下文是从myapplication中得到(没有,就创建)
    public CartStorage(Context context) {
        mContext = context;
        //创建读取之前存储数据的容器,大小100就够了
        mgoodsBeanSparseArray = new SparseArray<>(100);

        //C.SparseArray装好数据
        ListToSpareseArray();

    }

    public static final String JSON_CART ="json_cart";

    //取
    public List<GoodsBean> getAllData() {
        ArrayList<GoodsBean> goodsBeanList = new ArrayList<>();
        //D.从本地中获取
        String json = CacheUtils.getString(mContext, JSON_CART);
        //判断数据是否为null
        if(!TextUtils.isEmpty(json)){
            //使用Gson把Sting类型转换为List列表,记得关联Gson
            goodsBeanList =new Gson().fromJson(json, new TypeToken<List<GoodsBean>>(){}.getType());
        }
        return goodsBeanList;
    }

    //C.读取本地的数据加入到SparseArrayg中,存
    private void ListToSpareseArray() {
        //先把本地数据放入list集合中
        List<GoodsBean> goodsBeanList = getAllData();
        //把列表数据转换为SparseArray;
        for(int x =0 ; x <goodsBeanList.size() ;x++){
            //取出对应的位置的goodsBean数据
            GoodsBean goodsBean = goodsBeanList.get(x);
            //存入到sparseArray集合中  参数:1.int型,是商品的ID   2.要存的数据
            mgoodsBeanSparseArray.put( Integer.parseInt(goodsBean.getProduct_id()), goodsBean);
        }

    }



}
