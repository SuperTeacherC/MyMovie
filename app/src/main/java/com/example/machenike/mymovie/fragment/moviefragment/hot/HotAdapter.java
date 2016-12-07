package com.example.machenike.mymovie.fragment.moviefragment.hot;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.machenike.mymovie.R;
import com.example.machenike.mymovie.activity.DetailsActivity;
import com.example.machenike.mymovie.activity.PlayActivity;
import com.example.machenike.mymovie.activity.TicketActivity;
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
        final ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_hot_movie, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final HotBean.DataBean.MoviesBean moviesBean = movies.get(position);
        if(position == 0) {
            holder.hotTopone.setVisibility(View.VISIBLE);
        }else{
            holder.hotTopone.setVisibility(View.GONE);
        }
        holder.hotTopic.setText(moviesBean.getStar());
        holder.hotInfo.setText(moviesBean.getScm());
        holder.tvHotName.setText(moviesBean.getNm());
        Glide.with(mContext)
                .load(moviesBean.getImg())
                .into(holder.ivHotMovies);
        holder.tvHotName.setText(moviesBean.getNm());
        holder.tvHotUserFen.setText(moviesBean.getSc() + "");
        holder.tvHotVipFen.setText(moviesBean.getSn() + "");
        holder.tvHotScm.setText(moviesBean.getScm());
        holder.tvHotShowinfo.setText(moviesBean.getShowInfo());
        holder.imax3d.setVisibility(moviesBean.isImax() ? View.VISIBLE : View.GONE);
        holder.hot3d.setVisibility(moviesBean.isValue3d() ? View.VISIBLE : View.GONE);
        holder.tvHotReply.setVisibility(moviesBean.getPreSale() == 1 ? View.GONE : View.VISIBLE);
        holder.tvHotYushou.setVisibility(moviesBean.getPreSale() == 0 ? View.GONE : View.VISIBLE);
        final int  id= moviesBean.getId();
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailsActivity.class);

                intent.putExtra("details",id);
                mContext.startActivity(intent);
            }
        });

        holder.tvHotYushou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, TicketActivity.class);
                intent.putExtra("TICKET",id);
                mContext.startActivity(intent);
            }
        });
        holder.tvHotReply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, TicketActivity.class);
                intent.putExtra("TICKET",id);
                mContext.startActivity(intent);
            }
        });
        holder.ivHotMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PlayActivity.class);
                intent.putExtra("PLAY",id);
                mContext.startActivity(intent);
            }
        });
        holder.hotTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"专题页面", Toast.LENGTH_SHORT).show();
            }
        });
        holder.hotInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"资讯页面", Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.iv_hot_movies)
        ImageView ivHotMovies;
        @Bind(R.id.tv_hot_name)
        TextView tvHotName;
        @Bind(R.id.imax_2d)
        ImageView imax2d;
        @Bind(R.id.hot_3d)
        ImageView hot3d;
        @Bind(R.id.imax_3d)
        ImageView imax3d;
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
        @Bind(R.id.movie_info)
        LinearLayout movieInfo;
        @Bind(R.id.hot_item_all)
        LinearLayout hotItemAll;
        @Bind(R.id.tv_hot_reply)
        TextView tvHotReply;
        @Bind(R.id.tv_hot_yushou)
        TextView tvHotYushou;
        @Bind(R.id.hot_topic)
        TextView hotTopic;
        @Bind(R.id.hot_info)
        TextView hotInfo;
        @Bind(R.id.hot_topone)
        LinearLayout hotTopone;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);

        }
    }

}
