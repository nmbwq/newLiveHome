package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.response.EncyclopediaList;
import shangri.example.com.shangri.ui.webview.EncyclopediaActivityWebView;
import shangri.example.com.shangri.util.PointUtils;
import shangri.example.com.shangri.util.ViewHolder;

/**
 * Created by Administrator on 2018/1/3.
 */

public class guildListAdapter extends BaseListAdapter<EncyclopediaList.GuildBean> {
    private Context mContext;
    private Animation mLikeAnim;

    public guildListAdapter(Context context, int layoutId, List<EncyclopediaList.GuildBean> datas) {
        super(context, layoutId, datas);
        mContext = context;
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
    }

    @Override
    public void convert(ViewHolder helper, final EncyclopediaList.GuildBean guildBean) {
        if (guildBean.getLocation_tag() != null) {
            if (guildBean.getLocation_tag().length() > 0) {
                helper.setText(R.id.tv_symbol, "#" + guildBean.getLocation_tag() + "");
            }
        } else {
            helper.setText(R.id.tv_symbol, "");
        }
        helper.setText(R.id.tv_name, guildBean.getGuild_name() + "");
        helper.setText(R.id.tv_context, guildBean.getIntroduction() + "");
        ImageView im_image = helper.getView(R.id.im_image);
        Glide.with(mContext)
                .load(guildBean.getCover_url())
                .placeholder(R.mipmap.ic_moren)
                .crossFade()
                .into(im_image);
        helper.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (PointUtils.isFastClick()) {
                    Intent intent = new Intent(mContext, EncyclopediaActivityWebView.class);
                    intent.putExtra("type", 2);
                    intent.putExtra("id", guildBean.getId());
                    intent.putExtra("url", guildBean.getGuild_url());
                    intent.putExtra("is_collect", guildBean.getGuild_collect());
                    intent.putExtra("title", guildBean.getGuild_name());
                    intent.putExtra("imageurl", guildBean.getCover_url());
                    mContext.startActivity(intent);
                }
            }
        });
    }

    @Override
    public void convert(ViewHolder helper, EncyclopediaList.GuildBean guildBean, List<Object> payloads) {

    }
}
