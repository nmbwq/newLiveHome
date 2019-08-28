package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.response.EncyclopediaHomeBean;
import shangri.example.com.shangri.ui.activity.NewLoginUserActivity;
import shangri.example.com.shangri.ui.webview.EncyclopediaActivityWebView;
import shangri.example.com.shangri.util.PointUtils;
import shangri.example.com.shangri.util.ViewHolder;


/**
 * 咨询 adapter
 * Created by chengaofu on 2017/6/22.
 */

public class PingTaiAdapterTwo extends BaseListAdapter<EncyclopediaHomeBean.GuildBean> {

    private Context mContext;
    private Animation mLikeAnim;

    public PingTaiAdapterTwo(Context context, int layoutId, List<EncyclopediaHomeBean.GuildBean> datas) {
        super(context, layoutId, datas);
        mContext = context;
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
    }

    @Override
    public void convert(ViewHolder helper,final EncyclopediaHomeBean.GuildBean guildBean) {
        helper.setText(R.id.tv_name, guildBean.getGuild_name() + "");
        ImageView view = helper.getView(R.id.im_image);
        Glide.with(mContext)
                .load(guildBean.getCover_url())
                .placeholder(R.mipmap.bg_touxiang)
                .crossFade()
                .into(view);


            helper.getConvertView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (PointUtils.isFastClick()) {

                        if (TextUtils.isEmpty(UserConfig.getInstance().getToken())) {
                            mContext.startActivity(new Intent(mContext, NewLoginUserActivity.class));
                        }else {
                            Intent intent = new Intent(mContext, EncyclopediaActivityWebView.class);
                            intent.putExtra("type", 2);
                            intent.putExtra("id", guildBean.getId());
                            intent.putExtra("url", guildBean.getGuild_url());
                            intent.putExtra("imageurl", guildBean.getCover_url());
                            intent.putExtra("is_collect", guildBean.getGuild_collect());
                            intent.putExtra("title", guildBean.getGuild_name());
                            mContext.startActivity(intent);
                        }

                    }
                }
            });


    }

    @Override
    public void convert(ViewHolder helper, EncyclopediaHomeBean.GuildBean guildBean, List<Object> payloads) {

    }

}
