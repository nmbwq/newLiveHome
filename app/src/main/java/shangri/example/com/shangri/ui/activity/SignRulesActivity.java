package shangri.example.com.shangri.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
import shangri.example.com.shangri.model.bean.response.SignBean;
import shangri.example.com.shangri.model.bean.response.SupportFromList;
import shangri.example.com.shangri.model.bean.response.legalIndexBean;
import shangri.example.com.shangri.presenter.BindingGuildPresenter;
import shangri.example.com.shangri.presenter.view.BindingGuildView;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;

/**
 * 签到规则
 */

public class SignRulesActivity extends BaseActivity<BindingGuildView, BindingGuildPresenter> implements BindingGuildView {


    @BindView(R.id.tv_sign_number3)
    TextView tvSignNumber3;
    @BindView(R.id.tv_sign_number1)
    TextView tvSignNumber1;
    @BindView(R.id.tv_sign_number2)
    TextView tvSignNumber2;
    @BindView(R.id.tv_sign_number4)
    TextView tvSignNumber4;
    @BindView(R.id.tv_sign_number5)
    TextView tvSignNumber5;
    @BindView(R.id.tv_sign_number6)
    TextView tvSignNumber6;
    @BindView(R.id.tv_sign_number7)
    TextView tvSignNumber7;

    private ProgressDialogFragment mProgressDialog;
    List<SignBean.InvitationTypeBean> list=new ArrayList<>();
    @Override
    protected void initViewsAndEvents() {
        list=(List<SignBean.InvitationTypeBean>)getIntent().getSerializableExtra("list");
        if (list.size()>0){
            tvSignNumber1.setText("+" + list.get(0).getNum() + "");
            tvSignNumber2.setText("+" + list.get(1).getNum() + "");
            tvSignNumber3.setText("+" +list.get(2).getNum() + "");
            tvSignNumber4.setText("+" + list.get(3).getNum() + "");
            tvSignNumber5.setText("+" + list.get(4).getNum() + "");
            tvSignNumber6.setText("+" + list.get(5).getNum() + "");
            tvSignNumber7.setText("+" + list.get(6).getNum() + "");
        }
    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.signrules_layout;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.signrules_layout;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
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


    @OnClick({R.id.setting_back, R.id.tv_title})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_back:
                finish();
                break;
            case R.id.tv_title:
                break;
        }
    }
}
