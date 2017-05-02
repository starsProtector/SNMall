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
public class RecommendGridViewAdapter extends BaseAdapter{
    private Context mContext;
    private List<ResultBeanData.ResultBean.RecommendInfoBean> mRecommendInfoBeen;

    public RecommendGridViewAdapter(Context context, List<ResultBeanData.ResultBean.RecommendInfoBean> recommend_info) {
        mContext =context ;
        mRecommendInfoBeen =recommend_info;
    }


    @Override
    public int getCount() {
        return mRecommendInfoBeen.size();
    }

    @Override
    public View getView(int position, View convertview, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(convertview == null){
            convertview=View.inflate(mContext , R.layout.item_recommend_grid_view,null);
            viewHolder= new ViewHolder();
            viewHolder.iv_recommend = (ImageView) convertview.findViewById(R.id.iv_recommend);
            viewHolder.tv_name = (TextView) convertview.findViewById(R.id.tv_name);
            viewHolder.tv_price = (TextView) convertview.findViewById(R.id.tv_price);
            convertview.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertview.getTag();
        }
        ResultBeanData.ResultBean.RecommendInfoBean recommendInfoBean = mRecommendInfoBeen.get(position);
        //使用图片框架去处理
        Glide.with(mContext)
                .load(Constants.BASE_URL_IMAGE + recommendInfoBean.getFigure())
                .into(viewHolder.iv_recommend );
        viewHolder.tv_name.setText(recommendInfoBean.getName());
        viewHolder.tv_price.setText("人民币:" +recommendInfoBean.getCover_price());

        return convertview;
    }

    class ViewHolder{
        ImageView iv_recommend;
        TextView tv_name;
        TextView tv_price;
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
