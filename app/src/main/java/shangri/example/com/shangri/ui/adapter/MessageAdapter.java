package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.response.MessageResonse;
import shangri.example.com.shangri.util.ViewHolder;

/**
 * Created by Administrator on 2018/1/3.
 */

public class MessageAdapter extends BaseListAdapter<MessageResonse.MessagesBean> {
    private Context mContext;
    private Animation mLikeAnim;

    public MessageAdapter(Context context, int layoutId, List<MessageResonse.MessagesBean> datas) {
        super(context, layoutId, datas);
        mContext = context;
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
    }

    @Override
    public void convert(ViewHolder helper, MessageResonse.MessagesBean messagesBean) {

        ImageView tv_image = helper.getView(R.id.tv_image);
        ImageView tv_red_point = helper.getView(R.id.tv_red_point);
        TextView tv_text = helper.getView(R.id.tv_text);
        TextView tv_time = helper.getView(R.id.tv_time);

        switch (messagesBean.getMsg_type()) {
            case 0:
                tv_image.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.xitongicon));
                break;
            case 1:
                tv_image.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.fudaoiicon));
                break;
            case 2:
                tv_image.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.renwuicon));
                break;
        }
        if (messagesBean.getIs_read() == 0) {
            tv_red_point.setVisibility(View.VISIBLE);
        } else {
            tv_red_point.setVisibility(View.GONE);
        }
        tv_text.setText(messagesBean.getContent() + "");
        tv_time.setText(messagesBean.getMsg_time() + "");

    }

    @Override
    public void convert(ViewHolder helper, MessageResonse.MessagesBean messagesBean, List<Object> payloads) {

    }


}
