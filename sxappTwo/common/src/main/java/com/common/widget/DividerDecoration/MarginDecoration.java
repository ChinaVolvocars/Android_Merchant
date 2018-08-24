package com.common.widget.DividerDecoration;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.common.R;

public class MarginDecoration extends RecyclerView.ItemDecoration
{
    private Context context;

    private int margin;
    private int itemCount = 2;
    private int headVsize = 1;
    private boolean isShowTop;
    private boolean isNormal;
    private int orientation;              // 方向

    // 供单级列表使用
    public MarginDecoration(int orientation, int margin) {
        this.orientation = orientation;
        if (margin > 0) { this.margin = margin; }
        this.margin = margin > 0 ? margin : 15;
    }

    // 供单级列表使用，设置第一个item是否添加分割线
    public MarginDecoration(int orientation, int margin, int headVsize) {
        this.orientation = orientation;
        if (margin > 0) { this.margin = margin; }
        this.margin = margin > 0 ? margin : 15;
    }

    /**
     * 供多级列表使用
     * @param itemCount 列数
     */
    public MarginDecoration(Context context, int itemCount, int headVsize) {
        this.context = context;
        this.margin = margin > 0 ? margin : (int) context.getResources().getDimension(R.dimen.view_size_10);
        if (itemCount > this.itemCount) {  this.itemCount = itemCount; }
        this.headVsize += headVsize;
    }

    /**
     * @param orientation     列表方向
     * @param margin          分割线尺寸
     * @param isShowTop      第一条是否添加分割线
     */
    public MarginDecoration(int orientation, int margin, boolean isShowTop) {
        this.orientation = orientation;
        this.isShowTop = isShowTop;
        if (margin > 0) { this.margin = margin; }
        this.margin = margin > 0 ? margin : 1 ;
    }

    private int lastPosition;
    private int currentPosition;
    private int current;

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state)
    {
        final RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        //整个RecyclerView最后一个item的position
        lastPosition = state.getItemCount() - 1;
        //获取当前要进行布局的item的position
        current = parent.getChildLayoutPosition(view);

        if (current == -1) return;//holder出现异常时，可能为-1

        if (parent.getLayoutManager() instanceof StaggeredGridLayoutManager) {
            outRect.left = 0;
            outRect.right = 0;
            outRect.top = 0;
            outRect.bottom = 0;
            if (current < ((StaggeredGridLayoutManager) parent.getLayoutManager()).getSpanCount()) {
                // 利用item的margin配合RecyclerView的margin值使得间隔相等，这里只需设第一行item的相对顶部的高度
                outRect.top = margin;
            }
        } else if (parent.getLayoutManager() instanceof GridLayoutManager) {

            if (!isNormal) {
                setOutRect(outRect);
            } else {
//                outRect.left = margin;
//                outRect.right = margin;
//                outRect.top = margin;
//                outRect.bottom = margin;
//                setOtherRect(outRect, parent, view);
            }

        } else if (layoutManager instanceof LinearLayoutManager) { // LinearLayoutManager
            if (orientation == LinearLayoutManager.VERTICAL)
            {
                // 垂直
                if ((current < headVsize && !isShowTop) || current == lastPosition) {    // 判断为第一项或最后一项
                    outRect.set(0, 0, 0, 0);
                } else {
                    outRect.set(0, margin, 0, 0);
                }
            } else {
                // 水平
                if (current == lastPosition || current == 0) {    // 判断为第一项或最后一项
                    outRect.set(0, 0, 0, 0);
                } else {
                    outRect.set(0, 0, margin, 0);
                }
            }
        }
    }

    private void setOtherRect(Rect outRect, RecyclerView parent, View view) {
        GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
        //判断总的数量是否可以整除
        int totalCount = layoutManager.getItemCount();
        int surplusCount = totalCount % layoutManager.getSpanCount();
        int childPosition = parent.getChildAdapterPosition(view);
        if (layoutManager.getOrientation() == GridLayoutManager.VERTICAL) {//竖直方向的
            if (surplusCount == 0 && childPosition > totalCount - layoutManager.getSpanCount() - 1) {
                //后面几项需要bottom
                outRect.bottom = margin;
            } else if (surplusCount != 0 && childPosition > totalCount - surplusCount - 1) {
                outRect.bottom = margin;
            }
            if ((childPosition + 1) % layoutManager.getSpanCount() == 0) {//被整除的需要右边
                outRect.right = margin / 8;
            }
            outRect.top = margin;
            outRect.left = margin / 8;
        } else {
            if (surplusCount == 0 && childPosition > totalCount - layoutManager.getSpanCount() - 1) {
                //后面几项需要右边
                outRect.right = margin;
            } else if (surplusCount != 0 && childPosition > totalCount - surplusCount - 1) {
                outRect.right = margin;
            }
            if ((childPosition + 1) % layoutManager.getSpanCount() == 0) {//被整除的需要下边
                outRect.bottom = margin;
            }
            outRect.top = margin;
            outRect.left = margin;
        }
    }

    // 设置item间隔
    // 头部有两个viewgroup
    // 整体两边间隔是中间间隔的两倍
    private void setOutRect(Rect outRect)
    {
        outRect.top = (margin);
        outRect.bottom = 0;
        outRect.left = (margin / 8);
        outRect.right = (margin / 8);

        // 头部不间隔
        if (current < headVsize) {
            outRect.left = 0;
            outRect.right = 0;
            outRect.top = 0;
            outRect.bottom = 0;
        } else {      // 除去头部剩下的item
            currentPosition = current - headVsize;
            if (current % itemCount == 0) {      // 最左边的item
                outRect.left = margin / 4;
            } else if (current % itemCount == itemCount - 1) {
                outRect.right = (margin / 4);
            }
        }
    }
}