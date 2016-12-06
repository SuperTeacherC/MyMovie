package com.example.machenike.mymovie.fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.machenike.mymovie.R;
import com.example.machenike.mymovie.base.BaseFragment;

import butterknife.Bind;

/**
 * Created by Machenike on 2016/12/3.
 */
public class PhoneRegisterFragment extends BaseFragment {
    @Bind(R.id.et_phonenumber)
    EditText etPhonenumber;
    @Bind(R.id.iv_quxiao)
    ImageView ivQuxiao;
    @Bind(R.id.tv_number_null)
    TextView tvNumberNull;
    @Bind(R.id.tv_number_have)
    TextView tvNumberHave;
    @Bind(R.id.et_yanzhengma)
    EditText etYanzhengma;
    @Bind(R.id.bt_login)
    Button btLogin;
    private Object listner;

    @Override
    protected void initData() {
        getListner();

    }

    @Override
    protected int getLayoutID() {
        return R.layout.register_fragment;
    }

    @Override
    protected void initView() {

    }


    public void getListner() {
        etPhonenumber.addTextChangedListener(textWatcher);

        ivQuxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etPhonenumber.setText("");
            }
        });

    }

    public void getShow() {
        int length = etPhonenumber.getText().toString().length();
        Log.e("TAG", "length-------" + length);
        if (length == 11) {
            tvNumberNull.setVisibility(View.GONE);
            tvNumberHave.setVisibility(View.VISIBLE);
        } else {
            tvNumberNull.setVisibility(View.VISIBLE);
            tvNumberHave.setVisibility(View.GONE);
        }
        if (length > 0) {
            ivQuxiao.setVisibility(View.VISIBLE);
        } else {
            ivQuxiao.setVisibility(View.GONE);
        }
    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            getShow();
        }

        @Override
        public void afterTextChanged(Editable s) {
            getShow();
        }
    };

}
