package shangri.example.com.shangri.presenter.view;


import shangri.example.com.shangri.model.bean.request.TopTenBean;
import shangri.example.com.shangri.model.bean.response.DetailBean;

/**
 * 注册
 * Created by chengaofu on 2017/6/27.
 */

public interface DetailedDataView extends BaseView{

    void topDta(TopTenBean resultBean);//角色选择

    void detail(DetailBean resultBean);
}
