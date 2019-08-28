package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.response.CultivateBean;
import shangri.example.com.shangri.model.bean.response.TrainingArticleBean;
import shangri.example.com.shangri.ui.activity.AddInviteActivity;
import shangri.example.com.shangri.util.CornersTransform;
import shangri.example.com.shangri.util.ViewHolder;


/**
 * 咨询 adapter
 * Created by chengaofu on 2017/6/22.
 */

public class TypeListDialogAdapter extends BaseListAdapter<TrainingArticleBean.TrainsBean> {

    private Context mContext;
    private Animation mLikeAnim;


    public TypeListDialogAdapter(Context context, int layoutId, List<TrainingArticleBean.TrainsBean> datas) {
        super(context, layoutId, datas);
        mContext = context;
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
    }

    @Override
    public void convert(final ViewHolder helper, final TrainingArticleBean.TrainsBean data) { //, List<Object> payloads
        ImageView im_image = (ImageView) helper.getView(R.id.im_image);
        TextView tv_name = (TextView) helper.getView(R.id.tv_name);
        tv_name.setText(data.getName() + "");
        Glide.with(mContext)
                .load(data.getImg_url())
                .placeholder(R.mipmap.kk_pxzwt)
                .transform(new CornersTransform(mContext, 10))
                .crossFade()
                .into(im_image);

    }


    @Override
    public void convert(ViewHolder helper, TrainingArticleBean.TrainsBean data, List<Object> payloads) {
    }


}
