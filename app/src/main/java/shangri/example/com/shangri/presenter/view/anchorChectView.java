package shangri.example.com.shangri.presenter.view;


import shangri.example.com.shangri.model.bean.response.AllListBean;
import shangri.example.com.shangri.model.bean.response.GrabAnchorBean;
import shangri.example.com.shangri.model.bean.response.GrabAnchorOrderBean;
import shangri.example.com.shangri.model.bean.response.ListPhoenBean;
import shangri.example.com.shangri.model.bean.response.LookMeCompanyBean;
import shangri.example.com.shangri.model.bean.response.ManagementDataBean;
import shangri.example.com.shangri.model.bean.response.ManagerChectBean;
import shangri.example.com.shangri.model.bean.response.anchorChectBean;

/**
 * 公司通讯录
 * Created by chengaofu on 2017/6/27.
 */

public interface anchorChectView extends BaseView {
    void anchorApplys(anchorChectBean resultBean);//主播申请列表

    void managerApplys(ManagerChectBean resultBean); //管理员申请列表

    void viewCompany(LookMeCompanyBean resultBean); //管理员申请列表


    //审核接口
    void anchorCheck();
    //管理员审核接口
    void managerCheck();
}
