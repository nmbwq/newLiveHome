package shangri.example.com.shangri.ui.activity;


import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;
import com.aspsine.irecyclerview.universaladapter.recyclerview.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.SearchBean;
import shangri.example.com.shangri.model.bean.response.companyDetailBean;
import shangri.example.com.shangri.model.bean.response.companyListBean;
import shangri.example.com.shangri.model.bean.response.partSelectBean;
import shangri.example.com.shangri.model.bean.response.personalSearchBean;
import shangri.example.com.shangri.presenter.CompanyPresenter;
import shangri.example.com.shangri.presenter.view.companyView;
import shangri.example.com.shangri.ui.adapter.TaskStateSerchAdapter;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.view.FastLinearScrollManger;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.util.KeyBoardUtil;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 执法人列表搜索
 * Created by admin on 2017/12/22.
 */

public class SerchTaskStatemanActivity extends BaseActivity<companyView, CompanyPresenter> implements companyView {
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
    @BindView(R.id.search_no)
    LinearLayout search_no;
    @BindView(R.id.layout_network_break)
    LinearLayout layout_network_break;
    @BindView(R.id.rl_net_info)
    RelativeLayout rl_net_info;





    private static final int ACTION_FRESH = 0; //刷新
    private static final int ACTION_LOAD_MORE = 1; //加载更多
    private static int ACTION_TYPE = 0;

    private ProgressDialogFragment mProgressDialog;
    private int currPage = 1;

    private TaskStateSerchAdapter mAdapter;
    private List<partSelectBean.PersonsBean> mlList = new ArrayList<>();

    private boolean mIsCancel = true; //是否取消, 默认可以取消
    private String mSearchContent = "21"; //搜索内容
    //    任务ID
    String task_id;


    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_serch_task_man_list;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_serch_task_man_list;
    }

    @Override
    protected CompanyPresenter createPresenter() {
        return new CompanyPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {

        task_id = getIntent().getStringExtra("task_id");
        initSpringView();
        mSearchText.setFocusable(true);
        KeyBoardUtil.KeyBoard(SerchTaskStatemanActivity.this, "open");
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
                    KeyBoardUtil.hide_keyboard_from(SerchTaskStatemanActivity.this, mSearchText);
                    searchBefore();
                    if (!NetWorkUtil.isNetworkConnected(SerchTaskStatemanActivity.this)) {
                        rl_net_info.setVisibility(View.GONE);
                        layout_network_break.setVisibility(View.VISIBLE);
                        ToastUtil.showShort(getResources().getString(R.string.search_not_net));
                    } else {
                        rl_net_info.setVisibility(View.VISIBLE);
                        layout_network_break.setVisibility(View.GONE);
                        mPresenter.partSelect(task_id, mSearchContent);
                    }
                    return true;
                }
                return false;
            }
        });

        rv_partol.setLayoutManager(new FastLinearScrollManger(SerchTaskStatemanActivity.this));
        mAdapter = new TaskStateSerchAdapter(this, R.layout.item_taskstate_people_item, mlList, "");
        mAdapter.openLoadAnimation(new ScaleInAnimation());
        rv_partol.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {

            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });

    }

    //点击搜索前要做的事
    private void searchBefore() {
        currPage = 1;
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
        rv_partol.setVisibility(View.VISIBLE);
        mSearchNotFound.setVisibility(View.INVISIBLE);
    }


    @Override
    public void requestFailed(String message) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialogFragment();
        }
    }


    public void loadData() {
        if (!NetWorkUtil.isNetworkConnected(SerchTaskStatemanActivity.this)) {
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
            mPresenter.partSelect(task_id, mSearchContent);
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
                ACTION_TYPE = ACTION_FRESH;
                loadData();
            }

            @Override
            public void onLoadmore() {
                if (!NetWorkUtil.isNetworkConnected(SerchTaskStatemanActivity.this)) {
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
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void companyDetail(companyDetailBean resultBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void adminApply() {

    }


    @Override
    public void partSelect(partSelectBean resultBean) {
        sv_partol.onFinishFreshAndLoad();
        search_no.setVisibility(View.GONE);
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        if (currPage == 1) {
            mlList.clear();
            if (resultBean.getPersons().size() > 0) {
                sv_partol.setVisibility(View.VISIBLE);
                mSearchNotFound.setVisibility(View.GONE);
            } else {
                sv_partol.setVisibility(View.GONE);
                mSearchNotFound.setVisibility(View.VISIBLE);
            }
        }
        mAdapter.addAll(resultBean.getPersons());
        mlList = mAdapter.getAll();

    }

    @Override
    public void personalSearch(personalSearchBean resultBean) {

    }

    @Override
    public void adminSelect(SearchBean resultBean) {

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
                    if (!NetWorkUtil.isNetworkConnected(SerchTaskStatemanActivity.this)) {
                        rl_net_info.setVisibility(View.GONE);
                        layout_network_break.setVisibility(View.VISIBLE);
                        ToastUtil.showShort(getResources().getString(R.string.search_not_net));
                    } else {
                        rl_net_info.setVisibility(View.VISIBLE);
                        layout_network_break.setVisibility(View.GONE);
                        if (mProgressDialog == null) {
                            mProgressDialog = new ProgressDialogFragment();
                        }
                        mProgressDialog.show(this.getSupportFragmentManager());
                        currPage = 1;
                        mPresenter.partSelect(task_id, mSearchContent);
                    }
                }
                break;
            case R.id.search_delete:
                mSearchText.setText("");
                break;
        }
    }
}
