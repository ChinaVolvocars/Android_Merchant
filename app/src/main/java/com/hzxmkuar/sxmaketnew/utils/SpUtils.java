//package com.hzxmkuar.sxmaketnew.utils;
//
///**
// * Created by little_bug on 2018/10/14.
// */
//
//import android.content.Context;
//import android.content.SharedPreferences;
//
//import com.common.utils.ContextUtils;
//import com.hzxmkuar.sxmaketnew.base.MainApplication;
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.io.StreamCorruptedException;
//
///**
// * SharedPreference工具类
// */
//public class SpUtils {
//
//    private static final String CONFIG_NAME = "config";
//
//    public static SharedPreferences getSp() {
//        return ContextUtils.getAppContext().getSharedPreferences(CONFIG_NAME, Context.MODE_PRIVATE);
//    }
//
//    public static String getString(String key) {
//
//        return getSp().getString(key, "");
//    }
//
//
//    public static Boolean getBoolean(String key, boolean b) {
//
//        return getSp().getBoolean(key, b);
//    }
//
//    public static int getInt(String key) {
//
//        return getSp().getInt(key, 0);
//    }
//
//    public static void put(String key, Object value) {
//
//        SharedPreferences.Editor edit = getSp().edit();
//        if (value instanceof String) {
//
//            edit.putString(key, (String) value);
//
//        } else if (value instanceof Integer) {
//
//            edit.putInt(key, (Integer) value);
//
//        } else if (value instanceof Boolean) {
//
//            edit.putBoolean(key, (Boolean) value);
//
//        }
//
//        edit.commit();
//    }
//
//    /**
//     * 根据key值，移除指定数据
//     *
//     * @param key
//     */
//    public static void remove(String key) {
//        SharedPreferences.Editor edit = getSp().edit();
//        edit.remove(key);
//        edit.commit();
//    }
//
//    public static void clear() {
//        SharedPreferences.Editor edit = getSp().edit();
//        edit.clear();
//        edit.commit();
//    }
//
//    /**
//     * 针对复杂类型存储<对象>
//     *
//     * @param key
//     * @param object
//     */
//    public static void putObject(String key, Object object) {
//
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ObjectOutputStream out = null;
//        try {
//
//            out = new ObjectOutputStream(baos);
//            out.writeObject(object);
//            String objectVal = new String(android.util.Base64.encode(baos.toByteArray(), android.util.Base64.DEFAULT));
//            SharedPreferences.Editor edit = getSp().edit();
//            edit.putString(key, objectVal);
//            edit.commit();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (baos != null) {
//                    baos.close();
//                }
//                if (out != null) {
//                    out.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    @SuppressWarnings("unchecked")
//    public static <T> T getObject(String key, Class<T> clazz) {
//        if (getSp().contains(key)) {
//            String objectVal = getSp().getString(key, null);
//            byte[] buffer = android.util.Base64.decode(objectVal, android.util.Base64.DEFAULT);
//            ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
//            ObjectInputStream ois = null;
//            try {
//                ois = new ObjectInputStream(bais);
//                T t = (T) ois.readObject();
//                return t;
//            } catch (StreamCorruptedException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            } finally {
//                try {
//                    if (bais != null) {
//                        bais.close();
//                    }
//                    if (ois != null) {
//                        ois.close();
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return null;
//    }
//
//
//}