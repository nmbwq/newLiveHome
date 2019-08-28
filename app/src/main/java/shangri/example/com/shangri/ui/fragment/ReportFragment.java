package shangri.example.com.shangri.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.flyco.tablayout.SegmentTabLayout;

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

/**
 * 底导 5
 */
public class ReportFragment extends BaseFragment<ReportChildIncomeView, ReportChildIncomePresenter> implements ReportChildIncomeView {
    private Unbinder unbinder;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    @BindView(R.id.tl1)
    SegmentTabLayout mTl1;//会长角色   //解决圆角问题，用暂用两个tablayout
    @BindView(R.id.tl2)
    SegmentTabLayout mTl2;//主播角色
    @BindView(R.id.iv_icon)
    ImageView ivIcon;//tablayout中间的图标

    @BindView(R.id.ll_info)
    LinearLayout ll_info;
    private int role;


    private String[] mTitles1 = {"团队收益", "团队管理"};
    private String[] mTitles2 = {"团队收益"};//主播管理员的角色
    private String[] mTitles3 = {"个人收益"};//主播管理员的角色
    private int[] mShowType = {0, 1};
    private ReqReportBean reqReportBean;

    private List<GuildListBean.GuildsBean> mList = new ArrayList<>();

    @Override
    protected int getNormalLayoutId() {
        return R.layout.fragment_report;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.fragment_report;
    }

    @Override
    protected ReportChildIncomePresenter createPresenter() {
        return new ReportChildIncomePresenter(getActivity(), this);
    }


    @Override
    protected void initViewsAndEvents() {
        //获取昨天数据   主要是为了看有没有公会 看显示不显示模拟报表
        reqReportBean = new ReqReportBean();
        reqReportBean.show_type = "0";
        reqReportBean.type = "yesterday";
        reqReportBean.token = UserConfig.getInstance().getToken();
        mPresenter.loadData(reqReportBean);
        if (UserConfig.getInstance().getRole().length() > 0) {
            role = Integer.parseInt(UserConfig.getInstance().getRole());
        } else {
            role = 0;
        }
        switch (role) {//角色0无权限 1公会长 2主播 3管理员
            case 0://
                break;
            case 1://
                mFragments.add(ReportIncomeFragment.getInstance(mTitles1[0], mShowType[0]));
                mFragments.add(ReportManagerFragment.getInstance(mTitles1[1], mShowType[1]));
                mTl1.setTabData(mTitles1, getActivity(), R.id.fl_change, mFragments);
                mTl2.setVisibility(View.GONE);
                break;
            case 2://
                mTl1.setVisibility(View.GONE);
                ivIcon.setVisibility(View.GONE);
                mTl2.setVisibility(View.VISIBLE);
                mFragments.add(ReportIncomeFragment.getInstance(mTitles3[0], mShowType[0]));
                mTl2.setTabData(mTitles3, getActivity(), R.id.fl_change, mFragments);
                break;
            case 3://
                mTl1.setVisibility(View.GONE);
                ivIcon.setVisibility(View.GONE);
                mTl2.setVisibility(View.VISIBLE);
                mFragments.add(ReportIncomeFragment.getInstance(mTitles2[0], mShowType[0]));
                mTl2.setTabData(mTitles2, getActivity(), R.id.fl_change, mFragments);
                break;
        }

    }

    @Override
    public void requestFailed(String message) {

    }

    @Override
    public void loadData(GuildListBean listBean) {
        mList = listBean.getGuilds();
        Log.d("Debug", "返回的数据长度为" + mList.size());
        ll_info.setVisibility(View.VISIBLE);
    }

    @Override
    public void loadManagerData(RespAdminBean listBean) {

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

    @OnClick({R.id.ll_info})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_info:
                break;

        }
    }
}
