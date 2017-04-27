package com.sn.snmall.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sn.snmall.R;
import com.sn.snmall.home.bean.ResultBeanData;
import com.sn.snmall.utils.Constants;

import java.util.List;

/**
 * date:2017/4/27
 * author:易宸锋(dell)
 * function:
 */
public class SeckillRecycleViewAdapter extends RecyclerView.Adapter <SeckillRecycleViewAdapter.ViewHolder>{
    private Context mContext;
    private List<ResultBeanData.ResultBean.SeckillInfoBean.ListBean> mSeckillInfoBean;

    //构造方法,初始化上下文对象及数据
    public SeckillRecycleViewAdapter(Context context, List<ResultBeanData.ResultBean.SeckillInfoBean.ListBean> list) {
        mContext =context;
        mSeckillInfoBean =list;
    }

    //创建VIewHolder的方法
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //创建布局
        View inflate = View.inflate(mContext, R.layout.item_seckill, null);
        //创建VIewHolder,把inflate放入其中
        return new ViewHolder(inflate);
    }

    //绑定数据的方法
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //根据位置得到对应的数据,在进行绑定
        ResultBeanData.ResultBean.SeckillInfoBean.ListBean listBean = mSeckillInfoBean.get(position);
        //使用GLide开源框架更加网址ImageVIew加载图片
        Glide.with(mContext)
                .load(Constants.BASE_URL_IMAGE+listBean.getFigure())
                .into(holder.iv_figure);
        //设置价格
        holder.tv_cover_price.setText(listBean.getCover_price());
        //设置降价文本
        holder.tv_origin_price.setText(listBean.getOrigin_price());

    }

    //确定总条数
    @Override
    public int getItemCount() {
        return mSeckillInfoBean.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private final ImageView iv_figure;
        private final TextView tv_cover_price;
        private final TextView tv_origin_price;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_figure = (ImageView) itemView.findViewById(R.id.iv_figure);
            tv_cover_price = (TextView) itemView.findViewById(R.id.tv_cover_price);
            tv_origin_price = (TextView) itemView.findViewById(R.id.tv_origin_price);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mOnSeckillRecycleView != null){
                        mOnSeckillRecycleView.OnItemClick(getLayoutPosition());
                    }
                }
            });
        }
    }

    //B.自定义的一个RecyclerView点击事件的接口
    public interface OnSeckillRecycleView{
        //当某条被点击时回调
        void OnItemClick(int position);
    }

    private OnSeckillRecycleView mOnSeckillRecycleView;

    //设置item的监听
    public void setOnSeckillRecycleView(OnSeckillRecycleView OnSeckillRecycleView){
        mOnSeckillRecycleView=OnSeckillRecycleView;
    }

    /*如果你给RecyclerView设置点击事件
    * 1.定义接口,接口中一个抽象方法,参数为int型
    * 2.设置接口的成员对象
    * 3.设置方法,参数就是接口类型,把从外界得到的接口类型参数设置给接口成员对象
    * 4.在holder定义点击事件,在点击事件内部,使用接口调抽象方法
    * 5.外界通过adapter对象,使用我们在adapter中定义的方法,即可完成我们的需求
    * 提示:怎么获取条目位置,通过getLayoutPosition方法.
    * */

}
