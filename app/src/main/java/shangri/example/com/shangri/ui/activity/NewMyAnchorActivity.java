package shangri.example.com.shangri.ui.activity;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.event.ChangePoint;
import shangri.example.com.shangri.model.bean.response.MyAnchorListBeanResponse1;
import shangri.example.com.shangri.model.bean.response.MyTaskBean;
import shangri.example.com.shangri.model.bean.response.timeBean;
import shangri.example.com.shangri.presenter.MyTaskPresenter;
import shangri.example.com.shangri.presenter.view.MyTaskView;
import shangri.example.com.shangri.ui.adapter.MyAnchorTypeAdapter;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.view.FastLinearScrollManger;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.PointUtils;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 我的主播列表
 * Created by admin on 2017/12/22.
 */

public class NewMyAnchorActivity extends BaseActivity<MyTaskView, MyTaskPresenter> implements MyTaskView {


    @BindView(R.id.re_my_guild_list)
    RecyclerView re_my_guild_list;
    @BindView(R.id.re_falest_guild_list)
    RecyclerView re_falest_guild_list;
    @BindView(R.id.iv_add_guild)
    TextView iv_add_guild;
    @BindView(R.id.activity_empty_linshi1)
    LinearLayout activity_empty_linshi1;

    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;

    @BindView(R.id.layout_network_break)
    LinearLayout layout_network_break;
    @BindView(R.id.rl_net_info)
    RelativeLayout rl_net_info;

    @BindView(R.id.image_type_empty)
    ImageView image_type_empty;
    @BindView(R.id.tv_text1_empty)
    TextView tv_text1_empty;
    @BindView(R.id.tv_text2_empty)
    TextView tv_text2_empty;


    private static final int ACTION_FRESH = 0; //刷新
    private static final int ACTION_LOAD_MORE = 1; //加载更多
    private static int ACTION_TYPE = 0;
    private MyAnchorTypeAdapter mAdapter;
    private MyAnchorTypeAdapter FastmAdapter;
    //用来添加消息头
    private List<MyAnchorListBeanResponse1.GuildsBean> mNewsList = new ArrayList<>();
    private List<MyAnchorListBeanResponse1.GuildsBean> FastNewsList = new ArrayList<>();
    private ProgressDialogFragment mProgressDialog;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.new_my_anchor_activity;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.new_my_anchor_activity;
    }

    @Override
    protected MyTaskPresenter createPresenter() {
        return new MyTaskPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        image_type_empty.setImageDrawable(getResources().getDrawable(R.mipmap.zhubo_kong));
        tv_text1_empty.setText("您还没有主播呢");
        tv_text2_empty.setText("邀请主播加入公会吧~");
//        initSpringView();
        re_my_guild_list.setLayoutManager(new FastLinearScrollManger(this));
        re_falest_guild_list.setLayoutManager(new FastLinearScrollManger(this));
        mAdapter = new MyAnchorTypeAdapter(this, R.layout.my_anchor_type_item, mNewsList, 1);
        mAdapter.openLoadAnimation(new ScaleInAnimation());
        FastmAdapter = new MyAnchorTypeAdapter(this, R.layout.my_anchor_type_item1, FastNewsList, 2);
        FastmAdapter.openLoadAnimation(new ScaleInAnimation());
        re_my_guild_list.setAdapter(mAdapter);
        re_falest_guild_list.setAdapter(FastmAdapter);
        if (!NetWorkUtil.isNetworkConnected(NewMyAnchorActivity.this)) {
            rl_net_info.setVisibility(View.GONE);
            layout_network_break.setVisibility(View.VISIBLE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
        } else {
            rl_net_info.setVisibility(View.VISIBLE);
            layout_network_break.setVisibility(View.GONE);
            mPresenter.myAnchorList();
        }
    }

    @OnClick({R.id.iv_add_guild, R.id.setting_back, R.id.reload})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_add_guild:
                if (PointUtils.isFastClick()) {
                    toActivity();
                }
            case R.id.reload:
                if (PointUtils.isFastClick()) {
                    if (!NetWorkUtil.isNetworkConnected(NewMyAnchorActivity.this)) {
                        rl_net_info.setVisibility(View.GONE);
                        layout_network_break.setVisibility(View.VISIBLE);
                        ToastUtil.showShort(getResources().getString(R.string.search_not_net));
                    } else {
                        rl_net_info.setVisibility(View.VISIBLE);
                        layout_network_break.setVisibility(View.GONE);
                        mPresenter.myAnchorList();
                    }
                }
                break;
            case R.id.setting_back:
                finish();
                break;
        }
    }

    /**
     * 点击撤销  刷新之前点击进入的freagment
     *
     * @param event
     */
    @SuppressWarnings("JavaDoc")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ChangePoint event) {
        Log.d("Debug", "到达我的公会列表界面");
    }

    private void toActivity() {
        Intent intent = new Intent(NewMyAnchorActivity.this, AddAnchorActivity.class);
        intent.putExtra("guildNameText", "");
        intent.putExtra("guild_id", "");
        intent.putExtra("type", 1 + "");
        startActivity(intent);
    }

    @Override
    public void requestFailed(String message) {
        if (mProgressDialog != null && mProgressDialog.isResumed()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void mvpExpleme(MyTaskBean resultBean) {

    }

    @Override
    public void AnchorList(MyAnchorListBeanResponse1 resultBean) {

        if (resultBean.getGuilds().size() > 0 || resultBean.getQuick_guilds().size() > 0) {
            activity_empty_linshi1.setVisibility(View.GONE);
            rl_net_info.setVisibility(View.VISIBLE);
            mNewsList.clear();
            FastNewsList.clear();
            mNewsList = resultBean.getGuilds();
            FastNewsList = resultBean.getQuick_guilds();
            if (FastNewsList.size() == 0) {
                tv2.setVisibility(View.GONE);
                tv3.setVisibility(View.GONE);
                iv_add_guild.setVisibility(View.GONE);
                iv_add_guild.setEnabled(false);
            } else {
                tv2.setVisibility(View.VISIBLE);
                tv3.setVisibility(View.VISIBLE);
                iv_add_guild.setVisibility(View.VISIBLE);
                iv_add_guild.setEnabled(true);
            }
            if (mNewsList.size() == 0) {
                tv1.setVisibility(View.GONE);
            } else {
                tv1.setVisibility(View.VISIBLE);
            }
            mAdapter.setData(mNewsList);
            FastmAdapter.setData(FastNewsList);
        } else {
            activity_empty_linshi1.setVisibility(View.VISIBLE);
            rl_net_info.setVisibility(View.GONE);
        }

    }

    @Override
    public void memberLate(timeBean resultBean) {

    }

    protected void onResume() {
        super.onResume();
        if (!NetWorkUtil.isNetworkConnected(NewMyAnchorActivity.this)) {
            rl_net_info.setVisibility(View.GONE);
            layout_network_break.setVisibility(View.VISIBLE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
        } else {
            rl_net_info.setVisibility(View.VISIBLE);
            layout_network_break.setVisibility(View.GONE);
            mPresenter.myAnchorList();
        }
    }
}
