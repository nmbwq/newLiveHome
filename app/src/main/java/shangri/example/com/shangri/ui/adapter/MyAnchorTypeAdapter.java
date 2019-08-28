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
import shangri.example.com.shangri.model.bean.response.MyAnchorListBeanResponse;
import shangri.example.com.shangri.model.bean.response.MyAnchorListBeanResponse1;
import shangri.example.com.shangri.model.bean.response.MyGuildListDataBean;
import shangri.example.com.shangri.ui.activity.MyAnchorGuanliAcvity;
import shangri.example.com.shangri.ui.activity.VerifyBindingActivity;
import shangri.example.com.shangri.ui.view.RoundImageView;
import shangri.example.com.shangri.util.ToastUtil;
import shangri.example.com.shangri.util.ViewHolder;


/**
 * Created by Administrator on 2017/12/29.
 */

public class MyAnchorTypeAdapter extends BaseListAdapter<MyAnchorListBeanResponse1.GuildsBean> {
    int istype;

    public MyAnchorTypeAdapter(Context context, int layoutId, List<MyAnchorListBeanResponse1.GuildsBean> datas, int type) {
        super(context, layoutId, datas);
        istype = type;
    }

    @Override
    public void convert(ViewHolder helper, final MyAnchorListBeanResponse1.GuildsBean guildsBean) {
        final RoundImageView im_icon = helper.getView(R.id.im_icon);
        TextView tv_titel = helper.getView(R.id.tv_titel);
        tv_titel.setText(guildsBean.getGuild_name());
        //右面的图标
        ImageView im_type = helper.getView(R.id.im_type);
        //1 快速公会 1 平台认证公会 im_type隐藏
        if (istype == 2) {
            im_type.setVisibility(View.INVISIBLE);
        } else {
            im_type.setVisibility(View.VISIBLE);
//            1审核通过 2验证过期
            if (guildsBean.getCheck_status().equals("1")) {
                im_type.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.gonghui_renzheng));
            } else {
                im_type.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.gonghui_renzheng_guoqi));
            }
        }
        helper.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (istype == 1) {
                    if (guildsBean.getCheck_status().equals("1")) {
                        Intent intent = new Intent(mContext, MyAnchorGuanliAcvity.class);
                        intent.putExtra("guild_id", guildsBean.getGuild_id());
                        intent.putExtra("guild_name", guildsBean.getGuild_name());
                        intent.putExtra("type", istype + "");
                        intent.putExtra("table_flag", guildsBean.getTable_flag() + "");
                        mContext.startActivity(intent);
                    } else {
                        ToastUtil.showShort("当前公会已认证过期，请重新认证");
                    }
                } else {
                    Intent intent = new Intent(mContext, MyAnchorGuanliAcvity.class);
                    intent.putExtra("guild_id", guildsBean.getGuild_id());
                    intent.putExtra("guild_name", guildsBean.getGuild_name());
                    intent.putExtra("type", istype + "");
                    intent.putExtra("table_flag", guildsBean.getTable_flag() + "");
                    mContext.startActivity(intent);
                }

            }
        });

        TextView tv_renzhegn = helper.getView(R.id.tv_renzhegn);

        tv_renzhegn.setText(guildsBean.getAnchors_count() + "人");
        Glide.with(mContext).load(guildsBean.getIcon_url()).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                im_icon.setImageBitmap(resource);
            }
        });
    }

    @Override
    public void convert(ViewHolder helper, MyAnchorListBeanResponse1.GuildsBean guildsBean, List<Object> payloads) {

    }


}
