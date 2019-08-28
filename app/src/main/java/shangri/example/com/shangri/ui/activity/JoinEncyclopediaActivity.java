package shangri.example.com.shangri.ui.activity;


import android.app.AlertDialog;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.EncyclopediaList;
import shangri.example.com.shangri.model.bean.response.MessageResonse;
import shangri.example.com.shangri.presenter.EncyclopediaListPresenter;
import shangri.example.com.shangri.presenter.EncyclopedialistView;
import shangri.example.com.shangri.ui.dialog.CommontDialog;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.util.KeyBoardUtil;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 我要入住界面
 * Created by admin on 2017/12/22.
 */

public class JoinEncyclopediaActivity extends BaseActivity<EncyclopedialistView, EncyclopediaListPresenter> implements EncyclopedialistView {

    @BindView(R.id.setting_back)
    ImageView settingBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.tv_select_type)
    TextView tvSelectType;
    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.et_user_phone)
    EditText etUserPhone;

    @BindView(R.id.rl_select)
    RelativeLayout rl_select;
    @BindView(R.id.select_item)
    LinearLayout selectItem;
    @BindView(R.id.iv_select)
    ImageView iv_select;


    private ProgressDialogFragment mProgressDialog;

    AlertDialog alertDialog;
    //    入住类型1直播平台2公会3主播
    int type = -1;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_join_encyclopedia;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_join_encyclopedia;
    }

    @Override
    protected void initViewsAndEvents() {
    }

    @Override
    protected EncyclopediaListPresenter createPresenter() {
        return new EncyclopediaListPresenter(this, this);
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

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
//            Log.d("Debug", "到达这里");
//            if (alertDialog.isShowing()) {
//                alertDialog.dismiss();
//                finish();
//            }
//            return false;
//        } else {
//            return super.onKeyDown(keyCode, event);
//        }
//
//    }


    @OnClick({R.id.setting_back, R.id.rl_select, R.id.tv_exit, R.id.ll_zhibo, R.id.ll_gonghui, R.id.ll_zhubo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_back:
                finish();
                break;
            case R.id.rl_select:
//                startActivity(new Intent(JoinEncyclopediaActivity.this, EncyclopediaActivityWebView.class));
                if (selectItem.getVisibility() == View.GONE) {
                    selectItem.setVisibility(View.VISIBLE);
                    iv_select.setImageDrawable(getResources().getDrawable(R.mipmap.xiangshang));

                } else {
                    selectItem.setVisibility(View.GONE);
                    iv_select.setImageDrawable(getResources().getDrawable(R.mipmap.xiangxia));
                }
                break;
            case R.id.tv_exit:
                KeyBoardUtil.closeKeybord(etUserPhone, JoinEncyclopediaActivity.this);
                if (type == -1) {
                    ToastUtil.showShort("请选择入驻类型");
                    return;
                }

                if (etUserName.getText().toString().length() == 0) {
                    ToastUtil.showShort("请输入昵称");
                    return;
                }

                if (etUserPhone.getText().toString().length() == 0) {
                    ToastUtil.showShort("请输入电话号码");
                    return;
                }
                mPresenter.inuser(type, etUserName.getText().toString(), etUserPhone.getText().toString());
                break;
            case R.id.ll_zhibo:
                type = 1;
                selectItem.setVisibility(View.GONE);
                tvSelectType.setText("直播平台");
                iv_select.setImageDrawable(getResources().getDrawable(R.mipmap.xiangxia));
                break;
            case R.id.ll_gonghui:
                type = 2;
                selectItem.setVisibility(View.GONE);
                tvSelectType.setText("公会");
                iv_select.setImageDrawable(getResources().getDrawable(R.mipmap.xiangxia));

                break;
            case R.id.ll_zhubo:
                selectItem.setVisibility(View.GONE);
                type = 3;
                tvSelectType.setText("主播");
                iv_select.setImageDrawable(getResources().getDrawable(R.mipmap.xiangxia));

                break;
        }
    }

    @Override
    public void encyclopediaPlatfromList(EncyclopediaList resultBean) {

    }

    @Override
    public void messageList(MessageResonse resultBean) {

    }

    @Override
    public void wikiFocus(EncyclopediaList resultBean) {

    }

    @Override
    public void addRuzhu() {

        alertDialog = CommontDialog.homeDialog1(JoinEncyclopediaActivity.this, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                finish();
            }
        });
    }

    @Override
    public void wikiDoCollect() {

    }

    @Override
    public void wikiCancelCollect() {

    }

    @Override
    public void messageRead() {

    }

    @Override
    public void consultShare() {

    }
}
