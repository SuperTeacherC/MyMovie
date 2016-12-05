package com.example.machenike.mymovie.fragment.moviefragment.find;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.machenike.mymovie.R;
import com.example.machenike.mymovie.bean.FindBottomBean;
import com.example.machenike.mymovie.utils.GetUrl;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Machenike on 2016/12/5.
 */
public class FindBottomAdapter extends BaseAdapter {
    private final Context mContext;
    private List<FindBottomBean.DataBean> data;


    public FindBottomAdapter(Context mContext, List<FindBottomBean.DataBean> data) {
        this.mContext = mContext;
        this.data = data;
        Log.e("TAG","FindBottomAdapter " +data.toString());
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate( R.layout.item_find_bottom,parent,false);
            holder  = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.priceFestivername.setText(data.get(position).getFestivalName());
        holder.tvTime.setText(data.get(position).getHeldDate());
        holder.tvMovieName.setText(data.get(position).getMovieName());
        holder.tvPrizeName.setText(data.get(position).getPrizeName());
        String path = GetUrl.getPath(data.get(position).getImg(), mContext);
        Glide.with(mContext).load(path).error(R.drawable.xiaofei).placeholder(R.drawable.xiaofei).into(holder.ivMoviewPic);
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.price_festivername)
        TextView priceFestivername;
        @Bind(R.id.tv_time)
        TextView tvTime;
        @Bind(R.id.iv_moview_pic)
        ImageView ivMoviewPic;
        @Bind(R.id.tv_prize_name)
        TextView tvPrizeName;
        @Bind(R.id.tv_movie_name)
        TextView tvMovieName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
