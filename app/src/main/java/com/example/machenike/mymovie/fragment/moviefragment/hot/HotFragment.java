package com.example.machenike.mymovie.fragment.moviefragment.hot;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.example.machenike.mymovie.R;
import com.example.machenike.mymovie.base.BaseFragment;
import com.example.machenike.mymovie.bean.HotBannerBean;
import com.example.machenike.mymovie.bean.HotBean;
import com.example.machenike.mymovie.utils.Constant;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerClickListener;
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
public class HotFragment extends BaseFragment {
    @Bind(R.id.tv_search)
    TextView tvSearch;
    @Bind(R.id.hot_search)
    LinearLayout hotSearch;
    @Bind(R.id.lv_hot)
    ListView lvHot;
    @Bind(R.id.refresh)
    MaterialRefreshLayout refresh;
    private List<HotBean.DataBean.MoviesBean> movies;
    private HotBean hotBean;
    private Context mContext;
    public List<String> imagesUrl;
    private Banner banner;
    private int totalPager;

    //默认第一页
    private  int curPage =1;
    @Override
    protected void initData() {
        View headview = View.inflate(getContext(), R.layout.hot_headview, null);
        banner = (Banner) headview.findViewById(R.id.banner);
        lvHot.addHeaderView(headview);
        getDataformNet();
        initListener();
        materrefresh();
    }
    private boolean isLoadMore = true;
    private void materrefresh() {
        refresh.setLoadMore(isLoadMore);
        refresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getDataformNet();
                        refresh.finishRefresh();
                    }
                }, 2000);
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (curPage < totalPager) {
                            curPage += 1;
                            OkHttpUtils.get()
                                    .url(Constant.hotUlr + "&offset=" + curPage + "&limit=" + 12)
                                    .build()
                                    .execute(new StringCallback() {
                                        @Override
                                        public void onError(Call call, Exception e, int id) {
                                            Log.e("TAG", "联网失败" + e);
                                        }

                                        @Override
                                        public void onResponse(String response, int id) {
                                            //     Log.e("TAG", response.toString());
                                            processData(response);
                                            getBannerFromNet();
                                            lvHot.setAdapter(new HotAdapter(getContext(), movies));

                                        }
                                    });
                        } else {//没有更多页面
                            refresh.finishRefresh();
                            Toast.makeText(getContext(), "没有更多数据", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, 2000);
                refresh.finishRefreshLoadMore();
            }
        });
    }

    private void initListener() {
        banner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(getContext(), "position==" + position, Toast.LENGTH_SHORT).show();

            }
        });

        hotSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "搜索页面", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getDataformNet() {
        OkHttpUtils.get()
                .url(Constant.hotUlr+"&offset="+curPage+"&limit="+12)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "联网失败" + e);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        //     Log.e("TAG", response.toString());
                        processData(response);
                        getBannerFromNet();
                        lvHot.setAdapter(new HotAdapter(getContext(), movies));

                    }
                });
    }
    private void getBannerFromNet (){
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
    private void processData(String response) {
        Gson gson = new Gson();
        hotBean = gson.fromJson(response, HotBean.class);

        movies = hotBean.getData().getMovies();
        totalPager =movies.size();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.hot_fragment;
    }

    @Override
    protected void initView() {
    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }

    }
}
