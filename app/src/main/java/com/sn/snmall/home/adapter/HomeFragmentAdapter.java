package com.sn.snmall.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.sn.snmall.R;
import com.sn.snmall.home.bean.ResultBeanData;
import com.sn.snmall.utils.Constants;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;
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
        }else if(viewType == CHANNEL){
            //C.返回创建的CHANNELViewHolder
            return new ChannelViewHolder(mContext,mLayoutInflater.inflate(R.layout.channel_item,null));
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
        }else if(getItemViewType(position) == CHANNEL){
            ChannelViewHolder channelViewHolder = (ChannelViewHolder) holder;
            //C.给频道的ViewHoler设置数据
            channelViewHolder.setData(mResultBean.getChannel_info());
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
        return 2;
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
            //给Banner控件设置加载图片的数据,如果是加载网址,就要设置监听,在其内部使用图片开源框架加载图片Glide
            ArrayList<String> imagersUrl = new ArrayList<>();
            //从BannerInfoBean容器中拿到图片的网址,在放到集合里
            for(int x=0; x<banner_info.size(); x++){
                String imagerUrl = banner_info.get(x).getImage();
                Log.d("YDS",imagerUrl);
                imagersUrl.add(imagerUrl);
            }

            //设置广告条循环时所用的小点
            mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            //设置图片   参数:1String类型的集合   2.加载图片后的回调监听
            mBanner.setImages(imagersUrl, new OnLoadImageListener() {
                @Override
                public void OnLoadImage(ImageView view, Object url) {
                    //联网请求图片,使用的是Glide根据网址获取图片,是ImageView与Glide转换的数据进行绑定
                    Glide.with(mContext)
                            .load(Constants.BASE_URL_IMAGE+url)
                            .into(view);
                }
            });
            //设置广告条轮播时,手风琴样式的切换
            mBanner.setBannerAnimation(Transformer.Accordion);
            //设置item的点击事件
            mBanner.setOnBannerClickListener(new OnBannerClickListener() {
                @Override
                public void OnBannerClick(int position) {
                    Toast.makeText(mContext, "Position 是" +position , Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    /**
     * C.从外界拿到数据,设置给频道
     */
    private class ChannelViewHolder extends RecyclerView.ViewHolder {
        private final GridView gc_Channel;
        private Context mContext;
        private ChannelAdapter mChannelAdapter;


        public ChannelViewHolder(Context context, View inflate) {
            super(inflate);
            mContext =context;
            gc_Channel = (GridView) inflate.findViewById(R.id.gv_channel);
        }

        public void setData(List<ResultBeanData.ResultBean.ChannelInfoBean> channel_info) {
            //得到数据,创建适配器对象
            mChannelAdapter = new ChannelAdapter(mContext, channel_info);
            //设置GridVIew的点击事件
            gc_Channel.setAdapter(mChannelAdapter);
            //设置GridView的点击事件
            gc_Channel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    Toast.makeText(mContext, "positon " +position, Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
