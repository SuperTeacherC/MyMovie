package com.example.machenike.mymovie.fragment.moviefragment;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;

import com.example.machenike.mymovie.R;
import com.example.machenike.mymovie.base.BaseFragment;
import com.example.machenike.mymovie.bean.HotBean;
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
public class HotFragment extends BaseFragment {


//    @Bind(R.id.bannerTitle)
//    Banner bannerTitle;
    @Bind(R.id.lv_hot)
    ListView lvHot;
    private List<HotBean.DataBean.MoviesBean> movies;
    private HotBean hotBean;
    private Context mContext;

    @Override
    protected void initData() {
        getDataformNet();
    }

    private void getDataformNet() {
        OkHttpUtils.get()
                .url(Constant.hotUlr)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "联网失败" + e);
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("TAG",response.toString());
                        processData(response);
                        lvHot.setAdapter(new HotAdapter(getContext(), movies));
                    }
                });
    }

    private void processData(String response) {
        Gson gson = new Gson();
        hotBean = gson.fromJson(response, HotBean.class);
        movies = hotBean.getData().getMovies();

    }

    @Override
    protected int getLayoutID() {
        return R.layout.hot_fragment;
    }

    @Override
    protected void initView() {

    }

}
