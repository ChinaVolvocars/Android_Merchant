//package com.hzxmkuar.sxmaketnew.common;
//
//import android.annotation.TargetApi;
//import android.app.Activity;
//import android.content.ClipData;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Build;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.util.Log;
//import android.view.View;
//import android.webkit.ValueCallback;
//import android.webkit.WebChromeClient;
//import android.webkit.WebSettings;
//import android.webkit.WebView;
//import android.webkit.WebViewClient;
//import android.widget.LinearLayout;
//
///**
// * Created by Administrator on 2017/6/6.
// */
//
//public class FristApproveActivity extends Activity {
//
//    private static int FILECHOOSER_RESULTCODE = 100;
//    LinearLayout llFinish;
//    WebView webview;
//    private String uid;
//    private String hashId;
//    private ValueCallback<Uri> mUploadMessage;
//    private ValueCallback<Uri[]> mUploadCallbackAboveL;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        initView();
//        initEvent();
//
//    }
//
//    private void initEvent() {
//        llFinish.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//
//    }
//
//    private void initView() {
//        WebSettings settings = webview.getSettings();
//        settings.setJavaScriptEnabled(true);
//        webview.loadUrl("http://xmzb1703053.php.hzxmnet.com/web/auth.html?uid=" + uid + "&hashid=" + hashId);
//        webview.setWebViewClient(new WebViewClient(){
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return true;
//            }
//        });
//
//        webview.setWebChromeClient(new WebChromeClient() {
//
//            public void openFileChooser(ValueCallback<Uri> uploadMsg) {
//                Log.d("hahahh", "openFileChoose(ValueCallback<Uri> uploadMsg)");
//                mUploadMessage = uploadMsg;
//                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
//                i.addCategory(Intent.CATEGORY_OPENABLE);
//                i.setType("*/*");
//                FristApproveActivity.this.startActivityForResult(Intent.createChooser(i, "File Chooser"), FILECHOOSER_RESULTCODE);
//            }
//
//            public void openFileChooser(ValueCallback uploadMsg, String acceptType) {
//                Log.d("hahaha", "openFileChoose( ValueCallback uploadMsg, String acceptType )");
//                mUploadMessage = uploadMsg;
//                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
//                i.addCategory(Intent.CATEGORY_OPENABLE);
//                i.setType("*/*");
//                FristApproveActivity.this.startActivityForResult(
//                        Intent.createChooser(i, "File Browser"),
//                        FILECHOOSER_RESULTCODE);
//            }
//
//            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
//                Log.d("hahhaha", "openFileChoose(ValueCallback<Uri> uploadMsg, String acceptType, String capture)");
//                mUploadMessage = uploadMsg;
//                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
//                i.addCategory(Intent.CATEGORY_OPENABLE);
//                i.setType("*/*");
//                FristApproveActivity.this.startActivityForResult(Intent.createChooser(i, "File Browser"), FristApproveActivity.FILECHOOSER_RESULTCODE);
//            }
//
//            //5.0
//            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
//                mUploadCallbackAboveL = filePathCallback;
//                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
//                i.addCategory(Intent.CATEGORY_OPENABLE);
//                i.setType("*/*");
//                FristApproveActivity.this.startActivityForResult(
//                        Intent.createChooser(i, "File Browser"),
//                        FILECHOOSER_RESULTCODE);
//                return true;
//            }
//
//        });
//
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == FILECHOOSER_RESULTCODE) {
//            if (null == mUploadMessage && null == mUploadCallbackAboveL) return;
//            Uri result = data == null || resultCode != RESULT_OK ? null : data.getData();
//            if (mUploadCallbackAboveL != null) {
//                onActivityResultAboveL(requestCode, resultCode, data);
//            } else if (mUploadMessage != null) {
//                mUploadMessage.onReceiveValue(result);
//                mUploadMessage = null;
//            }
//        }
//    }
//
//    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//    private void onActivityResultAboveL(int requestCode, int resultCode, Intent data) {
//        if (requestCode != FILECHOOSER_RESULTCODE
//                || mUploadCallbackAboveL == null) {
//            return;
//        }
//        Uri[] results = null;
//        if (resultCode == Activity.RESULT_OK) {
//            if (data == null) {
//            } else {
//                String dataString = data.getDataString();
//                ClipData clipData = data.getClipData();
//                if (clipData != null) {
//                    results = new Uri[clipData.getItemCount()];
//                    for (int i = 0; i < clipData.getItemCount(); i++) {
//                        ClipData.Item item = clipData.getItemAt(i);
//                        results[i] = item.getUri();
//                    }
//                }
//                if (dataString != null)
//                    results = new Uri[]{Uri.parse(dataString)};
//            }
//        }
//        mUploadCallbackAboveL.onReceiveValue(results);
//        mUploadCallbackAboveL = null;
//        return;
//    }
//}