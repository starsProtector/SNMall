package com.sn.snmall.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * date:2017/4/24
 * author:易宸锋(dell)
 * function:app包下的Fragment,安卓系统3.0以后可以使用(如果有2.3的手机使用程序,会直接找不到类,崩溃)
 * v4包下的fragment,可以向下兼容(如果有2.3的手机使用程序,依然能正常运行)
 * 但是:现在百分之99的手机系统,都在3.0的安卓系统以上.所以你们出去做开发时,也就没必要是V4包下的fragment.
 * v4包下的Fragment存在bug
 * Base抽取原则:就是子类共有的东西,抽取到父类,子类都有,但实现效果不同的东西,抽取到父类为抽象方法.
 */

public abstract class BaseFragment extends Fragment {

    //上下文
    public Context mContext;

    //该方法在Fragment创建时回调,得到上下文
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    //让Fragment加载XML布局资源,因为每个子类Fragment显示的界面都不同,所以返回一个抽象方法
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return initView(inflater, container, savedInstanceState);
    }

    /**
     *加载Fragment布局资源,子类必须要覆写的抽象方法
     */
    public abstract View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) ;

    //Fragment与Activity被创建是回调,进行数据的初始化
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 联网获取数据,进行数据的初始化,展示数据,子类必须要覆写抽象方法
     */
    public abstract void initData();


}
