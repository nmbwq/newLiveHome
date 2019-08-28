package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.response.ChoiceGuildBean;
import shangri.example.com.shangri.util.ViewHolder;


/**
 * 咨询 adapter
 * Created by chengaofu on 2017/6/22.
 */

public class SocietyAdapter1 extends BaseListAdapter<ChoiceGuildBean.GuildsBean> {

    private Context mContext;
    private Animation mLikeAnim;
    public static String id = "";

    public SocietyAdapter1(Context context, int layoutId, List<ChoiceGuildBean.GuildsBean> datas) {
        super(context, layoutId, datas);
        mContext = context;
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
    }

    @Override
    public void convert(ViewHolder helper, ChoiceGuildBean.GuildsBean guildsBean) {
        helper.setText(R.id.tv_name, guildsBean.getGuild_name());
        TextView tv_name = helper.getView(R.id.tv_name);
        ImageView im_select = helper.getView(R.id.im_select);
        Log.d("Debug", "");
        if (id.length() == 0) {
            if (helper.getPosition() == 0) {
                im_select.setVisibility(View.VISIBLE);
                tv_name.setTextColor(mContext.getResources().getColor(R.color.text_color_task));
            } else {
                im_select.setVisibility(View.GONE);
                tv_name.setTextColor(mContext.getResources().getColor(R.color.color_999999));

            }
        } else {
            if (id.equals(guildsBean.getGuild_id())) {
                im_select.setVisibility(View.VISIBLE);
                tv_name.setTextColor(mContext.getResources().getColor(R.color.text_color_task));
            } else {
                im_select.setVisibility(View.GONE);
                tv_name.setTextColor(mContext.getResources().getColor(R.color.color_999999));
            }

        }

    }

    @Override
    public void convert(ViewHolder helper, ChoiceGuildBean.GuildsBean guildsBean, List<Object> payloads) {

    }


}
