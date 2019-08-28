package shangri.example.com.shangri.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;
import com.aspsine.irecyclerview.universaladapter.recyclerview.OnItemClickListener;
import com.bigkoo.convenientbanner.ConvenientBanner;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseFragment;
import shangri.example.com.shangri.model.bean.event.BossBean;
import shangri.example.com.shangri.model.bean.response.AllListBean;
import shangri.example.com.shangri.model.bean.response.BanagetBean;
import shangri.example.com.shangri.model.bean.response.BossDataBean;
import shangri.example.com.shangri.model.bean.response.BossPlatBean;
import shangri.example.com.shangri.model.bean.response.BossTongGaoBean;
import shangri.example.com.shangri.model.bean.response.CollectBean;
import shangri.example.com.shangri.model.bean.response.GrabAnchorBean;
import shangri.example.com.shangri.model.bean.response.GrabAnchorOrderBean;
import shangri.example.com.shangri.model.bean.response.IsFaceResponse;
import shangri.example.com.shangri.model.bean.response.JudgeGradeBean;
import shangri.example.com.shangri.model.bean.response.NewCompanyBean;
import shangri.example.com.shangri.model.bean.response.PositionListBean;
import shangri.example.com.shangri.model.bean.response.ReadPhotoBean;
import shangri.example.com.shangri.model.bean.response.RecruitDetailBean;
import shangri.example.com.shangri.model.bean.response.RequestListBean;
import shangri.example.com.shangri.model.bean.response.ResumeIndexBean;
import shangri.example.com.shangri.model.bean.response.anchorRecruitListBean;
import shangri.example.com.shangri.model.bean.response.chatAnchorResponseBean;
import shangri.example.com.shangri.model.bean.response.newBossDataBean;
import shangri.example.com.shangri.model.bean.response.sendSucceedResonse;
import shangri.example.com.shangri.model.bean.response.wantListBean;
import shangri.example.com.shangri.presenter.BossFragmentPresenter;
import shangri.example.com.shangri.presenter.view.BossFragmentView;
import shangri.example.com.shangri.ui.adapter.BossAdapter;
import shangri.example.com.shangri.ui.adapter.CollectBossAdapter;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.view.FastLinearScrollManger;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.ui.webview.BossWebView;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 主播招聘界面
 * Created by chengaofu on 2017/6/21.
 */

public class CollectNewBossFragment extends BaseFragment<BossFragmentView, BossFragmentPresenter> implements BossFragmentView, View.OnClickListener {
    @Override
    public void getRecruitDetail(RecruitDetailBean resultBean) {

    }

    @Override
    public void limitNumber(IsFaceResponse resultBean) {

    }

    @Override
    public void customLink(RequestListBean resultBean) {

    }

    private static final int ACTION_FRESH = 0; //刷新
    private static final int ACTION_LOAD_MORE = 1; //加载更多
    private static int ACTION_TYPE = 0;
    @BindView(R.id.reload)
    Button reload;
    @BindView(R.id.search_irv)
    RecyclerView newsEntertainIrv;
    @BindView(R.id.search_springview)
    SpringView mNewsEntertainSpringView;
    @BindView(R.id.image_type_empty)
    ImageView image_type_empty;
    @BindView(R.id.tv_text1_empty)
    TextView tv_text1_empty;
    @BindView(R.id.tv_text2_empty)
    TextView tv_text2_empty;
    @BindView(R.id.im_image)
    ImageView imImage;
    @BindView(R.id.tv_text)
    TextView tvText;
    @BindView(R.id.search_not_found)
    LinearLayout searchNotFound;
    @BindView(R.id.rl_net_info)
    RelativeLayout rlNetInfo;
    @BindView(R.id.layout_network_break)
    LinearLayout layout_network_break;



    private int currPage = 1;
    private final int PAGE_SIZE = 15;
    private int mPageNum = 0; //总页数

    private ProgressDialogFragment mProgressDialog;
    //轮播图
    private ConvenientBanner mBanner;
    //列表
    private List<CollectBean.CollectsBean> mNewsList = new ArrayList<>();
    private CollectBossAdapter mAdapter;


    @Override
    protected void initViewsAndEvents() {

        //标题渐变效果
        newsEntertainIrv.setLayoutManager(new FastLinearScrollManger(getActivity()));
        mAdapter = new CollectBossAdapter(getActivity(), R.layout.item_boss, mNewsList);
        mAdapter.openLoadAnimation(new ScaleInAnimation());
        // 注册订阅者
        EventBus.getDefault().register(this);
        newsEntertainIrv.setAdapter(mAdapter);
        initSpringView();
        mPresenter.collectList(currPage + "", 12 + "");
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BossBean event) {
        for (int i = 0; i < mAdapter.getAll().size(); i++) {
            CollectBean.CollectsBean dataBean = mAdapter.get(i);
            if (event.getPhone()) {
                Log.d("Debug", "到达更改的里面" + event.getRecruit_id());
                if (dataBean.getId().equals(event.getRecruit_id())) {
//                      //换成已沟通
                    dataBean.setIs_linkup(1);
                }
                mAdapter.notifyDataSetChanged();
            } else {
                if (dataBean.getId().equals(event.getRecruit_id())) {
                    mAdapter.remove(dataBean);
                    mAdapter.notifyDataSetChanged();
                    if (mAdapter.getAll().size() == 0) {
                        searchNotFound.setVisibility(View.VISIBLE);
                        mNewsEntertainSpringView.setVisibility(View.GONE);
                    }
                }
            }
        }
    }


    private void supportRequestWindowFeature(int featureActionBarOverlay) {
    }


    private void initSpringView() {
        mNewsEntertainSpringView.setType(SpringView.Type.FOLLOW);
        mNewsEntertainSpringView.setHeader(new SpringViewHeader(getActivity()));
        mNewsEntertainSpringView.setFooter(new SpringViewFooter(getActivity()));
        mNewsEntertainSpringView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                ACTION_TYPE = ACTION_FRESH;
                mNewsEntertainSpringView.onFinishFreshAndLoad(); //停止加载
                loadData();
            }

            @Override
            public void onLoadmore() {
                ACTION_TYPE = ACTION_LOAD_MORE;
                mNewsEntertainSpringView.onFinishFreshAndLoad(); //停止加载
                requestNewsList();
            }
        });

    }

    protected void loadData() {
        if (!NetWorkUtil.isNetworkConnected(getActivity())) {
            Log.d("Debug", "到达没网络这里");
            mNewsEntertainSpringView.setVisibility(View.INVISIBLE);
            layout_network_break.setVisibility(View.VISIBLE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
        } else {
            Log.d("Debug", "到达有网络这里");
            mNewsEntertainSpringView.setVisibility(View.VISIBLE);
            layout_network_break.setVisibility(View.INVISIBLE);
            mNewsList.clear();
            currPage = 1;
//            request();
            mPresenter.collectList(currPage + "", 12 + "");
        }

    }

    private void requestNewsList() {
        if (!NetWorkUtil.isNetworkConnected(getActivity())) {
            Log.d("Debug", "到达没网络这里");
            mNewsEntertainSpringView.setVisibility(View.INVISIBLE);
            layout_network_break.setVisibility(View.VISIBLE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
        } else {
            mNewsEntertainSpringView.setVisibility(View.VISIBLE);
            layout_network_break.setVisibility(View.INVISIBLE);
            if (currPage < mPageNum) {
                currPage++;
                mPresenter.collectList(currPage + "", 12 + "");
//                request();
            } else {
                ToastUtil.showShort("已加载全部");
                mNewsEntertainSpringView.onFinishFreshAndLoad(); //停止加载
            }
        }
    }


    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_collect_interlocution1;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_collect_interlocution1;
    }

    @Override
    protected BossFragmentPresenter createPresenter() {
        return new BossFragmentPresenter(getContext(), this);

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
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }


    /**
     * 招聘列表接口
     *
     * @param mAccountDataBean
     */
    @Override
    public void recruitList(BossDataBean mAccountDataBean) {

    }

    @Override
    public void NewrecruitList(newBossDataBean mAccountDataBean) {

    }

    @Override
    public void positionList(PositionListBean resultBean) {

    }

    @Override
    public void platfromType(BossPlatBean mAccountDataBean) {

    }

    @Override
    public void grabAnchor(GrabAnchorBean mAccountDataBean) {

    }

    @Override
    public void grabAnchorOrder(GrabAnchorOrderBean mAccountDataBean) {

    }

    @Override
    public void resumeAllList(AllListBean resultBean) {

    }

    @Override
    public void anchorRecruitList(anchorRecruitListBean mAccountDataBean) {

    }

    @Override
    public void conscribeIndex(BossTongGaoBean mAccountDataBean) {

    }

    @Override
    public void wantList(wantListBean mAccountDataBean) {

    }

    @Override
    public void leaveAnchor(wantListBean mAccountDataBean) {

    }

    @Override
    public void resumeState(ResumeIndexBean resultBean) {

    }


    @Override
    public void url(BossPlatBean mAccountDataBean) {

    }

    @Override
    public void doCollect() {

    }

    @Override
    public void noLike() {

    }

    @Override
    public void cancelCollect() {

    }

    @Override
    public void sendSucceed(sendSucceedResonse resultBean) {

    }

    @Override
    public void guildNumber(sendSucceedResonse resultBean) {

    }

    @Override
    public void chatAnchor(chatAnchorResponseBean resultBean) {

    }


    @Override
    public void readPhoto(ReadPhotoBean resultBean) {

    }

    @Override
    public void resumeIndex(ResumeIndexBean resultBean) {

    }

    @Override
    public void anchorShare() {

    }

    @Override
    public void anchorMakeTelephone() {

    }

    @Override
    public void makeCall(BanagetBean resultBean) {

    }

    @Override
    public void surplusMakeCall(BanagetBean resultBean) {

    }

    @Override
    public void getCollect(CollectBean mAccountDataBean) {
        mPageNum = mAccountDataBean.getTotal_page();
        mNewsEntertainSpringView.onFinishFreshAndLoad();
        if (ACTION_TYPE == ACTION_FRESH) {
            if (mAccountDataBean.getCollects().size() > 0) {
                mAdapter.setData(mAccountDataBean.getCollects());
                searchNotFound.setVisibility(View.GONE);
                mNewsEntertainSpringView.setVisibility(View.VISIBLE);
            } else {
                searchNotFound.setVisibility(View.VISIBLE);
                mNewsEntertainSpringView.setVisibility(View.GONE);
            }

        } else if (ACTION_TYPE == ACTION_LOAD_MORE) {
            mAdapter.addAllAt(mNewsList.size(), mAccountDataBean.getCollects());
        }
        mNewsList = mAdapter.getAll();
    }

    @Override
    public void recruitBanner(BanagetBean resultBean) {

    }

    @Override
    public void companyAdd(NewCompanyBean resultBean) {

    }

    @Override
    public void judgeGrade(JudgeGradeBean resultBean) {

    }

    @Override
    public void residueNumber() {

    }

    @Override
    public void resumeDoCollect() {

    }

    @Override
    public void resumeCancelCollect() {

    }

    @Override
    public void onClick(View view) {

    }
}
