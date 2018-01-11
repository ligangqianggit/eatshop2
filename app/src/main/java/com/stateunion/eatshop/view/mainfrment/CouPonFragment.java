package com.stateunion.eatshop.view.mainfrment;

import android.content.Context;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.stateunion.eatshop.R;
import com.stateunion.eatshop.view.basefrment.BaseFragment;

/**
 * Created by admini on 2017/11/28.
 */

public class CouPonFragment  extends BaseFragment {
    private WebView web;
    private Context context=null;
    @Override
    public int getLayoutId() {
        return R.layout.frag_fuli;
    }

    @Override
    public void createView(View rootView) {
        context=rootView.getContext();
        intview(rootView);
    }
    public void intview(View view){
        web= (WebView) view.findViewById(R.id.web_fuli);
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
    }

    @Override
    public void refreshData(View rootView) {

    }
}
