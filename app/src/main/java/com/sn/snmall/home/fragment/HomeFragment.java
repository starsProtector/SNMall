package com.sn.snmall.home.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.sn.snmall.R;
import com.sn.snmall.base.BaseFragment;
import com.sn.snmall.home.adapter.HomeFragmentAdapter;
import com.sn.snmall.home.bean.ResultBeanData;
import com.sn.snmall.utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

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
 * 7.使用RecyclerView控件,设置适配器与管理对象
 */

public class HomeFragment extends BaseFragment {

    private RecyclerView rvHome;
    private ImageButton ib_top;
    private TextView tv_search_home;
    private TextView tv_message_home;
    private ResultBeanData.ResultBean mResultBean;
    private HomeFragmentAdapter mHomeFragmentAdapter;

    @Override
    public View initView(LayoutInflater iflater, ViewGroup container, Bundle savedInstanceState) {
        //加载布局资源
        View view = View.inflate(mContext, R.layout.fragment_home, null);
        rvHome = (RecyclerView) view.findViewById(R.id.rv_home);
        ib_top = (ImageButton) view.findViewById(R.id.ib_top);
        tv_search_home = (TextView) view.findViewById(R.id.tv_search_home);
        tv_message_home = (TextView) view.findViewById(R.id.tv_message_home);
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
    public void initData() {
        //使用okhttp工具类get请求网络
        okhttpGetData();
    }

    //使用okhttp工具类get请求
    private void okhttpGetData() {
        //设置请求的网址
        String url = Constants.HOME_URL;
        OkHttpUtils
                .get()//设置请求网络的方式
                .url(url)//设置请求网址
                .build()//构建对象
                //执行请求操作
                .execute(new StringCallback() {
            //请求网络失败回调
            @Override
            public void onError(Call call, Exception e, int i) {
                Toast.makeText(mContext, "网络请求失败,么么哒", Toast.LENGTH_SHORT).show();
            }
            //请求网络成功回调,使用fastJson进行Json的解析
            @Override
            public void onResponse(String s, int i) {
                //测试是否请求网络成功,及线程是否允许在此更新UI,也就是判断这里是否是主线程
                Toast.makeText(mContext, "请求网络成功了,哈哈哈哈!", Toast.LENGTH_SHORT).show();
//                Log.d("YCF",s);
                //解析Json数据
                processData(s);
            }
        });

    }

    //Json解析数据的方法
    private void processData(String jsonData) {
        //使用fastJson解析数据,把整理好的数据放入指定的容器中    参数:1.json的数据   2.容器类中的字节码
        ResultBeanData resultBeanData = JSON.parseObject(jsonData, ResultBeanData.class);
        //得到容器中装数据的集合
        mResultBean = resultBeanData.getResult();
        //测试是否解析Json数据成功
        String name = mResultBean.getHot_info().get(0).getName();
//        Log.d("DS",name);

        //B.对mResultBean判断对象是否为空
        if(mResultBean != null ){
            //有数据,创建RecyclerView的适配器,    参数:1.上下文   2.数据
            mHomeFragmentAdapter = new HomeFragmentAdapter(mContext, mResultBean);
            //RecycleView设置适配器
            rvHome.setAdapter(mHomeFragmentAdapter);
            //提示:使用Re测一测设置布局管理者,决定RecyclerView的整体面貌.    参数:1.上下文   2.决定面貌为一行
            rvHome.setLayoutManager(new GridLayoutManager(mContext,1));

        }else{
            Toast.makeText(mContext, "没有数据,所以显示为null", Toast.LENGTH_SHORT).show();
        }

    }





}
