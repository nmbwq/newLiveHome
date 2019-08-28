package shangri.example.com.shangri.presenter.view;

import shangri.example.com.shangri.model.bean.response.UserInfoBean;

/**
 * 验证码登陆
 * Created by chengaofu on 2017/6/19.
 */

public interface LoginView extends BaseView{

    void getSMSVerificationCode(); //得到手机校验码

    void checkCode();//校验验证码

    void loginCode(UserInfoBean resultBean);//验证码登录
//    void onLoginFailed();
}
