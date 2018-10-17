package com.common.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

/**
 * @desc: SP相关工具类
 * @author: Leo
 * @date: 2016/12/23
 */
public class SPUtils {

    private static final String CONFIG_NAME = "config";

    public static SharedPreferences getSp() {
        return ContextUtils.getAppContext().getSharedPreferences(CONFIG_NAME, Context.MODE_PRIVATE);
    }

    /**
     * 存储bool
     *
     * @param key   key
     * @param value 数值
     */
    public static void setShareBoolean(String key, boolean value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ContextUtils.getAppContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * 取出bool
     *
     * @param key key
     * @return bool
     */
    public static boolean getShareBoolean(String key) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ContextUtils.getAppContext());
        return sharedPreferences.getBoolean(key, false);
    }

    /**
     * 存储String
     *
     * @param key   key
     * @param value 数值
     */
    public static void setShareString(String key, String value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ContextUtils.getAppContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, StringUtils.nullToStr(value));
        editor.apply();
    }

    /**
     * 取出String
     *
     * @param key key
     * @return String
     */
    public static String getShareString(String key) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ContextUtils.getAppContext());
        return sharedPreferences.getString(key, "");
    }

    /**
     * 存储String
     *
     * @param key   key
     * @param value 数值
     */
    public static void setShareDouble(String key, double value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ContextUtils.getAppContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value + "");
        editor.apply();
    }

    /**
     * 取出String
     *
     * @param key key
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
     *
     * @param key   key
     * @param value 数值
     */
    public static void setShareInt(String key, int value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ContextUtils.getAppContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value + "");
        editor.apply();
    }

    /**
     * 取出String
     *
     * @param key key
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
     *
     * @param key   key
     * @param value 数值
     */
    public static void setShareJson(String key, String value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ContextUtils.getAppContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * 取出json
     *
     * @param key key
     * @return String
     */
    public static String getShareJson(String key) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ContextUtils.getAppContext());
        return sharedPreferences.getString(key, "");
    }


    /**
     * 针对复杂类型存储<对象>
     *
     * @param key
     * @param object
     */
    public static void putObject(String key, Object object) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(baos);
            out.writeObject(object);
            String objectVal = new String(android.util.Base64.encode(baos.toByteArray(), android.util.Base64.DEFAULT));
            SharedPreferences.Editor edit = getSp().edit();
            edit.putString(key, objectVal);
            edit.commit();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T getObject(String key, Class<T> clazz) {
        if (getSp().contains(key)) {
            String objectVal = getSp().getString(key, null);
            byte[] buffer = android.util.Base64.decode(objectVal, android.util.Base64.DEFAULT);
            ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(bais);
                T t = (T) ois.readObject();
                return t;
            } catch (StreamCorruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bais != null) {
                        bais.close();
                    }
                    if (ois != null) {
                        ois.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 根据key值，移除指定数据
     *
     * @param key
     */
    public static void remove(String key) {
        SharedPreferences.Editor edit = getSp().edit();
        edit.remove(key);
        edit.commit();
    }

    public static void clear() {
        SharedPreferences.Editor edit = getSp().edit();
        edit.clear();
        edit.commit();
    }


}