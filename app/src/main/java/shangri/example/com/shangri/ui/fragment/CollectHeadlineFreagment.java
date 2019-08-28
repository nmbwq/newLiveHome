package shangri.example.com.shangri.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.Constant;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseEvent;
import shangri.example.com.shangri.base.BaseFragment;
import shangri.example.com.shangri.model.bean.event.PraiseEventBean;
import shangri.example.com.shangri.model.bean.event.RegisterCollect;
import shangri.example.com.shangri.model.bean.response.BannerHomeLookBean;
import shangri.example.com.shangri.model.bean.response.CollectBean;
import shangri.example.com.shangri.model.bean.response.InterlocutionBean;
import shangri.example.com.shangri.presenter.SearchPresenter;
import shangri.example.com.shangri.presenter.view.SearchListView;
import shangri.example.com.shangri.ui.activity.AudioPlayActivity;
import shangri.example.com.shangri.ui.adapter.NewHeadLinesAdapter;
import shangri.example.com.shangri.ui.listener.NewsListListener;
import shangri.example.com.shangri.ui.listener.PraiseListener;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.ui.webview.ActivityWebView;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.PointUtils;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * Created by Administrator on 2018/1/15.
 */

public class CollectHeadlineFreagment extends BaseFragment<SearchListView, SearchPresenter> implements SearchListView {
    @BindView(R.id.search_irv)
    RecyclerView mSearchRecycler;
    @BindView(R.id.search_springview)
    SpringView mSearchSpringView;
    @BindView(R.id.search_not_found)
    LinearLayout mSearchNotFound;
    @BindView(R.id.activity_empty_linshi1)
    LinearLayout activity_empty_linshi;
    @BindView(R.id.image_type_empty)
    ImageView image_type_empty;
    @BindView(R.id.tv_text1_empty)
    TextView tv_text1_empty;
    @BindView(R.id.tv_text2_empty)
    TextView tv_text2_empty;

    @BindView(R.id.layout_network_break)
    LinearLayout layout_network_break;
    @BindView(R.id.rl_net_info)
    RelativeLayout rl_net_info;
    @BindView(R.id.reload)
    Button reload;
    @BindView(R.id.im_image)
    ImageView imImage;
    @BindView(R.id.tv_text)
    TextView tvText;

    private boolean mIsCancel = true; //是否取消, 默认可以取消

    private List<CollectBean.CollectsBean> mNewsList = new ArrayList<>();
    private NewHeadLinesAdapter mAdapter;
    private int currPage = 1;
    private final int PAGE_SIZE = 5;
    private int mPageNum = 0; //总页数
    private String mSearchContent = ""; //搜索内容
    private String order = "new";
    private static final int ACTION_FRESH = 0; //刷新
    private static final int ACTION_LOAD_MORE = 1; //加载更多
    private static int ACTION_TYPE = 0;

    @Override
    protected void initViewsAndEvents() {
        image_type_empty.setImageDrawable(getResources().getDrawable(R.mipmap.shoucang_kong));
        tv_text1_empty.setText("没有收藏癖的你");
        tv_text2_empty.setText("上哪里去找回忆吆~");
        initSpringView();
        mNewsList.clear();
        mSearchRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new NewHeadLinesAdapter(getActivity(), R.layout.item_consultation, mNewsList);
        mAdapter.openLoadAnimation(new ScaleInAnimation());
        mSearchRecycler.setAdapter(mAdapter);
        //点击有用
        mAdapter.setPraiseListener(new PraiseListener() {
            @Override
            public void onPraise(int position) {
                if (mNewsList.get(position).getRegister_good() == 0) {
                    mPresenter.headPraise(String.valueOf(mNewsList.get(position).getId()), position);// 未点赞，则点赞
                } else if (mNewsList.get(position).getRegister_good() == 1) {
                    mPresenter.headCancel(String.valueOf(mNewsList.get(position).getId()), position); // 已点赞，则取消点赞
                }
            }

            @Override
            public void onColltion(int position) {
            }
        });

        //点击无用
        mAdapter.setNewsListListener(new NewsListListener() {
            @Override
            public void onItemClickListener(int position) {

                if (PointUtils.isFastClick()) {
                    if (mNewsList.get(position).getIsbad() == 0) {
                        mPresenter.addBad(String.valueOf(mNewsList.get(position).getId()), position);
                    } else {
                        mPresenter.cancelBad(String.valueOf(mNewsList.get(position).getId()), position);
                    }
                }
            }
        });



        mAdapter.setNewsListListener(new NewsListListener() {

            @Override
            public void onItemClickListener(int position) {

                if (PointUtils.isFastClick()) {
//                int position = viewHolder.getAdapterPosition();
                    CollectBean.CollectsBean collectsBean = mNewsList.get(position);
                    if (TextUtils.isEmpty(collectsBean.getAudio_url())) {
                        Intent intent = new Intent(getActivity(), ActivityWebView.class);
                        intent.putExtra("title", collectsBean.getTitle());
                        intent.putExtra("url", collectsBean.getArticle_url());
                        intent.putExtra("imageurl", collectsBean.getCover_url());
                        intent.putExtra("id", collectsBean.getId() + "");
                        //浏览量
                        intent.putExtra("browse_amount", collectsBean.getBrowse_amount());
                        //点击量
                        intent.putExtra("good_amount", collectsBean.getGood_amount());
                        //收藏量
                        intent.putExtra("collect_amount", collectsBean.getCollect_amount());
                        //0为未点赞 1为已点赞
                        intent.putExtra("register_good", collectsBean.getRegister_good());
                        //0为未收藏 1为已收藏
                        intent.putExtra("register_collect", collectsBean.getRegister_collect());
                        intent.putExtra("isFormType", "article");
                        intent.putExtra("type", "2");
                        mPresenter.headBrowse(collectsBean.getId(), position);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(getActivity(), AudioPlayActivity.class);
                        intent.putExtra("html", collectsBean.getArticle_url());     //页面点赞
                        intent.putExtra("imageurl", collectsBean.getCover_url());   //图片地址
                        intent.putExtra("audiourl", collectsBean.getAudio_url());   //音频地址
                        intent.putExtra("title", collectsBean.getTitle());          //标题
                        intent.putExtra("register", collectsBean.getRegister_good());
                        intent.putExtra("position", position);
                        intent.putExtra("pageid", "ConsultationFragment");
                        intent.putExtra("id", collectsBean.getId());
                        intent.putExtra("isFormType", "article");
                        //0为未收藏 1为已收藏
                        intent.putExtra("type", "2");
                        intent.putExtra("register_collect", collectsBean.getRegister_collect());
                        startActivity(intent);
                    }
                }
            }
        });
        loadData();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEventMain(BaseEvent baseEvent) {
        Log.d("Debug", "首页面数据" + baseEvent.getData().toString());
        if (baseEvent.getEventId() == Constant.TYPE_HEAD_PRAISE) {   //详情点赞后 列表局部刷新
            PraiseEventBean messageEvent = (PraiseEventBean) baseEvent.getData();
            if (messageEvent == null) return;
            if (messageEvent.getType() == Constant.ConsultationFragment) {
                int praisePos = messageEvent.getPraisePosition();
                CollectBean.CollectsBean pageData = mNewsList.get(praisePos);
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
                CollectBean.CollectsBean pageData = mNewsList.get(praisePos);
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


    private void initSpringView() {
        mSearchSpringView.setType(SpringView.Type.FOLLOW);
        mSearchSpringView.setHeader(new SpringViewHeader(getActivity()));
        mSearchSpringView.setFooter(new SpringViewFooter(getActivity()));
        mSearchSpringView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                ACTION_TYPE = ACTION_FRESH;
                loadData();
            }

            @Override
            public void onLoadmore() {
                ACTION_TYPE = ACTION_LOAD_MORE;
                searchNewsList();
            }
        });

    }


    private void loadData() {

        if (!NetWorkUtil.isNetworkConnected(getActivity())) {
            rl_net_info.setVisibility(View.GONE);
            layout_network_break.setVisibility(View.VISIBLE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
            mSearchSpringView.onFinishFreshAndLoad(); //停止加载
        } else {
            rl_net_info.setVisibility(View.VISIBLE);
            layout_network_break.setVisibility(View.GONE);
            mNewsList.clear();
            currPage = 1;
            mAdapter.notifyDataSetChanged();
            mPresenter.collectList(currPage + "", 7 + "");
        }


    }

    private void searchNewsList() {

        if (!NetWorkUtil.isNetworkConnected(getActivity())) {
            rl_net_info.setVisibility(View.GONE);
            layout_network_break.setVisibility(View.VISIBLE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
            mSearchSpringView.onFinishFreshAndLoad(); //停止加载
        } else {
            if (currPage < mPageNum) {
                currPage++;
                mSearchSpringView.onFinishFreshAndLoad();
                mPresenter.collectList(currPage + "", 7 + "");
            } else {
                ToastUtil.showShort("已加载全部");
                mSearchSpringView.onFinishFreshAndLoad(); //停止加载
            }
        }


    }

    @Override
    public void search(BannerHomeLookBean resultBean) {

    }

    @Override
    public void interlocution(InterlocutionBean resultBean) {

    }

    @Override
    public void good(int position) {
        int Good_amount = Integer.valueOf(mNewsList.get(position).getGood_amount());
        if (mNewsList.get(position).getIsgood() == 0) {
            mNewsList.get(position).setIsgood(1);
            mNewsList.get(position).setGood_amount(String.valueOf(Good_amount + 1));
        } else {
            mNewsList.get(position).setIsgood(0);
            mNewsList.get(position).setGood_amount(String.valueOf(Good_amount - 1));
        }
        mAdapter.notifyItemRangeChanged(position, 1, "");//局部刷新点赞
    }

    @Override
    public void bad(int position) {
//        int Good_amount = Integer.valueOf(mNewsList.get(position).getBad_amount());
//        if (mNewsList.get(position).getIsbad() == 0) {
//            mNewsList.get(position).setIsbad(1);
//            mNewsList.get(position).setBad_amount(String.valueOf(Good_amount + 1));
//        } else {
//            mNewsList.get(position).setIsbad(0);
//            mNewsList.get(position).setBad_amount(String.valueOf(Good_amount - 1));
//        }
//        mAdapter.notifyItemRangeChanged(position, 1, "");//局部刷新点赞
    }

    /**
     * 我的收藏接口
     *
     * @param resultBean
     */
    @Override
    public void getCollect(CollectBean resultBean) {
        Log.d("Debug", "请求成功");
        mPageNum = resultBean.getTotal_page();
        if (resultBean.getCollects() == null) {
            Log.d("Debug", "返回数据诶空");
            return;
        }
        mSearchSpringView.onFinishFreshAndLoad();
        if (ACTION_TYPE == ACTION_FRESH) {
            if (resultBean.getCollects().size() > 0) {
                mSearchSpringView.setVisibility(View.VISIBLE);
                activity_empty_linshi.setVisibility(View.GONE);
            } else {
                mSearchSpringView.setVisibility(View.GONE);
                activity_empty_linshi.setVisibility(View.VISIBLE);
            }
            mAdapter.setData(resultBean.getCollects());
        } else if (ACTION_TYPE == ACTION_LOAD_MORE) {
            mAdapter.addAllAt(mNewsList.size(), resultBean.getCollects());
        }
        mNewsList = mAdapter.getAll();

    }

    @Override
    public void addRequestCollect(int position) {

    }

    @Override
    public void DeleteRequestCollect(int position) {

    }

    @Override
    public void addPeixunLike(int position) {

    }

    @Override
    public void DeletePeixunLike(int position) {

    }

    @Override
    public void addPeixunColect(int position) {

    }

    @Override
    public void DeletePeixunColect(int position) {

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

    private void refreshPraiseCount(int pos, long count) { //点赞刷新
        CollectBean.CollectsBean pageData = mNewsList.get(pos);
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

    @Override
    public void Browse(int position) {
        int mBrowse = Integer.parseInt(mNewsList.get(position).getBrowse_amount());
        mBrowse = mBrowse + 1;
        mNewsList.get(position).setBrowse_amount(String.valueOf(mBrowse));
        mAdapter.notifyItemRangeChanged(position, 1, "");//局部刷新
    }

    @Override
    public void requestFailed(String message) {

    }


    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_collect_interlocution;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_collect_interlocution;
    }

    @Override
    protected SearchPresenter createPresenter() {
        return new SearchPresenter(getActivity(), this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick({R.id.reload, R.id.search_irv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.reload:
                loadData();
                break;
            case R.id.search_irv:
                break;
        }
    }
}
