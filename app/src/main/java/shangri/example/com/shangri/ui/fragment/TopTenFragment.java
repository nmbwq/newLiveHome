package shangri.example.com.shangri.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;
import com.aspsine.irecyclerview.universaladapter.recyclerview.OnItemClickListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseLazyFragment;
import shangri.example.com.shangri.model.bean.ChuandiBean;
import shangri.example.com.shangri.model.bean.event.ReportRefushDate;
import shangri.example.com.shangri.model.bean.request.TopTenBean;
import shangri.example.com.shangri.model.bean.response.DetailBean;
import shangri.example.com.shangri.presenter.DetailedDataPresenter;
import shangri.example.com.shangri.presenter.view.DetailedDataView;
import shangri.example.com.shangri.ui.activity.AnchoDetailActivity;
import shangri.example.com.shangri.ui.adapter.TopTenItemAdapter;
import shangri.example.com.shangri.ui.view.FastLinearScrollManger;
import shangri.example.com.shangri.util.PointUtils;
import shangri.example.com.shangri.util.TimeUtil;
import shangri.example.com.shangri.util.ToastUtil;


/**
 * Created by Administrator on 2018/1/13.
 */

@SuppressLint("ValidFragment")
public class TopTenFragment extends BaseLazyFragment<DetailedDataView, DetailedDataPresenter> implements DetailedDataView {
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_Fans)
    TextView tvFans;
    @BindView(R.id.tv_more)
    TextView tv_more;


    private Unbinder unbinder;
    private TopTenItemAdapter mAdapter;
    private String mGuildid;
    private String End_date;
    private String Start_date;
    private String Platfrom_name;
    //判断是哪个界面  1 魅力值top帮 2开播时长top帮 3粉丝增长TOP
    private String type;

    @BindView(R.id.layout_network_break)
    LinearLayout mNetBreakLayout;
    @BindView(R.id.news_entertain_irv)
    RecyclerView mNewsAnchorRecycler;


    @BindView(R.id.activity_empty_linshi)
    LinearLayout activity_empty_linshi;
    @BindView(R.id.ll_info)
    LinearLayout ll_info;
    //当前页数
    private int page = 1;
    //总页数
    private int sumNumber = 1;


    //快速公会1 非快速公会0
    private String IStype;
    private String Table_flag;

    private List<TopTenBean.QuickDataBean.DataBeanXX.DataBeanX> mNewsList = new ArrayList<>();

    public static TopTenFragment getInstance(String Type, String guildid, String start_date, String end_date, String platfrom_name, String Istype, String table_flag) {
        TopTenFragment topTenFragment = new TopTenFragment(Type, guildid, start_date, end_date, platfrom_name, Istype, table_flag);
        return topTenFragment;
    }

    @SuppressLint("ValidFragment")
    public TopTenFragment(String Type, String guildid, String start_date, String end_date, String platfrom_name, String Istype, String table_flag) {
        type = Type;
        Start_date = start_date;
        mGuildid = guildid;
        End_date = end_date;
        Platfrom_name = platfrom_name;
        IStype = Istype;
        Table_flag = table_flag;
        page = 1;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        unbinder.unbind();
    }

    @Override
    protected void initView() {
        Log.d("Debug", "top榜初始化页面");
        EventBus.getDefault().register(this);

        initSpringView();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ReportRefushDate event) {
        if (getUserVisibleHint()) {
            type = event.getType();
            //因为eventBus注册了三次每次发信息都是三次 请求三次  这里做判断  三次请求只请求一次
            if (PointUtils.isFastClick()) {
                mPresenter.topData(event.getEnd_date(), event.getGuildid(), event.getStart_date(), page + "", IStype, Table_flag);
                Log.d("Debug", "接收到推送 结束时间" + event.getEnd_date() + "开始时间" + event.getStart_date() + "传过来type值为" + type);
            }
        }
    }

    @Override
    protected void loadData() {
        if (getUserVisibleHint()) {
            mPresenter.topData(End_date, mGuildid, Start_date, page + "", IStype, Table_flag);
        }
    }

    private void initSpringView() {
        mNewsAnchorRecycler.setLayoutManager(new FastLinearScrollManger(getActivity()));
        mAdapter = new TopTenItemAdapter(getActivity(), R.layout.item_item_top_ten, mNewsList);
        mAdapter.openLoadAnimation(new ScaleInAnimation());
        mNewsAnchorRecycler.setAdapter(mAdapter);
        if (UserConfig.getInstance().getRole().equals("2")) {

        } else {
            mAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                    if (ChuandiBean.start_date.length() == 0 || ChuandiBean.end_date.length() == 0) {
                        try {
                            if (Platfrom_name.indexOf("菠萝街") != -1) {
                                ChuandiBean.Detailstart_date = TimeUtil.getCurrentTime() + "";
                                ChuandiBean.Detailend_date = TimeUtil.getCurrentTime() + "";
                                Log.d("Debug", "是菠萝街");
                            } else {
                                //获取昨天的时间
                                String ourSelData = TimeUtil.getOurSelData(-1);
                                ChuandiBean.Detailstart_date = TimeUtil.convertTimeToLong8(ourSelData) / 1000 + "";
                                ChuandiBean.Detailend_date = TimeUtil.convertTimeToLong8(ourSelData) / 1000 + "";
                                Log.d("Debug", "不是菠萝街");
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    } else {
                        ChuandiBean.Detailstart_date = ChuandiBean.start_date;
                        ChuandiBean.Detailend_date = ChuandiBean.end_date;
                    }
                    if (ChuandiBean.time_slot.length() == 0) {
                        ChuandiBean.Detailtime_slot = "week";
                    } else {
                        ChuandiBean.Detailtime_slot = ChuandiBean.time_slot;
                    }
                    Log.d("Debug", "开始时间" + ChuandiBean.Detailstart_date + "结束时间" + ChuandiBean.Detailend_date);
                    ChuandiBean.Detailplatfrom_name = Platfrom_name;
                    TopTenBean.QuickDataBean.DataBeanXX.DataBeanX dataBean = mAdapter.get(position);
                    Intent intent7 = new Intent(getActivity(), AnchoDetailActivity.class);
                    intent7.putExtra("anchor_uid", dataBean.getBelong_platfrom_uid());
                    intent7.putExtra("guild_id", dataBean.getGuild_id());
                    intent7.putExtra("platfrom_name", Platfrom_name);
                    intent7.putExtra("isFromDeatil", true);
                    startActivity(intent7);
                }

                @Override
                public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                    return false;
                }
            });
        }

    }

    @Override
    public void requestFailed(String message) {


    }

    @Override
    public void topDta(TopTenBean resultBean) {
        Log.d("aaaa", "type--------------" + type);
        if (mNewsList.size() > 0) {
            mNewsList.clear();
        }
        if (IStype.equals("0")) {
            get1(resultBean);
        } else {
            if (UserConfig.getInstance().getRole().equals("2")) {
                get3(resultBean);
            } else {
                get2(resultBean);
            }

        }
    }

    private void get3(TopTenBean resultBean) {
        tv_more.setVisibility(View.GONE);
        tv_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        List<TopTenBean.AnchorTopBean> data = new ArrayList<>();
        //主播top帮  取两处数据
        for (int i = 0; i < resultBean.getAnchor_top().size(); i++) {
            data.add(resultBean.getAnchor_top().get(i));
        }
        for (int i = 0; i < resultBean.getAnchor_data().size(); i++) {
            data.add(resultBean.getAnchor_data().get(i));
        }
        if (data.size() > 0) {
            if (data.get(Integer.parseInt(type)).getData().getData().size() > 0) {
                ll_info.setVisibility(View.VISIBLE);
                activity_empty_linshi.setVisibility(View.GONE);
                if (data.get(Integer.parseInt(type)).getName().contains("土豪")) {
                    tvFans.setText("礼物价值");
                } else {
                    tvFans.setText("收益增长" + data.get(Integer.parseInt(type)).getName().replace("增长", ""));
                }
                if (mAdapter != null) {
                    mAdapter.clear();
                }
                if (mNewsList.size() > 0) {
                    mNewsList.clear();
                }
                for (int i = 0; i < data.get(Integer.parseInt(type)).getData().getData().size(); i++) {
                    TopTenBean.QuickDataBean.DataBeanXX.DataBeanX dataBeanX1 = new TopTenBean.QuickDataBean.DataBeanXX.DataBeanX();
                    TopTenBean.AnchorTopBean.DataBeanX.DataBean dataBean = data.get(Integer.parseInt(type)).getData().getData().get(i);

                    if (dataBean.getNickname() == null || dataBean.getNickname().length() == 0) {
                        dataBeanX1.setAnchor_name(dataBean.getAnchor_name());
                    } else {
                        dataBeanX1.setAnchor_name(dataBean.getNickname());
                    }
                    if (dataBean.getCredits() == null || dataBean.getCredits().length() == 0) {
                        dataBeanX1.setCount(dataBean.getCount());
                    } else {
                        dataBeanX1.setCount(dataBean.getCredits());
                    }
                    mNewsList.add(dataBeanX1);
                }
                mAdapter.setData(mNewsList);
            } else {
                ll_info.setVisibility(View.GONE);
                activity_empty_linshi.setVisibility(View.VISIBLE);
            }
        } else {
            ll_info.setVisibility(View.GONE);
            activity_empty_linshi.setVisibility(View.VISIBLE);
        }
    }

    private void get2(TopTenBean resultBean) {
        if (resultBean.getQuick_data().size() > 0) {
            if (resultBean.getQuick_data().get(Integer.parseInt(type)).getData().getData().size() > 0) {
                ll_info.setVisibility(View.VISIBLE);
                activity_empty_linshi.setVisibility(View.GONE);
                mNewsList = resultBean.getQuick_data().get(Integer.parseInt(type)).getData().getData();
                tvFans.setText(resultBean.getQuick_data().get(Integer.parseInt(type)).getName());
                sumNumber = resultBean.getQuick_data().get(Integer.parseInt(type)).getData().getLast_page();

                if (UserConfig.getInstance().getRole().equals("2")) {
                    tv_more.setVisibility(View.GONE);
                    tv_more.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    });
                } else {
                    Log.d("Debug", "返回的总页数为" + sumNumber + "当前页数" + page);
                    if (sumNumber > 1 && sumNumber > page) {
                        tv_more.setVisibility(View.VISIBLE);
                    } else {
                        tv_more.setVisibility(View.GONE);
                        tv_more.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        });
                    }
                }
                if (page == 1) {
                    mAdapter.setData(mNewsList);
                } else {
                    if (isRequest) {
                        mAdapter.addAll(mNewsList);
                        isRequest = false;
                    }
                }
            } else {
                get(resultBean);
            }
        } else {
            get(resultBean);
        }
    }


    private void get1(TopTenBean resultBean) {
        if (resultBean.getTop_data().size() > 0) {
            if (resultBean.getTop_data().get(Integer.parseInt(type)).getData().getData().size() > 0) {
                ll_info.setVisibility(View.VISIBLE);
                activity_empty_linshi.setVisibility(View.GONE);
                mNewsList = resultBean.getTop_data().get(Integer.parseInt(type)).getData().getData();
                tvFans.setText(resultBean.getTop_data().get(Integer.parseInt(type)).getName());
                sumNumber = resultBean.getTop_data().get(Integer.parseInt(type)).getData().getLast_page();
                Log.d("Debug", "返回的总页数为" + sumNumber);
                if (UserConfig.getInstance().getRole().equals("2")) {
                    tv_more.setVisibility(View.GONE);
                    tv_more.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    });
                } else {
                    if (sumNumber > 1 && sumNumber > page) {
                        tv_more.setVisibility(View.VISIBLE);
                    } else {
                        tv_more.setVisibility(View.GONE);
                        tv_more.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        });
                    }
                }
                if (page == 1) {
                    mAdapter.setData(mNewsList);
                } else {
                    Log.d("Debug", "到达这里--------" + isRequest + "返回的数据长度" + mNewsList.size());
                    if (isRequest) {
                        mAdapter.addAll(mNewsList);
                        isRequest = false;
                    }
                }
            } else {
                get(resultBean);
            }
        } else {
            get(resultBean);
        }
    }


    private void get(TopTenBean resultBean) {
        if (page == 1) {
            ll_info.setVisibility(View.GONE);
            activity_empty_linshi.setVisibility(View.VISIBLE);
        } else {
            ll_info.setVisibility(View.VISIBLE);
            activity_empty_linshi.setVisibility(View.GONE);
            if (UserConfig.getInstance().getRole().equals("2")) {
                tv_more.setVisibility(View.GONE);
                tv_more.setEnabled(false);
            } else {
                if (sumNumber > 1 && sumNumber > page) {
                    tv_more.setVisibility(View.VISIBLE);
                    tv_more.setEnabled(false);
                } else {
                    tv_more.setVisibility(View.GONE);
                    tv_more.setEnabled(true);
                }
            }
            tvFans.setText(resultBean.getTop_data().get(Integer.parseInt(type)).getName());
        }
    }

    @Override
    public void detail(DetailBean resultBean) {

    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.fragment_top_ten;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.fragment_top_ten;
    }

    @Override
    protected DetailedDataPresenter createPresenter() {
        return new DetailedDataPresenter(getActivity(), this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    private boolean isRequest;

    @OnClick(R.id.tv_more)
    public void onViewClicked() {
        Log.d("Debug", "当前页" + page + "总共的页数" + sumNumber);
        isRequest = true;
        if (page < sumNumber) {
            page++;
            mPresenter.topData(End_date, mGuildid, Start_date, page + "", IStype, Table_flag);
        } else {
            ToastUtil.showShort("没有更多数据");
        }
    }
}
