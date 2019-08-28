package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.response.BannerHomeLookBean;
import shangri.example.com.shangri.model.bean.response.SortModelBean;
import shangri.example.com.shangri.ui.listener.NewsListListener;
import shangri.example.com.shangri.ui.listener.PraiseListener;
import shangri.example.com.shangri.ui.view.RoundImageView;
import shangri.example.com.shangri.util.ViewHolder;


/**
 * 咨询 adapter
 * Created by chengaofu on 2017/6/22.
 */

public class InformationAdapter extends BaseListAdapter<SortModelBean.PlatfromsBean> {

    private Context mContext;
    private Animation mLikeAnim;
    private PraiseListener mPraiseListener;
    private NewsListListener mNewsListListener;

    public InformationAdapter(Context context, int layoutId, List<SortModelBean.PlatfromsBean> datas) {
        super(context, layoutId, datas);
        mContext = context;
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
    }

    @Override
    public void convert(final ViewHolder helper, SortModelBean.PlatfromsBean data) {
        TextView tv_user_item_name = helper.getView(R.id.tv_user_item_name);
        final RoundImageView iv_user_item_icon = helper.getView(R.id.iv_user_item_icon);
        tv_user_item_name.setText(data.getName());
        Glide.with(mContext).load(data.getIcon_url()).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                iv_user_item_icon.setImageBitmap(resource);
            }
        });
        helper.getView(R.id.video_news_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNewsListListener.onItemClickListener(helper.getAdapterPosition());
            }
        });
    }


    @Override
    public void convert(ViewHolder helper, SortModelBean.PlatfromsBean data, List<Object> payloads) { //item局部刷新


    }


    public void setItemListListener(NewsListListener newsListListener) { //列表点击
        this.mNewsListListener = newsListListener;
    }
}
