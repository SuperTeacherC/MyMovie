package com.example.machenike.mymovie.fragment.moviefragment.wait;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.machenike.mymovie.R;

/**
 * Created by Machenike on 2016/12/2.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter {
    private static final int TYPE_ONE = 1;
    private static final int TYPE_TWO = 2;
    private static final int TYPE_THREE = 3;
    private Context mContext;

    public RecyclerViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ONE) {
//            return  new OneTypeViewHolder(mContext, View.inflate(mContext, R.layout.one_type_wait,null));
            return new OneTypeViewHolder(mContext, LayoutInflater.from(mContext).inflate(R.layout.one_type_wait,parent, false));

        } else if (viewType == TYPE_TWO) {
           // return new TwoTypeViewHolder(mContext, View.inflate(mContext, R.layout.two_tyoe_wait, null));
            return new TwoTypeViewHolder(mContext, LayoutInflater.from(mContext).inflate(R.layout.two_tyoe_wait, parent, false));
        } else {
           // return new ThreeTypeViewHolder(mContext, View.inflate(mContext, R.layout.three_tyoe_wait, null));
            return new ThreeTypeViewHolder(mContext, LayoutInflater.from(mContext).inflate(R.layout.three_tyoe_wait,parent, false));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_ONE;
        } else if (position == 1) {
            return TYPE_TWO;
        } else {
            return TYPE_THREE;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_ONE) {
            OneTypeViewHolder oneTypeViewHolder = (OneTypeViewHolder) holder;
            oneTypeViewHolder.setData(position);
        } else if (getItemViewType(position) == TYPE_TWO) {
            TwoTypeViewHolder twoTypeViewHolder = (TwoTypeViewHolder) holder;
            twoTypeViewHolder.setData(position);
        } else {
            ThreeTypeViewHolder threeTypeViewHolder = (ThreeTypeViewHolder) holder;
            threeTypeViewHolder.setData(position);
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
