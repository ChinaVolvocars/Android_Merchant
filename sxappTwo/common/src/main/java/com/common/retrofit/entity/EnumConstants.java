package com.common.retrofit.entity;

import com.common.R;

import java.util.HashMap;
import java.util.Map;

/**
 * 枚举公共类
 */
public class EnumConstants {

    public static Map<String, String> OrderStatus = new HashMap<>();

    static {
        OrderStatus.put("1", "待处理");
        OrderStatus.put("2", "待确认");
        OrderStatus.put("4", "已完成");
    }
}