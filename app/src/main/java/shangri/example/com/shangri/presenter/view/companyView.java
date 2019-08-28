package shangri.example.com.shangri.presenter.view;


import shangri.example.com.shangri.model.bean.response.ManagerChectBean;
import shangri.example.com.shangri.model.bean.response.SearchBean;
import shangri.example.com.shangri.model.bean.response.anchorChectBean;
import shangri.example.com.shangri.model.bean.response.companyDetailBean;
import shangri.example.com.shangri.model.bean.response.companyListBean;
import shangri.example.com.shangri.model.bean.response.partSelectBean;
import shangri.example.com.shangri.model.bean.response.personalSearchBean;

/**
 * 公司通讯录
 * Created by chengaofu on 2017/6/27.
 */

public interface companyView extends BaseView {
    void companySearch(companyListBean resultBean);//公司列表

    //
    void companyDetail(companyDetailBean resultBean); //公司详情列表

    //
//
    //申请成为管理员
    void adminApply();

    //    //管理员审核接口
//    void managerCheck();
//执行人搜索
    void partSelect(partSelectBean resultBean);

    void personalSearch(personalSearchBean resultBean);

    void adminSelect(SearchBean resultBean);


}
