package shangri.example.com.shangri.ui.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import shangri.example.com.shangri.Constant;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.EncyclopediaList;
import shangri.example.com.shangri.model.bean.response.MessageResonse;
import shangri.example.com.shangri.presenter.EncyclopediaListPresenter;
import shangri.example.com.shangri.presenter.EncyclopedialistView;
import shangri.example.com.shangri.ui.adapter.anchorListAdapter;
import shangri.example.com.shangri.ui.adapter.guildListAdapter;
import shangri.example.com.shangri.ui.adapter.platfromListAdapter;
import shangri.example.com.shangri.ui.view.FastLinearScrollManger;
import shangri.example.com.shangri.ui.view.GridSpacingItemDecoration;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.util.ActivityUtils;
import shangri.example.com.shangri.util.KeyBoardUtil;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 搜索
 * Created by chengaofu on 2017/6/26.
 */

public class WikiSearchActivity extends BaseActivity<EncyclopedialistView, EncyclopediaListPresenter> implements EncyclopedialistView {


    @BindView(R.id.search_irv)
    RecyclerView rv_partol;
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
    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;

    @BindView(R.id.layout_network_break)
    LinearLayout layout_network_break;
    @BindView(R.id.rl_net_info)
    RelativeLayout rl_net_info;

    private boolean mIsCancel = true; //是否取消, 默认可以取消

    private int currPage = 1;
    private final int PAGE_SIZE = 5;
    private String mSearchContent = ""; //搜索内容
    //1是找平台 2找公会 3找主播
    public int type = -1;

    private static final int ACTION_FRESH = 0; //刷新
    private static final int ACTION_LOAD_MORE = 1; //加载更多
    private static int ACTION_TYPE = 0;


    private anchorListAdapter anchormAdapter;
    private guildListAdapter guildmAdapter;
    private platfromListAdapter platfrommAdapter;

    private List<EncyclopediaList.AnchorBean> AnchorBeanList = new ArrayList<>();
    private List<EncyclopediaList.GuildBean> GuildlList = new ArrayList<>();
    private List<EncyclopediaList.PlatfromBean> PlatfromList = new ArrayList<>();

    @Override
    protected EncyclopediaListPresenter createPresenter() {
        return new EncyclopediaListPresenter(WikiSearchActivity.this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        type = getIntent().getIntExtra("type", -1);
        switch (type) {
            case 1:
                rv_partol.setLayoutManager(new FastLinearScrollManger(WikiSearchActivity.this));
                platfrommAdapter = new platfromListAdapter(this, R.layout.platfrom_item_layout, PlatfromList);
                platfrommAdapter.openLoadAnimation(new ScaleInAnimation());
                rv_partol.setAdapter(platfrommAdapter);
                break;
            case 2:
                rv_partol.setLayoutManager(new FastLinearScrollManger(WikiSearchActivity.this));
                guildmAdapter = new guildListAdapter(this, R.layout.society_item_layout, GuildlList);
                guildmAdapter.openLoadAnimation(new ScaleInAnimation());
                rv_partol.setAdapter(guildmAdapter);
                break;
            case 3:
                rv_partol.setLayoutManager(new GridLayoutManager(WikiSearchActivity.this, 2));
                anchormAdapter = new anchorListAdapter(this, R.layout.zhubo_item_layout, AnchorBeanList);
                rv_partol.addItemDecoration(new GridSpacingItemDecoration(10));
                anchormAdapter.openLoadAnimation(new ScaleInAnimation());
                rv_partol.setAdapter(anchormAdapter);
                break;
            default:
                break;
        }
        initSpringView();

        mSearchText.setFocusable(true);
        KeyBoardUtil.KeyBoard(WikiSearchActivity.this, "open");

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
                    KeyBoardUtil.hide_keyboard_from(WikiSearchActivity.this, mSearchText);
                    searchBefore();
                    if (!NetWorkUtil.isNetworkConnected(WikiSearchActivity.this)) {
                        rl_net_info.setVisibility(View.GONE);
                        layout_network_break.setVisibility(View.VISIBLE);
                        ToastUtil.showShort(getResources().getString(R.string.search_not_net));
                    } else {
                        rl_net_info.setVisibility(View.VISIBLE);
                        layout_network_break.setVisibility(View.GONE);
                        mPresenter.GetEncyclopediaList(type, true, mSearchContent);
                    }
                    return true;
                }
                return false;
            }
        });

    }


    private void initSpringView() {
        mSearchSpringView.setType(SpringView.Type.FOLLOW);
        mSearchSpringView.setHeader(new SpringViewHeader(this));
//        sv_partol.setFooter(new SpringViewFooter(this));
        mSearchSpringView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                if (!NetWorkUtil.isNetworkConnected(WikiSearchActivity.this)) {
                    mSearchSpringView.onFinishFreshAndLoad(); //停止加载
                } else {
                    ACTION_TYPE = ACTION_FRESH;
                    loadData();

                }
            }

            @Override
            public void onLoadmore() {
                if (!NetWorkUtil.isNetworkConnected(WikiSearchActivity.this)) {
                    mSearchSpringView.onFinishFreshAndLoad(); //停止加载
                } else {
                    ACTION_TYPE = ACTION_LOAD_MORE;
                    searchNewsList();
                }

            }
        });
    }


    private void loadData() {
        if (!NetWorkUtil.isNetworkConnected(this)) {
            rl_net_info.setVisibility(View.GONE);
            layout_network_break.setVisibility(View.VISIBLE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
            mSearchSpringView.onFinishFreshAndLoad(); //停止加载
        } else {
            rl_net_info.setVisibility(View.VISIBLE);
            layout_network_break.setVisibility(View.GONE);
        }
        currPage = 1;
        String content = mSearchText.getText().toString();
        if (!TextUtils.isEmpty(content)) {
            if (!NetWorkUtil.isNetworkConnected(this)) {
                rl_net_info.setVisibility(View.GONE);
                layout_network_break.setVisibility(View.VISIBLE);
                ToastUtil.showShort(getResources().getString(R.string.search_not_net));
            } else {
                rl_net_info.setVisibility(View.VISIBLE);
                layout_network_break.setVisibility(View.GONE);
                mPresenter.GetEncyclopediaList(type, true, mSearchContent);
            }
        } else {
            ToastUtil.showShort("请输入内容后搜索");
            mSearchSpringView.onFinishFreshAndLoad(); //停止加载

        }
    }

    private void searchNewsList() {
        if (!NetWorkUtil.isNetworkConnected(this)) {
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
            mPresenter.GetEncyclopediaList(type, true, mSearchContent);
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
                    search_no.setVisibility(View.GONE);
                    frameLayout.setVisibility(View.VISIBLE);
                    searchBefore();
                    mSearchContent = mSearchText.getText().toString();
                    if (!NetWorkUtil.isNetworkConnected(this)) {
                        rl_net_info.setVisibility(View.GONE);
                        layout_network_break.setVisibility(View.VISIBLE);
                        ToastUtil.showShort(getResources().getString(R.string.search_not_net));
                    } else {
                        rl_net_info.setVisibility(View.VISIBLE);
                        layout_network_break.setVisibility(View.GONE);
                        mPresenter.GetEncyclopediaList(type, true, mSearchContent);
                    }
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
        currPage = 1;
//        mAdapter.notifyDataSetChanged();
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
        return R.layout.activity_search_wiki;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_search_wiki;
    }


    @Override
    public void encyclopediaPlatfromList(EncyclopediaList resultBean) {
        AnchorBeanList.clear();
        GuildlList.clear();
        PlatfromList.clear();
        AnchorBeanList = resultBean.getAnchor();
        GuildlList = resultBean.getGuild();
        PlatfromList = resultBean.getPlatfrom();
        switch (type) {
            case 1:
                if (PlatfromList.size() < 1) {
                    requestFailed("");
                    mSearchNotFound.setVisibility(View.VISIBLE);
                    mSearchSpringView.setVisibility(View.INVISIBLE);
                } else {
                    mSearchSpringView.onFinishFreshAndLoad();
                    mSearchNotFound.setVisibility(View.INVISIBLE);
                    mSearchSpringView.setVisibility(View.VISIBLE);
                }
                platfrommAdapter.setData(PlatfromList);
                break;
            case 2:

                if (GuildlList.size() < 1) {
                    requestFailed("");
                    mSearchNotFound.setVisibility(View.VISIBLE);
                    mSearchSpringView.setVisibility(View.INVISIBLE);
                } else {
                    mSearchSpringView.onFinishFreshAndLoad();
                    mSearchNotFound.setVisibility(View.INVISIBLE);
                    mSearchSpringView.setVisibility(View.VISIBLE);
                }
                guildmAdapter.setData(GuildlList);

                break;
            case 3:
                if (AnchorBeanList.size() < 1) {
                    requestFailed("");
                    mSearchNotFound.setVisibility(View.VISIBLE);
                    mSearchSpringView.setVisibility(View.INVISIBLE);
                } else {
                    mSearchSpringView.onFinishFreshAndLoad();
                    mSearchNotFound.setVisibility(View.INVISIBLE);
                    mSearchSpringView.setVisibility(View.VISIBLE);
                }
                anchormAdapter.setData(AnchorBeanList);
                break;
        }
    }

    @Override
    public void messageList(MessageResonse resultBean) {

    }

    @Override
    public void wikiFocus(EncyclopediaList resultBean) {

    }

    @Override
    public void addRuzhu() {

    }

    @Override
    public void wikiDoCollect() {

    }

    @Override
    public void wikiCancelCollect() {

    }

    @Override
    public void messageRead() {

    }

    @Override
    public void consultShare() {

    }
}
