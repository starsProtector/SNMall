package com.sn.snmall.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.sn.snmall.R;
import com.sn.snmall.home.bean.GoodsBean;
import com.sn.snmall.utils.Constants;

//展示商品详情的类,其XML布局最复杂
public class GoodsInfoActivity extends AppCompatActivity implements View.OnClickListener {
    //使用工具进行的初始化
    private ImageButton ibGoodInfoBack;
    private ImageButton ibGoodInfoMore;
    private ImageView ivGoodInfoImage;
    private TextView tvGoodInfoName;
    private TextView tvGoodInfoDesc;
    private TextView tvGoodInfoPrice;
    private TextView tvGoodInfoStore;
    private TextView tvGoodInfoStyle;
    private WebView wbGoodInfoMore;
    private LinearLayout llGoodsRoot;
    private TextView tvGoodInfoCallcenter;
    private TextView tvGoodInfoCollection;
    private TextView tvGoodInfoCart;
    private Button btnGoodInfoAddcart;

    //更多布局控件的实例化
    private TextView tv_more_share;
    private TextView tv_more_search;
    private TextView tv_more_home;
    private Button btn_more;
    private LinearLayout ll_root;
    //装数据的容器
    private GoodsBean mGoodsBean;

    private void findViews() {
        ibGoodInfoBack = (ImageButton) findViewById(R.id.ib_good_info_back);
        ibGoodInfoMore = (ImageButton) findViewById(R.id.ib_good_info_more);
        ivGoodInfoImage = (ImageView) findViewById(R.id.iv_good_info_image);
        tvGoodInfoName = (TextView) findViewById(R.id.tv_good_info_name);
        tvGoodInfoDesc = (TextView) findViewById(R.id.tv_good_info_desc);
        tvGoodInfoPrice = (TextView) findViewById(R.id.tv_good_info_price);
        tvGoodInfoStore = (TextView) findViewById(R.id.tv_good_info_store);
        tvGoodInfoStyle = (TextView) findViewById(R.id.tv_good_info_style);
        wbGoodInfoMore = (WebView) findViewById(R.id.wb_good_info_more);
        llGoodsRoot = (LinearLayout) findViewById(R.id.ll_goods_root);

        tvGoodInfoCallcenter = (TextView) findViewById(R.id.tv_good_info_callcenter);
        tvGoodInfoCollection = (TextView) findViewById(R.id.tv_good_info_collection);
        tvGoodInfoCart = (TextView) findViewById(R.id.tv_good_info_cart);

        btnGoodInfoAddcart = (Button) findViewById(R.id.btn_good_info_addcart);

        tv_more_share = (TextView) findViewById(R.id.tv_more_share);
        tv_more_search = (TextView) findViewById(R.id.tv_more_search);
        tv_more_home = (TextView) findViewById(R.id.tv_more_home);
        btn_more = (Button) findViewById(R.id.btn_more);

        ll_root = (LinearLayout) findViewById(R.id.ll_root);

        //点击事件
        ibGoodInfoBack.setOnClickListener(this);
        ibGoodInfoMore.setOnClickListener(this);
        btnGoodInfoAddcart.setOnClickListener(this);

        tvGoodInfoCallcenter.setOnClickListener(this);
        tvGoodInfoCollection.setOnClickListener(this);
        tvGoodInfoCart.setOnClickListener(this);
        //设置更多布局栏里控件的点击事件
        tv_more_share.setOnClickListener(this);
        tv_more_search.setOnClickListener(this);
        tv_more_home.setOnClickListener(this);

    }

    private static final String GOODS_BEAN = "goodsBean";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);
        //A.进行控件的初始化
        findViews();
        //B.接收外界传过来的序列化数据
        mGoodsBean = (GoodsBean) getIntent().getSerializableExtra(GOODS_BEAN);
        //B.判断传过来的数据是否为null
        if (mGoodsBean != null) {
            Log.d("YDS",mGoodsBean.toString());
            //C.把得到的数据设置到UI界面上(为了使代码阅读性更好,设置UI的逻辑抽取到方法中)
            setDataForView(mGoodsBean);

        }

    }

    //C.把得到的数据设置到UI界面上
    private void setDataForView(GoodsBean goodsBean) {
        //设置图片
        Glide.with(this)
                .load(Constants.BASE_URL_IMAGE + goodsBean.getFigure())
                .into(ivGoodInfoImage);
        //设置文本
        tvGoodInfoName.setText(goodsBean.getName());
        //设置价格
        tvGoodInfoPrice.setText("$" + goodsBean.getCover_price());
        //根据产品号加载对应的网页
        setWebViewData(goodsBean.getProduct_id());

    }

    //C.根据产品号加载对应的网页
    private void setWebViewData(String product_id) {
        if (product_id != null) {
            //使用webView加载一个网址
            wbGoodInfoMore.loadUrl("http://www.baidu.com");
            //实现游览网页,在自身APP内进行游览
            wbGoodInfoMore.setWebViewClient(new WebViewClient() {
                /*                @Override
                                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                                    //此方法是在API21以上放有效,所以进行判断,用户手机系统是否大于21.
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                        view.loadUrl(request.getUrl().toString());
                                    }
                                    return true;
                                }*/
                //此方法已经过时,但是可以在低版本的手机执行
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    //返回值是 true 的时候控制去 WebView 打开，为 false 调用系统,浏览器或第三方浏览器
                    view.loadUrl(url);
                    return true;
                }
            });
            //设置webView支持JavaScript,首先得到webView的设置对象,用settings进行设置
            WebSettings settings = wbGoodInfoMore.getSettings();
            settings.setJavaScriptEnabled(true);
/*            //支持双击页面变大变小
            settings.setUseWideViewPort(true);
            //优先使用缓存
            settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            //允许网页缩放
            settings.setSupportZoom(true);*/

        }

    }

    @Override
    public void onClick(View v) {
        //返回的按钮,关闭商品详情页
        if (v == ibGoodInfoBack) {
            finish();
        } else if (v == ibGoodInfoMore) {
            Toast.makeText(GoodsInfoActivity.this, "更多", Toast.LENGTH_SHORT).show();
            ll_root.setVisibility(View.VISIBLE);
        } else if (v == btnGoodInfoAddcart) {
            Toast.makeText(this, "添加到购物车", Toast.LENGTH_SHORT).show();
        } else if (v == tvGoodInfoCallcenter) {
            Toast.makeText(GoodsInfoActivity.this, "联系客服", Toast.LENGTH_SHORT).show();
        } else if (v == tvGoodInfoCollection) {
            Toast.makeText(this, "收藏", Toast.LENGTH_SHORT).show();
        } else if (v == tvGoodInfoCart) {
            Toast.makeText(GoodsInfoActivity.this, "购物车", Toast.LENGTH_SHORT).show();
        } else if (v == tv_more_share) {
            Toast.makeText(GoodsInfoActivity.this, "分享", Toast.LENGTH_SHORT).show();
        } else if (v == tv_more_search) {
            Toast.makeText(GoodsInfoActivity.this, "搜索", Toast.LENGTH_SHORT).show();
        } else if (v == tv_more_home) {
            Toast.makeText(GoodsInfoActivity.this, "首页", Toast.LENGTH_SHORT).show();
        }
    }

}
