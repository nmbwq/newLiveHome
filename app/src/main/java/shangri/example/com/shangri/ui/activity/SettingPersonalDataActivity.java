package shangri.example.com.shangri.ui.activity;


import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.presenter.SettingPersonalDataPresenter;
import shangri.example.com.shangri.presenter.view.SettingPersonalDataView;
import shangri.example.com.shangri.ui.dialog.InputConfirmDialog;
import shangri.example.com.shangri.ui.dialog.SexConfirmDialog;
import shangri.example.com.shangri.ui.view.CircleImageView;
import shangri.example.com.shangri.util.ActivityUtils;
import shangri.example.com.shangri.util.BitmapCache;

/**
 * 个人资料
 * Created by admin on 2017/12/22.
 */

public class SettingPersonalDataActivity extends BaseActivity<SettingPersonalDataView, SettingPersonalDataPresenter> implements SettingPersonalDataView {
    @BindView(R.id.mine_profile_image)
    CircleImageView mUserAvatar;
    @BindView(R.id.tv_nickname)
    TextView tv_nickname;
    @BindView(R.id.tv_autograph)
    TextView tv_autograph;
    @BindView(R.id.tv_bing_phone)
    TextView tv_bing_phone;
    @BindView(R.id.tv_sex)
    TextView tv_sex;
    @BindView(R.id.tv_title)
    TextView tv_title;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.setting_personal_data;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.setting_personal_data;
    }

    @Override
    protected SettingPersonalDataPresenter createPresenter() {
        return new SettingPersonalDataPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        tv_title.setText("设置个人资料");
        setAvatarImage(UserConfig.getInstance().getAvatar());
        tv_nickname.setText(UserConfig.getInstance().getNickname());
        if (UserConfig.getInstance().getGender().equals("M")) {
            tv_sex.setText("男");
        } else {
            tv_sex.setText("女");
        }
        tv_autograph.setText(UserConfig.getInstance().getSign());
        tv_bing_phone.setText(UserConfig.getInstance().getBingphone());

    }

    @OnClick({R.id.rl_head, R.id.rl_nick_name, R.id.rl_sex
            , R.id.rl_autograph, R.id.rl_bing_phone, R.id.iv_back})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_head:
                ActivityUtils.startActivity(this, AvatarActivity.class);
                break;
            case R.id.rl_nick_name:
                showNameDialogInput();
                break;
            case R.id.rl_sex:
                showSexDialogInput();
                break;
            case R.id.rl_autograph:
                showAutographDialogInput();
                break;
            case R.id.rl_bing_phone:

                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    //昵称
    private void showNameDialogInput() {
        InputConfirmDialog mCenterConfirmDialog = new InputConfirmDialog(tv_nickname.getText().toString(), "16");
        mCenterConfirmDialog.setCallBack(new InputConfirmDialog.CallBack() {
            @Override
            public void confirm(String content) {
                tv_nickname.setText(content);
                UserConfig.getInstance().setNickname(content);
                addUpdateInfo("nickname", content);
            }
        });
        mCenterConfirmDialog.show(this.getSupportFragmentManager());
    }

    private void addUpdateInfo(String field, String value) {
        mPresenter.addUpdateInfo(field, value);
    }

    //性别
    private void showSexDialogInput() {
        SexConfirmDialog mCenterConfirmDialog = new SexConfirmDialog("16");
        mCenterConfirmDialog.setCallBack(new SexConfirmDialog.CallBack() {
            @Override
            public void confirm(String content) {
                tv_sex.setText(content);
                if (content.equals("女")) {
                    UserConfig.getInstance().setGender("F");
                } else {
                    UserConfig.getInstance().setGender("M");
                }
                addUpdateInfo("sex", UserConfig.getInstance().getGender());
            }
        });
        mCenterConfirmDialog.show(this.getSupportFragmentManager());
    }

    //签名
    private void showAutographDialogInput() {
        InputConfirmDialog mCenterConfirmDialog = new InputConfirmDialog(tv_autograph.getText().toString(), "30");
        mCenterConfirmDialog.setCallBack(new InputConfirmDialog.CallBack() {
            @Override
            public void confirm(String content) {
                tv_autograph.setText(content);
                UserConfig.getInstance().setSign(content);
                addUpdateInfo("signature", content);
            }
        });
        mCenterConfirmDialog.show(this.getSupportFragmentManager());
    }

    //手机号
    private void showPhoneDialogInput() {
        InputConfirmDialog mCenterConfirmDialog = new InputConfirmDialog(tv_bing_phone.getText().toString(), "11");
        mCenterConfirmDialog.setCallBack(new InputConfirmDialog.CallBack() {
            @Override
            public void confirm(String content) {
                tv_bing_phone.setText(content);
                UserConfig.getInstance().setBingphone(content);
                addUpdateInfo("", content);
            }
        });
        mCenterConfirmDialog.show(this.getSupportFragmentManager());
    }

    @Override
    public void onResume() {
        super.onResume();
        setAvatarImage(UserConfig.getInstance().getAvatar());
    }

    private void setAvatarImage(String url) {

        BitmapCache.getInstance().loadBitmaps(mUserAvatar, url, null);
    }

    @Override
    public void addInfo(String s) {

    }

    @Override
    public void requestFailed(String message) {

    }
}
