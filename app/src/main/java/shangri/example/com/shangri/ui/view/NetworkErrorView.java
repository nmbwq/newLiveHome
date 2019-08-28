package shangri.example.com.shangri.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import shangri.example.com.shangri.Constant;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * 网络加载失败
 * Created by chengaofu on 2017/7/18.
 */

public class NetworkErrorView extends LinearLayout implements View.OnClickListener{
    public NetworkErrorView(Context context) {
        super(context);
    }

    public NetworkErrorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NetworkErrorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void show(){
        LayoutInflater.from(getContext()).inflate(R.layout.layout_network_break, this, true);
        setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View view) {

        //发送通知
        EventBus.getDefault().postSticky(new BaseEvent(Constant.EVENT_NETWORK_ENTRY, new Object()));
    }
}
