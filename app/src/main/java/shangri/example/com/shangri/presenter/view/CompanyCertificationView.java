package shangri.example.com.shangri.presenter.view;

import shangri.example.com.shangri.model.bean.response.CertificationBean;
import shangri.example.com.shangri.model.bean.response.CompanyMainBean;

public interface CompanyCertificationView   extends   BaseView{
//    void getCompanyMain(CompanyMainBean resultBean);

    void certificationSuccess(CertificationBean resultBean);
}
