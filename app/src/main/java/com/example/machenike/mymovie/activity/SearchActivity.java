package com.example.machenike.mymovie.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.machenike.mymovie.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchActivity extends Activity {
    @Bind(R.id.et_search)
    EditText etSearch;
    @Bind(R.id.iv_search_clear)
    ImageView ivSearchClear;
    @Bind(R.id.tv_quxiao)
    TextView tvQuxiao;
    @Bind(R.id.listview)
    ListView listview;
    ArrayList<Map<String, Object>> mData = new ArrayList<>();
    ArrayList<String> mListTitle = new ArrayList<>();
    ArrayList<String> mListText = new ArrayList<>();
    private String  eChanged = "eChanged";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        initListener();

    }

        public void getmData(ArrayList<Map<String, Object>> mDatas)
        {
            Map<String, Object> item = new HashMap<String, Object>();
            mListTitle.add("This is a title!");
            mListText.add("this is a text.2014.09.18.16.33");

            item.put("title", mListTitle.get(0));
            item.put("text",mListText.get(0));
            mDatas.add(item);
            mListTitle.add("his is an another title!");
            mListText.add("this is an another text.2014.09.18.16.33");

            item = new HashMap<String, Object>();
            item.put("title", mListTitle.get(1));
            item.put("text", mListText.get(1));
            mDatas.add(item);
        }
    private void set_mListView_adapter()
    {
        getmData(mData);

        SimpleAdapter adapter = new SimpleAdapter(this, mData, android.R.layout.simple_list_item_2,
                new String[]{"title", "text"}, new int[]{android.R.id.text1, android.R.id.text2});

        listview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    Handler myhandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            return false;
        }
    });

    private void initListener() {
        tvQuxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ivSearchClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etSearch.setText("");
            }
        });

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etSearch.getText().length()==0) {
                    ivSearchClear.setVisibility(View.GONE);
                } else {

                    myhandler.post(new Runnable() {
                        @Override
                        public void run() {
                            set_mListView_adapter();
                        }
                    });
                    ivSearchClear.setVisibility(View.VISIBLE);
                }
            }
        });
    }


    
}
