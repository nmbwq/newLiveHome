package shangri.example.com.shangri.api;

import java.io.Serializable;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import shangri.example.com.shangri.model.bean.request.AddGoodBean;
import shangri.example.com.shangri.model.bean.request.AddInfo;
import shangri.example.com.shangri.model.bean.request.AddNewBean;
import shangri.example.com.shangri.model.bean.request.AddPhoneBean;
import shangri.example.com.shangri.model.bean.request.AddPlatfrom;
import shangri.example.com.shangri.model.bean.request.AddStarBean;
import shangri.example.com.shangri.model.bean.request.AddTaskBean;
import shangri.example.com.shangri.model.bean.request.AnchorChectRequestBean;
import shangri.example.com.shangri.model.bean.request.AnchorOrder;
import shangri.example.com.shangri.model.bean.request.AnchorsBean;
import shangri.example.com.shangri.model.bean.request.BannerBean;
import shangri.example.com.shangri.model.bean.request.BannerDetalBean;
import shangri.example.com.shangri.model.bean.request.BaseBeen;
import shangri.example.com.shangri.model.bean.request.BaseRequestEntity;
import shangri.example.com.shangri.model.bean.request.BindWXBean;
import shangri.example.com.shangri.model.bean.request.BindingGuildBean;
import shangri.example.com.shangri.model.bean.request.BossBeen;
import shangri.example.com.shangri.model.bean.request.CheckCodeBean;
import shangri.example.com.shangri.model.bean.request.Collect;
import shangri.example.com.shangri.model.bean.request.CollectRequestBean;
import shangri.example.com.shangri.model.bean.request.CommentBean;
import shangri.example.com.shangri.model.bean.request.CompanyAddBean;
import shangri.example.com.shangri.model.bean.request.CompanyRequestBean;
import shangri.example.com.shangri.model.bean.request.CreatCompanyBean;
import shangri.example.com.shangri.model.bean.request.FeedbackBean;
import shangri.example.com.shangri.model.bean.request.GetSMSVerificationCodeBean;
import shangri.example.com.shangri.model.bean.request.GivesGetBean;
import shangri.example.com.shangri.model.bean.request.HeadCollect;
import shangri.example.com.shangri.model.bean.request.IdIdentBean;
import shangri.example.com.shangri.model.bean.request.ImageBean;
import shangri.example.com.shangri.model.bean.request.InformationBean;
import shangri.example.com.shangri.model.bean.request.InterBBean;
import shangri.example.com.shangri.model.bean.request.JoinGuildBean;
import shangri.example.com.shangri.model.bean.request.LabelBean;
import shangri.example.com.shangri.model.bean.request.LaunchPatrolBean;
import shangri.example.com.shangri.model.bean.request.LoginBean;
import shangri.example.com.shangri.model.bean.request.LoginCodeBean;
import shangri.example.com.shangri.model.bean.request.LookBean;
import shangri.example.com.shangri.model.bean.request.ManagementBean;
import shangri.example.com.shangri.model.bean.request.ManagerChectRequestBean;
import shangri.example.com.shangri.model.bean.request.MessageBean;
import shangri.example.com.shangri.model.bean.request.MineFragmentBeen;
import shangri.example.com.shangri.model.bean.request.MoneyGainBean;
import shangri.example.com.shangri.model.bean.request.MyAnchorListBean;
import shangri.example.com.shangri.model.bean.request.MyTaskZhuBean;
import shangri.example.com.shangri.model.bean.request.NewTaskBean;
import shangri.example.com.shangri.model.bean.request.NewsDetailBean;
import shangri.example.com.shangri.model.bean.request.NewsListBean;
import shangri.example.com.shangri.model.bean.request.NormalRequestBean;
import shangri.example.com.shangri.model.bean.request.OncilckBottomBean;
import shangri.example.com.shangri.model.bean.request.ParticipateTaskBean;
import shangri.example.com.shangri.model.bean.request.ParticipationBean;
import shangri.example.com.shangri.model.bean.request.PatrolBean;
import shangri.example.com.shangri.model.bean.request.PayrequestBean;
import shangri.example.com.shangri.model.bean.request.PeiCollect;
import shangri.example.com.shangri.model.bean.request.PersonalData;
import shangri.example.com.shangri.model.bean.request.Praise;
import shangri.example.com.shangri.model.bean.request.RegisterUserBean;
import shangri.example.com.shangri.model.bean.request.RegisterUserBean1;
import shangri.example.com.shangri.model.bean.request.ReqAnchoDetailBean;
import shangri.example.com.shangri.model.bean.request.ReqReportBean;
import shangri.example.com.shangri.model.bean.request.ReqShare;
import shangri.example.com.shangri.model.bean.request.RequestLeaveBean;
import shangri.example.com.shangri.model.bean.request.ResetPwdBean;
import shangri.example.com.shangri.model.bean.request.ResumeAddBean;
import shangri.example.com.shangri.model.bean.request.ResumeDelBean;
import shangri.example.com.shangri.model.bean.request.SearchNewsBean;
import shangri.example.com.shangri.model.bean.request.SetPwdBean;
import shangri.example.com.shangri.model.bean.request.SofwwareUserPresenterBeen;
import shangri.example.com.shangri.model.bean.request.TaskDeleteBean;
import shangri.example.com.shangri.model.bean.request.TaskResponseBean;
import shangri.example.com.shangri.model.bean.request.TelBean;
import shangri.example.com.shangri.model.bean.request.TipsBean;
import shangri.example.com.shangri.model.bean.request.TipsDetailBean;
import shangri.example.com.shangri.model.bean.request.TopTenBean;
import shangri.example.com.shangri.model.bean.request.UpdatePwdBean;
import shangri.example.com.shangri.model.bean.request.UpdateTaskBean;
import shangri.example.com.shangri.model.bean.request.UpdateUserBean;
import shangri.example.com.shangri.model.bean.request.UploadCover;
import shangri.example.com.shangri.model.bean.request.WeChatBean;
import shangri.example.com.shangri.model.bean.request.addRuzhuBean;
import shangri.example.com.shangri.model.bean.request.alertBean;
import shangri.example.com.shangri.model.bean.request.deletePlatfrom;
import shangri.example.com.shangri.model.bean.request.encyclopediaRequestBean;
import shangri.example.com.shangri.model.bean.request.encyclopediaRequestBean1;
import shangri.example.com.shangri.model.bean.request.partSelectRequestBean;
import shangri.example.com.shangri.model.bean.request.positionAddBean;
import shangri.example.com.shangri.model.bean.request.tellAboutRequestBean;
import shangri.example.com.shangri.model.bean.request.upgradeBean;
import shangri.example.com.shangri.model.bean.request.wikiCollectBean;
import shangri.example.com.shangri.model.bean.response.*;
import shangri.example.com.shangri.presenter.MyGuildBean;
import shangri.example.com.shangri.model.bean.response.WelfareGuildBean;

/**
 * Created by chengaofu on 2017/6/27.
 */
public interface RxService {
    //注册获取短信验证码
//    @POST("api/sms/signup")
    //分享
    @POST("api/report/index/share")
    Observable<BaseResponseEntity<ResShareBean>> onShare(
            @Body ReqShare entity);

    @POST("api/report/index/earnings")
    Observable<BaseResponseEntity<RespAdminBean>> reportMagagerData(
            @Body ReqReportBean entity);

    @POST("api/sign/get/verify")
    Observable<BaseResponseEntity<Object>> getSMSVerificationCode(
            @Body GetSMSVerificationCodeBean entity);


    //手机号是否注册
    @POST("api/sign/telephone/count")
    Observable<BaseResponseEntity<TelResposeBean>> checkPhone(
            @Body TelBean entity);

    //忘记获取短信验证码
    @POST("api/sms/forget")
    Observable<BaseResponseEntity<Object>> getSMSForgetCode(
            @Body GetSMSVerificationCodeBean entity);

    //注册下一步
    @POST("api/sign/verify/code")
    Observable<BaseResponseEntity<UserRegistrationNext>> checkCode(
            @Body CheckCodeBean entity);

    //设置密码
    @POST("api/sign/register")
    Observable<BaseResponseEntity<RegigstBean>> registerUser(
            @Body RegisterUserBean entity);

    //设置密码
    @POST("api/register/resetpwd")
    Observable<BaseResponseEntity<Object>> registerUser1(
            @Body RegisterUserBean1 entity);

    //选择角色
    @POST("api/choice/role")
    Observable<BaseResponseEntity<Object>> getRole(
            @Body SofwwareUserPresenterBeen entity);


    //个人名片
    @POST("api/mine/personal/detail")
    Observable<BaseResponseEntity<CardRequestBean>> personalPetail(
            @Body SofwwareUserPresenterBeen entity);


    //个人名片
    @POST("api/mine/other/detail")
    Observable<BaseResponseEntity<CardRequestBean>> otherDetail(
            @Body SofwwareUserPresenterBeen entity);

    //登陆
    @POST("api/sign/login")
    Observable<BaseResponseEntity<UserInfoBean>> onLogin(
            @Body LoginBean entity);


    //法务首页接口
    @POST("api/legal/index")
    Observable<BaseResponseEntity<legalIndexBean>> legalIndex(
            @Body LaunchPatrolBean entity);


    //简历上传图片接口
    @POST("api/resume/uploading")
    Observable<BaseResponseEntity<legalIndexBean>> resumeUploading(
            @Body LaunchPatrolBean entity);


    //添加简历信息接口
    @POST("api/resume/new/add")
    Observable<BaseResponseEntity<Object>> resumeAdd(
            @Body ResumeAddBean entity);


    //简历照片删除
    @POST("api/resume/photo/del")
    Observable<BaseResponseEntity<Object>> resumePhotoDel(
            @Body ResumeDelBean entity);

    //简历设置封面
    @POST("api/resume/photo/first")
    Observable<BaseResponseEntity<Object>> resumePhotoFirst(
            @Body ResumeDelBean entity);

    //添加公司接口
    @POST("api/guild/company/add")
    Observable<BaseResponseEntity<NewCompanyBean>> companyAdd(
            @Body CompanyAddBean entity);

    //编辑简历
    @POST("api/resume/new/update")
    Observable<BaseResponseEntity<Object>> resumeUpdate(
            @Body ResumeAddBean entity);


    //获取简历图片接口
    @POST("api/resume/read/photo")
    Observable<BaseResponseEntity<ReadPhotoBean>> readPhoto(
            @Body ResumeAddBean entity);


    //简历首页
    @POST("api/resume/index")
    Observable<BaseResponseEntity<ResumeIndexBean>> resumeIndex(
            @Body ResumeAddBean entity);

    //有简历web页面进行请求数据
    @POST("api/timing/resume/detail")
    Observable<BaseResponseEntity<ResumeIndexBean>> resumeDetail(
            @Body ResumeAddBean entity);


    //获取简历状态
    @POST("api/resume/status")
    Observable<BaseResponseEntity<ResumeIndexBean>> resumeState(
            @Body ResumeAddBean entity);


    //法务我的套餐列表
    @POST("api/legal/my_package")
    Observable<BaseResponseEntity<legalMypagerBean>> legalmyPackage(
            @Body LaunchPatrolBean entity);


    //不考虑职位列表
    @POST("api/recruit/no/like/list")
    Observable<BaseResponseEntity<BossDataBean>> noLikeList(
            @Body LaunchPatrolBean entity);


    //职位列表接口
    @POST("api/guild/position/list")
    Observable<BaseResponseEntity<PositionListBean>> positionList(
            @Body LaunchPatrolBean entity);


    //会长沟通列表接口
    @POST("api/guild/link/up/list")
    Observable<BaseResponseEntity<upListBean>> upList(
            @Body LaunchPatrolBean entity);

    //会长已抢ta列表
    @POST("api/welfare/grade/list")
    Observable<BaseResponseEntity<WelfareGuildBean>> gradeList(
            @Body LaunchPatrolBean entity);

    //主播投递接口
    @POST("api/anchor/send/resume")
    Observable<BaseResponseEntity<sendResumeBean>> sendResume(
            @Body LaunchPatrolBean entity);

    //职位详情接口
    @POST("api/acquirement/anchor/detail")
    Observable<BaseResponseEntity<anchorDetailBean>> anchorDetail(
            @Body LaunchPatrolBean entity);


    //会长沟通主播接口
    @POST("api/guild/link/up/anchor")
    Observable<BaseResponseEntity<Object>> upAnchor(
            @Body LaunchPatrolBean entity);

    //是否查看接口
    @POST("api/resume/whether/check")
    Observable<BaseResponseEntity<Object>> whetherCheck(
            @Body LaunchPatrolBean entity);


    //微信登陆
    @POST("api/sign/login/wxa")
    Observable<BaseResponseEntity<WeChatInfoBean>> loginWX(
            @Body WeChatBean entity);

    //    请求web界面
    @POST("api/sign/protocol")
    Observable<BaseResponseEntity<WebBean>> signProtocol(
            @Body WeChatBean entity);

    //忘记密码进行短信校验
    @POST("api/sign/get/verify")
    Observable<BaseResponseEntity<ForgetBean>> onForgetPass(
            @Body CheckCodeBean entity);

    //忘记密码进行设置密码
    @POST("api/sign/forget")
    Observable<BaseResponseEntity<Object>> resetPassword(
            @Body ResetPwdBean entity);

    //更新最后一次登陆时间
    @POST("api/user/update/logintime")
    Observable<BaseResponseEntity<Object>> memberLogin(
            @Body ResetPwdBean entity);

    //广告页
    @POST("api/timing/ad/index")
    Observable<BaseResponseEntity<AdDataBean>> adIndex(
            @Body ResetPwdBean entity);


    //服务保密协议接口
    @POST("api/sign/service/privary/deal")
    Observable<BaseResponseEntity<AdDataBean>> privaryDeal(
            @Body ResetPwdBean entity);

    //我的
    @POST("api/common/register/info")
    Observable<BaseResponseEntity<AccountDataBean>> accountData(
            @Body MineFragmentBeen entity);

    //招聘列表接口
    @POST("api/recruit/list")
    Observable<BaseResponseEntity<BossDataBean>> recruitList(
            @Body BossBeen entity);

    //游客招聘列表接口
    @POST("api/timing/recruit/list")
    Observable<BaseResponseEntity<BossDataBean>> timingRecruitList(
            @Body BossBeen entity);


    //新版职位推荐列表
    @POST("api/timing/recruit/recommend/list")
    Observable<BaseResponseEntity<newBossDataBean>> NewTimingRecruitList(
            @Body BossBeen entity);


    //直播平台、直播类型接口
    @POST("api/recruit/platfrom/type")
    Observable<BaseResponseEntity<BossPlatBean>> platfromType(
            @Body BossBeen entity);


    //求职状态接口
    @POST("api/resume/wanted/status")
    Observable<BaseResponseEntity<wantedStatusBean>> wantedStatus(
            @Body BossBeen entity);


    //游客直播平台、直播类型接口
    @POST("api/timing/platfrom/type")
    Observable<BaseResponseEntity<BossPlatBean>> timingPlatfromType(
            @Body BossBeen entity);


    //主播招聘列表导航接口
    @POST("api/timing/anchor/recruit/list")
    Observable<BaseResponseEntity<anchorRecruitListBean>> anchorRecruitList(
            @Body BossBeen entity);


    //获取求职列表
//    @POST("api/timing/want/list")
//    新版求职列表推荐
    @POST("api/resume/recommend/list")
    Observable<BaseResponseEntity<wantListBean>> wantList(
            @Body BossBeen entity);


    //会长给主播留电话接口(1查看剩余次数 2留电话(需传下面参数) 默认1)
    @POST("api/recruit/guild/leave/anchor")
    Observable<BaseResponseEntity<wantListBean>> leaveAnchor(
            @Body BossBeen entity);

    //修改职位信息接口
    @POST("api/guild/update/position/info")
    Observable<BaseResponseEntity<Object>> updatePosition(
            @Body positionAddBean entity);

    //发布职位接口
    @POST("api/guild/job/add")
    Observable<BaseResponseEntity<Object>> jobAdd(
            @Body positionAddBean entity);

    //福利列表接口
    @POST("api/welfare/list")
    Observable<BaseResponseEntity<welfareListPlatBean>> welfareList(
            @Body BossBeen entity);

    //职位亮点
    @POST("api/recruit/change/lightspot")
    Observable<BaseResponseEntity<changeLightspotBean>> changeLightspot(
            @Body BossBeen entity);

    //招募首页接口
    @POST("api/conscribe/index")
    Observable<BaseResponseEntity<BossTongGaoBean>> conscribeIndex(
            @Body BossBeen entity);

    //游客招募首页接口
    @POST("api/timing/conscribe/index")
    Observable<BaseResponseEntity<BossTongGaoBean>> timingConscribeIndex(
            @Body BossBeen entity);


    //招聘页安全提示
    @POST("api/sign/recruit/page")
    Observable<BaseResponseEntity<BossPlatBean>> recruitPage(
            @Body BossBeen entity);


    //招聘收藏接口
    @POST("api/recruit/do/collect")
    Observable<BaseResponseEntity<Object>> doCollect(
            @Body BossBeen entity);

    //不考虑接口
    @POST("api/recruit/no/like")
    Observable<BaseResponseEntity<Object>> noLike(
            @Body BossBeen entity);

    //招聘取消收藏接口
    @POST("api/recruit/cancel/collect")
    Observable<BaseResponseEntity<Object>> cancelCollect(
            @Body BossBeen entity);


    //预留电话以及发送形象卡片
    @POST("api/resume/send/succeed")
    Observable<BaseResponseEntity<sendSucceedResonse>> sendSucceed(
            @Body BossBeen entity);


    //会长约聊剩余次数接口
    @POST("api/swop/guild/number")
    Observable<BaseResponseEntity<sendSucceedResonse>> guildNumber(
            @Body BossBeen entity);


    //主播分享接口
    @POST("api/recruit/anchor/share")
    Observable<BaseResponseEntity<Object>> anchorShare(
            @Body BossBeen entity);


    //主播拨打电话接口
    @POST("api/recruit/anchor/make/telephone")
    Observable<BaseResponseEntity<Object>> anchorMakeTelephone(
            @Body BossBeen entity);


    //修改密码
    @POST("api/mine/modifypwd")
    Observable<BaseResponseEntity<Object>> updatePwd(
            @Body UpdatePwdBean entity);

    //上传图片||头像上传
    @POST("api/mine/upload/avatar")
    Observable<BaseResponseEntity<ImageUpload>> imageUpload(
            @Body ImageBean entity);

    //用户反馈
    @POST("api/mine/question")
    Observable<BaseResponseEntity<Object>> uploadFeedBcakContent(
            @Body FeedbackBean entity);


    //主播邀请详细列表
    @POST("api/invitation/my/invite")
    Observable<BaseResponseEntity<InviteListDataBean>> invitationMyInvite(
            @Body FeedbackBean entity);


    //近7天收益接口
    @POST("api/task/seven/day/earnings")
    Observable<BaseResponseEntity<SevenDayEarningsBean>> sevenDayEarnings(
            @Body FeedbackBean entity);


    //提现记录列表接口
    @POST("api/invitation/user/price/list")
    Observable<BaseResponseEntity<PriceListBean>> userPriceList(
            @Body FeedbackBean entity);

    //我的公会
    @POST("api/mine/guild")
    Observable<BaseResponseEntity<MyGuildListDataBean>> getGuildList(
            @Body BaseBeen entity);

    //信息列表接口
    @POST("api/swop/message/info")
    Observable<BaseResponseEntity<messageInfoDataBean>> messageInfo(
            @Body FeedbackBean entity);


    //签到列表
    @POST("api/register/sign/in/list")
    Observable<BaseResponseEntity<SignBean>> signInList(
            @Body BaseBeen entity);


    //立即签到接口
    @POST("api/register/sign/in/sign")
    Observable<BaseResponseEntity<SignBean>> inSign(
            @Body BaseBeen entity);
    //主播赚钱接口(新版本)
    @POST("api/anchor/how/make/money")
    Observable<BaseResponseEntity<HowMakeMoneyBean>> makeMoney(
            @Body BaseBeen entity);

    //赚钱页面领取波币接口
    @POST("api/anchor/how/make/money/gain")
    Observable<BaseResponseEntity<Object>> makeMoneyGain(
            @Body MoneyGainBean entity);

    //绑定公会  选择平台
    @POST("api/common/select/platfrom")
    Observable<BaseResponseEntity<SortModelBean>> getBindingListData(
            @Body BaseBeen entity);

    //绑定公会  快速绑定选择平台
    @POST("api/common/select/quick/platfrom")
    Observable<BaseResponseEntity<SortModelBean>> quickPlatfrom(
            @Body BaseBeen entity);

    //资讯首页
    @POST("api/read/train/index")
    Observable<BaseResponseEntity<BannerHomeLookBean>> article(
            @Body BannerDetalBean entity);

    //百科咨询
    @POST("api/read/wiki/index")
    Observable<BaseResponseEntity<EncyclopediaHomeBean>> getEncyclopediaDate(
            @Body BannerDetalBean entity);


    //游客身份咨询百科
    @POST("api/timing/wiki/index")
    Observable<BaseResponseEntity<EncyclopediaHomeBean>> timingWikiIndex(
            @Body BannerDetalBean entity);


    //巡查列表
    @POST("api/inspect/list")
    Observable<BaseResponseEntity<PatrolDataBean>> getPartolList(
            @Body PatrolBean entity);

    //任务列表
    @POST("api/task/list")
    Observable<BaseResponseEntity<NewTaskDataBean>> getTaskList(
            @Body NewTaskBean entity);


    //他人辅导列表
    @POST("api/inspect/other/list")
    Observable<BaseResponseEntity<PatrolDataBean>> otherList(
            @Body PatrolBean entity);


    //巡查列表提醒查看
    @POST("api/inspect/toalert")
    Observable<BaseResponseEntity<Object>> getAlite(
            @Body alertBean entity);

    //任务撤销
    @POST("api/task/repeal")
    Observable<BaseResponseEntity<Object>> taskRepeal(
            @Body alertBean entity);

    //任务列表提醒查看
    @POST("api/task/toalert")
    Observable<BaseResponseEntity<Object>> taskAoalert(
            @Body alertBean entity);

    //任务列表提醒查看
    @POST("api/task/part/in")
    Observable<BaseResponseEntity<Object>> partIn(
            @Body alertBean entity);


    //主播删除
    @POST("api/mine/guild/anchor/delete")
    Observable<BaseResponseEntity<Object>> anchorDelete(
            @Body alertBean entity);


    //聊天消息设置已读接口
    @POST("api/swop/message/info/del")
    Observable<BaseResponseEntity<Object>> infoDel(
            @Body alertBean entity);


    //聊天消息设置已读接口
    @POST("api/swop/message/seting/read")
    Observable<BaseResponseEntity<Object>> setingRead(
            @Body alertBean entity);

    //立即升级接口
    @POST("api/broker/hand/upgrade")
    Observable<BaseResponseEntity<Object>> handUpgrade(
            @Body alertBean entity);

    //主播移除
    @POST(" api/mine/anchor/delete")
    Observable<BaseResponseEntity<Object>> mineAnchorDelete(
            @Body alertBean entity);


    //添加主播
    @POST("api/mine/guild/add/anchor")
    Observable<BaseResponseEntity<Object>> anchorAdd(
            @Body alertBean entity);

    //相似度对比
    @POST("api/legal/face/contrast")
    Observable<BaseResponseEntity<companyMyBean>> faceContrast(
            @Body alertBean entity);

    //营业执照认证存储
    @POST("api/legal/licence/discern")
    Observable<BaseResponseEntity<Object>> licenceDiscern(
            @Body alertBean entity);

    //姓名和身份证号是否一直
    @POST("api/legal/auth/idcard")
    Observable<BaseResponseEntity<companyMyBean>> authIdcard(
            @Body alertBean entity);


    //人脸认证剩余次数
    @POST("api/legal/auth/face_num")
    Observable<BaseResponseEntity<Object>> face_num(
            @Body alertBean entity);

    //企业认证剩余次数
    @POST("api/legal/auth/company_num")
    Observable<BaseResponseEntity<Object>> company_num(
            @Body alertBean entity);

    //识别存储
    @POST("api/legal/face/discern")
    Observable<BaseResponseEntity<companyMyBean>> faceDiscern(
            @Body IdIdentBean entity);


    //法务照片缓存
    @POST("api/legal/uploading")
    Observable<BaseResponseEntity<companyMyBean>> legalUploading(
            @Body IdIdentBean entity);

    //免责协议
    @POST("api/legal/protocol")
    Observable<BaseResponseEntity<companyMyBean>> legalProtocol(
            @Body IdIdentBean entity);


    //职位升级套餐
    @POST("api/recruit/upgrade/package")
    Observable<BaseResponseEntity<upgradePackageBean>> upgradePackage(
            @Body BaseBeen entity);

    //职位升级
    @POST(" api/recruit/upgrade")
    Observable<BaseResponseEntity<Object>> recruitUpgrade(
            @Body upgradeBean entity);


    //签署流程返回地址
    @POST("api/legal/sign/process")
    Observable<BaseResponseEntity<companyMyBean>> signProcess(
            @Body IdIdentBean entity);


    //获取任务详情
    @POST("api/task/detail")
    Observable<BaseResponseEntity<NewTaskDataBean.TasksBean>> taskDetail(
            @Body alertBean entity);

    //主播列表接口
    @POST("api/mine/guild/anchor/list")
    Observable<BaseResponseEntity<NewAndroidListDataBean>> anchorList(
            @Body alertBean entity);


    //主播绑定列表接口
    @POST("api/mine/anchor/list")
    Observable<BaseResponseEntity<mineAnchorListDataBean>> mineAnchorList(
            @Body alertBean entity);

    //辅导设置已读
    @POST("api/inspect/read")
    Observable<BaseResponseEntity<Object>> inspectRead(
            @Body alertBean entity);


    //有没有公司
    @POST("api/mine/company/my")
    Observable<BaseResponseEntity<companyMyBean>> companyMy(
            @Body alertBean entity);


    //判断会员过期时间与是否已赠送接口
    @POST("api/common/member/late")
    Observable<BaseResponseEntity<timeBean>> memberLate(
            @Body alertBean entity);

    //修改公会比率
    @POST("api/mine/guild/ratio")
    Observable<BaseResponseEntity<Object>> guildRatio(
            @Body alertBean entity);

    //辅导详情
    @POST("api/inspect/detail")
    Observable<BaseResponseEntity<PatrolDataBean.InspectsBean>> inspectDetail(
            @Body alertBean entity);

    //选择公会
    @POST("api/common/select/guild")
    Observable<BaseResponseEntity<ChoiceGuildBean>> selectguild(
            @Body MyGuildBean entity);


    //选择主播
    @POST("api/common/select/anchor")
    Observable<BaseResponseEntity<ChoiceAnchorsBean>> getanchor(
            @Body AnchorsBean entity);


    //未弹出的通知
    @POST("api/notify/notice/pop")
    Observable<BaseResponseEntity<NoticesResponseBean>> noticePop(
            @Body AnchorsBean entity);

    //新消息未读数量
    @POST("api/message/my/noread")
    Observable<BaseResponseEntity<NoticesResponseBean>> myNoread(
            @Body AnchorsBean entity);

    //弹出福利推送提示
    @POST("api/invitation/alert")
    Observable<BaseResponseEntity<NoticesResponseBean>> invitationAlert(
            @Body AnchorsBean entity);

    //弹出福利推送提示
    @POST("api/broker/hand/upgrade/alert")
    Observable<BaseResponseEntity<upgradeAlertBean>> upgradeAlert(
            @Body AnchorsBean entity);

    //登录是否弹出赠送好礼
    @POST("api/login/pop/gives")
    Observable<BaseResponseEntity<upgradeAlertBean>> popGives(
            @Body BaseBeen entity);


    //标签
    @POST("api/common/tags")
    Observable<BaseResponseEntity<LabelDataBean>> selecttags(
            @Body LabelBean entity);


    //发起巡查接口
    @POST("api/inspect/add")
    Observable<BaseResponseEntity<Object>> launchadd(
            @Body LaunchPatrolBean entity);

    //培训点赞
    @POST("api/read/train/do/good")
    Observable<BaseResponseEntity<Object>> praise(
            @Body Praise entity);

    //修改职位状态接口
    @POST("api/guild/update/job/status")
    Observable<BaseResponseEntity<Object>> jobStatus(
            @Body Praise entity);


    //头条（文章）评论
    @POST("api/article/comment")
    Observable<BaseResponseEntity<Object>> articleComment(
            @Body CommentBean entity);

    //头条评论回复
    @POST("api/article/reply")
    Observable<BaseResponseEntity<Object>> articleReply(
            @Body CommentBean entity);


    //培训评论回复
    @POST("api/train/reply")
    Observable<BaseResponseEntity<Object>> trainReply(
            @Body CommentBean entity);

    //培训（干货）评论
    @POST("api/train/comment")
    Observable<BaseResponseEntity<Object>> trainComment(
            @Body CommentBean entity);


    //会长沟通主播接口
    @POST("api/guild/driving/link/up/anchor")
    Observable<BaseResponseEntity<Object>> linkUpAnchor(
            @Body Praise entity);

    //培训取消点赞
    @POST("api/read/train/cancel/good")
    Observable<BaseResponseEntity<Object>> articlecancel(
            @Body Praise entity);

    //培训点击收藏
    @POST("api/read/train/do/collect")
    Observable<BaseResponseEntity<Object>> collect(
            @Body Collect entity);


    //购买次数减少接口
    @POST("api/recruit/buy/residue/number")
    Observable<BaseResponseEntity<Object>> residueNumber(
            @Body Collect entity);


    //安卓专用判断是否抢过
    @POST("api/android/dedicated/judge/grade")
    Observable<BaseResponseEntity<JudgeGradeBean>> judgeGrade(
            @Body Collect entity);




    //购买次数减少接口
    @POST("api/resume/all/list")
    Observable<BaseResponseEntity<AllListBean>> resumeAllList(
            @Body Collect entity);


    //抢ta功能  会长抢主播接口
    @POST("api/resume/guild/grab/anchor")
    Observable<BaseResponseEntity<GrabAnchorBean>> grabAnchor(
            @Body AnchorOrder entity);


    //抢ta功能  抢主播支付波豆接口
    @POST("api/resume/guild/grab/anchor/order")
    Observable<BaseResponseEntity<GrabAnchorOrderBean>> grabAnchorOrder(
            @Body AnchorOrder entity);

    //一键分享接口
    @POST("api/turntable/one/key/share")
    Observable<BaseResponseEntity<Object>> keyShare(
            @Body Collect entity);


    //简历收藏接口
    @POST("api/resume/do/collect")
    Observable<BaseResponseEntity<Object>> resumeDoCollect(
            @Body Collect entity);

    //简历取消收藏接口
    @POST("api/resume/cancel/collect")
    Observable<BaseResponseEntity<Object>> resumeCancelCollect(
            @Body Collect entity);

    //培训取消收藏
    @POST("api/read/train/cancel/collect")
    Observable<BaseResponseEntity<Object>> deletecollect(
            @Body Collect entity);

    //培训取消收藏
    @POST("api/consult/share")
    Observable<BaseResponseEntity<Object>> consultShare(
            @Body Collect entity);

    //咨询搜索
    @POST("api/read/train/index")
    Observable<BaseResponseEntity<BannerHomeLookBean>> search(
            @Body SearchNewsBean entity);


    //评论的数量
    @POST("api/read/comment/num")
    Observable<BaseResponseEntity<BannerHomeLookBean>> commentNum(
            @Body Collect entity);

    //浏览量
    @POST("api/read/train/browse")
    Observable<BaseResponseEntity<Object>> browse(
            @Body Praise entity);


    //公会首页//报表主页-主体头
    @POST("api/report/index/mainnew")
    Observable<BaseResponseEntity<ManagementDataBean>> guild(
            @Body ManagementBean entity);


    //添加公会
    @POST("api/mine/guild/add")
    Observable<BaseResponseEntity<Object>> guildadd(
            @Body BindingGuildBean entity);

    //重新认证
    @POST("api/mine/guild/again/auth")
    Observable<BaseResponseEntity<Object>> againAuth(
            @Body BindingGuildBean entity);


    //升级公会接口
    @POST("api/mine/guild/upgrade")
    Observable<BaseResponseEntity<Object>> guildUpgrade(
            @Body BindingGuildBean entity);

    //选择公会
    @POST("api/common/select/guild")
    Observable<BaseResponseEntity<SelectGuildBean>> selectGuild(
            @Body BaseBeen entity);


    //加入公会
    @POST("api/mine/guild/join")
    Observable<BaseResponseEntity<Object>> injoguildadd(
            @Body JoinGuildBean entity);

    //主播快速公会绑定接口
    @POST("api/mine/anchor/add")
    Observable<BaseResponseEntity<Object>> anchorfastAdd(
            @Body JoinGuildBean entity);

    //快速绑定公会
    @POST("api/mine/guild/quick/binding")
    Observable<BaseResponseEntity<AddSeccussBean>> quickbinding(
            @Body JoinGuildBean entity);

    //查看支持平台接口
    @POST("api/mine/support/platfrom")
    Observable<BaseResponseEntity<SupportFromList>> supportPlatfrom(
            @Body JoinGuildBean entity);


    //报表-图表接口
    @POST("api/report/index/chartnew")
    Observable<BaseResponseEntity<ManagementChartBean>> chart(
            @Body ManagementBean entity);


    //报表主页-详细数据接口
    @POST("api/report/index/detailnew")
    Observable<BaseResponseEntity<DetailBean>> detail(
            @Body ManagementBean entity);

    //ToP榜信息
    @POST("api/report/index/topnew")
    Observable<BaseResponseEntity<TopTenBean>> topData(
            @Body ManagementBean entity);

    //公司组织架构
    @POST("api/mine/company/org")
    Observable<BaseResponseEntity<companyOrgBean>> companyOrg(
            @Body ManagementBean entity);


    //公司管理员列表
    @POST("api/mine/company/admins")
    Observable<BaseResponseEntity<companyAdminListBean>> companyAdmins(
            @Body ManagementBean entity);

    //公司离职列表
    @POST("api/mine/company/breaklist")
    Observable<BaseResponseEntity<companyBreaklistListBean>> companyBreaklist(
            @Body ManagementBean entity);

    //主播离职操作
    @POST("api/mine/company/breakanchor")
    Observable<BaseResponseEntity<Object>> companyBreakanchor(
            @Body RequestLeaveBean entity);

    //管理员离职操作
    @POST("api/mine/company/admin/relieve")
    Observable<BaseResponseEntity<Object>> companyLizhi(
            @Body RequestLeaveBean entity);


    //公会解绑管理员
    @POST("api/mine/guild/admin/relieve")
    Observable<BaseResponseEntity<Object>> companyJiebang(
            @Body RequestLeaveBean entity);


    //管理员解绑主播
    @POST("api/mine/admin/breakanchor")
    Observable<BaseResponseEntity<Object>> adminBreakanchor(
            @Body RequestLeaveBean entity);

    //管理员关联主播
    @POST("api/mine/admin/addanchor")
    Observable<BaseResponseEntity<Object>> adminAddanchor(
            @Body RequestLeaveBean entity);


    //第五底导
    @POST("api/report/index/earnings")
    Observable<BaseResponseEntity<GuildListBean>> reportData(
            @Body ReqReportBean entity); //第五底导


    //第五底导报表新接口
    @POST("api/report/index/earnings/new")
    Observable<BaseResponseEntity<GuildListBean>> reportData1(
            @Body ReqReportBean entity); //第五底导

    //主播报表详情
    @POST("api/report/anchor/detailnew")
    Observable<BaseResponseEntity<RespAnchoDetailBean>> getAnchoDetail(
            @Body ReqAnchoDetailBean entity);

    //等级规则、介绍接口
    @POST("api/broker/lift/rule")
    Observable<BaseResponseEntity<liftRuleBean>> liftRule(
            @Body ReqAnchoDetailBean entity);

    //常见问题
    @POST("api/timing/qa/index")
    Observable<BaseResponseEntity<InterlocutionBean>> qaIndex(
            @Body InterBBean entity);

    //我的收藏接口
    @POST("api/mine/collects")
    Observable<BaseResponseEntity<CollectBean>> collectList(
            @Body CollectRequestBean entity);

    //我的收藏接口
    @POST("api/recruit/link/up/list")
    Observable<BaseResponseEntity<BossDataBean>> upList(
            @Body CollectRequestBean entity);

    //招聘轮播图接口
    @POST("api/recruit/banner")
    Observable<BaseResponseEntity<BanagetBean>> recruitBanner(
            @Body CollectRequestBean entity);

    //游客招聘轮播图接口
    @POST("api/timing/recruit/banner")
    Observable<BaseResponseEntity<BanagetBean>> timingBanner(
            @Body CollectRequestBean entity);


    //主播拨打电话接口
    @POST("api/recruit/anchor/make/call")
    Observable<BaseResponseEntity<BanagetBean>> makeCall(
            @Body BossBeen entity);


    //分享成功后次数
    @POST("api/recruit/anchor/surplus/make/call")
    Observable<BaseResponseEntity<BanagetBean>> surplusMakeCall(
            @Body BossBeen entity);


    //点击有用的
    @POST("api/qa/add/good")
    Observable<BaseResponseEntity<Object>> addGood(
            @Body AddGoodBean entity);


    //取消有用
    @POST("api/qa/cancel/good")
    Observable<BaseResponseEntity<Object>> cancelgood(
            @Body AddGoodBean entity);


    //点击没用
    @POST("api/qa/add/bad")
    Observable<BaseResponseEntity<Object>> addBad(
            @Body AddGoodBean entity);

    //问答收藏
    @POST("api/qa/do/collect")
    Observable<BaseResponseEntity<Object>> addRequestCollect(
            @Body AddGoodBean entity);

    //取消问答收藏
    @POST("api/qa/cancel/collect")
    Observable<BaseResponseEntity<Object>> DeleteRequestCollect(
            @Body AddGoodBean entity);

    //取消没用的
    @POST("api/qa/cancel/bad")
    Observable<BaseResponseEntity<Object>> cancelbad(
            @Body AddGoodBean entity);

    //取消没用的
    @POST("api/report/anchors/guild")
    Observable<BaseResponseEntity<LookDetailBean>> LookDetail(
            @Body LookBean entity);


    //昵称是否重复
    @POST("api/common/repeat/nickname")
    Observable<BaseResponseEntity<Object>> nickname(
            @Body InformationBean entity);


    //添加资料接口
    @POST("api/common/add/info")
    Observable<BaseResponseEntity<Object>> addInfo(
            @Body AddInfo entity);

    //更新个人资料
    @POST("api/mine/update/info")
    Observable<BaseResponseEntity<Object>> updateInfo(
            @Body PersonalData entity);


    //我收录的直播平台列表接口
    @POST("api/mine/platfroms")
    Observable<BaseResponseEntity<MyPlatfromList>> minePlatfroms(
            @Body BaseBeen entity);

    //百科列表接口
    @POST("api/read/wiki/pgalist")
    Observable<BaseResponseEntity<EncyclopediaList>> EncyclopediaList(
            @Body encyclopediaRequestBean entity);

    //百科列表接口
    @POST("api/read/wiki/focus")
    Observable<BaseResponseEntity<EncyclopediaList>> wikiFocus(
            @Body encyclopediaRequestBean entity);


    //合同列表接口
    @POST("api/legal/guild_contract")
    Observable<BaseResponseEntity<CompactListResponse>> legalGuildContract(
            @Body encyclopediaRequestBean entity);


    //合同详情界面
    @POST("api/legal/contract_detail")
    Observable<BaseResponseEntity<CompactDetailResponse>> legalDetail(
            @Body encyclopediaRequestBean entity);

    //获取合同模板列表
    @POST("api/legal/template/list")
    Observable<BaseResponseEntity<CompactMobanResponse>> templateList(
            @Body encyclopediaRequestBean entity);

    //是否认证
    @POST("api/legal/is_face")
    Observable<BaseResponseEntity<IsFaceResponse>> legalIsface(
            @Body encyclopediaRequestBean entity);


    //会长剩余发布职位数接口
    @POST("api/recruit/limit/number")
    Observable<BaseResponseEntity<IsFaceResponse>> limitNumber(
            @Body encyclopediaRequestBean entity);


    //模版H5展示
    @POST("api/legal/template")
    Observable<BaseResponseEntity<IsFaceResponse>> legalTemplate(
            @Body encyclopediaRequestBean entity);

    //会长签章
    @POST("api/legal/contract_signature")
    Observable<BaseResponseEntity<ChairmanSignResponse>> legalContract_signature(
            @Body WebDates entity);

    //生成草稿箱
    @POST("api/legal/txcontract")
    Observable<BaseResponseEntity<Object>> legalTxcontract(
            @Body WebDates entity);

    //会长合同验证码获取
    @POST("api/legal/guild/verify")
    Observable<BaseResponseEntity<Object>> legalContractVerify(
            @Body encyclopediaRequestBean entity);

    //主播合同验证码获取
    @POST("api/legal/anchor/verify")
    Observable<BaseResponseEntity<Object>> legalanchorVerify(
            @Body encyclopediaRequestBean entity);

    //合同验证验证码
    @POST("api/legal/verify/code")
    Observable<BaseResponseEntity<Object>> ontractVerifyCode(
            @Body encyclopediaRequestBean1 entity);

    //合同验证通过,并推送
    @POST("api/legal/txcontract/push")
    Observable<BaseResponseEntity<Object>> legalTxcontractPush(
            @Body encyclopediaRequestBean entity);


    //主播签章
    @POST("api/legal/anchor_signature")
    Observable<BaseResponseEntity<ChairmanSignResponse>> legalAnchor_signature(
            @Body encyclopediaRequestBean entity);

    //合同撤销
    @POST("api/legal/contract_repeal")
    Observable<BaseResponseEntity<ChairmanSignResponse>> legalContractRepeal(
            @Body encyclopediaRequestBean entity);

    //加载pdf界面
    @POST("api/legal/contract_base")
    Observable<BaseResponseEntity<pdfResponse>> legalContractBase(
            @Body encyclopediaRequestBean entity);

    //剩余签章数量
    @POST("api/legal/sign/number")
    Observable<BaseResponseEntity<pdfResponse>> signNumber(
            @Body encyclopediaRequestBean entity);


    //设置消息已读
    @POST("api/message/read")
    Observable<BaseResponseEntity<Object>> messageRead(
            @Body encyclopediaRequestBean entity);


    //我要入驻
    @POST("api/read/wiki/inuser")
    Observable<BaseResponseEntity<Object>> wikiInuser(
            @Body addRuzhuBean entity);

    //百科关注
    @POST("api/read/wiki/do/collect")
    Observable<BaseResponseEntity<Object>> wikiDoCollect(
            @Body wikiCollectBean entity);

    //消息请求列表
    @POST("api/message/list")
    Observable<BaseResponseEntity<MessageResonse>> messageList(
            @Body MessageBean entity);

    //百科关注
    @POST("api/read/wiki/cancel/collect")
    Observable<BaseResponseEntity<Object>> wikiCancelCollect(
            @Body wikiCollectBean entity);


    //上传封面接口
    @POST("api/mine/upload/cover")
    Observable<BaseResponseEntity<ImageUpload>> uploadCover(
            @Body UploadCover entity);

    //增加平台
    @POST("api/mine/add/platfrom")
    Observable<BaseResponseEntity<Object>> addPlatfrom(
            @Body AddPlatfrom entity);


    //更新平台接口
    @POST("api/mine/update/platfrom")
    Observable<BaseResponseEntity<Object>> updatePlatfrom(
            @Body AddPlatfrom entity);

    //删除平台
    @POST("api/mine/delete/platfrom")
    Observable<BaseResponseEntity<Object>> deletePlatfrom(
            @Body deletePlatfrom entity);


    //任务列表
    @POST("api/task/index")
    Observable<BaseResponseEntity<TaskDataBean>> getTasklList(
            @Body TaskBean entity);


    //任务列表
    @POST("api/task/add")
    Observable<BaseResponseEntity<Object>> taskAdd(
            @Body AddTaskBean entity);

    //主播参与任务接口
    @POST("api/task/take/part")
    Observable<BaseResponseEntity<Object>> participate(
            @Body ParticipateTaskBean entity);


    //个人任务列表
    @POST("api/task/register")
    Observable<BaseResponseEntity<MyTaskBean>> myTaskList(
            @Body MyTaskZhuBean entity);

    //公司联系人列表
    @POST("api/mine/company/contacts")
    Observable<BaseResponseEntity<ListPhoenBean>> companyContacts(
            @Body MyTaskZhuBean entity);

    //t公司添加联系人
    @POST("api/mine/company/addcontact")
    Observable<BaseResponseEntity<Object>> companyAddContacts(
            @Body AddPhoneBean entity);

    //主播申请列表接口
    @POST("api/mine/anchor/applys")
    Observable<BaseResponseEntity<anchorChectBean>> anchorApplys(
            @Body AnchorChectRequestBean entity);


    //约聊列表接口
    @POST("api/chat/with/list")
    Observable<BaseResponseEntity<tellAboutResponseBean>> chatWithList(
            @Body tellAboutRequestBean entity);

    //投递简历接口
    @POST("api/swop/anchor/send/resume")
    Observable<BaseResponseEntity<Object>> sendResume(
            @Body tellAboutRequestBean entity);


    //新的拨打/留电话记录
    @POST("api/swop/dial/telephone/message")
    Observable<BaseResponseEntity<Object>> dialTelephoneMessage(
            @Body tellAboutRequestBean entity);

    //交换电话微信接口
    @POST("api/swop/telephone/wechat")
    Observable<BaseResponseEntity<Object>> telephoneWechat(
            @Body tellAboutRequestBean entity);


    //约聊界面拨打电话接口
    @POST("api/swop/dial/telephone/resume")
    Observable<BaseResponseEntity<Object>> telephoneResumet(
            @Body tellAboutRequestBean entity);


    //会长约聊主播接口
    @POST("api/guild/chat/anchor")
    Observable<BaseResponseEntity<chatAnchorResponseBean>> chatAnchor(
            @Body tellAboutRequestBean entity);


    //管理员申请列表接口
    @POST("api/mine/company/admin/list")
    Observable<BaseResponseEntity<ManagerChectBean>> managerApplys(
            @Body AnchorChectRequestBean entity);

    //公司搜索
    @POST("api/mine/company/search")
    Observable<BaseResponseEntity<companyListBean>> companySearch(
            @Body CompanyRequestBean entity);


    //帮助中心
    @POST("api/vip/qa/list")
    Observable<BaseResponseEntity<RequestListBean>> vipQaList(
            @Body CompanyRequestBean entity);


    //客服电话 & 微信
    @POST("api/custom/link")
    Observable<BaseResponseEntity<RequestListBean>> customLink(
            @Body CompanyRequestBean entity);

    //选择执行人
    @POST("api/task/select/anchor")
    Observable<BaseResponseEntity<taskSelectListBean>> taskSelectanchor(
            @Body TaskResponseBean entity);

    //增加缓存
    @POST("api/task/cache/add")
    Observable<BaseResponseEntity<Object>> cachAdd(
            @Body TaskResponseBean entity);

    //主播搜索接口
    @POST("api/report/anchor/oplayer")
    Observable<BaseResponseEntity<AnchorSerchBean>> anchorOplayer(
            @Body TaskResponseBean entity);


    @POST("api/task/issue")
    Observable<BaseResponseEntity<Object>> taskIssue(
            @Body AddNewBean entity);

    //公司详情
    @POST("api/mine/company/detail")
    Observable<BaseResponseEntity<companyDetailBean>> companyDetail(
            @Body CompanyRequestBean entity);

    //执行人搜索
    @POST("api/task/part/select")
    Observable<BaseResponseEntity<partSelectBean>> partSelect(
            @Body partSelectRequestBean entity);

    //公司搜索
    @POST("api/mine/personal/search")
    Observable<BaseResponseEntity<personalSearchBean>> personalSearch(
            @Body partSelectRequestBean entity);


    //主播绑定公会以及管理员绑定公会  待搜索的公会列表
    @POST("api/mine/guild/admin/select")
    Observable<BaseResponseEntity<SearchBean>> adminSelect(
            @Body partSelectRequestBean entity);

    //申请成为管理员
    @POST("api/mine/guild/admin/apply")
    Observable<BaseResponseEntity<Object>> adminApply(
            @Body CompanyRequestBean entity);


    //帮助中心统计
    @POST("api/vip/qa/useful")
    Observable<BaseResponseEntity<Object>> vipQaUseful(
            @Body CompanyRequestBean entity);

    //关于我们信息
    @POST("api/common/about/ours")
    Observable<BaseResponseEntity<AboutOursBean>> aboutOurs(
            @Body BaseBeen entity);


    //主播申请接口
    @POST("api/mine/anchor/check")
    Observable<BaseResponseEntity<Object>> anchorCheck(
            @Body AnchorChectRequestBean entity);

    //审核公会管理员
    @POST("api/mine/company/admin/pass")
    Observable<BaseResponseEntity<Object>> managerCheck(
            @Body ManagerChectRequestBean entity);

    //查看联系我的公司
    @POST("api/invitation/view/company")
    Observable<BaseResponseEntity<LookMeCompanyBean>> viewCompany(
            @Body ManagerChectRequestBean entity);

    @POST("api/mine/company/delcontact")
    Observable<BaseResponseEntity<Object>> companyDelcontact(
            @Body AddPhoneBean entity);


    //创建公司
    @POST("api/mine/guild/company")
    Observable<BaseResponseEntity<Object>> CreatCompany(
            @Body CreatCompanyBean entity);

    //修改公司
    @POST("api/mine/guild/company/update")
    Observable<BaseResponseEntity<Object>> companyUpdate(
            @Body CreatCompanyBean entity);

    //我的主播列表
    @POST("api/mine/anchor/index")
    Observable<BaseResponseEntity<MyAnchorListBeanResponse>> myAnchorList(
            @Body MyAnchorListBean entity);


    //我的主播优化列表
    @POST("api/mine/guild/anchor/manage")
    Observable<BaseResponseEntity<MyAnchorListBeanResponse1>> anchorManage(
            @Body MyAnchorListBean entity);


    //参与任务的主播列表接口
    @POST("api/task/taked/part/anchors")
    Observable<BaseResponseEntity<ParticipationTaskBean>> participationTaskList(
            @Body ParticipationBean entity);


    //获取支付信息
    @POST("api/order/index")
    Observable<BaseResponseEntity<PayResponseTaskBean>> getorderInfo(
            @Body PayrequestBean entity);


    //职位套餐购买接口
    @POST("api/recruit/pay_package")
    Observable<BaseResponseEntity<LegalPayResponseTaskBean>> recruitPay_package(
            @Body PayrequestBean entity);


    //法务获取支付信息
    @POST("api/legal/pay_package")
    Observable<BaseResponseEntity<LegalPayResponseTaskBean>> getLegalInfo(
            @Body PayrequestBean entity);


    //获取支付信息
    @POST("api/order/guild")
    Observable<BaseResponseEntity<DaysPriceBean>> orderGuild(
            @Body PayrequestBean entity);


    //编辑任务接口
    @POST("api/task/update")
    Observable<BaseResponseEntity<Object>> taskUpdate(
            @Body UpdateTaskBean entity);

    //删除任务接口
    @POST("api/task/delete")
    Observable<BaseResponseEntity<Object>> taskDelete(
            @Body TaskDeleteBean entity);

    //头条
    @POST("api/read/article/index")
    Observable<BaseResponseEntity<HeadLinesData>> headLines(
            @Body BannerDetalBean entity);

    //头条搜索
    @POST("api/read/article/index")
    Observable<BaseResponseEntity<HeadLinesData>> headSearch(
            @Body SearchNewsBean entity);

    //培训搜索
    @POST("api/read/train/index")
    Observable<BaseResponseEntity<BannerHomeLookBean>> PeixunSearch(
            @Body SearchNewsBean entity);

    //头条点赞
    @POST("api/read/article/do/good")
    Observable<BaseResponseEntity<Object>> headPraise(
            @Body Praise entity);

    //头条取消点赞
    @POST("api/read/article/cancel/good")
    Observable<BaseResponseEntity<Object>> headCancel(
            @Body Praise entity);

    //头条浏览量
    @POST("api/read/article/browse")
    Observable<BaseResponseEntity<Object>> headbrowse(
            @Body Praise entity);

    //培训收藏
    @POST("api/read/train/do/collect")
    Observable<BaseResponseEntity<Object>> registerCollect(
            @Body PeiCollect entity);

    //取消培训收藏
    @POST("api/read/train/cancel/collect")
    Observable<BaseResponseEntity<Object>> cancelCollect(
            @Body PeiCollect entity);

    //头条收藏
    @POST("api/read/article/do/collect")
    Observable<BaseResponseEntity<Object>> headCollect(
            @Body HeadCollect entity);

    //取消头条收藏
    @POST("api/read/article/cancel/collect")
    Observable<BaseResponseEntity<Object>> headCancelCollect(
            @Body HeadCollect entity);

    //消息数量   管理界面用的
    @POST("api/mine/count")
    Observable<BaseResponseEntity<CountBean>> mineCount(
            @Body HeadCollect entity);


    @POST("user/logout")
    Observable<BaseResponseEntity<Object>> logout(    //退出
                                                      @Body BaseRequestEntity<Object> entity);

    @POST("user/updateUser")
    Observable<BaseResponseEntity<Object>> updateUserInfo(     //修改用户资料
                                                               @Body BaseRequestEntity<UpdateUserBean> entity);

    @POST("user/loginByPhone")
    Observable<BaseResponseEntity<UserInfoBean>> loginCode(     //验证码登录
                                                                @Body BaseRequestEntity<LoginCodeBean> entity);

    @POST("api/sign/register")
    Observable<BaseResponseEntity<ResTokenBean>> setPwd(     //设置新密码
                                                             @Body SetPwdBean entity);

    @POST("api/sign/bind/wx")
    Observable<BaseResponseEntity<ResTokenBean>> bindWX(     //设置新密码
                                                             @Body BindWXBean entity);


    @POST("information/hotAndRecommend")
    Observable<BaseResponseEntity<NewsListInfoBean>> requestNewsList(   //资讯列表
                                                                        @Body BaseRequestEntity<NewsListBean> entity);


    @POST("banner/getBannerDetailByTypeId")
    Observable<BaseResponseEntity<List<BannerInfoBean>>> getBannerDetailByTypeId(   //轮播图
                                                                                    @Body BaseRequestEntity<BannerBean> entity);

    @POST("types/getType")
    Observable<BaseResponseEntity<List<NewsTypeInfoBean>>> getType(   //获取资讯名称
                                                                      @Body BaseRequestEntity<Object> entity);


    @POST("message/listMessage")
    Observable<BaseResponseEntity<TipsListInfoBean>> queryMessageList(   //获取所有公告
                                                                         @Body BaseRequestEntity<TipsBean> entity);

    @POST("message/getMsgById")
    Observable<BaseResponseEntity<TipsDetailInfoBean>> getMsgById(   //查看公告详情
                                                                     @Body BaseRequestEntity<TipsDetailBean> entity);


    @POST("information/detailed")
    Observable<BaseResponseEntity<NewsDetailInfoBean>> getNewsDetail(     //根据资讯ID获取资讯详情
                                                                          @Body BaseRequestEntity<NewsDetailBean> entity);


    @POST("versionselect/getVersionNum")
    Observable<BaseResponseEntity<List<AppVersionBean>>> checkVersion(    //版本检查
                                                                          @Body BaseRequestEntity<Object> entity);


    //创建家族头像
    @Multipart
    @POST("api/mine/upload/avatar")
    Observable<BaseResponseEntity<ImageUpload>> updaFamilyImage(
            @Body ImageBean entity, @Part MultipartBody.Part[] file);


    //        导航列表
    @POST("api/timing/train/catagory")
    Observable<BaseResponseEntity<CultivateBean>> navigationLists(@Body LaunchPatrolBean entity);


    //       一周资讯

    @POST("api/timing/article/list")
    Observable<BaseResponseEntity<NewsBean>> news(@Body LaunchPatrolBean entity);


    //       看看 banner
    @POST("api/timing/banner")
    Observable<BaseResponseEntity<LookBannerBean>> lookBanner(@Body LaunchPatrolBean entity);


//       培训首页系列文章接口

    @POST("api/looked/train/list")
    Observable<BaseResponseEntity<TrainingArticleBean>> train(@Query("token") String token,
                                                              @Query("catagory_id") String catagory_id);
//    看看更多

    @POST("api/looked/train/list/more")
    Observable<BaseResponseEntity<MoreBean>> more(@Query("token") String token,
                                                  @Query("catagory_id") String catagory_id);

    //    我的推送消息
    @POST("api/recommend/my/list")
    Observable<BaseResponseEntity<MessagesBean>> getMessage(@Body LookBean entity);

    //    推送消息设置已读
    @POST("api/recommend/my/read")
    Observable<BaseResponseEntity<MessagesBean>> setIsRead(@Query("token") String token,
                                                           @Query("rcd_id") String rcd_id);

    //    小程序专用招聘详情
    @POST("api/read/recruit/xcx/detail")
    Observable<BaseResponseEntity<RecruitDetailBean>> getRecruitDetail(@Body LaunchPatrolBean entity);

    //   简历详细 H5 调用
    @POST("api/timing/resume/detail")
    Observable<BaseResponseEntity<ResumeDetailBean>> getResumeDetail(@Body LaunchPatrolBean entity);


    //    招聘已沟通列表接口
    @POST("api/recruit/link/up/list")
    Observable<BaseResponseEntity<BossDataBean>> getLinkUp(@Query("token") String token,
                                                           @Query("type") int type,
                                                           @Query("page") String page);

    //   会长拨打电话、留电话列表接口
    @POST("api/welfare/guild/list")
    Observable<BaseResponseEntity<WelfareGuildBean>> getGuildList(@Query("token") String token,
                                                                  @Query("type") String type);

    //   购买明细接口
    @POST("api/recruit/buy/package/detail")
    Observable<BaseResponseEntity<BuyDetailBean>> buyDetail(@Query("token") String token);

    //   VIP购买明细接口
    @POST("api/vip/order/buy")
    Observable<BaseResponseEntity<BuyVIPDetailBean>> buyVIPDetail(@Body NormalRequestBean entity);


    //        额度接口
    @POST("api/invitation/my/balance")
    Observable<BaseResponseEntity<AmountBean>> getAmount(@Query("token") String token);

    //  每日任务参数接口
    @POST("api/task/everyday/mission")
    Observable<BaseResponseEntity<everydayMissionBean>> everydayMission(@Body BaseBeen entity);

    //  登录好礼领取
    @POST("api/login/gives/get")
    Observable<BaseResponseEntity<Object>> givesGet(@Body GivesGetBean entity);

    //  更新底导点击次数接口
    @POST("api/timing/common/oncilck/bottom")
    Observable<BaseResponseEntity<Object>> oncilckBottom(@Body OncilckBottomBean entity);

    // 全局弹窗设置接口
    @POST("api/common/global/alert/seting")
    Observable<BaseResponseEntity<Object>> alertSeting(@Body OncilckBottomBean entity);


    //  领取投递简历波币接口
    @POST("api/task/send/resume/draw")
    Observable<BaseResponseEntity<Object>> resumeDraw(@Body BaseBeen entity);

    //       生成邀请码
    @POST("api/invitation/create/code")
    Observable<BaseResponseEntity<InvitationCodeBean>> getCode(@Query("token") String token);


    //       主播福利提现
    @POST("api/invitation/user/price")
    Observable<BaseResponseEntity<Object>> welfareWithdrawal
    (@Query("token") String token, @Query("zfb_account") String zfb_account
            , @Query("apply_user") String apply_user, @Query("apply_amount") String apply_amount, @Query("package_id") String package_id);


    //  api/invitation/user/price/package
    @POST("api/invitation/user/price/package")
    Observable<BaseResponseEntity<PricePackageBean>> pricePackage(@Body BaseBeen entity);


    //       主播收支明细列表
    @POST("api/invitation/my/bills")
    Observable<BaseResponseEntity<DetailsBean>> getBills(@Query("token") String token);

    //   公司主页接口
    @POST("api/company/index")
    Observable<BaseResponseEntity<CompanyMainBean>> companyMain(@Query("token") String token);

    //公司入驻平台接口
    @POST("api/company/enter/platfrom")
    Observable<BaseResponseEntity<Object>> enterPlatfrom(@Query("token") String token, @Query("plat_id") String plat_id, @Query("other_palt") String other_palt);

    //公司明星主播接口
    @POST("api/company/star/anchor")
    Observable<BaseResponseEntity<Object>> setStarAnchor(@Body AddStarBean starBean);

    //公司相册接口
    @POST("api/company/photo/album")
    Observable<BaseResponseEntity<Object>> upPhotoAlbum(@Body AddStarBean starBean);

    //      添加公司
    @POST("api/guild/company/add")
    Observable<BaseResponseEntity<AddCompanyBean>> addCompany(@Query("token") String token, @Query("type") String type);

    //    企业认证
    @POST("api/legal/is_face")
    Observable<BaseResponseEntity<CertificationBean>> authentication(@Query("token") String token);

    //    公司热招职位列表接口
    @POST("api/company/hot/position")
    Observable<BaseResponseEntity<PositionListBean>> getHotPosition(@Body LaunchPatrolBean entity);

    //    会长收藏主播列表
    @POST("api/welfare/guild/collect/list")
    Observable<BaseResponseEntity<CollecBean>> getCollectList(@Body LaunchPatrolBean entity);

    //    会长小播推荐
    @POST("api/recommend/resume/list")
    Observable<BaseResponseEntity<RecommendBean>> getRecommendList(@Body LaunchPatrolBean entity);

    //    公司入驻平台、明星主播、相册删除
    @POST("api/company/plat/anchor/photo/del")
    Observable<BaseResponseEntity<Object>> deleteImg(@Body LaunchPatrolBean entity);


    //       主播领取福利
    @POST("api/invitation/user/receive")
    Observable<BaseResponseEntity<Object>> receiveWeal(@Query("token") String token);


    //       主播提现判断接口
    @POST("api/invitation/apply/condition")
    Observable<BaseResponseEntity<InvitationCodeBean>> applyPondition(@Query("token") String token);

    //       首页文章
    @POST("api/timing/looked/train/list")
    Observable<BaseResponseEntity<TrainingArticleBean>> trainList(@Body LaunchPatrolBean entity);

    //   新的消息列表
    @POST("api/message/my/list")
    Observable<BaseResponseEntity<NewMessageBean>> getNewMessage(@Body LaunchPatrolBean entity);

    //   消息已读设置
    @POST("api/message/my/read")
    Observable<BaseResponseEntity<Object>> isReadMessage(@Body LaunchPatrolBean entity);

    //   VIP套餐列表
    @POST("api/vip/packages/list")
    Observable<BaseResponseEntity<VIPCardBean>> getVIPPackagesList(@Body NormalRequestBean entity);

    //   虚拟币套餐列表
    @POST("api/virtualCoin/packages/list")
    Observable<BaseResponseEntity<VirtualCoinBean>> getVirtualCoin(@Body NormalRequestBean entity);

    //   VIP权益
    @POST("api/vip/advantage/list")
    Observable<BaseResponseEntity<VIPAdvantageBean>> getVIPAdvantage(@Body NormalRequestBean entity);

    //   VIP权益说明
    @POST("api/vip/advantage/explain")
    Observable<BaseResponseEntity<VIPAdvantageExplain>> getVIPAdvantageExplain(@Body NormalRequestBean entity);

    //   Vip支付
    @POST("api/vip/create/order")
    Observable<BaseResponseEntity<PayResponseTaskBean>> VIPPay(@Body NormalRequestBean entity);

    //   虚拟币支付
    @POST("api/virtualCoin/create/order")
    Observable<BaseResponseEntity<PayResponseTaskBean>> virtualCoinPay(@Body NormalRequestBean entity);

    //   vip-banner接口
    @POST("api/vip/banner")
    Observable<BaseResponseEntity<VIPBannerBean>> VIPBanner(@Body NormalRequestBean entity);

    //   波豆详情接口
    @POST("api/invitation/mybd/bills")
    Observable<BaseResponseEntity<BdBills>> myBdBills(@Body NormalRequestBean entity);

//      主播锦囊
//    @POST("api/timing/qa/index")
//    Observable<BaseResponseEntity<>>
}
