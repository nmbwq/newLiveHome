package shangri.example.com.shangri.presenter.view;


import shangri.example.com.shangri.model.bean.response.TaskDataBean;

/**
 * 注册
 * Created by chengaofu on 2017/6/27.
 */

public interface TeskView extends BaseView {

    void mvpExpleme(TaskDataBean resultBean);//角色选择

    void taskDelete();
}
