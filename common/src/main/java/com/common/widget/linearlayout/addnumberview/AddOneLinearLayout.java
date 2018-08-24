package com.common.widget.linearlayout.addnumberview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.R;

public class AddOneLinearLayout extends LinearLayout
{
    private final static int reduce_number = 1;
    private final static int add_number = 2;
    private ImageButton reduce;
    private TextView numbeText;
    private ImageButton add;
    private int size;
    private NumberClickListener numberClickListener;

    public AddOneLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.layout_base_addreduce, this);
        initView();
        // TypedArray是存放资源的array,1.通过上下文得到这个数组,attrs是构造函数传进来的,对应attrs.xml
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AddOneLinearLayout);
        // 获得xml里定义的属性,格式为 名称_属性名 后面是默认值
        Drawable reduceDrawable = a.getDrawable(R.styleable.AddOneLinearLayout_reductionDrawble);
        if(reduceDrawable != null) {
            reduce.setImageDrawable(reduceDrawable);
        }
        Drawable addDrawable = a.getDrawable(R.styleable.AddOneLinearLayout_addDrawble);
        if(addDrawable != null) {
            add.setImageDrawable(addDrawable);
        }
        // 为了保持以后使用该属性一致性,返回一个绑定资源结束的信号给资源
        a.recycle();

    }

    class ChangeHandler extends Handler {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case reduce_number:
                    if (size < 1) {
                        reduce.setImageResource(R.mipmap.icon_reduce);
                    } else {
                        reduce.setImageResource(R.mipmap.icon_reduce);
                    }
                    numbeText.setText(size + "");
                    break;
                case add_number:
                    if (size < 1) {
                        reduce.setImageResource(R.mipmap.icon_reduce);
                    } else {
                        reduce.setImageResource(R.mipmap.icon_reduce);
                    }
                    numbeText.setText(size + "");
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    }

    private void initView() {
        reduce = (ImageButton) findViewById(R.id.img_reduce);
        numbeText = (TextView) findViewById(R.id.txt_num);
        add = (ImageButton) findViewById(R.id.img_add);
        numbeText.setText("1");
        if (size < 1) {
            reduce.setImageResource(R.mipmap.icon_reduce);
        } else {
            reduce.setImageResource(R.mipmap.icon_reduce);
        }

        reduce.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(size == 0){
                    return;
                }
                size--;
                if (null != numberClickListener) {
                    numberClickListener.onChangeNumber();
                }
                Message msg = new ChangeHandler().obtainMessage();
                msg.what = reduce_number;
                msg.sendToTarget();
            }
        });

        add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                size++;
                if (null != numberClickListener) {
                    numberClickListener.onChangeNumber();
                }
                Message msg = new ChangeHandler().obtainMessage();
                msg.what=add_number;
                msg.sendToTarget();
            }
        });
    }

    public int getNumber(){
        return size;
    }

    public String getNumberStr(){
        return numbeText.getText().toString();
    }

    public void setNumber(String number){
        numbeText.setText(number);
    }

    public void setNumberClickListener(NumberClickListener NumberClickListener){
        this.numberClickListener = NumberClickListener;
    }

    public interface NumberClickListener {
        void onChangeNumber();
    }
}