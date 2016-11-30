package com.example.machenike.mymovie.fragment.moviefragment;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.machenike.mymovie.R;
import com.example.machenike.mymovie.bean.HotBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Machenike on 2016/11/30.
 */
public class HotAdapter extends BaseAdapter {
    private final Context mContext;
    private List<HotBean.DataBean.MoviesBean> movies;

    public HotAdapter(Context mContext, List<HotBean.DataBean.MoviesBean> movies) {
        this.mContext = mContext;
        this.movies = movies;
    }

    @Override
    public int getCount() {
        return movies == null ? 0 : movies.size();
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
        ViewHolder holder ;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_hot_movie, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        HotBean.DataBean.MoviesBean moviesBean = movies.get(position);
        
        holder.tvHotName.setText(moviesBean.getNm());
        Glide.with(mContext)
                .load(moviesBean.getImg())
                .into(holder.ivHotMovies);
        holder.tvHotName.setText(moviesBean.getNm());
//        holder.tvHotUserFen.setText((int) moviesBean.getSc());
        holder.tvHotScm.setText(moviesBean.getScm());
        holder.tvHotShowinfo.setText(moviesBean.getShowInfo());
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.iv_hot_movies)
        ImageView ivHotMovies;
        @Bind(R.id.tv_hot_name)
        TextView tvHotName;
        @Bind(R.id.tv_hot_user)
        TextView tvHotUser;
        @Bind(R.id.tv_hot_user_fen)
        TextView tvHotUserFen;
        @Bind(R.id.tv_hot_vip)
        TextView tvHotVip;
        @Bind(R.id.tv_hot_vip_fen)
        TextView tvHotVipFen;
        @Bind(R.id.tv_hot_scm)
        TextView tvHotScm;
        @Bind(R.id.tv_hot_showinfo)
        TextView tvHotShowinfo;
        @Bind(R.id.tv_hot_reply)
        TextView tvHotReply;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
