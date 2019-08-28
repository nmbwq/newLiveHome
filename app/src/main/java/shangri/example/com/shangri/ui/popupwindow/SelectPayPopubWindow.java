package shangri.example.com.shangri.ui.popupwindow;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import shangri.example.com.shangri.R;

/**
 * Description:选择支付方式
 * Data：2018/11/16-16:55
 * Author: lin
 */
public class SelectPayPopubWindow extends BasePopupWindow {
    private Context mContext;
    public SelectPayPopubWindow(Context context, String bottomContent, OnSelector face) {
        super(context, bottomContent);
        this.mContext = context;
        this.face = face;
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setFocusable(true);// 取得焦点
        //注意  要是点击外部空白处弹框消息  那么必须给弹框设置一个背景色  不然是不起作用的
        setBackgroundDrawable(new BitmapDrawable());
        //点击外部消失
        setOutsideTouchable(true);
        //设置可以点击
        setTouchable(true);
        //进入退出的动画
        setAnimationStyle(R.style.mypopwindow_anim_style);
        if (mContentView != null) {
            //从底部显示
            showAtLocation(mContentView, Gravity.BOTTOM, 0, 0);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_select_pay;
    }
    int pay_type = 2;
    @Override
    protected void confirmClick() {
        if (mContentView == null) return;
        TextView go_buy = mContentView.findViewById(R.id.go_buy);
        final ImageView weixinSelect = mContentView.findViewById(R.id.weixin_select);
        final ImageView alipaySelect = mContentView.findViewById(R.id.alipay_select);
        RelativeLayout rl_alipay = mContentView.findViewById(R.id.rl_alipay);
        RelativeLayout rl_weixin = mContentView.findViewById(R.id.rl_weixin);
        go_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                face.onSelector(pay_type);
            }
        });
        rl_weixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weixinSelect.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.xuanzhong_3));
                alipaySelect.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.wei_xuanzhong));
                pay_type = 2;
            }
        });
        rl_alipay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weixinSelect.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.wei_xuanzhong));
                alipaySelect.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.xuanzhong_3));
                pay_type = 1;
            }
        });



    }
    OnSelector face;
    public interface OnSelector{
        void onSelector(int pos);
    }

}
