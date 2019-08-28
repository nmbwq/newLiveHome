package shangri.example.com.shangri.presenter.view;


import shangri.example.com.shangri.model.bean.response.MyGuildListDataBean;
import shangri.example.com.shangri.model.bean.response.SupportFromList;
import shangri.example.com.shangri.model.bean.response.timeBean;

/**
 * 注册
 * Created by chengaofu on 2017/6/27.
 */

public interface MyGuildView extends BaseView{

    void myGuildDtaList(MyGuildListDataBean resultBean);//角色选择

    void memberLate(timeBean resultBean);
    void guildRatio();

    void  SupportFromList(SupportFromList resultBean);


}
