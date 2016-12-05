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
import com.example.machenike.mymovie.bean.WaitTopBean;
import com.example.machenike.mymovie.utils.Constant;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

/**
 * Created by Machenike on 2016/12/3.
 */
public class OneTypeViewHolder extends RecyclerView.ViewHolder {
    private final Context mContext;

    private LinearLayout llone;
    private List<WaitTopBean.DataBean> data;

    public OneTypeViewHolder(Context mContext, View inflate) {
        super(inflate);
        this.mContext = mContext;
//        View view = View.inflate(mContext, R.layout.item_one_type, null);
//        view = LayoutInflater.from(mContext).inflate(R.layout.item_one_type, (ViewGroup) inflate, false);
//        ivMovie = (ImageView) view.findViewById(R.id.iv_movie);
//        tvDsc = (TextView) view.findViewById(R.id.tv_dsc);
//        ivPlay= (ImageView) view.findViewById(R.id.iv_play);
//        tvName = (TextView) view.findViewById(R.id.tv_name);
//        llone = (LinearLayout) inflate.findViewById(R.id.ll_one);
        llone = (LinearLayout) inflate.findViewById(R.id.ll_one);
        Log.e("TAG","data1"+data);
//      if(data !=null && data.size()>0) {
//          Log.e("TAG","data2"+data);
//        for (int i = 0;i<data.size();i++) {
//            Log.e("TAG","view"+view);
//            llone.addView(view);
//        }
//      }
    }

    public void setData(final int position) {
        OkHttpUtils.get().url(Constant.waitTop)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "联网失败");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        processData(response);


                    }
                });
    }

    private void processData(String response) {
        WaitTopBean waitTopBean = new Gson().fromJson(response, WaitTopBean.class);
        data = waitTopBean.getData();
        ImageView ivMovie;
        ImageView ivPlay;
        TextView tvDsc;
        TextView tvName;

        if(data !=null && data.size()>0) {
            Log.e("TAG","data2"+data);
            for (int i = 0;i<data.size();i++) {
                View view = LayoutInflater.from(mContext).inflate(R.layout.item_one_type, llone, false);
                ivMovie = (ImageView) view.findViewById(R.id.iv_movie);
                tvDsc = (TextView) view.findViewById(R.id.tv_dsc);
                ivPlay= (ImageView) view.findViewById(R.id.iv_play);
                tvName = (TextView) view.findViewById(R.id.tv_name);

                Log.e("TAG","view"+view);
                Glide.with(mContext).load(data.get(i).getImg()).into(ivMovie);
                tvDsc.setText(data.get(i).getName());
                tvName.setText(data.get(i).getMovieName());
                llone.addView(view);

            }
        }
    }
}
