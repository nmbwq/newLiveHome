package shangri.example.com.shangri.presenter.view;

import shangri.example.com.shangri.model.bean.response.WelfareGuildBean;
import shangri.example.com.shangri.model.bean.response.sendResumeBean;
import shangri.example.com.shangri.model.bean.response.upListBean;

/**
 * Description:
 * Data：2018/11/6-10:27
 * Author: lin
 */
public interface ResumeManageView extends BaseView {
    //主播投递
    void sendResume(sendResumeBean resultBean);
    //已沟通
    void upList(upListBean resultBean);

    void gradeList(WelfareGuildBean resultBean);


    //会长拨打电话、留电话列表接口
    void getGuildList(WelfareGuildBean welfareGuildBean);
}
