package shangri.example.com.shangri.presenter.view;


import shangri.example.com.shangri.model.bean.response.LookMeCompanyBean;
import shangri.example.com.shangri.model.bean.response.ManagerChectBean;
import shangri.example.com.shangri.model.bean.response.NewCompanyBean;
import shangri.example.com.shangri.model.bean.response.anchorChectBean;
import shangri.example.com.shangri.model.bean.response.chatAnchorResponseBean;
import shangri.example.com.shangri.model.bean.response.tellAboutResponseBean;
import shangri.example.com.shangri.model.bean.response.wantListBean;

/**
 * 公司通讯录
 * Created by chengaofu on 2017/6/27.
 */

public interface TellAboutView extends BaseView {
    void chatWithList(tellAboutResponseBean resultBean);
    void sendResume();
    void telephoneWechat();
    void  chatAnchor(chatAnchorResponseBean resultBean);
    void telephoneResumet();

    void dialTelephoneMessage();


    void companyAdd(NewCompanyBean resultBean);

    void leaveAnchor(wantListBean mAccountDataBean);//

}
