package shangri.example.com.shangri.ui.activity;

import android.support.v4.content.ContextCompat;
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
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.BannerHomeLookBean;
import shangri.example.com.shangri.model.bean.response.CollectBean;
import shangri.example.com.shangri.model.bean.response.InterlocutionBean;
import shangri.example.com.shangri.presenter.SearchPresenter;
import shangri.example.com.shangri.presenter.view.SearchListView;
import shangri.example.com.shangri.ui.adapter.InterLocutionAdapter;
import shangri.example.com.shangri.ui.listener.NewsListListener;
import shangri.example.com.shangri.ui.listener.PraiseListener;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.util.KeyBoardUtil;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * Created by Administrator on 2018/1/15.
 */

public class InterlocutionActivity extends BaseActivity<SearchListView, SearchPresenter> implements SearchListView {
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

    @BindView(R.id.tv_newest)
    TextView tv_newest;

    @BindView(R.id.tv_useful)
    TextView tv_useful;

    @BindView(R.id.layout_network_break)
    LinearLayout layout_network_break;
    @BindView(R.id.rl_net_info)
    FrameLayout rl_net_info;


    private boolean mIsCancel = true; //是否取消, 默认可以取消

    private List<InterlocutionBean.QalistBean> mNewsList = new ArrayList<>();
    private InterLocutionAdapter mAdapter;
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
        initSpringView();
        KeyBoardUtil.KeyBoard(InterlocutionActivity.this, "close");
//        mNewsList.clear();
        mSearchRecycler.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new InterLocutionAdapter(this, R.layout.item_interlocution, mNewsList);
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
                    KeyBoardUtil.hide_keyboard_from(InterlocutionActivity.this, mSearchText);
                    searchBefore();
                    if (!NetWorkUtil.isNetworkConnected(InterlocutionActivity.this)) {
                        rl_net_info.setVisibility(View.GONE);
                        layout_network_break.setVisibility(View.VISIBLE);
                        ToastUtil.showShort(getResources().getString(R.string.search_not_net));
                    } else {
                        rl_net_info.setVisibility(View.VISIBLE);
                        layout_network_break.setVisibility(View.GONE);
                        mPresenter.qaIndex(currPage, order, mSearchContent);
                    }
                    return true;
                }
                return false;
            }
        });
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
                if (mNewsList.get(position).getIsbad() == 0) {
                    mPresenter.addBad(String.valueOf(mNewsList.get(position).getId()), position);
                } else {
                    mPresenter.cancelBad(String.valueOf(mNewsList.get(position).getId()), position);
                }
            }
        });
        if (!NetWorkUtil.isNetworkConnected(InterlocutionActivity.this)) {
            rl_net_info.setVisibility(View.GONE);
            layout_network_break.setVisibility(View.VISIBLE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
        } else {
            rl_net_info.setVisibility(View.VISIBLE);
            layout_network_break.setVisibility(View.GONE);
            mPresenter.qaIndex(currPage, order, mSearchContent);
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

    private void initSpringView() {
        mSearchSpringView.setType(SpringView.Type.FOLLOW);
        mSearchSpringView.setHeader(new SpringViewHeader(this));
        mSearchSpringView.setFooter(new SpringViewFooter(this));
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


    @OnClick({R.id.tv_newest, R.id.tv_useful, R.id.search_cancel, R.id.search_delete, R.id.reload})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_newest:
                tv_newest.setTextColor(ContextCompat.getColor(this, R.color.text_color_little_orange));
                tv_useful.setTextColor(ContextCompat.getColor(this, R.color.white));
                order = "new";
                currPage = 1;
                if (!NetWorkUtil.isNetworkConnected(InterlocutionActivity.this)) {
                    rl_net_info.setVisibility(View.GONE);
                    layout_network_break.setVisibility(View.VISIBLE);
                    ToastUtil.showShort(getResources().getString(R.string.search_not_net));
                } else {
                    rl_net_info.setVisibility(View.VISIBLE);
                    layout_network_break.setVisibility(View.GONE);
                    mPresenter.qaIndex(currPage, order, mSearchContent);
                }
                break;
            case R.id.tv_useful:
                tv_useful.setTextColor(ContextCompat.getColor(this, R.color.text_color_little_orange));
                tv_newest.setTextColor(ContextCompat.getColor(this, R.color.white));
                order = "good";
                currPage = 1;
                if (!NetWorkUtil.isNetworkConnected(InterlocutionActivity.this)) {
                    rl_net_info.setVisibility(View.GONE);
                    layout_network_break.setVisibility(View.VISIBLE);
                    ToastUtil.showShort(getResources().getString(R.string.search_not_net));
                } else {
                    rl_net_info.setVisibility(View.VISIBLE);
                    layout_network_break.setVisibility(View.GONE);
                    mPresenter.qaIndex(currPage, order, mSearchContent);
                }
                break;
            case R.id.search_cancel:
                if (mIsCancel) { //取消
                    finish();
                } else {  //搜索
                    searchBefore();
                    mSearchContent = mSearchText.getText().toString().trim();
                    if (!NetWorkUtil.isNetworkConnected(InterlocutionActivity.this)) {
                        rl_net_info.setVisibility(View.GONE);
                        layout_network_break.setVisibility(View.VISIBLE);
                        ToastUtil.showShort(getResources().getString(R.string.search_not_net));
                    } else {
                        rl_net_info.setVisibility(View.VISIBLE);
                        layout_network_break.setVisibility(View.GONE);
                        mPresenter.qaIndex(currPage, order, mSearchContent);
                    }
                }

                break;
            case R.id.search_delete: //删除
                mSearchText.setText("");
                break;
            case R.id.reload:
                loadData();
                break;
        }
    }

    private void loadData() {
        if (!NetWorkUtil.isNetworkConnected(this)) {
            rl_net_info.setVisibility(View.GONE);
            layout_network_break.setVisibility(View.VISIBLE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
            mSearchSpringView.onFinishFreshAndLoad(); //停止加载
        }else {
            rl_net_info.setVisibility(View.VISIBLE);
            layout_network_break.setVisibility(View.GONE);
        }
//        mNewsList.clear();
        currPage = 1;
        mAdapter.notifyDataSetChanged();

        String content = mSearchText.getText().toString();
        if (!TextUtils.isEmpty(content)) {
            mPresenter.qaIndex(currPage, order, mSearchContent);
        } else {
//            ToastUtil.showShort("请输入内容后搜索");
            mSearchSpringView.onFinishFreshAndLoad(); //停止加载
        }
    }

    private void searchNewsList() {
        if (!NetWorkUtil.isNetworkConnected(this)) {
            rl_net_info.setVisibility(View.GONE);
            layout_network_break.setVisibility(View.VISIBLE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
            mSearchSpringView.onFinishFreshAndLoad(); //停止加载
        }else {
                rl_net_info.setVisibility(View.VISIBLE);
                layout_network_break.setVisibility(View.GONE);
        }
        if (currPage < mPageNum) {
            currPage++;
            mSearchSpringView.onFinishFreshAndLoad();
            mPresenter.qaIndex(currPage, order, mSearchContent);
        } else {
            ToastUtil.showShort("已加载全部");
            mSearchSpringView.onFinishFreshAndLoad(); //停止加载
        }
    }

    @Override
    public void search(BannerHomeLookBean resultBean) {

    }

    @Override
    public void interlocution(InterlocutionBean resultBean) {
        mPageNum = resultBean.getTotal_page();
        mSearchSpringView.onFinishFreshAndLoad();
        if (ACTION_TYPE == ACTION_FRESH) {
            mAdapter.setData(resultBean.getQalist());
        } else if (ACTION_TYPE == ACTION_LOAD_MORE) {
            mAdapter.addAllAt(mNewsList.size(), resultBean.getQalist());
        }
        mNewsList = mAdapter.getAll();
        if (mNewsList.size() == 0) {
            mSearchNotFound.setVisibility(View.VISIBLE);
        } else {
            mSearchSpringView.setVisibility(View.VISIBLE);
        }
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

    @Override
    public void getCollect(CollectBean resultBean) {

    }

    @Override
    public void addRequestCollect(int position) {
        Log.d("Debug", "点击收藏成功");
        int Collect_amount = Integer.valueOf(mNewsList.get(position).getCollect_amount());
        mNewsList.get(position).setRegister_collect(1);
        mNewsList.get(position).setCollect_amount(String.valueOf(Collect_amount + 1));
//        mAdapter.notifyItemRangeChanged(position, 1, "");
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void DeleteRequestCollect(int position) {
        int Collect_amount = Integer.valueOf(mNewsList.get(position).getCollect_amount());
        mNewsList.get(position).setRegister_collect(0);
        mNewsList.get(position).setCollect_amount(String.valueOf(Collect_amount - 1));
//        mAdapter.notifyItemRangeChanged(position, 1, "");
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
        return R.layout.activity_interlocution;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_interlocution;
    }

    @Override
    protected SearchPresenter createPresenter() {
        return new SearchPresenter(this, this);
    }

}
