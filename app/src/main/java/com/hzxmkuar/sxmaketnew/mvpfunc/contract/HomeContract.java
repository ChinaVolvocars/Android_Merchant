package com.hzxmkuar.sxmaketnew.mvpfunc.contract;

import com.amap.api.location.AMapLocation;
import com.common.mvp.BasePresenter;
import com.common.mvp.BaseView;

public interface HomeContract {
    interface View extends BaseView {
        void locationSuccess(AMapLocation location);
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getHomePic(int type);

        public abstract void getLocation();
    }
}