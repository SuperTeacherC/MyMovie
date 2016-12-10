package com.example.machenike.mymovie.fragment.moviefragment.find.abroad;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.machenike.mymovie.R;
import com.example.machenike.mymovie.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Machenike on 2016/12/8.
 */
public class AbroadActivity extends BaseActivity {
    @Bind(R.id.tab_FindFragment_title)
    TabLayout tabFindFragmentTitle;
    @Bind(R.id.vp_FindFragment_pager)
    ViewPager vpFindFragmentPager;
    @Bind(R.id.iv_quxiao)
    ImageView ivQuxiao;
    private AmFragment amFragment;
    private JpFragment jpFragment;
    private KrFragment krFragment;
    private List<Fragment> list_fragment;
    private List<String> list_title;

    @Override
    protected void initListener() {
        ivQuxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Log.e("TAG","finish");
            }
        });
    }

    @Override
    protected void initData() {
        amFragment = new AmFragment();
        jpFragment = new JpFragment();
        krFragment = new KrFragment();
        list_fragment = new ArrayList<>();
        list_title = new ArrayList<>();
        //将fragment添加到list中
        list_fragment.add(amFragment);
        list_fragment.add(jpFragment);
        list_fragment.add(krFragment);

        list_title.add("美国");
        list_title.add("日本");
        list_title.add("韩国");

        //设置TabLayout的模式
        tabFindFragmentTitle.setTabMode(TabLayout.MODE_FIXED);
        //为TabLayout添加tab名称
        tabFindFragmentTitle.addTab(tabFindFragmentTitle.newTab().setText(list_title.get(0)));
        tabFindFragmentTitle.addTab(tabFindFragmentTitle.newTab().setText(list_title.get(1)));
        tabFindFragmentTitle.addTab(tabFindFragmentTitle.newTab().setText(list_title.get(2)));

        vpFindFragmentPager.setAdapter(new Find_tab_Adapter(getSupportFragmentManager()));
        tabFindFragmentTitle.setupWithViewPager(vpFindFragmentPager);
    }

    @Override
    protected int initLayout() {
        return R.layout.abroad_fragment;
    }

    private class Find_tab_Adapter extends FragmentPagerAdapter {
        public Find_tab_Adapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list_fragment.get(position);
        }

        @Override
        public int getCount() {
            return list_fragment.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return list_title.get(position);
        }
    }
}
