package com.common.retrofit.entity;

import com.amap.api.location.AMapLocation;
import com.common.base.Constants;
import com.common.retrofit.entity.result.UserBean;
import com.common.retrofit.entity.resultImpl.CityLocalBean;
import com.common.utils.EmptyUtils;
import com.common.utils.SPUtils;
import com.common.utils.StringUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/*******************************
 * 数据保存

 * created at 2017/3/14 下午 2:17
 ********************************/
public class DataCenter {
    public static boolean isPhone = false;
    public static boolean isWx = false;
    public static boolean isQq = false;
    public static List<String> hot = new ArrayList<>();
    public static int type;
    public static int thirdId = -1;
    private static DataCenter instance = null;
    public static final String USERID = "USERID";                  // 用户id
    public static final String EASEUSERNAME = "EASEUSERNAME";      // 环信账号
    public static final String TOKEN = "TOKEN";                    // 用户令牌
    public static final String ISFIRST = "ISFIRST";                // 是否首次启动保存key
    public static final String USERBENA = "USERBENA";              // 用户详细信息保存key
    public static final String CITYNAME = "CITYNAME";              // 用户当前城市名
    public static final String LATITUDE = "LATITUDE";              // 用户当前纬度信息
    public static final String LONGITUDE = "LONGITUDE";            // 用户当前经度信息
    public static final String LOCATIONDETAIL = "LOCATIONDETAIL";  // 用户当前详细位置信息

    // 位置设置界面保存我的位置和当前位置到本地
    public static final String SETMYLOCATION = "SETMYLOCATION";    // 保存我的位置
    public static final String SETNOWLOCATION = "SETNOWLOCATION";  // 保存当前位置

    public static String HashId;  // 测试hashid
    public static int UserId = 0;                          // 测试UserId
    public static String IS_FIRST;                          // 测试UserId
    public static String HXuser = "";
    public static String HXpas = "";

    private double latitude;                // 纬度
    private double longitude;               // 经度
    private String cityName;               // 城市名
    private String cityDirec;              // 区地址
    private String locationDetail;         // 详细地址

    private UserBean userBean;
    /**
     * 高德地图
     */
    private AMapLocation mapLocation;
    private CityLocalBean cityLocalBean;

    // 用户TOKEN保存到本地
    private String token;      // 用户令牌
    private String easeUserName;      // 环信账号

    private DataCenter() {
        instance = this;
    }

    public static void initDataCenter() {
        if (null == instance) {
            new DataCenter();
        }
    }

    public static DataCenter getInstance() {
        if (instance == null) {
            synchronized (DataCenter.class) {
                if (instance == null) {
                    instance = new DataCenter();
                }
            }
        }
        return instance;
    }

    public int getUserId() {
        return 14;
    }

    /**
     * 保存登录信息
     *
     * @param bean 登录返回信息
     */
    public static void saveLoginDataInfo(UserBean bean) {
        if (EmptyUtils.isNotEmpty(bean.getHashid())) {
            DataCenter.UserId = bean.getUid();
            DataCenter.HashId = bean.getHashid();
            /*DataCenter.HXuser = bean.getHx_username();
            DataCenter.HXpas = bean.getHx_password();*/
            DataCenter.getInstance().setToken(bean.getHashid());
            SPUtils.setShareString(DataCenter.TOKEN, bean.getHashid());
        }
        DataCenter.getInstance().setUserBean(bean);
        SPUtils.setShareJson(DataCenter.USERBENA, new Gson().toJson(bean));
    }

    public String getEaseUserName() {
        return EmptyUtils.isEmpty(easeUserName) ? SPUtils.getShareString(DataCenter.EASEUSERNAME) : StringUtils.nullToStr(easeUserName);
    }

    public void setEaseUserName(String easeUserName) {
        this.easeUserName = StringUtils.nullToStr(easeUserName);
    }

    /**
     * 清空登录信息
     */
    public static void deleteLoginDataInfo() {
        DataCenter.HashId = "";
        DataCenter.UserId = -1;
        DataCenter.getInstance().setToken("");
        SPUtils.setShareString(DataCenter.TOKEN, "");
        DataCenter.getInstance().setUserBean(null);
        SPUtils.setShareJson(DataCenter.USERBENA, "");
    }

    public String getToken() {
        return EmptyUtils.isEmpty(token) ? SPUtils.getShareString(DataCenter.TOKEN) : StringUtils.nullToStr(token);
    }

    public void setToken(String token) {
        Constants.TOKEN = StringUtils.nullToStr(token);
        this.token = StringUtils.nullToStr(token);
    }

    public CityLocalBean getCityLocalBean() {
        return cityLocalBean;
    }

    public void setCityLocalBean(CityLocalBean cityLocalBean) {
        this.cityLocalBean = cityLocalBean;
    }

    // 登录信息为空时，优先选取本地数据，若本地也为空则返回空对象。
    public UserBean getUserBean() {
        String userBeanJson = SPUtils.getShareJson(DataCenter.USERBENA);
        UserBean userBean = EmptyUtils.isEmpty(new Gson().fromJson(userBeanJson, UserBean.class)) ?
                new UserBean() : new Gson().fromJson(userBeanJson, UserBean.class);

        return EmptyUtils.isEmpty(this.userBean) ? userBean : this.userBean;
    }

    public void setUserBean(UserBean userBean) {
        if (EmptyUtils.isEmpty(userBean)) {
            userBean = new UserBean();
        }
        this.userBean = userBean;
    }

    // 保存地理位置
    public static void savePosition(AMapLocation location) {
        if (EmptyUtils.isEmpty(location)) {
            return;
        }

        DataCenter.getInstance().setMapLocation(location);
        String locationStr = location.getProvince() + location.getCity() + location.getDistrict() + location.getPoiName();
        DataCenter.getInstance().setCityName(location.getCity());
        SPUtils.setShareString(DataCenter.CITYNAME, location.getCity());
        DataCenter.getInstance().setLocationDetail(locationStr);
        SPUtils.setShareString(DataCenter.LOCATIONDETAIL, locationStr);
        DataCenter.getInstance().setMapLocation(location);
        DataCenter.getInstance().setLatitude(location.getLatitude());
        SPUtils.setShareDouble(DataCenter.LATITUDE, location.getLatitude());
        DataCenter.getInstance().setLongitude(location.getLongitude());
        SPUtils.setShareDouble(DataCenter.LONGITUDE, location.getLongitude());
    }

    public String getCityName() {
        String city = StringUtils.isEmpty(cityName) ? "定位中" : cityName;
        return StringUtils.isEmpty(cityName) ? SPUtils.getShareJson(DataCenter.CITYNAME) : city;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getLocationDetail() {
        return StringUtils.isEmpty(locationDetail) ? SPUtils.getShareString(DataCenter.LOCATIONDETAIL) : locationDetail;
    }

    public void setLocationDetail(String locationDetail) {
        this.locationDetail = locationDetail;
    }

    public double getLongitude() {
        return longitude == 0 ? 120.314401 : longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude == 0 ? 30.300436 : latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public AMapLocation getMapLocation() {
        return mapLocation;
    }

    public void setMapLocation(AMapLocation mapLocation) {
        this.mapLocation = mapLocation;
    }

    public void setCityDirec(String cityDirec) {
        this.cityDirec = cityDirec;
    }

    public String getCityDirec() {
        if (EmptyUtils.isEmpty(getMapLocation())) {
            return "定位中";
        } else {
            return getMapLocation().getCity();
        }
    }
}