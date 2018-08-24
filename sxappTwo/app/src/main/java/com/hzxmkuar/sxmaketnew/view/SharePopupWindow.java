package com.hzxmkuar.sxmaketnew.view;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.common.retrofit.entity.result.ShareBean;
import com.common.widget.toast.ToastManager;
import com.hzxmkuar.sxmaketnew.R;
import com.hzxmkuar.sxmaketnew.utils.PopAnimUtil;
import com.xmkj.payandlogin.ShareConfig;
import com.xmkj.payandlogin.ShareManager;
import com.xmkj.payandlogin.ShareUtil;
import com.xmkj.payandlogin.share.ShareListener;
import com.xmkj.payandlogin.share.SharePlatform;

/**
 * Created by Administrator on 2016/9/14.
 */
public class SharePopupWindow extends PopupWindow implements View.OnClickListener {

    public final View shareView;
    private Context mContext;
    private Object o;
    private View parent;
    private Animation dissmissAnimation;
    private LinearLayout ll_share_qq;
    private LinearLayout ll_share_weichat;
    private LinearLayout ll_share_wxcircle;
    private LinearLayout ll_share_wb;
    private LinearLayout ll_share_qqkj;
    private LinearLayout ll_share_copy;

    private String content = "";
    private String img = "";
    private String title ="";
    private String url = "";

    public SharePopupWindow(final Context mContext, View parent, ShareBean o) {
        super(mContext);
        this.mContext = mContext;
        this.parent = parent;
        this.o = o;
        shareView = View.inflate(mContext, R.layout.dialog_share_platform, null);
        initView(shareView);
        setAnimationStyle(R.style.CustomDialog);
        setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setFocusable(true);
        setOutsideTouchable(true);
        setTouchable(true);
        setBackgroundDrawable(new BitmapDrawable());
        setContentView(shareView);
        showAtLocation(parent, Gravity.CENTER | Gravity.BOTTOM, 0, 0);
        dissmissAnimation = AnimationUtils.loadAnimation(mContext, R.anim.pophidden_anim);
        PopAnimUtil.dopPpDissmissAinm(mContext, shareView, this);
    }
    private void initView(View shareView) {
        ShareConfig config = ShareConfig.instance()
                .qqId("1106405999")
                .wxId("wxca16b769e56e7fb5")
                .weiboRedirectUrl("e7bac93280ae624ff0e777b1551d18b5")
                .weiboScope("e7bac93280ae624ff0e777b1551d18b5")
                .weiboId("2273922718")
                .wxSecret("c57b3ffaa9013f0fc204feaf2a3d4161");
        ShareManager.init(config);
            ShareBean myTXBean = (ShareBean) o;
            if (myTXBean == null) {
                ToastManager.showShortToast("暂无分享");
                return;
            }
            content = myTXBean.getContent();
            img = myTXBean.getImg();
            title = myTXBean.getTitle();
            url = url.replace("\\","/");
            img = img.replace("\\","/");
            url = myTXBean.getUrl();
        //qq好友
        ll_share_qq = (LinearLayout) shareView.findViewById(R.id.ll_share_qq);
        //新浪
        ll_share_wb = (LinearLayout) shareView.findViewById(R.id.ll_share_wb);
        //微信好友
        ll_share_weichat = (LinearLayout) shareView.findViewById(R.id.ll_share_weichat);
        //朋友圈
        ll_share_wxcircle = (LinearLayout) shareView.findViewById(R.id.ll_share_wxcircle);
        //qq空间
        ll_share_qqkj = (LinearLayout) shareView.findViewById(R.id.ll_share_qqkj);
        //复制链接
        ll_share_copy = (LinearLayout) shareView.findViewById(R.id.ll_share_copy);
        initListener();
    }

    private void initListener() {
        ll_share_qq.setOnClickListener(this);
        ll_share_wb.setOnClickListener(this);
        ll_share_weichat.setOnClickListener(this);
        ll_share_wxcircle.setOnClickListener(this);
        ll_share_qqkj.setOnClickListener(this);
        ll_share_copy.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
            if (i == R.id.ll_share_qq) {
                ShareUtil.shareMedia(mContext, SharePlatform.QQ, title, content, url, img, new ShareListener() {
                    @Override
                    public void shareSuccess() {
                    }

                    @Override
                    public void shareFailure(Exception e) {
                    }

                    @Override
                    public void shareCancel() {

                    }
                });
            }else if (i == R.id.ll_share_weichat){
                ShareUtil.shareMedia(mContext, SharePlatform.WX, title, content, url, img, new ShareListener() {
                    @Override
                    public void shareSuccess() {
                    }
                    @Override
                    public void shareFailure(Exception e) {
                    }
                    @Override
                    public void shareCancel() {
                    }
                });
            }else if (i == R.id.ll_share_wxcircle){
                ShareUtil.shareMedia(mContext, SharePlatform.WX_TIMELINE, title, content, url, img, new ShareListener() {
                    @Override
                    public void shareSuccess() {
                    }
                    @Override
                    public void shareFailure(Exception e) {
                    }
                    @Override
                    public void shareCancel() {
                    }
                });
            }else if (i == R.id.ll_share_wb){
                ShareUtil.shareMedia(mContext, SharePlatform.WEIBO, title, content, url, img, new ShareListener() {
                    @Override
                    public void shareSuccess() {
                    }
                    @Override
                    public void shareFailure(Exception e) {
                    }
                    @Override
                    public void shareCancel() {
                    }
                });
            }else if (i == R.id.ll_share_qqkj){
                ShareUtil.shareMedia(mContext, SharePlatform.QZONE, title, content, url, img, new ShareListener() {
                    @Override
                    public void shareSuccess() {
                    }
                    @Override
                    public void shareFailure(Exception e) {
                    }
                    @Override
                    public void shareCancel() {
                    }
                });
            }else if (i == R.id.ll_share_copy){
                //获取剪贴板管理器：
                ClipboardManager cm = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                // 创建普通字符型ClipData
                ClipData mClipData = ClipData.newPlainText("Label", url);
                // 将ClipData内容放到系统剪贴板里。
                cm.setPrimaryClip(mClipData);
            }
        }
}
