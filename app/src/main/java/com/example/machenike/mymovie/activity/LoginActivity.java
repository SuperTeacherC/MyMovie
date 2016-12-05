package com.example.machenike.mymovie.activity;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.example.machenike.mymovie.R;
import com.example.machenike.mymovie.base.BaseActivity;
import com.example.machenike.mymovie.fragment.AccountLoginFragment;
import com.example.machenike.mymovie.fragment.PhoneRegisterFragment;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;

import butterknife.Bind;

public class LoginActivity extends BaseActivity {

    String[] titles = {"账号密码登录", "手机号快捷登录"};
    @Bind(R.id.register)
    TextView register;
    @Bind(R.id.tl_1)
    SlidingTabLayout tll;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private MyPagerAdapter mAdapter;
    private Context mContext;
    private ViewPager vp;
    AccountLoginFragment accountLoginFragment;
    PhoneRegisterFragment phoneRegisterFragment;
    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        vp = (ViewPager) findViewById(R.id.vp);
        accountLoginFragment = new AccountLoginFragment();
        phoneRegisterFragment = new PhoneRegisterFragment();

        mFragments.add(accountLoginFragment);
        mFragments.add(phoneRegisterFragment);
        mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        vp.setAdapter(mAdapter);

        tll.setViewPager(vp,titles);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_login;
    }

    private class MyPagerAdapter extends FragmentPagerAdapter{
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}
