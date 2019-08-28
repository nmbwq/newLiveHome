package shangri.example.com.shangri.presenter.view;

/**
 * 设置新密码
 * Created by chengaofu on 2017/7/2.
 */

public interface SetPwdView extends BaseView{

    void setPwd(String token);
    void bindWx(String token);
}
