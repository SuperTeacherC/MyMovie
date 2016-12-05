package com.example.machenike.mymovie.fragment.moviefragment.find;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.machenike.mymovie.R;
import com.example.machenike.mymovie.bean.FindCenterBean;
import com.example.machenike.mymovie.utils.GetUrl;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Machenike on 2016/12/5.
 */
public class GridViewAdapter extends BaseAdapter {
    private final Context mContext;
    private List<FindCenterBean.DataBean> data;

    public GridViewAdapter(Context mContext, List<FindCenterBean.DataBean> data) {
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
            ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_find_center, parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.tvBoardname.setText(data.get(position).getBoardName());
        holder.tvMoviename.setText(data.get(position).getMovieName());
        //*************截取字符串***************

        String path1 = GetUrl.getPath(data.get(position).getMovieImgs().get(0),mContext);
        String path2 = GetUrl.getPath(data.get(position).getMovieImgs().get(1),mContext);
        //*******************截取字符串end******************
        Glide.with(mContext).load(path1).error(R.drawable.xiaofei).placeholder(R.drawable.xiaofei).into(holder.ivPic1);
        Glide.with(mContext).load(path2).error(R.drawable.xiaofei).placeholder(R.drawable.xiaofei).into(holder.ivPic2);
        holder.setColor(position);
        return convertView;
    }


    static class ViewHolder {
        @Bind(R.id.tv_boardname)
        TextView tvBoardname;
        @Bind(R.id.tv_moviename)
        TextView tvMoviename;
        @Bind(R.id.ll_text)
        LinearLayout llText;
        @Bind(R.id.iv_pic2)
        ImageView ivPic2;
        @Bind(R.id.iv_pic1)
        ImageView ivPic1;
        @Bind(R.id.fl_pic)
        RelativeLayout flPic;

        private void setColor(int position) {
            if(position==0) {
                tvBoardname.setTextColor(Color.parseColor("#0099cc"));
            }else if(position ==1) {
                tvBoardname.setTextColor(Color.parseColor("#cc3333"));
            }else if(position ==2) {
                tvBoardname.setTextColor(Color.parseColor("#ff9900"));
            }else{
                tvBoardname.setTextColor(Color.parseColor("#66cc00"));
            }
        }
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
