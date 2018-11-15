package com.common.utils;

import android.content.Context;
import android.content.res.Resources;
import android.text.InputType;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.R;


/**
 * 创建者     jc
 */
public class UIUtils {
    /**
     * 得到上下文
     */
    public static Context getContext() {
        return ContextUtils.getAppContext();
    }

    /**
     * 得到Resource对象
     */
    public static Resources getResources() {
        return getContext().getResources();
    }

    /**
     * 得到String.xml中的字符串信息
     */
    public static String getString(int resId) {
        return getResources().getString(resId);
    }


    /**
     * 得到String.xml中的字符串数组信息
     */
    public static String[] getStrings(int resId) {
        return getResources().getStringArray(resId);
    }

    /**
     * 得到Color.xml中的颜色信息
     */
    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }

    /**
     * 得到应用程序包名
     *
     * @return
     */
    public static String getPackageName() {
        return getContext().getPackageName();
    }

    /**
     * dip-->px
     *
     * @param dip
     * @return
     */
    public static int dip2Px(int dip) {
        /*
        1.  px/(ppi/160) = dp
        2.  px/dp = density
         */
        //取得当前手机px和dp的倍数关系
        float density = getResources().getDisplayMetrics().density;
        int px = (int) (dip * density + .5f);
        return px;
    }

    public static int px2Dip(int px) {
        // 2.  px/dp = density

        //取得当前手机px和dp的倍数关系
        float density = getResources().getDisplayMetrics().density;

        int dip = (int) (px / density + .5f);
        return dip;
    }


    /**
     * 设置提示字体的 大小
     *
     * @param strConten
     * @param size
     */
    public static void setHintSize(EditText edtInput, String strConten, int size) {
        if (null != edtInput && null != strConten){
            SpannableString ss = new SpannableString(strConten);
            // 新建一个属性对象,设置文字的大小
            AbsoluteSizeSpan ass = new AbsoluteSizeSpan(size, true);
            // 附加属性到文本
            ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            // 设置hint
            edtInput.setHint(new SpannedString(ss)); // 一定要进行转换,否则属性会消失
        }
    }

    public static void setHintSize(TextView edtInput, String strConten, int size) {
        if (null != edtInput && null != strConten){
            SpannableString ss = new SpannableString(strConten);
            // 新建一个属性对象,设置文字的大小
            AbsoluteSizeSpan ass = new AbsoluteSizeSpan(size, true);
            // 附加属性到文本
            ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            // 设置hint
            edtInput.setHint(new SpannedString(ss)); // 一定要进行转换,否则属性会消失
        }

    }


    private static long lastClickTime;

    /**
     *  是否快速点击
     * @return
     */
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 800) {
            return true;
        }
        lastClickTime = time;
        return false;
    }



    /**
     *  判断输入框内容是否为空
     * @param inputView  EditText输入框
     * @return 如果为空 返回true    如果不为空 返回false
     */
    public static boolean isEditTextEmpty(EditText inputView) {
//        if (null != inputView) {
//            return TextUtils.isEmpty(inputView.getText().toString().trim());
//        } else {
//            return true;
//        }
        if (null != inputView){
            return false;
        }else {
            return TextUtils.isEmpty(inputView.getText().toString().trim());
        }
    }

    /**
     * 设置按钮是否可点击
     * @param phoneInput 手机号码输入框对象
     * @param pwdInput 密码框输入框对象
     * @return  手机号码输入框及密码输入框不为空时返回 true 否则返回 false
     */
    public static boolean setBtIsClicable(EditText phoneInput,EditText pwdInput){
        if (null != phoneInput && null != pwdInput){
            if (!(TextUtils.isEmpty(phoneInput.getText().toString())) && !(TextUtils.isEmpty(pwdInput.getText().toString()))) {
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    /**
     * 设置输入框中的清除内容按钮的状态与是否可点击
     * @param inputIsEmpty 输入框内容是否为空
     * @param editTextHasFocus 输入框是否有焦点
     */
    public static void setCleanBtnState(boolean inputIsEmpty, boolean editTextHasFocus, ImageView ivClean){
        if (null != ivClean){
            if (!inputIsEmpty  && editTextHasFocus) {
                ivClean.setVisibility(View.VISIBLE);
                ivClean.setClickable(true);
            } else {
                ivClean.setVisibility(View.GONE);
                ivClean.setClickable(false);
            }
        }
    }


    /**
     *  设置输入框里面的内容是显示或者隐藏
     * @param showOrHint 显示或隐藏
     * @param inputEdit 输入框对象
     * @param contentStr 输入框内容
     */
    public static void setEditTextTransform(boolean showOrHint,EditText inputEdit,String contentStr){
        if (null != inputEdit && null != contentStr){
            if (showOrHint) {
                inputEdit.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                inputEdit.setSelection( contentStr.length());
            } else {
                inputEdit.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                inputEdit.setSelection( contentStr.length());
            }
        }
    }

    /**
     * 获取输入框里面的内容
     * @param edtInput 输入框
     * @return 返回输入框里面的内容
     */
    public static String getViewContentStr(EditText edtInput) {
        String contentStr = "";
        if (null != edtInput) {
            contentStr = edtInput.getText().toString().trim();
            if (!TextUtils.isEmpty(contentStr)) {
                return contentStr;
            }
        }
        return contentStr;
    }

}
