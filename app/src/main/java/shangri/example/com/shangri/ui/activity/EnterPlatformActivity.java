package shangri.example.com.shangri.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.adapter.PlatFormAdapter2;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.BossPlatBean;
import shangri.example.com.shangri.presenter.EnterPlatformPresent;
import shangri.example.com.shangri.presenter.view.EnterPlatformView;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.util.InputUtil;
import shangri.example.com.shangri.util.PointUtils;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 添加平台
 */
public class EnterPlatformActivity extends BaseActivity<EnterPlatformView, EnterPlatformPresent> implements EnterPlatformView {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.grid_recyc)
    RecyclerView grid_recyc;
    @BindView(R.id.commit)
    TextView commit;
    @BindView(R.id.et_platform)
    EditText et_platform;
    @BindView(R.id.view_line)
    View view_line;
    List<String> posList = new ArrayList<>();

    PlatFormAdapter2 mAdapter;
    List<BossPlatBean.PlatfromBean> platfromBeanList = new ArrayList<>();
    private ProgressDialogFragment mProgressDialog;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_enter_platform;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_enter_platform;
    }

    @Override
    protected EnterPlatformPresent createPresenter() {
        return new EnterPlatformPresent(this,this);
    }

    @Override
    protected void initViewsAndEvents() {

        grid_recyc.setLayoutManager(new GridLayoutManager(this,3));
        mAdapter = new PlatFormAdapter2(this,R.layout.item_paltform_add,platfromBeanList,new PlatFormAdapter2.IsCheck(){
            @Override
            public void getPosition(int pos, boolean isCheck) {
                if (isCheck){
                    posList.add(pos+"");
                }else {
                    posList.remove(pos+"");
                }
            }
        });
        grid_recyc.setAdapter(mAdapter);
        mPresenter.platformType();
    }

    @Override
    public void platformType(BossPlatBean bossPlatBean) {
        platfromBeanList.addAll(bossPlatBean.getPlatfrom());

        for (int i = 0;i<platfromBeanList.size();i++){
            if (platfromBeanList.get(i).getPlat_name().equals("全平台")){
                platfromBeanList.remove(i);
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    StringBuffer stringBuffer = new StringBuffer();
    private String getCommitList(){
        if (stringBuffer.length()>0)
        stringBuffer.delete(0,stringBuffer.length());
        if (!posList.isEmpty()){
            for (int i = 0;i<posList.size()-1;i++){
                stringBuffer.append(platfromBeanList.get(Integer.parseInt(posList.get(i))).getId()+",");
            }
            stringBuffer.append(platfromBeanList.get(Integer.parseInt(posList.get(posList.size()-1))).getId());
        }else {
            return "";
        }
        return stringBuffer.toString();
    }
    @Override
    public void enterPlatfrom() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
         CompanyHomepageActivity.isRefresh = true;
         ToastUtil.showShort("添加成功");
         finish();
    }

    @OnClick({R.id.back,R.id.commit})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.commit:
                if (PointUtils.isFastClick()) {
                    if (getCommitList().isEmpty()){
                        ToastUtil.showShort("至少选择一个平台");
                    } else{
                        if (mProgressDialog == null) {
                            mProgressDialog = new ProgressDialogFragment();
                        }
                        mProgressDialog.show(this.getSupportFragmentManager());
                        mPresenter.enterPlatfrom(getCommitList(),et_platform.getText().toString());
                    }
                }
                break;
        }
    }

    @Override
    public void requestFailed(String message) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onStart() {
        InputUtil.hideInputMethdView(this,et_platform);
        view_line.requestFocus();
        grid_recyc.requestFocus();
        super.onStart();
    }
}
