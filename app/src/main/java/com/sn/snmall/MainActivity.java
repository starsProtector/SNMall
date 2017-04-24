package com.sn.snmall;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 1.完成XML布局
 * 2.搭建ButterKnife环境,安装studio插件(提示:ButterKnife7.0,如果用8.0,记着还要对工作空间的build文件进行配置)
 * 3.创建BaseFragment
 * 4.定义各个子页面的Fragment
 * 5.初始化Fragment,将其中放入容器中
 * 6.设置RadioGroup的监听
 * 7.得到对应的Fragment,进行切换
 * 8.对Fragment进行优化,解决切换Fragment,会再次创建Fragment,重走生命周期方法的问题,以及横竖屏切换Fragment内容重叠问题
 */
public class MainActivity extends AppCompatActivity {

    @Bind(R.id.rg_main)
    RadioGroup mRgMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //默认主界面一运行,就选中的第一个按钮
        mRgMain.check(R.id.rb_home);
    }


}
