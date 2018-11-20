package com.hzxmkuar.sxmaketnew.newversion;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.internal.ParcelableSparseArray;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.common.utils.SPUtils;
import com.hzxmkuar.sxmaketnew.R;
import com.hzxmkuar.sxmaketnew.fragment.InvoiceElectronicFragment;
import com.hzxmkuar.sxmaketnew.fragment.InvoiceExpressFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.hzxmkuar.sxmaketnew.fragment.InvoiceExpressFragment.WID;

public class InvoiceInformationActivity extends BaseMvpActivity {

    ///当前显示的fragment
    private static final String CURRENT_FRAGMENT = "STATE_FRAGMENT_SHOW";
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.t_name)
    TextView tName;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.view_point01)
    TextView viewPoint01;
    @BindView(R.id.view_point02)
    TextView viewPoint02;
    @BindView(R.id.tv_text)
    TextView tvText;
    @BindView(R.id.view_point03)
    TextView viewPoint03;
    @BindView(R.id.view_point04)
    TextView viewPoint04;
    @BindView(R.id.rl_pay_for_ohter)
    LinearLayout rlPayForOhter;
    @BindView(R.id.view_bottomline_pay_for_ohter)
    TextView viewBottomlinePayForOhter;
    @BindView(R.id.view_point05)
    TextView viewPoint05;
    @BindView(R.id.view_point06)
    TextView viewPoint06;
    @BindView(R.id.tv_)
    TextView tv;
    @BindView(R.id.view_point07)
    TextView viewPoint07;
    @BindView(R.id.view_point08)
    TextView viewPoint08;
    @BindView(R.id.rl_invoice_withdraw)
    LinearLayout rlInvoiceWithdraw;
    @BindView(R.id.view_bottomline_withdraw)
    TextView viewBottomlineWithdraw;
    @BindView(R.id.fl)
    FrameLayout fl;
    private String wId;
    private Fragment currentFragment = new Fragment();
    private List<Fragment> fragments = new ArrayList<>();
    private int currentIndex;
    private FragmentManager fragmentManager;

    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @Override
    protected void doLogicFunc() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_invoice_information;
    }

    @Override
    protected void onViewCreated() {
        tName.setText("提交发票");
        Intent intent = getIntent();
        wId = intent.getStringExtra(WID);
        System.out.println("获取到的wid：" + wId);
        //bundle 传值丢失，使用sp保存
        SPUtils.setShareString(WID, wId);

        changeViewState(true);
        attachClickListener(rlPayForOhter);
        attachClickListener(rlInvoiceWithdraw);
        attachClickListener(back);

    }

    @Override
    protected void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState != null) {
            //获取“内存重启”时保存的索引下标
            currentIndex = savedInstanceState.getInt(CURRENT_FRAGMENT, 0);
            fragments.removeAll(fragments);
            fragments.add(fragmentManager.findFragmentByTag(0 + ""));
            fragments.add(fragmentManager.findFragmentByTag(1 + ""));
            //恢复fragment页面
            restoreFragment();
        } else {
            Bundle bundle = new Bundle();
            bundle.putString(WID, wId);
            fragments.add(InvoiceElectronicFragment.newInstance(bundle));
            fragments.add(InvoiceExpressFragment.newInstance(bundle));

            showFragment();
        }

    }


    /**
     * 使用show() hide()切换页面
     * 显示fragment
     */
    private void showFragment() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //如果之前没有添加过
        if (!fragments.get(currentIndex).isAdded()) {
            transaction.hide(currentFragment).add(R.id.fl, fragments.get(currentIndex), "" + currentIndex);  //第三个参数为添加当前的fragment时绑定一个tag
        } else {
            transaction.hide(currentFragment).show(fragments.get(currentIndex));
        }
        currentFragment = fragments.get(currentIndex);
        transaction.commit();
    }

    /**
     * 恢复fragment
     */
    private void restoreFragment() {
        FragmentTransaction mBeginTreansaction = fragmentManager.beginTransaction();
        for (int i = 0; i < fragments.size(); i++) {
            if (i == currentIndex) {
                mBeginTreansaction.show(fragments.get(i));
            } else {
                mBeginTreansaction.hide(fragments.get(i));
            }
        }
        mBeginTreansaction.commit();
        //把当前显示的fragment记录下来
        currentFragment = fragments.get(currentIndex);
    }


    @Override
    protected void onViewClicked(View view) {
        if (view.getId() == rlPayForOhter.getId()) {
            changeViewState(true);
            currentIndex = 0;
            showFragment();
        } else if (view.getId() == rlInvoiceWithdraw.getId()) {
            changeViewState(false);
            currentIndex = 1;
            showFragment();
        } else if (view.getId() == back.getId()) {
            finish();
        }


    }


    private void fragmentReplace(boolean flag) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        if (flag) {
            transaction.replace(R.id.fl, fragments.get(0));
        } else {
            transaction.replace(R.id.fl, fragments.get(1));
        }
        transaction.commit();
    }


    private void changeViewState(boolean isDefult) {
        if (isDefult) {
            viewPoint01.setBackgroundResource(R.drawable.circle_color_yellow_99fcc80a);
            viewPoint02.setBackgroundResource(R.drawable.circle_color_yellow_fcc80a);
            viewPoint03.setBackgroundResource(R.drawable.circle_color_yellow_fcc80a);
            viewPoint04.setBackgroundResource(R.drawable.circle_color_yellow_99fcc80a);
            viewBottomlinePayForOhter.setVisibility(View.VISIBLE);

            viewPoint05.setBackgroundResource(R.drawable.circle_color_black_994c4c4c);
            viewPoint06.setBackgroundResource(R.drawable.circle_color_black_4c4c4c);
            viewPoint07.setBackgroundResource(R.drawable.circle_color_black_4c4c4c);
            viewPoint08.setBackgroundResource(R.drawable.circle_color_black_994c4c4c);
            viewBottomlineWithdraw.setVisibility(View.INVISIBLE);

        } else {
            viewPoint01.setBackgroundResource(R.drawable.circle_color_black_994c4c4c);
            viewPoint02.setBackgroundResource(R.drawable.circle_color_black_4c4c4c);
            viewPoint03.setBackgroundResource(R.drawable.circle_color_black_4c4c4c);
            viewPoint04.setBackgroundResource(R.drawable.circle_color_black_994c4c4c);
            viewBottomlinePayForOhter.setVisibility(View.INVISIBLE);

            viewPoint05.setBackgroundResource(R.drawable.circle_color_blue_9955a7fb);
            viewPoint06.setBackgroundResource(R.drawable.circle_color_blue_55a7fb);
            viewPoint07.setBackgroundResource(R.drawable.circle_color_blue_55a7fb);
            viewPoint08.setBackgroundResource(R.drawable.circle_color_blue_9955a7fb);
            viewBottomlineWithdraw.setVisibility(View.VISIBLE);

        }
    }


}
