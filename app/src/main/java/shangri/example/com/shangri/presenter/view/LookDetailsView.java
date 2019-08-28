package shangri.example.com.shangri.presenter.view;


import shangri.example.com.shangri.model.bean.response.LookDetailBean;

/**
 * 注册
 * Created by chengaofu on 2017/6/27.
 */

public interface LookDetailsView extends BaseView{

    void onDetails(LookDetailBean resultBean);//角色选择

}
