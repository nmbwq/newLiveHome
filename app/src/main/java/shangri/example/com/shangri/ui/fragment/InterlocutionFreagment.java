package shangri.example.com.shangri.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseFragment;
import shangri.example.com.shangri.model.bean.response.BannerHomeLookBean;
import shangri.example.com.shangri.model.bean.response.CollectBean;
import shangri.example.com.shangri.model.bean.response.InterlocutionBean;
import shangri.example.com.shangri.presenter.SearchPresenter;
import shangri.example.com.shangri.presenter.view.SearchListView;
import shangri.example.com.shangri.ui.adapter.NewInterLocutionAdapter;
import shangri.example.com.shangri.ui.listener.NewsListListener;
import shangri.example.com.shangri.ui.listener.PraiseListener;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.ToastUtil;
import shangri.example.com.shangri.util.PointUtils;

/**
 * Created by Administrator on 2018/1/15.
 */

public class InterlocutionFreagment extends BaseFragment<SearchListView, SearchPresenter> implements SearchListView {
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
    @BindView(R.id.reload)
    Button reload;
    @BindView(R.id.im_image)
    ImageView imImage;
    @BindView(R.id.tv_text)
    TextView tvText;
    private boolean mIsCancel = true; //是否取消, 默认可以取消

    @BindView(R.id.layout_network_break)
    LinearLayout layout_network_break;
    @BindView(R.id.rl_net_info)
    RelativeLayout rl_net_info;

    private List<CollectBean.CollectsBean> mNewsList = new ArrayList<>();
    private NewInterLocutionAdapter mAdapter;
    private int currPage = 1;
    private final int PAGE_SIZE = 5;
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
        mAdapter = new NewInterLocutionAdapter(getActivity(), R.layout.item_interlocution, mNewsList);
        mAdapter.openLoadAnimation(new ScaleInAnimation());
        mSearchRecycler.setAdapter(mAdapter);
        //点击有用
        mAdapter.setPraiseListener(new PraiseListener() {
            @Override
            public void onPraise(int position) {
                if (mNewsList.get(position).getIsgood() == 0) {
                    mPresenter.addGood(String.valueOf(mNewsList.get(position).getId()), position);
                } else {
                    mPresenter.cancelGood(String.valueOf(mNewsList.get(position).getId()), position);
                }
            }

            @Override
            public void onColltion(int position) {
                if (mNewsList.get(position).getRegister_collect() == 0) {
                    mPresenter.addCollect(String.valueOf(mNewsList.get(position).getId()), position);
                } else {
                    mPresenter.deleteCollect(String.valueOf(mNewsList.get(position).getId()), position);
                }
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
        loadData();
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
            mPresenter.collectList(currPage + "", 8 + "");
        }


    }

    private void searchNewsList() {
        if (!NetWorkUtil.isNetworkConnected(getActivity())) {
            rl_net_info.setVisibility(View.GONE);
            layout_network_break.setVisibility(View.VISIBLE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
            mSearchSpringView.onFinishFreshAndLoad(); //停止加载
        } else {
            rl_net_info.setVisibility(View.VISIBLE);
            layout_network_break.setVisibility(View.GONE);
            int mPageNum = 0;
            if (currPage < mPageNum) {
                currPage++;
                mSearchSpringView.onFinishFreshAndLoad();
                mPresenter.collectList(currPage + "", 8 + "");
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
        int Good_amount = Integer.valueOf(mNewsList.get(position).getBad_amount());
        if (mNewsList.get(position).getIsbad() == 0) {
            mNewsList.get(position).setIsbad(1);
            mNewsList.get(position).setBad_amount(String.valueOf(Good_amount + 1));
        } else {
            mNewsList.get(position).setIsbad(0);
            mNewsList.get(position).setBad_amount(String.valueOf(Good_amount - 1));
        }
        mAdapter.notifyItemRangeChanged(position, 1, "");//局部刷新点赞
    }

    /**
     * 我的收藏接口
     *
     * @param resultBean
     */
    @Override
    public void getCollect(CollectBean resultBean) {
        Log.d("Debug", "请求成功");
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
        Log.d("Debug", "点击收藏成功");
        int Collect_amount = Integer.valueOf(mNewsList.get(position).getCollect_amount());
        mNewsList.get(position).setRegister_collect(1);
        mNewsList.get(position).setCollect_amount(String.valueOf(Collect_amount + 1));
//        mAdapter.notifyItemRangeChanged(position, 1, "");
        mPresenter.collectList(currPage + "", 8 + "");
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void DeleteRequestCollect(int position) {
        int Collect_amount = Integer.valueOf(mNewsList.get(position).getCollect_amount());
        mNewsList.get(position).setRegister_collect(0);
        mNewsList.get(position).setCollect_amount(String.valueOf(Collect_amount - 1));
//        mAdapter.notifyItemRangeChanged(position, 1, "");
        mPresenter.collectList(currPage + "", 8 + "");
        mAdapter.notifyDataSetChanged();
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
    public void praise(int position) {

    }

    @Override
    public void Browse(int position) {

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
