package shangri.example.com.shangri.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.AccountDataBean;
import shangri.example.com.shangri.model.bean.response.GrabAnchorBean;
import shangri.example.com.shangri.model.bean.response.GrabAnchorOrderBean;
import shangri.example.com.shangri.model.bean.response.JudgeGradeBean;
import shangri.example.com.shangri.model.bean.response.NewCompanyBean;
import shangri.example.com.shangri.model.bean.response.ResumeDetailBean;
import shangri.example.com.shangri.model.bean.response.ResumeIndexBean;
import shangri.example.com.shangri.model.bean.response.chatAnchorResponseBean;
import shangri.example.com.shangri.model.bean.response.sendSucceedResonse;
import shangri.example.com.shangri.model.bean.response.wantListBean;
import shangri.example.com.shangri.presenter.LookAnchorPresenter;
import shangri.example.com.shangri.presenter.LookAnchorView;
import shangri.example.com.shangri.ui.adapter.ImageMuchAdapter;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.view.CansCrollRecycle.RecyclerViewPager;

/**
 * Created by Administrator on 2017/7/3.
 * do what to 2017/7/3
 */

public class ChangeAnchorDetailActivity extends BaseActivity<LookAnchorView, LookAnchorPresenter> implements LookAnchorView {
    @BindView(R.id.recy_image)
    RecyclerViewPager recy_image;

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_age)
    TextView tvAge;
    @BindView(R.id.im_sex)
    ImageView imSex;
    @BindView(R.id.tv_jingyan)
    TextView tvJingyan;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_type)
    TextView tv_type;
    @BindView(R.id.tv_type1)
    TextView tv_type1;
    @BindView(R.id.tv_type2)
    TextView tv_type2;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv_state)
    TextView tv_state;

    @BindView(R.id.ll_info)
    LinearLayout llInfo;
    @BindView(R.id.tv_photo_positon)
    TextView tv_photo_positon;
    @BindView(R.id.ll_type_check)
    LinearLayout ll_type_check;

    @BindView(R.id.rl_type_fail)
    RelativeLayout rl_type_fail;
    @BindView(R.id.im_jubao)
    TextView im_jubao;


    @BindView(R.id.tv_fail_message)
    TextView tv_fail_message;
    ResumeIndexBean.ResumeBean bean;

    int imageposition = 1;
    String id;
    private ProgressDialogFragment mProgressDialog;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.look_job_item2;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.look_job_item2;
    }

    @Override
    protected LookAnchorPresenter createPresenter() {
        return new LookAnchorPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        id = getIntent().getStringExtra("id");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialogFragment();
        }
        mProgressDialog.show(ChangeAnchorDetailActivity.this.getSupportFragmentManager());
        mPresenter.resumeDetail(id, "");
    }

    public void initEvent() {
        //主页不允许左滑返回
        mSwipeBackLayout.setEnableGesture(false);
        im_jubao.setVisibility(View.VISIBLE);
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();

        tvName.setText(bean.getNickname() + "");
        tvAge.setText(bean.getAge() + "岁");

        if (bean.getSex().equals("1")) {
            imSex.setImageDrawable(this.getResources().getDrawable(R.mipmap.nan));
        } else {
            imSex.setImageDrawable(this.getResources().getDrawable(R.mipmap.nv));
        }
        tvJingyan.setText(bean.getLive_age() + "年直播经验");
//        状态 0:未审核 1:审核通过 2:审核失败
        switch (bean.getStatus()) {
            case 0:
                ll_type_check.setVisibility(View.VISIBLE);
                rl_type_fail.setVisibility(View.GONE);
                break;
            case 1:
                ll_type_check.setVisibility(View.GONE);
                rl_type_fail.setVisibility(View.GONE);
                break;
            case 2:
                ll_type_check.setVisibility(View.GONE);
                rl_type_fail.setVisibility(View.VISIBLE);
                tv_fail_message.setText("原因：" + bean.getCheck_description() + "");
                break;
        }

        List<String> Imagedatas = new ArrayList<>();
        for (int i = 0; i < bean.getPhoto().size(); i++) {
            Imagedatas.add(bean.getPhoto().get(i).getImg_url());
        }
        LinearLayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recy_image.setLayoutManager(layout);
        ImageMuchAdapter imageMuchAdapter;
        imageMuchAdapter = new ImageMuchAdapter(this, R.layout.look_image_item, Imagedatas);
        recy_image.setAdapter(imageMuchAdapter);
        imageMuchAdapter.setData(Imagedatas);

        if (bean.getPhoto().size() > 0) {
            tv_photo_positon.setVisibility(View.VISIBLE);
            tv_photo_positon.setText("1/" + bean.getPhoto().size());
        } else {
            tv_photo_positon.setVisibility(View.GONE);
        }

        recy_image.addOnPageChangedListener(new RecyclerViewPager.OnPageChangedListener() {
            @Override
            public void OnPageChanged(int oldPosition, int newPosition) {
                Log.d("Debug", "现在的位置是" + newPosition);
                if (newPosition >= 0) {
                    tv_photo_positon.setText(newPosition + 1 + "/" + bean.getPhoto().size());
                }

            }
        });

        if (bean.getType_name().size() == 0) {
            tv_type.setVisibility(View.GONE);
            tv_type1.setVisibility(View.GONE);
            tv_type2.setVisibility(View.GONE);
        } else if (bean.getType_name().size() == 1) {
            tv_type.setText(bean.getType_name().get(0) + "");
            tv_type.setVisibility(View.VISIBLE);
            tv_type1.setVisibility(View.GONE);
            tv_type2.setVisibility(View.GONE);
        } else if (bean.getType_name().size() == 2) {
            tv_type.setVisibility(View.VISIBLE);
            tv_type1.setVisibility(View.VISIBLE);
            tv_type2.setVisibility(View.GONE);
            tv_type.setText(bean.getType_name().get(0) + "");
            tv_type1.setText(bean.getType_name().get(1) + "");
        } else {
            tv_type.setVisibility(View.VISIBLE);
            tv_type1.setVisibility(View.VISIBLE);
            tv_type2.setVisibility(View.VISIBLE);
            tv_type.setText(bean.getType_name().get(0) + "");
            tv_type1.setText(bean.getType_name().get(1) + "");
            tv_type2.setText(bean.getType_name().get(2) + "");
        }
        if (bean.getPay_low().equals("0") && bean.getPay_high().equals("0")) {
            tvMoney.setText("期望底薪：待议");
        } else {
            tvMoney.setText("期望底薪：" + Integer.parseInt(bean.getPay_low()) / 1000 + "k" + "-" + Integer.parseInt(bean.getPay_high()) / 1000 + "k");
        }
        tv1.setVisibility(View.GONE);
        tv2.setVisibility(View.GONE);
        if (bean.getWanted_status_name().length() > 0) {
            tv_state.setVisibility(View.VISIBLE);
            tv_state.setText(bean.getWanted_status_name());
        } else {
            tv_state.setVisibility(View.GONE);
        }

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) llInfo.getLayoutParams();
//获取当前控件的布局对象
        params.height = height;//设置当前控件布局的高度
        llInfo.setLayoutParams(params);
    }

    @Override
    public void requestFailed(String message) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void leaveAnchor(wantListBean resultBean) {

    }

    @Override
    public void getResumeDetailBean(ResumeDetailBean resultBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void companyAdd(NewCompanyBean resultBean) {

    }

    @Override
    public void accountData(AccountDataBean dataBean) {

    }

    @Override
    public void guildNumber(sendSucceedResonse resultBean) {

    }

    @Override
    public void chatAnchor(chatAnchorResponseBean resultBean) {

    }

    @Override
    public void resumeDetail(ResumeIndexBean resultBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        bean = resultBean.getResume();
        initEvent();
    }

    @Override
    public void grabAnchor(GrabAnchorBean mAccountDataBean) {

    }

    @Override
    public void judgeGrade(JudgeGradeBean mAccountDataBean) {

    }

    @Override
    public void grabAnchorOrder(GrabAnchorOrderBean mAccountDataBean) {

    }

    @Override
    public void residueNumber() {

    }

    @Override
    public void resumeDoCollect() {

    }

    @Override
    public void resumeCancelCollect() {

    }

    @OnClick({R.id.webview_back, R.id.im_jubao, R.id.tv_fail_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.webview_back:
                finish();
                break;
            case R.id.im_jubao:
            case R.id.tv_fail_commit:
                Intent intent = new Intent(ChangeAnchorDetailActivity.this, AddInviteActivity.class);
                intent.putExtra("bean", bean);
                intent.putExtra("isFromUpdate", true);
                startActivity(intent);
                break;


        }
    }


}
