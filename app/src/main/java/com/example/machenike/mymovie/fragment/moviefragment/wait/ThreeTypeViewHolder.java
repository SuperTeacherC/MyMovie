package com.example.machenike.mymovie.fragment.moviefragment.wait;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.machenike.mymovie.R;
import com.example.machenike.mymovie.bean.WaitBottomBean;

import java.util.List;

/**
 * Created by Machenike on 2016/12/3.
 */
public class ThreeTypeViewHolder extends RecyclerView.ViewHolder {

    ImageView mIv_hot_movies;
    LinearLayout mHot_item_all;
    LinearLayout mMovie_info;
    TextView mTv_hot_name;
    LinearLayout mLl_fen;
    TextView mTv_hot_vip_fen;
    TextView mTv_hot_number;
    TextView mTv_hot_scm;
    TextView mTv_hot_act_name;
    TextView mTv_hot_reply;
    TextView mTv_hot_yushou;
    LinearLayout mHot_topone;
    TextView mtvTitileTime;
    TextView mtvDianying;
    TextView mtvDiangyingfen;
    LinearLayout mllDianying;
    LinearLayout mLLxiangkan;
    private final Context mContext;
    private List<WaitBottomBean.DataBean.ComingBean> data
            ;

    public ThreeTypeViewHolder(Context mContext, View inflate) {
        super(inflate);
        this.mContext = mContext;

        mIv_hot_movies = (ImageView) inflate.findViewById(R.id.iv_hot_movies);
        mHot_item_all = (LinearLayout) inflate.findViewById(R.id.hot_item_all);
        mMovie_info = (LinearLayout) inflate.findViewById(R.id.movie_info);
        mTv_hot_name = (TextView) inflate.findViewById(R.id.tv_hot_name);
        mLl_fen = (LinearLayout) inflate.findViewById(R.id.ll_fen);
        mTv_hot_vip_fen = (TextView) inflate.findViewById(R.id.tv_hot_vip_fen);
        mTv_hot_number = (TextView) inflate.findViewById(R.id.tv_hot_number);
        mTv_hot_scm = (TextView) inflate.findViewById(R.id.tv_hot_scm);
        mTv_hot_act_name = (TextView) inflate.findViewById(R.id.tv_hot_act_name);
        mTv_hot_reply = (TextView) inflate.findViewById(R.id.tv_hot_reply);
        mTv_hot_yushou = (TextView) inflate.findViewById(R.id.tv_hot_yushou);
        mHot_topone = (LinearLayout) inflate.findViewById(R.id.hot_topone);
        mtvTitileTime = (TextView) inflate.findViewById(R.id.tv_titileTime);
        mtvDiangyingfen = (TextView) inflate.findViewById(R.id.tv_dianying_fen);
        mtvDianying = (TextView) inflate.findViewById(R.id.tv_dianying);
        mllDianying = (LinearLayout) inflate.findViewById(R.id.ll_dianying);
        mLLxiangkan = (LinearLayout) inflate.findViewById(R.id.ll_xiangkan);


    }

    public void setData(List<WaitBottomBean.DataBean.ComingBean> data, int position) {
        this.data = data;
        isTitleShow(position-2);
    }

    public void isState(int i) {
//        itemView.setContentDescription(data.get(i).getComingTitle());
        if(data.get(i).getMk() == 0) {//是否点映 ==0 为非点映
           mllDianying.setVisibility(View.GONE);
            if(data.get(i).getProScore() ==0) {//是否专业 ==0 为非专业

                mLLxiangkan.setVisibility(View.VISIBLE);//想看栏
                mLl_fen.setVisibility(View.GONE);//专业栏

                mllDianying.setVisibility(View.GONE);//点映栏
                mTv_hot_number.setText(data.get(i).getWish() + "");

                mTv_hot_yushou.setVisibility(View.GONE);//预售

                mTv_hot_reply.setVisibility(View.VISIBLE); //想看
            }else{
                mLLxiangkan.setVisibility(View.GONE);//想看栏
                mLl_fen.setVisibility(View.VISIBLE);//专业栏

                mllDianying.setVisibility(View.GONE);//点映栏
                mTv_hot_vip_fen.setText(data.get(i).getProScore() + "");

                mTv_hot_yushou.setVisibility(View.VISIBLE);//预售
                mTv_hot_reply.setVisibility(View.GONE); //想看
            }
        }else{

            mLLxiangkan.setVisibility(View.GONE);//想看栏
            mLl_fen.setVisibility(View.GONE);//专业栏

            mllDianying.setVisibility(View.VISIBLE);//点映栏
            mtvDiangyingfen.setText(data.get(i).getMk() + "");

            mTv_hot_yushou.setVisibility(View.VISIBLE);//预售
            mTv_hot_reply.setVisibility(View.GONE); //想看
        }
    }

    public void isTitleShow(int i ){
        itemView.setContentDescription(data.get(i).getComingTitle());
       itemView.setTag(View.VISIBLE);
        mtvTitileTime.setVisibility(View.VISIBLE);
        if(i>=1) {
            boolean equals = (data.get(i).getComingTitle().toString()).equals(data.get(i - 1).getComingTitle().toString());
            if (equals) {
                mtvTitileTime.setVisibility(View.GONE);
                itemView.setTag(View.GONE);
            }else{
                mtvTitileTime.setVisibility(View.VISIBLE);
                itemView.setTag(View.VISIBLE);
              //  itemView.setContentDescription(data.get(i).getComingTitle());
               // itemView.setTag(View.VISIBLE);
            }
        }
    }

}
