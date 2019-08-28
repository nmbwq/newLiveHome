package shangri.example.com.shangri.presenter.view;

import shangri.example.com.shangri.model.bean.response.UserRegistrationNext;
import shangri.example.com.shangri.model.bean.response.WebBean;

/**
 * 注册
 * Created by chengaofu on 2017/6/19.
 */

public interface RegisterView extends BaseView {

    void getSMSVerificationCode(); //得到手机校验码

    void checkCode(UserRegistrationNext resultBean); //校验验证码
    void signProtocol(WebBean resultBean); //校验验证码
    void checkPhone(String count);
//    void registerFaild();
}
