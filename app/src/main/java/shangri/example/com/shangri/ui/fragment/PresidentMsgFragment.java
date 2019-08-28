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
import shangri.example.com.shangri.model.bean.response.anchorDetailBean;
import shangri.example.com.shangri.presenter.NewMessagePresenter;
import shangri.example.com.shangri.presenter.view.NewMessageView;
import shangri.example.com.shangri.ui.activity.LookAnchorDetailActivity;
import shangri.example.com.shangri.ui.view.FastLinearScrollManger;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.ui.webview.MessagesWebView;
import shangri.example.com.shangri.util.AndroidInterface.CompanyInterfaceUtils;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.ToastUtil;
import shangri.example.com.shangri.util.PointUtils;

/**
 * Description:会长消息列表
 * Data：2018/11/19-17:51
 * Author: lin
 */
public class PresidentMsgFragment extends BaseFragment<NewMessageView, NewMessagePresenter> implements NewMessageView {
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
    private int mPage = 1;

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
        mAdapter = new NewMessageAdapter(getActivity(), 1, R.layout.message_list_item_nem, mList, new NewMessageAdapter.onClickItem() {
            @Override
            public void onClick(String id, String main_id, String url) {
                Log.e("NewMessageAdapter", "onClick: id = " + id);
                mPresenter.isReadMessage(id);
                if (url.length() > 0) {
                    //url
                    Intent intent = new Intent(getActivity(), MessagesWebView.class);
                    intent.putExtra("url", url);
                    startActivity(intent);
                } else {
                    //简历ID或者职位ID
                    if (UserConfig.getInstance().getRole().equals("1")) {
                        //会长跳主播详情
                        Intent intent = new Intent(getActivity(), LookAnchorDetailActivity.class);
                        intent.putExtra("id", main_id);
                        startActivity(intent);
                    } else if (UserConfig.getInstance().getRole().equals("2")) {
                        //主播跳职位
                        mPresenter.anchorDetail(main_id);
                    }
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
            mPresenter.getNewMessage(page, 1);
        }
    }

    @Override
    public void anchorDetail(anchorDetailBean bean) {
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
