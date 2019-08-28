package shangri.example.com.shangri.ui.view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 设置RecyclerView的间距
 * Created by yanggz888 on 2017/6/24.
 */

public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

    private int halfSpace;

    public GridSpacingItemDecoration(int space) {
        this.halfSpace = space / 2;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        if (parent.getPaddingLeft() != halfSpace) {
            parent.setPadding(halfSpace, 0, halfSpace, halfSpace);
            parent.setClipToPadding(false);
        }

        outRect.top = 0;
        outRect.bottom = halfSpace;
        outRect.left = halfSpace;
        outRect.right = halfSpace;
    }
}
