package com.example.machenike.mymovie.fragment.cinemafragment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.example.machenike.mymovie.R;
import com.example.machenike.mymovie.base.BaseFragment;
import com.example.machenike.mymovie.bean.CinemaBean;
import com.example.machenike.mymovie.bean.HotBannerBean;
import com.example.machenike.mymovie.citypicker.CityPickerActivity;
import com.example.machenike.mymovie.utils.Constant;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import okhttp3.Call;

/**
 * Created by Machenike on 2016/11/30.
 */
public class CinemaFragment extends BaseFragment {

    @Bind(R.id.tv_city)
    TextView tvCity;
    @Bind(R.id.cinema_search)
    ImageView cinemaSearch;
    @Bind(R.id.cinema_select)
    ImageView cinemaSelect;
    @Bind(R.id.tv_hot_reply)
    TextView tvHotReply;
    @Bind(R.id.rl_titleShow)
    RelativeLayout rlTitleShow;
    @Bind(R.id.cinema_listview)
    ListView cinemaListview;
    @Bind(R.id.refresh)
    MaterialRefreshLayout refresh;
    private CinemaBean cinemaBean;
    private CinemaBean.DataBean data;
    private List<CinemaBean.DataBean.东城区Bean> beans;
    private Banner banner;
    public List<String> imagesUrl;
    private static final int CITY = 0;
    @Override
    protected void initData() {
        View headbanner = View.inflate(mContext, R.layout.cinema_banner_headview, null);
        View headView = LayoutInflater.from(mContext).inflate(R.layout.cinema_headview, cinemaListview, false);
        banner = (Banner) headbanner.findViewById(R.id.banner);
        cinemaListview.addHeaderView(headbanner);
        cinemaListview.addHeaderView(headView);
        getDataFromNet();
        getRefresh();
        showTitle();
        initListener();
    }

    private void initListener() {
        tvCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(getActivity(), CityPickerActivity.class);
                startActivityForResult(intent,CITY);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==CITY && resultCode ==CityPickerActivity.RESULT_OK) {
            String picked_city = data.getStringExtra("picked_city");
            tvCity.setText(picked_city);
        }
    }

    public  void showTitle(){
        cinemaListview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(firstVisibleItem >0) {
                    rlTitleShow.setVisibility(View.VISIBLE);
                }
                else{
                    rlTitleShow.setVisibility(View.GONE);
                }
            }
        });
    }
    private void getRefresh() {
        refresh.setLoadMore(false);
        refresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                getDataFromNet();
                refresh.finishRefresh();
            }
        });
    }

    @Override
    protected int getLayoutID() {
        return R.layout.cinema_fragment;
    }

    @Override
    protected void initView() {

    }

    public void getDataFromNet() {
        OkHttpUtils.get()
                .url(Constant.cinema)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(mContext, "联网失败", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        cinemaBean = new Gson().fromJson(response, CinemaBean.class);
                        beans = cinemaBean.getData().get东城区();
                        cinemaListview.setAdapter(new MyCinemaListViewAdapter(mContext, beans));
                    }
                });
        OkHttpUtils.get()
                .url(Constant.hotbanner)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        HotBannerBean hotBannerBean = gson.fromJson(response, HotBannerBean.class);
                        final List<HotBannerBean.DataBean> data = hotBannerBean.getData();
                        imagesUrl = new ArrayList<String>();
                        for (int i = 0; i < data.size(); i++) {
                            String url = data.get(i).getImgUrl();
                            imagesUrl.add(url);
                        }
                        banner.setImages(imagesUrl).setImageLoader(new GlideImageLoader()).start();
                    }
                });
    }


    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }

    }


}
