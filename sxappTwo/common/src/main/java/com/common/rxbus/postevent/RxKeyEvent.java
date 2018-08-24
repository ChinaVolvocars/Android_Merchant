package com.common.rxbus.postevent;

/**
 * @desc: 用于RxBus传递标志量
 * @author: Leo
 * @date: 2017/1/6
 */
public class RxKeyEvent {
    private Object value;
    private String key;

    /**
     * 强制下线
     */
    public static final String FORCE_LOGOUT = "FORCE_LOGOUT";

    /**
     * 选择省市区返回标志
     */
    public static final String PROVINCE_NAME = "PROVINCE_NAME";
    public static final String CITY_NAME = "CITY_NAME";
    public static final String AREA_NAME = "AREA_NAME";
    public static final String FINISH_ALL = "FINISH_ALL";
    public static final String CLEAR_ALL = "CLEAR_ALL";

    /**
     * 接收消息
     */
    public static final String GET_SESSAGE = "GET_SESSAGE";
    public static final String REFRESH_LIVE = "REFRESH_LIVE";
    /**
     * 地址保存成功
     */
    public static final String ADDRESS_SAVE_SUCCESS = "ADDRESS_SAVE_SUCCESS";
    public static final String VERIFY_SUCCESS = "VERIFY_SUCCESS";

    /**
     * 定位成功
     */
    public static final String LOCATION_SUCCESS = "LOCATION_SUCCESS";
    public static final String LOCATION_CHECK_SUCCESS = "LOCATION_CHECK_SUCCESS";

    /**
     * 个人设置修改成功
     */
    public static final String NICKNAME_SET_SUCCESS = "NICKNAME_SET_SUCCESS";
    public static final String AVATOR_SET_SUCCESS = "AVATOR_SET_SUCCESS";
    public static final String INTEGRATER_CHANGED = "INTEGRATER_CHANGED";

    /**
     * 图片返回标志
     */
    public static final String PICTURE_IS_RESULT = "PICTURE_IS_RESULT";

    /**
     * 排行榜
     */
    public static final String RANK_RESULT = "RANK_RESULT";
    /**
     * 刷新会话消息列表
     */
    public static final String REFRESH_MESSAGE = "REFRESH_MESSAGE";

    public RxKeyEvent(String key, Object value) {
        this.value = value;
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public String getKey() {
        return key;
    }
}
