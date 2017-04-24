package com.sn.snmall.app;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;

import com.sn.snmall.R;
import com.sn.snmall.base.BaseFragment;
import com.sn.snmall.community.fragment.CommunityFragment;
import com.sn.snmall.home.fragment.HomeFragment;
import com.sn.snmall.shoppingcart.fragment.ShoppingCartFragment;
import com.sn.snmall.type.fragment.TypeFragment;
import com.sn.snmall.user.fragment.UserFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 1.完成XML布局
 * 2.搭建ButterKnife环境,安装studio插件(提示:ButterKnife7.0,如果用8.0,记着还要对工作空间的build文件进行配置)
 * 3.创建BaseFragment
 * 4.定义各个子页面的Fragment
 * 5.初始化Fragment,将其中放入容器中
 * 6.设置RadioGroup的监听
 * 7.得到对应的Fragment,进行切换
 * 8.对Fragment进行优化,解决切换Fragment,会再次创建Fragment,重走生命周期方法的问题,以及横竖屏切换Fragment,重走生命周期方法的问题
 */
public class MainActivity extends AppCompatActivity {

    @Bind(R.id.rg_main)
    RadioGroup mRgMain;
    private ArrayList<BaseFragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //初始化Fragment
        initFragment();
        //默认主界面一运行,就选中的第一个按钮
        mRgMain.check(R.id.rb_home);
        //默认加载home的Fragment即可
        RePlaceFG(fragments.get(0));
    }

    //初始化各个模块的Fragment,并装入容器中.
    private void initFragment() {
        fragments = new ArrayList<>();
        //添加到容器集合中,按照顺序进行添加
        fragments.add(new HomeFragment());
        fragments.add(new TypeFragment());
        fragments.add(new CommunityFragment());
        fragments.add(new ShoppingCartFragment());
        fragments.add(new UserFragment());
    }

    //选择fragment的对应编号(程序员的世界,万物从0开始)
    private int position;

    @OnClick({R.id.rb_home, R.id.rb_type, R.id.rb_community, R.id.rb_cart, R.id.rb_user})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rb_home:
                position = 0 ;
                mRgMain.check(R.id.rb_home);
                break;
            case R.id.rb_type:
                position = 1;
                mRgMain.check(R.id.rb_type);
                break;
            case R.id.rb_community:
                position = 2;
                mRgMain.check(R.id.rb_community);
                break;
            case R.id.rb_cart:
                position = 3;
                mRgMain.check(R.id.rb_cart);
                break;
            case R.id.rb_user:
                position = 4;
                mRgMain.check(R.id.rb_user);
                break;
            default:
                position = 0 ;
                mRgMain.check(R.id.rb_home);
                break;
        }
        BaseFragment baseFragment = fragments.get(position);
        //替换Fragment
        RePlaceFG(baseFragment);

    }

    private void RePlaceFG(BaseFragment baseFragment) {
        //获取fragment的经理,方便对fragment的管理
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        //通过Fragment经理,去开启事务对象
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        //通过事务对象,去把XML里的控件替换成Fragment
        fragmentTransaction.replace(R.id.framLayout,baseFragment);
        //通过事务对象提交
        fragmentTransaction.commit();
    }

    /*对Fragment进行优化,解决切换Fragment,会再次创建Fragment,重走生命周期方法的问题,,在项目中切换Fragment,一直都用的是replace
    * ,但是这样做有一个问题,每次切换fragment,都会重新实例化,重新加载fragment,官方文档解释说:replace()这个方法只是在上一个Fragment
    * 不需要时,采用的简便方法,正确的切换方法是add(),切换时hide,add()另一个fragment,再次切换时,只需hide当前的fragment,show显示另
    * 一个fragment,就能够做到多个Fragment切换时不重新实例化
    * */

}
