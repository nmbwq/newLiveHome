package shangri.example.com.shangri.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.MyGuildListDataBean;
import shangri.example.com.shangri.model.bean.response.NewAndroidListDataBean;
import shangri.example.com.shangri.model.bean.response.mineAnchorListDataBean;
import shangri.example.com.shangri.presenter.NewAnchorPresenter;
import shangri.example.com.shangri.presenter.view.NewAnchorView;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 添加主播
 */

public class AddAnchorActivity extends BaseActivity<NewAnchorView, NewAnchorPresenter> implements NewAnchorView {

    @BindView(R.id.setting_back)
    ImageView settingBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.setting_pwd)
    TextView settingPwd;
    @BindView(R.id.et_pingtai)
    TextView etPingtai;
    @BindView(R.id.iv_select)
    ImageView ivSelect;
    @BindView(R.id.rl_select)
    RelativeLayout rlSelect;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.et_anchor_id)
    EditText etAnchorId;
    @BindView(R.id.rl_anchor_id)
    RelativeLayout rlAnchorId;
    @BindView(R.id.tv_exit)
    TextView tvExit;
    private ProgressDialogFragment mProgressDialog;
    boolean isRemember = false;
    //公会的名称
    String guildNameText = "";
    //公会的id
    String guild_id = "";

    // 1 主播管理界面右上角 2某个公会界面右上角  3在报表过来
    String type = "";

    @Override

    protected void initViewsAndEvents() {
        tvTitle.setText("添加主播");
        type = getIntent().getStringExtra("type");
        guildNameText = getIntent().getStringExtra("guildNameText");
        guild_id = getIntent().getStringExtra("guild_id");
        //报表 页面升级公会可以选择已绑定的快速公会  绑定公会成功界面和公会列表界面进行升级  没有选择的操作
        if (type.equals("2")) {
            ivSelect.setVisibility(View.GONE);
            etPingtai.setText(guildNameText);
        } else {
            ivSelect.setVisibility(View.VISIBLE);
            mPresenter.requestListData();
        }

    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.addanchor_layout;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.addanchor_layout;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("Debug", "到达公会升级的回调方法里面");
        //  如果请求码与返回码等于预期设置的值  则进行后续操作
        if (requestCode == 1 && resultCode == 2) {
            guildNameText = data.getStringExtra("guildNameText");
            guild_id = data.getStringExtra("guild_id");
            etPingtai.setText(guildNameText);
        }
    }

    @Override
    protected NewAnchorPresenter createPresenter() {
        return new NewAnchorPresenter(this, this);
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


    @OnClick({R.id.setting_back, R.id.rl_select, R.id.tv_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_back:
                finish();
                break;
            case R.id.rl_select:
                if (type.equals("2")) {
                } else {
                    Intent intent = new Intent(this, FastGuileSelectListActivity.class);
                    startActivityForResult(intent, 1);
                }
                break;
            case R.id.tv_exit:

                if (TextUtils.isEmpty(guild_id)) {
                    Toast.makeText(this, "请选择或输入公会名称", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(etPingtai.getText().toString().trim())) {
                    Toast.makeText(this, "请选择或输入公会名称", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(etAnchorId.getText().toString().trim())) {
                    Toast.makeText(this, "请输入主播id，多个用;分开", Toast.LENGTH_SHORT).show();
                    return;
                }

                //销毁到前两个界面
                if (mProgressDialog == null) {
                    mProgressDialog = new ProgressDialogFragment();
                }
                mProgressDialog.show(getSupportFragmentManager());

                ToastUtil.showShort("到达可以请求数据的时候");
                mPresenter.anchorAdd(guild_id, etAnchorId.getText().toString().trim());
                break;
        }
    }


    @Override
    public void anchorList(NewAndroidListDataBean bean) {

    }

    @Override
    public void myGuildDtaList(MyGuildListDataBean resultBean) {
        if (resultBean.getQuick_gulid().size() > 0) {
            MyGuildListDataBean.GuildsBean guildsBean = resultBean.getQuick_gulid().get(0);
            etPingtai.setText(guildsBean.getGuild_name());
            guild_id = guildsBean.getGuild_id();
        }
    }

    @Override
    public void mineAnchorList(mineAnchorListDataBean resultBean) {

    }

    @Override
    public void anchorDelete() {

    }

    @Override
    public void anchorAdd() {
        if (mProgressDialog != null && mProgressDialog.isResumed()) {
            mProgressDialog.dismiss();
        }
        finish();
        ToastUtil.showShort("添加成功");
    }
}
