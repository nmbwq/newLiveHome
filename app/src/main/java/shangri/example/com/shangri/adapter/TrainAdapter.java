package shangri.example.com.shangri.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.api.RxApi;
import shangri.example.com.shangri.api.RxHelper;
import shangri.example.com.shangri.api.RxObserver;
import shangri.example.com.shangri.api.RxService;
import shangri.example.com.shangri.model.bean.request.Praise;
import shangri.example.com.shangri.model.bean.response.BaseResponseEntity;
import shangri.example.com.shangri.model.bean.response.TrainingArticleBean;
import shangri.example.com.shangri.ui.activity.AudioPlayActivity;
import shangri.example.com.shangri.ui.webview.ActivityWebView;
import shangri.example.com.shangri.util.PointUtils;
import shangri.example.com.shangri.util.StartActivityUtils;

public class TrainAdapter extends BaseQuickAdapter<TrainingArticleBean.TrainsBean.ListBean, BaseViewHolder> {
    private Context context;

    public TrainAdapter(Context context, int layoutResId, @Nullable List<TrainingArticleBean.TrainsBean.ListBean> data) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final TrainingArticleBean.TrainsBean.ListBean item) {
        TagFlowLayout flowLayout = (TagFlowLayout) helper.getView(R.id.tag_fl);
        ImageView iv_consulation_item = helper.getView(R.id.iv_consulation_item);
        final ImageView iv_dian = helper.getView(R.id.iv_dian);
        ImageView im_is_video = helper.getView(R.id.im_is_video);
        final TextView entertain_news_view_count = helper.getView(R.id.entertain_news_view_count);

        final RelativeLayout video_news_item = helper.getView(R.id.video_news_item);
        helper.setText(R.id.tv_consultaton_titel, item.getTitle());
        helper.setText(R.id.entertain_news_like_num, item.getBrowse_amount());
        helper.setText(R.id.entertain_news_view_count, item.getGood_amount());
        String myString = item.getKeywords().replace("|", " ");
        String[] split = myString.split(" ");
        List<String> sData = Arrays.asList(split);
        flowLayout.setAdapter(new TextAdapter(mContext, sData));
        if (item.getAudio_url().length() > 0) {
            im_is_video.setVisibility(View.VISIBLE);
        } else {
            im_is_video.setVisibility(View.GONE);
        }
        if (item.getRegister_good() == 0) { //未点赞
            helper.setImageResource(R.id.iv_dian, R.mipmap.icon_good);
            helper.setTextColor(R.id.entertain_news_view_count, mContext.getResources().getColor(R.color.text_color_light_black));
        } else if (item.getRegister_good() == 1) { //已点赞
            helper.setImageResource(R.id.iv_dian, R.mipmap.icon_good4);
            helper.setTextColor(R.id.entertain_news_view_count, mContext.getResources().getColor(R.color.text_color_light_yellow));
        }
        Glide.with(context)
                .load(item.getCover_url())
                .asBitmap()
                .skipMemoryCache(true)
                .dontAnimate()
                .placeholder(R.mipmap.kk_wzmrtp)

                .transform(new RoundedCornersTransformation(context, 6, 0))
                .into(iv_consulation_item);


        video_news_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (PointUtils.isFastClick()) {
                    if (TextUtils.isEmpty(UserConfig.getInstance().getToken())) {
                        StartActivityUtils.startActivity();
                    } else {
                        if (TextUtils.isEmpty(item.getAudio_url())) {
                            Intent intent = new Intent(context, ActivityWebView.class);
                            intent.putExtra("title", item.getTitle());
                            intent.putExtra("url", item.getArticle_url());
                            intent.putExtra("imageurl", item.getCover_url());
                            Log.d("Debug", "当前的图片地址是" + item.getCover_url());
                            intent.putExtra("id", item.getTrain_id());
                            //浏览量
                            intent.putExtra("browse_amount", item.getBrowse_amount());
                            //点击量
                            intent.putExtra("good_amount", item.getGood_amount());
                            //收藏量
                            intent.putExtra("collect_amount", item.getCollect_amount());
                            //0为未点赞 1为已点赞
                            intent.putExtra("register_good", item.getRegister_good());
                            //0为未收藏 1为已收藏
                            intent.putExtra("register_collect", item.getRegister_collect());
                            intent.putExtra("type", "1");
                            //分享需要的参数
                            intent.putExtra("isFormType", "train");
                            intent.putExtra("position", helper.getAdapterPosition());
                            intent.putExtra("isMore", false);
                            intent.putExtra("create_time", item.getPublish_time()+"");
//                                mPresenter.requestBrowse(item.getId(), position);
                            Log.d("Debug", "当前的url地址为" + item.getArticle_url());
                            context.startActivity(intent);
                            return;
                        } else {
                            Intent intent = new Intent(context, AudioPlayActivity.class);
                            intent.putExtra("html", item.getArticle_url());     //页面点赞
                            intent.putExtra("imageurl", item.getCover_url());   //图片地址
                            Log.d("Debug", "音频地址" + item.getAudio_url());
                            intent.putExtra("audiourl", item.getAudio_url());   //音频地址
//                        intent.putExtra("audiourl", "https://zhibohome-audio-bucket.oss-cn-qingdao.aliyuncs.com/article/2018-05-04/5aec154d05973.mp3");
                            intent.putExtra("title", item.getTitle());          //标题
                            intent.putExtra("register", item.getRegister_good());
                            intent.putExtra("position", helper.getAdapterPosition());
                            intent.putExtra("pageid", "ConsultationFragment");
                            intent.putExtra("isMore", false);
                            intent.putExtra("id", item.getTrain_id());
                            intent.putExtra("type", "1");
                            intent.putExtra("create_time", item.getPublish_time()+"");
                            //0为未收藏 1为已收藏
                            intent.putExtra("register_collect", item.getRegister_collect());
                            context.startActivity(intent);
                            return;
                        }
                    }
                }


            }
        });
        iv_dian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (TextUtils.isEmpty(UserConfig.getInstance().getToken())) {
                    StartActivityUtils.startActivity();
                } else {
                    if (item.getRegister_good() == 0) {
                        // // 未点赞，则点赞
                        String infoId = item.getTrain_id();
                        createPraise(infoId);
                        item.setRegister_good(1);
                        String good_amount = item.getGood_amount();
                        int good = Integer.valueOf(good_amount);
                        int value = good + 1;
                        item.setGood_amount(value + "");
                        entertain_news_view_count.setTextColor(context.getResources().getColor(R.color.text_color_light_yellow));
                        entertain_news_view_count.setText(value + "");
                        iv_dian.setImageResource(R.mipmap.icon_good4);
                    } else if (item.getRegister_good() == 1) {
//                        // 已点赞，则取消点赞
                        String infoId = item.getTrain_id();
                        deletePraise(infoId);
                        item.setRegister_good(0);
                        String good_amount = item.getGood_amount();
                        int value = Integer.valueOf(good_amount);
                        int amoun = value - 1;
                        item.setGood_amount(amoun + "");
                        entertain_news_view_count.setTextColor(context.getResources().getColor(R.color.text_color_light_black));
                        entertain_news_view_count.setText(amoun + "");
                        iv_dian.setImageResource(R.mipmap.icon_good);
                    }

                }

            }
        });
    }


    protected CompositeDisposable mCompositeDisposable;
    protected RxService mRxSerivce = RxApi.getInstance().getService(RxService.class);

    public void createPraise(String infoId) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {

            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {

            }
        };
        Praise bean = new Praise();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setArticle_id(infoId);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.praise(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }


    public void deletePraise(String infoId) {
        RxObserver rxObserver = new RxObserver<Object>() {
            @Override
            public void onHandleSuccess(Object resultBean) {

            }

            @Override
            public void onHandleComplete() {

            }

            @Override
            public void onHandleFailed(String message) {

            }
        };
        Praise bean = new Praise();
        bean.setToken(UserConfig.getInstance().getToken());
        bean.setArticle_id(infoId);
        Observable<BaseResponseEntity<Object>> observable = mRxSerivce.articlecancel(bean);
        Disposable disposable = observable
                .compose(RxHelper.handleObservableResult())
                .subscribeWith(rxObserver);
        addSubscribe(disposable);
    }

    /**
     * 添加
     *
     * @param disposable
     */
    protected void addSubscribe(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }
}
