package com.example.machenike.mymovie.fragment;

import android.animation.ObjectAnimator;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
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
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

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
        flXl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMShareAPI mShareAPI = UMShareAPI.get(getActivity());
                mShareAPI.doOauthVerify(getActivity(), SHARE_MEDIA.SINA, umAuthListener);
            }
        });
        flWx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMShareAPI mShareAPI = UMShareAPI.get(getActivity());
                mShareAPI.doOauthVerify(getActivity(), SHARE_MEDIA.WEIXIN, umAuthListener);
            }
        });

        flQq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMShareAPI mShareAPI = UMShareAPI.get(getActivity());
                mShareAPI.doOauthVerify(getActivity(), SHARE_MEDIA.QQ, umAuthListener);
            }
        });
        flQz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"哥哥应该是百度。没找到百度的图片，qq登录和空间登录有什么不一样吗？点前面一个不就行了吗，**",Toast.LENGTH_SHORT).show();
            }
        });
    }

   private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Toast.makeText(mContext, "Authorize succeed", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText( mContext, "Authorize fail", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(mContext, "Authorize cancel", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected int getLayoutID() {
        return R.layout.login_fragment;
    }

    @Override
    protected void initView() {

    }

}
