package com.hzxmkuar.sxmaketnew.newversion;


import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.hzxmkuar.sxmaketnew.R;

import butterknife.BindView;
import butterknife.OnClick;

public class WebViewActivity extends BaseMvpActivity {
    public final static String URL = "url";
    public final static String TITLE = "title";

    @BindView(R.id.pb)
    ProgressBar pb;
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.t_name)
    TextView tvName;

    @OnClick(R.id.back)
    public void onFinishClicked() {
        onBackPressed();
    }

    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.web_activity;
    }

    @Override
    protected void onViewCreated() {
        String url = getIntent().getStringExtra(URL);
        String title = getIntent().getStringExtra(TITLE);
        tvName.setText(title);

        pb.setMax(100);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                pb.setProgress(newProgress);
                if (newProgress >= 100) {
                    pb.setVisibility(View.GONE);
                }
            }
        });
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl(url);
    }

    @Override
    protected void doLogicFunc() {

    }


    public static void runWebActivity(Context context, String title, String url) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(URL, url);
        intent.putExtra(TITLE, title);
        context.startActivity(intent);
    }


}
