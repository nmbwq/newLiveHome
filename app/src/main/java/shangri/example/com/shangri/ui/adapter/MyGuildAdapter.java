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

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.base.GlobalApp;
import shangri.example.com.shangri.model.bean.event.BrowseEventBean;
import shangri.example.com.shangri.model.bean.event.ChangePoint;
import shangri.example.com.shangri.model.bean.response.IsFaceResponse;
import shangri.example.com.shangri.model.bean.response.MyGuildListDataBean;
import shangri.example.com.shangri.ui.activity.UpgradeGuildeActivity;
import shangri.example.com.shangri.ui.activity.VerifyBindingActivity;
import shangri.example.com.shangri.ui.view.RoundImageView;
import shangri.example.com.shangri.util.ToastUtil;
import shangri.example.com.shangri.util.ViewHolder;

import java.util.List;


/**
 * Created by Administrator on 2017/12/29.
 */

public class MyGuildAdapter extends BaseListAdapter<MyGuildListDataBean.GuildsBean> {
    public MyGuildAdapter(Context context, int layoutId, List<MyGuildListDataBean.GuildsBean> datas) {
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


        im_state.setVisibility(View.VISIBLE);
        tv_state.setVisibility(View.VISIBLE);

        tv_titel.setText(myGuildListBean.getGuild_name());
        if (myGuildListBean.getCheck_status().equals("0")) {
            tv_refuse_text.setVisibility(View.GONE);
            tv_renzhegn.setVisibility(View.GONE);
            im_type.setVisibility(View.INVISIBLE);
            tv_state.setText("审核中");
            tv_state.setTextColor(ContextCompat.getColor(mContext, R.color.text_color_audit));
            im_state.setImageResource(R.mipmap.shenhe);
            if (myGuildListBean.getPlatfrom_name().contains("NOW") && UserConfig.getInstance().getRole().equals("1")) {
                rl_point.setVisibility(View.VISIBLE);
                im_update.setVisibility(View.GONE);
                my_guild_name.setText("系数：" + myGuildListBean.getGuild_ratio());
                my_guild_name.setTextColor(GlobalApp.getAppContext().getResources().getColor(R.color.color_999999));
            } else {
                rl_point.setVisibility(View.GONE);
            }

        } else if (myGuildListBean.getCheck_status().equals("1")) {
            tv_state.setText("审核通过");
            tv_renzhegn.setVisibility(View.GONE);
            im_type.setVisibility(View.VISIBLE);
            im_type.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.gonghui_renzheng));
            tv_refuse_text.setVisibility(View.GONE);
            tv_state.setTextColor(ContextCompat.getColor(mContext, R.color.text_color_pass));
            im_state.setImageResource(R.mipmap.tongguo);
            if (myGuildListBean.getPlatfrom_name().contains("NOW") && UserConfig.getInstance().getRole().equals("1")) {
                rl_point.setVisibility(View.VISIBLE);
                im_update.setVisibility(View.VISIBLE);
                my_guild_name.setText("系数：" + myGuildListBean.getGuild_ratio());
                my_guild_name.setTextColor(GlobalApp.getAppContext().getResources().getColor(R.color.white));
            } else {
                rl_point.setVisibility(View.GONE);
            }
        } else if (myGuildListBean.getCheck_status().equals("-1")) {
            im_type.setVisibility(View.INVISIBLE);
            tv_state.setText("审核失败");
            tv_renzhegn.setVisibility(View.GONE);
            tv_state.setTextColor(ContextCompat.getColor(mContext, R.color.text_color_error));
            im_state.setImageResource(R.mipmap.shibai);
            if (myGuildListBean.getCheck_mark().length() > 0) {
                tv_refuse_text.setVisibility(View.VISIBLE);
                tv_refuse_text.setText("审核失败原因：" + myGuildListBean.getCheck_mark());
            } else {
                tv_refuse_text.setVisibility(View.GONE);
            }
            if (myGuildListBean.getPlatfrom_name().contains("NOW") && UserConfig.getInstance().getRole().equals("1")) {
                rl_point.setVisibility(View.VISIBLE);
                im_update.setVisibility(View.GONE);
                my_guild_name.setText("系数：" + myGuildListBean.getGuild_ratio());
                my_guild_name.setTextColor(GlobalApp.getAppContext().getResources().getColor(R.color.color_999999));
            } else {
                rl_point.setVisibility(View.GONE);
            }

        } else if (myGuildListBean.getCheck_status().equals("2")) {
            tv_renzhegn.setVisibility(View.VISIBLE);
            im_type.setVisibility(View.VISIBLE);
            im_type.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.gonghui_renzheng_guoqi));
            im_state.setVisibility(View.GONE);
            tv_state.setVisibility(View.GONE);
            tv_refuse_text.setVisibility(View.GONE);
            if (myGuildListBean.getPlatfrom_name().contains("NOW") && UserConfig.getInstance().getRole().equals("1")) {
                rl_point.setVisibility(View.VISIBLE);
                im_update.setVisibility(View.GONE);
                my_guild_name.setText("系数：" + myGuildListBean.getGuild_ratio());
                my_guild_name.setTextColor(GlobalApp.getAppContext().getResources().getColor(R.color.color_999999));
            } else {
                rl_point.setVisibility(View.GONE);
            }


        }
        Glide.with(mContext).load(myGuildListBean.getIcon_url()).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                im_icon.setImageBitmap(resource);
            }
        });
        /**
         * now直播更改设置系数
         */
        im_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new ChangePoint(myGuildListBean.getGuild_id()));

            }

        });

        tv_renzhegn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, VerifyBindingActivity.class);
                intent.putExtra("guildNameText", myGuildListBean.getGuild_name());
                intent.putExtra("guild_id", myGuildListBean.getGuild_id());
                mContext.startActivity(intent);
            }
        });


        if (UserConfig.getInstance().getRole().equals("1")) {
        } else {
            im_type.setVisibility(View.GONE);
        }

    }

    @Override
    public void convert(ViewHolder helper, MyGuildListDataBean.GuildsBean myGuildListBean, List<Object> payloads) {

    }
}
