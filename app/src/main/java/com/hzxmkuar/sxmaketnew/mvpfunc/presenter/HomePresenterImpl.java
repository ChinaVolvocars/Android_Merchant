package com.hzxmkuar.sxmaketnew.mvpfunc.presenter;

import com.amap.api.location.AMapLocation;
import com.common.location.LocationSubscriber;
import com.common.location.RxLocation;
import com.common.retrofit.entity.DataCenter;
import com.common.utils.EmptyUtils;
import com.hzxmkuar.sxmaketnew.mvpfunc.contract.HomeContract;

public class HomePresenterImpl extends HomeContract.Presenter
{
    @Override
    public void getHomePic(final int type) {
//        CommonSubscriber<List<SlideBean>> subscriber = new CommonSubscriber<>(new SubscriberListener() {
//            @Override
//            public void onNext(Object o) {
//                List<SlideBean> list = (List<SlideBean>) o;
//                if (EmptyUtils.isNotEmpty(list)) {
//                    if (type == 1) {
//                        mView.bannarSuccess(list);
//                    } else if (type == 2) {
//                        mView.pictureSuccess(list);
//                    }
//                }
//            }
//
//            @Override
//            public void onError(String e, int code) { }
//        });
//
//        SlideMethods.getInstance().getSlidImg(subscriber, type);
//        rxManager.add(subscriber);
    }

    @Override
    public void getLocation() {
        RxLocation.get().locateLastKnown(context)
                .subscribe(new LocationSubscriber() {
                    @Override
                    public void onLocatedSuccess(AMapLocation location) {
                        if (EmptyUtils.isEmpty(location)) {
                            mView.showToastMsg("定位失败");
                            return;
                        }
                        mView.locationSuccess(location);
                        DataCenter.getInstance().savePosition(location);
                    }

                    @Override
                    public void onLocatedFail(AMapLocation location) {
                        DataCenter.getInstance().setLatitude(30.300436);
                        DataCenter.getInstance().setLongitude(120.314401);
                        DataCenter.getInstance().setCityDirec("江干区");
                        mView.showToastMsg("定位失败");
                    }
                });
    }
}