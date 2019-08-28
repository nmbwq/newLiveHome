package shangri.example.com.shangri.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;
import com.aspsine.irecyclerview.universaladapter.recyclerview.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseLazyFragment;
import shangri.example.com.shangri.model.bean.response.EncyclopediaList;
import shangri.example.com.shangri.model.bean.response.MessageResonse;
import shangri.example.com.shangri.presenter.EncyclopediaListPresenter;
import shangri.example.com.shangri.presenter.EncyclopedialistView;
import shangri.example.com.shangri.ui.activity.EoachDetailActivity;
import shangri.example.com.shangri.ui.activity.NewTaskDetailActivity;
import shangri.example.com.shangri.ui.adapter.MessageAdapter;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.view.FastLinearScrollManger;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 消息界面
 * Created by admin on 2017/12/22.
 */


public class MyMessageFreagment extends BaseLazyFragment<EncyclopedialistView, EncyclopediaListPresenter> implements EncyclopedialistView {

    @BindView(R.id.rv_partol)
    RecyclerView rv_partol;
    @BindView(R.id.sv_partol)
    SpringView sv_partol;

    @BindView(R.id.activity_empty_linshi1)
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
    LinearLayout rl_net_info;


    private static final int ACTION_FRESH = 0; //刷新
    private static final int ACTION_LOAD_MORE = 1; //加载更多
    private static int ACTION_TYPE = 0;
    @BindView(R.id.reload)
    Button reload;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    private Unbinder unbinder;

    private int currPage = 1;
    private int mPageNum = 0; //总页数
    private MessageAdapter Adapter;
    private ProgressDialogFragment mProgressDialog;
    private List<MessageResonse.MessagesBean> mList = new ArrayList<>();
    //1是找平台 2找公会 3找主播
    private int type = -1;
    private int msg_type;
    private String main_id;
    private int pos;

    public static MyMessageFreagment getInstance(int type) {
        MyMessageFreagment fragment = new MyMessageFreagment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        bundle.putString("NEWS_ID", "NEWS_ID");
        bundle.putString("NEWS_TYPE", "NEWS_ID");
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initView() {

        image_type_empty.setImageDrawable(getResources().getDrawable(R.mipmap.xiaoxi_kong));
        tv_text1_empty.setText("暂时还没有消息呢");
        tv_text2_empty.setText("");

        Bundle arguments = getArguments();
        type = arguments.getInt("type");
        initSpringView();


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
            mProgressDialog.show(getActivity().getSupportFragmentManager());
            loadData();
        }


        rv_partol.setLayoutManager(new FastLinearScrollManger(getActivity()));
        Adapter = new MessageAdapter(getActivity(), R.layout.new_item_message, mList);
        Adapter.openLoadAnimation(new ScaleInAnimation());
        rv_partol.setAdapter(Adapter);
        Adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                MessageResonse.MessagesBean messagesBean = Adapter.get(position);
                msg_type = Adapter.get(position).getMsg_type();
                main_id = Adapter.get(position).getMain_id() + "";
                pos = position;
//                消息类型 0 系统 1辅导 2任务
                switch (msg_type) {
                    case 0:
                        Log.d("Debug", "点击系统消息跳转");
                        break;
                    case 1:
                        mPresenter.messageRead(Adapter.get(position).getId());
                        break;
                    case 2:
                        mPresenter.messageRead(Adapter.get(position).getId());
                        break;
                }
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });

    }


    private void initSpringView() {
        sv_partol.setType(SpringView.Type.FOLLOW);
        sv_partol.setHeader(new SpringViewHeader(getActivity()));
        sv_partol.setFooter(new SpringViewFooter(getActivity()));
        sv_partol.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                ACTION_TYPE = ACTION_FRESH;
                onDataRefresh();
            }

            @Override
            public void onLoadmore() {

                ACTION_TYPE = ACTION_LOAD_MORE;
                requestPatolsList();

            }
        });
    }

    private void onDataRefresh() {
        if (!NetWorkUtil.isNetworkConnected(getActivity())) {
            rl_net_info.setVisibility(View.GONE);
            layout_network_break.setVisibility(View.VISIBLE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
            sv_partol.onFinishFreshAndLoad(); //停止加载
        } else {
            rl_net_info.setVisibility(View.VISIBLE);
            layout_network_break.setVisibility(View.GONE);
            currPage = 1;
            mPresenter.messageList(type + "", currPage + "");
        }
    }

    protected void loadData() {


        if (!NetWorkUtil.isNetworkConnected(getActivity())) {
            rl_net_info.setVisibility(View.GONE);
            layout_network_break.setVisibility(View.VISIBLE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
            sv_partol.onFinishFreshAndLoad(); //停止加载
        } else {
            rl_net_info.setVisibility(View.VISIBLE);
            layout_network_break.setVisibility(View.GONE);
            currPage = 1;
            mPresenter.messageList(type + "", currPage + "");
        }

    }

    private void requestPatolsList() {
        if (!NetWorkUtil.isNetworkConnected(getActivity())) {
            rl_net_info.setVisibility(View.GONE);
            layout_network_break.setVisibility(View.VISIBLE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
            sv_partol.onFinishFreshAndLoad(); //停止加载
        } else {
            rl_net_info.setVisibility(View.VISIBLE);
            layout_network_break.setVisibility(View.GONE);
            currPage++;
            mPresenter.messageList(type + "", currPage + "");
        }

    }

    @Override
    public void requestFailed(String message) {
        sv_partol.onFinishFreshAndLoad();
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
//        onDataRefresh();
    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_all_collect;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_all_collect;
    }

    @Override
    protected EncyclopediaListPresenter createPresenter() {
        return new EncyclopediaListPresenter(getActivity(), this);
    }


    @Override
    public void encyclopediaPlatfromList(EncyclopediaList resultBean) {

    }

    @Override
    public void messageList(MessageResonse resultBean) {
        sv_partol.onFinishFreshAndLoad();
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        if (currPage == 1) {
            Adapter.setData(resultBean.getMessages());
            if (resultBean.getMessages().size() > 0) {
                activity_empty_linshi.setVisibility(View.GONE);
                rv_partol.setVisibility(View.VISIBLE);
            } else {
                activity_empty_linshi.setVisibility(View.VISIBLE);
                rv_partol.setVisibility(View.GONE);
            }
        } else {
            Adapter.addAll(resultBean.getMessages());
        }
        mList = Adapter.getAll();
    }

    @Override
    public void wikiFocus(EncyclopediaList resultBean) {

    }

    @Override
    public void addRuzhu() {

    }

    @Override
    public void wikiDoCollect() {

    }

    @Override
    public void wikiCancelCollect() {

    }

    @Override
    public void messageRead() {
        switch (msg_type) {
            case 0:
                Log.d("Debug", "点击系统消息跳转");
                break;
            case 1:
                Adapter.get(pos).setIs_read(1);
                Adapter.notifyDataSetChanged();
                startActivity(new Intent(getActivity(), EoachDetailActivity.class).putExtra("inspect_id", main_id + ""));
                break;
            case 2:
                Adapter.get(pos).setIs_read(1);
                Adapter.notifyDataSetChanged();
                startActivity(new Intent(getActivity(), NewTaskDetailActivity.class).putExtra("task_id", main_id + ""));
                break;
        }
    }

    @Override
    public void consultShare() {

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

    @OnClick({R.id.reload, R.id.appbar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.reload:
                loadData();
                break;
            case R.id.appbar:
                break;
        }
    }
}
