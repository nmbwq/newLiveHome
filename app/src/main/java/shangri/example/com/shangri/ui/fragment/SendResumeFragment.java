package shangri.example.com.shangri.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseLazyFragment;
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
 * 发送简历
 */
public class SendResumeFragment extends BaseLazyFragment<SendResumeView, SendResumePresenter> implements SendResumeView {


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
    public int type;

    public static SendResumeFragment getInstance(int type) {
        SendResumeFragment fragment = new SendResumeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected int getNormalLayoutId() {
        return R.layout.send_resume_fragment;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.send_resume_fragment;
    }

    @Override
    protected SendResumePresenter createPresenter() {
        return new SendResumePresenter(getActivity(), this);
    }

    @Override
    protected void initView() {

        Bundle arguments = getArguments();
        type = arguments.getInt("type");

        imageTypeEmpty.setImageDrawable(getResources().getDrawable(R.mipmap.sousuo_kong));
        tvText1Empty.setText("对不起,这里空空如也呀~");
        tvText2Empty.setText("");
        rvSendRecyclerView.setLayoutManager(new FastLinearScrollManger(getActivity()));
        mAdapter = new BossAdapter(getActivity(), R.layout.item_boss, mNewsList);
        mAdapter.openLoadAnimation(new ScaleInAnimation());
        rvSendRecyclerView.setAdapter(mAdapter);
        mNewsEntertainSpringView.setType(SpringView.Type.FOLLOW);
        mNewsEntertainSpringView.setHeader(new SpringViewHeader(getActivity()));
        mNewsEntertainSpringView.setFooter(new SpringViewFooter(getActivity()));
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
    protected void loadData() {

    }

    @Override
    public void requestFailed(String message) {

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
}
