package shangri.example.com.shangri.presenter.view;


import shangri.example.com.shangri.model.bean.response.AboutOursBean;
import shangri.example.com.shangri.model.bean.response.RequestListBean;
import shangri.example.com.shangri.model.bean.response.SearchBean;
import shangri.example.com.shangri.model.bean.response.companyDetailBean;
import shangri.example.com.shangri.model.bean.response.companyListBean;
import shangri.example.com.shangri.model.bean.response.partSelectBean;
import shangri.example.com.shangri.model.bean.response.personalSearchBean;

/**
 * 公司通讯录
 * Created by chengaofu on 2017/6/27.
 */

public interface costomerServiceView extends BaseView {

    void vipQaList(RequestListBean resultBean);

    void customLink(RequestListBean resultBean);
    void aboutOurs(AboutOursBean resultBean);


    void vipQaUseful();

}
