package shangri.example.com.shangri.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.adapter.CommonAdapter;
import shangri.example.com.shangri.adapter.ViewHolder;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.event.BrowseEventBean;
import shangri.example.com.shangri.model.bean.response.MyGuildListDataBean;
import shangri.example.com.shangri.model.bean.response.NewAndroidListDataBean;
import shangri.example.com.shangri.model.bean.response.mineAnchorListDataBean;
import shangri.example.com.shangri.presenter.NewAnchorPresenter;
import shangri.example.com.shangri.presenter.view.NewAnchorView;
import shangri.example.com.shangri.swipeMenuListView.SwipeMenuListView;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.view.CircleImageView;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.SwipeLayout;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 主播管理  点击公会跳转的主播列表界面
 * Created by admin on 2017/12/22.
 */

public class MyAnchorBindingAcvity extends BaseActivity<NewAnchorView, NewAnchorPresenter> implements NewAnchorView {
    @BindView(R.id.setting_back)
    ImageView settingBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_add_guild)
    TextView ivAddGuild;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.rv_manager_partol)
    SwipeMenuListView rvManagerPartol;

    @BindView(R.id.activity_empty_linshi)
    LinearLayout activity_empty_linshi;
    @BindView(R.id.tv_layout_image)
    ImageView tvLayoutImage;
    @BindView(R.id.tv_layout_contont1)
    TextView tvLayoutContont1;
    @BindView(R.id.sv_partol)
    SpringView sv_partol;
    @BindView(R.id.rl_fast)
    RelativeLayout rl_fast;

    private ProgressDialogFragment mProgressDialog;

    private List<mineAnchorListDataBean.AnchorBean> mPatrolList = new ArrayList<>();

    private CommonAdapter<mineAnchorListDataBean.AnchorBean> ManagermAdapter;


    String guild_id;
    String guild_name;
    String type;
    String table_flag;

    private int currPage = 1;
    //删除的位置
    int position;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.new_anchor_list;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.new_anchor_list;
    }

    @Override
    protected NewAnchorPresenter createPresenter() {
        return new NewAnchorPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        ivAddGuild.setVisibility(View.VISIBLE);
        tvTitle.setText("主播绑定");
        ivAddGuild.setText("绑定");
        initSpringView();
        ManagermAdapter = new CommonAdapter<mineAnchorListDataBean.AnchorBean>(MyAnchorBindingAcvity.this, mPatrolList, R.layout.company_other_item) {
            @Override
            public void convert(final ViewHolder helper, final mineAnchorListDataBean.AnchorBean item) {
                final CircleImageView im_photo = helper.getView(R.id.im_photo);
                final TextView tv_name = helper.getView(R.id.tv_name);
                final TextView tv_id = helper.getView(R.id.tv_id);
                final LinearLayout tv_slide = helper.getView(R.id.tv_slide);
                final TextView tv_del = helper.getView(R.id.tv_del);
                final SwipeLayout swipeLayout = helper.getView(R.id.swipeLayout);
                final LinearLayout ll_parent = helper.getView(R.id.ll_parent);
                final LinearLayout ll_dianji = helper.getView(R.id.ll_dianji);


                Glide.with(mContext)
                        .load(item.getAvatar_url())
                        .placeholder(R.mipmap.bg_touxiang)
                        .crossFade()
                        .into(im_photo);
                tv_name.setText(item.getAnchor_name() + "");
                tv_id.setText("ID:" + item.getAnchor_uid() + "");

                tv_slide.setVisibility(View.VISIBLE);
                tv_del.setText("移除");
                tv_del.setBackgroundColor(mContext.getResources().getColor(R.color.ddba69));

                if (item.isOpen()) {
                    swipeLayout.setOpen(true);
                    swipeLayout.open();
                } else {
                    swipeLayout.setOpen(false);
                    swipeLayout.close();
                }
                swipeLayout.setSwipeChangeListener(new SwipeLayout.OnSwipeChangeListener() {
                    @Override
                    public void onStartOpen(SwipeLayout mSwipeLayout) {
                        ll_parent.requestDisallowInterceptTouchEvent(true);
                        //首先现将全部的item关闭   下面的操作 onOpen是滑动那个那个做标记的
                        if (!isOpen) {
                            for (SwipeLayout layout : openList) {
                                layout.close();
                            }
                            openList.clear();
                        }
                    }

                    @Override
                    public void onStartClose(SwipeLayout mSwipeLayout) {
                        ll_parent.requestDisallowInterceptTouchEvent(true);
                    }

                    @Override
                    public void onOpen(SwipeLayout mSwipeLayout) {
                        ll_parent.requestDisallowInterceptTouchEvent(true);
                        openList.add(mSwipeLayout);
                        item.setOpen(true);
                    }

                    @Override
                    public void onDraging(SwipeLayout mSwipeLayout) {
                        ll_parent.requestDisallowInterceptTouchEvent(true);
                    }

                    @Override
                    public void onClose(SwipeLayout mSwipeLayout) {
                        ll_parent.requestDisallowInterceptTouchEvent(false);
                        openList.remove(mSwipeLayout);
                        item.setOpen(false);
                    }
                });

                tv_del.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mProgressDialog == null) {
                            mProgressDialog = new ProgressDialogFragment();
                        }
                        mProgressDialog.show(getSupportFragmentManager());
                        position = helper.getPosition();
                        mPresenter.mineAnchorDelete(item.getAnchor_uid());
                    }
                });


            }
        };
        rvManagerPartol.setAdapter(ManagermAdapter);

    }


    private void initSpringView() {
        sv_partol.setType(SpringView.Type.FOLLOW);
        sv_partol.setHeader(new SpringViewHeader(this));
        sv_partol.setFooter(new SpringViewFooter(this));
        sv_partol.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                sv_partol.onFinishFreshAndLoad(); //停止加载
                currPage = 1;
                if (!NetWorkUtil.isNetworkConnected(MyAnchorBindingAcvity.this)) {
                    sv_partol.onFinishFreshAndLoad(); //停止加载
                } else {
                    mPresenter.mineAnchorList();
                }
            }

            @Override
            public void onLoadmore() {
                sv_partol.onFinishFreshAndLoad(); //停止加载
//                currPage++;
//                if (!NetWorkUtil.isNetworkConnected(MyAnchorBindingAcvity.this)) {
//                    sv_partol.onFinishFreshAndLoad(); //停止加载
//                } else {
//                    mPresenter.anchorList(type, guild_id, table_flag, currPage + "");
//                }

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        //销毁到前两个界面
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialogFragment();
        }
        mProgressDialog.show(getSupportFragmentManager());
        currPage = 1;
        mPresenter.mineAnchorList();
    }

    @Override
    public void requestFailed(String message) {
        if (mProgressDialog != null && mProgressDialog.isResumed()) {
            mProgressDialog.dismiss();
        }
    }

    /**
     * 是否是打开状态
     */
    private boolean isOpen;
    //存放所有已经打开的菜单
    private List<SwipeLayout> openList = new ArrayList<>();

    @Override
    public void anchorList(NewAndroidListDataBean bean) {

    }

    @Override
    public void myGuildDtaList(MyGuildListDataBean resultBean) {

    }

    @Override
    public void mineAnchorList(mineAnchorListDataBean resultBean) {
        sv_partol.onFinishFreshAndLoad(); //停止加载
        if (mProgressDialog != null && mProgressDialog.isResumed()) {
            mProgressDialog.dismiss();
        }
        mPatrolList = resultBean.getAnchor();
        if (currPage == 1) {
            if (mPatrolList.size() > 0) {
                rl_fast.setVisibility(View.GONE);
                sv_partol.setVisibility(View.VISIBLE);
                ManagermAdapter.setmDatas(mPatrolList);
            } else {
                rl_fast.setVisibility(View.VISIBLE);
                sv_partol.setVisibility(View.GONE);
            }
        } else {
            ManagermAdapter.addmDatas(mPatrolList);
        }

    }

    @Override
    public void anchorDelete() {
        if (mProgressDialog != null && mProgressDialog.isResumed()) {
            mProgressDialog.dismiss();
        }
        ToastUtil.showShort("删除成功");
        EventBus.getDefault().post(new BrowseEventBean());
        ManagermAdapter.removeData(position);
        for (int i = 0; i < ManagermAdapter.getmDatas().size(); i++) {
            ManagermAdapter.getmDatas().get(i).setOpen(false);
        }
        ManagermAdapter.notifyDataSetChanged();
        if (ManagermAdapter.getmDatas().size() == 0) {
            rl_fast.setVisibility(View.VISIBLE);
            sv_partol.setVisibility(View.GONE);
        } else {
            rl_fast.setVisibility(View.GONE);
            sv_partol.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void anchorAdd() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.setting_back, R.id.iv_add_guild, R.id.tv_fast_ident})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_back:
                finish();
                break;
            case R.id.iv_add_guild:
            case R.id.tv_fast_ident:
                Intent intent = new Intent(MyAnchorBindingAcvity.this, FastAnchorBindingActivity.class);
                startActivity(intent);
                break;
        }
    }

}
