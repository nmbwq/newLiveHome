package shangri.example.com.shangri.ui.activity;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.MyGuildListDataBean;
import shangri.example.com.shangri.model.bean.response.SupportFromList;
import shangri.example.com.shangri.model.bean.response.timeBean;
import shangri.example.com.shangri.presenter.MyGuildPresenter;
import shangri.example.com.shangri.presenter.view.MyGuildView;
import shangri.example.com.shangri.ui.adapter.PayMyGuildAdapter;
import shangri.example.com.shangri.ui.view.CircleImageView;
import shangri.example.com.shangri.ui.view.FastLinearScrollManger;
import shangri.example.com.shangri.ui.view.SpringView;
import shangri.example.com.shangri.ui.view.springview.SpringViewHeader;
import shangri.example.com.shangri.util.ActivityUtils;
import shangri.example.com.shangri.util.BitmapCache;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.TimeUtil;

/**
 * 我的公会
 * Created by admin on 2017/12/22.
 */

public class AnchorPayMyGuildActivity extends BaseActivity<MyGuildView, MyGuildPresenter> implements MyGuildView {


    @BindView(R.id.re_my_guild_list)
    RecyclerView re_my_guild_list;
    @BindView(R.id.iv_add_guild)
    ImageView iv_add_guild;
    @BindView(R.id.my_guild_springview)
    SpringView my_guild_springview;

    @BindView(R.id.layout_no_guild)
    LinearLayout layout_no_guild;


    @BindView(R.id.tv_type)
    TextView tv_type;
    @BindView(R.id.tv_bring_guild)
    TextView tv_bring_guild;
    @BindView(R.id.iv_no_guild)
    ImageView iv_no_guild;
    TextView tv_name;
    TextView tv_text;
    TextView tv_commit;
    CircleImageView im_icon;
    //时间
    String time;
    //请求下来到的时间
    String Datatime;


    private static final int ACTION_FRESH = 0; //刷新
    private static final int ACTION_LOAD_MORE = 1; //加载更多
    private static int ACTION_TYPE = 0;
    private PayMyGuildAdapter mAdapter;
    //用来添加消息头
    private List<MyGuildListDataBean.GuildsBean> mNewsList = new ArrayList<>();

    @Override
    protected int getNormalLayoutId() {
        return R.layout.anchorpay_my_guild_activity;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.anchorpay_my_guild_activity;
    }

    @Override
    protected MyGuildPresenter createPresenter() {
        return new MyGuildPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        initSpringView();
        re_my_guild_list.setLayoutManager(new FastLinearScrollManger(this));
        mAdapter = new PayMyGuildAdapter(this, R.layout.pay_my_guild_item, mNewsList);
        mAdapter.openLoadAnimation(new ScaleInAnimation());
        setHeaderPage();
        re_my_guild_list.setAdapter(mAdapter);
        mPresenter.memberLate();
        if (UserConfig.getInstance().getRole().equals("1")) {
            tv_bring_guild.setText("绑定公会");
            tv_type.setText("您还没有公会呢，赶紧绑定一个吧！");
        } else {
            tv_type.setText("您还没有公会呢，赶紧加入一个吧！");
            tv_bring_guild.setText("加入公会");
        }
        iv_no_guild.setImageResource(R.mipmap.bangding_kong);
    }

    private void setHeaderPage() {
        View header = LayoutInflater.from(AnchorPayMyGuildActivity.this).inflate(R.layout.anchorpay_heald_layout, null);
        tv_name = header.findViewById(R.id.tv_name);
        tv_text = header.findViewById(R.id.tv_text);
        tv_commit = header.findViewById(R.id.tv_commit);
        im_icon = header.findViewById(R.id.im_icon);

        tv_name.setText(UserConfig.getInstance().getUserName() + "");
        BitmapCache.getInstance().loadBitmaps(im_icon, UserConfig.getInstance().getAvatar(), null);


        tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AnchorPayMyGuildActivity.this, SelectPayActivity.class).putExtra("time", time));
                Log.d("Debug", "点击充值操作");
            }
        });
        mAdapter.setHeaderView(header);
    }

    @OnClick({R.id.tv_bring_guild, R.id.iv_add_guild, R.id.setting_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_bring_guild:
                toActivity();
                break;
            case R.id.iv_add_guild:
                toActivity();
                break;
            case R.id.setting_back:
                finish();
                break;

        }
    }

    private void toActivity() {
        if (UserConfig.getInstance().getRole().equals("1")) {
            //会长跳转到
            ActivityUtils.startActivity(this, BindingActivity.class);
        } else if (UserConfig.getInstance().getRole().equals("2")) {
            //主播跳转
            ActivityUtils.startActivity(this, JoinGuildActivity.class);
        } else {
            //管理员绑定公会
            ActivityUtils.startActivity(this, CompanyListActivity.class);
        }
    }

    private void initSpringView() {
        my_guild_springview.setType(SpringView.Type.FOLLOW);
        my_guild_springview.setHeader(new SpringViewHeader(this));
//        my_guild_springview.setFooter(new SpringViewFooter(this));
        my_guild_springview.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                if (!NetWorkUtil.isNetworkConnected(AnchorPayMyGuildActivity.this)) {
                    my_guild_springview.onFinishFreshAndLoad(); //停止加载
                } else {
                    ACTION_TYPE = ACTION_FRESH;
                    mPresenter.memberLate();
                }
            }

            @Override
            public void onLoadmore() {
            }
        });
    }

    @Override
    public void requestFailed(String message) {
        my_guild_springview.onFinishFreshAndLoad();
    }


    @Override
    public void myGuildDtaList(MyGuildListDataBean resultBean) {
        if (resultBean.getGuilds().size() > 0) {
            mNewsList.clear();
            //只获取审核通过的数据
            for (MyGuildListDataBean.GuildsBean bean : resultBean.getGuilds()) {
                if (bean.getCheck_status().equals("1")) {
                    mNewsList.add(bean);
                }
            }
            my_guild_springview.setVisibility(View.VISIBLE);
            layout_no_guild.setVisibility(View.GONE);
            mAdapter.setData(mNewsList);

            if (Datatime.length() > 0) {
                try {
                    time = TimeUtil.longToString(Long.parseLong(Datatime), "yyyy.MM.dd");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                tv_text.setText("您的vip会员将于" + time + "到期");
                tv_commit.setText("我要续费");
            } else {
                time = "";
                tv_text.setText("您还不是我们的vip宝宝哦");
                tv_commit.setText("我要VIP");
            }
        } else {
            time = "";
            my_guild_springview.setVisibility(View.GONE);
            layout_no_guild.setVisibility(View.VISIBLE);
            tv_text.setText("您还不是我们的vip宝宝哦");
            tv_commit.setText("我要VIP");
        }
    }

    @Override
    public void memberLate(timeBean resultBean) {
        Datatime = resultBean.getMember().getMember_time() + "";
        mPresenter.requestListData();
    }

    @Override
    public void guildRatio() {

    }

    @Override
    public void SupportFromList(SupportFromList resultBean) {

    }


    protected void onResume() {
        super.onResume();
        mPresenter.memberLate();
    }

}
