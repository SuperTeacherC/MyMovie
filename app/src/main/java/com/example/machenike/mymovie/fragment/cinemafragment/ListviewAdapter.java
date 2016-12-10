package com.example.machenike.mymovie.fragment.cinemafragment;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.machenike.mymovie.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Machenike on 2016/12/9.
 */
public class ListviewAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<String> strs;

    public ListviewAdapter(Context mContext, List<String> strs) {
        this.mContext = mContext;
        this.strs = strs;
    }

    @Override
    public int getCount() {
        return strs.size();
    }

    @Override
    public Object getItem(int position) {
        return strs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_selector, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvText.setText(strs.get(position));
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.tv_text)
        TextView tvText;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
