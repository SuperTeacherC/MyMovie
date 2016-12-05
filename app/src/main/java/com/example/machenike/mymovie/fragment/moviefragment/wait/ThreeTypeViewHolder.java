package com.example.machenike.mymovie.fragment.moviefragment.wait;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.machenike.mymovie.R;
import com.example.machenike.mymovie.bean.WaitBottomBean;
import com.example.machenike.mymovie.utils.Constant;
import com.example.machenike.mymovie.utils.GetUrl;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

import static android.view.View.VISIBLE;

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
    private LinearLayout ll_three;
    private WaitBottomBean waitBottomBean;
    private WaitBottomBean.DataBean data;
    private List<WaitBottomBean.DataBean.ComingBean> coming;

    public ThreeTypeViewHolder(Context mContext, View inflate) {
        super(inflate);
        this.mContext = mContext;
        ll_three = (LinearLayout) inflate.findViewById(R.id.ll_three);

    }

    public void setData(int position) {
        OkHttpUtils.get().url(Constant.waitBottom).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("TAG","waitBottom联网失败"+e);
            }

            @Override
            public void onResponse(String response, int id) {
                processData(response);
            }
        });




    }

    private void processData(String response) {


        waitBottomBean = new Gson().fromJson(response, WaitBottomBean.class);
        data = waitBottomBean.getData();
        coming = data.getComing();
        if (coming != null && coming.size() > 0) {
            for (int i = 0; i < coming.size(); i++) {
                View view = LayoutInflater.from(mContext).inflate(R.layout.item_three_type, ll_three, false);
                mIv_hot_movies = (ImageView) view.findViewById(R.id.iv_hot_movies);
                mHot_item_all = (LinearLayout) view.findViewById(R.id.hot_item_all);
                mMovie_info = (LinearLayout) view.findViewById(R.id.movie_info);
                mTv_hot_name = (TextView) view.findViewById(R.id.tv_hot_name);
                mLl_fen = (LinearLayout) view.findViewById(R.id.ll_fen);
                mTv_hot_vip_fen = (TextView) view.findViewById(R.id.tv_hot_vip_fen);
                mTv_hot_number = (TextView) view.findViewById(R.id.tv_hot_number);
                mTv_hot_scm = (TextView) view.findViewById(R.id.tv_hot_scm);
                mTv_hot_act_name = (TextView) view.findViewById(R.id.tv_hot_act_name);
                mTv_hot_reply = (TextView) view.findViewById(R.id.tv_hot_reply);
                mTv_hot_yushou = (TextView) view.findViewById(R.id.tv_hot_yushou);
                mHot_topone = (LinearLayout) view.findViewById(R.id.hot_topone);
                mtvTitileTime = (TextView) view.findViewById(R.id.tv_titileTime);
                mtvDiangyingfen = (TextView) view.findViewById(R.id.tv_dianying_fen);
                mtvDianying = (TextView) view.findViewById(R.id.tv_dianying);
                mllDianying = (LinearLayout) view.findViewById(R.id.ll_dianying);
                mLLxiangkan = (LinearLayout) view.findViewById(R.id.ll_xiangkan);

                String path = GetUrl.getPath(coming.get(i).getImg(), mContext);

                Glide.with(mContext).load(path) .error(R.drawable.xiaofei).placeholder(R.drawable.xiaofei).into(mIv_hot_movies);

                mTv_hot_name.setText(coming.get(i).getNm());
                mTv_hot_number.setText(coming.get(i).getWish()+"");
                mTv_hot_scm.setText(coming.get(i).getScm());
                mTv_hot_act_name.setText(coming.get(i).getStar());
                mtvTitileTime.setText(coming.get(i).getComingTitle());
                    isState(i);
                     isTitleShow(i);
                ll_three.addView(view);

            }

        }
    }

    private void isState(int i) {
        if(coming.get(i).getMk() == 0) {//是否点映 ==0 为非点映
           mllDianying.setVisibility(View.GONE);
            if(coming.get(i).getProScore() ==0) {//是否专业 ==0 为非专业

                mLLxiangkan.setVisibility(View.VISIBLE);//想看栏
                mLl_fen.setVisibility(View.GONE);//专业栏

                mllDianying.setVisibility(View.GONE);//点映栏
                mTv_hot_number.setText(coming.get(i).getWish() + "");

                mTv_hot_yushou.setVisibility(View.GONE);//预售

                mTv_hot_reply.setVisibility(View.VISIBLE); //想看
            }else{
                mLLxiangkan.setVisibility(View.GONE);//想看栏
                mLl_fen.setVisibility(View.VISIBLE);//专业栏

                mllDianying.setVisibility(View.GONE);//点映栏
                mTv_hot_vip_fen.setText(coming.get(i).getProScore() + "");

                mTv_hot_yushou.setVisibility(View.VISIBLE);//预售
                mTv_hot_reply.setVisibility(View.GONE); //想看
            }
        }else{

            mLLxiangkan.setVisibility(View.GONE);//想看栏
            mLl_fen.setVisibility(View.GONE);//专业栏

            mllDianying.setVisibility(View.VISIBLE);//点映栏
            mtvDiangyingfen.setText(coming.get(i).getMk() + "");

            mTv_hot_yushou.setVisibility(View.VISIBLE);//预售
            mTv_hot_reply.setVisibility(View.GONE); //想看
        }
    }

    public void isTitleShow(int i ){
        if(i>0) {
            boolean equals = (coming.get(i).getComingTitle().toString()).equals(coming.get(i - 1).getComingTitle().toString());
            if(equals) {
                mtvTitileTime.setVisibility(View.GONE);
            }else{
                mtvTitileTime.setVisibility(VISIBLE);
            }
        }

    }
}
