package shangri.example.com.shangri.presenter.view;

import java.util.List;

import shangri.example.com.shangri.model.bean.request.TopTenBean;
import shangri.example.com.shangri.model.bean.response.AppVersionBean;
import shangri.example.com.shangri.model.bean.response.companyAdminListBean;
import shangri.example.com.shangri.model.bean.response.companyBreaklistListBean;
import shangri.example.com.shangri.model.bean.response.companyOrgBean;

/**
 * Created by chengaofu on 2017/7/1.
 */

public interface CreatCompanyView extends BaseView {


    void Create(); //退出登录
    void update(); //退出登录

    void company(companyOrgBean resultBean);//角色选择

    void adminsList(companyAdminListBean resultBean);//管理员列表

    void breaklist(companyBreaklistListBean resultBean);//管理员列表

    void AnchorLeave(); //主播离职
    void companyLizhi(); //管理员离职
    void companyJiebang(); //管理员解绑
    void adminBreakanchor(); //管理员移除直播
    void adminAddanchors(); //管理员增加
}
