package com.example.machenike.mymovie.fragment.moviefragment.find;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.machenike.mymovie.R;
import com.example.machenike.mymovie.bean.FindTopBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Machenike on 2016/12/4.
 */
public class FindTypeAdapter extends BaseAdapter {

    private final Context mComtext;
    private  FindTopBean.DataBean dataBean;
    private final List<FindTopBean.DataBean.TagListBean> tagList;


    public FindTypeAdapter(Context mContext, FindTopBean.DataBean dataBean) {
        this.mComtext = mContext;
        this.dataBean = dataBean;
        tagList = dataBean.getTagList();
    }

    @Override
    public int getCount() {
        return tagList == null ? 0 : tagList.size();
    }

    @Override
    public Object getItem(int position) {
        return tagList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mComtext, R.layout.item_find_type, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        FindTopBean.DataBean.TagListBean tagListBean = tagList.get(position);
        holder.item.setText(tagListBean.getTagName());
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.item)
        TextView item;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
