package com.example.machenike.mymovie.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.example.machenike.mymovie.R;
import com.example.machenike.mymovie.SplashBean;
import com.example.machenike.mymovie.utils.Constant;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

public class WelcomeActivity extends Activity {

    private ImageView iv_splash;
    private RelativeLayout rl_splash;
    private SplashBean splashBean;
    private String pic;
    private Context mContext;
    private int width;
    private int height;
    private Handler handler;
    private String message;
    private static  final int WAITING = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // 去掉窗口标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 隐藏顶部的状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);
        rl_splash = (RelativeLayout)findViewById(R.id.rl_splash);
        iv_splash = (ImageView)findViewById(R.id.iv_splash);
                getDataFromNet();

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                },2500);
            }
        },1000);

    }
    public void getDataFromNet() {
        OkHttpUtils.get()
                .url(Constant.splashUlr)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "联网失败" + e);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        splashBean = gson.fromJson(response, SplashBean.class);
                        List<SplashBean.PostersBean> posters = splashBean.getPosters();
                        pic = posters.get(0).getPic();
                        Log.e("TAG", "联网成功");
                        Glide.with(WelcomeActivity.this)
                                .load(pic)
                                .into(iv_splash);
                    }
                });


    }

}
