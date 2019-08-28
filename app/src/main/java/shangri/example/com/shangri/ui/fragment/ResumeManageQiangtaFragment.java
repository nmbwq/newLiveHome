package shangri.example.com.shangri.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
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
import shangri.example.com.shangri.adapter.RecommendAdapter1;
import shangri.example.com.shangri.base.BaseFragment;
import shangri.example.com.shangri.model.bean.response.WelfareGuildBean;
import shangri.example.com.shangri.model.bean.response.sendResumeBean;
import shangri.example.com.shangri.model.bean.response.upListBean;
import shangri.example.com.shangri.presenter.ResumeManagePresent;
import shangri.example.com.shangri.presenter.view.ResumeManageView;
import shangri.example.com.shangri.ui.activity.LookAnchorDetailActivity;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * Description:
 * Data：2018/11/6-16:21
 * Author: lin
 */
public class ResumeManageQiangtaFragment extends BaseFragment<ResumeManageView, ResumeManagePresent> implements ResumeManageView {
    @BindView(R.id.rv_partol)
    RecyclerView rv_partol;
    @BindView(R.id.sv_partol)
    SpringView sv_partol;
    @BindView(R.id.activity_empty_linshi)
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


    //1 正常 2审核中 3审核失败 4已关闭  0全部
    int type = 0;
    int mPageNum = 0;
    RecommendAdapter1 mAdapter;
    private List<WelfareGuildBean.GuildList> mList = new ArrayList<>();

    @Override
    protected int getNormalLayoutId() {
        return R.layout.fragment_resume_manage1;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.fragment_resume_manage1;
    }

    @Override
    public void getGuildList(WelfareGuildBean welfareGuildBean) {

    }

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
        rv_partol.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mAdapter = new RecommendAdapter1(getActivity(), R.layout.item_host_collection, mList, new RecommendAdapter1.onItemClick() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), LookAnchorDetailActivity.class);
                intent.putExtra("id",mAdapter.getAll().get(position).getResume_id());
                startActivity(intent);
            }
        });
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
                sv_partol.onFinishFreshAndLoad();
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
            mPresenter.gradeList();
        }
    }

    @Override
    public void sendResume(sendResumeBean resultBean) {

    }

    @Override
    public void upList(upListBean resultBean) {

    }

    @Override
    public void gradeList(WelfareGuildBean resultBean) {
        sv_partol.onFinishFreshAndLoad();
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
    public void requestFailed(String message) {

    }

    @OnClick({R.id.reload})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.reload:
                reload();
                break;
        }
    }
}
