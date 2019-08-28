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

import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.response.MyGuildListDataBean;
import shangri.example.com.shangri.ui.activity.UpgradeGuildeActivity;
import shangri.example.com.shangri.ui.view.RoundImageView;
import shangri.example.com.shangri.util.ViewHolder;


/**
 * Created by Administrator on 2017/12/29.
 */

public class FastMyGuildAdapter1 extends BaseListAdapter<MyGuildListDataBean.GuildsBean> {
    public FastMyGuildAdapter1(Context context, int layoutId, List<MyGuildListDataBean.GuildsBean> datas) {
        super(context, layoutId, datas);
    }

    //item全部刷新
    @Override
    public void convert(ViewHolder helper, final MyGuildListDataBean.GuildsBean myGuildListBean) {
        final RoundImageView im_icon = helper.getView(R.id.im_icon);
        TextView tv_titel = helper.getView(R.id.tv_titel);
        tv_titel.setText(myGuildListBean.getGuild_name());
        Glide.with(mContext).load(myGuildListBean.getIcon_url()).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                im_icon.setImageBitmap(resource);
            }
        });


    }

    @Override
    public void convert(ViewHolder helper, MyGuildListDataBean.GuildsBean myGuildListBean, List<Object> payloads) {

    }
}
