package shangri.example.com.shangri.presenter;

import shangri.example.com.shangri.model.bean.response.AccountDataBean;
import shangri.example.com.shangri.model.bean.response.AdDataBean;
import shangri.example.com.shangri.model.bean.response.GrabAnchorBean;
import shangri.example.com.shangri.model.bean.response.GrabAnchorOrderBean;
import shangri.example.com.shangri.model.bean.response.JudgeGradeBean;
import shangri.example.com.shangri.model.bean.response.NewCompanyBean;
import shangri.example.com.shangri.model.bean.response.ResumeDetailBean;
import shangri.example.com.shangri.model.bean.response.ResumeIndexBean;
import shangri.example.com.shangri.model.bean.response.UserInfoBean;
import shangri.example.com.shangri.model.bean.response.WeChatInfoBean;
import shangri.example.com.shangri.model.bean.response.WebBean;
import shangri.example.com.shangri.model.bean.response.chatAnchorResponseBean;
import shangri.example.com.shangri.model.bean.response.sendSucceedResonse;
import shangri.example.com.shangri.model.bean.response.wantListBean;
import shangri.example.com.shangri.presenter.view.BaseView;

/**
 * 用户登录
 * Created by chengaofu on 2017/6/27.
 */

public interface LookAnchorView extends BaseView {
    void leaveAnchor(wantListBean resultBean);

    void getResumeDetailBean(ResumeDetailBean resultBean);

    void companyAdd(NewCompanyBean resultBean);

    void resumeDetail(ResumeIndexBean resultBean);
    void grabAnchor(GrabAnchorBean mAccountDataBean);//
    void judgeGrade(JudgeGradeBean mAccountDataBean);//


    void grabAnchorOrder(GrabAnchorOrderBean mAccountDataBean);//

    void residueNumber();

    void resumeDoCollect();

    void resumeCancelCollect();

    void accountData(AccountDataBean dataBean);

    void guildNumber(sendSucceedResonse resultBean);//

    void chatAnchor(chatAnchorResponseBean resultBean);//
}
