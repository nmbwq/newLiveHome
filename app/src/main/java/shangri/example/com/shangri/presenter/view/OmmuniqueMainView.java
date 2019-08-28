package shangri.example.com.shangri.presenter.view;


import shangri.example.com.shangri.model.bean.response.CountBean;

/**
 * 注册
 * Created by chengaofu on 2017/6/27.
 */

public interface OmmuniqueMainView extends BaseView{

    void mvpExpleme();//角色选择
    void mineCount(CountBean resultBean);

}
