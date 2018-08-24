package com.common.utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * @desc:         SP相关工具类
 * @author:       Leo
 * @date:         2016/12/23
 */
public class SPUtils {

    /**
     * 存储bool
         * @param key       key
     * @param value     数值
     */
    public static void setShareBoolean(String key, boolean value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ContextUtils.getAppContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * 取出bool
     * @param key       key
     * @return bool
     */
    public static boolean getShareBoolean(String key) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ContextUtils.getAppContext());
        return sharedPreferences.getBoolean(key, false);
    }

    /**
     * 存储String
     * @param key       key
     * @param value     数值
     */
    public static void setShareString(String key, String value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ContextUtils.getAppContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, StringUtils.nullToStr(value));
        editor.apply();
    }

    /**
     * 取出String
     * @param key       key
     * @return String
     */
    public static String getShareString(String key) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ContextUtils.getAppContext());
        return sharedPreferences.getString(key, "");
    }

    /**
     * 存储String
     * @param key       key
     * @param value     数值
     */
    public static void setShareDouble(String key, double value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ContextUtils.getAppContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value + "");
        editor.apply();
    }

    /**
     * 取出String
     * @param key       key
     * @return String
     */
    public static double getShareDouble(String key) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ContextUtils.getAppContext());
        double value = 0;
        if (StringUtils.isNumber(sharedPreferences.getString(key, ""))) {
            sharedPreferences.getString(key, "");
            value = Double.valueOf(sharedPreferences.getString(key, "0"));
        }
        return value;
    }

    /**
     * 存储String
     * @param key       key
     * @param value     数值
     */
    public static void setShareInt(String key, int value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ContextUtils.getAppContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value + "");
        editor.apply();
    }

    /**
     * 取出String
     * @param key       key
     * @return String
     */
    public static int getShareInt(String key) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ContextUtils.getAppContext());
        int value = 0;
        if (StringUtils.isNumber(sharedPreferences.getString(key, ""))) {
            sharedPreferences.getString(key, "");
            value = Integer.valueOf(sharedPreferences.getString(key, "0"));
        }
        return value;
    }

    /**
     * 存储json
     * @param key       key
     * @param value     数值
     */
    public static void setShareJson(String key, String value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ContextUtils.getAppContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * 取出json
     * @param key       key
     * @return String
     */
    public static String getShareJson(String key) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ContextUtils.getAppContext());
        return sharedPreferences.getString(key, "");
    }
}