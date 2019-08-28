package shangri.example.com.shangri.presenter.view;


import shangri.example.com.shangri.model.bean.response.MyAnchorListBeanResponse;
import shangri.example.com.shangri.model.bean.response.MyAnchorListBeanResponse1;
import shangri.example.com.shangri.model.bean.response.MyTaskBean;
import shangri.example.com.shangri.model.bean.response.timeBean;

/**
 * 注册
 * Created by chengaofu on 2017/6/27.
 */

public interface MyTaskView extends BaseView {

    void mvpExpleme(MyTaskBean resultBean);//角色选择
    void AnchorList(MyAnchorListBeanResponse1 resultBean);//角色选择
    void memberLate(timeBean resultBean);

}
