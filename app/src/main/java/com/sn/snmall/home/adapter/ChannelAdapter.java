package com.sn.snmall.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sn.snmall.R;
import com.sn.snmall.home.bean.ResultBeanData;
import com.sn.snmall.utils.Constants;

import java.util.List;

/**
 * date:2017/4/26
 * author:易宸锋(dell)
 * function:这是主页频道的adapter,主要是个HomeFragment下内部类ChannelVIewHolder使用
 */
public class ChannelAdapter extends BaseAdapter {
    private Context mContext;
    private List<ResultBeanData.ResultBean.ChannelInfoBean> mChannelInfoBeen;

    //从外界得到上下文数据及要加载的数据
    public ChannelAdapter(Context context, List<ResultBeanData.ResultBean.ChannelInfoBean> channel_info) {
        mContext = context;
        mChannelInfoBeen = channel_info;
    }

    @Override
    public int getCount() {
        return mChannelInfoBeen.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (convertView == null) {
            //使用复用参数,listView的优化
            convertView = View.inflate(mContext, R.layout.item_channel, null);
            viewHolder = new ViewHolder();
            viewHolder.iv_icon = (ImageView) convertView.findViewById(R.id.iv_channel);
            viewHolder.tv_title = (TextView) convertView.findViewById(R.id.tv_channel);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //拿到对应itme的数据
        ResultBeanData.ResultBean.ChannelInfoBean channelInfoBean = mChannelInfoBeen.get(position);
        //使用Gilded开源框架,让ImageVIew控件可以根据图片网址直接加载出图片
        Glide.with(mContext)
                .load(Constants.BASE_URL_IMAGE+channelInfoBean.getImage())
                .into(viewHolder.iv_icon);
        //文本控件加载数据
        viewHolder.tv_title.setText(channelInfoBean.getChannel_name());
        return convertView;
    }


    static class ViewHolder {
        ImageView iv_icon;
        TextView tv_title;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
}
