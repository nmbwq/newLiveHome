package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.ArrayList;
import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.response.SearchBean;
import shangri.example.com.shangri.ui.view.RoundImageView;
import shangri.example.com.shangri.util.AndroidInterface.CompanyInterfaceUtils;
import shangri.example.com.shangri.util.ViewHolder;

/**
 * Created by Administrator on 2018/1/3.
 */

public class CompanyListAdapter1 extends BaseListAdapter<SearchBean.AdminBean> {
    private Context mContext;
    private Animation mLikeAnim;
    List<SearchBean.AdminBean> data = new ArrayList<>();
    //    MyAnchoritemAdapter mAdapter;


    public CompanyListAdapter1(Context context, int layoutId, List<SearchBean.AdminBean> datas) {
        super(context, layoutId, datas);
        mContext = context;
        data = datas;
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
    }

    @Override
    public void convert(ViewHolder helper, final SearchBean.AdminBean companysBean) {
        TextView tv_renzhegn = helper.getView(R.id.tv_renzhegn);
        TextView tv_name = helper.getView(R.id.tv_name);
        final RoundImageView im_image = helper.getView(R.id.im_image);
        if (UserConfig.getInstance().getRole().equals("2")) {
            tv_renzhegn.setVisibility(View.GONE);
        } else if (UserConfig.getInstance().getRole().equals("3")) {
            tv_renzhegn.setVisibility(View.VISIBLE);
        }

        helper.setText(R.id.tv_name, companysBean.getGuild_name() + "");
        Glide.with(mContext).load(companysBean.getIcon_url()).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                im_image.setImageBitmap(resource);
            }
        });
        if (UserConfig.getInstance().getRole().equals("3")) {
            tv_renzhegn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CompanyInterfaceUtils.apllayFace.adminApllayFace(companysBean.getRegistrants_id() + "", companysBean.getGuild_id());
                }
            });
        }
    }

    @Override
    public void convert(ViewHolder helper, SearchBean.AdminBean companysBean, List<Object> payloads) {

    }

}
