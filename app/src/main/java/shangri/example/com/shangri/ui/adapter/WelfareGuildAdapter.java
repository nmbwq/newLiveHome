package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.response.WelfareGuildBean;
import shangri.example.com.shangri.ui.view.CircleImageView;
import shangri.example.com.shangri.util.ViewHolder;

/**
 * Description:
 * Data：2018/11/6-16:43
 * Author: lin
 */
public class WelfareGuildAdapter extends BaseListAdapter<WelfareGuildBean.GuildList> {
    private Context mContext;
    private Animation mLikeAnim;
    public WelfareGuildAdapter(Context context, int layoutId, List<WelfareGuildBean.GuildList> datas) {
        super(context, layoutId, datas);
        mContext = context;
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
    }

    @Override
    public void convert(ViewHolder helper, WelfareGuildBean.GuildList guildList) {
//        CircleImageView imageView = helper.getView(R.id.iv_icon);
//        Glide.with(mContext).load(guildList.getAvatar_url()).into(imageView);
//        helper.setText(R.id.nickname, guildList.getNickname() + "");
//        StringBuffer sb = new StringBuffer();
//        sb.append("求职意向:");
//        for(String s:guildList.getType_name())
//            sb.append(s +" ");
//        helper.setText(R.id.tv_intention, sb + "");
    }

    @Override
    public void convert(ViewHolder helper, WelfareGuildBean.GuildList guildList, List<Object> payloads) {

    }
}
