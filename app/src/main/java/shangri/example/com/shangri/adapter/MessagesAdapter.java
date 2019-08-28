package shangri.example.com.shangri.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.response.MessagesBean;
import shangri.example.com.shangri.ui.webview.MessagesWebView;
import shangri.example.com.shangri.util.ViewHolder;

/**
 * Description:消息列表适配器
 * Data：2018/11/4-10:04
 * Author: lin
 */
public class MessagesAdapter extends BaseListAdapter<MessagesBean.MessagesData> {
    private Context mContext;
    private Animation mLikeAnim;

    public MessagesAdapter(Context context, int layoutId, List<MessagesBean.MessagesData> datas,getMsgUrl mGetMsgUrl ) {
        super(context, layoutId, datas);
        this.mContext = context;
        this.mGetMsgUrl = mGetMsgUrl;
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
    }

    @Override
    public void convert(final ViewHolder helper, final MessagesBean.MessagesData messagesData) {
//        final int position = helper.getAdapterPosition();
        helper.setText(R.id.title, messagesData.getTitle());
        helper.setText(R.id.subtitle, messagesData.getSubtitle());
        helper.setText(R.id.current_time, getTime(messagesData.getCreate_time()) + "");
        if (messagesData.getIs_read() == 2) {
            helper.setBackgroundRes(R.id.iv_is_read, R.mipmap.wdxx_xxy);
        } else {
            helper.setBackgroundRes(R.id.iv_is_read, R.mipmap.wdxx_xx);
        }
        LinearLayout ll_detail = helper.getView(R.id.ll_detail);
        ll_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.setBackgroundRes(R.id.iv_is_read, R.mipmap.wdxx_xx);
                mGetMsgUrl.getMesUrl(messagesData.getId(),messagesData.getUrl());
//                Intent intent = new Intent(mContext, MessagesWebView.class);
//                intent.putExtra("url", messagesData.getUrl());
//                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public void convert(ViewHolder helper, MessagesBean.MessagesData messagesData, List<Object> payloads) {

    }

    public String getTime(String time) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd  HH:mm");
        long t = Long.parseLong(time);
        String sd = formatter.format(new Date(t));
        return sd;
    }

    private getMsgUrl mGetMsgUrl;

    public getMsgUrl getmGetMsgUrl() {
        return mGetMsgUrl;
    }

    public void setmGetMsgUrl(getMsgUrl mGetMsgUrl) {
        this.mGetMsgUrl = mGetMsgUrl;
    }

    public interface getMsgUrl{
        void getMesUrl(int id,String url);
    }

}
