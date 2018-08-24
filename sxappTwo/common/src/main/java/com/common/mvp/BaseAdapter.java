package com.common.mvp;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.widget.imageview.image.ImageLoaderUtils;
import com.common.utils.EmptyUtils;
import com.common.utils.StringUtils;


/**
 * BaseAdapter公共RecyclerView适配器
 * Created by 小俞 on 2017/1/12 0012.
 */

public abstract class BaseAdapter<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T> {

    @Override
    public T onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(T holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    /**
     * 填充文本兼容空对象
     *
     * @param textView textview
     * @param content  content
     */
    public void setTextView(TextView textView, String content) {
        if (EmptyUtils.isNull(textView)) {
            return;
        }
        textView.setText(StringUtils.nullToStr(content));
    }

    /**
     * 填充图片兼容空对象
     *
     * @param imageview imageview
     * @param url       content
     */
    public void setImageView(ImageView imageview, String url) {
        if (EmptyUtils.isNull(imageview)) {
            return;
        }
        ImageLoaderUtils.display(imageview, StringUtils.nullToStr(url));
    }
}
