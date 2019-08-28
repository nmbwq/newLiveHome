package shangri.example.com.shangri.presenter.view;


import shangri.example.com.shangri.model.bean.response.ManagementDataBean;
import shangri.example.com.shangri.model.bean.response.companyMyBean;
import shangri.example.com.shangri.model.bean.response.timeBean;

/**
 * 注册
 * Created by chengaofu on 2017/6/27.
 */

public interface ManagementMainView extends BaseView {

    void managementData(ManagementDataBean resultBean);//角色选择

    void companyMy(companyMyBean shareBean);

    void memberLate(timeBean shareBean);

}
