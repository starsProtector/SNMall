package com.sn.snmall.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.sn.snmall.R;

//要想把Toolbar取消掉,可以继承Activity,修改主题即可全屏显示该页面          .....Light.NoActionBar
public class WelcomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //两秒中进行主页面,使用Handler对象延时发送      参数: 1.Runnable()   2.毫秒值
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                jumpMainActivity();
            }
        },3000);
    }

    /**
     * 有启动页跳转到主页面
     */
    private void jumpMainActivity() {
        //启动主页面
        startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
        //结束Activity
        finish();
    }


}
