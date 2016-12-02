package com.example.machenike.mymovie.fragment.communityfragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.example.machenike.mymovie.R;
import com.example.machenike.mymovie.base.BaseFragment;
import com.example.machenike.mymovie.bean.CommunityBean;
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
public class CommunityFragment extends BaseFragment {
    @Bind(R.id.community_top)
    ImageView communityTop;
    @Bind(R.id.community_kuaixun)
    ImageView communityKuaixun;
    @Bind(R.id.community_shop)
    ImageView communityShop;
    @Bind(R.id.community_piaofang)
    ImageView communityPiaofang;
    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    private List<CommunityBean.DataBean.FeedsBean> feeds;

    @Override
    protected void initData() {
        getDataFromNet();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.community_fragment;
    }

    @Override
    protected void initView() {

    }

    public void getDataFromNet() {
        OkHttpUtils.get()
                .url(Constant.community)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG","联网失败");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        processData(response);
                    }
                });
    }

    private void processData(String response) {
        CommunityBean communityBean = new Gson().fromJson(response, CommunityBean.class);
        feeds = communityBean.getData().getFeeds();
        recyclerview.setAdapter(new CommunityAdapter(mContext,feeds));
        recyclerview.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));

    }
}
