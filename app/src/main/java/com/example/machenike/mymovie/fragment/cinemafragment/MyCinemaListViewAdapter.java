package com.example.machenike.mymovie.fragment.cinemafragment;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.machenike.mymovie.R;
import com.example.machenike.mymovie.bean.CinemaBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Machenike on 2016/12/1.
 */
public class MyCinemaListViewAdapter extends BaseAdapter {
    private Context mContext;
    private final List<CinemaBean.DataBean.东城区Bean> data;

    public MyCinemaListViewAdapter(Context mContext, List<CinemaBean.DataBean.东城区Bean> data) {
        this.mContext = mContext;
        this.data = data;
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
        ViewHolder holder ;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_cinema, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        CinemaBean.DataBean.东城区Bean bean = data.get(position);
        holder.cinemaName.setText(bean.getNm());
        holder.cinemaAddress.setText(bean.getAddr());
        holder.cinemaDistance.setText(bean.getDistance()+"");
        holder.cinemaPrice.setText(bean.getSellPrice()+"");
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.cinema_name)
        TextView cinemaName;
        @Bind(R.id.cinema_price)
        TextView cinemaPrice;
        @Bind(R.id.cinema_address)
        TextView cinemaAddress;
        @Bind(R.id.cinema_distance)
        TextView cinemaDistance;
        @Bind(R.id.cinema_sits)
        ImageView cinemaSits;
        @Bind(R.id.cinema_tui)
        ImageView cinemaTui;
        @Bind(R.id.cinema_xiaochi)
        ImageView cinemaXiaochi;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
