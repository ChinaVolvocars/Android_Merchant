package com.live.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.view.View;
import android.widget.ImageView;

import com.example.live.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import static android.R.attr.radius;

/**
 * Created by Administrator on 2016/11/12.
 */

public class ImageUtils {


    public static Bitmap doBlur(Bitmap sentBitmap, int intradius, boolean canReuseInBitmap) {

        // Stack Blur v1.0 from
        //http://www.quasimondo.com/StackBlurForCanvas/StackBlurDemo.html
        //
        // Java Author: Mario Klingemann <mario at="" quasimondo.com="">
        //http://incubator.quasimondo.com
        // created Feburary 29, 2004
        // Android port : Yahel Bouaziz <yahel at="" kayenko.com="">
        //http://www.kayenko.com
        // ported april 5th, 2012

        // This is a compromise between Gaussian Blur and Box blur
        // It creates much better looking blurs than Box Blur, but is
        // 7x faster than my Gaussian Blur implementation.
        //
        // I called it Stack Blur because this describes best how this
        // filter works internally: it creates a kind of moving stack
        // of colors whilst scanning through the image. Thereby it
        // just has to add one new block of color to the right side
        // of the stack and remove the leftmost color. The remaining
        // colors on the topmost layer of the stack are either added on
        // or reduced by one, depending on if they are on the right or
        // on the left side of the stack.
        //
        // If you are using this algorithm in your code please add
        // the following line:
        //
        // Stack Blur Algorithm by Mario Klingemann <mario@quasimondo.com>

        Bitmap bitmap;
        if (canReuseInBitmap) {
            bitmap = sentBitmap;
        } else {
            bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);
        }

        if (radius < 1) {
            return (null);
        }

        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        int[] pix = new int[w * h];
        bitmap.getPixels(pix, 0, w, 0, 0, w, h);

        int wm = w - 1;
        int hm = h - 1;
        int wh = w * h;
        int div = radius + radius + 1;

        int r[] = new int[wh];
        int g[] = new int[wh];
        int b[] = new int[wh];
        int rsum, gsum, bsum, x, y, i, p, yp, yi, yw;
        int vmin[] = new int[Math.max(w, h)];

        int divsum = (div + 1) >> 1;
        divsum *= divsum;
        int dv[] = new int[256 * divsum];
        for (i = 0; i < 256 * divsum; i++) {
            dv[i] = (i / divsum);
        }

        yw = yi = 0;

        int[][] stack = new int[div][3];
        int stackpointer;
        int stackstart;
        int[] sir;
        int rbs;
        int r1 = radius + 1;
        int routsum, goutsum, boutsum;
        int rinsum, ginsum, binsum;

        for (y = 0; y < h; y++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            for (i = -radius; i <= radius; i++) {
                p = pix[yi + Math.min(wm, Math.max(i, 0))];
                sir = stack[i + radius];
                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);
                rbs = r1 - Math.abs(i);
                rsum += sir[0] * rbs;
                gsum += sir[1] * rbs;
                bsum += sir[2] * rbs;
                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }
            }
            stackpointer = radius;

            for (x = 0; x < w; x++) {

                r[yi] = dv[rsum];
                g[yi] = dv[gsum];
                b[yi] = dv[bsum];

                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;

                stackstart = stackpointer - radius + div;
                sir = stack[stackstart % div];

                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];

                if (y == 0) {
                    vmin[x] = Math.min(x + radius + 1, wm);
                }
                p = pix[yw + vmin[x]];

                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);

                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];

                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;

                stackpointer = (stackpointer + 1) % div;
                sir = stack[(stackpointer) % div];

                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];

                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];

                yi++;
            }
            yw += w;
        }
        for (x = 0; x < w; x++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            yp = -radius * w;
            for (i = -radius; i <= radius; i++) {
                yi = Math.max(0, yp) + x;

                sir = stack[i + radius];

                sir[0] = r[yi];
                sir[1] = g[yi];
                sir[2] = b[yi];

                rbs = r1 - Math.abs(i);

                rsum += r[yi] * rbs;
                gsum += g[yi] * rbs;
                bsum += b[yi] * rbs;

                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }

                if (i < hm) {
                    yp += w;
                }
            }
            yi = x;
            stackpointer = radius;
            for (y = 0; y < h; y++) {
                // Preserve alpha channel: ( 0xff000000 & pix[yi] )
                pix[yi] = (0xff000000 & pix[yi]) | (dv[rsum] << 16) | (dv[gsum] << 8) | dv[bsum];

                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;

                stackstart = stackpointer - radius + div;
                sir = stack[stackstart % div];

                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];

                if (x == 0) {
                    vmin[y] = Math.min(y + r1, hm) * w;
                }
                p = x + vmin[y];

                sir[0] = r[p];
                sir[1] = g[p];
                sir[2] = b[p];

                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];

                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;

                stackpointer = (stackpointer + 1) % div;
                sir = stack[stackpointer];

                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];

                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];

                yi += w;
            }
        }

        bitmap.setPixels(pix, 0, w, 0, 0, w, h);

        return (bitmap);
    }


    public static Bitmap getBitMBitmap(String urlpath) {
        Bitmap map = null;
        try {
            URL url = new URL(urlpath);
            URLConnection conn = url.openConnection();
            conn.connect();
            InputStream in;
            in = conn.getInputStream();
            map = BitmapFactory.decodeStream(in);
            // TODO Auto-generated catch block
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }


    public static void setPokerImage(Context context, String pukeName, ImageView imageView) {
        imageView.setVisibility(View.VISIBLE);
        if ("club2".equals(pukeName)) {
            imageView.setImageResource(R.mipmap.club2);
        } else if ("club3".equals(pukeName)) {
            imageView.setImageResource(R.mipmap.club3);
        } else if ("club4".equals(pukeName)) {
            imageView.setImageResource(R.mipmap.club4);
        } else if ("club5".equals(pukeName)) {
            imageView.setImageResource(R.mipmap.club5);
        } else if ("club6".equals(pukeName)) {
            imageView.setImageResource(R.mipmap.club6);
        } else if ("club7".equals(pukeName)) {
            imageView.setImageResource(R.mipmap.club7);
        } else if ("club8".equals(pukeName)) {
            imageView.setImageResource(R.mipmap.club8);
        } else if ("club9".equals(pukeName)) {
            imageView.setImageResource(R.mipmap.club9);
        } else if ("club10".equals(pukeName)) {
            imageView.setImageResource(R.mipmap.club10);
        } else if ("clubJ".equals(pukeName)) {
            imageView.setImageResource(R.mipmap.club11);
        } else if ("clubQ".equals(pukeName)) {
            imageView.setImageResource(R.mipmap.club12);
        } else if ("clubK".equals(pukeName)) {
            imageView.setImageResource(R.mipmap.club13);
        } else if ("clubA".equals(pukeName)) {
            imageView.setImageResource(R.mipmap.club1);
        } else if ("diamond2".equals(pukeName)) {
            imageView.setImageResource(R.mipmap.diamond2);
        } else if ("diamond3".equals(pukeName)) {
            imageView.setImageResource(R.mipmap.diamond3);
        } else if ("diamond4".equals(pukeName)) {
            imageView.setImageResource(R.mipmap.diamond4);
        } else if ("diamond5".equals(pukeName)) {
            imageView.setImageResource(R.mipmap.diamond5);
        } else if ("diamond6".equals(pukeName)) {
            imageView.setImageResource(R.mipmap.diamond6);
        } else if ("diamond7".equals(pukeName)) {
            imageView.setImageResource(R.mipmap.diamond7);
        } else if ("diamond8".equals(pukeName)) {
            imageView.setImageResource(R.mipmap.diamond8);
        } else if ("diamond9".equals(pukeName)) {
            imageView.setImageResource(R.mipmap.diamond9);
        } else if ("diamond10".equals(pukeName)) {
            imageView.setImageResource(R.mipmap.diamond10);
        } else if ("diamondJ".equals(pukeName)) {
            imageView.setImageResource(R.mipmap.diamond11);
        } else if ("diamondQ".equals(pukeName)) {
            imageView.setImageResource(R.mipmap.diamond12);
        } else if ("diamondK".equals(pukeName)) {
            imageView.setImageResource(R.mipmap.diamond13);
        } else if ("diamondA".equals(pukeName)) {
            imageView.setImageResource(R.mipmap.diamond1);
        } else if ("heart2".equals(pukeName)) {
            imageView.setImageResource(R.mipmap.heart2);
        } else if ("heart3".equals(pukeName)) {
            imageView.setImageResource(R.mipmap.heart3);
        } else if ("heart4".equals(pukeName)) {
            imageView.setImageResource(R.mipmap.heart4);
        } else if ("heart5".equals(pukeName)) {
            imageView.setImageResource(R.mipmap.heart5);
        } else if ("heart6".equals(pukeName)) {
            imageView.setImageResource(R.mipmap.heart6);
        } else if ("heart7".equals(pukeName)) {
            imageView.setImageResource(R.mipmap.heart7);
        } else if ("heart8".equals(pukeName)) {
            imageView.setImageResource(R.mipmap.heart8);
        } else if ("heart9".equals(pukeName)) {
            imageView.setImageResource(R.mipmap.heart9);
        } else if ("heart10".equals(pukeName)) {
            imageView.setImageResource(R.mipmap.heart10);
        } else if ("heartJ".equals(pukeName)) {
            imageView.setImageResource(R.mipmap.heart11);
        } else if ("heartQ".equals(pukeName)) {
            imageView.setImageResource(R.mipmap.heart12);
        } else if ("heartK".equals(pukeName)) {
            imageView.setImageResource(R.mipmap.heart13);
        } else if ("heartA".equals(pukeName)) {
            imageView.setImageResource(R.mipmap.heart1);
        } else if ("spade2".equals(pukeName)) {
            imageView.setImageResource(R.mipmap.spade2);
        } else if ("spade3".equals(pukeName)) {
            imageView.setImageResource(R.mipmap.spade3);
        } else if ("spade4".equals(pukeName)) {
            imageView.setImageResource(R.mipmap.spade4);
        } else if ("spade5".equals(pukeName)) {
            imageView.setImageResource(R.mipmap.spade5);
        } else if ("spade6".equals(pukeName)) {
            imageView.setImageResource(R.mipmap.spade6);
        } else if ("spade7".equals(pukeName)) {
            imageView.setImageResource(R.mipmap.spade7);
        } else if ("spade8".equals(pukeName)) {
            imageView.setImageResource(R.mipmap.spade8);
        } else if ("spade9".equals(pukeName)) {
            imageView.setImageResource(R.mipmap.spade9);
        } else if ("spade10".equals(pukeName)) {
            imageView.setImageResource(R.mipmap.spade10);
        } else if ("spadeJ".equals(pukeName)) {
            imageView.setImageResource(R.mipmap.spade11);
        } else if ("spadeQ".equals(pukeName)) {
            imageView.setImageResource(R.mipmap.spade12);
        } else if ("spadeK".equals(pukeName)) {
            imageView.setImageResource(R.mipmap.spade13);
        } else if ("spadeA".equals(pukeName)) {
            imageView.setImageResource(R.mipmap.spade1);
        }

    }

    /**
     * 图片产生倒影
     *
     * @param
     * @param context
     * @return
     */
    public static Bitmap getReverseBitmapById(Bitmap bitmap, Context context) {
//        Bitmap sourceBitmap = BitmapFactory.decodeResource(context.getResources(), resId);
        //绘制原图的下一半图片
        Matrix matrix = new Matrix();
        //倒影翻转
        matrix.setScale(1, -1);

        Bitmap inverseBitmap = Bitmap.createBitmap(bitmap, 0, bitmap.getHeight() / 2, bitmap.getWidth(), bitmap.getHeight() / 3, matrix, false);
        //合成图片
        Bitmap groupbBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight() + bitmap.getHeight() / 3 + 60, bitmap.getConfig());
        //以合成图片为画布
        Canvas gCanvas = new Canvas(groupbBitmap);
        //将原图和倒影图片画在合成图片上
        gCanvas.drawBitmap(bitmap, 0, 0, null);
        gCanvas.drawBitmap(inverseBitmap, 0, bitmap.getHeight() + 50, null);
        //添加遮罩
        Paint paint = new Paint();
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        LinearGradient shader = new LinearGradient(0, bitmap.getHeight() + 50, 0,
                groupbBitmap.getHeight(), Color.BLACK, Color.TRANSPARENT, tileMode);
        paint.setShader(shader);
        //这里取矩形渐变区和图片的交集
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        gCanvas.drawRect(0, bitmap.getHeight() + 50, bitmap.getWidth(), groupbBitmap.getHeight(), paint);
        return groupbBitmap;
    }
}
