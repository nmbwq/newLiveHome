package shangri.example.com.shangri.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseFragment;
import shangri.example.com.shangri.model.bean.response.CompanyMainBean;
import shangri.example.com.shangri.presenter.CompanyMainPresent;
import shangri.example.com.shangri.presenter.view.CompanyMainView;

/**
 * Description:公司介紹
 * Data：2018/11/8-10:44
 * Author: lin
 */
public class CompanyIntroductionFragment extends BaseFragment<CompanyMainView, CompanyMainPresent> implements CompanyMainView {
    @BindView(R.id.tv1)
    TextView tv1;

    @BindView(R.id.tv2)
    TextView tv2;

    @BindView(R.id.company_location)
    TextView company_location;
    @BindView(R.id.company_detail)
    TextView company_detail;
    @Override
    protected int getNormalLayoutId() {
        return R.layout.fragment_company_introduction;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.fragment_company_introduction;
    }
    @Override
    protected CompanyMainPresent createPresenter() {
        return new CompanyMainPresent(getActivity(),this);
    }

    @Override
    protected void initViewsAndEvents() {
        mPresenter.getCompanyMain();
    }

    @Override
    public void getCompanyMain(CompanyMainBean bean) {
        company_location.setText(bean.getCompany().getLocation() + "");
        company_detail.setText(bean.getCompany().getCompany_description() + "");

        tv1.setText("规模:"+bean.getCompany().getCompany_scale());
        tv2.setText("旗下主播:"+bean.getCompany().getAnchor_scale());
    }

    @Override
    public void enterPlatfrom() {

    }

    @Override
    public void setStarAnchor() {

    }

    @Override
    public void upPhotoAlbum() {

    }

    @Override
    public void requestFailed(String message) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
    @Override
    public void deleteImg() {

    }
}
