package com.example.machenike.mymovie.fragment.communityfragment.mall;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.machenike.mymovie.R;
import com.example.machenike.mymovie.bean.ShopItemBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Machenike on 2016/12/9.
 */
public class ShopItemAdapter extends BaseAdapter {
    private final Context context;
    private List<ShopItemBean.DataBean.ListBean> list;

    public ShopItemAdapter(Context context, List<ShopItemBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_shop_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        Glide.with(context).load(list.get(position).getPic()).error(R.drawable.xiaofei).placeholder(R.drawable.xiaofei).into(holder.ivShopPic);

        holder.tvName.setText(list.get(position).getTitle());
        holder.tvPrice.setText(list.get(position).getPrice()+"");
        holder.tvValue.setText(list.get(position).getValue()+"");

        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.iv_shop_pic)
        ImageView ivShopPic;
        @Bind(R.id.tv_name)
        TextView tvName;
        @Bind(R.id.tv_price)
        TextView tvPrice;
        @Bind(R.id.tv_value)
        TextView tvValue;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
