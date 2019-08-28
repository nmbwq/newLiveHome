package shangri.example.com.shangri.presenter.view;


import shangri.example.com.shangri.model.bean.response.RegigstBean;
import shangri.example.com.shangri.model.bean.response.UserInfoBean;

/**
 * 注册
 * Created by chengaofu on 2017/6/27.
 */

public interface RegisterUserView extends BaseView{

    void registerUser();//两次密码是否一致

    void onLogin(UserInfoBean resultBean);     //登录
    void regiestBean(RegigstBean resultBean);
//    void registerFaild();
}
