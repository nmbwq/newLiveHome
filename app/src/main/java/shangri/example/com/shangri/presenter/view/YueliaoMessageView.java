package shangri.example.com.shangri.presenter.view;


import shangri.example.com.shangri.model.bean.response.MyGuildListDataBean;
import shangri.example.com.shangri.model.bean.response.NewAndroidListDataBean;
import shangri.example.com.shangri.model.bean.response.messageInfoDataBean;
import shangri.example.com.shangri.model.bean.response.mineAnchorListDataBean;

/**
 * 注册
 * Created by chengaofu on 2017/6/27.
 */

public interface YueliaoMessageView extends BaseView {

    void infoDel();
    void setingRead();
    void messageInfo(messageInfoDataBean resultBean);



}
