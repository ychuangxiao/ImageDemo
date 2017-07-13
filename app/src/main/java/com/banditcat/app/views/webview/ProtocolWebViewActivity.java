package com.banditcat.app.views.webview;

import android.os.Bundle;
import android.webkit.WebView;

import com.banditcat.app.R;
import com.banditcat.app.constant.AppConstant;
import com.banditcat.app.views.base.BaseActivity;
import com.ilogie.android.library.common.util.StringUtils;

import butterknife.BindView;

public class ProtocolWebViewActivity extends BaseActivity {


    @BindView(R.id.webView)
    WebView mWebView;//浏览器

    String webSiteUrl;


    /**
     * 初始化视图，工具条等信息
     */
    @Override
    public void initView() {

        injectExtras();

        setToolTitle(getString(R.string.title_activity_protocol_web_view)).setDisplayHome(true)
                .setHomeOnClickListener();

        mWebView.loadUrl(webSiteUrl);
        mWebView.getSettings().setJavaScriptEnabled(true);
    }

    /**
     * 获得布局视图ID
     *
     * @return 视图ID
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_protocol_web_view;
    }


    private void injectExtras() {
        Bundle extras_ = getIntent().getExtras();
        if (extras_ == null) {

            finish();
            return;
        }

        if (!extras_.containsKey(AppConstant.EXTRA_NO)) {
            finish();
            return;

        }

        webSiteUrl = extras_.getString(AppConstant.EXTRA_NO);

        if (StringUtils.isEmpty(webSiteUrl)) {
            finish();
            return;
        }


    }

}
