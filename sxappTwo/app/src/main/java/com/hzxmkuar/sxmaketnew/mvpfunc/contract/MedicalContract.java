package com.hzxmkuar.sxmaketnew.mvpfunc.contract;

import com.common.mvp.BasePresenter;
import com.common.mvp.BaseView;

public interface MedicalContract
{
    interface View extends BaseView
    {
        void cartSuccess();
        void uploadSuccess(String picID);
    }

    abstract class Presenter extends BasePresenter<View>
    {
        public abstract void addToCart(int shopId, int medicineId);
    }
}