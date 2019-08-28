package shangri.example.com.shangri.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;
import com.aspsine.irecyclerview.universaladapter.recyclerview.OnItemClickListener;

import org.greenrobot.eventbus.EventBus;
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
import shangri.example.com.shangri.adapter.NewsAdapter;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.eventmessage.NewsEvent;
import shangri.example.com.shangri.model.bean.response.BannerHomeLookBean;
import shangri.example.com.shangri.model.bean.response.CultivateBean;
import shangri.example.com.shangri.model.bean.response.HeadLinesData;
import shangri.example.com.shangri.model.bean.response.LookBannerBean;
import shangri.example.com.shangri.model.bean.response.NewsBean;
import shangri.example.com.shangri.model.bean.response.TrainingArticleBean;
import shangri.example.com.shangri.presenter.ConsultationPresenter;
import shangri.example.com.shangri.presenter.view.ConsultationView;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.view.FastLinearScrollManger;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.ui.webview.ActivityWebView;
import shangri.example.com.shangri.util.ActivityUtils;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.PointUtils;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 头条activity界面
 * Created by admin on 2017/12/22.
 */

public class TitleArticleActivity extends BaseActivity<ConsultationView, ConsultationPresenter> implements ConsultationView {
    @BindView(R.id.rv_partol)
    RecyclerView rv_partol;
    @BindView(R.id.sv_partol)
    SpringView sv_partol;
    @BindView(R.id.activity_empty_linshi)
    LinearLayout activity_empty_linshi;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.right)
    TextView right;


    @BindView(R.id.layout_network_break)
    LinearLayout layout_network_break;
    @BindView(R.id.rl_net_info)
    RelativeLayout rl_net_info;


    private static final int ACTION_FRESH = 0; //刷新
    private static final int ACTION_LOAD_MORE = 1; //加载更多
    private static int ACTION_TYPE = 0;
    private ProgressDialogFragment mProgressDialog;
    private int currPage = 1;
    //一共有几页数据
    private int mPageNum;
//    private ComBoAdapter mAdapter;
//    private List<legalMypagerBean.PayOrderBean> mList = new ArrayList<>();

    //文章数据接收体以及适配器
    private NewsAdapter newsAdapter;
    List<NewsBean.ArticlesBean.DataBean> newsData = new ArrayList<>();

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_combo_list;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_combo_list;
    }

    @Override
    protected ConsultationPresenter createPresenter() {
        return new ConsultationPresenter(this, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!NetWorkUtil.isNetworkConnected(TitleArticleActivity.this)) {
            rl_net_info.setVisibility(View.GONE);
            layout_network_break.setVisibility(View.VISIBLE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
            sv_partol.onFinishFreshAndLoad(); //停止加载
        } else {
            rl_net_info.setVisibility(View.VISIBLE);
            layout_network_break.setVisibility(View.GONE);
            currPage = 1;
            mPresenter.newInfo(currPage);
        }
    }

    @Override
    protected void initViewsAndEvents() {
        initSpringView();
        reload();
        String name = getIntent().getStringExtra("name");
        title.setText(name + "");
        right.setVisibility(View.GONE);
        rv_partol.setLayoutManager(new FastLinearScrollManger(TitleArticleActivity.this));
        newsAdapter = new NewsAdapter(this, R.layout.item_train, newsData);
        newsAdapter.openLoadAnimation(new ScaleInAnimation());
        newsAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                if (PointUtils.isFastClick()) {
                    if (TextUtils.isEmpty(UserConfig.getInstance().getToken())) {
                        ActivityUtils.startActivity(TitleArticleActivity.this, NewLoginUserActivity.class);
                    } else {
                        if (TextUtils.isEmpty(newsData.get(position).getAudio_url())) {
                            Intent intent = new Intent(TitleArticleActivity.this, ActivityWebView.class);
                            intent.putExtra("title", newsData.get(position).getTitle());
                            intent.putExtra("url", newsData.get(position).getArticle_url());
                            intent.putExtra("imageurl", newsData.get(position).getCover_url());
                            intent.putExtra("create_time", newsData.get(position).getPublish_time());
                            Log.d("Debug", "当前的图片地址是" + newsData.get(position).getCover_url());
                            intent.putExtra("id", newsData.get(position).getId());
                            //浏览量
                            intent.putExtra("browse_amount", newsData.get(position).getBrowse_amount());
                            //点击量
                            intent.putExtra("good_amount", newsData.get(position).getGood_amount());
                            //收藏量
                            intent.putExtra("collect_amount", newsData.get(position).getCollect_amount());
                            //0为未点赞 1为已点赞
                            intent.putExtra("register_good", newsData.get(position).getRegister_good());
                            //0为未收藏 1为已收藏
                            intent.putExtra("register_collect", newsData.get(position).getRegister_collect());
                            intent.putExtra("type", "2");
                            intent.putExtra("isMore", false);
                            //分享需要的参数
                            intent.putExtra("isFormType", "train");
                            intent.putExtra("position", position);
                            mPresenter.requestBrowse(newsData.get(position).getId(), position);
                            Log.d("Debug", "当前的url地址为" + newsData.get(position).getArticle_url());
                            startActivity(intent);
                            return;
                        } else {
                            Intent intent = new Intent(TitleArticleActivity.this, AudioPlayActivity.class);
                            intent.putExtra("html", newsData.get(position).getArticle_url());     //页面点赞
                            intent.putExtra("imageurl", newsData.get(position).getCover_url());   //图片地址
                            Log.d("Debug", "音频地址" + newsData.get(position).getAudio_url());
                            intent.putExtra("audiourl", newsData.get(position).getAudio_url());   //音频地址
//                        intent.putExtra("audiourl", "https://zhibohome-audio-bucket.oss-cn-qingdao.aliyuncs.com/article/2018-05-04/5aec154d05973.mp3");
                            intent.putExtra("title", newsData.get(position).getTitle());          //标题
                            intent.putExtra("register", newsData.get(position).getRegister_good());
                            intent.putExtra("position", position);
                            intent.putExtra("pageid", "ConsultationFragment");
                            intent.putExtra("id", newsData.get(position).getId());
                            intent.putExtra("type", "2");
                            intent.putExtra("isMore", false);
                            Log.d("Debug", "穿过去的时间为" + newsData.get(position).getPublish_time());
                            intent.putExtra("create_time", newsData.get(position).getPublish_time() + "");
                            //0为未收藏 1为已收藏
                            intent.putExtra("register_collect", newsData.get(position).getRegister_collect());
                            startActivity(intent);
                            return;
                        }
                    }
                }
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
        rv_partol.setAdapter(newsAdapter);
    }


    public void reload() {
        if (!NetWorkUtil.isNetworkConnected(TitleArticleActivity.this)) {
            rl_net_info.setVisibility(View.GONE);
            layout_network_break.setVisibility(View.VISIBLE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
            sv_partol.onFinishFreshAndLoad(); //停止加载
        } else {
            rl_net_info.setVisibility(View.VISIBLE);
            layout_network_break.setVisibility(View.GONE);
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialogFragment();
            }
            mProgressDialog.show(this.getSupportFragmentManager());
            currPage = 1;
            mPresenter.newInfo(currPage);
        }
    }

    @Override
    public void requestFailed(String message) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    private void initSpringView() {
        sv_partol.setGive(SpringView.Give.TOP);
        sv_partol.setType(SpringView.Type.FOLLOW);
        sv_partol.setHeader(new SpringViewHeader(this));
        sv_partol.setFooter(new SpringViewFooter(this));
        sv_partol.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                reload();
            }

            @Override
            public void onLoadmore() {
                requestTastList();
            }
        });

    }


    private void requestTastList() {
        if (currPage < mPageNum) {
            currPage++;
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialogFragment();
            }
            mProgressDialog.show(this.getSupportFragmentManager());
            mPresenter.newInfo(currPage);
        } else {
            sv_partol.onFinishFreshAndLoad(); //停止加载
        }

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEventMain(NewsEvent baseEvent) {
//        ToastUtil.showLong("消息过来了1111.....");
        if (baseEvent.advicesType == Constant.TYPE_HEAD_PRAISE) {
//            ToastUtil.showLong("消息过来了.....");//详情点赞后 资讯列表局部刷新


            int praisePos = baseEvent.position;
//                ToastUtil.showShort("消息过来了"+praisePos);
            NewsBean.ArticlesBean.DataBean pageData = newsData.get(praisePos);
            if (pageData.getRegister_good() == 0) {
                pageData.setRegister_good(1);
                pageData.setGood_amount((Integer.parseInt(pageData.getGood_amount()) + 1) + "");
                newsAdapter.notifyDataSetChanged();
            } else if (pageData.getRegister_good() == 1) {
                pageData.setRegister_good(0);
                pageData.setGood_amount((Integer.parseInt(pageData.getGood_amount()) - 1) + "");
                newsAdapter.notifyDataSetChanged();
            }

        } else if (baseEvent.advicesType == Constant.TYPE_HEAD_COllECT) { //资讯详情中收藏


            int praisePos = baseEvent.position;
            NewsBean.ArticlesBean.DataBean pageData = newsData.get(praisePos);
            if (pageData.getRegister_collect() == 0) {
                pageData.setRegister_collect(1);
                pageData.setCollect_amount((Integer.parseInt(pageData.getCollect_amount()) + 1) + "");
                newsAdapter.notifyDataSetChanged();
            } else if (pageData.getRegister_collect() == 1) {
                pageData.setRegister_collect(0);
                pageData.setCollect_amount((Integer.parseInt(pageData.getCollect_amount()) - 1) + "");
                newsAdapter.notifyDataSetChanged();
            }

        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.setting_back, R.id.reload})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_back:
                finish();
                break;
            case R.id.reload:
                reload();
                break;
        }
    }


    @Override
    public void bannerDetail(BannerHomeLookBean resultBean) {

    }

    @Override
    public void praise(int position) {

    }

    @Override
    public void Browse(int position) {

    }

    @Override
    public void search(HeadLinesData resultBean) {

    }

    @Override
    public void Collect(int position) {

    }

    @Override
    public void PeixunSearch(BannerHomeLookBean resultBean) {

    }

    @Override
    public void newsSucceed(NewsBean resultBean) {
        sv_partol.onFinishFreshAndLoad();
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        mPageNum = resultBean.getArticles().getTotal();
        if (currPage == 1) {
            newsData.clear();
            if (resultBean.getArticles().getData().size() > 0) {
                sv_partol.setVisibility(View.VISIBLE);
                activity_empty_linshi.setVisibility(View.GONE);
            } else {
                sv_partol.setVisibility(View.GONE);
                activity_empty_linshi.setVisibility(View.VISIBLE);
            }
        }
        newsAdapter.addAll(resultBean.getArticles().getData());
        newsData = newsAdapter.getAll();
    }

    @Override
    public void cultivateSucceed(CultivateBean cultivateBean) {

    }

    @Override
    public void lookBannerSucceed(LookBannerBean lookBannerBean) {

    }

    @Override
    public void trainingArticleSucceed(TrainingArticleBean trainingArticleBean) {

    }
}
