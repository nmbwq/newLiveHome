package shangri.example.com.shangri.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.AddSeccussBean;
import shangri.example.com.shangri.model.bean.response.BossPlatBean;
import shangri.example.com.shangri.model.bean.response.HostBindingBean;
import shangri.example.com.shangri.model.bean.response.MyGuildListDataBean;
import shangri.example.com.shangri.model.bean.response.SupportFromList;
import shangri.example.com.shangri.model.bean.response.legalIndexBean;
import shangri.example.com.shangri.presenter.BindingGuildPresenter;
import shangri.example.com.shangri.presenter.view.BindingGuildView;
import shangri.example.com.shangri.ui.adapter.SupportplatfromAdapter;
import shangri.example.com.shangri.ui.adapter.SupportplatfromAdapter1;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;

/**
 *
 */

public class BindingAnchorGuildeTypectivity extends BaseActivity<BindingGuildView, BindingGuildPresenter> implements BindingGuildView {


    boolean isRemember = false;
    @BindView(R.id.setting_back)
    ImageView settingBack;
    @BindView(R.id.tv_platfrom_ident)
    TextView tvPlatfromIdent;
    @BindView(R.id.tv_support_platfrom)
    TextView tvSupportPlatfrom;

    @BindView(R.id.rl_fast)
    RelativeLayout rl_fast;
    @BindView(R.id.rl_normal)
    RelativeLayout rl_normal;
    //0 是在报表获取管理界面添加工会   1 主播綁定  2 普通平台绑定公会
    int type;

    private String pingtainame, guildname;

    private ProgressDialogFragment mProgressDialog;

    List<SupportFromList.AuthPlatfromBean> auth_platfrom = new ArrayList<>();
    List<SupportFromList.QuickPlatfromBean> quick_platfrom = new ArrayList<>();

    @Override
    protected int getNormalLayoutId() {
        return R.layout.binding_anchor_type;
    }

    @Override
    protected void initViewsAndEvents() {
        type = getIntent().getIntExtra("type", 0);
        switch (type) {
            case 0:
                rl_fast.setVisibility(View.VISIBLE);
                rl_normal.setVisibility(View.VISIBLE);
                break;
            case 1:
                rl_fast.setVisibility(View.VISIBLE);
                rl_normal.setVisibility(View.GONE);
                break;
            case 2:
                rl_fast.setVisibility(View.GONE);
                rl_normal.setVisibility(View.VISIBLE);
                break;
        }
        //销毁到前两个界面
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialogFragment();
        }
        mProgressDialog.show(getSupportFragmentManager());
        mPresenter.supportPlatfrom();
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.binding_anchor_type;
    }


    @Override
    protected BindingGuildPresenter createPresenter() {
        return new BindingGuildPresenter(this, this);
    }


    @Override
    public void requestFailed(String message) {

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void myGuildDtaList(MyGuildListDataBean resultBean) {

    }

    @Override
    public void SupportFromList(SupportFromList bean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        auth_platfrom = bean.getAuth_platfrom();
        quick_platfrom = bean.getQuick_platfrom();

    }

    @Override
    public void guildUpgrade(AddSeccussBean bean) {

    }

    @Override
    public void bingSuccess() {

    }



    @Override
    public void platfromType(BossPlatBean mAccountDataBean) {

    }
    @Override
    public void Uploading(legalIndexBean resultBean) {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.setting_back, R.id.tv_fast_ident, R.id.tv_platfrom_ident, R.id.tv_support_platfrom})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_back:
                finish();
                break;
            case R.id.tv_fast_ident:
                startActivity(new Intent(BindingAnchorGuildeTypectivity.this, FastAnchorBindingActivity.class));
                break;
            case R.id.tv_platfrom_ident:
                startActivity(new Intent(BindingAnchorGuildeTypectivity.this, JoinGuildActivity.class));
                break;
            case R.id.tv_support_platfrom:
                showPopupWindowSevenDays();
                break;
        }
    }

    public PopupWindow mPopWindowSelectdays;

    /**
     * 获得七天的弹窗
     */
    private void showPopupWindowSevenDays() {
        //设置contentView
        View contentView = LayoutInflater.from(this).inflate(R.layout.support_platfrom_list, null);
        mPopWindowSelectdays = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindowSelectdays.setContentView(contentView);
//        //设置各个控件的点击响应
//        //显示PopupWindow
        RecyclerView re_fast_list = contentView.findViewById(R.id.re_fast_list);
        RecyclerView re_list = contentView.findViewById(R.id.re_list);
        final RelativeLayout rl_12 = contentView.findViewById(R.id.rl_12);
        RelativeLayout rl_11 = contentView.findViewById(R.id.rl_11);
        rl_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mPopWindowSelectdays.dismiss();
            }
        });
        rl_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        re_fast_list.setLayoutManager(new GridLayoutManager(this, 4));
        SupportplatfromAdapter1 supportplatfromAdapter1 = new SupportplatfromAdapter1(this, R.layout.item_support_platfrom, quick_platfrom);
        supportplatfromAdapter1.openLoadAnimation(new ScaleInAnimation());
        re_fast_list.setAdapter(supportplatfromAdapter1);
        supportplatfromAdapter1.setData(quick_platfrom);


        re_list.setLayoutManager(new GridLayoutManager(this, 4));
        SupportplatfromAdapter supportplatfromAdapter = new SupportplatfromAdapter(this, R.layout.item_support_platfrom, auth_platfrom);
        supportplatfromAdapter.openLoadAnimation(new ScaleInAnimation());
        re_list.setAdapter(supportplatfromAdapter);
        supportplatfromAdapter.setData(auth_platfrom);

        View rootview = LayoutInflater.from(this).inflate(R.layout.binding_anchor_type, null);
        mPopWindowSelectdays.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


}
