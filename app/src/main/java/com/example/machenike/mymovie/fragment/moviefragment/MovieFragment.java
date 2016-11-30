package com.example.machenike.mymovie.fragment.moviefragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.example.machenike.mymovie.R;
import com.example.machenike.mymovie.base.BaseFragment;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Machenike on 2016/11/30.
 */
public class MovieFragment extends BaseFragment {


    @Bind(R.id.tv_city)
    TextView tvCity;
    @Bind(R.id.tl_1)
    SegmentTabLayout tll;
    @Bind(R.id.tv_search)
    TextView tvSearch;
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
        viewpager.setCurrentItem(1);
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
