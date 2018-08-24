package com.hzxmkuar.sxmaketnew.utils;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2016/8/28.
 * <p>
 * EditText 延迟弹出
 */
public class EditTextUtil {


    public static void showEditTextDelayed(Context context, final EditText editText, long time) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
                           public void run() {
                               InputMethodManager inputManager =
                                       (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//                               inputManager.showSoftInput(editText, 0);
                               inputManager.toggleSoftInput(0, InputMethodManager.RESULT_SHOWN);
                           }
                       },
                1000);
    }

    public static void showEditText(Context context, final EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        InputMethodManager inputManager =
                (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(editText, InputMethodManager.HIDE_NOT_ALWAYS);
    }


    public static void aitEditText(Context context, final EditText editText, long time, String name) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
                           public void run() {
                               InputMethodManager inputManager =
                                       (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//                               inputManager.showSoftInput(editText, 0);
                               inputManager.toggleSoftInput(0, InputMethodManager.RESULT_SHOWN);
                           }
                       },
                1000);
        editText.setText(name);

    }

    public static boolean isKeyBoardShow(EditText editText) {
        InputMethodManager imm = (InputMethodManager) editText.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (imm.hideSoftInputFromWindow(editText.getWindowToken(), 0)) {
//            imm.showSoftInput(editText,0);
            return true;
            //软键盘已弹出
        } else {
            //软键盘未弹出
            return false;
        }
    }

    public static void hintKeyBoard(EditText editText){
        InputMethodManager imm = (InputMethodManager)editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(),0);
    }
}
