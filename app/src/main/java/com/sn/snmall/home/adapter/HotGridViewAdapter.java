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
 * date:2017/4/28
 * author:易宸锋(dell)
 * function:
 */
public class HotGridViewAdapter extends BaseAdapter{
    private Context mContext;
    private List<ResultBeanData.ResultBean.HotInfoBean> mHotInfoBeen;

    public HotGridViewAdapter(Context context, List<ResultBeanData.ResultBean.HotInfoBean> data) {
        mContext=context;
        mHotInfoBeen=data;
    }
    @Override
    public int getCount() {
        return mHotInfoBeen.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView=View.inflate(mContext, R.layout.item_hot_grid_view,null);
            viewHolder=new ViewHolder();
            viewHolder.iv_hot= (ImageView) convertView.findViewById(R.id.iv_hot);
            viewHolder.tv_name= (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tv_price= (TextView) convertView.findViewById(R.id.tv_price);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        //获取对应位置Item的数据
        ResultBeanData.ResultBean.HotInfoBean hotInfoBean = mHotInfoBeen.get(position);

        Glide.with(mContext)
                .load(Constants.BASE_URL_IMAGE+hotInfoBean.getFigure())
                .into(viewHolder.iv_hot);
        viewHolder.tv_name.setText(hotInfoBean.getName());
        viewHolder.tv_price.setText("$"+hotInfoBean.getCover_price());

        return convertView;
    }

    private static class ViewHolder{
        private ImageView iv_hot;
        private TextView tv_name;
        private TextView tv_price;
    }


}
