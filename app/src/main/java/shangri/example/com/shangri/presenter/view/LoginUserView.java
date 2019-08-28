package shangri.example.com.shangri.presenter.view;

import shangri.example.com.shangri.model.bean.response.AdDataBean;
import shangri.example.com.shangri.model.bean.response.UserInfoBean;
import shangri.example.com.shangri.model.bean.response.WeChatInfoBean;
import shangri.example.com.shangri.model.bean.response.WebBean;

/**
 * 用户登录
 * Created by chengaofu on 2017/6/27.
 */

public interface LoginUserView extends BaseView {

    void onLogin(UserInfoBean resultBean);

    void loginWX(WeChatInfoBean resultBean);

    void signProtocol(WebBean resultBean);

    void memberLogin();

    void adIndex(AdDataBean resultBean);

    void  privaryDeal(AdDataBean resultBean);

//    void onLoginFailed();
}
