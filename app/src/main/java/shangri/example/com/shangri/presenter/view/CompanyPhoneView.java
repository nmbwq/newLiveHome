package shangri.example.com.shangri.presenter.view;


import shangri.example.com.shangri.model.bean.response.ListPhoenBean;
import shangri.example.com.shangri.model.bean.response.MyAnchorListBeanResponse;
import shangri.example.com.shangri.model.bean.response.MyTaskBean;

/**
 * 公司通讯录
 * Created by chengaofu on 2017/6/27.
 */

public interface CompanyPhoneView extends BaseView {


    void companyContacts(ListPhoenBean resultBean);//返回公司通讯录列表
    void companyAddContacts();
    void companyDelcontact();

}
