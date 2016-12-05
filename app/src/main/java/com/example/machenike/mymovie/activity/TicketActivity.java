package com.example.machenike.mymovie.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.machenike.mymovie.R;
import com.example.machenike.mymovie.utils.Constant;

public class TicketActivity extends Activity {
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        Intent intent = getIntent();
        int ticket = intent.getIntExtra("TICKET", -1);
        webView = (WebView)findViewById(R.id.webview);
        webView.loadUrl(Constant.ticket + ticket);
        WebSettings webSettings  = webView.getSettings();
        webSettings.setBuiltInZoomControls(true);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
               view.loadUrl(url);
                return true;
            }
        });

    }
}
