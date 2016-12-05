package com.example.machenike.mymovie.fragment.moviefragment.find;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.machenike.mymovie.R;

import java.util.List;

/**
 * Created by Machenike on 2016/12/4.
 */
public class FindMovieAdapter extends RecyclerView.Adapter {
    private static final int TYPE = 0;
    private static final int AREA = 1;
    private static final int YEAR = 2;
    private static final int HOT = 3;
    private final Context mContext;
    private  List<FindTopBean.DataBean> data;

    public  FindMovieAdapter(Context mContext, List<FindTopBean.DataBean> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public int getItemViewType(int position) {
        if(position ==0) {
            return TYPE;
        }else if(position==1) {
            return AREA;
        }else if(position ==2) {
            return YEAR;
        }else{
            return HOT;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      if(viewType == TYPE) {
          return  new TypeViewHolder(mContext, View.inflate(mContext, R.layout.find_type,null));
      }else if(viewType == AREA) {
          return  new AreaViewHolder(mContext, View.inflate(mContext, R.layout.find_area,null));
      }else if(viewType == YEAR) {
          return  new YearViewHolder(mContext, View.inflate(mContext, R.layout.find_year,null));
      }else  {
          return  new HotViewHolder(mContext, View.inflate(mContext, R.layout.find_hot,null));
      }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE) {
            TypeViewHolder typeViewHolder = (TypeViewHolder) holder;
            typeViewHolder.setData(data.get(0));
        } else if (getItemViewType(position) == AREA) {
            AreaViewHolder areaViewHolder = (AreaViewHolder) holder;
            areaViewHolder.setData(data.get(1));
        } else if (getItemViewType(position) == YEAR) {
            YearViewHolder yearViewHolder = (YearViewHolder) holder;
            yearViewHolder.setData(data.get(2));
        } else {
            HotViewHolder hotViewHolder = (HotViewHolder) holder;
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }



    private class TypeViewHolder extends RecyclerView.ViewHolder {
        private final Context mContext;
        private LinearLayout ll_type;
        public TypeViewHolder(Context mContext, View inflate) {
            super(inflate);
            this.mContext = mContext;
            ll_type = (LinearLayout) inflate.findViewById(R.id.ll_type);
        }

        public void setData(FindTopBean.DataBean dataBean) {
            List<FindTopBean.DataBean.TagListBean> tagList = dataBean.getTagList();
            for (int i= 0 ;i<tagList.size();i++){
                View view = LayoutInflater.from(mContext).inflate(R.layout.item_find_type,ll_type, false);
                TextView item = (TextView) view.findViewById(R.id.item);
                item.setText(tagList.get(i).getTagName());
                ll_type.addView(view);
            }

        }
    }

    private class AreaViewHolder extends RecyclerView.ViewHolder {
        private final Context mContext;
        private LinearLayout ll_type;

        public AreaViewHolder(Context mContext, View inflate) {
            super(inflate);
            this.mContext = mContext;
            ll_type = (LinearLayout) inflate.findViewById(R.id.ll_type);
        }


        public void setData(FindTopBean.DataBean dataBean) {
            List<FindTopBean.DataBean.TagListBean> tagList = dataBean.getTagList();
            for (int i= 0 ;i<tagList.size();i++){
                View view = LayoutInflater.from(mContext).inflate(R.layout.item_find_type,ll_type, false);
                TextView item = (TextView) view.findViewById(R.id.item);
                item.setText(tagList.get(i).getTagName());
                ll_type.addView(view);
            }

        }
    }

    private class YearViewHolder extends RecyclerView.ViewHolder {
        private final Context mContext;
        private LinearLayout ll_type;


        public YearViewHolder(Context mContext, View inflate) {
            super(inflate);
            this.mContext = mContext;
            ll_type = (LinearLayout) inflate.findViewById(R.id.ll_type);
        }

        public void setData(FindTopBean.DataBean dataBean) {
            List<FindTopBean.DataBean.TagListBean> tagList = dataBean.getTagList();
            for (int i= 0 ;i<tagList.size();i++){
                View view = LayoutInflater.from(mContext).inflate(R.layout.item_find_type,ll_type, false);
                TextView item = (TextView) view.findViewById(R.id.item);
                item.setText(tagList.get(i).getTagName());
                ll_type.addView(view);
            }

        }
    }

    private class HotViewHolder extends RecyclerView.ViewHolder {
        public HotViewHolder(Context mContext, View inflate) {
            super(inflate);
        }
    }
}
