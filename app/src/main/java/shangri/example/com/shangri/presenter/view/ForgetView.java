package shangri.example.com.shangri.presenter.view;

/**
 * Created by mschen on 2017/6/28.
 */

public interface ForgetView extends BaseView{
    void getSMSVerificationCode(); //得到手机校验码

    void checkCode();//校验验证码

//    void forgetFailed();
}
