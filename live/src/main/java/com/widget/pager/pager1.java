package com.widget.pager;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.example.live.R;

/**
 * Created by Administrator on 2016/8/29.
 */
public class pager1 extends BasePager {
    private Context context;
    public pager1(Context context, FragmentActivity activity, String hx_chatRoomid, String fid, String name, String face,String title) {
        super(context,activity,hx_chatRoomid,fid,title);
        this.context=context;
    }

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.pager2, null);
        return view;
    }

    @Override
    public void initData() {

    }

}
