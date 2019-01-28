package com.hzxmkuar.sxmaketnew.utils;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hzxmkuar.sxmaketnew.R;

public class GlideUtils {

//    public static void loadImage(@NonNull Context context, @NonNull String url, @NonNull ImageView imageView) {
//        RequestOptions options = new RequestOptions()
//                .placeholder(R.mipmap.icon_image_background)
//                .error(R.mipmap.icon_image_background)
//                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
//        Glide.with(context).asBitmap().apply(options).load(url).into(imageView);
//    }
//
//    public static void loadRoundImage(@NonNull Context context, @NonNull String url, @NonNull ImageView imageView) {
//        RoundedCorners roundedCorners = new RoundedCorners(7);
//        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners)
//                .placeholder(R.mipmap.icon_image_background)
//                .error(R.mipmap.icon_image_background)
//                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
//        Glide.with(context).load(url).apply(options).into(imageView);
//    }
//
//    public static void loadGifImage(@NonNull Context context, @NonNull String url, @NonNull ImageView imageView) {
//        RequestOptions options = new RequestOptions()
//                .placeholder(R.mipmap.icon_image_background)
//                .error(R.mipmap.icon_image_background)
//                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
//        Glide.with(context).asGif().apply(options).load(url).into(imageView);
//    }
//
//    public static void loadLocalGifImage(@NonNull Context context, @DrawableRes int resourceId, @NonNull ImageView imageView) {
//        RequestOptions options = new RequestOptions()
//                .placeholder(R.mipmap.icon_image_background)
//                .error(R.mipmap.icon_image_background)
//                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
//        Glide.with(context).asGif().apply(options).load(resourceId).into(imageView);
//    }
//
//    public static void loadCircleCropImage(@NonNull Context context, @NonNull String url, @NonNull ImageView imageView) {
//        RequestOptions mRequestOptions = RequestOptions.circleCropTransform()
//                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
//                .placeholder(R.mipmap.icon_image_background).error(R.mipmap.icon_image_background)
//                .skipMemoryCache(true);
//        Glide.with(context).asBitmap().apply(mRequestOptions).load(url).into(imageView);
//    }
//
//    public static void loadImage(@NonNull Context context, @NonNull String url,
//                                 @NonNull ImageView imageView, boolean isCenterCrop) {
//        RequestOptions options = new RequestOptions().placeholder(R.mipmap.icon_image_background).error(R.mipmap.icon_image_background)
//                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
//        if (isCenterCrop) {
//            options = options.centerCrop();
//        } else {
//            options = options.centerInside();
//        }
//        Glide.with(context).asBitmap().apply(options).load(url).into(imageView);
//    }
//
//    public static void loadImage(@NonNull Context context, @DrawableRes int resId, @NonNull ImageView imageView) {
//        RequestOptions options = new RequestOptions().centerInside()
//                .placeholder(R.mipmap.icon_image_background)
//                .error(R.mipmap.icon_image_background)
//                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
//        Glide.with(context).asBitmap().apply(options).load(resId).into(imageView);
//    }


}
