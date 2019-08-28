package shangri.example.com.shangri.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.adapter.NewMessageAdapter;
import shangri.example.com.shangri.base.BaseFragment;
import shangri.example.com.shangri.model.bean.response.NewMessageBean;
import shangri.example.com.shangri.model.bean.response.PositionListBean;
import shangri.example.com.shangri.model.bean.response.anchorDetailBean;
import shangri.example.com.shangri.presenter.NewMessagePresenter;
import shangri.example.com.shangri.presenter.view.NewMessageView;
import shangri.example.com.shangri.ui.activity.ChangeAnchorDetailActivity;
import shangri.example.com.shangri.ui.view.FastLinearScrollManger;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.ui.webview.MessagesWebView;
import shangri.example.com.shangri.ui.webview.ZhiWeiWebView;
import shangri.example.com.shangri.util.AndroidInterface.CompanyInterfaceUtils;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.PointUtils;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * Description: 系统通知
 * Data：2018/11/20-9:27
 * Author: lin
 */
public class SystemNotificationFragment extends BaseFragment<NewMessageView, NewMessagePresenter> implements NewMessageView {
    @BindView(R.id.reload)
    Button reload;

    @BindView(R.id.layout_network_break)
    LinearLayout layout_network_break;
    @BindView(R.id.springview)
    SpringView springView;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    @BindView(R.id.activity_empty_linshi1)
    LinearLayout activity_empty_linshi;

    @BindView(R.id.image_type_empty)
    ImageView image_type_empty;
    @BindView(R.id.tv_text1_empty)
    TextView tv_text1_empty;
    @BindView(R.id.tv_text2_empty)
    TextView tv_text2_empty;
    int mPage = 1;

    private NewMessageAdapter mAdapter;
    private List<NewMessageBean.Messages> mList = new ArrayList<>();

    @Override
    protected int getNormalLayoutId() {
        return R.layout.fragment_sys_notification;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.fragment_sys_notification;
    }

    @Override
    protected NewMessagePresenter createPresenter() {
        return new NewMessagePresenter(getActivity(), this);
    }

    @Override
    protected void initViewsAndEvents() {
        image_type_empty.setImageDrawable(getResources().getDrawable(R.mipmap.xiaoxi_kong));
        tv_text1_empty.setText("暂时还没有消息呢");
        tv_text2_empty.setText("");
        initSpringView();

        mAdapter = new NewMessageAdapter(getActivity(), 2, R.layout.message_list_item_nem, mList, new NewMessageAdapter.onClickItem() {
            @Override
            public void onClick(String id, String main_id, String url) {
                mPresenter.isReadMessage(id);
                if (url.length() > 0) {
                    //url
                    Intent intent = new Intent(getActivity(), MessagesWebView.class);
                    intent.putExtra("url", url);
                    startActivity(intent);
                } else {
                    if (UserConfig.getInstance().getRole().equals("1")) {
                        //会长查看职位
                        mPresenter.anchorDetail(main_id);
                    } else if (UserConfig.getInstance().getRole().equals("2")) {
                        //主播查看简历
                        startActivity(new Intent(getActivity(), ChangeAnchorDetailActivity.class).putExtra("id", main_id));
                    }
                    Log.e("NewMessageAdapter", "onClick: id = " + main_id);
                }
            }
        });
        recycler_view.setLayoutManager(new FastLinearScrollManger(getActivity()));
        mAdapter.openLoadAnimation(new ScaleInAnimation());
        recycler_view.setAdapter(mAdapter);
        LoadData(mPage);

    }

    private void initSpringView() {
        springView.setGive(SpringView.Give.TOP);
        springView.setType(SpringView.Type.FOLLOW);
        springView.setHeader(new SpringViewHeader(getActivity()));
        springView.setFooter(new SpringViewFooter(getActivity()));
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                mPage = 1;
                LoadData(mPage);
            }

            @Override
            public void onLoadmore() {
                LoadData(mPage);
            }
        });
    }

    private void LoadData(int page) {
        if (!NetWorkUtil.isNetworkConnected(getActivity())) {
            springView.setVisibility(View.GONE);
            layout_network_break.setVisibility(View.VISIBLE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
        } else {
            springView.setVisibility(View.VISIBLE);
            layout_network_break.setVisibility(View.GONE);
            mPresenter.getNewMessage(page, 2);
        }
    }

    @Override
    public void anchorDetail(anchorDetailBean bean) {
        anchorDetailBean.RecruitBean data = bean.getRecruit();
        PositionListBean.ListBean.DataBean dataBean = new PositionListBean.ListBean.DataBean();
        dataBean.setId(Integer.parseInt(data.getId()));
        dataBean.setTitle(data.getTitle());
        dataBean.setPay_low(Integer.parseInt(data.getPay_low()));
        dataBean.setPay_high(Integer.parseInt(data.getPay_high()));
        dataBean.setCompany(data.getCompany());
        dataBean.setJob_method(Integer.parseInt(data.getJob_method()));
        dataBean.setJob_plat(data.getJob_plat());
        dataBean.setAnchor_type(data.getAnchor_type());
        dataBean.setSalary_type(Integer.parseInt(data.getSalary_type()));
        dataBean.setWelfare(data.getWelfare());
        dataBean.setWork_position(data.getWork_position());
        dataBean.setWork_address(data.getWork_address());
        dataBean.setHot(Integer.parseInt(data.getHot()));
        dataBean.setContact_phone(data.getContact_phone());
        dataBean.setPublish_type(Integer.parseInt(data.getPublish_type()));
        dataBean.setLightspot(data.getLightspot());
        dataBean.setQq(data.getQq());
        dataBean.setWeixin(data.getWeixin());
        dataBean.setEmail(data.getEmail());
        dataBean.setStatus(Integer.parseInt(data.getStatus()));
        dataBean.setJob_description(data.getJob_description());
        dataBean.setInfo_url(data.getInfo_url());
        dataBean.setCheck_number_real(Integer.parseInt(data.getCheck_number_real()));
        dataBean.setShare_number(data.getShare_number());
        dataBean.setAnchor_send_resume(data.getAnchor_send_resume());
        dataBean.setPlat_name(data.getPlat_name());
        dataBean.setType_name(data.getType_name());
        dataBean.setCheck_mark(data.getCheck_mark());
        startActivity(new Intent(getActivity(), ZhiWeiWebView.class).putExtra("bean", dataBean));
    }

    @Override
    public void getMessage(NewMessageBean newMessageBean) {
        springView.onFinishFreshAndLoad();
        List<NewMessageBean.Messages> data = newMessageBean.getMessages();
        if (mPage == 1) {
            mAdapter.clear();
            if (data.size() == 0) {
                springView.setVisibility(View.GONE);
                activity_empty_linshi.setVisibility(View.VISIBLE);
            } else {
                springView.setVisibility(View.VISIBLE);
                activity_empty_linshi.setVisibility(View.GONE);
            }
        }
        if (data.size() > 0) {
            mPage++;
            mAdapter.addAll(data);
        } else if (mPage > 1) {
            ToastUtil.showShort("没有更多数据");
        }
    }

    @Override
    public void isReadMessage() {
        CompanyInterfaceUtils.yueLiaoMessageFace.resush();
    }

    @OnClick({R.id.reload})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reload:
                if (PointUtils.isFastClick()) {
                    mPage = 1;
                    LoadData(mPage);
                }
                break;
        }
    }

    @Override
    public void requestFailed(String message) {
        springView.onFinishFreshAndLoad();
    }
}
