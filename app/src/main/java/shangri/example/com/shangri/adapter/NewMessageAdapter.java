package shangri.example.com.shangri.adapter;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.response.NewMessageBean;
import shangri.example.com.shangri.util.DateUtil;
import shangri.example.com.shangri.util.ViewHolder;

/**
 * Description:
 * Data：2018/11/20-9:45
 * Author: lin
 */
public class NewMessageAdapter extends BaseListAdapter<NewMessageBean.Messages> {
    private Context mContext;
    private Animation mLikeAnim;
    private int type;

    public NewMessageAdapter(Context context, int type, int layoutId, List<NewMessageBean.Messages> datas, onClickItem listener) {
        super(context, layoutId, datas);
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
        this.listener = listener;
        this.type = type;
    }

    @Override
    public void convert(final ViewHolder helper, final NewMessageBean.Messages messages) {
        final int position = helper.getAdapterPosition();
        helper.setText(R.id.title, messages.getTitle());
        helper.setText(R.id.subtitle, messages.getF_title());
        helper.setText(R.id.current_time, DateUtil.getTime(messages.getCreate_time()) + "");
        TextView tv_msg = helper.getView(R.id.tv_msg);
        final ImageView iv_visible = helper.getView(R.id.iv_visible);
        if (messages.getIcon_type().equals("1")) {
            helper.setImageResource(R.id.iv_is_read, R.mipmap.wdxx_zbtd);
        } else if (messages.getIcon_type().equals("2")) {
            helper.setImageResource(R.id.iv_is_read, R.mipmap.wdxx_xttz);
        } else if (messages.getIcon_type().equals("3")) {
            helper.setImageResource(R.id.iv_is_read, R.mipmap.wdxx_yytd);
        }
        if (messages.getIs_read().equals("0")) {
            iv_visible.setVisibility(View.VISIBLE);
        } else {
            iv_visible.setVisibility(View.GONE);
        }
        if (type == 1) {
            tv_msg.setText("查看详情");
        } else {
            // 系統消息 messages.getRcd_url()有值的话  显示的是平台消息（查看详情）
            if (messages.getRcd_url().length() > 0) {
                tv_msg.setText("查看详情");
            } else {
                if (UserConfig.getInstance().getRole().equals("1")) {
                    tv_msg.setText("查看职位");
                } else if (UserConfig.getInstance().getRole().equals("2")) {
                    tv_msg.setText("查看简历");
                }
            }
        }

        LinearLayout ll_detail = helper.getView(R.id.ll_detail);
        ll_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_visible.setVisibility(View.GONE);
//                if (messages.getIcon_type().equals("1")){
////                    helper.setImageResource(R.id.iv_is_read, R.mipmap.wdxx_zbtd);
////                } else if (messages.getIcon_type().equals("2")){
////                    helper.setImageResource(R.id.iv_is_read, R.mipmap.wdxx_xttz);
////                } else if (messages.getIcon_type().equals("3")){
////                    helper.setImageResource(R.id.iv_is_read, R.mipmap.wdxx_yytd);
////                }
                listener.onClick(messages.getId(), messages.getMain_id(), messages.getRcd_url());
            }
        });
    }

    @Override
    public void convert(ViewHolder helper, NewMessageBean.Messages messages, List<Object> payloads) {

    }

    private onClickItem listener;

    public interface onClickItem {
        void onClick(String id, String main_id, String url);
    }
}
