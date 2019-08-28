package shangri.example.com.shangri.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;
import com.aspsine.irecyclerview.universaladapter.recyclerview.OnItemClickListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.event.BrowseEventBean;
import shangri.example.com.shangri.model.bean.response.SearchBean;
import shangri.example.com.shangri.model.bean.response.anchorDetailBean;
import shangri.example.com.shangri.model.bean.response.companyDetailBean;
import shangri.example.com.shangri.model.bean.response.companyListBean;
import shangri.example.com.shangri.model.bean.response.partSelectBean;
import shangri.example.com.shangri.model.bean.response.personalSearchBean;
import shangri.example.com.shangri.model.bean.response.upListBean;
import shangri.example.com.shangri.presenter.CompanyPresenter;
import shangri.example.com.shangri.presenter.view.companyView;
import shangri.example.com.shangri.ui.adapter.CompanyListAdapter1;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.view.FastLinearScrollManger;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.util.ActivityManager;
import shangri.example.com.shangri.util.AndroidInterface.ApllayFace;
import shangri.example.com.shangri.util.AndroidInterface.CompanyInterfaceUtils;
import shangri.example.com.shangri.util.KeyBoardUtil;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 公司列表界面
 * Created by admin on 2017/12/22.
 */

public class CompanyListActivity extends BaseActivity<companyView, CompanyPresenter> implements companyView, ApllayFace {
    @BindView(R.id.rv_partol)
    RecyclerView rv_partol;
    @BindView(R.id.sv_partol)
    SpringView sv_partol;
    @BindView(R.id.search_content)
    EditText mSearchText;
    @BindView(R.id.search_delete)
    ImageView mDeleteImage;
    @BindView(R.id.search_cancel)
    TextView mCancelText;
    @BindView(R.id.search_not_found)
    LinearLayout mSearchNotFound;


    @BindView(R.id.layout_network_break)
    LinearLayout layout_network_break;
    @BindView(R.id.rl_net_info)
    RelativeLayout rl_net_info;

    @BindView(R.id.ll_tuijian)
    LinearLayout ll_tuijian;


    private static final int ACTION_FRESH = 0; //刷新
    private static final int ACTION_LOAD_MORE = 1; //加载更多
    private static int ACTION_TYPE = 0;

    private ProgressDialogFragment mProgressDialog;
    private int currPage = 1;

    private CompanyListAdapter1 mAdapter;
    private List<SearchBean.AdminBean> mPatrolList = new ArrayList<>();

    private boolean mIsCancel = true; //是否取消, 默认可以取消
    private String mSearchContent = ""; //搜索内容


    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_company_list;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_company_list;
    }

    @Override
    protected CompanyPresenter createPresenter() {
        return new CompanyPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {

        CompanyInterfaceUtils.setApllayBack(this);
        if (UserConfig.getInstance().getRole().equals("2")) {
            ll_tuijian.setVisibility(View.VISIBLE);
        } else if (UserConfig.getInstance().getRole().equals("3")) {
            ll_tuijian.setVisibility(View.GONE);
        }
        initSpringView();
        rv_partol.setLayoutManager(new FastLinearScrollManger(CompanyListActivity.this));
        mAdapter = new CompanyListAdapter1(this, R.layout.item_my_company, mPatrolList);
        mAdapter.openLoadAnimation(new ScaleInAnimation());
        rv_partol.setAdapter(mAdapter);

        if (UserConfig.getInstance().getRole().equals("2")) {
            mAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                    Intent intent = new Intent();
                    intent.putExtra("Guildename", mPatrolList.get(position).getGuild_name());
                    intent.putExtra("id", mPatrolList.get(position).getGuild_id());
                    setResult(2, intent);
                    finish();
                    Toast.makeText(CompanyListActivity.this, mPatrolList.get(position).getGuild_name(), Toast.LENGTH_SHORT).show();

                }

                @Override
                public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                    return false;
                }
            });
        }
        mSearchText.setFocusable(true);
        KeyBoardUtil.openKeybord(mSearchText, CompanyListActivity.this);

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
                    KeyBoardUtil.hide_keyboard_from(CompanyListActivity.this, mSearchText);
                    searchBefore();
                    loadData();
                    return true;
                }
                return false;
            }
        });
        loadData();

    }

    //点击搜索前要做的事
    private void searchBefore() {
        currPage = 1;
//        mAdapter.notifyDataSetChanged();
        rv_partol.setVisibility(View.VISIBLE);
        mSearchNotFound.setVisibility(View.INVISIBLE);
    }


    @Override
    public void requestFailed(String message) {

    }


    public void loadData() {
        if (!NetWorkUtil.isNetworkConnected(CompanyListActivity.this)) {
            rl_net_info.setVisibility(View.GONE);
            layout_network_break.setVisibility(View.VISIBLE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
        } else {
            rl_net_info.setVisibility(View.VISIBLE);
            layout_network_break.setVisibility(View.GONE);
            mPresenter.adminSelect(mSearchContent);
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
                if (!NetWorkUtil.isNetworkConnected(CompanyListActivity.this)) {
                    rl_net_info.setVisibility(View.GONE);
                    layout_network_break.setVisibility(View.VISIBLE);
                    ToastUtil.showShort(getResources().getString(R.string.search_not_net));
                    sv_partol.onFinishFreshAndLoad(); //停止加载
                } else {
                    rl_net_info.setVisibility(View.VISIBLE);
                    layout_network_break.setVisibility(View.GONE);
                    currPage = 1;
                    mPresenter.adminSelect(mSearchContent);
                }

            }

            @Override
            public void onLoadmore() {
                if (!NetWorkUtil.isNetworkConnected(CompanyListActivity.this)) {
                    sv_partol.onFinishFreshAndLoad(); //停止加载
                } else {
                    ACTION_TYPE = ACTION_LOAD_MORE;
//                    requestTastList();
                }

            }
        });

    }

    private void requestTastList() {
        int mPageNum = 0;
        if (currPage < mPageNum) {
            currPage++;
            sv_partol.onFinishFreshAndLoad();
        } else {
            sv_partol.onFinishFreshAndLoad(); //停止加载
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @Override
    public void companySearch(companyListBean resultBean) {
        sv_partol.onFinishFreshAndLoad();
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }


    @Override
    public void companyDetail(companyDetailBean resultBean) {

    }

    /**
     * 设置成功成功
     */
    @Override
    public void adminApply() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        EventBus.getDefault().post(new BrowseEventBean());
        showPopupWindowSevenDays();
    }

    @Override
    public void partSelect(partSelectBean resultBean) {

    }

    @Override
    public void personalSearch(personalSearchBean resultBean) {

    }

    @Override
    public void adminSelect(SearchBean resultBean) {
        sv_partol.onFinishFreshAndLoad();
        if (UserConfig.getInstance().getRole().equals("2")) {
            if (mSearchContent.length() > 0) {
                ll_tuijian.setVisibility(View.GONE);
            } else {
                ll_tuijian.setVisibility(View.VISIBLE);
            }
        }

        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        Log.d("Debug", "返回的数据长度为" + resultBean.getAdmin().size());
        if (currPage == 1) {
            mPatrolList.clear();
            if (resultBean.getAdmin().size() > 0) {
                rv_partol.setVisibility(View.VISIBLE);
                mSearchNotFound.setVisibility(View.GONE);
            } else {
                rv_partol.setVisibility(View.GONE);
                mSearchNotFound.setVisibility(View.VISIBLE);
            }
        }
        mAdapter.addAll(resultBean.getAdmin());
        mPatrolList = mAdapter.getAll();
    }

    @OnClick({R.id.search_delete, R.id.search_cancel, R.id.reload})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.reload:
                loadData();
                break;
            case R.id.search_cancel:
                if (mIsCancel) { //取消
                    finish();
                } else {  //搜索
                    searchBefore();
                    mSearchContent = mSearchText.getText().toString();
                    loadData();
                }
                break;
            case R.id.search_delete:
                mSearchText.setText("");
                break;
        }
    }

    //管理员身份   加入公会操作

    @Override
    public void adminApllayFace(String registrants_id, String guild_id) {
        Log.d("Debug", "管理员身份接收到eventBus");
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialogFragment();
        }
        mProgressDialog.show(this.getSupportFragmentManager());
        mPresenter.adminApply(registrants_id, guild_id);
    }

    @Override
    public void sendInfo(anchorDetailBean.UserInfoBean bean) {

    }

    @Override
    public void sendInfo1(upListBean.ListBean bean) {

    }


    private PopupWindow mPopWindowSelectdays;

    /**
     *
     */
    private void showPopupWindowSevenDays() {
        //设置contentView
        View contentView = LayoutInflater.from(this).inflate(R.layout.new_add_gonghui1, null);
        mPopWindowSelectdays = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindowSelectdays.setContentView(contentView);
//        //设置各个控件的点击响应
        TextView tv_content = contentView.findViewById(R.id.tv_content);
        TextView tv_next = contentView.findViewById(R.id.tv_next);

        tv_content.setText("主上，您已成功提交审核，请及时联系公会长进行审核。");
        tv_next.setText("查看进度");
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityManager.getInstance().finishActivity(BindingManagerTypectivity.class);
                finish();
                mPopWindowSelectdays.dismiss();
                startActivity(new Intent(CompanyListActivity.this, MyGuildActivity.class));
            }
        });
        View rootview = LayoutInflater.from(this).inflate(R.layout.activity_company_list, null);
        mPopWindowSelectdays.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


}
