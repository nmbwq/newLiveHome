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

import shangri.example.com.shangri.Constant;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.base.BaseEvent;
import shangri.example.com.shangri.model.bean.event.PraiseEventBean;
import shangri.example.com.shangri.model.bean.event.RegisterCollect;
import shangri.example.com.shangri.model.bean.response.CountBean;
import shangri.example.com.shangri.model.bean.response.HeadLinesData;
import shangri.example.com.shangri.presenter.HeadPresenter;
import shangri.example.com.shangri.presenter.view.HeadLinesView;
import shangri.example.com.shangri.ui.adapter.HeadLinesAdapter;
import shangri.example.com.shangri.ui.listener.NewsListListener;
import shangri.example.com.shangri.ui.listener.PraiseListener;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.ui.webview.ActivityWebView;
import shangri.example.com.shangri.util.ActivityUtils;
import shangri.example.com.shangri.util.KeyBoardUtil;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import shangri.example.com.shangri.util.PointUtils;

/**
 * 搜索
 * Created by chengaofu on 2017/6/26.
 */

public class ToutiaoSearchActivity extends BaseActivity<HeadLinesView, HeadPresenter> implements HeadLinesView {


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
    private List<HeadLinesData.ArticlesBean> mNewsList = new ArrayList<>();
    private HeadLinesAdapter mAdapter;
    private int currPage = 1;
    private final int PAGE_SIZE = 5;
    private String mSearchContent = ""; //搜索内容

    @Override
    protected HeadPresenter createPresenter() {
        return new HeadPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        initSpringView();
        mNewsList.clear();
        mSearchRecycler.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new HeadLinesAdapter(this, R.layout.item_consultation, mNewsList);
        mAdapter.openLoadAnimation(new ScaleInAnimation());
        mSearchRecycler.setAdapter(mAdapter);
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
                    KeyBoardUtil.hide_keyboard_from(ToutiaoSearchActivity.this, mSearchText);
                    searchBefore();
                    if (!NetWorkUtil.isNetworkConnected(ToutiaoSearchActivity.this)) {
                        rl_net_info.setVisibility(View.GONE);
                        layout_network_break.setVisibility(View.VISIBLE);
                        ToastUtil.showShort(getResources().getString(R.string.search_not_net));
                    } else {
                        rl_net_info.setVisibility(View.VISIBLE);
                        layout_network_break.setVisibility(View.GONE);
                        mPresenter.search(currPage, mSearchContent);
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
                    HeadLinesData.ArticlesBean bean = mNewsList.get(position);
                    if (TextUtils.isEmpty(bean.getAudio_url())) {
                        Intent intent = new Intent(ToutiaoSearchActivity.this, ActivityWebView.class);
                        intent.putExtra("title", bean.getTitle());
                        intent.putExtra("imageurl", bean.getCover_url());
                        intent.putExtra("url", bean.getArticle_url());
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
                        //当前列表的下标
                        intent.putExtra("position", position);
                        intent.putExtra("type", "2");
                        //分享需要的参数
                        intent.putExtra("isFormType", "article");
                        mPresenter.requestBrowse(bean.getId(), position);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(ToutiaoSearchActivity.this, AudioPlayActivity.class);
                        intent.putExtra("html", bean.getArticle_url());     //页面点赞
                        intent.putExtra("imageurl", bean.getCover_url());   //图片地址
                        intent.putExtra("audiourl", bean.getAudio_url());   //音频地址
                        intent.putExtra("title", bean.getTitle());          //标题
                        intent.putExtra("register", bean.getRegister_good());
                        intent.putExtra("position", position);
                        intent.putExtra("pageid", "ConsultationFragment");
                        intent.putExtra("type", "2");
                        intent.putExtra("id", bean.getId());
                        //分享需要的参数
                        intent.putExtra("isFormType", "article");
                        startActivity(intent);
                    }
                }
            }
        });
        mAdapter.setPraiseListener(new PraiseListener() {
            @Override
            public void onPraise(int position) {
                mPraisePos = position + 1;
                HeadLinesData.ArticlesBean pageData = mAdapter.get(mPraisePos);
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
        Log.d("Debug", "首页面数据" + baseEvent.getData().toString());
        if (baseEvent.getEventId() == Constant.TYPE_HEAD_PRAISE) {   //详情点赞后 列表局部刷新
            PraiseEventBean messageEvent = (PraiseEventBean) baseEvent.getData();
            if (messageEvent == null) return;
            if (messageEvent.getType() == Constant.ConsultationFragment) {
                int praisePos = messageEvent.getPraisePosition();
                HeadLinesData.ArticlesBean pageData = mNewsList.get(praisePos);
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
        } else if (baseEvent.getEventId() == Constant.TYPE_HEAD_COllECT) { //详情中收藏
            RegisterCollect messageEvent = (RegisterCollect) baseEvent.getData();
            if (messageEvent == null) return;
            if (messageEvent.getType() == Constant.ConsultationFragment) {
                int praisePos = messageEvent.getCollectPosition();
                HeadLinesData.ArticlesBean pageData = mNewsList.get(praisePos);
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
        HeadLinesData.ArticlesBean pageData = mNewsList.get(pos);
        if (pageData != null) {
            if (pageData.getRegister_collect() == 0) {
                pageData.setRegister_collect(1);
            } else if (pageData.getRegister_collect() == 1) {
                pageData.setRegister_collect(0);
            }
        }

    }

    private void refreshPraiseCount(int pos, long count) { //点赞刷新
        HeadLinesData.ArticlesBean pageData = mNewsList.get(pos);
        if (pageData != null) {
            if (pageData.getRegister_good() == 0) {
                pageData.setRegister_good(1);
            } else if (pageData.getRegister_good() == 1) {
                pageData.setRegister_good(0);
            }
            pageData.setGood_amount(String.valueOf(count));
            mAdapter.notifyDataSetChanged();
//            mAdapter.notifyItemRangeChanged(pos, 1, "");//局部刷新点赞
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
        if (!NetWorkUtil.isNetworkConnected(ToutiaoSearchActivity.this)) {
            rl_net_info.setVisibility(View.GONE);
            layout_network_break.setVisibility(View.VISIBLE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
            mSearchSpringView.onFinishFreshAndLoad(); //停止加载
        } else {
            rl_net_info.setVisibility(View.VISIBLE);
            layout_network_break.setVisibility(View.GONE);
        }
        mNewsList.clear();
        currPage = 1;
        mAdapter.notifyDataSetChanged();
        String content = mSearchText.getText().toString();
        if (!TextUtils.isEmpty(content)) {
            mPresenter.search(currPage, content);
        } else {
            ToastUtil.showShort("请输入内容后搜索");
            mSearchSpringView.onFinishFreshAndLoad(); //停止加载
        }
    }

    private void searchNewsList() {
        if (!NetWorkUtil.isNetworkConnected(ToutiaoSearchActivity.this)) {
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
            mPresenter.search(currPage, mSearchContent);
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
                    if (!NetWorkUtil.isNetworkConnected(ToutiaoSearchActivity.this)) {
                        rl_net_info.setVisibility(View.GONE);
                        layout_network_break.setVisibility(View.VISIBLE);
                        ToastUtil.showShort(getResources().getString(R.string.search_not_net));
                    } else {
                        rl_net_info.setVisibility(View.VISIBLE);
                        layout_network_break.setVisibility(View.GONE);
                    }
                    mPresenter.search(currPage, mSearchContent);
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
    public void bannerDetail(HeadLinesData resultBean) {

    }

    @Override
    public void praise(int praisePos) {
        int Good_amount = 0;
        try {
            if (praisePos == -1) {
                return;
            }
            if (mNewsList.get(praisePos + 1).getRegister_good() == 0) {
                Good_amount = Integer.valueOf(mNewsList.get(praisePos).getGood_amount());
                Good_amount = Good_amount + 1;
                mNewsList.get(praisePos + 1).setGood_amount(String.valueOf(Good_amount));
            } else if (mNewsList.get(praisePos + 1).getRegister_good() == 1) {
                Good_amount = Integer.valueOf(mNewsList.get(praisePos).getGood_amount());
                Good_amount = Good_amount - 1;
                mNewsList.get(praisePos + 1).setGood_amount(String.valueOf(Good_amount));// 已点赞，则取消点赞
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        refreshPraiseCount(praisePos + 1, Good_amount);
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
        search_no.setVisibility(View.GONE);
        mSearchSpringView.onFinishFreshAndLoad();
        if (currPage == 1) {
            mAdapter.setData(resultBean.getArticles());
            if (resultBean.getArticles().size() > 0) {
                mSearchSpringView.onFinishFreshAndLoad();
                mSearchNotFound.setVisibility(View.INVISIBLE);
                mSearchSpringView.setVisibility(View.VISIBLE);
            } else {
                requestFailed("");
                mSearchNotFound.setVisibility(View.VISIBLE);
                mSearchSpringView.setVisibility(View.INVISIBLE);
            }
        } else {
            mAdapter.addAll(resultBean.getArticles());
        }
        mNewsList = mAdapter.getAll();

//
//        mAdapter.(mNewsList.size(), resultBean.getArticles());
//        if (resultBean.getArticles().size() < 1) {
//            requestFailed("");
//            mSearchNotFound.setVisibility(View.VISIBLE);
//            mSearchSpringView.setVisibility(View.INVISIBLE);
//        } else {
//            mSearchSpringView.onFinishFreshAndLoad();
//            mSearchNotFound.setVisibility(View.INVISIBLE);
//            mSearchSpringView.setVisibility(View.VISIBLE);
//        }
//        mNewsList = mAdapter.getAll();
    }

    @Override
    public void Collect(int position) {

    }

    @Override
    public void mineCount(CountBean resultBean) {

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
