package com.sn.snmall.home.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.sn.snmall.home.bean.ResultBeanData;
import com.sn.snmall.utils.Constants;

import java.util.List;

/**
 * date:2017/4/27
 * author:易宸锋(dell)
 * function:这是主页活动的Adapter,主要给HomeFragmentAdapter下的内部类ACTviewHolder使用
 */
public class ACTAdapter extends PagerAdapter{
    private Context mContext;
    private List<ResultBeanData.ResultBean.ActInfoBean> mActInfoBeen;

    public ACTAdapter(Context context, List<ResultBeanData.ResultBean.ActInfoBean> act_info) {
        mContext =   context;
        mActInfoBeen =act_info;
    }

    //创建Item            ViewGroup container:理解VIewPager的化身
    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        //创建一个图片的对象,设置他内部元素拉伸填充
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        //用Glide框架加载数据到ImageView
        Glide.with(mContext)
                .load(Constants.BASE_URL_IMAGE+mActInfoBeen.get(position).getIcon_url())
                .into(imageView);
        //给图片设置点击事件
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "点击了" +position+"条,么么哒", Toast.LENGTH_SHORT).show();
            }
        });
        container.addView(imageView);
        return imageView;
    }

    //释放内存,销毁已经不用的Item
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);

    }

    //返回总条数
    @Override
    public int getCount() {
        return mActInfoBeen.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
