package com.hzxmkuar.sxmaketnew.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

public class HorizontalScrollTextView extends android.support.v7.widget.AppCompatTextView implements View.OnClickListener {
  private float textLength = 0f;// 文本长度
  private float viewWidth = 0f;//文本控件的长度
  private float step = 0f;// 文本的横坐标
  private float y = 0f;// 文本的纵坐标
  public boolean isStarting = false;// 是否开始滚动
  private Paint paint = null;
  private String text = "";// 文本内容
  private OnScrollCompleteListener onScrollCompleteListener;//滚动结束监听

  public HorizontalScrollTextView(Context context) {
     super(context);
     initView();
  }

  public HorizontalScrollTextView(Context context, AttributeSet attrs) {
       super(context, attrs);
       initView();
    }

  public HorizontalScrollTextView(Context context, AttributeSet attrs, int defStyle) {
       super(context, attrs, defStyle);
       initView();
  }

 public OnScrollCompleteListener getOnSrollCompleteListener() {
       return onScrollCompleteListener;
 } 

 public void setOnScrollCompleteListener(OnScrollCompleteListener  onScrollCompleteListener){
      this.onScrollCompleteListener = onScrollCompleteListener;
}

 private void initView() {
        setOnClickListener(this);
 }

 public void init(WindowManager windowManager) {
   paint = getPaint();
   //设置滚动字体颜色
   paint.setColor(Color.parseColor("#ffffff"));
   text = getText().toString();
   textLength = paint.measureText(text);
   viewWidth = getWidth();
   if (viewWidth == 0) {
     if (windowManager != null) {
        Display display = windowManager.getDefaultDisplay();
        viewWidth = display.getWidth();
       }
      }
        y = getTextSize() + getPaddingTop();
    }
    //开启滚动
  public void startScroll() {
     isStarting = true;
     invalidate();
  }
  //停止滚动
  public void stopScroll() {
     isStarting = false;
     invalidate();
  }

  @Override
  public void onDraw(Canvas canvas) {
       canvas.drawText(text,  - step, y, paint);
       if (!isStarting) {
           return;
        }
       step += 2.0;// 2.0为文字的滚动速度
       //判断是否滚动结束
       if (step > textLength){
           step = 0;
           onScrollCompleteListener.onScrollComplete();
       }
         invalidate();
   }

 //控制点击停止或者继续运行
  @Override
  public void onClick(View v) {
          if (isStarting)
            stopScroll();
          else
            startScroll();
   }

   public interface OnScrollCompleteListener {
         void onScrollComplete();
   }
}