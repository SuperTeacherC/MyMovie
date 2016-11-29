package com.example.machenike.mymovie;

import android.widget.TextView;

import com.example.machenike.mymovie.base.BaseActivity;

import butterknife.Bind;

public class MainActivity extends BaseActivity {

    @Bind(R.id.id)
    TextView id;

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }
}
