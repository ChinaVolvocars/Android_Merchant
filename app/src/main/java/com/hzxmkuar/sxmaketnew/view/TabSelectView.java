package com.hzxmkuar.sxmaketnew.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.utils.UIUtils;
import com.hzxmkuar.sxmaketnew.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 关联账号头部选择器
 * Created by jc on 2019/1/4.
 */
public class TabSelectView extends LinearLayout implements View.OnClickListener {
    private Context mContext;
    private LinearLayout ll_left_content;
    private LinearLayout ll_right_content;
    private TextView view_point01;
    private TextView view_point02;
    private TextView view_point03;
    private TextView view_point04;
    private TextView view_point05;
    private TextView view_point06;
    private TextView view_point07;
    private TextView view_point08;
    private TextView tv_left_title;
    private TextView tv_right_title;
    private TextView tv_left_bottom_line;
    private TextView tv_rightt_bottom_line;


    private int selectedPossition = 1;
    private List<TextView> leftPointList = new ArrayList<>();
    private List<TextView> rightPointList = new ArrayList<>();

    public interface OnTabSelectedListener {
        void tabSelected(int tabSlectedPosition);
    }

    private OnTabSelectedListener onTabSelectedListener;

    public void setOnTabSelectedListener(OnTabSelectedListener selectedListener) {
        this.onTabSelectedListener = selectedListener;
    }

    public TabSelectView(Context context) {
        this(context, null);
    }

    public TabSelectView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabSelectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        View rootView = LayoutInflater.from(context).inflate(R.layout.layout_tab_select, this);
        ll_left_content = (LinearLayout) findViewById(R.id.ll_left_content);
        ll_right_content = (LinearLayout) findViewById(R.id.ll_right_content);
        view_point01 = (TextView) findViewById(R.id.view_point01);
        view_point02 = (TextView) findViewById(R.id.view_point02);
        view_point03 = (TextView) findViewById(R.id.view_point03);
        view_point04 = (TextView) findViewById(R.id.view_point04);
        view_point05 = (TextView) findViewById(R.id.view_point05);
        view_point06 = (TextView) findViewById(R.id.view_point06);
        view_point07 = (TextView) findViewById(R.id.view_point07);
        view_point08 = (TextView) findViewById(R.id.view_point08);
        tv_left_title = (TextView) findViewById(R.id.tv_left_title);
        tv_right_title = (TextView) findViewById(R.id.tv_right_title);
        tv_left_bottom_line = (TextView) findViewById(R.id.tv_left_bottom_line);
        tv_rightt_bottom_line = (TextView) findViewById(R.id.tv_rightt_bottom_line);

        ll_left_content.setOnClickListener(this);
        ll_right_content.setOnClickListener(this);
        initLeftPointList();
        initRightPointList();
        setTitleContentState(selectedPossition);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == ll_left_content.getId()) {
            selectedPossition = 1;
        } else if (v.getId() == ll_right_content.getId()) {
            selectedPossition = 2;
        }
        setTitleContentState(selectedPossition);
        if (null != onTabSelectedListener) {
            onTabSelectedListener.tabSelected(selectedPossition);
        }
    }


    private void setTitleContentState(int tabPosition) {
        if (1 == tabPosition) {
            for (TextView textView : leftPointList) {
                textView.setVisibility(VISIBLE);
            }
            tv_left_bottom_line.setVisibility(VISIBLE);


            for (TextView textView : rightPointList) {
                textView.setVisibility(INVISIBLE);
            }
            tv_rightt_bottom_line.setVisibility(INVISIBLE);
            tv_left_title.setTextColor(UIUtils.getColor(R.color.color_fdc70a));
            tv_right_title.setTextColor(UIUtils.getColor(R.color.color_1e1e1e));

        } else {
            for (TextView textView : leftPointList) {
                textView.setVisibility(INVISIBLE);
            }
            tv_left_bottom_line.setVisibility(INVISIBLE);

            for (TextView textView : rightPointList) {
                textView.setVisibility(VISIBLE);
            }
            tv_rightt_bottom_line.setVisibility(VISIBLE);

            tv_right_title.setTextColor(UIUtils.getColor(R.color.color_fdc70a));
            tv_left_title.setTextColor(UIUtils.getColor(R.color.color_1e1e1e));
        }

    }

    private void initLeftPointList() {
        leftPointList.add(view_point01);
        leftPointList.add(view_point02);
        leftPointList.add(view_point03);
        leftPointList.add(view_point04);
    }

    private void initRightPointList() {
        rightPointList.add(view_point05);
        rightPointList.add(view_point06);
        rightPointList.add(view_point07);
        rightPointList.add(view_point08);
    }

}
