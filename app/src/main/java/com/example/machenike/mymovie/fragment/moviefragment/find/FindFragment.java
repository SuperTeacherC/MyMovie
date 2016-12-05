package com.example.machenike.mymovie.fragment.moviefragment.find;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.machenike.mymovie.R;
import com.example.machenike.mymovie.base.BaseFragment;
import com.example.machenike.mymovie.bean.FindBottomBean;
import com.example.machenike.mymovie.bean.FindCenterBean;
import com.example.machenike.mymovie.bean.FindTopBean;
import com.example.machenike.mymovie.utils.Constant;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.Bind;
import okhttp3.Call;

/**
 * Created by Machenike on 2016/11/30.
 */
public class FindFragment extends BaseFragment {
    @Bind(R.id.findrecyclerview)
    RecyclerView findrecyclerview;
    @Bind(R.id.gridview_find_center)
    GridView gridviewFindCenter;
    @Bind(R.id.ll_find_bottom)
    LinearLayout llFindBottom;
    @Bind(R.id.all_prize)
    TextView allPrize;
    private TextView mPrice_festivername;
    private TextView mTv_time;
    private ImageView mIv_moview_pic;
    private TextView mTv_prize_name;
    private TextView mTv_movie_name;

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
        getTopDataFromNet();

    }

    private void getTopDataFromNet() {
        OkHttpUtils.get()
                .url(Constant.findTop)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "FindTop联网失败 " + e);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        processData(response);
                        getCenterDataFromNet();
                        getBottomDataFromNet();

                    }
                });
    }

    private void getBottomDataFromNet() {
        OkHttpUtils.get()
                .url(Constant.findBottom)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "FindBottom联网失败 " + e);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        FindBottomBean findBottomBean = new Gson().fromJson(response, FindBottomBean.class);
                        Log.e("TAG", "0000000" + response);
                        List<FindBottomBean.DataBean> data = findBottomBean.getData();

                        if (data != null) {
                            for (int i = 0; i < data.size(); i++) {
                                View view = View.inflate(mContext, R.layout.item_find_bottom, null);
                                mPrice_festivername = (TextView) view.findViewById(R.id.price_festivername);
                                mTv_time = (TextView) view.findViewById(R.id.tv_time);
                                mIv_moview_pic = (ImageView) view.findViewById(R.id.iv_moview_pic);
                                mTv_prize_name = (TextView) view.findViewById(R.id.tv_prize_name);
                                mTv_movie_name = (TextView) view.findViewById(R.id.tv_movie_name);


                                mPrice_festivername.setText(data.get(i).getFestivalName());
                                mTv_time.setText(data.get(i).getHeldDate());
                                mTv_prize_name.setText(data.get(i).getPrizeName());
                                mTv_movie_name.setText(data.get(i).getMovieName());
                                Glide.with(mContext).load(data.get(i).getImg()).error(R.drawable.xiaofei).placeholder(R.drawable.xiaofei).into(mIv_moview_pic);

                                llFindBottom.addView(view);
                            }

                        }
                    }
                });
    }

    private void getCenterDataFromNet() {
        OkHttpUtils.get()
                .url(Constant.findCenter)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "FindCenter联网失败 " + e);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        FindCenterBean findCenterBean = new Gson().fromJson(response, FindCenterBean.class);
                        List<FindCenterBean.DataBean> data = findCenterBean.getData();
                        gridviewFindCenter.setAdapter(new GridViewAdapter(mContext, data));
                    }
                });
    }

    private void processData(String response) {
        FindTopBean findTopBean = new Gson().fromJson(response, FindTopBean.class);
        List<FindTopBean.DataBean> data = findTopBean.getData();
        findrecyclerview.setAdapter(new FindMovieAdapter(getContext(), data));
        findrecyclerview.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

    }

    @Override
    protected int getLayoutID() {
        return R.layout.find_fragment;
    }

}
