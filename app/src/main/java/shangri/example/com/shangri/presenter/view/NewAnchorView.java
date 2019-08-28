package shangri.example.com.shangri.presenter.view;


import shangri.example.com.shangri.model.bean.response.MyGuildListDataBean;
import shangri.example.com.shangri.model.bean.response.NewAndroidListDataBean;
import shangri.example.com.shangri.model.bean.response.NewTaskDataBean;
import shangri.example.com.shangri.model.bean.response.NewsDetailInfoBean;
import shangri.example.com.shangri.model.bean.response.mineAnchorListDataBean;

/**
 * 注册
 * Created by chengaofu on 2017/6/27.
 */

public interface NewAnchorView extends BaseView {

    void anchorList(NewAndroidListDataBean bean);
    void myGuildDtaList(MyGuildListDataBean resultBean);//角色选择
    void mineAnchorList(mineAnchorListDataBean resultBean);


    void anchorDelete();
    void anchorAdd();
}
