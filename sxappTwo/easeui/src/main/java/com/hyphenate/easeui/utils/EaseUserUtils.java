package com.hyphenate.easeui.utils;

import android.widget.ImageView;
import android.widget.TextView;

import com.common.utils.EmptyUtils;
import com.common.widget.imageview.image.ImageLoaderUtils;
import com.easeui.R;
import com.hyphenate.easeui.controller.EaseUI;
import com.hyphenate.easeui.controller.EaseUI.EaseUserProfileProvider;
import com.hyphenate.easeui.domain.EaseUser;

public class EaseUserUtils {
    
    static EaseUserProfileProvider userProvider;
    
    static {
        userProvider = EaseUI.getInstance().getUserProfileProvider();
    }
    
    /**
     * get EaseUser according username
     * @param username
     * @return
     */
    public static EaseUser getUserInfo(String username){
        if(userProvider != null)
            return userProvider.getUser(username);
        return null;
    }
    
    /**
     * set user avatar
     * @param username
     */
    public static void setUserAvatar(String username, ImageView imageView){
    	EaseUser user = EaseUI.getInstance().getUserProfileProvider().getUser(username);
        if(user != null && user.getAvatar() != null){
            try {
                int avatarResId = Integer.parseInt(user.getAvatar());
                ImageLoaderUtils.displayCircle(imageView, avatarResId);
            } catch (Exception e) {
                //use default avatar
                ImageLoaderUtils.displayCircle(imageView, user.getAvatar());
            }
        } else {
            ImageLoaderUtils.displayCircle(imageView, R.mipmap.loadingview_empty);
        }
    }

    /**
     * set user avatar
     * @param username
     */
    public static void setUserAvatar(String username, String avatar, ImageView imageView){
        if (EmptyUtils.isNotEmpty(avatar)) {
            ImageLoaderUtils.displayCircle(imageView, avatar);
        } else {
            ImageLoaderUtils.displayCircle(imageView, R.mipmap.loadingview_empty);
        }
    }

    /**
     * set user's nickname
     */
    public static void setUserNick(String username,TextView textView){
        if(textView != null){
        	EaseUser user = getUserInfo(username);
        	if(user != null && user.getNick() != null){
        		textView.setText(user.getNick());
        	}else{
        		textView.setText(username);
        	}
        }
    }
    
}
