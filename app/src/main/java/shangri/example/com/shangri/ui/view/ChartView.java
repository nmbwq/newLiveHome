package shangri.example.com.shangri.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import shangri.example.com.shangri.Constants;
import shangri.example.com.shangri.R;

/**
 * Created by Administrator on 2018/1/12.
 * http://blog.csdn.net/namesuper/article/details/51219752
 *
 *
 */

public class ChartView extends View {
    private Context context;

    /** 原点的X坐标 */
    public float XPoint = 80;
    /** 原点的Y坐标 */
    public float YPoint = 260;
    /** X的刻度长度 */
    public float XScale = 55;
    /** Y的刻度长度 */
    public float YScale = 40;
    /** X轴的长度 */
    public float XLength = 380;

    /** 横线间的距离 */
    public float YBetween;

    /** x刻度间的距离 */
    public float XBetween;

    /** 七个点的x坐标 */
    private List<Float> c1x= new ArrayList<>();
    /** 七个点的y坐标 */
    private List<Float> c1y= new ArrayList<>();

    // 可移动的圆点
    private float cx, cy;
    private boolean isMove;
    private float downX, downY;
    private List<Float> k1 = new ArrayList<>();// y=kx+b;
    private List<Float> b1= new ArrayList<>();

    //    private String txty1, txty2, txty3, txty4, txty5;
    private List<String> txtx= new ArrayList<>();
    List<String> txty= new ArrayList<>();
    private List<String> values= new ArrayList<>();
    // 移动的值
    private String value = "0.00";

    public float XScaleY;

    /** Y轴的长度 */
    public float YLength = 240; // Y轴的长度
    /** X的刻度 */
    public String[] XLabel; // X的刻度
    /** Y的刻度 */
    public String[] YLabel; // Y的刻度
    /** 数据 */
    public String[] Data; // 数据
    /** 显示的标题 */
    public String Title; // 显示的标题
    private String isWeek ="day";

    public ChartView(Context context) {

        super(context);
        this.context = context;
    }

    public ChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    public ChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public void setData( List<String> txtx,  List<String> txty,  List<String> values, String b) {
        isWeek = b;
        this.txtx.clear();
        this.values.clear();
        this.txty.clear();
        c1x.clear();
        c1y.clear();
        k1.clear();
        b1.clear();
        if (values.size()==0){
            return;
        }
        else if (values.size()==1){
            this.txtx.add(0,txtx.get(0));
            this.values.add(0,values.get(0));
            this.txty.add(0,txty.get(0));

            this.txtx.add(0,"0");
            this.values.add(0,"0");
            this.txty.add(0,"0");

        }
        this.txtx = txtx;
        this.values = values;
        this.txty= txty;
//        for (int i=0;i<txtx.length;i++){
//            this.txtx.add(i,txtx[i]);
//            this.values.add(i,values[i]);
//        }
//
//        for (int i=0;i<txty.length;i++){
//            this.txty.add(i,txty[i]);
//
//        }
        invalidate();
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);// 重写onDraw方法
        if (txtx.size()> 0) {
            c1x.clear();
            c1y.clear();
            k1.clear();
            b1.clear();
            setPositions();
// canvas.drawColor(Color.WHITE);//设置背景颜色
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.STROKE);
            paint.setAntiAlias(true);// 去锯齿
            paint.setColor(context.getResources().getColor(R.color.gray));// 颜色
            Paint paint1 = new Paint();
            paint1.setStyle(Paint.Style.STROKE);
            paint1.setAntiAlias(true);// 去锯齿
            paint1.setColor(context.getResources().getColor(R.color.text_color_little_orange));
            int lineWidth = Constants.SCREEN_WIDTH * 3 / 1080;
            paint1.setStrokeWidth(lineWidth);
            paint1.setStrokeWidth(2);

            int textSize = Constants.SCREEN_WIDTH * 30 / 1080;


            Paint paint4 = new Paint();
            paint4.setStyle(Paint.Style.STROKE);
            paint4.setAntiAlias(true);// 去锯齿
            paint4.setColor(context.getResources().getColor(R.color.text_color_line));
            int lineWidth2 = Constants.SCREEN_WIDTH * 3 / 1080/2;
            paint4.setStrokeWidth(lineWidth2);

            paint.setTextSize(textSize); // 设置轴文字大小


            float hei = YBetween * (txty.size()-1);
            float top = Float.parseFloat(txty.get(txty.size()-1));
            float bottom = Float.parseFloat(txty.get(0));
            for (int i=0;i<values.size();i++){
                if (txtx.size()>7){
                    c1x.add(i, XScale + (XBetween-30)*(i)/(txtx.size()/6));
                }
                else {
                    c1x.add(i, XScale + XBetween*(i));
                }

            }
            for (int i=0;i<values.size();i++){
//                c1y.add(i,(float) (hei * (top - Float.parseFloat(values.get(i))) / (top - bottom) + (YPoint - YBetween * txty.size()-1)));// 5-4.625);
                c1y.add(i,YPoint- (hei*(Float.parseFloat(values.get(i))/top)));//
            }

// 设置Y轴   paint.
            canvas.drawLine(XPoint, YPoint - YBetween * (txty.size()-1) - Constants.SCREEN_HEIGHT * 35 / 1230, XPoint, YPoint, paint4);
// 设置X轴

            for (int i=0;i<txty.size();i++){
                canvas.drawLine(XPoint, YPoint - YBetween*(i), XPoint + XLength, YPoint - YBetween*(i), paint4); // 轴线1
            }
            for (int i=0;i<txty.size();i++){
                canvas.drawText(txty.get(i), YScale, (YPoint - YBetween * i) + 8, paint);// y刻度4
            }
            int j = (txtx.size())/6;
            int k = j;
            for (int i = 0; i < txtx.size(); i++) {
                if (isWeek.equals("whole_week")) {
                    if (txtx.size() > 3) {
                        if (i == 0 || i == txtx.size() - 1) {
                            canvas.drawText(txtx.get(i), c1x.get(i) - 5, XScaleY, paint);//
                        }
                    } else {
                        canvas.drawText(txtx.get(i), c1x.get(i) - XBetween / 2 - 20, XScaleY, paint);// x刻度0
                    }
                } else if (isWeek.equals("whole_month")) {
                    if (txtx.size() > 3) {
                        if (i == 0 || i == txtx.size() - 1) {
                            canvas.drawText(txtx.get(i), c1x.get(i) - 5, XScaleY, paint);//
                        }
                        if (i == txtx.size() / 2) {
                            canvas.drawText(txtx.get(i), c1x.get(i) - 5, XScaleY, paint);//
                        }
                    } else {
                        canvas.drawText(txtx.get(i), c1x.get(i) - XBetween / 2 + 30, XScaleY, paint);// x刻度0

                    }
                } else if (isWeek.equals("day")) {
                    if (txtx.size() > 7) {
                        if (i == 0 || i == txtx.size() - 1) {
                            canvas.drawText(txtx.get(i), c1x.get(i) - 5, XScaleY, paint);// x刻度0
                        }
                        if (i == k) {
                            canvas.drawText(txtx.get(i), c1x.get(i) - 5, XScaleY, paint);// x刻度0
                            k = k + j;
                        }
                    } else {
                        canvas.drawText(txtx.get(i), c1x.get(i) - XBetween / 2 + 30, XScaleY, paint);// x刻度0

                    }
                }
            }


            paint1.setStyle(Paint.Style.FILL);

            int minR = 2;



//            折线图上的点
            for (int i=0;i<c1x.size();i++){
                canvas.drawCircle(c1x.get(i), c1y.get(i), minR, paint1);
            }




            for (int i=0;i<c1x.size();i++){
                canvas.drawPoint(c1x.get(i), c1y.get(i), paint1);
            }

            for (int i=0;i<c1x.size()-1;i++){
                canvas.drawLine(c1x.get(i), c1y.get(i),c1x.get(i+1), c1y.get(i+1), paint1);
                Log.d(String.valueOf("test"+c1x.get(i)),String.valueOf(c1x.get(i+1)));

            }



            for (int i=0;i<c1x.size()-1;i++){
                k1.add(i,getK(XScale + XBetween*(i+1), XScale + XBetween *(i+2), c1y.get(i), c1y.get(i+1)));

            }



            for (int i=0;i<c1x.size()-1;i++){
                b1.add(i,getB(XScale + XBetween * (i+1), c1y.get(i), k1.get(i)));
            }


            Paint paint2 = new Paint();
            paint2.setStyle(Paint.Style.STROKE);
            paint2.setAntiAlias(true);// 去锯齿
            paint2.setColor(Color.WHITE);
            paint2.setTextSize(textSize);

            Paint paint3 = new Paint();
            paint3.setStyle(Paint.Style.STROKE);
            paint3.setAntiAlias(true);// 去锯齿
            paint3.setColor(context.getResources().getColor(R.color.text_color_little_black));  //移动标线文字背景
            paint3.setStyle(Paint.Style.FILL);
            paint3.setTextSize(30);



//            if (cx == 0) {
            cx = c1x.get(c1x.size()-1);
//            }
//            if (cy == 0) {
            cy = c1y.get(c1y.size()-1);
//            }
            if (values.get(values.size()-1) != null) {
                value = values.get(values.size()-1);
            }

            //滑动上线的文字
            canvas.drawText(value, (float) (cx - (textSize * 1.5)), cy - (float) (textSize * 1.4), paint2);
        }

    }

    /**
     * 计算圆的y坐标
     *
     * @param x
     *            圆的x坐标
     * @param k
     *            y=kx+b
     * @param b
     *            y=kx+b
     * @return
     */
    private float getCY(float x, float k, float b) {
        return x * k + b;
    }

    /**
     * 计算直线的k值 y=kx+b
     *
     * @param x1
     *            第一个点的x坐标
     * @param x2
     *            第二个点的x坐标
     * @param y1
     *            第一个点的y坐标
     * @param y2
     *            第二个点的y坐标
     * @return
     */
    private float getK(float x1, float x2, float y1, float y2) {
        return (y2 - y1) / (x2 - x1);
    }

    /**
     * 计算直线的b值 y=kx+b
     *
     * @param x1
     *            任意一点的x坐标
     * @param y1
     *            任意一点的y坐标
     * @param k
     *            直线的k值
     * @return
     */
    private float getB(float x1, float y1, float k) {
        return y1 - k * x1;
    }

    private void setPositions() {
        XPoint = Constants.SCREEN_WIDTH * 157 / 720;
        YPoint = Constants.SCREEN_HEIGHT * 289 / 1230;
        XLength = Constants.SCREEN_WIDTH * 533 / 720;
        YLength = Constants.SCREEN_HEIGHT * 289 / 1230;
        YBetween = Constants.SCREEN_HEIGHT * 70 / 1230;
//        XBetween = (float) (Constants.SCREEN_WIDTH * 66.625 / 720);
        XBetween = (float) (Constants.SCREEN_WIDTH * 66.625 / 550);
        YScale = Constants.SCREEN_WIDTH * 51 / 720;
        XScale = Constants.SCREEN_WIDTH * 157 / 720;
        XScaleY = Constants.SCREEN_HEIGHT * 325 / 1230;
    }
}
