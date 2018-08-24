package com.common.mvp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.R;
import com.common.listener.OnOnceClickListener;
import com.common.rxbus.RxManager;
import com.common.utils.EmptyUtils;
import com.common.utils.ScreenUtils;
import com.common.utils.StringUtils;
import com.common.utils.ViewUtils;
import com.common.widget.dialog.CommonDialog;
import com.common.widget.imageview.image.ImageLoaderUtils;
import com.common.widget.loadingView.LoadingLayout;
import com.common.widget.navigation.NavigationBar;
import com.common.widget.progress.LoadDialog;
import com.common.widget.toast.ToastManager;

/**
 * @desc:         MVP模式
 * @author:       Leo
 * @date:         2016/10/26
 * @param <P>     扩展Presenter
 */
public abstract class BaseMvpFragment<P extends BasePresenter>
        extends Fragment implements BaseMView {

    protected P presenter;
    protected Context context;
    protected RxManager rxManager;

    private NavigationBar m_navigationBar;
    private LoadingLayout m_contentView;
    private View m_statusBar;                  // 状态栏

    /**
     * 缓存Fragment view
     * 避免多个Fragment切换时UI重绘
     */
    protected View contentView = null;

    public final static int mPageSize = 5;
    protected int mPageIndex = 1;
    protected int mIsRefreshOrLoadMore = 0; // 0是刷新1是加载更多
    protected boolean mIsHasNoData;         // 是否有更多数据
    private boolean isVisible;

    @SuppressWarnings("unchecked")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getActivity();
        rxManager = new RxManager();

        presenter = createPresenterInstance();
        if (presenter != null) {
            presenter.attachView(this, context);
        }
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if(getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }


    /**
     * 可见
     */
    protected void onVisible() {
        lazyLoad();
    }


    /**
     * 不可见
     */
    protected void onInvisible() {


    }


    /**
     * 延迟加载
     * 子类必须重写此方法
     */
    protected void lazyLoad(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        context = getActivity();

        if (contentView == null) {
            contentView = inflater.inflate(R.layout.activity_base, container, false);
            initRootView();
//            setStatusBar();
            setNavigation();

            View view = inflater.inflate(getLayoutId(), container, false);
            m_contentView.addView(view);

            onViewCreated(view);
            doLogicFunc();
        }

        // 缓存的mView需要判断是否已经被加过parent，如果有parent需要从parent删除，要不然会发生这个mView已经有parent的错误。
        ViewGroup parent = (ViewGroup) contentView.getParent();
        if (parent != null)
        {
            parent.removeView(contentView);
        }
        return contentView;
    }

    /**  设置状态栏高度  */
    protected void setStatusBarS() {
        m_statusBar.setVisibility(View.VISIBLE);
        ViewGroup.LayoutParams layoutParams = m_statusBar.getLayoutParams();
        layoutParams.height = ScreenUtils.getStatusHeight(context);
        m_statusBar.setLayoutParams(layoutParams);
        m_statusBar.setBackgroundResource(R.color.white);
    }

    public View getStatusBar() {
        return m_statusBar;
    }

    protected void setNavigation() {
        m_navigationBar.setVisibility(View.GONE);
    }

    private void initRootView() {
        m_contentView = (LoadingLayout) contentView.findViewById(R.id.appContent);
        m_navigationBar = (NavigationBar) contentView.findViewById(R.id.navigationBar);
        m_statusBar = contentView.findViewById(R.id.m_statusBar);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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

    protected abstract P createPresenterInstance();
    protected abstract int getLayoutId();
    // 初始化界面控件
    protected abstract void onViewCreated(View view);
    // 执行逻辑操作
    protected abstract void doLogicFunc();

    @Override
    public void showToastMsg(String msg) {
        ToastManager.showShortToast(msg);
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
        if (EmptyUtils.isNotEmpty(loadDialog)) {
            loadDialog.dismiss();
        }
    }

    /**
     * 创建通用对话框
     * @param text              提示文本
     * @param outSideCancel     点击窗外是否取消<默认<true>
     * @return
     */
    protected CommonDialog newCommonDialog(String text, Boolean outSideCancel)
    {
        CommonDialog dialog = CommonDialog.newInstance(context);
        dialog.setText(text);
        dialog.setCanceledOnTouchOutside(outSideCancel);
        dialog.show(getFragmentManager(), "CommonDialog");
        return dialog;
    }

    protected CommonDialog newCommonDialog(String text) {
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

    @Override
    public void statusReTry(LoadingLayout.OnReloadListener listener)
    {
        if (EmptyUtils.isNull(listener)) {   return;   }

        m_contentView.setOnReloadListener(listener);
    }

    /**
     * 填充文本兼容空对象
     * @param textView    textview
     * @param content     content
     */
    public void setTextView(TextView textView, String content)
    {
        if (EmptyUtils.isNull(textView)) {  return; }

        textView.setText(StringUtils.nullToStr(content));
    }

    /**
     * 填充图片兼容空对象
     * @param imageview    imageview
     * @param url          content
     */
    public void setImageView(ImageView imageview, String url)
    {
        if (EmptyUtils.isNull(imageview)) {  return; }

        ImageLoaderUtils.display(imageview, StringUtils.nullToStr(url));
    }

    public void gotoActivity(Class<?> cls)
    {
        gotoActivity(cls, null);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void gotoActivity(Class<?> cls, Bundle bundle)
    {
        if (null == cls) {
            return;
        }

        Intent intent = new Intent();
        intent.setClass(context, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 登录弹窗
     * @param loginFragment   loginFragment
     */
    public void gotoLogin(BaseDialogFragment loginFragment)
    {
        loginFragment.show(((BaseMvpActivity) context).getSupportFragmentManager(),"Login");
    }

    protected void attachClickListener(View view) {
        if (contentView != null) {
            if (view != null) {
                view.setOnClickListener(clickListener);
            }
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

    public NavigationBar getNavigationBar() {
        return m_navigationBar;
    }

    protected void clearMemory() {
        if (presenter != null) {
            presenter.detachView();
        }

        if (null != rxManager) {
            rxManager.clear();
        }

        // 清除图片缓存
        ImageLoaderUtils.cleanMemory(context);

        ViewUtils.clearAllChildViews(getActivity());
    }

    public boolean interceptBackPressed() {
        return false;
    }

    @Override
    public void onDestroy() {
        clearMemory();
        super.onDestroy();
    }
}
