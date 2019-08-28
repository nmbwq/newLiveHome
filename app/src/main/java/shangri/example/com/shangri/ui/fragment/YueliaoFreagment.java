package shangri.example.com.shangri.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.adapter.CommonAdapter;
import shangri.example.com.shangri.adapter.ViewHolder;
import shangri.example.com.shangri.base.BaseFragment;
import shangri.example.com.shangri.model.bean.event.BrowseEventBean;
import shangri.example.com.shangri.model.bean.response.messageInfoDataBean;
import shangri.example.com.shangri.presenter.YueliaoMessagePresenter;
import shangri.example.com.shangri.presenter.view.YueliaoMessageView;
import shangri.example.com.shangri.swipeMenuListView.SwipeMenuListView;
import shangri.example.com.shangri.ui.activity.TellAboutListActivity;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.view.CircleImageView;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.SwipeLayout;
import shangri.example.com.shangri.ui.view.springview.SpringViewFooter;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.util.AndroidInterface.CompanyInterfaceUtils;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.PointUtils;
import shangri.example.com.shangri.util.TimeUtil;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 消息约聊界面
 * Created by admin on 2017/12/22.
 */

public class YueliaoFreagment extends BaseFragment<YueliaoMessageView, YueliaoMessagePresenter> implements YueliaoMessageView {

    @BindView(R.id.rv_manager_partol)
    SwipeMenuListView rvManagerPartol;

    @BindView(R.id.activity_empty_linshi)
    LinearLayout activity_empty_linshi;
    @BindView(R.id.image_type_empty)
    ImageView image_type_empty;
    @BindView(R.id.tv_text1_empty)
    TextView tv_text1_empty;
    @BindView(R.id.tv_text2_empty)
    TextView tv_text2_empty;

    @BindView(R.id.sv_partol)
    SpringView sv_partol;


    private ProgressDialogFragment mProgressDialog;

    private List<messageInfoDataBean.ListBean.DataBean> mPatrolList = new ArrayList<>();

    private CommonAdapter<messageInfoDataBean.ListBean.DataBean> ManagermAdapter;

    private int currPage = 1;
    private int pageNum;
    //删除的位置
    int position;
    //点击已读的位置
    int setreadeId;
    /**
     * 是否是打开状态
     */
    private boolean isOpen;
    //存放所有已经打开的菜单
    private List<SwipeLayout> openList = new ArrayList<>();

    @Override
    protected int getNormalLayoutId() {
        return R.layout.new_yuliaomessage_list;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.new_yuliaomessage_list;
    }

    @Override
    protected YueliaoMessagePresenter createPresenter() {
        return new YueliaoMessagePresenter(getActivity(), this);
    }

    @Override
    protected void initViewsAndEvents() {
        image_type_empty.setImageDrawable(getResources().getDrawable(R.mipmap.sousuo_kong));
        tv_text1_empty.setText("对不起,这里空空如也呀~");
        tv_text2_empty.setText("");
        EventBus.getDefault().register(this);
        initSpringView();
        ManagermAdapter = new CommonAdapter<messageInfoDataBean.ListBean.DataBean>(getActivity(), mPatrolList, R.layout.company_other_item1) {
            @Override
            public void convert(final ViewHolder helper, final messageInfoDataBean.ListBean.DataBean item) {
                final CircleImageView im_photo = helper.getView(R.id.im_photo);
                final TextView tv_name = helper.getView(R.id.tv_name);
                final TextView tv_id = helper.getView(R.id.tv_id);
                final TextView tv_noread_number = helper.getView(R.id.tv_noread_number);
                final TextView tv_time = helper.getView(R.id.tv_time);


                final LinearLayout tv_slide = helper.getView(R.id.tv_slide);
                final TextView tv_del = helper.getView(R.id.tv_del);
                final SwipeLayout swipeLayout = helper.getView(R.id.swipeLayout);
                final LinearLayout ll_parent = helper.getView(R.id.ll_parent);
                final LinearLayout ll_dianji = helper.getView(R.id.ll_dianji);

                Glide.with(mContext)
                        .load(item.getAvatar().getAvatar_url())
                        .placeholder(R.mipmap.bg_touxiang)
                        .crossFade()
                        .into(im_photo);
                tv_name.setText(item.getAvatar().getNickname() + "");
                if (item.getLast_info().getContact_way() != null) {
                    if (item.getLast_info().getContact_way().length() > 0) {
                        tv_id.setText(item.getLast_info().getContent() + item.getLast_info().getContact_way() + "");
                    } else {
                        tv_id.setText(item.getLast_info().getContent() + "");
                    }
                } else {
                    tv_id.setText(item.getLast_info().getContent() + "");
                }

                tv_time.setText(TimeUtil.getTtime(Long.parseLong(item.getLast_info().getCreate_time() + "")));
                if (UserConfig.getInstance().getRole().equals("1")) {
                    if (item.getGuild_not_read_count() > 0) {
                        tv_noread_number.setVisibility(View.VISIBLE);
                        tv_noread_number.setText(item.getGuild_not_read_count() + "");
                    } else {
                        tv_noread_number.setVisibility(View.GONE);
                    }
                } else {
                    if (item.getAnchor_not_read_count() > 0) {
                        tv_noread_number.setVisibility(View.VISIBLE);
                        tv_noread_number.setText(item.getAnchor_not_read_count() + "");
                    } else {
                        tv_noread_number.setVisibility(View.GONE);
                    }
                }
                //item 跳转
                ll_dianji.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (PointUtils.isFastClick()) {
                            //已读  将未读数量控件隐藏
                            if (UserConfig.getInstance().getRole().equals("1")) {
                                item.setGuild_not_read_count(0);
                            } else {
                                item.setAnchor_not_read_count(0);
                            }
                            ManagermAdapter.notifyDataSetChanged();
                            setreadeId = item.getId();
                            if (mProgressDialog == null) {
                                mProgressDialog = new ProgressDialogFragment();
                            }
                            mProgressDialog.show(getActivity().getSupportFragmentManager());
                            mPresenter.setingRead(item.getId() + "");
                        }
                    }
                });

                tv_slide.setVisibility(View.VISIBLE);
                tv_del.setText("删除");
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
                        mProgressDialog.show(getActivity().getSupportFragmentManager());
                        position = helper.getPosition();
                        mPresenter.infoDel(item.getId() + "");
                    }
                });

            }
        };
        rvManagerPartol.setAdapter(ManagermAdapter);
        //销毁到前两个界面
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialogFragment();
        }
        mProgressDialog.show(getActivity().getSupportFragmentManager());
        currPage = 1;
        mPresenter.messageInfo(currPage + "");
    }


    private void initSpringView() {
        sv_partol.setType(SpringView.Type.FOLLOW);
        sv_partol.setHeader(new SpringViewHeader(getActivity()));
        sv_partol.setFooter(new SpringViewFooter(getActivity()));
        sv_partol.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                sv_partol.onFinishFreshAndLoad(); //停止加载
                currPage = 1;
                if (!NetWorkUtil.isNetworkConnected(getActivity())) {
                    sv_partol.onFinishFreshAndLoad(); //停止加载
                } else {
                    if (mProgressDialog == null) {
                        mProgressDialog = new ProgressDialogFragment();
                    }
                    mProgressDialog.show(getActivity().getSupportFragmentManager());
                    mPresenter.messageInfo(currPage + "");
                }
            }

            @Override
            public void onLoadmore() {
                sv_partol.onFinishFreshAndLoad(); //停止加载

                if (!NetWorkUtil.isNetworkConnected(getActivity())) {
                    sv_partol.onFinishFreshAndLoad(); //停止加载
                } else {
                    if (pageNum > currPage) {
                        currPage++;
                        if (mProgressDialog == null) {
                            mProgressDialog = new ProgressDialogFragment();
                        }
                        mProgressDialog.show(getActivity().getSupportFragmentManager());
                        mPresenter.messageInfo(currPage + "");
                    } else {
                        ToastUtil.showShort("已全部加载");
                    }
                }

            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void requestFailed(String message) {
        if (mProgressDialog != null && mProgressDialog.isResumed()) {
            mProgressDialog.dismiss();
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(getActivity());
    }


    @Override
    public void infoDel() {
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
            activity_empty_linshi.setVisibility(View.VISIBLE);
            sv_partol.setVisibility(View.GONE);
        } else {
            activity_empty_linshi.setVisibility(View.GONE);
            sv_partol.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setingRead() {
        if (mProgressDialog != null && mProgressDialog.isResumed()) {
            mProgressDialog.dismiss();
        }

        Log.d("Debug", "设置未读成功");
        CompanyInterfaceUtils.yueLiaoMessageFace.resush();
        //        约聊ID 为0时不跳转约聊界面
        Intent intent = new Intent(getActivity(), TellAboutListActivity.class);
        intent.putExtra("chat_id", setreadeId + "");
        startActivity(intent);
    }

    @Override
    public void messageInfo(messageInfoDataBean resultBean) {
        sv_partol.onFinishFreshAndLoad(); //停止加载
        if (mProgressDialog != null && mProgressDialog.isResumed()) {
            mProgressDialog.dismiss();
        }
        mPatrolList = resultBean.getList().getData();
        pageNum = resultBean.getList().getLast_page();
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
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
