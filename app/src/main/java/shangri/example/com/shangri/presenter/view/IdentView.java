package shangri.example.com.shangri.presenter.view;


import shangri.example.com.shangri.model.bean.response.NewCompanyBean;
import shangri.example.com.shangri.model.bean.response.NewTaskDataBean;
import shangri.example.com.shangri.model.bean.response.companyMyBean;

/**
 * 注册
 * Created by chengaofu on 2017/6/27.
 */

public interface IdentView extends BaseView {


    void faceContrast(companyMyBean bean);
    void faceDiscern(companyMyBean bean);
    void legalUploading(companyMyBean bean);

    void licenceDiscern();
    void authIdcard(companyMyBean bean);

    void companyAdd(NewCompanyBean bean);


    void face_num();
    void company_num();


}
