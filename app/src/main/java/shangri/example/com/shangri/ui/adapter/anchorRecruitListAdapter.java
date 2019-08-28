package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.response.BossDataBean;
import shangri.example.com.shangri.model.bean.response.anchorRecruitListBean;
import shangri.example.com.shangri.ui.webview.AnchorBossWebView;
import shangri.example.com.shangri.ui.webview.BossWebView;
import shangri.example.com.shangri.util.StartActivityUtils;
import shangri.example.com.shangri.util.ViewHolder;


/**
 * 咨询 adapter
 * Created by chengaofu on 2017/6/22.
 */

public class anchorRecruitListAdapter extends BaseListAdapter<anchorRecruitListBean.ListBean> {

    private Context mContext;
    private Animation mLikeAnim;


    public anchorRecruitListAdapter(Context context, int layoutId, List<anchorRecruitListBean.ListBean> datas) {
        super(context, layoutId, datas);
        mContext = context;
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
    }

    @Override
    public void convert(final ViewHolder helper, final anchorRecruitListBean.ListBean item) { //, List<Object> payloads
        ImageView iv_information = helper.getView(R.id.iv_information);
        TextView tv_name = (TextView) helper.getView(R.id.tv_name);
        tv_name.setText(item.getName());
        String type = item.getType();
        String id = item.getId() + "";
        String img_url = item.getImg_url();
        if (item.getType().equals("1")) {
            tv_name.setTextColor(mContext.getResources().getColor(R.color.color_d0a76c));
        } else {
            tv_name.setTextColor(mContext.getResources().getColor(R.color.white));
        }
        helper.setText(R.id.tv_name, item.getName());
        Glide.with(mContext).load(img_url)
                .dontAnimate()
                .placeholder(R.mipmap.bg_touxiang)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .into(iv_information);
    }


    @Override
    public void convert(ViewHolder helper, anchorRecruitListBean.ListBean data, List<Object> payloads) {
    }


}
