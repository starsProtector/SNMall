package com.sn.snmall.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sn.snmall.R;
import com.sn.snmall.home.bean.ResultBeanData;
import com.sn.snmall.utils.Constants;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnLoadImageListener;

import java.util.ArrayList;
import java.util.List;

/**
 * date:2017/4/25
 * author:易宸锋(dell)
 * function:就是HomeFragment下的RecyclerView适配器,在这里实现主页面丰富多彩的效果
 * 1.搭建加载不同类型的Item的RecyclerView适配器框架
 * 2.实现第一个item,广告条效果
 */
public class HomeFragmentAdapter extends RecyclerView.Adapter{
    //A.上下文
    private Context mContext;
    //A.RecycleView加载的Bean数据
    private ResultBeanData.ResultBean mResultBean;
    //A.方面加载布局的填充器对象
    private final LayoutInflater mLayoutInflater;

    //A.构造方法得到外界的上下文及加载数据容器
    public HomeFragmentAdapter(Context context, ResultBeanData.ResultBean resultBean) {
        mContext = context;
        mResultBean =resultBean;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    //A.实际相当于创建getView里的创建item一样(这里创建的是Holder)   参数:viewType,代表了当前的类型,由getItemViewType的返回值决定
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //B.判断返回类型,根据返回类型,决定创建什么样的Holder
        if(viewType == BANNER){
            //返回创建的BannerViewHolder
            return new BannerViewHolder(mContext,mLayoutInflater.inflate(R.layout.banner_viewpager,null));
        }

        return null;
    }

    //A.相当getView中的绑定数据
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //B.更加Item的位置,调用getItemViewType,知道给此item绑定什么类型的ViewHolder
        if (getItemViewType(position) == BANNER){
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            //给BannerViewHolder设置数据
            bannerViewHolder.setData(mResultBean.getBanner_info());
        }

    }

    //A.广告条幅类型(int数从0开始,数组从0开始,程序界:万物从0开始)
    private static final int BANNER =0;
    //A.频道类型
    private static final int CHANNEL = 1;
    //A.活动类型
    private static final int ACT = 2;
    //A.秒杀类型
    private static final int SECKILL = 3;
    //A.推荐类型
    private static final int RECOMMEND = 4;
    //A.热卖类型
    private static final int HOT = 5;

    //A.当前类型
    private int currentType =BANNER;

    //A.得到类型  position就代表条目的位置
    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case BANNER:
                currentType =BANNER;
                break;
            case CHANNEL:
                currentType =CHANNEL;
                break;
            case ACT:
                currentType =ACT;
                break;
            case SECKILL:
                currentType =SECKILL;
                break;
            case RECOMMEND:
                currentType =RECOMMEND;
                break;
            case HOT:
                currentType =HOT;
                break;
        }
        return currentType;
    }

    /**
     * A.决定RecyclerView有几条Item
     * 提示:我们没做一个ViewHolder,这里的数据必须变化,才能有效果
     * @return
     */
    @Override
    public int getItemCount() {
        return 1;
    }

    /**
     * B.主界面的广告条,Banner,各种类型的VIewHolder都要集成到RecyclerView的VIewHolder
     */
    private class BannerViewHolder extends RecyclerView.ViewHolder {
        private final Banner mBanner;
        private Context mContext;

        public BannerViewHolder(Context context, View inflate) {
            super(inflate);
            mContext = context;
            mBanner = (Banner) inflate.findViewById(R.id.banner);
        }

        //从外界拿到所需的数据,设置给Banner控件
        public void setData(List<ResultBeanData.ResultBean.BannerInfoBean> banner_info) {
            //给Banner控件设置加载图片的数据,如果仅仅上午加载网址,就要设置监听,在其内部使用图片开源框架加载图片Glide
            ArrayList<String> imagersUrl = new ArrayList<>();
            //从BannerInfoBean容器中拿到图片的网址,在放到集合里
            for(int x=0; x<banner_info.size(); x++){
                String imagerUrl = banner_info.get(x).getImage();
//                Log.d("YDS",imagerUrl);
                imagersUrl.add(imagerUrl);
            }
            //
            mBanner.setImages(imagersUrl, new OnLoadImageListener() {
                @Override
                public void OnLoadImage(ImageView view, Object url) {
                    Glide.with(mContext)
                            .load(Constants.BASE_URL_IMAGE+url)
                            .into(view);
                }
            });
        }
    }
}
