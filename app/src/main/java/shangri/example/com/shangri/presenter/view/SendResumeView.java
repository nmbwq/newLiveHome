package shangri.example.com.shangri.presenter.view;

import shangri.example.com.shangri.model.bean.response.BossDataBean;
import shangri.example.com.shangri.model.bean.response.CommunicationBean;

public interface SendResumeView   extends   BaseView {

  void  succeed(BossDataBean communicationBean);

}
