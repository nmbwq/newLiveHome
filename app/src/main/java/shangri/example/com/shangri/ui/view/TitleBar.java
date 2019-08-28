package shangri.example.com.shangri.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import shangri.example.com.shangri.R;

/**
 * 标题栏
 * Created by chengaofu on 2017/6/20.
 */

public class TitleBar extends RelativeLayout {

    protected ImageView leftImage;
    protected RelativeLayout rightLayout;
    protected ImageView rightImage;
    protected TextView rightTV;
    protected TextView titleView;
    protected RelativeLayout titleLayout;

    public TitleBar(Context context, AttributeSet attrs, int defStyle) {
        this(context, attrs);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TitleBar(Context context) {
        super(context);
        init(context, null);
    }

    private void init(Context context, AttributeSet attrs){
        LayoutInflater.from(context).inflate(R.layout.widget_title_bar, this);
        leftImage = findViewById(R.id.left_image);
        rightLayout = findViewById(R.id.right_layout);
        rightImage = findViewById(R.id.right_image);
        rightTV = findViewById(R.id.right_tv);
        titleView = findViewById(R.id.title);
        titleLayout = findViewById(R.id.root);

        parseStyle(context, attrs);
    }

    private void parseStyle(Context context, AttributeSet attrs){
        if(attrs != null){
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);
            String title = ta.getString(R.styleable.TitleBar_titleBarTitle);
            titleView.setText(title);

            Drawable leftDrawable = ta.getDrawable(R.styleable.TitleBar_titleBarLeftImage);
            if (null != leftDrawable) {
                leftImage.setImageDrawable(leftDrawable);
            }

            Drawable background = ta.getDrawable(R.styleable.TitleBar_titleBarBackground);
            if(null != background) {
                titleLayout.setBackgroundDrawable(background);
            }

            ta.recycle();
        }
    }

    /**
     * 设置标题最左边图片，一般为返回
     * @param resId
     */
    public void setLeftImageResource(int resId) {
        leftImage.setImageResource(resId);
    }

    /**
     * 设置最右边图片，一般为跳转到下个页面的指示
     * @param resId
     */
    public void setRightImageResource(int resId) {
        rightImage.setImageResource(resId);
    }

    /**
     * 设置右边的文本
     * @param text
     */
    public void setRightText(String text){rightTV.setText(text);}

    /**
     * 设置左边图片的点击事件
     * @param listener
     */
    public void setLeftImageClickListener(OnClickListener listener){
        leftImage.setOnClickListener(listener);
    }

    /**
     * 设置右边图片的点击事件
     * @param listener
     */
    public void setRightLayoutClickListener(OnClickListener listener){
        rightLayout.setOnClickListener(listener);
    }

    /**
     * 设置左边图片的显示，默认隐藏
     * @param visibility
     */
    public void setLeftImageVisibility(int visibility){
        leftImage.setVisibility(visibility);
    }

    /**
     * 设置右边图片的显示
     * @param visibility
     */
    public void setRightLayoutVisibility(int visibility){
        rightLayout.setVisibility(visibility);
    }

    /**
     * 设置右边文本的显示
     * @param visibility
     */
    public void setRightTvVisibility(int visibility){
        rightTV.setVisibility(visibility);
    }

    public void setTitle(String title){
        titleView.setText(title);
    }

    /**
     * 设置整个标题栏的背景颜色
     * @param color
     */
    public void setBackgroundColor(int color){
        titleLayout.setBackgroundColor(color);
    }


    /**
     * 返回右边布局的view
     * @return view
     */
    public RelativeLayout getRightLayout(){
        return rightLayout;
    }

    public  void setRightLayoutBackgroundColor(int color){
        rightLayout.setBackgroundResource(color);
    }
}
