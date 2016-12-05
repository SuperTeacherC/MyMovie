package com.example.machenike.mymovie.fragment.moviefragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.example.machenike.mymovie.R;
import com.example.machenike.mymovie.base.BaseFragment;
import com.example.machenike.mymovie.citypicker.CityPickerActivity;
import com.example.machenike.mymovie.fragment.moviefragment.find.FindFragment;
import com.example.machenike.mymovie.fragment.moviefragment.hot.HotFragment;
import com.example.machenike.mymovie.fragment.moviefragment.wait.WaitFragment;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Machenike on 2016/11/30.
 */
public class MovieFragment extends BaseFragment {


    private static final int CITY = 0;
    @Bind(R.id.tv_city)
    TextView tvCity;
    @Bind(R.id.tl_1)
    SegmentTabLayout tll;
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    private HotFragment hotFragment;
    private FindFragment findFragment;
    private WaitFragment waitFragment;
    private List<Fragment> fragmentList;
    String[] titles = {"放映", "待映", "找片"};

    @Override
    protected void initData() {
        initFragment();
        tll.setTabData(titles);
        viewpager.setAdapter(new MyAdapter(getFragmentManager()));
        tll.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewpager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tll.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });
        initListener();
        viewpager.setCurrentItem(0);
    }

    private void initListener() {
        tvCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CityPickerActivity.class);
               startActivityForResult(intent,CITY);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CITY && resultCode == CityPickerActivity.RESULT_OK) {
            String result = data.getStringExtra("picked_city");
            tvCity.setText(result);
        }
    }

    private void initFragment() {
        fragmentList = new ArrayList<>();
        hotFragment = new HotFragment();
        waitFragment = new WaitFragment();
        findFragment = new FindFragment();

        fragmentList.add(hotFragment);
        fragmentList.add(waitFragment);
        fragmentList.add(findFragment);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.movie_fragment;
    }

    @Override
    protected void initView() {

    }

    private class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}
