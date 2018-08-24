package com.live.utils;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;

import com.example.live.R;

import static android.graphics.Color.WHITE;

/**
 * Created by Administrator on 2016/11/10.
 */

public class TextViewUtils {

    public static SpannableStringBuilder chagneTextColor(Context context, String content,int type) {
        SpannableStringBuilder builder = new SpannableStringBuilder(content);
//type:1-普通聊天消息，2第一次-点亮，3—直播提示。4、非第一次点亮、5：礼物,6:弹幕
        int i = content.indexOf(":");
        int length = content.length();
//ForegroundColorSpan 为文字前景色，BackgroundColorSpan为文字背景色
        ForegroundColorSpan redContent = new ForegroundColorSpan(Color.parseColor("#FF6A6A"));
        ForegroundColorSpan whiteContent = new ForegroundColorSpan(WHITE);
        ForegroundColorSpan yellowNickName = new ForegroundColorSpan(Color.parseColor("#ff8e15"));
        ForegroundColorSpan yellowContenet = new ForegroundColorSpan(Color.parseColor("#FF9933"));
        builder.setSpan(yellowNickName, 0, i+1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        if(type==1){
            builder.setSpan(whiteContent, i+1, length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }else if(type==2){

            builder.setSpan(yellowContenet, i+1, length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }else if(type==3){
            builder.setSpan(yellowContenet, 0, length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }else if(type==5){
            builder.setSpan(redContent, i+1, length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return builder;
    }
}
