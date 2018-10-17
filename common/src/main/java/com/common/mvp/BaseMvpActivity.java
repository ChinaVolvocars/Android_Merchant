package com.common.mvp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.R;
import com.common.listener.OnOnceClickListener;
import com.common.retrofit.entity.DataCenter;
import com.common.rxbus.RxBus;
import com.common.rxbus.RxManager;
import com.common.rxbus.postevent.RxKeyEvent;
import com.common.utils.ActivityStack;
import com.common.utils.EmptyUtils;
import com.common.utils.KeyBoardUtils;
import com.common.utils.ScreenUtils;
import com.common.utils.StringUtils;
import com.common.widget.dialog.CommonDialog;
import com.common.widget.imageview.image.ImageLoaderUtils;
import com.common.widget.loadingView.LoadingLayout;
import com.common.widget.navigation.NavigationBar;
import com.common.widget.navigation.WidgetButton;
import com.common.widget.progress.LoadDialog;
import com.common.widget.toast.ToastManager;

import org.greenrobot.eventbus.EventBus;

import rx.functions.Action1;

/**
 * @desc:         MVP模式
 * @author:       Leo
 * @date:         2016/10/26
 * @param <P>     扩展Presenter
 */
public abstract class BaseMvpActivity<P extends BasePresenter> extends AppCompatActivity implements BaseMView {

    protected Bundle savedInstanceState;
    protected RxManager rxManager;
    protected Context context;
    protected P presenter;
    protected LayoutInflater m_inflater;
    protected LoadingLayout m_contentView;
    protected LinearLayout m_root;
    protected LinearLayout m_navigation;
    private NavigationBar m_navigationBar;
    protected View m_statusBar;                  // 状态栏

    public NavigationBar getNavigationBar() {
        return m_navigationBar;
    }

    public final static int mPageSize = 5;
    protected int mPageIndex = 1;
    /**
     *   下拉刷新或加载更多<br/>
     *  0 是刷新 <br/>
     *  1 是加载更多 <br/>
     */
    protected int mIsRefreshOrLoadMore = 0;
    protected boolean mIsHasNoData;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(android.view.Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        context = this;
        if (EmptyUtils.isNotEmpty(getSupportActionBar())) {
            getSupportActionBar().hide();
        }
        this.savedInstanceState = savedInstanceState;
        super.setContentView(R.layout.activity_base);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        rxManager = new RxManager();

        // 设置状态栏透明
        hideStatusBar();
        initRootView();
        // 设置标题栏类型
        setNavigationType(true);
        setNavigation();
        setStatusBar();
        presenter = createPresenterInstance();
        if (null != presenter) {
            presenter.attachView(this, context);
        }

        View view = m_inflater.inflate(getLayoutId(), m_root, false);
        m_contentView.addView(view);
        ActivityStack.getInstance().addActivity(this);
        onViewCreated();
        observeIsLogin();
        doLogicFunc();
    }

    private int backRes;

    /**
     * 设置标题栏类型
     * @param isSub    true 为二级标题栏
     */
    protected void setNavigationType(boolean isSub) {
        // 设置状态栏字体颜色为白色
        ScreenUtils.setTextColorStatusBar((Activity) context, isSub);
        getNavigationBar().setNavigationType(isSub);
        backRes = isSub ? R.mipmap.allback : R.mipmap.allback;
        if (!isSub) {
            m_navigation.setBackgroundResource(R.mipmap.bg_navigation);
        } else {
            m_navigation.setBackgroundResource(R.color.white);
        }
    }

    private void observeIsLogin() {
        rxManager.add(RxBus.getDefault().toObservable(RxKeyEvent.class).subscribe(new Action1<RxKeyEvent>() {
            @Override
            public void call(RxKeyEvent rxKeyEvent) {
                if (EmptyUtils.isNotEventEmpty(rxKeyEvent, RxKeyEvent.FORCE_LOGOUT) && (boolean) rxKeyEvent.getValue()) {
                    CommonDialog dialog = newCommonDialog("强制下线\n账号在别处登录", false);
                    dialog.setFuncText("关闭");
                    dialog.setBtnFunc(new OnOnceClickListener() {
                        @Override
                        public void onOnceClick(View v) {
                            DataCenter.deleteLoginDataInfo();
                            ActivityStack.getInstance().finishAllActivity();
                        }
                    });
                    dialog.setDialogType(CommonDialog.TYPE.SURE);
                }
            }
        }));
    }

    /**  状态栏背景透明  */
    protected void hideStatusBar() {
        ScreenUtils.translateStatusBar((Activity) context);
        setTheme(R.style.TranslucentTheme);
    }

    /**  设置状态栏高度  */
    protected void setStatusBar() {
        m_statusBar.setVisibility(View.VISIBLE);
        ViewGroup.LayoutParams layoutParams = m_statusBar.getLayoutParams();
        layoutParams.height = ScreenUtils.getStatusHeight(context);
        m_statusBar.setLayoutParams(layoutParams);
    }

    protected View getStatesBar(){
        return m_statusBar;
    }

    /**  设置titleBar */
    protected void setNavigation() {
        m_navigationBar.setVisibility(View.GONE);
    }

    /**  设置返回键  */
    protected void setNavigationBack() {
        m_navigationBar.setVisibility(View.VISIBLE);
        WidgetButton btnBack = new WidgetButton(context);
        btnBack.setBackgroundResource(backRes);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        getNavigationBar().setLeftMenu(btnBack);
    }

    /**  设置返回键  */
    protected void setNavigationBack(View.OnClickListener listener) {
        m_navigationBar.setVisibility(View.VISIBLE);
        WidgetButton btnBack = new WidgetButton(context);
        btnBack.setBackgroundResource(backRes);
        btnBack.setOnClickListener(listener);
        getNavigationBar().setLeftMenu(btnBack);
    }

    /**
     * 设置右边功能键
     * @param titleRes    右侧标题
     */
    protected void setNavigationRight(int titleRes) {
        m_navigationBar.setVisibility(View.VISIBLE);
        WidgetButton btnRight = new WidgetButton(context, titleRes);
        getNavigationBar().setRightMenu(btnRight);
    }

    /**
     * 设置返回键
     * @param resId      返回图标资源
     * @param titleRes   标题名
     */
    protected void setNavigationBack(int resId, int titleRes) {
        m_navigationBar.setVisibility(View.VISIBLE);
        WidgetButton btnBack = new WidgetButton(context);
        btnBack.setBackgroundResource(resId);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        getNavigationBar().setLeftMenu(btnBack);

        m_navigationBar.setAppWidgeTitle(titleRes);
    }

    /**
     * 设置返回键
     * @param titleStr title名
     */
    protected void setNavigationBack(String titleStr) {
        setNavigationBack();
        m_navigationBar.setAppWidgeTitle(titleStr);
    }

    /**
     * 设置返回键
     * @param titleStr title名
     */
    protected void setNavigationBack(String titleStr, View.OnClickListener listener) {
        setNavigationBack(listener);
        m_navigationBar.setAppWidgeTitle(titleStr);
    }

    /**
     * 设置返回键
     * @param titleRes title名
     */
    protected void setNavigationBack(int titleRes) {
        setNavigationBack();
        m_navigationBar.setAppWidgeTitle(getResources().getString(titleRes));
    }

    /**
     *  注册EventBus
     */
    protected void registerEvent() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    /**
     *  取消注册EventBus
     */
    protected void unRegisterEvent() {
        if (EventBus.getDefault() != null) {
            EventBus.getDefault().unregister(this);
        }
    }
    private void initRootView() {
        m_inflater = LayoutInflater.from(this);
        m_root = (LinearLayout) this.findViewById(R.id.root);
        m_contentView = (LoadingLayout) this.findViewById(R.id.appContent);
        m_navigationBar = (NavigationBar) this.findViewById(R.id.navigationBar);
        m_navigation = (LinearLayout) this.findViewById(R.id.navi_layout);
        m_statusBar = this.findViewById(R.id.m_statusBar);
    }

    /**  初始化Presenter在setContentView之前  */
    protected abstract P createPresenterInstance();

    protected abstract int getLayoutId();

    /**  初始化界面控件  */
    protected abstract void onViewCreated();

    /**  执行逻辑操作  */
    protected abstract void doLogicFunc();

    protected Activity getActivity() {
        return this;
    }

    public void gotoActivity(Class<?> cls){
        if (null == cls) {  return;  }
        Intent intent = new Intent();
        intent.setClass(this, cls);
        startActivity(intent);
    }

    @Override
    public void startActivity(Intent intent) {
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        super.startActivity(intent);
    }

    @Override
    public void showToastMsg(String msg) {
        ToastManager.showShortToast(msg);
    }

    public void showToastMsg(String msg, int res) {
        ToastManager.showShortToast(msg, res);
    }

    protected LoadDialog loadDialog;
    protected String loadText;//加载中提示文字
    protected boolean loadCancelable=true;

    @Override
    public void showProgressingDialog() {
        loadText=getResources().getString(R.string.string_loadText);
        loadDialog = new LoadDialog(context,loadText, loadCancelable);
        loadDialog.show();
    }

    @Override
    public void dismissProgressDialog() {
        if (null != loadDialog) {
            loadDialog.dismiss();
        }
    }

    /**
     * 创建通用对话框
     * @param text              提示文本
     * @param outSideCancel     点击窗外是否取消<默认<true>
     * @return
     */
    public CommonDialog newCommonDialog(String text, Boolean outSideCancel) {
        CommonDialog dialog = CommonDialog.newInstance(context);
        dialog.setText(text);
        dialog.setCanceledOnTouchOutside(outSideCancel);
        dialog.show(getSupportFragmentManager(), "CommonDialog");
        return dialog;
    }

    public CommonDialog newCommonDialog(String text) {
        return newCommonDialog(text, true);
    }

    @Override
    public void statusError() {
        m_contentView.setStatus(LoadingLayout.Status.Error);
    }

    @Override
    public void statusLoading() {
        m_contentView.setStatus(LoadingLayout.Status.Loading);
    }

    @Override
    public void statusNoNetwork() {
        m_contentView.setStatus(LoadingLayout.Status.No_Network);
    }

    @Override
    public void statusEmpty() {
        m_contentView.setStatus(LoadingLayout.Status.Empty);
    }

    @Override
    public void statusContent() {
        m_contentView.setStatus(LoadingLayout.Status.Success);
    }

    /**
     * 填充文本兼容空对象
     * @param textView    textview
     * @param content     content
     */
    public void setTextView(TextView textView, String content){
        if (EmptyUtils.isNull(textView)) {  return; }
        textView.setText(StringUtils.nullToStr(content));
    }

    /**
     * 填充文本兼容空对象
     * @param textView    textview
     * @param content     content
     */
    public void setTextViewVisibe(TextView textView, String content){
        if (EmptyUtils.isNull(textView)) { return;  }

        if (EmptyUtils.isEmpty(content)) {
            textView.setVisibility(View.GONE);
        } else {
            textView.setVisibility(View.VISIBLE);
        }
        textView.setText(StringUtils.nullToStr(content));
    }

    /**
     * 填充图片兼容空对象
     * @param imageview    imageview
     * @param url          content
     */
    public void setImageView(ImageView imageview, String url){
        if (EmptyUtils.isNull(imageview)) {  return; }

        ImageLoaderUtils.display(imageview, StringUtils.nullToStr(url));
    }

    @Override
    public void statusReTry(LoadingLayout.OnReloadListener listener){
        if (EmptyUtils.isNull(listener)) {   return;   }

        m_contentView.setOnReloadListener(listener);
    }

    /**
     * 界面返回时主动释放内存
     */
    @Override
    public void onBackPressed() {
        KeyBoardUtils.hideKeyBoard(m_root);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        clearMemory();
        super.onBackPressed();
    }

    @Override
    public void finish() {
        KeyBoardUtils.hideKeyBoard(m_root);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        super.finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    public void clearMemory(){
        if (presenter != null) {
            presenter.detachView();
        }

        if (null != rxManager) {
            rxManager.clear();
        }
        // 清除图片缓存
        ImageLoaderUtils.cleanMemory(context);
        // 清除栈
        ActivityStack.getInstance().finishActivity(this);
    }
    /**
     * 获取编辑框文本
     * @param editText
     * @return      编辑框中文本
     */
    protected String getEditTextStr(EditText editText) {
        if (EmptyUtils.isNotNull(editText)) {
            return editText.getText().toString();
        }
        return "";
    }

    /**
     * 获取编辑框文本
     * @param textView
     * @return      编辑框中文本
     */
    protected String getTextViewStr(TextView textView) {
        if (EmptyUtils.isNotNull(textView)) {
            return textView.getText().toString();
        }
        return "";
    }

    /**
     * 为控件添加单次点击事件
     * @param view
     */
    protected void attachClickListener(View view) {
        if (view != null) {
            view.setOnClickListener(clickListener);
        }
    }

    private OnOnceClickListener clickListener = new OnOnceClickListener() {
        @Override
        public void onOnceClick(View v) {
            onViewClicked(v);
        }
    };

    protected void onViewClicked(View view) {
    }

    @Override
    protected void onDestroy() {
        unRegisterEvent();
        clearMemory();
        super.onDestroy();
    }

    /**
     * 点击空白区域隐藏键盘
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (KeyBoardUtils.isShouldHideKeyboard(v, ev)) {
                KeyBoardUtils.hideSoftInput((Activity) context);
            }
        }
        return super.dispatchTouchEvent(ev);
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent event) {
//        // TODO Auto-generated method stub
//        if(event.getAction() == MotionEvent.ACTION_DOWN){
//            if (KeyBoardUtils.isShouldHideKeyboard(getCurrentFocus(), event)) {
////                KeyBoardUtils.hideSoftInput((Activity) context);
//                InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
//            }
//            KeyBoardUtils.hideSoftInput((Activity) context);
//        }
//        return super.dispatchTouchEvent(event);
//    }
}