package com.example.machenike.mymovie.fragment.moviefragment.find;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.machenike.mymovie.R;
import com.example.machenike.mymovie.base.BaseActivity;
import com.example.machenike.mymovie.bean.AllPrizeBean;
import com.example.machenike.mymovie.utils.Constant;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import okhttp3.Call;

public class AllPrizeActivity extends BaseActivity {

    @Bind(R.id.iv_quxiao)
    ImageView ivQuxiao;
    @Bind(R.id.listview)
    ListView listview;
    private Context mContext;
    private List<AllPrizeBean.DataBean> databean;
    List<Type> list = new ArrayList<>();
    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        getDataFromNet();




    }

    @Override
    protected int initLayout() {
        return R.layout.activity_all_prize;
    }

    public void getDataFromNet() {
        OkHttpUtils.get()
                .url(Constant.findAllPrize)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG","AllPrizeActivity联网失败");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        porcessData(response);
                    }
                });
    }

    private void porcessData(String response) {
        AllPrizeBean allPrizeBean = new Gson().fromJson(response, AllPrizeBean.class);
        databean = allPrizeBean.getData();
        for (int i = 0; i < databean.size();i++){
            Type type = new Type(databean.get(i).getRegion());
            for (int j = 0;j<databean.get(i).getFestivals().size()-1;j+=2){
                Data data = new Data(databean.get(i).getFestivals().get(j).getFestivalName(),
                        databean.get(i).getFestivals().get(j+1).getFestivalName());
                type.addItem(data);

            }
            list.add(type);

        }
        listview.setAdapter(new MyAllPrizeAdapter(this,list));
    }


    //数据模型
    public class Data {
        private String text1, text2;//数据1 2

        public Data(String text1, String text2) {
            this.text1 = text1;
            this.text2 = text2;
        }
        public String getText1() {
            return text1;
        }
        public String getText2() {
            return text2;
        }
    }

    //数据分类
    public class Type {
        private String title;  //ListView头部显示的标题
        private List<Data> mList; //头部对应的内容集合

        public Type(String title) {
            this.title = title;
            mList = new ArrayList<>();
        }

        // 添加项目
        public void addItem(Data data) {
            mList.add(data);
        }

        // 获取项目
        public Object getItem(int position) {
            if (position == 0) {
                return title;
            } else {
                return mList.get(position - 1);
            }
        }

        // return item数目，为集合大小+1
        public int size() {
            return mList.size() + 1;
        }
    }
}
