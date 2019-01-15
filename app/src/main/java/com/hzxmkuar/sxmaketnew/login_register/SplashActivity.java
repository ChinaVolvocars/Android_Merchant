package com.hzxmkuar.sxmaketnew.login_register;

import android.widget.ImageView;

import com.common.mvp.BaseMvpActivity;
import com.hzxmkuar.sxmaketnew.home.MainActivity;
import com.hzxmkuar.sxmaketnew.R;
import com.hzxmkuar.sxmaketnew.mvpfunc.contract.SplashContract;
import com.hzxmkuar.sxmaketnew.mvpfunc.presenter.SplashPresenterImpl;
import com.hzxmkuar.sxmaketnew.newversion.NewMainActivity;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * @desc: 启动页界面
 * @author: Leo
 * @date: 2016/12/23
 */
public class SplashActivity extends BaseMvpActivity<SplashPresenterImpl> implements SplashContract.View {
    private ImageView ivSplash;
    public static String mLongitude = "";
    public static String mLatitude = "";

    @Override
    protected SplashPresenterImpl createPresenterInstance() {
        return new SplashPresenterImpl();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void setNavigation() {
        super.setNavigation();
    }

    @Override
    protected void setStatusBar() {
        //
    }

    @Override
    protected void onViewCreated() {
        ivSplash = (ImageView) findViewById(R.id.iv_splash);
    }

    @Override
    protected void doLogicFunc() {
        Observable.timer(1500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        presenter.checkIsFirstIn();
                    }
                });
    }

    @Override
    public void readyToMain() {
//        gotoActivity(MainActivity.class);
        gotoActivity(NewMainActivity.class);
        finish();
    }

    @Override
    public void readyToLogin() {
        gotoActivity(LoginActivity.class);
        finish();
    }

    @Override
    public void readyToGuide() {
//        startActivity(GuideActivity.class);
        finish();
    }
}