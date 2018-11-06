package com.common.widget.imageview.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.common.R;
import com.common.utils.SizeUtils;
import com.common.widget.imageview.image.blur.BlurTransformation;

import java.io.ByteArrayOutputStream;
import java.io.File;

/**
 * Description : 图片加载工具类 使用glide框架封装
 */
public class ImageLoaderUtils {
    private static int placeholderRes = R.mipmap.loadingview_empty;
    private static int errorRes = R.mipmap.loadingview_error;

    /**
     * 不使用占位图
     * 加载String的图片地址
     *
     * @param imageView imageview
     * @param url       图片地址
     */
    public static void displayNoPlace(ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(imageView.getContext()).load(url).crossFade().into(imageView);
    }

    /**
     * 不使用占位图
     * 加载String的图片地址
     *
     * @param imageView imageview
     * @param url       图片资源名
     */
    public static void displayNoPlace(ImageView imageView, int url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(imageView.getContext()).load(url).crossFade().into(imageView);
    }

    /**
     * 加载String的图片地址
     *
     * @param imageView imageview
     * @param url       图片地址
     */
    public static void display(ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(imageView.getContext()).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .placeholder(placeholderRes)
                .error(errorRes)
                .crossFade(1000).into(imageView);


    }

    /**
     * 加载小图片
     *
     * @param imageView
     * @param url
     */
    public static void displaySmallPhoto(ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument loadingview_error");
        }
        Glide.with(imageView.getContext()).load(url).asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.loadingview_empty)
                .error(R.mipmap.loadingview_error)
                .thumbnail(0.5f)
                .into(imageView);
    }

    /**
     * 加载小图片
     *
     * @param imageView
     * @param url
     */
    public static void displaySmallPhoto(ImageView imageView, int url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument loadingview_error");
        }
        Glide.with(imageView.getContext()).load(url).asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.loadingview_empty)
                .error(R.mipmap.loadingview_error)
                .thumbnail(0.5f)
                .into(imageView);
    }

    /**
     * 加载小图片
     *
     * @param imageView
     * @param url
     */
    public static void displaySmallBitmap(ImageView imageView, Bitmap url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument loadingview_error");
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        url.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] bytes = baos.toByteArray();

        Glide.with(imageView.getContext()).load(bytes)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .override((int) SizeUtils.dp2px(imageView.getContext(), 60), (int) SizeUtils.dp2px(imageView.getContext(), 100))
                .thumbnail(0.5f)
                .into(imageView);
    }

    /**
     * 加载File的图片地址
     *
     * @param imageView imageview
     * @param url       图片文件地址
     */
    public static void display(ImageView imageView, File url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(imageView.getContext()).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .placeholder(placeholderRes)
                .error(errorRes)
                .crossFade(1000).into(imageView);
    }

    /**
     * 加载int的图片地址
     *
     * @param imageView imageview
     * @param url       图片资源文件名
     */
    public static void display(ImageView imageView, int url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(imageView.getContext()).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .placeholder(placeholderRes)
                .error(errorRes)
                .crossFade(1000).into(imageView);
    }

    /**
     * 加载int的图片地址
     *
     * @param imageView imageview
     * @param url       图片资源文件名
     */
    public static void displaySize(ImageView imageView, int url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(imageView.getContext()).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .override((int) SizeUtils.dp2px(imageView.getContext(), 50), (int) SizeUtils.dp2px(imageView.getContext(), 80))
                .crossFade(1000).into(imageView);
    }

    /**
     * 地址转换成文件形式加载
     * 加载设备本地图片
     *
     * @param imageView imageview
     * @param url       本地图片存储地址
     */
    public static void displayPhoto(ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(imageView.getContext()).load(new File(url))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(placeholderRes)
                .error(errorRes)
                .into(imageView);
    }

    /**
     * 地址转换成文件形式加载
     * 加载设备本地大图
     *
     * @param imageView imageview
     * @param url       本地图片存储地址
     */
    public static void displayBigPhoto(ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(imageView.getContext()).load(new File(url)).asBitmap()
                .format(DecodeFormat.PREFER_ARGB_8888)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(placeholderRes)
                .error(errorRes)
                .into(imageView);
    }

    /**
     * 加载String的圆形图片
     *
     * @param imageView imageview
     * @param url       图片地址
     */
    public static void displayRound(ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(imageView.getContext()).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(placeholderRes)
                .error(errorRes)
                .crossFade(1000)
                .centerCrop().transform(new GlideRoundTransform(imageView.getContext(), 10)).into(imageView);
    }

    /**
     * 加载String的圆形图片
     *
     * @param imageView imageview
     * @param url       图片地址
     */
    public static void displayCircle(ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(imageView.getContext()).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(placeholderRes)
                .error(errorRes)
                .thumbnail(0.2f)
                .crossFade(1000)
                .centerCrop().transform(new GlideCircleTransfromUtil(imageView.getContext())).into(imageView);
    }

    /**
     * 加载String的圆形图片
     *
     * @param imageView imageview
     * @param url       图片地址
     */
    public static void displayCircle(ImageView imageView, int url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(imageView.getContext()).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(placeholderRes)
                .error(errorRes)
                .crossFade(1000)
                .centerCrop().transform(new GlideCircleTransfromUtil(imageView.getContext())).into(imageView);
    }

    /**
     * 加载String的圆形图片
     *
     * @param imageView imageview
     * @param file      图片地址
     */
    public static void displayCircle(ImageView imageView, File file) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(imageView.getContext()).load(file)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(placeholderRes)
                .error(errorRes)
                .centerCrop().transform(new GlideCircleTransfromUtil(imageView.getContext())).into(imageView);
    }

    /**
     * 加载file的圆形图片
     *
     * @param imageView imageview
     * @param file      图片地址
     */
    public static void displayRound(ImageView imageView, File file) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(imageView.getContext()).load(file)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(placeholderRes)
                .error(errorRes)
                .centerCrop().transform(new GlideRoundTransformUtil(imageView.getContext())).into(imageView);
    }

    /**
     * 加载int的圆形图片
     *
     * @param imageView imageview
     * @param url       图片资源名
     */
    public static void displayRound(ImageView imageView, int url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(imageView.getContext()).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(placeholderRes)
                .error(errorRes)
                .centerCrop().transform(new GlideRoundTransformUtil(imageView.getContext())).into(imageView);
    }

    /**
     * 加载String模糊型图片
     *
     * @param imageView imageview
     * @param url       图片资源名
     * @param radius    模糊程度（0-25）
     */
    public static void displayBlur(ImageView imageView, String url, int radius) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(imageView.getContext()).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(placeholderRes)
                .error(errorRes)
                .dontAnimate()
                .centerCrop().bitmapTransform(new BlurTransformation(imageView.getContext(), radius)).into(imageView);
    }

    /**
     * 加载int模糊型图片
     *
     * @param imageView imageview
     * @param url       图片资源名
     * @param radius    模糊程度（0-25）
     */
    public static void displayBlur(Context context, ImageView imageView, int url, int radius) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(placeholderRes)
                .error(errorRes)
                .dontAnimate()
                .centerCrop().bitmapTransform(new BlurTransformation(context, radius)).into(imageView);
    }

    /**
     * 清楚缓存
     *
     * @param context 上下文
     */
    public static void cleanMemory(Context context) {
        Glide.get(context).clearMemory();
    }


    public static void displaySmallPhoto(View viewById) {

    }
}
