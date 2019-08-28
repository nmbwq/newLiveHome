package shangri.example.com.shangri.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseFragment;
import shangri.example.com.shangri.model.bean.request.ReqReportBean;
import shangri.example.com.shangri.model.bean.response.CountBean;
import shangri.example.com.shangri.model.bean.response.GuildListBean;
import shangri.example.com.shangri.model.bean.response.ResShareBean;
import shangri.example.com.shangri.model.bean.response.RespAdminBean;
import shangri.example.com.shangri.model.bean.response.SupportFromList;
import shangri.example.com.shangri.model.bean.response.companyMyBean;
import shangri.example.com.shangri.model.bean.response.timeBean;
import shangri.example.com.shangri.presenter.ReportChildIncomePresenter;
import shangri.example.com.shangri.presenter.view.ReportChildIncomeView;
import shangri.example.com.shangri.ui.adapter.TeamManegerListAdapter;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 5底导子 团队管理
 */
public class ReportChildManagerFragment extends BaseFragment<ReportChildIncomeView, ReportChildIncomePresenter> implements ReportChildIncomeView {

    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.activity_empty_linshi)
    LinearLayout activity_empty_linshi;

    @BindView(R.id.layout_network_break)
    LinearLayout layout_network_break;
    @BindView(R.id.rl_net_info)
    RelativeLayout rl_net_info;
    @BindView(R.id.ll_pop)
    LinearLayout llPop;

    private Unbinder unbinder;
    private String mType;
    private int mShowType;
    private TeamManegerListAdapter mAdapter;
    private static final String TAG = "ReportChildManagerFragm";
    private List<RespAdminBean.AdminsBean> mList;
    private ProgressDialogFragment mProgressDialog;

    private ReqReportBean reqReportBean;

    public static ReportChildManagerFragment getInstance(String type, int mShowType) {
        Log.i(TAG, "getInstance: 团队管理展示数据");
        ReportChildManagerFragment sf = new ReportChildManagerFragment();
        sf.mType = type;
        sf.mShowType = mShowType;
        Log.i(TAG, "sf.mShowType2 : " + sf.mShowType);
        return sf;
    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.fragment_report_child3;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.fragment_report_child3;
    }

    @Override
    protected ReportChildIncomePresenter createPresenter() {
        Log.i(TAG, "createPresenter: ");
        return new ReportChildIncomePresenter(getActivity(), this);
    }

    @Override
    protected void initViewsAndEvents() {
        Log.i(TAG, "initViewsAndEvents: ");
        mList = new ArrayList<>();
        mAdapter = new TeamManegerListAdapter(getActivity(), R.layout.item_report_manager, mList, mType);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setHasFixedSize(true);
        rv.setAdapter(mAdapter);
        reqReportBean = new ReqReportBean();
        reqReportBean.show_type = mShowType + "";
        reqReportBean.type = mType;
        reqReportBean.token = UserConfig.getInstance().getToken();

        if (!NetWorkUtil.isNetworkConnected(getActivity())) {
            rl_net_info.setVisibility(View.GONE);
            layout_network_break.setVisibility(View.VISIBLE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
        } else {
            rl_net_info.setVisibility(View.VISIBLE);
            layout_network_break.setVisibility(View.GONE);
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialogFragment();
            }
            mProgressDialog.show(getChildFragmentManager());
            mPresenter.loadManagerData(reqReportBean);
        }


    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
    }

    @Override
    public void loadData(GuildListBean listBean) {

    }

    @Override
    public void loadManagerData(RespAdminBean listBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        if (mList.size() > 0) {
            mList.clear();
        }
        mList = listBean.getAdmins();
        if (mList.size() > 0) {
            rv.setVisibility(View.VISIBLE);
            activity_empty_linshi.setVisibility(View.GONE);
        } else {
            rv.setVisibility(View.GONE);
            activity_empty_linshi.setVisibility(View.VISIBLE);
        }
        Log.i(TAG, "loadData: mlist.size:" + mList.size());
        mAdapter.setData(mList);
    }

    @Override
    public void onShare(ResShareBean shareBean) {

    }

    @Override
    public void companyMy(companyMyBean shareBean) {

    }

    @Override
    public void mineCount(CountBean resultBean) {

    }

    @Override
    public void memberLate(timeBean resultBean) {

    }

    @Override
    public void SupportFromList(SupportFromList resultBean) {

    }

    @Override
    public void requestFailed(String message) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.reload, R.id.iv_share_pic})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.reload:
                if (!NetWorkUtil.isNetworkConnected(getActivity())) {
                    rl_net_info.setVisibility(View.GONE);
                    layout_network_break.setVisibility(View.VISIBLE);
                    ToastUtil.showShort(getResources().getString(R.string.search_not_net));
                } else {
                    rl_net_info.setVisibility(View.VISIBLE);
                    layout_network_break.setVisibility(View.GONE);
                    if (mProgressDialog == null) {
                        mProgressDialog = new ProgressDialogFragment();
                    }
                    mProgressDialog.show(getChildFragmentManager());
                    mPresenter.loadManagerData(reqReportBean);
                }
                break;
            case R.id.iv_share_pic:
                break;
        }
    }
}
