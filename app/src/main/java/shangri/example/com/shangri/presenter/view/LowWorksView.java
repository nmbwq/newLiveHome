package shangri.example.com.shangri.presenter.view;

import shangri.example.com.shangri.model.bean.response.UserInfoBean;
import shangri.example.com.shangri.model.bean.response.WeChatInfoBean;
import shangri.example.com.shangri.model.bean.response.WebBean;
import shangri.example.com.shangri.model.bean.response.companyMyBean;
import shangri.example.com.shangri.model.bean.response.legalIndexBean;

/**
 * 用户登录
 * Created by chengaofu on 2017/6/27.
 */

public interface LowWorksView extends BaseView {

    void legalIndex(legalIndexBean resultBean);

    void legalProtocol(companyMyBean bean);
    void signProcess(companyMyBean bean);
}
