package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagFlowLayout;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.adapter.TextAdapter;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.response.BannerHomeLookBean;
import shangri.example.com.shangri.ui.listener.NewsListListener;
import shangri.example.com.shangri.ui.listener.PraiseListener;
import shangri.example.com.shangri.util.ViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 咨询 adapter
 * Created by chengaofu on 2017/6/22.
 */

public class ConsultationAdapter extends BaseListAdapter<BannerHomeLookBean.TrainsBean> {

    private Context mContext;
    private Animation mLikeAnim;
    private PraiseListener mPraiseListener;
    private NewsListListener mNewsListListener;

    public ConsultationAdapter(Context context, int layoutId, List<BannerHomeLookBean.TrainsBean> datas) {
        super(context, layoutId, datas);
        mContext = context;
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
    }

    @Override
    public void convert(final ViewHolder helper, BannerHomeLookBean.TrainsBean data) { //, List<Object> payloads
        ImageView iv_consulation_item = helper.getView(R.id.iv_consulation_item);
        TextView tv_consultaton_titel = helper.getView(R.id.tv_consultaton_titel);
        TextView entertain_news_like_num = helper.getView(R.id.entertain_news_like_num);
        TextView entertain_news_view_count = helper.getView(R.id.entertain_news_view_count);
        RelativeLayout rl_cultivate_title = helper.getView(R.id.rl_cultivate_title);
        TagFlowLayout flowLayout = (TagFlowLayout) helper.getView(R.id.tag_fl);


        ImageView iv_dian = helper.getView(R.id.iv_dian);
        ImageView im_is_video = helper.getView(R.id.im_is_video);

        if (data.getAudio_url().length() > 0) {
            im_is_video.setVisibility(View.VISIBLE);
        } else {
            im_is_video.setVisibility(View.GONE);
        }
        Glide.with(mContext)
                .load(data.getCover_url())
                .placeholder(R.mipmap.bg_touxiang)
                .crossFade()
                .into(iv_consulation_item);
        tv_consultaton_titel.setText(data.getTitle());
        entertain_news_like_num.setText(data.getBrowse_amount());
        entertain_news_view_count.setText(data.getGood_amount());
        String myString = data.getKeywords().replace("|", " ");
        String[] split = myString.split(" ");
        List<String> sData = Arrays.asList(split);


        flowLayout.setAdapter(new TextAdapter(mContext, sData));


        if (data.getRegister_good() == 0) { //未点赞
            iv_dian.setImageResource(R.mipmap.icon_good);
            entertain_news_view_count.setTextColor(mContext.getResources().getColor(R.color.text_color_light_black));
        } else if (data.getRegister_good() == 1) { //已点赞
            iv_dian.setImageResource(R.mipmap.icon_good4);
            entertain_news_view_count.setTextColor(mContext.getResources().getColor(R.color.text_color_light_yellow));
        }
        helper.getView(R.id.ll_dian).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPraiseListener != null) {
                    Log.d("Debug", "onPraise点击的位置为" + getPosition(helper));
                    mPraiseListener.onPraise(getPosition(helper) - 1);
                }

            }
        });

        helper.getView(R.id.video_news_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Debug", "适配器里面点击的位置是" + helper.getAdapterPosition());
                mNewsListListener.onItemClickListener(helper.getAdapterPosition());
            }
        });
    }


    @Override
    public void convert(ViewHolder helper, BannerHomeLookBean.TrainsBean data, List<Object> payloads) { //item局部刷新
        ImageView iv_dian = helper.getView(R.id.iv_dian);
        TextView entertain_news_like_num = helper.getView(R.id.entertain_news_like_num);
        TextView entertain_news_view_count = helper.getView(R.id.entertain_news_view_count);

        entertain_news_like_num.setText(data.getBrowse_amount());
        entertain_news_view_count.setText(data.getGood_amount());

        if (data.getRegister_good() == 0) { //未点赞
            iv_dian.setImageResource(R.mipmap.icon_good);
            entertain_news_view_count.setTextColor(mContext.getResources().getColor(R.color.text_color_light_black));
        } else if (data.getRegister_good() == 1) { //已点赞
            iv_dian.setImageResource(R.mipmap.icon_good4);
            entertain_news_view_count.setTextColor(mContext.getResources().getColor(R.color.text_color_light_yellow));

        }

    }


    public void setPraiseListener(PraiseListener praiseListener) {
        this.mPraiseListener = praiseListener;
    }

    public void setNewsListListener(NewsListListener newsListListener) { //列表点击
        this.mNewsListListener = newsListListener;
    }


}
