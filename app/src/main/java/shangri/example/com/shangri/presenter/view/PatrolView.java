package shangri.example.com.shangri.presenter.view;


import shangri.example.com.shangri.model.bean.response.ChoiceGuildBean;
import shangri.example.com.shangri.model.bean.response.LabelDataBean;
import shangri.example.com.shangri.model.bean.response.NewTaskDataBean;
import shangri.example.com.shangri.model.bean.response.PatrolDataBean;
import shangri.example.com.shangri.model.bean.response.companyMyBean;
import shangri.example.com.shangri.model.bean.response.timeBean;

/**
 * 注册
 * Created by chengaofu on 2017/6/27.
 */

public interface PatrolView extends BaseView{

    void PatrolsListData(PatrolDataBean resultBean);//角色选择

    void requestTaskList(NewTaskDataBean resultBean);//角色选择

    void listGuildData(ChoiceGuildBean resultBean);//公会选择
    void companyMy(companyMyBean resultBean);//公会选择
    void inspectDetail(PatrolDataBean.InspectsBean resultBean);//公会选择

    void getalert();//公会选择
    void inspectRead();//公会选择
    void taskAoalert();//公会选择
    void partIn();//公会选择

    void taskRepeal();//公会选择


    void memberLate(timeBean shareBean);


}
