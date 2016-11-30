package com.example.machenike.mymovie.fragment.moviefragment;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.machenike.mymovie.R;
import com.example.machenike.mymovie.bean.HotBean;

import java.util.List;

/**
 * Created by Machenike on 2016/11/30.
 */
public class HotAdapter extends BaseAdapter {
    private final Context mContext;
    private  List<HotBean.DataBean.MoviesBean> movies;

    public HotAdapter(Context mContext, List<HotBean.DataBean.MoviesBean> movies) {
        this.mContext = mContext;
        this.movies = movies;
    }

    @Override
    public int getCount() {
        return movies == null?0:movies.size();
    }

    @Override
    public Object getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_hot_movie,null);
        }
        return convertView;
    }
}
