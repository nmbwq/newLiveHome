package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.response.partSelectBean;
import shangri.example.com.shangri.model.bean.response.personalSearchBean;
import shangri.example.com.shangri.util.ViewHolder;


/**
 * 咨询 adapter
 * Created by chengaofu on 2017/6/22.
 */

public class ConpanySerchAdapter extends BaseListAdapter<personalSearchBean.PersonalsBean> {

    private Context mContext;
    private Animation mLikeAnim;
    String Title;

    public ConpanySerchAdapter(Context context, int layoutId, List<personalSearchBean.PersonalsBean> datas, String title) {
        super(context, layoutId, datas);
        mContext = context;
        Title = title;
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
    }


    @Override
    public void convert(ViewHolder helper, personalSearchBean.PersonalsBean personalsBean) {
        final ImageView im_photo = helper.getView(R.id.im_photo);
        Glide.with(mContext)
                .load(personalsBean.getAvatar_url())
                .placeholder(R.mipmap.bg_touxiang)
                .crossFade()
                .into(im_photo);
        final RelativeLayout rl_anchor_info = helper.getView(R.id.rl_anchor_info);
        final TextView tv_manager_name = helper.getView(R.id.tv_manager_name);
        final TextView tv_type = helper.getView(R.id.tv_type);
        final TextView tv_name = helper.getView(R.id.tv_name);
        final TextView tv_id = helper.getView(R.id.tv_id);

        if (personalsBean.getType() == 1) {
            rl_anchor_info.setVisibility(View.GONE);
            tv_manager_name.setVisibility(View.VISIBLE);
            tv_manager_name.setText(personalsBean.getNickname());
            tv_type.setText("管理员");
        } else {
            rl_anchor_info.setVisibility(View.VISIBLE);
            tv_manager_name.setVisibility(View.GONE);
            tv_type.setText("主播");
            tv_name.setText(personalsBean.getNickname());
            tv_id.setText("ID: " + personalsBean.getUid());
        }
    }

    @Override
    public void convert(ViewHolder helper, personalSearchBean.PersonalsBean personalsBean, List<Object> payloads) {

    }
}
