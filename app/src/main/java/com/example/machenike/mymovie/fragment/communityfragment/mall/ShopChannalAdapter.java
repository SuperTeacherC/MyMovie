package com.example.machenike.mymovie.fragment.communityfragment.mall;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.machenike.mymovie.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Machenike on 2016/12/9.
 */
public class ShopChannalAdapter extends BaseAdapter {

    private final Context context;
    private final int[] images;
    private String []names = {"数码","高玩专区","玩具","生活","服饰","超编","机器猫","魔兽","美队","星球大招"};
    public ShopChannalAdapter(Context context, int[] images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public int getCount() {
        return images.length-1;
    }

    @Override
    public Object getItem(int position) {
        return images[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_shop_channal, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.ivChannal.setImageResource(images[position]);
        holder.tvName.setText(names[position]);
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.iv_channal)
        ImageView ivChannal;
        @Bind(R.id.tv_name)
        TextView tvName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
