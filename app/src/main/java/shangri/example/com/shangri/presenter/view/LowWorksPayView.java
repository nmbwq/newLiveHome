package shangri.example.com.shangri.presenter.view;

import shangri.example.com.shangri.model.bean.response.BossDataBean;
import shangri.example.com.shangri.model.bean.response.IsFaceResponse;
import shangri.example.com.shangri.model.bean.response.NewCompanyBean;
import shangri.example.com.shangri.model.bean.response.PositionListBean;
import shangri.example.com.shangri.model.bean.response.anchorDetailBean;
import shangri.example.com.shangri.model.bean.response.legalIndexBean;
import shangri.example.com.shangri.model.bean.response.legalMypagerBean;
import shangri.example.com.shangri.model.bean.response.sendResumeBean;
import shangri.example.com.shangri.model.bean.response.upListBean;

/**
 * 用户登录
 * Created by chengaofu on 2017/6/27.
 */

public interface LowWorksPayView extends BaseView {

    void legalmyPackage(legalMypagerBean resultBean);

    void positionList(PositionListBean resultBean);

    void sendResume(sendResumeBean resultBean);

    void upList(upListBean resultBean);

    void anchorDetail(anchorDetailBean resultBean);

    void noLikeList(BossDataBean resultBean);


    void companyAdd(NewCompanyBean resultBean);
    void limitNumber(IsFaceResponse resultBean);


    void upAnchor();


}
