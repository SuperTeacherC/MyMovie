package com.example.machenike.mymovie.fragment.communityfragment.mall;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.machenike.mymovie.R;
import com.example.machenike.mymovie.activity.SearchActivity;
import com.example.machenike.mymovie.base.BaseActivity;
import com.example.machenike.mymovie.bean.ShopBannerBean;
import com.example.machenike.mymovie.bean.ShopItemBean;
import com.example.machenike.mymovie.utils.Constant;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.Bind;
import okhttp3.Call;

public class ShopActivity extends BaseActivity {


    @Bind(R.id.iv_shop_banner)
    ImageView ivShopBanner;
    @Bind(R.id.tv_back)
    ImageView tvBack;
    @Bind(R.id.tv_search)
    ImageView tvSearch;
    @Bind(R.id.gridview_channal)
    GridView gridviewChannal;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_dsc)
    TextView tvDsc;
    @Bind(R.id.tv_name2)
    TextView tvName2;
    @Bind(R.id.tv_dsc2)
    TextView tvDsc2;
    @Bind(R.id.iv_shop2)
    ImageView ivShop2;
    @Bind(R.id.tv_name3)
    TextView tvName3;
    @Bind(R.id.tv_dsc3)
    TextView tvDsc3;
    @Bind(R.id.iv_shop3)
    ImageView ivShop3;
    @Bind(R.id.gridview_item)
    GridView gridviewItem;
    int images []={R.drawable.a01,R.drawable.a02,R.drawable.a03,R.drawable.a04,R.drawable.a05,R.drawable.a06,
                   R.drawable.a07,R.drawable.a08,R.drawable.a09,R.drawable.a10,R.drawable.a01,};
    private ShopItemBean.DataBean data;
    private List<ShopItemBean.DataBean.ListBean> list;

    @Override
    protected void initListener() {

        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(ShopActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void initData() {
        getBannerfromNet();
        getDatafromNet();


        gridviewChannal.setAdapter(new ShopChannalAdapter(this, images));

    }

    private void getDatafromNet() {
    OkHttpUtils.get()
            .url(Constant.shopitem)
            .build()
            .execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    Log.e("TAG","ShopItem联网失败");
                }

                @Override
                public void onResponse(String response, int id) {
                    ShopItemBean shopItemBean = new Gson().fromJson(response, ShopItemBean.class);
                    data = shopItemBean.getData();
                    list = data.getList();

                    gridviewItem.setAdapter(new ShopItemAdapter(ShopActivity.this,list));
                }
            });

    }

    private void getBannerfromNet() {
        OkHttpUtils.get()
                .url(Constant.shopBanner)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "ShopBanner联网失败");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        ShopBannerBean shopBannerBean = new Gson().fromJson(response, ShopBannerBean.class);
                        List<ShopBannerBean.DataBean> data = shopBannerBean.getData();

                        Glide.with(ShopActivity.this).load(data.get(0).getImgUrl()).error(R.drawable.xiaofei).placeholder(R.drawable.xiaofei).into(ivShopBanner);
                    }
                });

    }

    @Override
    protected int initLayout() {
        return R.layout.activity_shop;
    }

}
