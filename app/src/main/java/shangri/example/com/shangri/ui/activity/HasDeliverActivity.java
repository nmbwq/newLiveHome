package shangri.example.com.shangri.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.BossDataBean;
import shangri.example.com.shangri.model.bean.response.CommunicationBean;
import shangri.example.com.shangri.presenter.SendResumePresenter;
import shangri.example.com.shangri.presenter.view.SendResumeView;
import shangri.example.com.shangri.ui.adapter.BossAdapter;
import shangri.example.com.shangri.ui.view.FastLinearScrollManger;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.util.ToastUtil;


/**
 * 主播已投递界面
 */
public class HasDeliverActivity extends BaseActivity<SendResumeView, SendResumePresenter> implements SendResumeView {


    @BindView(R.id.rv_send)
    RecyclerView rvSendRecyclerView;
    Unbinder unbinder;
    @BindView(R.id.sv_send)
    SpringView mNewsEntertainSpringView;
    List<CommunicationBean.ListBean.DataBean> data = new ArrayList<>();
    @BindView(R.id.image_type_empty)
    ImageView imageTypeEmpty;
    @BindView(R.id.tv_text1_empty)
    TextView tvText1Empty;
    @BindView(R.id.tv_text2_empty)
    TextView tvText2Empty;
    @BindView(R.id.activity_empty_linshi1)
    LinearLayout activity_empty_linshi;

    //列表
    private List<BossDataBean.ListBean.DataBean> mNewsList = new ArrayList<>();
    private BossAdapter mAdapter;
    private int currPage = 1;
    private int mPageNum = 0; //总页数

    //    1发简历 2留电话 3拨打电话 默认1
    public int type = 1;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.send_resume_activity;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.send_resume_activity;
    }

    @Override
    protected SendResumePresenter createPresenter() {
        return new SendResumePresenter(HasDeliverActivity.this, this);
    }

    @Override
    protected void initViewsAndEvents() {


        imageTypeEmpty.setImageDrawable(getResources().getDrawable(R.mipmap.sousuo_kong));
        tvText1Empty.setText("对不起,这里空空如也呀~");
        tvText2Empty.setText("");
        rvSendRecyclerView.setLayoutManager(new FastLinearScrollManger(HasDeliverActivity.this));
        mAdapter = new BossAdapter(HasDeliverActivity.this, R.layout.item_boss, mNewsList);
        mAdapter.openLoadAnimation(new ScaleInAnimation());
        rvSendRecyclerView.setAdapter(mAdapter);
        mNewsEntertainSpringView.setType(SpringView.Type.FOLLOW);
        mNewsEntertainSpringView.setHeader(new SpringViewHeader(HasDeliverActivity.this));
        mNewsEntertainSpringView.setFooter(new SpringViewFooter(HasDeliverActivity.this));
        mNewsEntertainSpringView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                currPage = 1;
                mPresenter.getCommunicationList(UserConfig.getInstance().getToken(), type, currPage + "");

            }

            @Override
            public void onLoadmore() {
                if (currPage < mPageNum) {
                    currPage++;
                    mPresenter.getCommunicationList(UserConfig.getInstance().getToken(), type, currPage + "");
                } else {
                    ToastUtil.showShort("已加载全部");
                    mNewsEntertainSpringView.onFinishFreshAndLoad();
                }
            }
        });
        mPresenter.getCommunicationList(UserConfig.getInstance().getToken(), type, currPage + "");
    }


    @Override
    public void requestFailed(String message) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO: add setContentView(...) invocation
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void succeed(BossDataBean communicationBean) {

        mNewsEntertainSpringView.onFinishFreshAndLoad();
        mPageNum = communicationBean.getList().getLast_page();
        Log.d("Debug", "当前的页数为" + mPageNum);

        if (currPage == 1) {
            mNewsList.clear();
            if (communicationBean.getList().getData().size() > 0) {
                activity_empty_linshi.setVisibility(View.GONE);
                mNewsEntertainSpringView.setVisibility(View.VISIBLE);
            } else {
                activity_empty_linshi.setVisibility(View.VISIBLE);
                mNewsEntertainSpringView.setVisibility(View.GONE);
            }
        }
        mAdapter.addAllAt(mNewsList.size(), communicationBean.getList().getData());
        mAdapter.notifyDataSetChanged();
        mNewsList = mAdapter.getAll();
    }

    @OnClick({R.id.iv_go_back, R.id.title})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_go_back:
                finish();
                break;
            case R.id.title:
                break;
        }
    }
}
