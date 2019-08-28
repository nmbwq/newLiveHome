package shangri.example.com.shangri.presenter.view;

import shangri.example.com.shangri.model.bean.response.AccountDataBean;
import shangri.example.com.shangri.model.bean.response.AddCompanyBean;
import shangri.example.com.shangri.model.bean.response.CertificationBean;
import shangri.example.com.shangri.model.bean.response.NewCompanyBean;
import shangri.example.com.shangri.model.bean.response.VIPCardBean;

public interface WelfareView extends BaseView {

    void addSuccess(AddCompanyBean resultBean);
    void companyAdd(NewCompanyBean resultBean);
    void getVIPPackagesList(VIPCardBean bean);
    void accountData(AccountDataBean dataBean);
    void certificationSuccess(CertificationBean resultBean);

}
