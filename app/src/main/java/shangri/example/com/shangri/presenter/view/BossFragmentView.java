package shangri.example.com.shangri.presenter.view;


import shangri.example.com.shangri.model.bean.response.AccountDataBean;
import shangri.example.com.shangri.model.bean.response.AllListBean;
import shangri.example.com.shangri.model.bean.response.BanagetBean;
import shangri.example.com.shangri.model.bean.response.BossDataBean;
import shangri.example.com.shangri.model.bean.response.BossPlatBean;
import shangri.example.com.shangri.model.bean.response.BossTongGaoBean;
import shangri.example.com.shangri.model.bean.response.CollectBean;
import shangri.example.com.shangri.model.bean.response.GrabAnchorBean;
import shangri.example.com.shangri.model.bean.response.GrabAnchorOrderBean;
import shangri.example.com.shangri.model.bean.response.IsFaceResponse;
import shangri.example.com.shangri.model.bean.response.JudgeGradeBean;
import shangri.example.com.shangri.model.bean.response.NewCompanyBean;
import shangri.example.com.shangri.model.bean.response.PositionListBean;
import shangri.example.com.shangri.model.bean.response.ReadPhotoBean;
import shangri.example.com.shangri.model.bean.response.RecruitDetailBean;
import shangri.example.com.shangri.model.bean.response.RequestListBean;
import shangri.example.com.shangri.model.bean.response.ResumeIndexBean;
import shangri.example.com.shangri.model.bean.response.anchorRecruitListBean;
import shangri.example.com.shangri.model.bean.response.chatAnchorResponseBean;
import shangri.example.com.shangri.model.bean.response.newBossDataBean;
import shangri.example.com.shangri.model.bean.response.sendSucceedResonse;
import shangri.example.com.shangri.model.bean.response.wantListBean;

/**
 * 注册
 * Created by chengaofu on 2017/6/27.
 */

public interface BossFragmentView extends BaseView {

    void recruitList(BossDataBean mAccountDataBean);//

    void NewrecruitList(newBossDataBean mAccountDataBean);//

    void positionList(PositionListBean resultBean);

    void platfromType(BossPlatBean mAccountDataBean);//

    void grabAnchor(GrabAnchorBean mAccountDataBean);//

    void grabAnchorOrder(GrabAnchorOrderBean mAccountDataBean);//

    void resumeAllList(AllListBean resultBean); //管理员申请列表

    void anchorRecruitList(anchorRecruitListBean mAccountDataBean);//

    void conscribeIndex(BossTongGaoBean mAccountDataBean);//

    void wantList(wantListBean mAccountDataBean);//

    void leaveAnchor(wantListBean mAccountDataBean);//

    void resumeState(ResumeIndexBean resultBean);

    void url(BossPlatBean mAccountDataBean);//

    void doCollect();//

    void noLike();//

    void cancelCollect();//

    void sendSucceed(sendSucceedResonse resultBean);//

    void guildNumber(sendSucceedResonse resultBean);//

    void chatAnchor(chatAnchorResponseBean resultBean);//

    void readPhoto(ReadPhotoBean resultBean);

    void resumeIndex(ResumeIndexBean resultBean);

    void anchorShare();

    void anchorMakeTelephone();

    void makeCall(BanagetBean resultBean);

    void surplusMakeCall(BanagetBean resultBean);

    void getCollect(CollectBean resultBean);


    void recruitBanner(BanagetBean resultBean);

    void companyAdd(NewCompanyBean resultBean);

    void judgeGrade(JudgeGradeBean resultBean);


    void residueNumber();

    void resumeDoCollect();

    void resumeCancelCollect();

    void getRecruitDetail(RecruitDetailBean resultBean);

    void limitNumber(IsFaceResponse resultBean);

    void customLink(RequestListBean resultBean);


}
