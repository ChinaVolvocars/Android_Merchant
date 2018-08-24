package com.common.location;

import android.content.Context;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdate;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.common.R;

/**
 * Created by Leo on LocationUtils.
 */
public class LocationUtils
{
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;

    public double Latitude = 0;
    public double Longitude = 0;
    public String singerAddress = "";
    public String address = "";
    public Callback callback = null;

    private static final  double EARTH_RADIUS = 6378137;//赤道半径(单位m)

    public LocationUtils(Context context)
    {
        getLocation(context, true);
    }

    public void setmLocationCallBack(Callback callback)
    {
        this.callback = callback;
    }

    /**
     * 高德定位
     * @param context
     */
    public void getLocation(Context context, boolean isHightAccuracy)
    {
        locationClient = new AMapLocationClient(context);
        locationOption = new AMapLocationClientOption();

        if (isHightAccuracy)
        {
            locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            locationOption.setNeedAddress(true);
            locationOption.setGpsFirst(true);
            locationOption.setOnceLocation(true);
        } else {
            locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Device_Sensors);
            locationOption.setGpsFirst(true);
            locationOption.setOnceLocation(false);
            locationOption.setInterval(1000);
        }

        locationClient.setLocationOption(locationOption);
        locationClient.setLocationListener(listener);
        locationClient.startLocation();
    }

    private AMapLocationListener listener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {
                Latitude = aMapLocation.getLatitude();
                Longitude = aMapLocation.getLongitude();
                address = aMapLocation.getAddress();
                singerAddress = aMapLocation.getProvince() + aMapLocation.getCity();
                callback.getLocation(Latitude, Longitude, address, singerAddress);
                callback.getAddress(aMapLocation);
                locationClient.stopLocation();
                destoryLocation();
                System.out.println("Latitude:" + Latitude + "," + "Longitude:" + Longitude);
                System.out.println("Accuracy:" + aMapLocation.getAccuracy() + "," + "Speed" + aMapLocation.getSpeed());
            } else {
                callback.locationFail();
            }
        }
    };

    public void destoryLocation()
    {
        if (null != locationClient) {
            locationClient.onDestroy();
            locationClient = null;
            locationOption = null;
        }
    }

    /**
     * 在地图上添加marker标记
     * @param aMap
     * @param latitude
     * @param longitude
     * @param address
     */
    public static void setMapView(AMap aMap, double latitude, double longitude, String address)
    {
        com.amap.api.maps2d.model.LatLng latlng = new com.amap.api.maps2d.model.LatLng(latitude, longitude);
        com.amap.api.maps2d.model.MarkerOptions markerOptions = new com.amap.api.maps2d.model.MarkerOptions();
        markerOptions.position(latlng);
        markerOptions.anchor(0.5f, 0.5f);
        markerOptions.title("所在位置：").snippet(address);
        markerOptions.icon(com.amap.api.maps2d.model.BitmapDescriptorFactory.defaultMarker(com.amap.api.maps2d.model.BitmapDescriptorFactory.HUE_AZURE));
        markerOptions.draggable(true);
        final Marker marker = aMap.addMarker(markerOptions);
        marker.showInfoWindow();// 设置默认显示一个infowinfow
        aMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng, 18));
    }

    /**
     * 在地图上添加marker标记
     * @param aMap
     */
    public static void setMapView(AMap aMap, LatLng latlng)
    {
        com.amap.api.maps2d.model.MarkerOptions markerOptions = new com.amap.api.maps2d.model.MarkerOptions();
        markerOptions.position(latlng);
        markerOptions.anchor(0.5f, 0.5f);
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.mipmap.icon_location_dot));
        markerOptions.draggable(true);
        final Marker marker = aMap.addMarker(markerOptions);
        marker.showInfoWindow();// 设置默认显示一个infowinfow

        aMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng, 18));
    }

    public static void changeCamera(LatLng latLng, AMap mAMap) {
        CameraPosition cameraPosition = new CameraPosition(latLng, 18, 0, 0);
        final CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        mAMap.animateCamera(cameraUpdate, 500, null);
    }

    public static void changeCamera(CameraUpdate cameraUpdate, AMap mAMap) {
        mAMap.animateCamera(cameraUpdate, 500, null);
    }

    public static void moveCamera(LatLng latLng, AMap mAMap) {
        CameraPosition cameraPosition = new CameraPosition(latLng, 18, 0, 0);
        final CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        mAMap.moveCamera(cameraUpdate);
    }

    // 返回单位是米
    public static double getDistance(double latitude1, double longitude1, double latitude2, double longitude2)
    {
        double Lat1 = rad(latitude1);
        double Lat2 = rad(latitude2);
        double a = Math.abs(Lat1 - Lat2);
        double b = Math.abs(rad(longitude1) - rad(longitude2));
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(Lat1) * Math.cos(Lat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    // 城市距离排序
//    public static void cityDistanceSort(ArrayList<City> cities)
//    {
//        if (null == cities || cities.size() == 0)
//            return;
//
//        int length=cities.size(); //数组长度
//        int j;                    //当前值的位置
//        int i;                    //指向j前的位置
//        City key;                 //当前要进行插入排序的值
//        //从数组的第二个位置开始遍历值
//        for(j = 1 ; j < length ; j++){
//            key = cities.get(j);
//            i = j - 1;
//            //a[i]比当前值大时，a[i]后移一位,空出i的位置，好让下一次循环的值后移
//            while(i >= 0 && cities.get(i).getDistance() > key.getDistance()){
//                cities.set(i + 1, cities.get(i));
//                i--;         //i前移
//            }//跳出循环(找到要插入的中间位置或已遍历到0下标)
//            cities.set(i + 1, key);
//        }
//    }
}
