package shangri.example.com.shangri.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.presenter.SofwwareUserPresenter;
import shangri.example.com.shangri.presenter.view.SoftwareUserView;
import shangri.example.com.shangri.ui.dialog.CenterConfirmDialog;
import shangri.example.com.shangri.util.ActivityManager;
import shangri.example.com.shangri.util.ActivityUtils;

/**
 * 角色选择
 * Created by admin on 2017/12/22.
 */

public class SoftwareActivity extends BaseActivity<SoftwareUserView, SofwwareUserPresenter> implements SoftwareUserView {

    String role = "";//角色权限 1公会长 2主播 3管理员
    @BindView(R.id.rl_huizhang)
    RelativeLayout rlHuizhang;
    @BindView(R.id.rl_zhubo)
    RelativeLayout rlZhubo;

    @Override
    protected SofwwareUserPresenter createPresenter() {
        return new SofwwareUserPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {

    }

    private void isOk(final String role, String s) {
        CenterConfirmDialog mCenterConfirmDialog = new CenterConfirmDialog("领取身份后，暂不允许修改哦!");
        mCenterConfirmDialog.setCallBack(new CenterConfirmDialog.CallBack() {
            @Override
            public void confirm(String message) {
                mPresenter.addInfo(role, "", "", "", "", "", ""
                        , "", "");
//                mPresenter.sofwwareUser(role);
//                softwareUser();
            }
        });
        mCenterConfirmDialog.show(this.getSupportFragmentManager());
    }

    @Override
    public void softwareUser() {
    }

    @Override
    public void addInfo(String s) {
        UserConfig.getInstance().setRole(role);
        mPresenter.memberLogin();
        //主播跳转到发布简历界面
        if (role.equals("2")) {
            Intent intent = new Intent(this, AddInviteActivity.class);
            intent.putExtra("isFromSelectRoles", true);
            startActivity(intent);
        } else {
            ActivityUtils.startActivity(this, MainActivity.class);
        }
        finish();
    }

    @Override
    public void requestFailed(String message) {

    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_software;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_software;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rl_huizhang, R.id.rl_zhubo, R.id.setting_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_back:
                finish();
                break;
            case R.id.rl_huizhang:
                isOk("1", "会长");
                role = "1";
                break;
            case R.id.rl_zhubo:
                isOk("2", "主播");
                role = "2";
                break;
        }

    }


}
