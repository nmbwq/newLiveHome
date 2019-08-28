package shangri.example.com.shangri.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.Constant;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.adapter.MoreArticlesAdapter;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.eventmessage.MoreArticlesEvent;
import shangri.example.com.shangri.eventmessage.NewsEvent;
import shangri.example.com.shangri.manage.WrapContentLinearLayoutManager;
import shangri.example.com.shangri.model.bean.response.MoreBean;
import shangri.example.com.shangri.model.bean.response.TrainingArticleBean;
import shangri.example.com.shangri.presenter.MoreArticlesPresenter;
import shangri.example.com.shangri.presenter.view.MoreArticlesView;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.ui.webview.ActivityWebView;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 更多  文章
 */
public class MoreArticlesActivity extends BaseActivity<MoreArticlesView, MoreArticlesPresenter> implements MoreArticlesView {

    @BindView(R.id.iv_go_back)
    ImageView ivGoBack;
    @BindView(R.id.rl_more_bg)
    RelativeLayout rlMoreBg;
    @BindView(R.id.rv_more)
    RecyclerView rvMore;
    @BindView(R.id.tv_more_title)
    TextView tvMoreTitle;
    @BindView(R.id.sv_news)
    SpringView svNews;
    private int onLoadMore = 1;
    List<MoreBean.ListBean.DataBean> moreData = new ArrayList<>();
    private MoreArticlesAdapter moreArticlesAdapter;
    private String catagory_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);


    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_more_articles;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_more_articles;
    }

    @Override
    protected MoreArticlesPresenter createPresenter() {
        return new MoreArticlesPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        WrapContentLinearLayoutManager layoutManager =
                new WrapContentLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvMore.setHasFixedSize(true);
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);

        rvMore.setNestedScrollingEnabled(false);
        rvMore.setLayoutManager(layoutManager);
        moreArticlesAdapter = new MoreArticlesAdapter(this, R.layout.item_train, moreData);

        Intent intent = getIntent();
        catagory_id = intent.getStringExtra("catagory_id");
        String img_url = intent.getStringExtra("img_url");
        String title = intent.getStringExtra("title");
        final String type = intent.getStringExtra("type");
        tvMoreTitle.setText(title);
        String token = UserConfig.getInstance().getToken();
        mPresenter.articleMore(token, catagory_id);
        moreArticlesAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (moreData.isEmpty()) return;
//                Toast.makeText(MoreArticlesActivity.this, "onItemClick" + position, Toast.LENGTH_SHORT).show();
                if (TextUtils.isEmpty(moreData.get(position).getAudio_url())) {
                    Intent intent = new Intent(MoreArticlesActivity.this, ActivityWebView.class);
                    intent.putExtra("title", moreData.get(position).getTitle());
                    intent.putExtra("url", moreData.get(position).getArticle_url());
                    intent.putExtra("imageurl", moreData.get(position).getCover_url());
                    Log.d("Debug", "当前的图片地址是" + moreData.get(position).getCover_url());
                    intent.putExtra("id", moreData.get(position).getId());
                    //浏览量
                    intent.putExtra("browse_amount", moreData.get(position).getBrowse_amount());
                    //点击量
                    intent.putExtra("good_amount", moreData.get(position).getGood_amount());
                    //收藏量
                    intent.putExtra("collect_amount", moreData.get(position).getCollect_amount());
                    //0为未点赞 1为已点赞
                    intent.putExtra("register_good", moreData.get(position).getRegister_good());
                    //0为未收藏 1为已收藏
                    intent.putExtra("register_collect", moreData.get(position).getRegister_collect());
                    intent.putExtra("type", 3+"");
                    intent.putExtra("isMore", true);
                    //分享需要的参数
                    intent.putExtra("isFormType", "train");
                    intent.putExtra("position", position);
                    mPresenter.requestBrowse(moreData.get(position).getId(), position);
//                    Log.d("Debug", "当前的url地址为" + trains.get(position).getList().get(position).getArticle_url());
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(MoreArticlesActivity.this, AudioPlayActivity.class);
                    intent.putExtra("html", moreData.get(position).getArticle_url());     //页面点赞
                    intent.putExtra("imageurl", moreData.get(position).getCover_url());   //图片地址
                    Log.d("Debug", "音频地址" + moreData.get(position).getAudio_url());
                    intent.putExtra("audiourl", moreData.get(position).getAudio_url());   //音频地址
//                        intent.putExtra("audiourl", "https://zhibohome-audio-bucket.oss-cn-qingdao.aliyuncs.com/article/2018-05-04/5aec154d05973.mp3");
                    intent.putExtra("title", moreData.get(position).getTitle());          //标题
                    intent.putExtra("register", moreData.get(position).getRegister_good());
                    intent.putExtra("position", position);
                    intent.putExtra("pageid", "ConsultationFragment");
                    intent.putExtra("id", moreData.get(position).getId());
                    intent.putExtra("type", 3+"");
                    intent.putExtra("isMore", true);
                    //0为未收藏 1为已收藏
                    intent.putExtra("register_collect", moreData.get(position).getRegister_collect());
                    startActivity(intent);
                }
            }


        });

        Glide.with(this).load(img_url)
                .into(new ViewTarget<RelativeLayout, GlideDrawable>(rlMoreBg) {
                          @Override
                          public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                              rlMoreBg.setBackground(resource.getCurrent());
                          }
                      }
                );


        svNews.setType(SpringView.Type.FOLLOW);
        svNews.setHeader(new SpringViewHeader(this));
        svNews.setFooter(new SpringViewFooter(this));
        svNews.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
//                onLoadMore = 1;
                String token = UserConfig.getInstance().getToken();
                mPresenter.articleMore(token, catagory_id);

            }

            @Override
            public void onLoadmore() {
//                onLoadMore++;
                svNews.onFinishFreshAndLoad();
//
                ToastUtil.showShort("没有更多数据");


            }
        });


    }

    @OnClick(R.id.iv_go_back)
    public void onViewClicked() {


        finish();
    }

    @Override
    public void moreArticlesSucceed(MoreBean moreBean) {


        moreData.clear();
        moreArticlesAdapter.notifyItemRangeRemoved(0, moreData.size());
        svNews.onFinishFreshAndLoad();


        moreData = moreBean.getList().getData();
        moreArticlesAdapter.setNewData(moreData);
        rvMore.setAdapter(moreArticlesAdapter);
//            moreArticlesAdapter.notifyDataSetChanged();
        moreArticlesAdapter.notifyItemRangeChanged(0, moreData.size());


    }

    @Override
    public void Browse(int position) {
        if (position < 0) return;
        String browse_amount = moreData.get(position).getBrowse_amount();
        Integer value = Integer.valueOf(browse_amount);
        moreData.get(position).setBrowse_amount(value + "");
        moreArticlesAdapter.notifyDataSetChanged();
    }

    @Override
    public void requestFailed(String message) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MoreArticlesEvent moreArticlesEvent) {

        Log.d("tpye",moreArticlesEvent.advicesType+"");

        if (moreArticlesEvent.advicesType  == Constant.TYPE_PRAISE) {//   培训点赞 局部刷新
            int praisePos = moreArticlesEvent.position;
            MoreBean.ListBean.DataBean dataBean = moreData.get(praisePos);
            int register_good = dataBean.getRegister_good();
            if (register_good == 0) {
                dataBean.setRegister_good(1);
                String good_amount = dataBean.getGood_amount();
                int valueOf = Integer.valueOf(good_amount);
                int value = valueOf + 1;

                dataBean.setGood_amount(value + "");
                moreArticlesAdapter.notifyDataSetChanged();
            } else if (register_good == 1) {
                dataBean.setRegister_good(0);
                String good_amount = dataBean.getGood_amount();
                int valueOf = Integer.valueOf(good_amount);
                int value = valueOf - 1;

                dataBean.setGood_amount(value + "");
                moreArticlesAdapter.notifyDataSetChanged();
            }
        } else if (moreArticlesEvent.advicesType == Constant.TYPE_COllECT) {

            int praisePos =moreArticlesEvent.position;

            MoreBean.ListBean.DataBean dataBean = moreData.get(praisePos);
            int register_collect = dataBean.getRegister_collect();
            if (register_collect == 0) {
                dataBean.setRegister_collect(1);
                String collect_amount = dataBean.getCollect_amount();
                Integer valueOf = Integer.valueOf(collect_amount);
                int value = valueOf + 1;
                dataBean.setCollect_amount(value + "");
                moreArticlesAdapter.notifyDataSetChanged();

            } else if (register_collect == 1) {
                dataBean.setRegister_collect(0);
                String collect_amount = dataBean.getCollect_amount();
                Integer valueOf = Integer.valueOf(collect_amount);
                int value = valueOf - 1;
                dataBean.setCollect_amount(value + "");
                moreArticlesAdapter.notifyDataSetChanged();
            }
        }

    }


}
