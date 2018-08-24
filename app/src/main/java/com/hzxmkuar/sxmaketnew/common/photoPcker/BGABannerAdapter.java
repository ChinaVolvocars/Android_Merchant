package com.hzxmkuar.sxmaketnew.common.photoPcker;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.common.widget.guideview.BGABanner;
import com.common.widget.imageview.image.ImageLoaderUtils;

public class BGABannerAdapter implements BGABanner.Adapter
{
    private Context context;

    public BGABannerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public void fillBannerItem(BGABanner banner, View view, Object model, int position) {
//        SlideBean beanList = (SlideBean) model;
//        if (EmptyUtils.isNotEmpty(beanList.getPic())) {
//            ImageLoaderUtils.display((ImageView) view, beanList.getPic());
//        } else {
//            ImageLoaderUtils.display((ImageView) view, R.mipmap.default_pic);
//        }

        ImageLoaderUtils.display((ImageView) view, (String) model);
    }
}
