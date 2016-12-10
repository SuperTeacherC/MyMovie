package com.example.machenike.mymovie.fragment.moviefragment.find;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.machenike.mymovie.R;

import java.util.List;

/**
 * Created by Machenike on 2016/12/8.
 */
public class MyAllPrizeAdapter extends BaseAdapter {
    private static final int TYPE_HEADER = 0;  //代表标题
    private static final int TYPE_ITEM = 1;    //代表项目item
    private final Context context;

    private List<AllPrizeActivity.Type> mList;
    private LayoutInflater inflater;


    public MyAllPrizeAdapter(Context context, List<AllPrizeActivity.Type> list) {
        mList = list;
        this.context = context;
        inflater =LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        int count = 0;
        if (mList != null) {
            for (AllPrizeActivity.Type type : mList) {
                count += type.size();
            }
        }
        Log.e("TAG","***********************count******************"+count);
        return count;
    }

    @Override
    public Object getItem(int position) {

        int head = 0;  //标题位置
        for (AllPrizeActivity.Type type : mList) {
            int size = type.size();
            int current = position - head;
            if (current < size) {
                //返回对应位置的值
                return type.getItem(current);
            }
            head += size;
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        Log.e("TAG","getItemViewType"+getItemViewType(position));
        Log.e("TAG","position"+position);

        switch (getItemViewType(position)) {
            //分为两种情况加载item
            case TYPE_HEADER: //加载标题布局
                if (convertView == null) {
                    viewHolder = new ViewHolder();
                    convertView = inflater.inflate(R.layout.find_prize_title, parent, false);
                    viewHolder.title = (TextView) convertView.findViewById(R.id.tv_prizeTitle);
                    convertView.setTag(viewHolder);
                } else {
                    viewHolder = (ViewHolder) convertView.getTag();
                }
                viewHolder.title.setText((CharSequence) getItem(position));
                break;
            case TYPE_ITEM: //加载数据项目布局
                if (convertView == null) {
                    viewHolder = new ViewHolder();
                    convertView = inflater.inflate(R.layout.find_prize_item, parent, false);
                    viewHolder.tv1 = (TextView) convertView.findViewById(R.id.tv_prize1);
                    viewHolder.tv2 = (TextView) convertView.findViewById(R.id.tv_prize2);
                    convertView.setTag(viewHolder);
                } else {
                    viewHolder = (ViewHolder) convertView.getTag();
                }
                AllPrizeActivity.Data data = (AllPrizeActivity.Data) getItem(position);
                viewHolder.tv1.setText(data.getText1());
                viewHolder.tv2.setText(data.getText2());
                break;
        }

        return convertView;
    }

    /**
     *
     * @return 返回item类型数目
     */
    @Override
    public int getViewTypeCount() {
        return 2;
    }

    /**
     * 获取当前item的类型
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        int head = 0;
        for (AllPrizeActivity.Type type : mList) {
            int size = type.size();
            int current = position - head;
            if (current == 0) {
                return TYPE_HEADER;
            }
            head += size;
        }
        return TYPE_ITEM;
    }

    /**
     * 判断当前的item是否可以点击
     * @param position
     * @return
     */
    @Override
    public boolean isEnabled(int position) {
        return getItemViewType(position) != TYPE_HEADER;
    }
    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }
    private class ViewHolder {
        TextView tv1, tv2,title;
    }

}
