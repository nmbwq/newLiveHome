package shangri.example.com.shangri.presenter.view;

import shangri.example.com.shangri.model.bean.response.AppVersionBean;

import java.util.List;

/**
 *
 * Created by chengaofu on 2017/7/1.
 */

public interface SettingView extends BaseView{

    void checkVersion(List<AppVersionBean> resultBean); //检查版本

    void logout(); //退出登录
}
