package com.example.machenike.mymovie.fragment.communityfragment;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.example.machenike.mymovie.R;
import com.example.machenike.mymovie.base.BaseFragment;
import com.example.machenike.mymovie.bean.CommunityBean;
import com.example.machenike.mymovie.bean.CommunityTopBean;
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
    @Bind(R.id.tv_community_top)
    TextView tvCommunityTop;
    @Bind(R.id.community_kuaixun)
    ImageView communityKuaixun;
    @Bind(R.id.tv_community_kuaixun)
    TextView tvCommunityKuaixun;
    @Bind(R.id.community_shop)
    ImageView communityShop;
    @Bind(R.id.tv_community_shop)
    TextView tvCommunityShop;
    @Bind(R.id.community_piao)
    ImageView communityPiao;
    @Bind(R.id.tv_community_piao)
    TextView tvCommunityPiao;
    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.refresh)
    MaterialRefreshLayout refresh;
    private List<CommunityBean.DataBean.FeedsBean> feeds;
    int curpager=0;
    private CommunityAdapter communityAdapter;
    private boolean istrue;

    @Override
    protected void initData() {
        getTopFromNet();
        getDataFromNet();
        getRefresh();
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
                .url(Constant.community_start + curpager + Constant.community_end)
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
        CommunityBean communityBean = new Gson().fromJson(response, CommunityBean.class);
        feeds = communityBean.getData().getFeeds();
        communityAdapter = new CommunityAdapter(mContext, feeds);
        recyclerview.setAdapter(communityAdapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        istrue = communityBean.getData().getPaging().isHasMore();

    }

    public void getTopFromNet() {
        OkHttpUtils.get()
                .url(Constant.communityTop)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "getTopFromNet联网失败");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        CommunityTopBean communityTopBean = new Gson().fromJson(response, CommunityTopBean.class);

                        tvCommunityTop.setText(communityTopBean.getData().get(0).getTitle());
                        tvCommunityKuaixun.setText(communityTopBean.getData().get(1).getTitle());
                        tvCommunityShop.setText(communityTopBean.getData().get(2).getTitle());
                        tvCommunityPiao.setText(communityTopBean.getData().get(3).getTitle());

                        Glide.with(mContext).load(communityTopBean.getData().get(0).getImage().getUrl())
                                .error(R.drawable.xiaofei).placeholder(R.drawable.xiaofei).into(communityTop);
                        Glide.with(mContext).load(communityTopBean.getData().get(1).getImage().getUrl())
                                .error(R.drawable.xiaofei).placeholder(R.drawable.xiaofei).into(communityKuaixun);
                        Glide.with(mContext).load(communityTopBean.getData().get(2).getImage().getUrl())
                                .error(R.drawable.xiaofei).placeholder(R.drawable.xiaofei).into(communityShop);
                        Glide.with(mContext).load(communityTopBean.getData().get(3).getImage().getUrl())
                                .error(R.drawable.xiaofei).placeholder(R.drawable.xiaofei).into(communityPiao);
                    }
                });
    }


    public void getRefresh() {
        refresh.setLoadMore(true);
        refresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                getDataFromNet();
                refresh.finishRefresh();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (istrue) {
                            curpager += 10;
                            OkHttpUtils.get()
                                    .url(Constant.community_start + curpager + Constant.community_end)
                                    .build()
                                    .execute(new StringCallback() {
                                        @Override
                                        public void onError(Call call, Exception e, int id) {
                                            Log.e("TAG", "联网失败" + e);
                                        }

                                        @Override
                                        public void onResponse(String response, int id) {
                                            //     Log.e("TAG", response.toString());
                                            feeds.addAll(processDataMore(response));
                                            communityAdapter.notifyDataSetChanged();

                                        }
                                    });
                        } else {//没有更多页面
                            Toast.makeText(getContext(), "没有更多数据", Toast.LENGTH_SHORT).show();
                        }
                        refresh.finishRefresh();
                    }
                }, 2000);
                refresh.finishRefreshLoadMore();
            }
        });
    }

    private List<CommunityBean.DataBean.FeedsBean> processDataMore(String response) {
        Gson gson = new Gson();
        CommunityBean communityBean = gson.fromJson(response, CommunityBean.class);
        return   communityBean.getData().getFeeds();
    }

}
