package com.common.widget.linearlayout.addnumberview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.common.R;

public class AddTextView extends View {
    Paint mPaint; //画笔
    public AddTextView(Context context) {
        super(context);
    }
    public AddTextView(Context context, AttributeSet attrs){
        super(context, attrs);
        mPaint = new Paint();
        //TypedArray是一个用来存放由context.obtainStyledAttributes获得的属性的数组
        //在使用完成后，调用recycle方法
        //属性的名称是styleable中的名称+“_”+属性名称
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.AddTextView);
        int textColor = array.getColor(R.styleable.AddTextView_textColor, 0XFF00FF00); //提供默认值，放置未指定
        float textSize = array.getDimension(R.styleable.AddTextView_textSize, 12);
        mPaint.setColor(textColor);
        mPaint.setTextSize(textSize);
        array.recycle();
    }
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        mPaint.setStyle(Paint.Style.FILL); //设置填充
        canvas.drawRect(10, 10, 100, 100, mPaint); //绘制矩形
        mPaint.setColor(Color.BLUE);
        canvas.drawText("自定义TextView", 10, 40, mPaint);
    }
}