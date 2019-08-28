package shangri.example.com.shangri.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import shangri.example.com.shangri.util.DensityUtil;

/**
 *
 * Created by chengaofu on 2017/7/2.
 */

public class FixedGridLayout extends ViewGroup {
    int mCellWidth;
    int mCellHeight;

    public FixedGridLayout(Context context) {
        super(context);
    }

    public FixedGridLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mCellWidth = (DensityUtil.getScreenWidth(context) -
                     DensityUtil.px2dip(context, 15)*2 -
                     DensityUtil.px2dip(context, 23)*3) / 4;
        mCellHeight = DensityUtil.px2dip(context, 69);
    }

    public void setCellWidth(int px) {
        mCellWidth = px;
        requestLayout();
    }

    public void setCellHeight(int px) {
        mCellHeight = px;
        requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int cellWidthSpec = MeasureSpec.makeMeasureSpec(mCellWidth,
                MeasureSpec.AT_MOST); //控件的最大宽度
        int cellHeightSpec = MeasureSpec.makeMeasureSpec(mCellHeight,
                MeasureSpec.AT_MOST); //控件的最大高度

        int count = getChildCount();
        for (int index=0; index<count; index++) {
            final View child = getChildAt(index);
            child.measure(cellWidthSpec, cellHeightSpec); //设置控件的宽度和高度
        }
        // Use the size our parents gave us, but default to a minimum size to avoid
        // clipping transitioning children
//        int minCount =  count > 3 ? count : 3;
        int minCount =  count > 4 ? 4 : count ;
        setMeasuredDimension(resolveSize(mCellWidth * minCount, widthMeasureSpec), //计算当前视图的宽高
                resolveSize(mCellHeight * minCount, heightMeasureSpec));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int cellWidth = mCellWidth;
        int cellHeight = mCellHeight;
        int columns = (r - l) / cellWidth; //列数
        if (columns < 0) {
            columns = 1;
        }
        int x = 0;
        int y = 0;
        int i = 0;
        int count = getChildCount(); //总数
        for (int index=0; index<count; index++) {
            final View child = getChildAt(index);

            int w = child.getMeasuredWidth();
            int h = child.getMeasuredHeight();

            int left = x + ((cellWidth-w)/2);//左边距
            int top = y + ((cellHeight-h)/2);//上边距

            child.layout(left, top, left+w, top+h);

            if (i >= (columns-1)) {
                // advance to next row
                i = 0;
                x = 0;
                y += cellHeight;
            } else {
                i++;
                x += cellWidth;
            }
        }
    }
}
