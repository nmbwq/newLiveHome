package shangri.example.com.shangri.ui.activity;

import android.content.Intent;
import android.os.Bundle;
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
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.adapter.MessagesAdapter;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.MessagesBean;
import shangri.example.com.shangri.presenter.MessagesPresent;
import shangri.example.com.shangri.presenter.view.MessagesListView;
import shangri.example.com.shangri.ui.view.FastLinearScrollManger;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.ui.webview.MessagesWebView;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.PointUtils;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 原消息列表
 */
public class MessagesActivity extends BaseActivity<MessagesListView, MessagesPresent> implements MessagesListView, MessagesAdapter.getMsgUrl {
    private int mPage = 1;

    private MessagesAdapter mAdapter;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.layout_network_break)
    LinearLayout layout_network_break;
    @BindView(R.id.springview)
    SpringView springView;
    @BindView(R.id.reload)
    Button reload;

    @BindView(R.id.activity_empty_linshi1)
    LinearLayout activity_empty_linshi;
    @BindView(R.id.image_type_empty)
    ImageView image_type_empty;
    @BindView(R.id.tv_text1_empty)
    TextView tv_text1_empty;
    @BindView(R.id.tv_text2_empty)
    TextView tv_text2_empty;


    List<MessagesBean.MessagesData> messagesDataList = new ArrayList<>();

    @Override
    public void getMessage(MessagesBean messagesBean) {
        springView.onFinishFreshAndLoad();
        Log.e("MessagesActivity", "getMessage: " + messagesBean.getCurrent_page());
        List<MessagesBean.MessagesData> data = messagesBean.getMessages();
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
            //默认显示在最底部
            int size = mAdapter.getAll().size();
            recyclerView.smoothScrollToPosition(size - 1);
        }

    }

    @OnClick({R.id.reload, R.id.back})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.reload:
                if (PointUtils.isFastClick()) {
                    mPage = 1;
                    LoadData(mPage);
                }
                break;
        }
    }

    private void initSpringView() {
        springView.setGive(SpringView.Give.TOP);
        springView.setType(SpringView.Type.FOLLOW);
        springView.setHeader(new SpringViewHeader(this));
        springView.setFooter(new SpringViewFooter(this));
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

    @Override
    public void setIsRead(MessagesBean messagesBean) {
    }
    @Override
    public void getMesUrl(int id, String url) {
        mPresenter.setIsRead(id);
        Intent intent = new Intent(this, MessagesWebView.class);
        intent.putExtra("url", url);
        startActivity(intent);
    }

    private void LoadData(int page) {
        if (!NetWorkUtil.isNetworkConnected(this)) {
            springView.setVisibility(View.GONE);
            layout_network_break.setVisibility(View.VISIBLE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
        } else {
            springView.setVisibility(View.VISIBLE);
            layout_network_break.setVisibility(View.GONE);
            mPresenter.getMessage(page);
        }
    }

    @Override
    public void requestFailed(String message) {
        springView.onFinishFreshAndLoad();
    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_messages;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_messages;
    }

    @Override
    protected MessagesPresent createPresenter() {
        return new MessagesPresent(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        image_type_empty.setImageDrawable(getResources().getDrawable(R.mipmap.xiaoxi_kong));
        tv_text1_empty.setText("暂时还没有消息呢");
        tv_text2_empty.setText("");
        mAdapter = new MessagesAdapter(this, R.layout.messages_list_item, messagesDataList, this);
        initSpringView();
        recyclerView.setLayoutManager(new FastLinearScrollManger(this));
        mAdapter.openLoadAnimation(new ScaleInAnimation());
        recyclerView.setAdapter(mAdapter);
        LoadData(mPage);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
