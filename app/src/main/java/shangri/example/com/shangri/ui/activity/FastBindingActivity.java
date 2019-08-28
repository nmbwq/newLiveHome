package shangri.example.com.shangri.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
import shangri.example.com.shangri.ui.webview.symbolWebView;
import shangri.example.com.shangri.util.ActivityManager;
import shangri.example.com.shangri.util.ToastUtil;
import shangri.example.com.shangri.util.PointUtils;

/**
 * Created by Administrator on 2017/12/30.
 */

public class FastBindingActivity extends BaseActivity<BindingGuildView, BindingGuildPresenter> implements BindingGuildView {

    @BindView(R.id.user_login_remember_password_icon)
    ImageView user_login_remember_password_icon;

    @BindView(R.id.tv_title)
    TextView tv_title;
    boolean isRemember = false;

    @BindView(R.id.et_pingtai)
    TextView et_pingtai;

    @BindView(R.id.et_guild_name)
    EditText et_guild_name;
    @BindView(R.id.rl_clsan)
    RelativeLayout rlClsan;
    @BindView(R.id.et_xishu)
    EditText et_xishu;
    @BindView(R.id.rl_now)
    RelativeLayout rl_now;
    @BindView(R.id.tv_now_content)
    TextView tv_now_content;
    @BindView(R.id.rl_guild_id)
    RelativeLayout rl_guild_id;
    @BindView(R.id.rl_anchor_id)
    RelativeLayout rl_anchor_id;
    @BindView(R.id.et_guild_id)
    EditText etGuildId;
    @BindView(R.id.et_anchor_id)
    EditText etAnchorId;

    private ProgressDialogFragment mProgressDialog;

    @Override
    protected void initViewsAndEvents() {
        tv_title.setText("快速绑定");
    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.fastbinding;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.fastbinding;
    }

    @OnClick({R.id.user_login_remember_password, R.id.iv_add_guild, R.id.setting_back, R.id.rl_pass,
            R.id.tv_exit, R.id.iv_select,R.id.tv_12})
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
            case R.id.iv_add_guild:        //返回
                finish();
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

            case R.id.tv_exit:        //下一步
                if (!isRemember) {
                    Toast.makeText(this, "请勾选保密协议", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(et_pingtai.getText().toString().trim())) {
                    Toast.makeText(this, "请选择或输入直播平台", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(et_guild_name.getText().toString().trim())) {
                    Toast.makeText(this, "请输入公会名称", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (is_paltfrom.equals("1")) {
                    if (TextUtils.isEmpty(etGuildId.getText().toString().trim())) {
                        Toast.makeText(this, "请输入公会id", Toast.LENGTH_SHORT).show();
                        return;
                    }
                } else {
                    if (TextUtils.isEmpty(etAnchorId.getText().toString().trim())) {
                        Toast.makeText(this, "请输入主播id，多个用；分开", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                if (et_pingtai.getText().toString().trim().contains("NOW")) {

                    if (TextUtils.isEmpty(et_xishu.getText().toString().trim())) {
                        ToastUtil.showShort("请填写大于0小于等于1的系数");
                        return;
                    }
                    if ((0 > Double.parseDouble(et_xishu.getText().toString())) || Double.parseDouble(et_xishu.getText().toString()) > 1) {
                        ToastUtil.showShort("请填写大于0小于等于1的系数");
                        return;
                    }
                }
                String pingtainame = et_pingtai.getText().toString().trim();
                String guildname = et_guild_name.getText().toString().trim();

                //销毁到前两个界面
                if (mProgressDialog == null) {
                    mProgressDialog = new ProgressDialogFragment();
                }
                mProgressDialog.show(getSupportFragmentManager());

                if (is_paltfrom.equals("1")) {
                    mPresenter.quickbinding(id, pingtainame, et_guild_name.getText().toString(), etGuildId.getText().toString(), "", table_flag);
                } else {
                    mPresenter.quickbinding(id, pingtainame, et_guild_name.getText().toString(), "", etAnchorId.getText().toString(), table_flag);
                }

                break;
            case R.id.iv_select:
            case R.id.rl_pass://选择平台
                Intent mintent = new Intent(this, FastGuildListActivity.class);
                startActivityForResult(mintent, 1);
                break;
        }
    }

    String pingtainame = "";
    String id = "";
    String is_paltfrom = "";
    String table_flag = "";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //  如果请求码与返回码等于预期设置的值  则进行后续操作
        if (requestCode == 1 && resultCode == 2) {
            // 获取返回的数据
            pingtainame = data.getStringExtra("pingtainame");
            id = data.getStringExtra("id");
            //1公会名称布局  2主播布局
            is_paltfrom = data.getStringExtra("is_paltfrom");
            table_flag = data.getStringExtra("table_flag");

            if (is_paltfrom == null) {
                is_paltfrom = 2 + "";
            }
            if (is_paltfrom.equals("1")) {
                rl_guild_id.setVisibility(View.VISIBLE);
                rl_anchor_id.setVisibility(View.GONE);
            } else {
                rl_guild_id.setVisibility(View.GONE);
                rl_anchor_id.setVisibility(View.VISIBLE);
            }
            Log.d("Debug", "返回的平台名称是" + pingtainame + "平台id" + id + "is_paltfrom" + is_paltfrom);

            et_pingtai.setText(pingtainame);
            if (pingtainame.contains("NOW")) {
                rl_now.setVisibility(View.VISIBLE);
                tv_now_content.setVisibility(View.VISIBLE);
            } else {
                rl_now.setVisibility(View.GONE);
                tv_now_content.setVisibility(View.GONE);
            }
        }
    }

    @Override
    protected BindingGuildPresenter createPresenter() {
        return new BindingGuildPresenter(this, this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void requestFailed(String message) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void onSuccess() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void myGuildDtaList(MyGuildListDataBean resultBean) {

    }

    @Override
    public void SupportFromList(SupportFromList bean) {

    }

    //绑定快速公会成功
    @Override
    public void guildUpgrade(AddSeccussBean bean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        EventBus.getDefault().post(new BrowseEventBean());
        showPopupWindowSevenDays(bean.getGuild_id() + "");
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

    private PopupWindow mPopWindowSelectdays;

    /**
     *
     */
    private void showPopupWindowSevenDays(final String guild_id) {
        //设置contentView
        View contentView = LayoutInflater.from(this).inflate(R.layout.new_add_gonghui, null);
        mPopWindowSelectdays = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindowSelectdays.setContentView(contentView);
//        //设置各个控件的点击响应
        TextView tv_content = contentView.findViewById(R.id.tv_content);
        TextView tv_cancle = contentView.findViewById(R.id.tv_cancle);
        TextView tv_next = contentView.findViewById(R.id.tv_next);

        tv_content.setText("恭喜主上，您已成功绑定公会。升级公会，无需添加更多主播，即可直接查看公会所有主播数据。");
        tv_cancle.setText("升级公会");
        tv_next.setText("我先体验");

        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FastBindingActivity.this, UpgradeGuildeActivity.class);
                intent.putExtra("type", "1");
                intent.putExtra("guildNameText", et_guild_name.getText().toString());
                intent.putExtra("guild_id", guild_id);
                startActivity(intent);
                mPopWindowSelectdays.dismiss();
                ActivityManager.getInstance().finishActivity(BindingGuildeTypectivity.class);
                finish();
            }
        });
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopWindowSelectdays.dismiss();
                ActivityManager.getInstance().finishActivity(BindingGuildeTypectivity.class);
                Intent intent = new Intent(FastBindingActivity.this, MyGuildActivity.class);
                finish();

            }
        });
        View rootview = LayoutInflater.from(this).inflate(R.layout.activity_patrol, null);
        mPopWindowSelectdays.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


}
