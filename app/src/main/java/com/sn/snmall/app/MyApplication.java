package com.sn.snmall.app;

import android.app.Application;
import android.content.Context;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * date:2017/4/25
 * author:易宸锋(dell)
 * function:自定义的Application(在清单文件进行配置,注册及加权限),对使用的第三方框架进行初始化的地方
 * 下载okhttp工具类地址:https://github.com/hongyangAndroid/okhttputils
 */

public class MyApplication extends Application {
    //B.创建一个上下文对象,方便其他类调用
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化OKhttp工具类
        initOk();
        //B.应用一启动,就创建一个上下文对象
        mContext =this;
    }

    //B.获取上下文方法
    public static Context getmContext(){
        return mContext;
    }

    private void initOk() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                // .addInterceptor(new LoggerInterceptor("TAG"))
                //设置连接超时
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                //设置读取超时
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();
        //进行okhttp工具类初始化的操作
        OkHttpUtils.initClient(okHttpClient);
    }
}
