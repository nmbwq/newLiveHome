package shangri.example.com.shangri.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.target.Target;
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
import shangri.example.com.shangri.model.bean.response.MoreBean;
import shangri.example.com.shangri.ui.activity.LoginTypeActivity;
import shangri.example.com.shangri.util.GlideRoundTransform;
import shangri.example.com.shangri.util.StartActivityUtils;

/**
 * 更多文章
 */
public class MoreArticlesAdapter extends BaseQuickAdapter<MoreBean.ListBean.DataBean, BaseViewHolder> {
    private Context context;

    public MoreArticlesAdapter(Context context, int layoutResId, @Nullable List<MoreBean.ListBean.DataBean> data) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final MoreBean.ListBean.DataBean item) {
        helper.setText(R.id.tv_consultaton_titel, item.getTitle());
        helper.setText(R.id.entertain_news_like_num, item.getBrowse_amount());
        helper.setText(R.id.entertain_news_view_count, item.getGood_amount());
        final TextView entertain_news_view_count = helper.getView(R.id.entertain_news_view_count);
        final ImageView iv_dian = helper.getView(R.id.iv_dian);

        ImageView im_is_video = helper.getView(R.id.im_is_video);

        if (item.getAudio_url().length() > 0) {
            im_is_video.setVisibility(View.VISIBLE);
        } else {
            im_is_video.setVisibility(View.GONE);
        }


        TagFlowLayout flowLayout = (TagFlowLayout) helper.getView(R.id.tag_fl);
        String myString = item.getKeywords().replace("|", " ");
        String[] split = myString.split(" ");
        List<String> sData = Arrays.asList(split);


        flowLayout.setAdapter(new TextAdapter(mContext, sData));


        if (item.getRegister_good() == 0) { //未点赞
            iv_dian.setImageResource(R.mipmap.icon_good);
            helper.setTextColor(R.id.entertain_news_view_count, mContext.getResources().getColor(R.color.text_color_light_black));
        } else if (item.getRegister_good() == 1) { //已点赞
            iv_dian.setImageResource(R.mipmap.icon_good4);
            helper.setTextColor(R.id.entertain_news_view_count, mContext.getResources().getColor(R.color.text_color_light_yellow));
        }
        ImageView iv_consulation_item = helper.getView(R.id.iv_consulation_item);


        Glide.with(context)
                .load(item.getCover_url())

                .crossFade()
                .placeholder(R.mipmap.bg_touxiang)

                .transform(new GlideRoundTransform(context, 6))
                .into(iv_consulation_item);


        iv_dian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (TextUtils.isEmpty(UserConfig.getInstance().getToken())) {
                    StartActivityUtils.startActivity();
                } else {
                    String infoId = item.getId();
                    if (item.getRegister_good() == 0) {
                        createPraise(infoId);// 未点赞，则点赞

                        item.setRegister_good(1);
                        String good_amount = item.getGood_amount();
                        int good = Integer.valueOf(good_amount);
                        int value = good + 1;
                        item.setGood_amount(value + "");
                        entertain_news_view_count.setTextColor(mContext.getResources().getColor(R.color.text_color_light_yellow));
                        iv_dian.setImageResource(R.mipmap.icon_good4);
                        entertain_news_view_count.setText(value + "");
                    } else if (item.getRegister_good() == 1) {
                        deletePraise(infoId); // 已点赞，则取消点赞
                        entertain_news_view_count.setTextColor(mContext.getResources().getColor(R.color.text_color_light_black));
                        iv_dian.setImageResource(R.mipmap.icon_good);
                        item.setRegister_good(0);
                        String good_amount = item.getGood_amount();
                        int good = Integer.valueOf(good_amount);
                        int value = good - 1;
                        item.setGood_amount(value + "");
                        entertain_news_view_count.setText(value + "");

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
