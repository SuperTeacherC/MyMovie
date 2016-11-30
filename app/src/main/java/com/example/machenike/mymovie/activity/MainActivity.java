package com.example.machenike.mymovie.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.machenike.mymovie.R;
import com.example.machenike.mymovie.base.BaseActivity;
import com.example.machenike.mymovie.fragment.cinemafragment.CinemaFragment;
import com.example.machenike.mymovie.fragment.communityfragment.CommunityFragment;
import com.example.machenike.mymovie.fragment.mine.MineFragment;
import com.example.machenike.mymovie.fragment.moviefragment.MovieFragment;

import butterknife.Bind;

public class MainActivity extends BaseActivity {

    private static final int MESSAGE_BACK = 0;
    @Bind(R.id.frameLayout)
    FrameLayout frameLayout;
    @Bind(R.id.rb_movie)
    RadioButton rbMovie;
    @Bind(R.id.rb_cinema)
    RadioButton rbCinema;
    @Bind(R.id.rb_community)
    RadioButton rbCommunity;
    @Bind(R.id.rb_mine)
    RadioButton rbMine;
    @Bind(R.id.rg_main)
    RadioGroup rgMain;

    private MovieFragment movieFragment;
    private CinemaFragment cinemaFragment;
    private MineFragment mineFragment;
    private CommunityFragment communityFragment;
    private FragmentTransaction transaction;

    @Override
    protected void initListener() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_movie:
                        setSelect(0);
                        break;
                    case R.id.rb_cinema:
                        setSelect(1);
                        break;
                    case R.id.rb_community:
                        setSelect(2);
                        break;
                    case R.id.rb_mine:
                        setSelect(3);
                        break;
                }
            }
        });
    }

    private void setSelect(int i) {
        FragmentManager supportFragmentManager = this.getSupportFragmentManager();
        transaction = supportFragmentManager.beginTransaction();
        hideFragment();
        if(i==0) {
            if(movieFragment ==null) {
                movieFragment = new MovieFragment();
                transaction.add(R.id.frameLayout,movieFragment);
            }
            transaction.show(movieFragment);
        }else if(i ==1) {
            if(cinemaFragment == null) {
                cinemaFragment = new CinemaFragment();
                transaction.add(R.id.frameLayout,cinemaFragment);
            }
            transaction.show(cinemaFragment);
        }else if(i==2){
            if(communityFragment == null) {
                communityFragment = new CommunityFragment();
                transaction.add(R.id.frameLayout,communityFragment);
            }
            transaction.show(communityFragment);
        }else if (i==3){
            if(mineFragment == null) {
                mineFragment = new MineFragment();
                transaction.add(R.id.frameLayout,mineFragment);
            }
            transaction.show(mineFragment);
        }
        transaction.commit();
    }

    private void hideFragment() {
        if(movieFragment != null) {
            transaction.hide(movieFragment);
        }
        if(cinemaFragment != null) {
            transaction.hide(cinemaFragment);
        }
        if(communityFragment != null) {
            transaction.hide(communityFragment);
        }
        if(mineFragment != null){
            transaction.hide(mineFragment);
        }
    }

    @Override
    protected void initData() {
        initListener();
        rgMain.check(R.id.rb_movie);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    boolean flag = true;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MESSAGE_BACK:
                    flag = true;
                    break;
            }
        }
    };

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && flag == true) {
            Toast.makeText(this, "再次点击退出", Toast.LENGTH_SHORT).show();
          handler.sendEmptyMessageDelayed(MESSAGE_BACK,2000);
            flag = false;
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
