package shangri.example.com.shangri.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseFragment;
import shangri.example.com.shangri.model.bean.response.sendResumeBean;
import shangri.example.com.shangri.model.bean.response.upListBean;
import shangri.example.com.shangri.presenter.ResumeManagePresent;
import shangri.example.com.shangri.presenter.view.ResumeManageView;
import shangri.example.com.shangri.model.bean.response.WelfareGuildBean;
import shangri.example.com.shangri.ui.adapter.SendResumeAdapter;
import shangri.example.com.shangri.ui.view.FastLinearScrollManger;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * Description:简历管理主播投递
 * Data：2018/11/6-10:54
 * Author: lin
 */
public class ResumeManage1Fragment extends BaseFragment<ResumeManageView, ResumeManagePresent> implements ResumeManageView {
    @BindView(R.id.rv_partol)
    RecyclerView rv_partol;

    @BindView(R.id.sv_partol)
    SpringView sv_partol;


    @BindView(R.id.layout_network_break)
    LinearLayout layout_network_break;

    @BindView(R.id.rl_net_info)
    RelativeLayout rl_net_info;

    @BindView(R.id.activity_empty_linshi)
    LinearLayout activity_empty_linshi;
    @BindView(R.id.image_type_empty)
    ImageView image_type_empty;
    @BindView(R.id.tv_text1_empty)
    TextView tv_text1_empty;
    @BindView(R.id.tv_text2_empty)
    TextView tv_text2_empty;


    //1 正常 2审核中 3审核失败 4已关闭  0全部
    int type = 0;
    int mPageNum = 0;

    private static final int ACTION_FRESH = 0; //刷新

    private static final int ACTION_LOAD_MORE = 1; //加载更多
    private static int ACTION_TYPE = 0;
    //    private ProgressDialogFragment mProgressDialog;
    private SendResumeAdapter mAdapter;

    private List<sendResumeBean.ResumeBean> mList = new ArrayList<>();

    @Override
    protected ResumeManagePresent createPresenter() {
        return new ResumeManagePresent(getActivity(), this);
    }

    @Override
    protected void initViewsAndEvents() {
        image_type_empty.setImageDrawable(getResources().getDrawable(R.mipmap.sousuo_kong));
        tv_text1_empty.setText("对不起,这里空空如也呀~");
        tv_text2_empty.setText("");
        initSpringView();
        reload();
        rv_partol.setLayoutManager(new FastLinearScrollManger(getActivity()));
        mAdapter = new SendResumeAdapter(getActivity(), R.layout.item_toudi_item, mList);
        mAdapter.openLoadAnimation(new ScaleInAnimation());
        rv_partol.setAdapter(mAdapter);
    }

    private void initSpringView() {
        sv_partol.setGive(SpringView.Give.TOP);
        sv_partol.setType(SpringView.Type.FOLLOW);
        sv_partol.setHeader(new SpringViewHeader(getActivity()));
        sv_partol.setFooter(new SpringViewFooter(getActivity()));
        sv_partol.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                reload();
            }
            @Override
            public void onLoadmore() {
//                reload();
            }
        });

    }

    public void reload() {
        if (!NetWorkUtil.isNetworkConnected(getActivity())) {
            rl_net_info.setVisibility(View.GONE);
            layout_network_break.setVisibility(View.VISIBLE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
            sv_partol.onFinishFreshAndLoad(); //停止加载
        } else {
            rl_net_info.setVisibility(View.VISIBLE);
            layout_network_break.setVisibility(View.GONE);
//            if (mProgressDialog == null) {
//                mProgressDialog = new ProgressDialogFragment();
//            }
//            mProgressDialog.show(getActivity().getSupportFragmentManager());
            mPresenter.sendResume();
        }
    }

    @Override
    public void sendResume(sendResumeBean resultBean) {
        sv_partol.onFinishFreshAndLoad();
//        if (mProgressDialog != null) {
//            mProgressDialog.dismiss();
//        }
        mList.clear();
        if (resultBean.getResume().size() > 0) {
            sv_partol.setVisibility(View.VISIBLE);
            activity_empty_linshi.setVisibility(View.GONE);
        } else {
            sv_partol.setVisibility(View.GONE);
            activity_empty_linshi.setVisibility(View.VISIBLE);
        }
        mAdapter.addAll(resultBean.getResume());
        mList = mAdapter.getAll();
    }

    @Override
    public void upList(upListBean resultBean) {

    }

    @Override
    public void gradeList(WelfareGuildBean resultBean) {

    }

    @Override
    public void requestFailed(String message) {
        sv_partol.onFinishFreshAndLoad();
    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.fragment_resume_manage1;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.fragment_resume_manage1;
    }

    @OnClick({R.id.reload})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.reload:
                reload();
                break;
        }
    }

    @Override
    public void getGuildList(WelfareGuildBean welfareGuildBean) {

    }
}
