package com.example.machenike.mymovie.fragment.moviefragment;

import android.widget.TextView;

import com.example.machenike.mymovie.R;
import com.example.machenike.mymovie.base.BaseFragment;
import com.flyco.tablayout.SegmentTabLayout;

import butterknife.Bind;

/**
 * Created by Machenike on 2016/11/30.
 */
public class MovieFragment extends BaseFragment {

    @Bind(R.id.tv_search)
    TextView tvSearch;
    @Bind(R.id.tl_1)
    SegmentTabLayout tll;

    @Override
    protected void initData() {
        String[] titles = {"放映", "待映", "照片"};

        tll.setTabData(titles);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.movie_fragment;
    }

    @Override
    protected void initView() {

    }
}
