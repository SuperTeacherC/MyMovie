package com.example.machenike.mymovie.fragment.moviefragment.wait;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.machenike.mymovie.R;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import butterknife.Bind;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

public class JCPlayActivity extends AppCompatActivity {
    @Bind(R.id.iv_share)
    ImageView ivShare;
    @Bind(R.id.iv_out)
    ImageView ivOut;

    private JCVideoPlayer jc_videoplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 去掉窗口标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 隐藏顶部的状态栏
        setContentView(R.layout.activity_jcplay);
        ButterKnife.bind(this);

        jc_videoplayer = (JCVideoPlayer) findViewById(R.id.jc_videoplayer);
        String mp4 = getIntent().getStringExtra("MP4");
        String title = getIntent().getStringExtra("TITLE");
        String image = getIntent().getStringExtra("IMAGE");

        jc_videoplayer.setUp(mp4, image, title, false);

        initListener();
    }

    private void initListener() {
        ivShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ShareAction(JCPlayActivity.this).withText("hello")
                        .setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN)
                        .setCallback(umShareListener).open();
            }
        });

        ivOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat","platform"+platform);

            Toast.makeText(JCPlayActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(JCPlayActivity.this,platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if(t!=null){
                Log.d("throw","throw:"+t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(JCPlayActivity.this,platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };

}
