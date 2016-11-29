package com.example.machenike.mymovie.base;

import android.app.Activity;
import android.os.Bundle;

import butterknife.ButterKnife;

/**
 * Created by Machenike on 2016/11/29.
 */
public abstract class BaseActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(initLayout());
        ButterKnife.bind(this);

        initData();
        initListener();

    }

    protected abstract void initListener();

    protected abstract void initData();

    protected abstract int initLayout();


}
