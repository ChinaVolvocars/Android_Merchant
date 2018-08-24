package com.common.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.R;
import com.common.widget.imageview.image.ImageLoaderUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @desc:   view工具类
 * @author: Leo
 * @date:   2016/09/28
 */
public class ViewUtils
{
    /**
     * 获取activity 根布局
     * @param context
     * @return
     */
    public static View getRootView(Activity context) {
        return ((ViewGroup) context.findViewById(android.R.id.content)).getChildAt(0);
    }

    /**
     * 从父亲布局中移除自己
     * @param v
     */
    public static void removeSelfFromParent(View v) {
        ViewParent parent = v.getParent();
        if (parent != null) {
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(v);
            }
        }
    }

    public static void switchViewVisibility(View... targetViews) {
        for (View v : targetViews) {
            if (v != null) {
                v.setVisibility(v.getVisibility() == View.VISIBLE ? View.GONE
                        : View.VISIBLE);
            }
        }
    }

    /**
     * 获取目标界面所有view
     * @param activity  目标界面
     * @return  界面子view列表
     */
    public static List<View> getAllChildViews(Activity activity) {

        View view = activity.getWindow().getDecorView();

        return getAllChildViews(view);
    }

    public static void clearAllChildViews(Activity activity)
    {
        View view = activity.getWindow().getDecorView();

        if (view instanceof ViewGroup) {
            ((ViewGroup) view).removeAllViews();
        }
    }

    /**
     * 获取目标View的所有子view
     * @param view   目标view
     * @return   子view列表
     */
    private static List<View> getAllChildViews(View view) {

        List<View> allchildren = new ArrayList<View>();

        if (view instanceof ViewGroup) {

            ViewGroup vp = (ViewGroup) view;

            for (int i = 0; i < vp.getChildCount(); i++) {

                View viewchild = vp.getChildAt(i);

                allchildren.add(viewchild);

                allchildren.addAll(getAllChildViews(viewchild));
            }
        }

        return allchildren;
    }

    public static void setViewShow(View... targetViews) {
        for (View v : targetViews) {
            if (v != null) {
                v.setVisibility(View.VISIBLE);
            }
        }
    }

    public static void setViewHide(View... targetViews) {
        for (View v : targetViews) {
            if (v != null) {
                v.setVisibility(View.GONE);
            }
        }
    }

    public static void setViewEnabled(boolean enable, View... targetViews) {
        for (View v : targetViews) {
            if (v != null) {
                v.setEnabled(enable);
            }
        }
    }

    public static void setViewVisibility(boolean show, View... targetViews) {
        for (View v : targetViews) {
            if (v != null) {
                v.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        }
    }

    public static void setBackgroundResource(int resid, View... targetViews) {
        for (View v : targetViews) {
            if (v != null) {
                v.setBackgroundResource(resid);
            }
        }
    }

    private static int checkVilidity(float f) {
        if (f < 0) {
            return 0;
        }
        return (int) f;
    }

    private static int getVilidity(int actual, int expect) {
        if (actual < expect) {
            return actual;
        }
        return expect;
    }

    /**
     * 动态添加白色间隙
     * @param context   c
     * @param layout   layout
     * @param params   params
     */
    public static void CreateGapView(Context context, LinearLayout layout, LinearLayout.LayoutParams params) {
        View view = new View(context);
        view.setLayoutParams(params);
        layout.addView(view);
    }

    /**
     * 动态添加白色间隙
     * @param context c
     * @param layout  layout
     */
    public static void CreateGapView(Context context, LinearLayout layout) {
        View view = new View(context);
        view.setLayoutParams(getGapParams(SizeUtils.dp2px(context, 10)));
        layout.addView(view);
    }

    /**
     * 根据间隙宽度创建间隙params
     * @param gapSize gapSize
     * @return parames
     */
    public static LinearLayout.LayoutParams getGapParams(float gapSize)
    {
        return new LinearLayout.LayoutParams((int)gapSize, LinearLayout.LayoutParams.MATCH_PARENT);
    }

    public static ImageView CreateImageView(ArrayList<String> beans, Context context,
                                       View.OnClickListener listener, int position)
    {
        ImageView imageView;
        imageView = new ImageView(context);
        imageView.setTag(R.id.tag, beans);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(params);
        imageView.setId(position);
        imageView.setOnClickListener(listener);
        ImageLoaderUtils.display(imageView, beans.get(position));
        return imageView;
    }

    /**
     * 设置编辑框是否可编辑
     * @param editText    编辑框
     * @param canBeEdit   是否可编辑
     */
    public static void setViewCanEdit(EditText editText, boolean canBeEdit) {
        if (!canBeEdit) {
            editText.setFocusable(false);
            editText.setFocusableInTouchMode(false);
        } else {
            editText.setFocusableInTouchMode(true);
            editText.setFocusable(true);
            editText.requestFocus();
        }
    }

    // 流式标签
    public static TextView creatTextView(Context mContext, String text)
    {
        TextView textView = new TextView(mContext);
        //字体颜色
        textView.setTextColor(ResourceUtils.getColor(mContext, R.color.black));
        //设置字体大小
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        textView.setText(text);
        //背景
        textView.setBackgroundResource(R.drawable.wc_nomal);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        //padding
        int padding = (int) SizeUtils.dp2px(mContext, 10);
        ViewCompat.setPaddingRelative(textView, padding , padding / 2,
                padding, padding / 2);
        layoutParams.setMargins(10, 10, 10, 0);
        textView.setLayoutParams(layoutParams);
        return textView;
    }
    public static TextView creatTextViews(Context mContext, String text)
    {
        TextView textView = new TextView(mContext);
        //字体颜色
        textView.setTextColor(ResourceUtils.getColor(mContext, R.color.black));
        //设置字体大小
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        textView.setText(text);
        //背景
        textView.setBackgroundResource(R.drawable.cz_nomal);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        //padding
        int padding = (int) SizeUtils.dp2px(mContext, 10);
        ViewCompat.setPaddingRelative(textView, padding , padding / 2,
                padding, padding / 2);
        layoutParams.setMargins(10, 10, 10, 0);
        textView.setLayoutParams(layoutParams);
        return textView;
    }
    public static TextView creatTextViewss(Context mContext, String text)
    {
        TextView textView = new TextView(mContext);
        //字体颜色
        textView.setTextColor(ResourceUtils.getColor(mContext, R.color.base_text_color));
        //设置字体大小
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        textView.setText(text);
        //背景
        textView.setBackgroundResource(R.drawable.cz_nomal);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        //padding
        int padding = (int) SizeUtils.dp2px(mContext, 10);
        ViewCompat.setPaddingRelative(textView, padding , padding / 2,
                padding, padding / 2);
        layoutParams.setMargins(10, 10, 10, 0);
        textView.setLayoutParams(layoutParams);
        return textView;
    }
}
