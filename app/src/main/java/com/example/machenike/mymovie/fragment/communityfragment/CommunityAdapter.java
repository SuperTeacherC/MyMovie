package com.example.machenike.mymovie.fragment.communityfragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.machenike.mymovie.R;
import com.example.machenike.mymovie.bean.CommunityBean;

import java.util.List;

/**
 * Created by Machenike on 2016/12/2.
 */
public class CommunityAdapter extends RecyclerView.Adapter {

    private static final int TYPE_ONE = 1;
    private static final int TYPE_THREE = 3;
    private static final int OTHER_TYPE = 2;
    private Context mContext;


    private List<CommunityBean.DataBean.FeedsBean> feeds;

    public CommunityAdapter(Context mContext, List<CommunityBean.DataBean.FeedsBean> feeds) {
        this.mContext = mContext;
        this.feeds = feeds;
    }

    @Override
    public int getItemViewType(int position) {
        if (feeds.get(position).getImages().size() == 1) {
            return TYPE_ONE;
        } else if (feeds.get(position).getImages().size() == 3) {
            return TYPE_THREE;
        }
        return OTHER_TYPE;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ONE) {
            return new OneViewHolder(mContext, View.inflate(mContext, R.layout.item_community_one, null));
        } else if (viewType == TYPE_THREE) {
            return new ThreeViewHolder(mContext, View.inflate(mContext, R.layout.item_community_three, null));
        }
        return new OtherViewHolder(mContext,View.inflate(mContext,R.layout.item_community_other,null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_ONE) {
            OneViewHolder oneHolder = (OneViewHolder) holder;
            oneHolder.setData(feeds,position);
        } else if (getItemViewType(position) == TYPE_THREE) {
            ThreeViewHolder threeViewHolder = (ThreeViewHolder) holder;
            threeViewHolder.setData(feeds,position);
        }else if(getItemViewType(position) == OTHER_TYPE) {
            OtherViewHolder  otherViewHolder = (OtherViewHolder) holder;
            otherViewHolder.setData(feeds,position);
        }


    }

    @Override
    public int getItemCount() {
        return feeds.size();
    }

    private class OneViewHolder extends RecyclerView.ViewHolder {
        private final Context mContext;
        ImageView iv_community_movie;
        TextView tv_maoyan;
        TextView tv_xiaoxi;
        ImageView iv_xiaoxi;
        TextView tv_liulan;
        ImageView iv_liulan;
        TextView tv_title;

        public OneViewHolder(Context mContext, View inflate) {
            super(inflate);
            this.mContext = mContext;

            iv_community_movie = (ImageView) inflate.findViewById(R.id.iv_community_movie);
            tv_maoyan = (TextView) inflate.findViewById(R.id.tv_maoyan);
            tv_xiaoxi = (TextView) inflate.findViewById(R.id.tv_xiaoxi);
            iv_xiaoxi = (ImageView) inflate.findViewById(R.id.iv_xiaoxi);
            tv_liulan = (TextView) inflate.findViewById(R.id.tv_liulan);
            iv_liulan = (ImageView) inflate.findViewById(R.id.iv_liulan);
            tv_title = (TextView) inflate.findViewById(R.id.tv_title);

        }

        public void setData(List<CommunityBean.DataBean.FeedsBean> feeds, int position) {
            Glide.with(mContext).load(feeds.get(position).getImages().get(0).getUrl()).into(iv_community_movie);
            Log.e("TAG", "" + feeds.get(position).getTitle());
            Log.e("TAG", "" + tv_title);

            tv_title.setText(feeds.get(position).getTitle());
            tv_maoyan.setText(feeds.get(position).getUser().getNickName());
            tv_liulan.setText(feeds.get(position).getViewCount()+"");
            tv_xiaoxi.setText(feeds.get(position).getUser().getTopicCount()+"");

        }
    }

    private class ThreeViewHolder extends RecyclerView.ViewHolder {
        private final Context mContext;
        TextView tv_title;
        ImageView iv_img1;
        ImageView iv_img2;
        ImageView iv_img3;
        TextView tv_maoyan;
        TextView tv_xiaoxi;
        ImageView iv_xiaoxi;
        TextView tv_liulan;
        ImageView iv_liulan;

        public ThreeViewHolder(Context mContext, View inflate) {
            super(inflate);
            this.mContext = mContext;

            tv_title = (TextView) inflate.findViewById(R.id.tv_title);
            iv_img1 = (ImageView) inflate.findViewById(R.id.iv_img1);
            iv_img2 = (ImageView) inflate.findViewById(R.id.iv_img2);
            iv_img3 = (ImageView) inflate.findViewById(R.id.iv_img3);
            tv_maoyan = (TextView) inflate.findViewById(R.id.tv_maoyan);
            tv_xiaoxi = (TextView) inflate.findViewById(R.id.tv_xiaoxi);
            iv_xiaoxi = (ImageView) inflate.findViewById(R.id.iv_xiaoxi);
            tv_liulan = (TextView) inflate.findViewById(R.id.tv_liulan);
            iv_liulan = (ImageView) inflate.findViewById(R.id.iv_liulan);
        }

        public void setData(List<CommunityBean.DataBean.FeedsBean> feeds, int position) {
            Glide.with(mContext).load(feeds.get(position).getImages().get(0).getUrl()).into(iv_img1);
            Glide.with(mContext).load(feeds.get(position).getImages().get(1).getUrl()).into(iv_img2);
            Glide.with(mContext).load(feeds.get(position).getImages().get(2).getUrl()).into(iv_img3);
            tv_title.setText(feeds.get(position).getTitle());
            tv_maoyan.setText(feeds.get(position).getUser().getNickName());

            tv_liulan.setText(feeds.get(position).getViewCount()+"");
            tv_xiaoxi.setText(feeds.get(position).getUser().getTopicCount()+"");
        }
    }

    private class OtherViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        ImageView iv_img1;
        ImageView iv_img2;
        TextView tv_maoyan;
        TextView tv_xiaoxi;
        ImageView iv_xiaoxi;
        TextView tv_liulan;
        ImageView iv_liulan;
        public OtherViewHolder(Context mContext, View inflate) {
            super(inflate);
            tv_title = (TextView) inflate.findViewById(R.id.tv_title);
            iv_img1 = (ImageView) inflate.findViewById(R.id.iv_img1);
            iv_img2 = (ImageView) inflate.findViewById(R.id.iv_img2);
            tv_maoyan = (TextView) inflate.findViewById(R.id.tv_maoyan);
            tv_xiaoxi = (TextView) inflate.findViewById(R.id.tv_xiaoxi);
            iv_xiaoxi = (ImageView) inflate.findViewById(R.id.iv_xiaoxi);
            tv_liulan = (TextView) inflate.findViewById(R.id.tv_liulan);
            iv_liulan = (ImageView) inflate.findViewById(R.id.iv_liulan);
        }

        public void setData(List<CommunityBean.DataBean.FeedsBean> feeds, int position) {
            Log.e("TAG","otherposition"+ position);
            Glide.with(mContext).load(feeds.get(position).getImages().get(0).getUrl()).into(iv_img1);
            Glide.with(mContext).load(feeds.get(position).getImages().get(1).getUrl()).into(iv_img2);
            tv_title.setText(feeds.get(position).getTitle());
            tv_maoyan.setText(feeds.get(position).getUser().getNickName());

            tv_liulan.setText(feeds.get(position).getViewCount()+"");
            tv_xiaoxi.setText(feeds.get(position).getUser().getTopicCount()+"");
        }
    }
}
