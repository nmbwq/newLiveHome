package shangri.example.com.shangri.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.liftRuleBean;
import shangri.example.com.shangri.presenter.UpGradePresenter;
import shangri.example.com.shangri.presenter.view.UpgradeView;
import shangri.example.com.shangri.ui.adapter.WelfareAdapter;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.view.CircleImageView;
import shangri.example.com.shangri.ui.view.VerticalProgressBar;
import shangri.example.com.shangri.util.BitmapCache;
import shangri.example.com.shangri.util.DensityUtil;
import shangri.example.com.shangri.util.ToastUtil;


/**
 * 主播升等级界面
 */

public class UpgradeActivity1 extends BaseActivity<UpgradeView, UpGradePresenter> implements UpgradeView {


    @BindView(R.id.setting_back)
    ImageView settingBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_add_guild)
    TextView ivAddGuild;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.my_photo)
    CircleImageView myPhoto;
    @BindView(R.id.ll_image)
    LinearLayout llImage;
    @BindView(R.id.my_photo1)
    CircleImageView myPhoto1;
    @BindView(R.id.ll_image1)
    LinearLayout llImage1;
    @BindView(R.id.my_photo2)
    CircleImageView myPhoto2;
    @BindView(R.id.ll_image2)
    LinearLayout llImage2;
    @BindView(R.id.progress_register)
    VerticalProgressBar progressRegister;
    @BindView(R.id.tv_register_sum)
    TextView tvRegisterSum;
    @BindView(R.id.progress_resume)
    VerticalProgressBar progressResume;
    @BindView(R.id.tv_resume_sum)
    TextView tvResumeSum;
    @BindView(R.id.progress_register1)
    VerticalProgressBar progressRegister1;
    @BindView(R.id.tv_register_sum1)
    TextView tvRegisterSum1;
    @BindView(R.id.progress_resume1)
    VerticalProgressBar progressResume1;
    @BindView(R.id.tv_resume_sum1)
    TextView tvResumeSum1;
    @BindView(R.id.progress_register2)
    VerticalProgressBar progressRegister2;
    @BindView(R.id.tv_register_sum2)
    TextView tvRegisterSum2;
    @BindView(R.id.progress_resume2)
    VerticalProgressBar progressResume2;
    @BindView(R.id.tv_resume_sum2)
    TextView tvResumeSum2;
    @BindView(R.id.ll_type_text)
    LinearLayout llTypeText;
    @BindView(R.id.tv_message)
    TextView tvMessage;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    @BindView(R.id.rv_partol)
    RecyclerView rvPartol;

    private WelfareAdapter mAdapter;
    private List<liftRuleBean.WelfareBean> mPatrolList = new ArrayList<>();


    private ProgressDialogFragment mProgressDialog;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_upgrade_layout1;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_upgrade_layout1;
    }

    @Override
    protected UpGradePresenter createPresenter() {
        return new UpGradePresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
//        rvPartol.setLayoutManager(new FastLinearScrollManger(UpgradeActivity.this));

        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialogFragment();
        }
        mProgressDialog.show(UpgradeActivity1.this.getSupportFragmentManager());
        mPresenter.liftRule();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void requestFailed(String message) {

    }


    @OnClick({R.id.setting_back, R.id.iv_add_guild, R.id.tv_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_back:
                finish();
                break;
            case R.id.iv_add_guild:
                startActivity(new Intent(this, GradeRulesActivity.class));
                break;
            case R.id.tv_commit:
                if (is_accord_rule == 1) {
                    if (mProgressDialog == null) {
                        mProgressDialog = new ProgressDialogFragment();
                    }
                    mProgressDialog.show(UpgradeActivity1.this.getSupportFragmentManager());
                    mPresenter.handUpgrade();
                } else {
                    startActivity(new Intent(this, AnchorInvateActivity.class));
                }
                break;
        }
    }

    //    邀请注册人数
    int register_anchor;
    //    邀请人上传简历数
    int register_resume;
    //    是否符合升级条件 1是 0否
    int is_accord_rule;
    //邀请好友注册每个等级的个数
    int registerNumber;
    int registerNumber1;
    int registerNumber2;
    //好友上传简历每个等级个数
    int upResumeNumber;
    int upResumeNumber1;
    int upResumeNumber2;

    @Override
    public void liftRule(liftRuleBean resultBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        if (mAdapter == null) {
            mPatrolList.add(new liftRuleBean.WelfareBean("奖励（波币）", "邀请主播注册", "邀请主播上传简历", "个人简历被查看"));
            for (int i = 0; i < resultBean.getWelfare().size(); i++) {
                mPatrolList.add(resultBean.getWelfare().get(i));
            }
            rvPartol.setLayoutManager(new LinearLayoutManager(UpgradeActivity1.this, LinearLayoutManager.HORIZONTAL, false));
            mAdapter = new WelfareAdapter(UpgradeActivity1.this, R.layout.new_item_welfare, mPatrolList, false);
            mAdapter.openLoadAnimation(new ScaleInAnimation());
            rvPartol.setAdapter(mAdapter);
        }
//        //圆形图片要用下面这个方法加载地址
        BitmapCache.getInstance().loadBitmaps(myPhoto, UserConfig.getInstance().getAvatar() + "", null);
        BitmapCache.getInstance().loadBitmaps(myPhoto1, UserConfig.getInstance().getAvatar() + "", null);
        BitmapCache.getInstance().loadBitmaps(myPhoto2, UserConfig.getInstance().getAvatar() + "", null);
        is_accord_rule = resultBean.getIs_accord_rule();
        //    邀请注册人数
        register_anchor = resultBean.getRegister_anchor();
        //    邀请人上传简历数
        register_resume = resultBean.getRegister_resume();

        Log.d("Debug", "register_resume返回的数据" + register_resume + "is_accord_rule返回的数据" + is_accord_rule);

        for (int i = 0; i < resultBean.getRule().size(); i++) {
            if (resultBean.getRule().get(i).getLevel().equals("1")) {
                registerNumber = Integer.parseInt(resultBean.getRule().get(i).getInvite_register_num());
                upResumeNumber = Integer.parseInt(resultBean.getRule().get(i).getInvite_resume_num());
            } else if (resultBean.getRule().get(i).getLevel().equals("2")) {
                registerNumber1 = Integer.parseInt(resultBean.getRule().get(i).getInvite_register_num());
                upResumeNumber1 = Integer.parseInt(resultBean.getRule().get(i).getInvite_resume_num());
            } else if (resultBean.getRule().get(i).getLevel().equals("3")) {
                registerNumber2 = Integer.parseInt(resultBean.getRule().get(i).getInvite_register_num());
                upResumeNumber2 = Integer.parseInt(resultBean.getRule().get(i).getInvite_resume_num());
            }
        }

        tvRegisterSum.setText(registerNumber + "");
        tvRegisterSum1.setText(registerNumber1 + "");
        tvRegisterSum2.setText(registerNumber2 + "");
        tvResumeSum.setText(upResumeNumber + "");
        tvResumeSum1.setText(upResumeNumber1 + "");
        tvResumeSum2.setText(upResumeNumber2 + "");
        if (is_accord_rule == 1) {
            tvCommit.setText("激活等级身份");
        } else {
            tvCommit.setText("立即邀请");

        }
        switch (resultBean.getLevel()) {
            case 0:
                llImage.setVisibility(View.VISIBLE);
                llImage1.setVisibility(View.GONE);
                llImage2.setVisibility(View.GONE);
                if (is_accord_rule == 0) {
                    String str1 = "太棒了！再邀请" + "<font color='#ffffff'>" + (registerNumber - register_anchor) + "</font>" + "人注册；" + "<font color='#ffffff'>" + (upResumeNumber - register_resume) + "</font>" + "人上传简历即可升级，快去邀请吧！";
                    tvMessage.setTextSize(12);
                    tvMessage.setText(Html.fromHtml(str1));
                } else {
                    String str1 = "恭喜您现在可以升级啦！";
                    tvMessage.setTextSize(12);
                    tvMessage.setText(Html.fromHtml(str1));
                }
                progressRegister.setProgress((register_anchor * 100 / registerNumber));
                progressResume.setProgress((register_resume * 100 / upResumeNumber));

                break;
            case 1:
                llImage.setVisibility(View.GONE);
                llImage1.setVisibility(View.VISIBLE);
                llImage2.setVisibility(View.GONE);
                if (is_accord_rule == 0) {
                    String str1 = "太棒了！再邀请" + "<font color='#ffffff'>" + (registerNumber1 - register_anchor) + "</font>" + "人注册；" + "<font color='#ffffff'>" + (upResumeNumber1 - register_resume) + "</font>" + "人上传简历即可升级，快去邀请吧！";
                    tvMessage.setTextSize(12);
                    tvMessage.setText(Html.fromHtml(str1));
                } else {
                    String str1 = "恭喜您现在可以升级啦！";
                    tvMessage.setTextSize(12);
                    tvMessage.setText(Html.fromHtml(str1));
                }
                progressRegister1.setProgress((register_anchor * 100 / registerNumber1));
                progressResume1.setProgress((register_resume * 100 / upResumeNumber1));
                break;
            case 2:
                if (is_accord_rule == 0) {
                    String str1 = "太棒了！再邀请" + "<font color='#ffffff'>" + (registerNumber2 - register_anchor) + "</font>" + "人注册；" + "<font color='#ffffff'>" + (upResumeNumber2 - register_resume) + "</font>" + "人上传简历即可升级，快去邀请吧！";
                    tvMessage.setTextSize(12);
                    tvMessage.setText(Html.fromHtml(str1));
                } else {
                    String str1 = "恭喜您现在可以升级啦！";
                    tvMessage.setTextSize(12);
                    tvMessage.setText(Html.fromHtml(str1));
                }
                llImage.setVisibility(View.GONE);
                llImage1.setVisibility(View.GONE);
                llImage2.setVisibility(View.VISIBLE);
                progressRegister2.setProgress((register_anchor * 100 / registerNumber2));
                progressResume2.setProgress((register_resume * 100 / upResumeNumber2));
                break;
            case 3:
                String str1 = "太棒了，您已经成为高级经纪人了。";
                tvMessage.setTextSize(12);
                tvMessage.setText(Html.fromHtml(str1));
                llImage.setVisibility(View.GONE);
                llImage1.setVisibility(View.GONE);
                llImage2.setVisibility(View.VISIBLE);
                progressRegister2.setProgress(100);
                progressResume2.setProgress(100);
                break;
        }

    }

    @Override
    public void handUpgrade() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        ToastUtil.showShort("恭喜您，激活成功");
        mPresenter.liftRule();
    }


}
