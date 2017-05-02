package com.sn.snmall.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.sn.snmall.R;
import com.sn.snmall.app.GoodsInfoActivity;
import com.sn.snmall.home.bean.GoodsBean;
import com.sn.snmall.home.bean.ResultBeanData;
import com.sn.snmall.utils.Constants;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.listener.OnLoadImageListener;
import com.zhy.magicviewpager.transformer.ScaleInTransformer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * date:2017/4/25
 * author:易宸锋(dell)
 * function:就是HomeFragment下的RecyclerView适配器,在这里实现主页面丰富多彩的效果
 * 1.搭建加载不同类型的Item的RecyclerView适配器框架
 * 2.实现第一个item,广告条效果
 */
public class HomeFragmentAdapter extends RecyclerView.Adapter {
    //A.广告条幅类型(int数从0开始,数组从0开始,程序界:wanwucning万物从零开始)
    private static final int BANNER = 0;
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
    private int currentType = BANNER;

    //A.方便加载布局
    private final LayoutInflater mLayoutInflater;
    //A.上下文
    private Context mContext;
    //A.RecycleView加载的Bean的数据容器
    private ResultBeanData.ResultBean mResultBean;

    //A.构造方法得到外界的上下文及加载数据容器
    public HomeFragmentAdapter(Context context, ResultBeanData.ResultBean resultBean) {
        mContext = context;
        mResultBean = resultBean;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    //A.相当于创建getVIew里创建ViewHolder,    参数viewType:当前类型,由getItemViewType的return返回值决定
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //B.判断返回类型
        if (viewType == BANNER) {
            //返回创建的BannerViewHolder
            return new BannerViewHolder(mContext, mLayoutInflater.inflate(R.layout.banner_viewpager, null));
        } else if (viewType == CHANNEL) {
            //C.返回创建的ChannelViewHolder
            return new ChannelViewHolder(mContext, mLayoutInflater.inflate(R.layout.channel_item, null));
        } else if (viewType == ACT) {
            //D.返回创建的ACTViewHolder
            return new ACTViewHolder(mContext, mLayoutInflater.inflate(R.layout.act_item, null));
        } else if (viewType == SECKILL) {
            //E.返回创建的SeckillViewHolder
            return new SeckillViewHolder(mContext, mLayoutInflater.inflate(R.layout.seckill_item, null));
        }  else if (viewType ==RECOMMEND) {
            //F.返回创建的RecommendViewHolder
            return new RecommendViewHolder(mContext, mLayoutInflater.inflate(R.layout.rcommend_item, null));
        }  else if (viewType == HOT) {
            //G.返回创建的HotViewHolder
            return new HotViewHolder(mContext, mLayoutInflater.inflate(R.layout.hot_item, null));
        }
        return null;
    }

    //A.相当于getVIew中的绑定数据
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //B.根据Item位置,调用getItemViewType,知道该给此item绑定什么类型的ViewHolder
        if (getItemViewType(position) == BANNER) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            //给BannerViewHolder设置数据
            bannerViewHolder.setData(mResultBean.getBanner_info());
        } else if (getItemViewType(position) == CHANNEL) {
            ChannelViewHolder channelViewHolder = (ChannelViewHolder) holder;
            //给频道的ViewHolder设置数据
            channelViewHolder.setData(mResultBean.getChannel_info());
        } else if (getItemViewType(position) == ACT) {
            ACTViewHolder actViewHolder = (ACTViewHolder) holder;
            //给滑动的ViewHolder设置数据
            actViewHolder.setData(mResultBean.getAct_info());
        }else if (getItemViewType(position) == SECKILL) {
            SeckillViewHolder seckillViewHolder = (SeckillViewHolder) holder;
            //给秒杀的ViewHolder设置数据
            seckillViewHolder.setData(mResultBean.getSeckill_info());
        }else if (getItemViewType(position) == RECOMMEND) {
            RecommendViewHolder recommendViewHolder = (RecommendViewHolder) holder;
            //给推荐的ViewHolder设置数据
            recommendViewHolder.setData(mResultBean.getRecommend_info());
        }else if (getItemViewType(position) == HOT) {
            HotViewHolder hotViewHolder = (HotViewHolder) holder;
            //给推荐的ViewHolder设置数据
            hotViewHolder.setData(mResultBean.getHot_info());
        }
    }

    //A.得到类型
    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case BANNER:
                currentType = BANNER;
                break;
            case CHANNEL:
                currentType = CHANNEL;
                break;
            case ACT:
                currentType = ACT;
                break;
            case SECKILL:
                currentType = SECKILL;
                break;
            case RECOMMEND:
                currentType = RECOMMEND;
                break;
            case HOT:
                currentType = HOT;
                break;
        }
        return currentType;
    }

    //A.设置item的条数
    @Override
    public int getItemCount() {
        //开发慢慢的写,从1开始
        return 6;
    }

    //B.主界面的广告条,Banner,各种类型的ViewHolder都要集成RecycleView的ViewHolder
    class BannerViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private Banner mBanner;

        //构造方法,以便从外界得到上下文,和XML布局资源,同时初始化Banner控件
        public BannerViewHolder(Context context, View itemView) {
            super(itemView);
            mContext = context;
            mBanner = (Banner) itemView.findViewById(R.id.banner);
        }

        //从外界拿到所需数据,设置给Banner控件
        public void setData(List<ResultBeanData.ResultBean.BannerInfoBean> banner_info) {
            //给Banner控件设置加载图片的数据,如果仅仅是加载网址,就要设置监听,在其内部使用图片开源框架加载出图片
            List<String> imagersUrl = new ArrayList<>();
            //从BannerInfoBean容器中拿到图片的网址,再放入ArrayList集合中
            for (int x = 0; x < banner_info.size(); x++) {
                String imagerUrl = banner_info.get(x).getImage();
                Log.d("YDS", imagerUrl);
                imagersUrl.add(imagerUrl);
            }
            //设置广告条循环时所用到的小点.
            mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            //参数:1.String类型的集合    2.加载图片后的回调监听
            mBanner.setImages(imagersUrl, new OnLoadImageListener() {
                @Override
                public void OnLoadImage(ImageView view, Object url) {
                    //联网请求图片,使用的是Glide根据网址获取到图片,使ImageView与Glide转换的数据进行绑定
                    Glide.with(mContext)
                            .load(Constants.BASE_URL_IMAGE + url)
                            .into(view);
                }
            });
            //设置广告条轮播时就是手风琴的效果
            mBanner.setBannerAnimation(Transformer.Accordion);
            //设置item的点击事件
            mBanner.setOnBannerClickListener(new OnBannerClickListener() {
                @Override
                public void OnBannerClick(int position) {
                    Toast.makeText(mContext, "Position==" + position, Toast.LENGTH_SHORT).show();
                    //根据点击事件跳转到商品详情页,因为好多地方都要用到此逻辑,所以抽取此方法
//                    startGoodsInfoActivity(goodsBean);
                }
            });
        }
    }

    //c.主页的频道,GridView
    private class ChannelViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private GridView gc_Channel;
        private ChannelAdapter mAdapter;

        public ChannelViewHolder(Context context, View itemView) {
            super(itemView);
            mContext = context;
            gc_Channel = (GridView) itemView.findViewById(R.id.gv_channel);

        }

        public void setData(List<ResultBeanData.ResultBean.ChannelInfoBean> channel_info) {
            //得到数据,创建适配器对象
            mAdapter = new ChannelAdapter(mContext, channel_info);
            //设置给GridView的适配器(注意:要有效果的话,记得把getItemCount()该为2即可)
            gc_Channel.setAdapter(mAdapter);
            //设置GridView的点击事件
            gc_Channel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    Toast.makeText(mContext, "position" + position, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    //D.主页的滑动,ViewPager
    private class ACTViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private ViewPager mAct_viewpager;

        public ACTViewHolder(Context context, View itemView) {
            super(itemView);
            mContext = context;
            mAct_viewpager = (ViewPager) itemView.findViewById(R.id.act_viewpager);
            //给ViewPager设置间距
            mAct_viewpager.setPageMargin(40);
            mAct_viewpager.setOffscreenPageLimit(3);//>=3
            //setPageTransformer 决定动画效果
            mAct_viewpager.setPageTransformer(true, new
                    ScaleInTransformer());
        }

        public void setData(List<ResultBeanData.ResultBean.ActInfoBean> data) {
            //有数据,设置VIewPager的适配器,(注意:要有效果的话,记得把getItemCount()该为3即可)
            ACTAdapter actAdapter = new ACTAdapter(mContext, data);
            mAct_viewpager.setAdapter(actAdapter);
        }
    }



    //E.主页的秒杀,GridView
    private class SeckillViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_time_seckill ;
        private TextView tv_more_seckill;
        private RecyclerView rv_seckill;
        private Context mContext;
        //E2.倒计时的时间,从服务器那拿两个值,进行相减得到倒计时的真实数值.这里定义了个临时变量
        private long dt=0;
        Handler handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                dt=dt-1000;
                //E2.把拿到的秒值时间数据转换为小时,分,秒的形式
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                String time = simpleDateFormat.format(new Date(dt));
                //E2.把倒计时显示到UI上
                tv_time_seckill.setText(time);
                //E2.在不断发送消息前先移除一下,减少OOM
                handler.removeMessages(0);
                handler.sendEmptyMessageDelayed(0,1000);
                //E2.经过我们的仔细观察可以看到,时间为0时,依然再减,所以要进行判断
                if(dt <=0 ){
                    //把所有消息移除
                    handler.removeCallbacksAndMessages(null);
                }
            }
        };

        public SeckillViewHolder(Context context, View itemView) {
            super(itemView);
            tv_time_seckill = (TextView) itemView.findViewById(R.id.tv_time_seckill);
            tv_more_seckill = (TextView) itemView.findViewById(R.id.tv_more_seckill);
            rv_seckill = (RecyclerView) itemView.findViewById(R.id.rv_seckilll);
            mContext = context;
        }

        public void setData(final ResultBeanData.ResultBean.SeckillInfoBean data) {
            //得到数据了,设置数据:文本和RecycleView的数据,
            SeckillRecycleViewAdapter seckillRecycleViewAdapter = new SeckillRecycleViewAdapter(mContext, data.getList());
            rv_seckill.setAdapter(seckillRecycleViewAdapter);
            //设置布局管理器,参数:  1.上下文    2.设置方向(LinearLayoutManager.HORIZONTAL水平方向)   3.是不是倒序,false代表不是
            //提示:要有效果的话,记得把getItemCount()该为4即可
            rv_seckill.setLayoutManager(new LinearLayoutManager(mContext , LinearLayoutManager.HORIZONTAL,false));
            //设置item的点击事件(注意:RecycleView没有设置点击事件的方法,这个使用的是自己封装的方法)
            seckillRecycleViewAdapter.setOnSeckillRecycleView(new SeckillRecycleViewAdapter.OnSeckillRecycleView() {
                @Override
                public void OnItemClick(int position) {
//                    Toast.makeText(mContext, "秒杀="+getLayoutPosition(), Toast.LENGTH_SHORT).show();
                    //F.拿到对应位置的大数据的容器
                    ResultBeanData.ResultBean.SeckillInfoBean.ListBean listBean = data.getList().get(position);
                    //F.创建单独存放商品详情数据的容器
                    GoodsBean goodsBean = new GoodsBean();
                    //F.把容器中的数据放入到GoodsBean数据里.
                    goodsBean.setCover_price(listBean.getCover_price());
                    goodsBean.setFigure(listBean.getFigure());
                    goodsBean.setName(listBean.getName());
                    goodsBean.setProduct_id(listBean.getProduct_id());
                    //跳转到商品详情页
                    startGoodsInfoActivity(goodsBean);
                }
            }) ;



            //E2.计算秒杀倒计时,应为从bean集合里,拿到的时间数据不是int型,所以用Integer进行转换(其逻辑代码就是java基础的内容)
            dt=Integer.valueOf(data.getEnd_time()) -Integer.valueOf(data.getStart_time());
            //E2.建立handler实现定时器的效果,循环发送消息,以便能够使时间不断减一
            handler.sendEmptyMessageDelayed(0,1000);

        }
    }

    //F.主页的推荐,GridView
    private class RecommendViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private TextView tv_more_recommend;
        private GridView gv_recommend;
        //进行要用到对象的初始化
        public RecommendViewHolder(Context context, View itemView) {
            super(itemView);
            mContext=context;
            tv_more_recommend= (TextView) itemView.findViewById(R.id.tv_more_recommend);
            gv_recommend= (GridView) itemView.findViewById(R.id.gv_recommend);
        }

        public void setData(final List<ResultBeanData.ResultBean.RecommendInfoBean> data) {
            //有数据,创建适配器类对象
            RecommendGridViewAdapter recommendGridViewAdapter = new RecommendGridViewAdapter(mContext,data);
            //为GridVIew设置适配器(注意:要有效果的话,记得把getItemCount()该为5即可)
            gv_recommend.setAdapter(recommendGridViewAdapter);
            //设置GridView的点击事件
            gv_recommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                    Toast.makeText(mContext, "点击了"+position, Toast.LENGTH_SHORT).show();
                    //F.拿到对应位置的大数据的容器
                    ResultBeanData.ResultBean.RecommendInfoBean recommendInfoBean = data.get(position);
                    //F.创建单独存放商品详情数据的容器
                    GoodsBean goodsBean = new GoodsBean();
                    //F.把容器中的数据放入到GoodsBean数据里.
                    goodsBean.setCover_price(recommendInfoBean.getCover_price());
                    goodsBean.setFigure(recommendInfoBean.getFigure());
                    goodsBean.setName(recommendInfoBean.getName());
                    goodsBean.setProduct_id(recommendInfoBean.getProduct_id());
                    //跳转到商品详情页
                    startGoodsInfoActivity(goodsBean);
                }
            });
        }
    }

    //G.主页的热卖.GridView
    private class HotViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private TextView tv_more_hot;
        private GridView gv_hot;

        public HotViewHolder(Context context, View itemView) {
            super(itemView);
            mContext=context;
            tv_more_hot= (TextView) itemView.findViewById(R.id.tv_more_hot);
            gv_hot= (GridView) itemView.findViewById(R.id.gv_hot);
        }

        public void setData(final List<ResultBeanData.ResultBean.HotInfoBean> data) {
            //有数据,创建适配器对象
            HotGridViewAdapter hotGridViewAdapter = new HotGridViewAdapter(mContext, data);
            gv_hot.setAdapter(hotGridViewAdapter);
            //设置GridView的点击事件
            gv_hot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                    Toast.makeText(mContext, "点击了"+position, Toast.LENGTH_SHORT).show();

                    //F.拿到对应位置的大数据的容器
                    ResultBeanData.ResultBean.HotInfoBean hotInfoBean = data.get(position);
                    //F.创建单独存放商品详情数据的容器
                    GoodsBean goodsBean = new GoodsBean();
                    //F.把容器中的数据放入到GoodsBean数据里.
                    goodsBean.setCover_price(hotInfoBean.getCover_price());
                    goodsBean.setFigure(hotInfoBean.getFigure());
                    goodsBean.setName(hotInfoBean.getName());
                    goodsBean.setProduct_id(hotInfoBean.getProduct_id());

                    //F.跳转到商品详情页
                    startGoodsInfoActivity(goodsBean);
                }
            });
        }
    }

    private static final String  GOODS_BEAN= "goodsBean";
    //跳转到商品详情页
    private void startGoodsInfoActivity(GoodsBean goodsBean) {
        Intent intent = new Intent(mContext, GoodsInfoActivity.class);
        intent.putExtra(GOODS_BEAN,goodsBean);
        mContext.startActivity(intent);
    }

}