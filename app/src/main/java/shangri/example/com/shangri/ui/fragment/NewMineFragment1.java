package shangri.example.com.shangri.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import jp.wasabeef.glide.transformations.BlurTransformation;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseFragment;
import shangri.example.com.shangri.model.bean.event.FourshowEventBean;
import shangri.example.com.shangri.model.bean.response.AccountDataBean;
import shangri.example.com.shangri.presenter.MineFragmentPresenter;
import shangri.example.com.shangri.presenter.view.MineFragmentView;
import shangri.example.com.shangri.ui.activity.AcitivityAboutUs;
import shangri.example.com.shangri.ui.activity.AddCompanyInfoActivity2;
import shangri.example.com.shangri.ui.activity.AddInviteActivity;
import shangri.example.com.shangri.ui.activity.AnchorInvateActivity;
import shangri.example.com.shangri.ui.activity.BoBiActivity;
import shangri.example.com.shangri.ui.activity.ChangeAnchorDetailActivity;
import shangri.example.com.shangri.ui.activity.CompanyHomepageActivity;
import shangri.example.com.shangri.ui.activity.CompanyHomepageActivityTwo;
import shangri.example.com.shangri.ui.activity.GuildAndDateMergeActivity;
import shangri.example.com.shangri.ui.activity.HasDeliverActivity;
import shangri.example.com.shangri.ui.activity.HaveGouTongTitleCollectActivity;
import shangri.example.com.shangri.ui.activity.HostCollectionActivity;
import shangri.example.com.shangri.ui.activity.MessagesActivityNew;
import shangri.example.com.shangri.ui.activity.MyCollectAndFocusActivity;
import shangri.example.com.shangri.ui.activity.MyCollectResumeActivity;
import shangri.example.com.shangri.ui.activity.MyCustomerServiceActivity;
import shangri.example.com.shangri.ui.activity.MySettingActivity;
import shangri.example.com.shangri.ui.activity.ResumeManageActivity;
import shangri.example.com.shangri.ui.activity.SettingPersonalDataActivity;
import shangri.example.com.shangri.ui.activity.SignActivity;
import shangri.example.com.shangri.ui.activity.SignAndTaskActivity;
import shangri.example.com.shangri.ui.activity.UpgradeActivity1;
import shangri.example.com.shangri.ui.activity.UserFeedBackActivity;
import shangri.example.com.shangri.ui.activity.VIPActivity;
import shangri.example.com.shangri.ui.activity.VirtualCoinActivity;
import shangri.example.com.shangri.ui.activity.WelfareActivity;
import shangri.example.com.shangri.ui.activity.ZhiweiListActivity;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.view.CircleImageView;
import shangri.example.com.shangri.ui.view.HeadZoomScrollView;
import shangri.example.com.shangri.ui.view.SystemUtils;
import shangri.example.com.shangri.ui.webview.symbolHaveTitleWebView;
import shangri.example.com.shangri.util.ActivityUtils;
import shangri.example.com.shangri.util.BitmapCache;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.PointUtils;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 我的
 * Created by chengaofu on 2017/6/21.
 */

public class NewMineFragment1 extends BaseFragment<MineFragmentView, MineFragmentPresenter> implements MineFragmentView {


    public static int face_status = 0;
    public static int license_status = 0;
    @BindView(R.id.reload)
    Button reload;
    @BindView(R.id.im_bacground)
    ImageView imBacground;
    @BindView(R.id.my_image)
    CircleImageView myImage;
    @BindView(R.id.image_type)
    TextView imageType;
    @BindView(R.id.rl1)
    RelativeLayout rl1;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.image_sex)
    ImageView imageSex;
    @BindView(R.id.im_is_vip)
    ImageView im_is_vip;
    @BindView(R.id.tv_info)
    TextView tvInfo;
    @BindView(R.id.rl_update)
    RelativeLayout rl_update;
    @BindView(R.id.tv_2_number)
    TextView tv2Number;
    @BindView(R.id.tv_2_noread_number)
    TextView tv2NoreadNumber;
    @BindView(R.id.tv_2)
    TextView tv2;
    @BindView(R.id.tv_1_number)
    TextView tv1Number;
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.ll_1)
    LinearLayout ll1;
    @BindView(R.id.tv_3_number)
    TextView tv3Number;
    @BindView(R.id.tv_3)
    TextView tv3;
    @BindView(R.id.ll_3)
    LinearLayout ll3;
    @BindView(R.id.rl_101)
    RelativeLayout rl101;
    @BindView(R.id.im_companyorresume_image)
    ImageView imCompanyorresumeImage;
    @BindView(R.id.im_sign)
    ImageView im_sign;
    @BindView(R.id.im_candraw)
    ImageView im_candraw;


    @BindView(R.id.tv_companyorresume_name)
    TextView tvCompanyorresumeName;

    @BindView(R.id.rl_companyorresume)
    RelativeLayout rlCompanyorresume;
    @BindView(R.id.iv_fuli)
    ImageView iv_fuli;
    @BindView(R.id.im_bodou_image)
    ImageView imBodouImage;
    @BindView(R.id.tv_bodou_name)
    TextView tvBodouName;
    @BindView(R.id.tv_bodou_number)
    TextView tvBodouNumber;
    @BindView(R.id.rl_bodou)
    RelativeLayout rlBodou;
    @BindView(R.id.im_bobi_image)
    ImageView imBobiImage;
    @BindView(R.id.tv_bobi_name)
    TextView tvBobiName;
    @BindView(R.id.tv_bobi_number)
    TextView tvBobiNumber;
    @BindView(R.id.rl_bobi)
    RelativeLayout rlBobi;
    @BindView(R.id.im_vip_image)
    ImageView imVipImage;
    @BindView(R.id.tv_vip_name)
    TextView tvVipName;
    @BindView(R.id.rl_vip)
    RelativeLayout rlVip;
    @BindView(R.id.im_zhitongche_image)
    ImageView imZhitongcheImage;
    @BindView(R.id.tv_zhitongche_name)
    TextView tvZhitongcheName;
    @BindView(R.id.rl_zhitongche)
    RelativeLayout rlZhitongche;
    @BindView(R.id.im_bangding_glide_image)
    ImageView imBangdingGlideImage;
    @BindView(R.id.tv_bangding_glide_name)
    TextView tvBangdingGlideName;
    @BindView(R.id.rl_bangding_glide)
    RelativeLayout rlBangdingGlide;
    @BindView(R.id.im_collectandfocus_image)
    ImageView imCollectandfocusImage;
    @BindView(R.id.tv_collectandfocus_name)
    TextView tvCollectandfocusName;
    @BindView(R.id.rl_collectandfocus)
    RelativeLayout rlCollectandfocus;
    @BindView(R.id.im_about_me_image)
    ImageView imAboutMeImage;
    @BindView(R.id.tv_about_me_name)
    TextView tvAboutMeName;
    @BindView(R.id.rl_about_me)
    RelativeLayout rlAboutMe;
    @BindView(R.id.wode_xiaoxi)
    ImageView wodeXiaoxi;
    @BindView(R.id.tv_no_readmessage_number)
    TextView tv_no_readmessage_number;
    @BindView(R.id.ll_wode_shezhi)
    LinearLayout llWodeShezhi;
    @BindView(R.id.ll_wode_service)
    LinearLayout llWodeService;
    @BindView(R.id.rl_my_task)
    RelativeLayout rl_my_task;
    @BindView(R.id.tv_task_line)
    TextView tv_task_line;
    @BindView(R.id.rl_my_upgrade)
    RelativeLayout rl_my_upgrade;
    @BindView(R.id.tv_jiangli)
    TextView tv_jiangli;
    @BindView(R.id.tv_upgrade_state)
    TextView tv_upgrade_state;
    @BindView(R.id.tv_my_upgrade_name)
    TextView tv_my_upgrade_name;
    Unbinder unbinder;

    @BindView(R.id.layout_network_break)
    LinearLayout layout_network_break;

    @BindView(R.id.tv_resume_tishi_image)
    ImageView tv_resume_tishi_image;
    @BindView(R.id.tv_jiangli_wanshan_tishi)
    TextView tv_jiangli_wanshan_tishi;
    @BindView(R.id.tv_company_state)
    TextView tvCompanyState;

    @BindView(R.id.nac_root)
    HeadZoomScrollView fadingScrollView;
    @BindView(R.id.rl_scrollcahngge_view)
    RelativeLayout rl_scrollcahngge_view;
    @BindView(R.id.rl_photo_layout)
    RelativeLayout rl_photo_layout;

    @BindView(R.id.ll_select)
    LinearLayout ll_select;


    @BindView(R.id.nac_layout)
    TextView nac_layout;

    @BindView(R.id.im_leadwebview_image)
    ImageView im_leadwebview_image;
    @BindView(R.id.tv_leadwebview_name)
    TextView tv_leadwebview_name;
    @BindView(R.id.tv_show_new_weboimage)
    ImageView tv_show_new_weboimage;


    private ProgressDialogFragment mProgressDialog;

    public static int IS_VIP;
    //剩余拨打电话次数
    String residue_num = "";

    //简历id
    String resume_id = "";
    //    公司信息是否完善 1 完善 0不完善
    int company_is_perfect = 0;

    @Override
    protected void initViewsAndEvents() {
        //将下面两个布局宽度设置为系统屏幕大小 （为了适应我的页面向下滑动布局不向外面扩张，保持原有布局向下滑动）
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) rl_photo_layout.getLayoutParams();
        lp.width = SystemUtils.getDisplayWidth(getActivity());
        rl_photo_layout.setLayoutParams(lp);
        RelativeLayout.LayoutParams lp1 = (RelativeLayout.LayoutParams) ll_select.getLayoutParams();
        lp.width = SystemUtils.getDisplayWidth(getActivity());
        ll_select.setLayoutParams(lp1);
        //滑动标题改变颜色
        intseco();
        //直播数据  与签约直通车  模块现在隐藏
        rlBangdingGlide.setVisibility(View.GONE);
        rlZhitongche.setVisibility(View.GONE);
        if (UserConfig.getInstance().getRole().equals("2")) {
            imCompanyorresumeImage.setImageDrawable(getActivity().getResources().getDrawable(R.mipmap.wdzb_wdjl));
            tvCompanyorresumeName.setText("我的简历");
            imBangdingGlideImage.setImageDrawable(getActivity().getResources().getDrawable(R.mipmap.wdzb_zbsjbd));
            tvBangdingGlideName.setText("直播数据绑定");

            im_leadwebview_image.setImageDrawable(getActivity().getResources().getDrawable(R.mipmap.wd_rhzq));
            tv_leadwebview_name.setText("如何赚钱");

            //成为经纪人
            rl_my_upgrade.setVisibility(View.GONE);

            tvCompanyState.setVisibility(View.GONE);
            rlVip.setVisibility(View.GONE);
            rlBodou.setVisibility(View.GONE);
            im_candraw.setVisibility(View.VISIBLE);
//            LayoutParams 取的是所要操作的父布局的属性
//            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) rl_my_task.getLayoutParams();
//            lp.setMargins(0, 0, 0, DensityUtil.dip2px(getActivity(), 10));
//              tv_task_line.setVisibility(View.GONE);
//            公司状态布局隐藏
            tvCompanyState.setVisibility(View.GONE);
            rl_my_task.setVisibility(View.GONE);
        } else {
            imCompanyorresumeImage.setImageDrawable(getActivity().getResources().getDrawable(R.mipmap.wdhz_wdgs));
            tvCompanyorresumeName.setText("我的公司");
            imBangdingGlideImage.setImageDrawable(getActivity().getResources().getDrawable(R.mipmap.wdhz_ghsj));
            tvBangdingGlideName.setText("公会数据");

            im_leadwebview_image.setImageDrawable(getActivity().getResources().getDrawable(R.mipmap.wd_xsgl));
            tv_leadwebview_name.setText("新手攻略");

            tvCompanyState.setVisibility(View.VISIBLE);
            rl_my_upgrade.setVisibility(View.GONE);
//            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) rl_my_task.getLayoutParams();
//            lp.setMargins(0, 0, 0, 0);
//            tv_task_line.setVisibility(View.VISIBLE);
//            会长隐藏会员中心
            rlVip.setVisibility(View.GONE);
            im_candraw.setVisibility(View.GONE);

            // ，公司状态布局显示
            tvCompanyState.setVisibility(View.GONE);
            rl_my_task.setVisibility(View.VISIBLE);
        }
        if (UserConfig.getInstance().getRole().equals("2")) {
            if (UserConfig.getInstance().getIsresume().equals("0")) {
                //           引导上传简历提示语以及图片隐藏 ，
                tv_resume_tishi_image.setVisibility(View.VISIBLE);
                tv_jiangli_wanshan_tishi.setVisibility(View.VISIBLE);
            } else {
//           引导上传简历提示语以及图片显示，
                tv_resume_tishi_image.setVisibility(View.GONE);
                tv_jiangli_wanshan_tishi.setVisibility(View.GONE);
            }

        } else {
            tv_resume_tishi_image.setVisibility(View.GONE);
            tv_jiangli_wanshan_tishi.setVisibility(View.GONE);
        }
        // 新手攻略和如何赚钱   点击过web页面  new 图片消失
        if (UserConfig.getInstance().getIsFiveShow().equals("1")) {
            tv_show_new_weboimage.setVisibility(View.GONE);
        } else {
            tv_show_new_weboimage.setVisibility(View.VISIBLE);
        }
    }

    private void supportRequestWindowFeature(int featureActionBarOverlay) {
    }


    @Override
    public void onResume() {
        super.onResume();
        if (!NetWorkUtil.isNetworkConnected(getActivity())) {
//            rl_net_info.setVisibility(View.VISIBLE);
            layout_network_break.setVisibility(View.GONE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
        } else {
//            rl_net_info.setVisibility(View.VISIBLE);
            layout_network_break.setVisibility(View.GONE);
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialogFragment();
            }
            mProgressDialog.show(getActivity().getSupportFragmentManager());
            mPresenter.accountData(UserConfig.getInstance().getMobile(), UserConfig.getInstance().getPwd());
        }
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.new_fragment_mine1;
    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.new_fragment_mine1;
    }

    @Override
    protected MineFragmentPresenter createPresenter() {
        return new MineFragmentPresenter(getContext(), this);
    }

    @Override
    public void accountData(AccountDataBean mAccountDataBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        //向mainactivity 发送消息  判断我的模块上面  有没有未读消息小红点
        FourshowEventBean fourshowEventBean = new FourshowEventBean();
        fourshowEventBean.setFromMine(true);
        EventBus.getDefault().post(fourshowEventBean);

//        今日是否签到
        if (mAccountDataBean.getIs_today_sign().equals("1")) {
            im_sign.setImageDrawable(getActivity().getResources().getDrawable(R.mipmap.wd_wqd));
            im_sign.setVisibility(View.GONE);
        } else {
            im_sign.setImageDrawable(getActivity().getResources().getDrawable(R.mipmap.wd_yqd));
            im_sign.setVisibility(View.GONE);
        }

        IS_VIP = mAccountDataBean.getIs_vip();
        tvBodouNumber.setText(mAccountDataBean.getBd() + "波豆");
        tvBobiNumber.setText(mAccountDataBean.getBb() + "波币");
//       剩余拨打电话次数
        residue_num = mAccountDataBean.getResidue_num() + "";
        //个性签名
        String signature = mAccountDataBean.getRegister_info().getSignature();

       /* //会长有vip图标  是 vip tvInfo显示的是vip天数  没有vip tvInfo显示的是     主播以及管理员 vip图片隐藏  tvInfo显示的是 签名
        if (UserConfig.getInstance().getRole().equals("1")) {
            im_is_vip.setVisibility(View.VISIBLE);
//            是否vip 1是 0否
            if (mAccountDataBean.getIs_vip() == 1) {
                final String expire_time = mAccountDataBean.getExpire_time();
                try {
                    long currentTime = TimeUtil.getCurrentTime();
                    Log.d("Debug", "返回的当前时间戳为" + currentTime);
                    if (Long.parseLong(expire_time) > currentTime) {
//                                ToastUtil.showShort("会员没有过期");
                        im_is_vip.setImageDrawable(getActivity().getResources().getDrawable(R.mipmap.vip));
                        Long s = (Long.parseLong(expire_time) - currentTime) / (24 * 60 * 60) + 1;
                        tvInfo.setText("剩余" + s + "天");
                    } else {
//                        ToastUtil.showShort("会员过期");
                        im_is_vip.setImageDrawable(getActivity().getResources().getDrawable(R.mipmap.wd_wvip));
                        tvInfo.setText("您还不是VIP会员");
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                im_is_vip.setImageDrawable(getActivity().getResources().getDrawable(R.mipmap.wd_wvip));
                tvInfo.setText("您还不是VIP会员");
            }
            tvInfo.setTextColor(getActivity().getResources().getColor(R.color.white));
        } else {
            im_is_vip.setVisibility(View.GONE);
            if (signature.isEmpty()) {
                signature = "小主很酷，一言不发";
            }
            tvInfo.setText(signature);
            tvInfo.setTextColor(getActivity().getResources().getColor(R.color.color_dddcdc));
        }*/
        //现在主播以及会长都显示个性签名  不做判断
        im_is_vip.setVisibility(View.GONE);
        if (signature.isEmpty()) {
            signature = "小主很酷，一言不发";
        }
        tvInfo.setText(signature);
        tvInfo.setTextColor(getActivity().getResources().getColor(R.color.color_dddcdc));

        //tv2NoreadNumber是简历管理未读数量
        if (mAccountDataBean.getIs_read() == 0) {
            tv2NoreadNumber.setVisibility(View.GONE);
        } else {
            tv2NoreadNumber.setVisibility(View.VISIBLE);
            tv2NoreadNumber.setText(mAccountDataBean.getIs_read() + "");
        }
//        tv2Number是简历管理总数量
        if (UserConfig.getInstance().getRole().equals("1")) {
//            简历管理  职位管理 主播收藏
            tv2Number.setText(mAccountDataBean.getResume_total() + "");
            tv1Number.setText(mAccountDataBean.getSend_position_total() + "");
            tv3Number.setText(mAccountDataBean.getAnchor_collect_total() + "");
            tv2.setText("简历管理");
            tv1.setText("职位管理");
            tv3.setText("主播收藏");
        } else {
            //职位收藏  已沟通过  已投递
            tv2Number.setText(mAccountDataBean.getCollect_total() + "");
            tv1Number.setText(mAccountDataBean.getIs_linkup_total() + "");
            tv3Number.setText(mAccountDataBean.getSend_resume_total() + "");
            tv2.setText("职位收藏");
            tv1.setText("已沟通过");
            tv3.setText("简历投递");
        }
        company_is_perfect = mAccountDataBean.getCompany_is_perfect();

        setAvatarImage(mAccountDataBean.getRegister_info().getAvatar_url()
                , mAccountDataBean.getRegister_info().getAvatar_url());

        face_status = mAccountDataBean.getFace_status();
        license_status = mAccountDataBean.getLicense_status();
        if (mAccountDataBean.getFace_status() == 1 && mAccountDataBean.getLicense_status() == 1) {
            tvCompanyState.setText("已认证");
        } else {
            tvCompanyState.setText("未认证");
        }
        if (mAccountDataBean.getMsg_noread_total() == 0) {
            tv_no_readmessage_number.setVisibility(View.GONE);
        } else {
            tv_no_readmessage_number.setVisibility(View.VISIBLE);
            tv_no_readmessage_number.setText(mAccountDataBean.getMsg_noread_total() + "");
        }
        resume_id = mAccountDataBean.getRegister_info().getResume_id();
        tvName.setText(mAccountDataBean.getRegister_info().getNickname() + "");
        nac_layout.setText(mAccountDataBean.getRegister_info().getNickname() + "");

        if (mAccountDataBean.getRegister_info().getSex().equals("M")) {
            imageSex.setImageResource(R.mipmap.xingbienan);
        } else {
            imageSex.setImageResource(R.mipmap.xingbienv);
        }
        switch (mAccountDataBean.getRegister_info().getRegister_role()) {
            case "1":
                imageType.setText("会长");
                break;
            case "2":
                imageType.setText("主播");
                break;
            case "3":
                imageType.setText("管理员");
                break;
            default:
                break;
        }
//        0主播 1初级 2中级 3高级
        if (UserConfig.getInstance().getRole().equals("2")) {
            switch (mAccountDataBean.getLevel()) {
                case "0":
                    tv_my_upgrade_name.setText("成为经纪人");
                    tv_jiangli.setText("奖励翻倍");
                    break;
                case "1":
                    tv_my_upgrade_name.setText("升为中级经纪人");
                    tv_jiangli.setText("奖励更多");
                    break;
                case "2":
                    tv_my_upgrade_name.setText("升为高级经纪人");
                    tv_jiangli.setText("奖励更多");
                    break;
                case "3":
                    tv_my_upgrade_name.setText("高级经纪人");
                    tv_jiangli.setVisibility(View.GONE);
                    tv_upgrade_state.setVisibility(View.GONE);
                    break;
            }
        }
        //判断是否有消息
//        Log.e("accountData:", "Msg_noread_total = " + mAccountDataBean.getMsg_noread_total());
//        if (mAccountDataBean.getImg_url().length() > 0) {
//            iv_fuli.setVisibility(View.VISIBLE);
//            Glide.with(getActivity()).load(mAccountDataBean.getImg_url()).into(iv_fuli);
//        } else {
//            iv_fuli.setVisibility(View.GONE);
//        }
        iv_fuli.setVisibility(View.GONE);
        UserConfig.getInstance().setSign(signature);
        UserConfig.getInstance().setAvatar(mAccountDataBean.getRegister_info().getAvatar_url());
        UserConfig.getInstance().setNickname(mAccountDataBean.getRegister_info().getNickname());
        UserConfig.getInstance().setGender(mAccountDataBean.getRegister_info().getSex());
        UserConfig.getInstance().setBingphone(mAccountDataBean.getRegister_info().getTelephone());
        UserConfig.getInstance().setWxinfoId(mAccountDataBean.getRegister_info().getWxinfo_id());
    }


    private void setAvatarImage(String url, String url2) {
        String avatar = UserConfig.getInstance().getAvatar();
        Log.d("Debug", "图片的地址是" + avatar);
        Glide.with(getContext())
                .load(url)
                .placeholder(R.mipmap.wd_mrtx)
                .crossFade()
                // 设置高斯模糊
                .bitmapTransform(new BlurTransformation(getActivity(), 90, 1))
                .into(imBacground);
        BitmapCache.getInstance().loadBitmaps(myImage, url, null);
    }

    @Override
    public void requestFailed(String message) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

    }

    @OnClick({R.id.rl_ask_question, R.id.rl_leadwebview, R.id.rl_update, R.id.rl_my_upgrade, R.id.reload, R.id.rl_my_task, R.id.im_sign, R.id.ll_2, R.id.ll_1, R.id.ll_3, R.id.rl_companyorresume, R.id.rl_zhitongche, R.id.iv_fuli, R.id.rl_bodou, R.id.rl_bobi, R.id.rl_vip, R.id.rl_bangding_glide, R.id.rl_collectandfocus, R.id.rl_about_me, R.id.ll_4, R.id.ll_wode_shezhi, R.id.ll_wode_service})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //会长跳转  新手功略  主播跳转 如何赚钱  web页面
            case R.id.rl_leadwebview:
                if (PointUtils.isFastClick()) {
                    UserConfig.getInstance().pwdIsFiveShow("1");
                    tv_show_new_weboimage.setVisibility(View.GONE);
                    //跳转web页面地址
                    String url = "";
                    String titleName = "";
                    if (UserConfig.getInstance().getRole().equals("1")) {
                        url = "http://www.zhibohome.net/single/learn/learn_president/index.html";
                        titleName = "新手攻略";
                    } else {
                        url = "http://www.zhibohome.net/single/learn/learn_anchor/index.html";
                        titleName = "如何赚钱";
                    }
                    Intent intent = new Intent(getActivity(), symbolHaveTitleWebView.class);
                    intent.putExtra("url", url);
                    intent.putExtra("titleName", titleName);
                    startActivity(intent);
                }
                break;
            //问题反馈
            case R.id.rl_ask_question:
                if (PointUtils.isFastClick()) {
                    ActivityUtils.startActivity(getActivity(), UserFeedBackActivity.class);
                }
                break;
            //任务中心界面
            case R.id.im_sign:
                if (PointUtils.isFastClick()) {
                    Intent intent = new Intent(getActivity(), SignAndTaskActivity.class);
                    intent.putExtra("isFromAnchorBobi", false);
                    startActivity(intent);
                }
                break;
            case R.id.rl_my_task:
                if (PointUtils.isFastClick()) {
                    Intent intent = new Intent(getActivity(), SignAndTaskActivity.class);
                    intent.putExtra("isFromAnchorBobi", true);
                    startActivity(intent);
                }
                break;
            //个人信息进行修改
            case R.id.rl_update:
                if (PointUtils.isFastClick()) {
                    Intent intent2 = new Intent(getActivity(), SettingPersonalDataActivity.class);
                    startActivity(intent2);
                }
                break;
            case R.id.reload:
                if (PointUtils.isFastClick()) {
                    if (!NetWorkUtil.isNetworkConnected(getActivity())) {
//                        rl_net_info.setVisibility(View.GONE);
                        layout_network_break.setVisibility(View.VISIBLE);
                        ToastUtil.showShort(getResources().getString(R.string.search_not_net));
                    } else {
//                        rl_net_info.setVisibility(View.VISIBLE);
                        layout_network_break.setVisibility(View.GONE);
                        if (mProgressDialog == null) {
                            mProgressDialog = new ProgressDialogFragment();
                        }
                        mProgressDialog.show(getActivity().getSupportFragmentManager());
                        mPresenter.accountData(UserConfig.getInstance().getMobile(), UserConfig.getInstance().getPwd());
                    }
                }
                break;
            case R.id.ll_2:
                if (UserConfig.getInstance().getRole().equals("2")) {
                    //主播跳转职位收藏界面
                    Intent intent9 = new Intent(getActivity(), MyCollectResumeActivity.class);
                    startActivity(intent9);
                } else {
//                    //会长跳转主播投递
                    //会长跳转简历管理
                    startActivity(new Intent(getActivity(), ResumeManageActivity.class));
                }
                break;
            case R.id.ll_1:
                if (UserConfig.getInstance().getRole().equals("2")) {
                    startActivity(new Intent(getActivity(), HaveGouTongTitleCollectActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), ZhiweiListActivity.class));
                }
                break;
            //主播跳转已投递界面  会长跳转主播收藏界面
            case R.id.ll_3:
                if (UserConfig.getInstance().getRole().equals("2")) {
                    ActivityUtils.startActivity(getActivity(), HasDeliverActivity.class);
                } else {
                    if (PointUtils.isFastClick()) {
                        ActivityUtils.startActivity(getActivity(), HostCollectionActivity.class);
                    }
                }
                break;
            //主播跳转简历  会长跳转公司
            case R.id.rl_companyorresume:
                if (PointUtils.isFastClick()) {
                    if (UserConfig.getInstance().getRole().equals("1")) {
                        if (company_is_perfect == 1) {
                            CompanyHomepageActivityTwo.COMPANY_TOKEN = "";
                            ActivityUtils.startActivity(getActivity(), CompanyHomepageActivity.class);
                        } else {
                            Intent intent = new Intent(getActivity(), AddCompanyInfoActivity2.class);
                            intent.putExtra("IsFirstFabuJob", true);
                            intent.putExtra("IsFromMine", true);
                            startActivity(intent);
                        }
                    } else {
                        if (UserConfig.getInstance().getIsresume().equals("0")) {
                            startActivity(new Intent(getActivity(), AddInviteActivity.class));
                        } else {
                            startActivity(new Intent(getActivity(), ChangeAnchorDetailActivity.class).putExtra("id", resume_id));
                        }
                    }
                }
                break;
            //升等级界面跳转
            case R.id.rl_my_upgrade:
                if (PointUtils.isFastClick()) {
                    ActivityUtils.startActivity(getActivity(), UpgradeActivity1.class);
                }
                break;
            case R.id.iv_fuli:
                if (PointUtils.isFastClick()) {
                    if (UserConfig.getInstance().getRole().equals("2")) {
                        //主播跳转
                        ActivityUtils.startActivity(getActivity(), AnchorInvateActivity.class);
                    } else {
                        //会长跳转
                        ActivityUtils.startActivity(getActivity(), WelfareActivity.class);
                    }
                }
                break;
            //跳转到波豆
            case R.id.rl_bodou:
                if (PointUtils.isFastClick()) {
                    ActivityUtils.startActivity(getActivity(), VirtualCoinActivity.class);
                }
                break;
            //主播会长跳转波币
            case R.id.rl_bobi:
                if (PointUtils.isFastClick()) {
                    ActivityUtils.startActivity(getActivity(), BoBiActivity.class);
                }
                break;
            //会长跳转vip界面
            case R.id.rl_vip:
                if (PointUtils.isFastClick()) {
                    if (UserConfig.getInstance().getRole().equals("1")) {
                        ActivityUtils.startActivity(getActivity(), VIPActivity.class);
                    }
                }
                break;
            //跳转我的公会界面
            case R.id.rl_bangding_glide:
                if (PointUtils.isFastClick()) {


                    startActivity(new Intent(getActivity(), GuildAndDateMergeActivity.class));


//                    if (UserConfig.getInstance().getRole().equals("2")) {
//                        //主播跳转公会绑定和快速绑定界面
//                        startActivity(new Intent(getActivity(), AnchorNewMyGuildActivity.class));
//                    } else {
//                        //会长跳转公会管理
//                        startActivity(new Intent(getActivity(), GuildManagerActivity.class));
//                    }
                }
                break;
            //收藏关注何合并界面
            case R.id.rl_collectandfocus:
                if (PointUtils.isFastClick()) {
                    startActivity(new Intent(getActivity(), MyCollectAndFocusActivity.class));
                }

                break;
            //关于我们
            case R.id.rl_about_me:
                if (PointUtils.isFastClick()) {
                    startActivity(new Intent(getActivity(), AcitivityAboutUs.class));
                }
                break;
            //跳转到消息页面
            case R.id.ll_4:
                if (PointUtils.isFastClick()) {
                    Intent intent = new Intent(getActivity(), MessagesActivityNew.class);
                    startActivity(intent);
                }
                break;
            //跳转直通车界面
            case R.id.rl_zhitongche:
                if (PointUtils.isFastClick()) {
                    startActivity(new Intent(getActivity(), SignActivity.class));
                }
                break;
            //设置界面
            case R.id.ll_wode_shezhi:
                if (PointUtils.isFastClick()) {
                    ActivityUtils.startActivity(getActivity(), MySettingActivity.class);
                }
                break;
            //联系客服
            case R.id.ll_wode_service:
                if (PointUtils.isFastClick()) {
                    startActivity(new Intent(getActivity(), MyCustomerServiceActivity.class));
                }
                break;
        }
    }

    /**
     * 滑动监听,标题改动
     */
    private void intseco() {

        //获取dimen属性中 标题和头部图片的高度
        final float title_height = getResources().getDimension(R.dimen.title_height);
        final float head_height = getResources().getDimension(R.dimen.head_height);

        //滑动事件回调监听（一次滑动的过程一般会连续触发多次）
        fadingScrollView.setOnScrollListener(new HeadZoomScrollView.OnScrollListener() {
            @Override
            public void onScroll(int oldy, int dy, boolean isUp) {
                float move_distance = head_height - title_height;
                Log.d("Debug", "到达图片滑动" + move_distance);
                if (!isUp && dy <= move_distance) {
                    //手指往上滑,距离未超过头部高度(状态是一直在改变的)
                    TitleAlphaChange(dy, move_distance);
                } else if (!isUp && dy > move_distance) {
                    // 手指往上滑,距离超过头部高度(当把头部布局掩盖住时，做标题栏布局 改变布局操作或是改变图片或是字体操作)
                } else if (isUp && dy > move_distance) {
                    //返回顶部，但距离头部位置大于头部高度 （和上面做的操作一样）
                } else if (isUp && dy <= move_distance) {
                    //返回顶部，但距离头部位置小于头部高度
                    TitleAlphaChange(dy, move_distance);
                }
            }
        });
    }

    private void TitleAlphaChange(int dy, float mHeaderHeight_px) {//设置标题栏透明度变化
        float percent = (float) Math.abs(dy) / Math.abs(mHeaderHeight_px);
        //如果是设置背景透明度，则传入的参数是int类型，取值范围0-255
        //如果是设置控件透明度，传入的参数是float类型，取值范围0.0-1.0
        //这里我们是设置背景透明度就好，因为设置控件透明度的话，返回ICON等也会变成透明的。
        //alpha 值越小越透明
        int alpha = (int) (percent * 255);
        rl_scrollcahngge_view.setBackgroundColor(Color.argb((int) alpha, 0, 0, 0));
        nac_layout.setTextColor(Color.argb((int) alpha, 255, 255, 255));
    }

}