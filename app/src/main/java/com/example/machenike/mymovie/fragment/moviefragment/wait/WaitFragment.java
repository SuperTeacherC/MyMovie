package com.example.machenike.mymovie.fragment.moviefragment.wait;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.machenike.mymovie.R;
import com.example.machenike.mymovie.base.BaseFragment;

import butterknife.Bind;

/**
 * Created by Machenike on 2016/11/30.
 */
public class WaitFragment extends BaseFragment {
    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.wait_fragment;
    }

    @Override
    protected void initView() {
        recyclerview.setAdapter(new RecyclerViewAdapter(getContext()));
        recyclerview.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
    }

}
