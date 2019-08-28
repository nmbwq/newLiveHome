package shangri.example.com.shangri.ui.activity;


import android.content.Intent;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.base.GlobalApp;
import shangri.example.com.shangri.model.bean.event.BrowseEventBean;
import shangri.example.com.shangri.model.bean.response.AddSeccussBean;
import shangri.example.com.shangri.model.bean.response.BossPlatBean;
import shangri.example.com.shangri.model.bean.response.MyGuildListDataBean;
import shangri.example.com.shangri.model.bean.response.SupportFromList;
import shangri.example.com.shangri.model.bean.response.legalIndexBean;
import shangri.example.com.shangri.presenter.BindingGuildPresenter;
import shangri.example.com.shangri.presenter.view.BindingGuildView;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;

import butterknife.BindView;
import butterknife.OnClick;
import shangri.example.com.shangri.ui.webview.symbolWebView;
import shangri.example.com.shangri.util.ActivityManager;
import shangri.example.com.shangri.util.PointUtils;

/**
 * 加入非快速公会界面
 * Created by admin on 2017/12/22.
 */

public class JoinGuildActivity extends BaseActivity<BindingGuildView, BindingGuildPresenter> implements BindingGuildView {
    @BindView(R.id.user_login_remember_password_icon)
    ImageView user_login_remember_password_icon;

    @BindView(R.id.et_pingtai)
    TextView et_pingtai;

    @BindView(R.id.et_guild_id)
    EditText et_guild_id;

    @BindView(R.id.et_pingtai_name)
    EditText et_pingtai_name;


    private ProgressDialogFragment mProgressDialog;
    boolean isRemember = false;
    private String guildId;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_join_guild;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_join_guild;
    }

    @Override
    protected void initViewsAndEvents() {
    }

    @Override
    protected BindingGuildPresenter createPresenter() {
        return new BindingGuildPresenter(this, this);
    }


    @OnClick({R.id.user_login_remember_password,
            R.id.rl_pass, R.id.setting_back, R.id.tv_exit, R.id.tv_12})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_login_remember_password:        //返回
                if (!isRemember) {
                    user_login_remember_password_icon.setImageResource(R.mipmap.icon_xuanzhong);
                    isRemember = true;
                } else {
                    user_login_remember_password_icon.setImageResource(R.mipmap.icon_yuanjiao5);
                    isRemember = false;
                }
                break;
            case R.id.setting_back:        //返回
                finish();
                break;
            case R.id.tv_12:        //返回
                if (PointUtils.isFastClick()) {
                    Intent resultIntent5 = new Intent(GlobalApp.getAppContext(), symbolWebView.class);
                    resultIntent5.putExtra("url", GlobalApp.URL);
                    startActivity(resultIntent5);
                }
                break;


            case R.id.tv_exit:
                if (!isRemember) {
                    Toast.makeText(this, "请勾选保密协议", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(et_pingtai.getText().toString().trim())) {
                    Toast.makeText(this, "请选择所属公会", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(et_guild_id.getText().toString().trim())) {
                    Toast.makeText(this, "请输入您在该公会平台下的ID", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(et_pingtai_name.getText().toString().trim())) {
                    Toast.makeText(this, "请输入您在该公会下的直播平台昵称", Toast.LENGTH_SHORT).show();
                    return;
                }
                String uid = et_guild_id.getText().toString().trim();
                String etpingtai_name = et_pingtai_name.getText().toString().trim();
                if (mProgressDialog == null) {
                    mProgressDialog = new ProgressDialogFragment();
                }
                mProgressDialog.show(getSupportFragmentManager());
                mPresenter.joinGuild(guildId, uid, etpingtai_name);
                break;
            case R.id.rl_pass:
                //选择公会
                Intent mintent = new Intent(this, CompanyListActivity.class);
                startActivityForResult(mintent, 1);
                break;


        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //  如果请求码与返回码等于预期设置的值  则进行后续操作
        if (requestCode == 1 && resultCode == 2) {
            // 获取返回的数据
            guildId = data.getStringExtra("id");
            String Guildename = data.getStringExtra("Guildename");
            et_pingtai.setText(Guildename);
        }
    }

    @Override
    public void onSuccess() {
        requestFailed("");
        EventBus.getDefault().post(new BrowseEventBean());

        showPopupWindowSevenDays();

//        LookProgressDialog mLookPmrogressDialog = new LookProgressDialog("加入成功！", "查看进度 >>");
//        mLookPmrogressDialog.setCallBack(new LookProgressDialog.CallBack() {
//            @Override
//            public void confirm(String message) {
//                finish();
//            }
//        });
//        mLookPmrogressDialog.show(getSupportFragmentManager());
    }

    @Override
    public void myGuildDtaList(MyGuildListDataBean resultBean) {

    }

    @Override
    public void SupportFromList(SupportFromList bean) {

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
    public void requestFailed(String message) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }


    private PopupWindow mPopWindowSelectdays;

    /**
     *
     */
    private void showPopupWindowSevenDays() {
        //设置contentView
        View contentView = LayoutInflater.from(this).inflate(R.layout.new_add_gonghui1, null);
        mPopWindowSelectdays = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindowSelectdays.setContentView(contentView);
//        //设置各个控件的点击响应
        TextView tv_content = contentView.findViewById(R.id.tv_content);
        TextView tv_next = contentView.findViewById(R.id.tv_next);

        tv_content.setText("主上，您已成功提交审核，请及时联系公会长进行审核。");
        tv_next.setText("查看进度");
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityManager.getInstance().finishActivity(BindingAnchorGuildeTypectivity.class);
                finish();
                mPopWindowSelectdays.dismiss();
                startActivity(new Intent(JoinGuildActivity.this, MyGuildActivity.class));
            }
        });
        View rootview = LayoutInflater.from(this).inflate(R.layout.activity_join_guild, null);
        mPopWindowSelectdays.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


}
