package com.hzxmkuar.sxmaketnew.common;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.common.listener.OnOnceClickListener;
import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.common.widget.navigation.WidgetButton;
import com.hzxmkuar.sxmaketnew.R;
import com.just.library.AgentWeb;
import com.just.library.ChromeClientCallbackManager;

public class BaseUrlActivity extends BaseMvpActivity {
    private static final String TAG = "BaseUrlActivity";
    protected AgentWeb mAgentWeb;
    private LinearLayout mLinearLayout;
    private WidgetButton[] widgeButtons;
    private WidgetButton btnBack;
    private WidgetButton btnClose;

    public static final String MAIN_URL = "MAIN_URL";
    public static final String MAIN_TITLE = "MAIN_TITLE";
    private String webViewUrl;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_base_url;
    }

    @Override
    protected void setNavigation() {
        btnBack = new WidgetButton(context);
        btnClose = new WidgetButton(context);
        btnClose.setBackgroundResource(R.mipmap.allback);
        widgeButtons = new WidgetButton[]{btnBack, btnClose};

        btnClose.setOnClickListener(new OnOnceClickListener() {
            @Override
            public void onOnceClick(View v) {
                finish();
            }
        });
        btnBack.setOnClickListener(new OnOnceClickListener() {
            @Override
            public void onOnceClick(View v) {
                if (!mAgentWeb.back()) {
                    finish();
                }
            }
        });
        getNavigationBar().setLeftMenus(widgeButtons);
        webViewUrl = getIntent().getStringExtra(MAIN_URL);
    }

    @Override
    protected void onViewCreated() {
        mLinearLayout = (LinearLayout) findViewById(R.id.web_container);
    }

    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @Override
    protected void doLogicFunc() {
         /* webview加载进度条 */
        mAgentWeb = AgentWeb.with(this)//
                .setAgentWebParent(mLinearLayout, new LinearLayout.LayoutParams(-1, -1))//
                .useDefaultIndicator()
                .defaultProgressBarColor()

                .setReceivedTitleCallback(new ChromeClientCallbackManager.ReceivedTitleCallback() {
                    @Override
                    public void onReceivedTitle(WebView view, String title) {
                        if ("迎新礼".equals(getIntent().getStringExtra(MAIN_TITLE))) {
                            Log.i(TAG, "onReceivedTitle:    getIntent().getStringExtra(MAIN_TITLE):     " + getIntent().getStringExtra(MAIN_TITLE));
                            getNavigationBar().setAppWidgeTitle(getIntent().getStringExtra(MAIN_TITLE));
                        } else {
                            getNavigationBar().setAppWidgeTitle(title);
                        }

                    }
                })
                .setSecutityType(AgentWeb.SecurityType.strict)
                .createAgentWeb()
                .ready()
                .go(webViewUrl);

        if (mAgentWeb != null) {
            mAgentWeb.getJsInterfaceHolder().addJavaObject("android", new AndroidInterface(mAgentWeb, this.getActivity()));
        }
    }

    public class AndroidInterface {

        private AgentWeb agent;

        public AndroidInterface(AgentWeb agent, Context context) {
            this.agent = agent;
        }

        @JavascriptInterface
        public void finish() {
            BaseUrlActivity.this.finish();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (mAgentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mAgentWeb.uploadFileResult(requestCode, resultCode, data);
    }
}