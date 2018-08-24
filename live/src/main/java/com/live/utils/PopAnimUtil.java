package com.live.utils;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.PopupWindow;

import com.example.live.R;

/**
 * Created by Administrator on 2016/9/18.
 */
public class PopAnimUtil {

    private static Animation animation;

    public static void dopPpDissmissAinm(final Context context, final View view, final PopupWindow popupWindow){
        animation = AnimationUtils.loadAnimation(context, R.anim.pophidden_anim);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.startAnimation(animation);
            }
        });

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(popupWindow!=null){
                    popupWindow.dismiss();
                }

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
}
