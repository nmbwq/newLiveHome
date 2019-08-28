package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.base.GlobalApp;
import shangri.example.com.shangri.model.bean.response.EncyclopediaHomeBean;
import shangri.example.com.shangri.ui.activity.NewLoginUserActivity;
import shangri.example.com.shangri.ui.view.RoundImageView;
import shangri.example.com.shangri.ui.webview.EncyclopediaActivityWebView;
import shangri.example.com.shangri.util.PointUtils;
import shangri.example.com.shangri.util.ViewHolder;


/**
 * 咨询 adapter
 * Created by chengaofu on 2017/6/22.
 */

public class ZhuBoAdapter extends BaseListAdapter<EncyclopediaHomeBean.AnchorBean> {

    private Context mContext;
    private Animation mLikeAnim;

    public ZhuBoAdapter(Context context, int layoutId, List<EncyclopediaHomeBean.AnchorBean> datas) {
        super(context, layoutId, datas);
        mContext = context;
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
    }

    @Override
    public void convert(ViewHolder helper, final EncyclopediaHomeBean.AnchorBean anchorBean) {
        helper.setText(R.id.tv_name, anchorBean.getAnchor_name() + "");
        helper.setText(R.id.tv_fans_number, "粉丝量:" + anchorBean.getAnchor_fans());
        final RoundImageView view = helper.getView(R.id.im_image);
        final RoundImageView im_image1 = helper.getView(R.id.im_image1);

        final RelativeLayout ll_three = helper.getView(R.id.ll_three);

        RelativeLayout rl_back = helper.getView(R.id.rl_back);

        switch (helper.getPosition()) {
            case 0:
                rl_back.setBackground(GlobalApp.mAppContext.getResources().getDrawable(R.mipmap.zhubo_jinse));
                ll_three.setVisibility(View.GONE);
                view.setVisibility(View.VISIBLE);
                break;
            case 1:
                rl_back.setBackground(GlobalApp.mAppContext.getResources().getDrawable(R.mipmap.zhubo_yinse));
                ll_three.setVisibility(View.GONE);
                view.setVisibility(View.VISIBLE);
                break;
            case 2:
                rl_back.setBackground(GlobalApp.mAppContext.getResources().getDrawable(R.mipmap.zhubo_baise));
                ll_three.setVisibility(View.GONE);
                view.setVisibility(View.VISIBLE);
                break;
            default:
                ll_three.setBackground(GlobalApp.mAppContext.getResources().getDrawable(R.mipmap.tuoyuan));
                ll_three.setVisibility(View.VISIBLE);
                view.setVisibility(View.GONE);
                break;
        }
        Glide.with(mContext).load(anchorBean.getCover_url()).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                view.setImageBitmap(resource);
            }
        });
        Glide.with(mContext).load(anchorBean.getCover_url()).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                im_image1.setImageBitmap(resource);
            }
        });


            helper.getConvertView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (PointUtils.isFastClick()) {


                        if (TextUtils.isEmpty(UserConfig.getInstance().getToken())) {
                            mContext.startActivity(new Intent(mContext, NewLoginUserActivity.class));
                        }else {
                            Intent intent = new Intent(mContext, EncyclopediaActivityWebView.class);
                            intent.putExtra("type", 3);
                            intent.putExtra("id", anchorBean.getId());
                            intent.putExtra("url", anchorBean.getAnchor_url());
                            intent.putExtra("imageurl", anchorBean.getCover_url());
                            intent.putExtra("is_collect", anchorBean.getAnchor_collect());
                            intent.putExtra("title", anchorBean.getAnchor_name());
                            mContext.startActivity(intent);
                        }

                    }
                }
            });


    }

    @Override
    public void convert(ViewHolder helper, EncyclopediaHomeBean.AnchorBean anchorBean, List<Object> payloads) {

    }


}
