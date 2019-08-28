package shangri.example.com.shangri.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.BossPlatBean;
import shangri.example.com.shangri.model.bean.response.IsFaceResponse;
import shangri.example.com.shangri.model.bean.response.NewCompanyBean;
import shangri.example.com.shangri.model.bean.response.changeLightspotBean;
import shangri.example.com.shangri.model.bean.response.welfareListPlatBean;
import shangri.example.com.shangri.presenter.ChairmanRelesePresenter;
import shangri.example.com.shangri.presenter.view.ChairmanReleseView;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 会长发布职位界面
 * Created by mschen on 2017/6/23.
 */

public class ComDescriptionActivity extends BaseActivity<ChairmanReleseView, ChairmanRelesePresenter> implements ChairmanReleseView {


    @BindView(R.id.setting_back)
    ImageView settingBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.et_point)
    EditText etPoint;
    @BindView(R.id.et_des)
    EditText etDes;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    //    String lightspot = "";
    String job_description = "";

    @Override
    protected ChairmanRelesePresenter createPresenter() {
        return new ChairmanRelesePresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
//       lightspot = getIntent().getStringExtra("lightspot");
        job_description = getIntent().getStringExtra("job_description");
//        if (lightspot.length() > 0) {
//            etPoint.setText(lightspot);
//        }
        if (job_description.length() > 0) {
            etDes.setText(job_description);
        }
    }


    @Override
    public void requestFailed(String message) {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_add_companydes_layout;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_add_companydes_layout;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO: add setContentView(...) invocation
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    @Override
    public void companyAdd(NewCompanyBean resultBean) {

    }

    @Override
    public void platfromType(BossPlatBean mAccountDataBean) {

    }

    @Override
    public void welfareList(welfareListPlatBean mAccountDataBean) {

    }

    @Override
    public void changeLightspot(changeLightspotBean mAccountDataBean) {

    }

    @Override
    public void jobAdd() {

    }

    @Override
    public void updatePosition() {

    }

    @Override
    public void legalIsface(IsFaceResponse resultBean) {

    }

    @Override
    public void limitNumber(IsFaceResponse resultBean) {

    }

    @OnClick({R.id.setting_back, R.id.tv_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_back:
                finish();
                break;
            case R.id.tv_commit:
                if (etDes.getText().toString().length() == 0) {
                    ToastUtil.showShort("详细描述不能为空");
                    return;
                }
                //数据是使用Intent返回
                Intent intent = new Intent();
                //把返回数据存入Intent
//                intent.putExtra("lightspot", etPoint.getText().toString());
                intent.putExtra("job_description", etDes.getText().toString());
                //设置返回数据
                setResult(1001, intent);
                //关闭Activity
                finish();
                break;
        }
    }
}
