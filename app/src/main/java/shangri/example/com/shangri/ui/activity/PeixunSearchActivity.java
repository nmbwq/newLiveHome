package shangri.example.com.shangri.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import shangri.example.com.shangri.Constant;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.base.BaseEvent;
import shangri.example.com.shangri.model.bean.event.PraiseEventBean1;
import shangri.example.com.shangri.model.bean.event.RegisterCollect1;
import shangri.example.com.shangri.model.bean.response.BannerHomeLookBean;
import shangri.example.com.shangri.model.bean.response.CultivateBean;
import shangri.example.com.shangri.model.bean.response.HeadLinesData;
import shangri.example.com.shangri.model.bean.response.LookBannerBean;
import shangri.example.com.shangri.model.bean.response.NewsBean;
import shangri.example.com.shangri.model.bean.response.TrainingArticleBean;
import shangri.example.com.shangri.presenter.ConsultationPresenter;
import shangri.example.com.shangri.presenter.view.ConsultationView;
import shangri.example.com.shangri.ui.adapter.ConsultationAdapter;
import shangri.example.com.shangri.ui.listener.NewsListListener;
import shangri.example.com.shangri.ui.listener.PraiseListener;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.ui.webview.ActivityWebView;
import shangri.example.com.shangri.util.ActivityUtils;
import shangri.example.com.shangri.util.KeyBoardUtil;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.PointUtils;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 搜索
 * Created by chengaofu on 2017/6/26.
 */

public class PeixunSearchActivity extends BaseActivity<ConsultationView, ConsultationPresenter> implements ConsultationView {
    @BindView(R.id.search_irv)
    RecyclerView mSearchRecycler;
    @BindView(R.id.search_springview)
    SpringView mSearchSpringView;
    @BindView(R.id.search_content)
    EditText mSearchText;
    @BindView(R.id.search_delete)
    ImageView mDeleteImage;
    @BindView(R.id.search_cancel)
    TextView mCancelText;
    @BindView(R.id.search_not_found)
    LinearLayout mSearchNotFound;
    @BindView(R.id.search_no)
    LinearLayout search_no;

    @BindView(R.id.layout_network_break)
    LinearLayout layout_network_break;
    @BindView(R.id.rl_net_info)
    RelativeLayout rl_net_info;

    private int mPraisePos = 0; //点赞的位置
    private boolean mIsCancel = true; //是否取消, 默认可以取消
    //用来添加消息头
    private List<BannerHomeLookBean.TrainsBean> mNewsList = new ArrayList<>();
    private ConsultationAdapter mAdapter;
    private int currPage = 1;
    private final int PAGE_SIZE = 5;
    private String mSearchContent = ""; //搜索内容

    @Override
    protected ConsultationPresenter createPresenter() {
        return new ConsultationPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        initSpringView();
        mNewsList.clear();
        mSearchRecycler.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ConsultationAdapter(this, R.layout.item_consultation_peixun, mNewsList);
        mAdapter.openLoadAnimation(new ScaleInAnimation());
        mSearchRecycler.setAdapter(mAdapter);
//        mPresenter.search(currPage, mSearchContent);
        mSearchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String content = editable.toString();
                if (TextUtils.isEmpty(content)) {
                    mDeleteImage.setVisibility(View.GONE);
                    mIsCancel = true;
                    mCancelText.setText(getResources().getString(R.string.cancel));
                } else {
                    mDeleteImage.setVisibility(View.VISIBLE);
                    mIsCancel = false;
                    mCancelText.setText(getResources().getString(R.string.search));
                }
            }
        });

        mSearchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    mSearchContent = mSearchText.getText().toString();
                    if (TextUtils.isEmpty(mSearchContent)) {
                        ToastUtil.showShort("请输入内容后搜索");
                        return false;
                    }
                    KeyBoardUtil.hide_keyboard_from(PeixunSearchActivity.this, mSearchText);
                    searchBefore();
                    if (!NetWorkUtil.isNetworkConnected(PeixunSearchActivity.this)) {
                        rl_net_info.setVisibility(View.GONE);
                        layout_network_break.setVisibility(View.VISIBLE);
                        ToastUtil.showShort(getResources().getString(R.string.search_not_net));
                    } else {
                        rl_net_info.setVisibility(View.VISIBLE);
                        layout_network_break.setVisibility(View.GONE);
                        mPresenter.PeixunSearch(currPage, mSearchContent);
                    }
                    return true;
                }
                return false;
            }
        });

        mAdapter.setNewsListListener(new NewsListListener() {

            @Override
            public void onItemClickListener(int position) {
                if (PointUtils.isFastClick()) {
//                int position = viewHolder.getAdapterPosition();
                    BannerHomeLookBean.TrainsBean bean = mNewsList.get(position);
                    if (TextUtils.isEmpty(bean.getAudio_url())) {
                        Intent intent = new Intent(PeixunSearchActivity.this, ActivityWebView.class);
                        intent.putExtra("title", bean.getTitle());
                        intent.putExtra("url", bean.getArticle_url());
                        intent.putExtra("imageurl", bean.getCover_url());
                        Log.d("Debug", "当前的图片地址是" + bean.getCover_url());
                        intent.putExtra("id", bean.getId() + "");
                        //浏览量
                        intent.putExtra("browse_amount", bean.getBrowse_amount());
                        //点击量
                        intent.putExtra("good_amount", bean.getGood_amount());
                        //收藏量
                        intent.putExtra("collect_amount", bean.getCollect_amount());
                        //0为未点赞 1为已点赞
                        intent.putExtra("register_good", bean.getRegister_good());
                        //0为未收藏 1为已收藏
                        intent.putExtra("register_collect", bean.getRegister_collect());
                        intent.putExtra("type", "1");
                        //分享需要的参数
                        intent.putExtra("isFormType", "train");
                        intent.putExtra("position", position);
                        mPresenter.requestBrowse(bean.getId(), position);
                        Log.d("Debug", "当前的url地址为" + bean.getArticle_url());
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(PeixunSearchActivity.this, AudioPlayActivity.class);
                        intent.putExtra("html", bean.getArticle_url());     //页面点赞
                        intent.putExtra("imageurl", bean.getCover_url());   //图片地址
                        Log.d("Debug", "音频地址" + bean.getAudio_url());
                        intent.putExtra("audiourl", bean.getAudio_url());   //音频地址
//                        intent.putExtra("audiourl", "https://zhibohome-audio-bucket.oss-cn-qingdao.aliyuncs.com/article/2018-05-04/5aec154d05973.mp3");
                        intent.putExtra("title", bean.getTitle());          //标题
                        intent.putExtra("register", bean.getRegister_good());
                        intent.putExtra("position", position);
                        intent.putExtra("pageid", "ConsultationFragment");
                        intent.putExtra("id", bean.getId());
                        intent.putExtra("type", "1");
                        //0为未收藏 1为已收藏
                        intent.putExtra("register_collect", bean.getRegister_collect());
                        startActivity(intent);
                    }
                }
            }
        });

        mAdapter.setPraiseListener(new PraiseListener() {
            @Override
            public void onPraise(int position) {
                mPraisePos = position + 1;
                BannerHomeLookBean.TrainsBean pageData = mAdapter.get(mPraisePos);
                Log.d("Debug", "返回的pageData.getRegister_good()" + pageData.getRegister_good());
                String infoId = pageData.getId();
                if (pageData.getRegister_good() == 0) {
                    mPresenter.requestPraise(infoId, mPraisePos);// 未点赞，则点赞
                } else if (pageData.getRegister_good() == 1) {
                    mPresenter.requestCancel(infoId, mPraisePos); // 已点赞，则取消点赞
                }
            }

            @Override
            public void onColltion(int position) {

            }
        });

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEventMain(BaseEvent baseEvent) {
        if (baseEvent.getEventId() == Constant.TYPE_PRAISE) {   //详情点赞后 列表局部刷新
            PraiseEventBean1 messageEvent = (PraiseEventBean1) baseEvent.getData();
            if (messageEvent == null) return;
            if (messageEvent.getType() == Constant.ConsultationFragment) {
                int praisePos = messageEvent.getPraisePosition();
                BannerHomeLookBean.TrainsBean pageData = mNewsList.get(praisePos);
                if (pageData.getRegister_good() == 0) {
                    pageData.setRegister_good(1);
                    pageData.setGood_amount((Integer.parseInt(pageData.getGood_amount()) + 1) + "");
                    mAdapter.notifyDataSetChanged();
                } else if (pageData.getRegister_good() == 1) {
                    pageData.setRegister_good(0);
                    pageData.setGood_amount((Integer.parseInt(pageData.getGood_amount()) - 1) + "");
                    mAdapter.notifyDataSetChanged();
                }
            }
        } else if (baseEvent.getEventId() == Constant.TYPE_COllECT) { //详情中收藏
            RegisterCollect1 messageEvent = (RegisterCollect1) baseEvent.getData();
            if (messageEvent == null) return;
            if (messageEvent.getType() == Constant.ConsultationFragment) {
                int praisePos = messageEvent.getCollectPosition();
                BannerHomeLookBean.TrainsBean pageData = mNewsList.get(praisePos);
                if (pageData.getRegister_collect() == 0) {
                    pageData.setRegister_collect(1);
                    pageData.setCollect_amount((Integer.parseInt(pageData.getCollect_amount()) + 1) + "");
                    mAdapter.notifyDataSetChanged();
                } else if (pageData.getRegister_collect() == 1) {
                    pageData.setRegister_collect(0);
                    pageData.setCollect_amount((Integer.parseInt(pageData.getCollect_amount()) - 1) + "");
                    mAdapter.notifyDataSetChanged();
                }
            }
        }
    }

    private void refreshPeiCollect(int pos) { //收藏刷新
        BannerHomeLookBean.TrainsBean pageData = mNewsList.get(pos);
        if (pageData != null) {
            if (pageData.getRegister_collect() == 0) {
                pageData.setRegister_collect(1);
            } else if (pageData.getRegister_collect() == 1) {
                pageData.setRegister_collect(0);
            }
//            mAdapter.notifyItemRangeChanged(pos + 1, 1, "");//局部刷新点赞
        }
    }

    private void refreshPraiseCount(int pos, long count) { //点赞刷新
        BannerHomeLookBean.TrainsBean pageData = mNewsList.get(pos);
        if (pageData != null) {
            if (pageData.getRegister_good() == 0) {
                pageData.setRegister_good(1);
            } else if (pageData.getRegister_good() == 1) {
                pageData.setRegister_good(0);
            }
            pageData.setGood_amount(String.valueOf(count));
            mAdapter.notifyItemRangeChanged(pos, 1, "");//局部刷新点赞
        }
    }

    private void initSpringView() {
        mSearchSpringView.setType(SpringView.Type.FOLLOW);
        mSearchSpringView.setHeader(new SpringViewHeader(this));
        mSearchSpringView.setFooter(new SpringViewFooter(this));
        mSearchSpringView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }

            @Override
            public void onLoadmore() {
                searchNewsList();
            }
        });

    }


    private void loadData() {
        if (!NetWorkUtil.isNetworkConnected(PeixunSearchActivity.this)) {
            rl_net_info.setVisibility(View.GONE);
            layout_network_break.setVisibility(View.VISIBLE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
            mSearchSpringView.onFinishFreshAndLoad(); //停止加载
        } else {
            rl_net_info.setVisibility(View.VISIBLE);
            layout_network_break.setVisibility(View.GONE);
        }
        mNewsList.clear();
        if (mAdapter != null) {
            mAdapter.clear();
        }
        currPage = 1;
        mAdapter.notifyDataSetChanged();

        String content = mSearchText.getText().toString();
        if (!TextUtils.isEmpty(content)) {
            mPresenter.PeixunSearch(currPage, content);
        } else {
            ToastUtil.showShort("请输入内容后搜索");
            mSearchSpringView.onFinishFreshAndLoad(); //停止加载

        }
    }

    private void searchNewsList() {
        if (!NetWorkUtil.isNetworkConnected(PeixunSearchActivity.this)) {
            rl_net_info.setVisibility(View.GONE);
            layout_network_break.setVisibility(View.VISIBLE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
            mSearchSpringView.onFinishFreshAndLoad(); //停止加载
        } else {
            rl_net_info.setVisibility(View.VISIBLE);
            layout_network_break.setVisibility(View.GONE);
        }
        int mPageNum = 0;
        if (currPage < mPageNum) {
            currPage++;
            mSearchSpringView.onFinishFreshAndLoad();
            mPresenter.PeixunSearch(currPage, mSearchContent);
        } else {
            mSearchSpringView.onFinishFreshAndLoad(); //停止加载
        }
    }


    @OnClick({R.id.search_cancel, R.id.search_delete, R.id.reload})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_cancel:
                if (mIsCancel) { //取消
                    finish();
                } else {  //搜索
                    searchBefore();
                    mSearchContent = mSearchText.getText().toString();
                    if (!NetWorkUtil.isNetworkConnected(PeixunSearchActivity.this)) {
                        rl_net_info.setVisibility(View.GONE);
                        layout_network_break.setVisibility(View.VISIBLE);
                        ToastUtil.showShort(getResources().getString(R.string.search_not_net));
                    } else {
                        rl_net_info.setVisibility(View.VISIBLE);
                        layout_network_break.setVisibility(View.GONE);
                    }
                    mPresenter.PeixunSearch(currPage, mSearchContent);
                }

                break;
            case R.id.search_delete: //删除
                mSearchText.setText("");
                break;
            case R.id.reload: //删除
                loadData();
                break;
        }
    }


    @Override
    public void bannerDetail(BannerHomeLookBean resultBean) {

    }

    @Override
    public void praise(int praisePos) {
        int Good_amount = 0;
        try {
            if (praisePos == -1) {
                return;
            }
            if (mNewsList.get(praisePos).getRegister_good() == 0) {
                Good_amount = Integer.valueOf(mNewsList.get(praisePos).getGood_amount());
                Good_amount = Good_amount + 1;
                mNewsList.get(praisePos).setGood_amount(String.valueOf(Good_amount));
            } else if (mNewsList.get(praisePos).getRegister_good() == 1) {
                Good_amount = Integer.valueOf(mNewsList.get(praisePos).getGood_amount());
                Good_amount = Good_amount - 1;
                mNewsList.get(praisePos).setGood_amount(String.valueOf(Good_amount));// 已点赞，则取消点赞
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        refreshPraiseCount(praisePos, Good_amount);
    }

    @Override
    public void Browse(int position) {
        int mBrowse = Integer.parseInt(mNewsList.get(position).getBrowse_amount());
        mBrowse = mBrowse + 1;
        mNewsList.get(position).setBrowse_amount(String.valueOf(mBrowse));
        mAdapter.notifyItemRangeChanged(position + 1, 1, "");//局部刷新
    }

    @Override
    public void search(HeadLinesData resultBean) {

//        mPageNum = Integer.parseInt(resultBean.getPageNum());
    }

    @Override
    public void PeixunSearch(BannerHomeLookBean resultBean) {
        mSearchSpringView.onFinishFreshAndLoad();
        search_no.setVisibility(View.GONE);
        if (mAdapter == null) {
            Log.d("Debug", "适配器为空");
        } else {
            Log.d("Debug", "适配器不为空");
        }
        mAdapter.addAllAt(mNewsList.size(), resultBean.getTrains());
        if (resultBean.getTrains().size() < 1) {
            requestFailed("");
            mSearchNotFound.setVisibility(View.VISIBLE);
            mSearchSpringView.setVisibility(View.INVISIBLE);
        } else {
            mSearchSpringView.onFinishFreshAndLoad();
            mSearchNotFound.setVisibility(View.INVISIBLE);
            mSearchSpringView.setVisibility(View.VISIBLE);
        }
        mNewsList = mAdapter.getAll();
    }

    @Override
    public void newsSucceed(NewsBean newsBean) {

    }

    @Override
    public void cultivateSucceed(CultivateBean cultivateBean) {

    }

    @Override
    public void lookBannerSucceed(LookBannerBean resultBean) {

    }

    @Override
    public void trainingArticleSucceed(TrainingArticleBean trainingArticleBean) {

    }

    @Override
    public void Collect(int position) {

    }


    @Override
    public void requestFailed(String message) {
        mSearchSpringView.onFinishFreshAndLoad();
        if (TextUtils.isEmpty(message)) return;
        if (message.contains(String.valueOf(Constant.CODE_100027))) {
            ToastUtil.showShort("token 失效，需重新登录");
            ActivityUtils.startActivity(this, NewLoginUserActivity.class);
            finish();
        }
    }


    //点击搜索前要做的事
    private void searchBefore() {
        mNewsList.clear();
        currPage = 1;
        mAdapter.notifyDataSetChanged();
        mSearchSpringView.setVisibility(View.VISIBLE);
        mSearchNotFound.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_search;
    }


}
