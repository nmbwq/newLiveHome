package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.base.GlobalApp;
import shangri.example.com.shangri.model.bean.event.ChangePoint;
import shangri.example.com.shangri.model.bean.response.MyGuildListDataBean;
import shangri.example.com.shangri.ui.activity.UpgradeGuildeActivity;
import shangri.example.com.shangri.ui.view.RoundImageView;
import shangri.example.com.shangri.util.ViewHolder;


/**
 * Created by Administrator on 2017/12/29.
 */

public class FastMyGuildAdapter extends BaseListAdapter<MyGuildListDataBean.GuildsBean> {
    public FastMyGuildAdapter(Context context, int layoutId, List<MyGuildListDataBean.GuildsBean> datas) {
        super(context, layoutId, datas);
    }

    //item全部刷新
    @Override
    public void convert(ViewHolder helper, final MyGuildListDataBean.GuildsBean myGuildListBean) {
        final RoundImageView im_icon = helper.getView(R.id.im_icon);
        TextView tv_titel = helper.getView(R.id.tv_titel);
        ImageView im_state = helper.getView(R.id.im_state);
        TextView tv_state = helper.getView(R.id.tv_state);
        TextView my_guild_name = helper.getView(R.id.my_guild_name);

        final RelativeLayout rl_bottom = helper.getView(R.id.rl_bottom);
        RelativeLayout rl_onclik = helper.getView(R.id.rl_onclik);

        ImageView im_update = helper.getView(R.id.im_update);
        //右面的图标
        ImageView im_type = helper.getView(R.id.im_type);

        RelativeLayout rl_point = helper.getView(R.id.rl_point);
        TextView tv_refuse_text = helper.getView(R.id.tv_refuse_text);
        TextView tv_renzhegn = helper.getView(R.id.tv_renzhegn);
        rl_point.setVisibility(View.GONE);
        Glide.with(mContext).load(myGuildListBean.getIcon_url()).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                im_icon.setImageBitmap(resource);
            }
        });
        im_type.setVisibility(View.INVISIBLE);
        tv_titel.setText(myGuildListBean.getGuild_name());
        if (myGuildListBean.getCheck_status().equals("3")) {
            im_state.setVisibility(View.VISIBLE);
            tv_state.setVisibility(View.VISIBLE);
            tv_refuse_text.setVisibility(View.GONE);
            tv_renzhegn.setVisibility(View.GONE);
            tv_state.setText("升级中");
            tv_state.setTextColor(ContextCompat.getColor(mContext, R.color.text_color_audit));
            im_state.setImageResource(R.mipmap.shenhe);
        } else {
            tv_renzhegn.setVisibility(View.VISIBLE);
            tv_renzhegn.setText("升级");
            im_state.setVisibility(View.GONE);
            tv_state.setVisibility(View.GONE);
            tv_refuse_text.setVisibility(View.GONE);
        }

        tv_renzhegn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, UpgradeGuildeActivity.class);
                intent.putExtra("type", 2+"");
                intent.putExtra("guildNameText", myGuildListBean.getGuild_name());
                intent.putExtra("guild_id", myGuildListBean.getGuild_id());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public void convert(ViewHolder helper, MyGuildListDataBean.GuildsBean myGuildListBean, List<Object> payloads) {

    }
}
