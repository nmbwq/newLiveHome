package shangri.example.com.shangri.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.adapter.CommonAdapter;
import shangri.example.com.shangri.adapter.ViewHolder;
import shangri.example.com.shangri.base.BaseActivity;
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
import shangri.example.com.shangri.util.TimeUtil;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 主播管理  点击公会跳转的主播列表界面
 * Created by admin on 2017/12/22.
 */

public class MyAnchorGuanliAcvity extends BaseActivity<NewAnchorView, NewAnchorPresenter> implements NewAnchorView {
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
    private ProgressDialogFragment mProgressDialog;

    private List<NewAndroidListDataBean.AnchorsBean.DataBean> mPatrolList = new ArrayList<>();

    private CommonAdapter<NewAndroidListDataBean.AnchorsBean.DataBean> ManagermAdapter;


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
        guild_id = getIntent().getStringExtra("guild_id");
        guild_name = getIntent().getStringExtra("guild_name");
        type = getIntent().getStringExtra("type");
        table_flag = getIntent().getStringExtra("table_flag");
        tvTitle.setText(guild_name);
        if (type.equals("2")) {
            ivAddGuild.setVisibility(View.VISIBLE);
        } else {
            ivAddGuild.setVisibility(View.GONE);
        }
        initSpringView();


        ManagermAdapter = new CommonAdapter<NewAndroidListDataBean.AnchorsBean.DataBean>(MyAnchorGuanliAcvity.this, mPatrolList, R.layout.company_other_item) {
            @Override
            public void convert(final ViewHolder helper, final NewAndroidListDataBean.AnchorsBean.DataBean item) {
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


                if (type.equals("2")) {
                    tv_slide.setVisibility(View.VISIBLE);
                    tv_del.setText("移除");
                    tv_del.setBackgroundColor(mContext.getResources().getColor(R.color.ddba69));
                } else {
                    tv_slide.setVisibility(View.GONE);
                }

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
                        mPresenter.anchorDelete(guild_id, item.getAnchor_uid());
                    }
                });


                ll_dianji.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        long currentTime = 0;
                        try {
                            currentTime = TimeUtil.getCurrentTime();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        Intent intent7 = new Intent(MyAnchorGuanliAcvity.this, AnchoDetailActivity.class);
                        if (type.equals("2")) {
                            intent7.putExtra("isFromAnchor", true);
                        }
                        intent7.putExtra("anchor_uid", item.getAnchor_uid());
                        intent7.putExtra("guild_id", guild_id);
                        startActivity(intent7);
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
                if (!NetWorkUtil.isNetworkConnected(MyAnchorGuanliAcvity.this)) {
                    sv_partol.onFinishFreshAndLoad(); //停止加载
                } else {
                    mPresenter.anchorList(type, guild_id, table_flag, currPage + "");
                }
            }

            @Override
            public void onLoadmore() {
                sv_partol.onFinishFreshAndLoad(); //停止加载
                currPage++;
                if (!NetWorkUtil.isNetworkConnected(MyAnchorGuanliAcvity.this)) {
                    sv_partol.onFinishFreshAndLoad(); //停止加载
                } else {
                    mPresenter.anchorList(type, guild_id, table_flag, currPage + "");
                }

            }
        });

    }

//
//    private void searchNewsList() {
//            if (currPage < mPageNum) {
//                currPage++;
//                mSearchSpringView.onFinishFreshAndLoad();
//                mPresenter.collectList(currPage + "", 6 + "");
//            } else {
//                ToastUtil.showShort("已加载全部");
//                mSearchSpringView.onFinishFreshAndLoad(); //停止加载
//            }
//    }
//

    @Override
    protected void onResume() {
        super.onResume();
        //销毁到前两个界面
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialogFragment();
        }
        mProgressDialog.show(getSupportFragmentManager());
        currPage = 1;
        mPresenter.anchorList(type, guild_id, table_flag, currPage + "");
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
        sv_partol.onFinishFreshAndLoad(); //停止加载
        if (mProgressDialog != null && mProgressDialog.isResumed()) {
            mProgressDialog.dismiss();
        }
        if (type.equals("2")) {
            mPatrolList = bean.getQuick_anchors().getData();
        } else {
            mPatrolList = bean.getAnchors().getData();
        }
        if (currPage == 1) {
            if (mPatrolList.size() > 0) {
                activity_empty_linshi.setVisibility(View.GONE);
                sv_partol.setVisibility(View.VISIBLE);
                ManagermAdapter.setmDatas(mPatrolList);
            } else {
                activity_empty_linshi.setVisibility(View.VISIBLE);
                sv_partol.setVisibility(View.GONE);
            }
        } else {
            ManagermAdapter.addmDatas(mPatrolList);
        }

    }

    @Override
    public void myGuildDtaList(MyGuildListDataBean resultBean) {

    }

    @Override
    public void mineAnchorList(mineAnchorListDataBean resultBean) {

    }

    @Override
    public void anchorDelete() {
        if (mProgressDialog != null && mProgressDialog.isResumed()) {
            mProgressDialog.dismiss();
        }
        ToastUtil.showShort("删除成功");
        ManagermAdapter.removeData(position);
        for (int i = 0; i < ManagermAdapter.getmDatas().size(); i++) {
            ManagermAdapter.getmDatas().get(i).setOpen(false);
        }
        ManagermAdapter.notifyDataSetChanged();
        if (ManagermAdapter.getmDatas().size() == 0) {
            activity_empty_linshi.setVisibility(View.VISIBLE);
            sv_partol.setVisibility(View.GONE);
        } else {
            activity_empty_linshi.setVisibility(View.GONE);
            sv_partol.setVisibility(View.VISIBLE);
        }
//        currPage = 1;
//        mPresenter.anchorList(type, guild_id, table_flag, currPage + "");
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

    @OnClick({R.id.setting_back, R.id.iv_add_guild})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_back:
                finish();
                break;
            case R.id.iv_add_guild:
                if (type.equals("2")) {
                    Intent intent = new Intent(MyAnchorGuanliAcvity.this, AddAnchorActivity.class);
                    intent.putExtra("guildNameText", guild_name);
                    intent.putExtra("guild_id", guild_id);
                    intent.putExtra("type", 2 + "");
                    startActivity(intent);
                }
                break;
        }
    }
}
