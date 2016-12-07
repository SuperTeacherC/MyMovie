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
import com.example.machenike.mymovie.bean.WaitCenterBean;
import com.example.machenike.mymovie.utils.Constant;
import com.example.machenike.mymovie.utils.GetUrl;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

/**
 * Created by Machenike on 2016/12/3.
 */
public class TwoTypeViewHolder extends RecyclerView.ViewHolder {
    private final Context mContext;
    ImageView ivMovie;
    ImageView ivPlay;
    ImageView ivLike;
    TextView tvTwoName;
    TextView tvNumber;
    TextView tvTime;
    TextView tv_sticky_header_view;
    private LinearLayout ll_two;
    private List<WaitCenterBean.DataBean.ComingBean> coming;

    public TwoTypeViewHolder(Context mContext, View inflate) {
        super(inflate);
        this.mContext = mContext;
        inflate.setTag(View.VISIBLE);
        inflate.setContentDescription("最近最受期待");

        ll_two = (LinearLayout) inflate.findViewById(R.id.ll_two);
        tv_sticky_header_view = (TextView) inflate.findViewById(R.id.tv_sticky_header_view);
        tv_sticky_header_view.setText("最近最受期待");
    }

    public void setData(final int position) {
        OkHttpUtils.get().url(Constant.waitCenter).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("TAG", "联网失败"+e);
            }

            @Override
            public void onResponse(String response, int id) {
                processData(response);
            }
        });
    }

    private void processData(String response) {

        WaitCenterBean waitCenterBean = new Gson().fromJson(response,WaitCenterBean.class);
        coming = waitCenterBean.getData().getComing();

        if(coming != null && coming.size()>0) {
            for (int i = 0; i < coming.size(); i++) {
                View view = LayoutInflater.from(mContext).inflate(R.layout.item_two_type, ll_two, false);
                ivMovie = (ImageView) view.findViewById(R.id.iv_movie);
                ivPlay = (ImageView) view.findViewById(R.id.iv_play);
                ivLike = (ImageView) view.findViewById(R.id.iv_like);
                tvTwoName = (TextView) view.findViewById(R.id.tv_two_name);
                tvNumber = (TextView) view.findViewById(R.id.tv_number);
                tvTime = (TextView) view.findViewById(R.id.tv_time);


               //*************截取字符串***************
                String path = GetUrl.getPath(coming.get(i).getImg(), mContext);
                //*******************截取字符串end******************
                Glide.with(mContext).load(path).error(R.drawable.xiaofei).placeholder(R.drawable.xiaofei).into(ivMovie);
                tvNumber.setText(coming.get(i).getWish()+"");
                tvTwoName.setText(coming.get(i).getNm());
                String time = coming.get(i).getComingTitle();
                String settime = time.substring(0,time.indexOf(" "));
                tvTime.setText(settime);
                isShow(i);
                ll_two.addView(view);
            }
        }

    }

    private void isShow(int i) {
        if(coming.get(i).getDur() == 0) {
            ivPlay.setVisibility(View.GONE);
        }else{
            ivPlay.setVisibility(View.VISIBLE);
        }
    }


}
