package com.common.widget.webview;

import android.content.Context;
import android.net.http.SslError;
import android.os.Build;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.common.utils.EmptyUtils;

/**
 * Webview的相关设置
 * 林天训
 */
public class WebViewManager {
    private static WebViewManager sWebViewManger;
    private Context mContext;

    private WebViewManager(Context mContext) {
        this.mContext = mContext;
    }

    public static WebViewManager getInstance(Context mContext) {
        if (null == sWebViewManger) {
            sWebViewManger = new WebViewManager(mContext);
        }
        return sWebViewManger;
    }

    /**
     * webView 相关设置 以及cookie同步
     *
     * @param mWebView     webview
     * @param url          链接
     * @param mProgressBar 进度条
     */
    public void setWebViewSetting(WebView mWebView, String url, final ProgressBar mProgressBar) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // chromium, enable hardware acceleration
            mWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else {
            // older android version, disable hardware acceleration
            mWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

         /*webview加载进度条*/
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setDomStorageEnabled(true);//支持domstorage 若不加会加载不出某些html
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (EmptyUtils.isNotEmpty(mProgressBar)) {
                    if (newProgress == 100) {
                        mProgressBar.setVisibility(View.GONE);
                    } else {
                        mProgressBar.setProgress(newProgress);
                        if (mProgressBar.getVisibility() == View.GONE) {
                            mProgressBar.setVisibility(View.VISIBLE);
                        }
                    }
                }
                super.onProgressChanged(view, newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }
        });

        mWebView.setWebViewClient(new WebViewClient() {
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }
        });
        mWebView.clearCache(true);
        mWebView.clearHistory();
        mWebView.loadUrl(url);
    }
}
