package com.example.machenike.mymovie.fragment.mine;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.machenike.mymovie.R;
import com.example.machenike.mymovie.activity.LoginActivity;
import com.example.machenike.mymovie.base.BaseFragment;

import butterknife.Bind;

/**
 * Created by Machenike on 2016/11/30.
 */
public class MineFragment extends BaseFragment {
    @Bind(R.id.ib_user_icon_avator)
    ImageButton ibUserIconAvator;
    @Bind(R.id.tv_username)
    TextView tvUsername;
    @Bind(R.id.mine_next)
    ImageView mineNext;
    @Bind(R.id.ll_wantsee)
    LinearLayout llWantsee;
    @Bind(R.id.ll_seen)
    LinearLayout llSeen;
    @Bind(R.id.ll_criticism)
    LinearLayout llCriticism;
    @Bind(R.id.topic)
    LinearLayout topic;
    @Bind(R.id.tv_all_order)
    TextView tvAllOrder;
    @Bind(R.id.tv_user_nouse)
    TextView tvUserNouse;
    @Bind(R.id.tv_user_pay)
    TextView tvUserPay;
    @Bind(R.id.tv_user_talk)
    TextView tvUserTalk;
    @Bind(R.id.tv_user_drawback)
    TextView tvUserDrawback;
    @Bind(R.id.tv_user_message)
    TextView tvUserMessage;
    @Bind(R.id.tv_user_collect)
    TextView tvUserCollect;
    @Bind(R.id.tv_user_coupon)
    TextView tvUserCoupon;
    @Bind(R.id.tv_user_score)
    TextView tvUserScore;
    @Bind(R.id.tv_user_prize)
    TextView tvUserPrize;
    @Bind(R.id.tv_user_ticket)
    TextView tvUserTicket;
    @Bind(R.id.tv_user_money)
    TextView tvUserMoney;
    @Bind(R.id.tv_user_invitation)
    TextView tvUserInvitation;
    @Bind(R.id.tv_user_callcenter)
    TextView tvUserCallcenter;
    @Bind(R.id.tv_user_shop)
    TextView tvUserShop;
    @Bind(R.id.tv_user_settings)
    TextView tvUserSettings;
    @Bind(R.id.scrollview)
    ScrollView scrollview;

    @Override
    protected void initData() {
        initListener();
    }

    private void initListener() {
        tvUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected int getLayoutID() {
        return R.layout.mine_fragment;
    }

    @Override
    protected void initView() {

    }

}
