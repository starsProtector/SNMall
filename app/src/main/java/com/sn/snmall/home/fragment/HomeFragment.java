package com.sn.snmall.home.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.sn.snmall.R;
import com.sn.snmall.base.BaseFragment;

/**
 * date:2017/4/24
 * author:易宸锋(dell)
 * function:主页面的Fragment
 * 1.完成XML布局
 * 2.控件的初始化,设置点击事件
 * 3.在build文件下,关联okhttp工具类,在Application,清单文件文件下进行配置
 * 4.使用okhttp工具类,完成网络请求数据
 * 5.配置一个常量类Constants,复制存放URL网址
 * 6.使用fastjson解析数据,把数据装入ResultBeanData这个Bean类中
 *
 */

public class HomeFragment extends BaseFragment {

    private RecyclerView rvHome;
    private ImageButton ib_top;
    private TextView tv_search_home;
    private TextView tv_message_home;

    @Override
    public View initView(LayoutInflater iflater, ViewGroup container, Bundle savedInstanceState) {
        //加载布局资源
        View view = View.inflate(mContext, R.layout.fragment_home, null);
        rvHome = (RecyclerView) view.findViewById(R.id.rv_home);
        ib_top = (ImageButton) view.findViewById(R.id.ib_top);
        tv_search_home = (TextView) view.findViewById(R.id.tv_search_home);
        tv_message_home=(TextView) view.findViewById(R.id.tv_message_home);
        //给按钮设置点击事件
        initListener();

        return view;
    }

    //给控件设置点击事件
    private void initListener() {
        //置顶的监听
        ib_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //回到顶部
                rvHome.scrollToPosition(0);
            }
        });
        //搜索的监听
        tv_search_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "搜索", Toast.LENGTH_SHORT).show();
            }
        });
        //消息的监听
        tv_message_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "进入消息中心", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void initData(){

    }


}
