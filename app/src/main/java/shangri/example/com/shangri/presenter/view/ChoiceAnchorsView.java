package shangri.example.com.shangri.presenter.view;


import shangri.example.com.shangri.model.bean.response.ChoiceAnchorsBean;
import shangri.example.com.shangri.model.bean.response.NoticesResponseBean;
import shangri.example.com.shangri.model.bean.response.upgradeAlertBean;

/**
 * 注册
 * Created by chengaofu on 2017/6/27.
 */

public interface ChoiceAnchorsView extends BaseView {

    void listAnchorsData(ChoiceAnchorsBean resultBean);//角色选择

    void noticePop(NoticesResponseBean resultBean);//角色选择

    void myNoread(NoticesResponseBean resultBean);//角色选择

    void invitationAlert(NoticesResponseBean resultBean);//角色选择

    void upgradeAlert(upgradeAlertBean resultBean);//角色选择

    void popGives(upgradeAlertBean resultBean);//角色选择

    void givesGet();
    void oncilckBottom();


}
