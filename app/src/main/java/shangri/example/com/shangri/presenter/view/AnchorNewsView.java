package shangri.example.com.shangri.presenter.view;


import shangri.example.com.shangri.model.bean.request.TopTenBean;
import shangri.example.com.shangri.model.bean.response.ManagementChartBean;
import shangri.example.com.shangri.model.bean.response.ManagementDataBean;

/**
 * 注册
 * Created by chengaofu on 2017/6/27.
 */

public interface AnchorNewsView extends BaseView{

    void indexHead(ManagementDataBean resultBean);//角色选择

    void ChartBean(ManagementChartBean resultBean);
    void topDta(TopTenBean resultBean);
}
