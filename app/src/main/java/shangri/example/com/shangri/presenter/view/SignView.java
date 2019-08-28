package shangri.example.com.shangri.presenter.view;


import shangri.example.com.shangri.model.bean.response.AddSeccussBean;
import shangri.example.com.shangri.model.bean.response.AmountBean;
import shangri.example.com.shangri.model.bean.response.BossPlatBean;
import shangri.example.com.shangri.model.bean.response.HostBindingBean;
import shangri.example.com.shangri.model.bean.response.HowMakeMoneyBean;
import shangri.example.com.shangri.model.bean.response.InvitationCodeBean;
import shangri.example.com.shangri.model.bean.response.MyGuildListDataBean;
import shangri.example.com.shangri.model.bean.response.SignBean;
import shangri.example.com.shangri.model.bean.response.SupportFromList;
import shangri.example.com.shangri.model.bean.response.everydayMissionBean;
import shangri.example.com.shangri.model.bean.response.legalIndexBean;

/**
 * 注册
 * Created by chengaofu on 2017/6/27.
 */

public interface SignView extends BaseView{

    void signInList(SignBean resultBean);//   上传图片
    void inSign(SignBean resultBean);
    void makeMoney(HowMakeMoneyBean resultBean);
    void  makeMoneyGain();
    void getCredits(AmountBean resultBean);
    void everydayMission(everydayMissionBean resultBean);
    void resumeDraw();

    void givesGet();

    void applyPondition(InvitationCodeBean codeBean);


}
