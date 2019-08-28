package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.response.SupportFromList;
import shangri.example.com.shangri.model.bean.response.TipsPageDataBean;
import shangri.example.com.shangri.ui.view.CircleImageView;
import shangri.example.com.shangri.ui.view.RoundImageView;
import shangri.example.com.shangri.util.RegexUtil;
import shangri.example.com.shangri.util.ViewHolder;

/**
 * 平台公告 adapter
 * Created by chengaofu on 2017/6/29.
 */

public class SupportplatfromAdapter extends BaseListAdapter<SupportFromList.AuthPlatfromBean> {

    public SupportplatfromAdapter(Context context, int layoutId, List<SupportFromList.AuthPlatfromBean> datas) {
        super(context, layoutId, datas);
        mContext = context;
    }


    @Override
    public void convert(ViewHolder helper, SupportFromList.AuthPlatfromBean data) { //, List<Object> payload

       final RoundImageView view = helper.getView(R.id.im_image);
//        Glide.with(mContext)
//                .load(data.getIcon_url())
//                .placeholder(R.mipmap.bg_touxiang)
//                .crossFade()
//                .into(view);

        Glide.with(mContext).load(data.getIcon_url()).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                view.setImageBitmap(resource);
            }
        });

        helper.setText(R.id.tv1, data.getName());
    }

    @Override
    public void convert(ViewHolder helper, SupportFromList.AuthPlatfromBean tipsPageDataBean, List<Object> payloads) {

    }
}
