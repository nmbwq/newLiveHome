package shangri.example.com.shangri.ui.view;

import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.model.bean.ChartMarkViewBean;

/**
 * Created by Administrator on 2018/8/3.
 */

public class MyMarkerView extends MarkerView {

    private TextView tvContent;

    public MyMarkerView(Context context) {
        super(context, R.layout.mark_view);

        tvContent = findViewById(R.id.tvContent);
    }

    // callbacks everytime the MarkerView is redrawn, can be used to update the
    // content (user-interface) 每次 MarkerView 重绘此方法都会被调用，并为您提供更新它显示的内容的机会
    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        //这里就设置你想显示到makerview上的数据，Entry可以得到X、Y轴坐标，也可以e.getData()获取其他你设置的数据
//        tvContent.setText("" + Utils.formatNumber(e.getX(), 0, true));
        ChartMarkViewBean data = (ChartMarkViewBean) e.getData();
        //没有设置y值的直接取纵坐标 （可左滑右划有y值 主播明细详情界面没有）
        if (data.getY().length() == 0) {
            tvContent.setText(data.getMarkdate() + data.getSymbol() + e.getY());
        } else {
            tvContent.setText(data.getMarkdate() + data.getSymbol() + data.getY());
        }

        super.refreshContent(e, highlight);
    }


    /*
     * offset 是以點到的那個點作為 (0,0) 中心然後往右下角畫出來 该方法是让markerview现实到坐标的上方
     * 所以如果要顯示在點的上方
     * X=寬度的一半，負數
     * Y=高度的負數
     */
    @Override
    public MPPointF getOffset() {
        // Log.e("ddd", "width:" + (-(getWidth() / 2)) + "height:" + (-getHeight()));
        return new MPPointF(-(getWidth() / 2), -(getHeight() + 50));
    }


}
