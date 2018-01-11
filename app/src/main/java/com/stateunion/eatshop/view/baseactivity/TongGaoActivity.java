package com.stateunion.eatshop.view.baseactivity;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import com.stateunion.eatshop.R;

/**
 * Created by 青春 on 2018/1/2.
 */

public class TongGaoActivity extends BaseActivity{
    private ImageView iv_tonggao_back;
    private WebView web;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        intview();
    }
    public void intview(){
        web = (WebView) findViewById(R.id.web_tonggao);
        web.getSettings().setSupportMultipleWindows(true);
        web.getSettings().setAllowFileAccess(true); //启用或禁止WebView访问文件数据
        web.getSettings().setBuiltInZoomControls(true);// 设置是否支持缩放
        web.getSettings().setDefaultTextEncodingName("utf-8");// 设置在解码时使用的默认编码
        web.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);// 设置布局方式
        web.getSettings().setSupportZoom(true);// 设置是否支持变焦
        WebSettings webSettings = web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowContentAccess(true);
        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        //缩放操作
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件
        //其他细节操作
//            webSettings.setCacheMode( webSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        web.loadUrl("http://ceshi123.dns178.com/home/index/tonggaolist");
        web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
               view.loadUrl(url);
                return true;
            }
        });

        iv_tonggao_back= (ImageView) findViewById(R.id.iv_tonggao_back);
        iv_tonggao_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TongGaoActivity.this.finish();
            }
        });
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
