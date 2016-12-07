package com.example.machenike.mymovie.fragment.moviefragment.wait;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.machenike.mymovie.R;
import com.example.machenike.mymovie.bean.WaitBottomBean;
import com.example.machenike.mymovie.utils.GetUrl;

import java.util.List;

/**
 * Created by Machenike on 2016/12/2.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter {
    private static final int TYPE_ONE = 1;
    private static final int TYPE_TWO = 2;
    private static final int TYPE_THREE = 3;

    public static final int FIRST_STICKY_VIEW = 4;
    public static final int HAS_STICKY_VIEW = 5;
    public static final int NONE_STICKY_VIEW = 6;
    private  WaitBottomBean waitBottomBean;
    private Context mContext;
    private  View view;
    private final List<WaitBottomBean.DataBean.ComingBean> coming;

    public RecyclerViewAdapter(Context context, WaitBottomBean waitBottomBean) {
        this.mContext = context;
        this.waitBottomBean = waitBottomBean;
        view = View.inflate(mContext, R.layout.wait_fragment, null);
        coming = waitBottomBean.getData().getComing();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ONE) {
//            return  new OneTypeViewHolder(mContext, View.inflate(mContext, R.layout.one_type_wait,null));
            return new OneTypeViewHolder(mContext, LayoutInflater.from(mContext).inflate(R.layout.one_type_wait,parent, false));
        } else if (viewType == TYPE_TWO) {
           // return new TwoTypeViewHolder(mContext, View.inflate(mContext, R.layout.two_tyoe_wait, null));
            return new TwoTypeViewHolder(mContext, LayoutInflater.from(mContext).inflate(R.layout.two_tyoe_wait, parent, false));
        } else {
           // return new ThreeTypeViewHolder(mContext, View.inflate(mContext, R.layout.three_tyoe_wait, null));
            return new ThreeTypeViewHolder(mContext, LayoutInflater.from(mContext).inflate(R.layout.item_three_type,parent, false));
        }
    }
    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_ONE;
        } else if (position == 1) {
            return TYPE_TWO;
        } else {
            return TYPE_THREE;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_ONE) {
            OneTypeViewHolder oneTypeViewHolder = (OneTypeViewHolder) holder;
            oneTypeViewHolder.setData(position);
            
        } else if (getItemViewType(position) == TYPE_TWO) {
            TwoTypeViewHolder twoTypeViewHolder = (TwoTypeViewHolder) holder;
            twoTypeViewHolder.setData(position);
        } else {
            ThreeTypeViewHolder threeTypeViewHolder = (ThreeTypeViewHolder) holder;
            threeTypeViewHolder.setData(coming,position);
            String path = GetUrl.getPath(coming.get(position-2).getImg(), mContext);

            Glide.with(mContext).load(path).error(R.drawable.xiaofei).placeholder(R.drawable.xiaofei).into(threeTypeViewHolder.mIv_hot_movies);
            threeTypeViewHolder.mTv_hot_name.setText(coming.get(position-2).getNm());
            threeTypeViewHolder.mTv_hot_number.setText(coming.get(position-2).getWish() + "");
            threeTypeViewHolder.mTv_hot_scm.setText(coming.get(position-2).getScm());
            threeTypeViewHolder.mTv_hot_act_name.setText(coming.get(position-2).getStar());
            threeTypeViewHolder.mtvTitileTime.setText(coming.get(position-2).getComingTitle());
            threeTypeViewHolder.isState(position-2);
           // threeTypeViewHolder. isTitleShow(position-2);

            //threeTypeViewHolder.itemView.setContentDescription(coming.get(position).getComingTitle());
        }
    }
    @Override
    public int getItemCount() {
        return waitBottomBean.getData().getComing().size();
    }

}
