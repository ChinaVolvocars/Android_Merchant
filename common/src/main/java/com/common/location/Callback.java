package com.common.location;/**
 * Created by admin on 2016/2/22.
 */

import com.amap.api.location.AMapLocation;

/**
 * Created by Leo on Callback.
 */
public interface Callback
{
    void getLocation(double latitude, double longitude, String address, String singerAddress);
    void getAddress(AMapLocation address);
    void locationFail();
}
