package com.example.machenike.mymovie.fragment;

import android.animation.ObjectAnimator;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.machenike.mymovie.R;
import com.example.machenike.mymovie.base.BaseFragment;
import com.example.machenike.mymovie.utils.DensityUtil;

import butterknife.Bind;

/**
 * Created by Machenike on 2016/12/3.
 */
public class AccountLoginFragment extends BaseFragment {
    @Bind(R.id.et_number)
    EditText etNumber;
    @Bind(R.id.et_password)
    EditText etPassword;
    @Bind(R.id.iv_eyes)
    ImageView ivEyes;
    @Bind(R.id.bt_login)
    Button btLogin;
    @Bind(R.id.tv_forget)
    TextView tvForget;
    @Bind(R.id.iv_up_down)
    ImageView ivUpDown;
    @Bind(R.id.ll_up_down)
    LinearLayout llUpDown;
    @Bind(R.id.fl_xl)
    FrameLayout flXl;
    @Bind(R.id.fl_wx)
    FrameLayout flWx;
    @Bind(R.id.fl_qq)
    FrameLayout flQq;
    @Bind(R.id.fl_qz)
    FrameLayout flQz;
    boolean flag = true;
    boolean flag1 = true;

    @Override
    protected void initData() {
        initListener();

    }

    private void initListener() {
        ivEyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "eyes", Toast.LENGTH_SHORT).show();
                if (flag) {
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    ivEyes.setImageResource(R.drawable.psd_hide);

                } else {
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    ivEyes.setImageResource(R.drawable.psd_show);
                }
                flag = !flag;
            }
        });

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "login", Toast.LENGTH_SHORT).show();
            }
        });

        tvForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "forget", Toast.LENGTH_SHORT).show();
            }
        });
        ivUpDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (flag1) {
                    ivUpDown.setImageResource(R.drawable.up);
                    ObjectAnimator animator = ObjectAnimator.ofFloat(llUpDown,"translationY", DensityUtil.dip2px(mContext,110));
                    animator.setDuration(500);
                    animator.start();
                } else {
                    ivUpDown.setImageResource(R.drawable.down);
                    ObjectAnimator animator = ObjectAnimator.ofFloat(llUpDown,"translationY",DensityUtil.dip2px(mContext,-20));
                    animator.setDuration(500);
                    animator.start();
                }
                flag1 = !flag1;
            }
        });
    }

    private void startDownAnimation() {
        AnimationSet animationSet  = new AnimationSet(true);
        TranslateAnimation translateAnimation = new TranslateAnimation( Animation.RELATIVE_TO_SELF, 0.0f,Animation.RELATIVE_TO_SELF,0.0f,
                Animation.RELATIVE_TO_SELF,0.0f,Animation.RELATIVE_TO_SELF,1.5f);
        translateAnimation.setFillAfter(true);
        translateAnimation.start();
        //将AlphaAnimation这个已经设置好的动画添加到 AnimationSet中
        animationSet.addAnimation(translateAnimation);
        //启动动画
        llUpDown.startAnimation(animationSet);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.login_fragment;
    }

    @Override
    protected void initView() {

    }

}
