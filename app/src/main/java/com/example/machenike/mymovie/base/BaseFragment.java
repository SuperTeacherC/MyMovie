package com.example.machenike.mymovie.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by Machenike on 2016/11/29.
 */
public  abstract class BaseFragment extends Fragment {

    protected Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(mContext,getLayoutID(),null);
        Log.e("TAG", "getLayoutID" + getLayoutID());
        ButterKnife.bind(this, view);
        initView();
        return view;
    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    protected abstract void initData();
    protected abstract int getLayoutID();
    protected abstract void initView();

    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
