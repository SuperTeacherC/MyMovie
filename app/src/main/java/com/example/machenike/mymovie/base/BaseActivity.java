package com.example.machenike.mymovie.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import butterknife.ButterKnife;

/**
 * Created by Machenike on 2016/11/29.
 */
public abstract class BaseActivity extends FragmentActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(initLayout());
        ButterKnife.bind(this);
        initListener();
        initData();
    }

    protected abstract void initListener();

    protected abstract void initData();

    protected abstract int initLayout();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
