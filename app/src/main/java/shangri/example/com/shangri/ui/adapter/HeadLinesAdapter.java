package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.response.BannerHomeLookBean;
import shangri.example.com.shangri.model.bean.response.HeadLinesData;
import shangri.example.com.shangri.ui.listener.NewsListListener;
import shangri.example.com.shangri.ui.listener.PraiseListener;
import shangri.example.com.shangri.util.ViewHolder;


/**
 * 咨询 adapter
 * Created by chengaofu on 2017/6/22.
 */

public class HeadLinesAdapter extends BaseListAdapter<HeadLinesData.ArticlesBean> {

    private Context mContext;
    private Animation mLikeAnim;
    private PraiseListener mPraiseListener;
    private NewsListListener mNewsListListener;

    public HeadLinesAdapter(Context context, int layoutId, List<HeadLinesData.ArticlesBean> datas) {
        super(context, layoutId, datas);
        mContext = context;
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
    }

    @Override
    public void convert(final ViewHolder helper, HeadLinesData.ArticlesBean data) { //, List<Object> payloads

        ImageView iv_consulation_item = helper.getView(R.id.iv_consulation_item);
        TextView tv_consultaton_titel = helper.getView(R.id.tv_consultaton_titel);
        TextView entertain_news_like_num = helper.getView(R.id.entertain_news_like_num);
        TextView entertain_news_view_count = helper.getView(R.id.entertain_news_view_count);
        TextView tv_increase = helper.getView(R.id.tv_increase);
        ImageView iv_dian = helper.getView(R.id.iv_dian);


        Glide.with(mContext)
                .load(data.getCover_url())
                .placeholder(R.mipmap.bg_touxiang)
                .crossFade()
                .into(iv_consulation_item);
        tv_consultaton_titel.setText(data.getTitle());
        entertain_news_like_num.setText(data.getBrowse_amount());
        entertain_news_view_count.setText(data.getGood_amount());
        String myString = data.getKeywords().replace("|", "   ");

        tv_increase.setText(myString);
//        TextView likeNum = helper.getView(R.id.video_news_like_num);
//        TextView content = helper.getView(R.id.video_news_content);
//        content.setText(data.getTitle());
//        TextView browseCount = helper.getView(R.id.video_news_view_count);
//        browseCount.setText(data.getBrowseCount());
//        TextView playCount = helper.getView(R.id.video_news_play_count);
//        playCount.setText(data.getBrowseCount());
//        TextView timestamp = helper.getView(R.id.video_news_timestamp);

        //日期 1970-01-01
//        timestamp.setText(RegexUtil.format(data.getReleaseTime(), RegexUtil.defaultDatePattern));
//        if(data.getReleaseTime() > 0){
//            timestamp.setText(DateUtil.format(new Date(data.getReleaseTime())));
//        }else{
//            timestamp.setText("");
//        }
        if (data.getRegister_good() == 0) { //未点赞
            iv_dian.setImageResource(R.mipmap.icon_good);
            entertain_news_view_count.setTextColor(mContext.getResources().getColor(R.color.text_color_light_black));
        } else if (data.getRegister_good() == 1) { //已点赞
            iv_dian.setImageResource(R.mipmap.icon_good4);
            entertain_news_view_count.setTextColor(mContext.getResources().getColor(R.color.text_color_light_yellow));
        }
//        likeNum.setText(String.valueOf(data.getPraiseCount()));

//        ImageView thumbImageView = helper.getView(R.id.video_news_thumb);
//        thumbImageView.setScaleType(ImageView.ScaleType.FIT_XY);

//        List<String> picList = data.getPictureUrlList();
//        if (picList != null && picList.size() > 0) {
//            BitmapCache.getInstance().loadBitmaps(thumbImageView, picList.get(0),null); //因为是黑底，所以背景改了也看不出来
//        } else {
//            thumbImageView.setImageResource(R.mipmap.jiazaitu_small);
//        }

        helper.getView(R.id.ll_dian).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mPraiseListener != null) {
                    mPraiseListener.onPraise(getPosition(helper)-1);
                }

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
    public void convert(ViewHolder helper, HeadLinesData.ArticlesBean data, List<Object> payloads) { //item局部刷新
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
//        entertain_news_view_count.setText(String.valueOf(data.getGood_amount()));
//            final TextView like = helper.getView(R.id.addOne_like); //点赞动画
//            if (like.getVisibility() == View.GONE) {
//                like.setVisibility(View.VISIBLE);
//                like.startAnimation(mLikeAnim);
//                new Handler().postDelayed(new Runnable() {
//                    public void run() {
//                        like.setVisibility(View.GONE);
//                    }
//                }, 1000);
//            }
//        ImageView likeFinger = helper.getView(R.id.video_news_like_click);
//        TextView likeNum = helper.getView(R.id.video_news_like_num);
//        TextView playCount = helper.getView(R.id.video_news_play_count);
//        playCount.setText(data.getBrowseCount());
//        TextView browseCount = helper.getView(R.id.video_news_view_count);
//        browseCount.setText(data.getBrowseCount());
//        if (data.getIsPraise() == 0) { //未点赞
//            likeFinger.setImageResource(R.mipmap.ic_zx_dianzan2);
//            likeNum.setTextColor(mContext.getResources().getColor(R.color.text_color_little_black));
//        } else if (data.getIsPraise() == 1) { //已点赞
//            likeFinger.setImageResource(R.mipmap.ic_zx_dianzan4);
//            likeNum.setTextColor(mContext.getResources().getColor(R.color.text_color_light_yellow));
//            final TextView like = helper.getView(R.id.addOne_like); //点赞动画
//            if (like.getVisibility() == View.GONE) {
//                like.setVisibility(View.VISIBLE);
//                like.startAnimation(mLikeAnim);
//                new Handler().postDelayed(new Runnable() {
//                    public void run() {
//                        like.setVisibility(View.GONE);
//                    }
//                }, 1000);
//            }
//        }
//        likeNum.setText(String.valueOf(data.getPraiseCount()));



    public void setPraiseListener(PraiseListener praiseListener) {
        this.mPraiseListener = praiseListener;
    }

    public void setNewsListListener(NewsListListener newsListListener) { //列表点击
        this.mNewsListListener = newsListListener;
    }


}
