package com.sn.snmall.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * date:2017/5/2
 * author:易宸锋(dell)
 * function:开源框架
 */

public class CacheUtils {

    //得到保存的String类型数据
    public static String  getString(Context context ,String key){
        //参数:  1.文件名称  2.文件类型
        SharedPreferences yds = context.getSharedPreferences("yds", Context.MODE_PRIVATE);
        return yds.getString(key,"");
    }

    //保存String类型数据,参数:上下文 保存键 3.保存String类型数据
    public static void saveString(Context  context ,String key ,String value){
        SharedPreferences yds = context.getSharedPreferences("yds", Context.MODE_PRIVATE);
        yds.edit().putString(key,value).commit();
    }

}
