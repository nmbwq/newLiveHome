package shangri.example.com.shangri.ui.fragment;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;

import java.text.ParseException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import jp.wasabeef.glide.transformations.BlurTransformation;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseFragment;
import shangri.example.com.shangri.base.GlobalApp;
import shangri.example.com.shangri.model.bean.event.FourshowEventBean;
import shangri.example.com.shangri.model.bean.response.AccountDataBean;
import shangri.example.com.shangri.presenter.MineFragmentPresenter;
import shangri.example.com.shangri.presenter.view.MineFragmentView;
import shangri.example.com.shangri.ui.activity.AcitivityAboutUs;
import shangri.example.com.shangri.ui.activity.AddCompanyInfoActivity2;
import shangri.example.com.shangri.ui.activity.AddInviteActivity;
import shangri.example.com.shangri.ui.activity.AnchorChectListActivity;
import shangri.example.com.shangri.ui.activity.ChangeAnchorDetailActivity;
import shangri.example.com.shangri.ui.activity.CompamyInfoActivity;
import shangri.example.com.shangri.ui.activity.CompanyHomepageActivity;
import shangri.example.com.shangri.ui.activity.FastAnchorBindingActivity;
import shangri.example.com.shangri.ui.activity.GuildManagerActivity;
import shangri.example.com.shangri.ui.activity.HaveGouTongTitleCollectActivity;
import shangri.example.com.shangri.ui.activity.HostCollectionActivity;
import shangri.example.com.shangri.ui.activity.MessagesActivityNew;
import shangri.example.com.shangri.ui.activity.MyCollectActivity;
import shangri.example.com.shangri.ui.activity.MyCollectResumeActivity;
import shangri.example.com.shangri.ui.activity.MyCustomerServiceActivity;
import shangri.example.com.shangri.ui.activity.MyFocusActivity;
import shangri.example.com.shangri.ui.activity.MyGuildActivity;
import shangri.example.com.shangri.ui.activity.MyManagerActivity;
import shangri.example.com.shangri.ui.activity.MySettingActivity;
import shangri.example.com.shangri.ui.activity.RemainderActivity;
import shangri.example.com.shangri.ui.activity.ResumeManageActivity;
import shangri.example.com.shangri.ui.activity.SettingPersonalDataActivity;
import shangri.example.com.shangri.ui.activity.SignActivity;
import shangri.example.com.shangri.ui.activity.UserBenefitsActivity;
import shangri.example.com.shangri.ui.activity.VIPActivity;
import shangri.example.com.shangri.ui.activity.VirtualCoinActivity;
import shangri.example.com.shangri.ui.activity.WelfareActivity;
import shangri.example.com.shangri.ui.activity.ZhiweiListActivity;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.view.CircleImageView;
import shangri.example.com.shangri.ui.view.FadingScrollView;
import shangri.example.com.shangri.util.ActivityUtils;
import shangri.example.com.shangri.util.BitmapCache;
import shangri.example.com.shangri.util.DensityUtil;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.PointUtils;
import shangri.example.com.shangri.util.TimeUtil;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 我的
 * Created by chengaofu on 2017/6/21.
 */

public class NewMineFragment extends BaseFragment<MineFragmentView, MineFragmentPresenter> implements MineFragmentView {

    @Override
    protected int getNormalLayoutId() {
        return R.layout.new_fragment_mine;
    }

    @BindView(R.id.im_bacground)
    ImageView imBacground;
    @BindView(R.id.rl_b)
    RelativeLayout rl_b;
    @BindView(R.id.rl_101)
    RelativeLayout rl_101;
    @BindView(R.id.my_image)
    CircleImageView myImage;
    @BindView(R.id.image_type)
    ImageView imageType;
    @BindView(R.id.rl1)
    RelativeLayout rl1;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.image_sex)
    ImageView imageSex;
    @BindView(R.id.ll2)
    LinearLayout ll2;
    @BindView(R.id.tv_info)
    TextView tvInfo;
    @BindView(R.id.image_update)
    ImageView imageUpdate;
    @BindView(R.id.im_12)
    ImageView im12;


    @BindView(R.id.tv_space)
    TextView tv_space;
    @BindView(R.id.nac_layout)
    TextView nac_layout;
    @BindView(R.id.empty)
    ImageView empty;
    @BindView(R.id.tv_1)
    TextView tv_1;
    @BindView(R.id.ll_1)
    LinearLayout ll_1;
    @BindView(R.id.tv_2)
    TextView tv_2;
    @BindView(R.id.tv_2_type)
    TextView tv_2_type;

    @BindView(R.id.ll_2)
    LinearLayout ll_2;
    @BindView(R.id.im_3)
    ImageView im_3;
    @BindView(R.id.tv_3)
    TextView tv_3;
    @BindView(R.id.tv_10)
    TextView tv_10;

    @BindView(R.id.tv_15)
    TextView tv_15;

    @BindView(R.id.ll_3)
    LinearLayout ll_3;
    /**
     * 主播收藏
     */
    @BindView(R.id.ll_4)
    LinearLayout ll_4;
    @BindView(R.id.host_collection_num)
    TextView host_collection_num;
    @BindView(R.id.host_collection)
    TextView host_collection;

    private Unbinder unbinder;

    @BindView(R.id.ll_anchor_line)
    TextView llAnchorLine;
    @BindView(R.id.ll_anchor)
    LinearLayout ll_anchor;
    @BindView(R.id.ll_anchor_fawu)
    LinearLayout ll_anchor_fawu;

    @BindView(R.id.layout_network_break)
    LinearLayout layout_network_break;
    @BindView(R.id.rl_net_info)
    RelativeLayout rl_net_info;


    private String member_time = "";
    @BindView(R.id.reload)
    Button reload;
    @BindView(R.id.iv16)
    TextView iv16;
    @BindView(R.id.iv17)
    TextView iv17;
    @BindView(R.id.iv1)
    TextView iv1;
    @BindView(R.id.iv2)
    TextView iv2;
    @BindView(R.id.iv3)
    TextView iv3;
    @BindView(R.id.iv4)
    TextView iv4;
    @BindView(R.id.iv8)
    TextView iv8;
    @BindView(R.id.iv9)
    TextView iv9;
    @BindView(R.id.iv10)
    TextView iv10;

    @BindView(R.id.tv_state)
    TextView tv_state;
    @BindView(R.id.rl_image_text)
    RelativeLayout rl_image_text;

    //我的消息
    @BindView(R.id.ll_wode_xiaoxi)
    LinearLayout ll_wode_xiaoxi;
    //我的设置
    @BindView(R.id.ll_wode_shezhi)
    LinearLayout ll_wode_shezhi;
    @BindView(R.id.wode_xiaoxi)
    ImageView wode_xiaoxi;
    //福利
    @BindView(R.id.iv_fuli)
    ImageView iv_fuli;
    //已沟通-职位管理
    @BindView(R.id.tv_21)
    TextView tv21;
    @BindView(R.id.tv_no_readmessage_number)
    TextView tv_no_readmessage_number;
    @BindView(R.id.tv_company_state)
    TextView tv_company_state;


    //完善公司首页
    @BindView(R.id.company_homepage)
    LinearLayout company_homepage;
    @BindView(R.id.im_is_vip)
    ImageView im_is_vip;

    //波豆模块
    @BindView(R.id.ll_bd_company)
    LinearLayout ll_bd_company;
    @BindView(R.id.bd_num)
    TextView bd_num;
    @BindView(R.id.tv_company_message)
    TextView tv_company_message;
    @BindView(R.id.company_message)
    TextView company_message;
    @BindView(R.id.ll_bd)
    LinearLayout ll_bd;
    @BindView(R.id.ll_company)
    RelativeLayout ll_company;


    //简历web页面
    String info_url = "";
    //简历id
    String resume_id = "";
    //    公司信息是否完善 1 完善 0不完善
    int company_is_perfect = 0;

    public static int face_status = 0;
    public static int license_status = 0;

    private ProgressDialogFragment mProgressDialog;

    public static int IS_VIP;

    @Override
    protected void initViewsAndEvents() {
//         imBacground;
//         rl_b;
//         rl_101;
        android.view.ViewGroup.LayoutParams p1 = imBacground.getLayoutParams();
        p1.height = DensityUtil.dip2px(getActivity(), 260);
        imBacground.setLayoutParams(p1);
        android.view.ViewGroup.LayoutParams p2 = rl_b.getLayoutParams();
        p2.height = DensityUtil.dip2px(getActivity(), 300);
        rl_b.setLayoutParams(p2);
        android.view.ViewGroup.LayoutParams p3 = rl_101.getLayoutParams();
        p3.height = DensityUtil.dip2px(getActivity(), 303);
        rl_101.setLayoutParams(p3);

        //标题渐变效果
        TextView layout = getActivity().findViewById(R.id.nac_layout);
        layout.setAlpha(0);
        FadingScrollView fadingScrollView = getActivity().findViewById(R.id.nac_root);
        fadingScrollView.setFadingView(layout);
        ll_bd_company.setVisibility(View.GONE);
        if (UserConfig.getInstance().getRole().equals("1")) {
            android.view.ViewGroup.LayoutParams p4 = imBacground.getLayoutParams();
            p4.height = DensityUtil.dip2px(getActivity(), 290);
            imBacground.setLayoutParams(p4);
            android.view.ViewGroup.LayoutParams p5 = rl_b.getLayoutParams();
            p5.height = DensityUtil.dip2px(getActivity(), 330);
            rl_b.setLayoutParams(p5);
            android.view.ViewGroup.LayoutParams p6 = rl_101.getLayoutParams();
            p6.height = DensityUtil.dip2px(getActivity(), 333);
            rl_101.setLayoutParams(p6);

            ll_bd_company.setVisibility(View.VISIBLE);
            ll_4.setVisibility(View.VISIBLE);
            Drawable drawable1 = getResources().getDrawable(R.mipmap.wode_gonghui);
            drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());
            iv1.setCompoundDrawables(null, drawable1, null, null);
            iv1.setText("公会管理");

            Drawable drawable2 = getResources().getDrawable(R.mipmap.zhubo_shenqing);
            drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
            iv2.setCompoundDrawables(null, drawable2, null, null);
            iv2.setText("主播申请");

            Drawable drawable3 = getResources().getDrawable(R.mipmap.qianyue_zhitongche);
            drawable3.setBounds(0, 0, drawable3.getMinimumWidth(), drawable3.getMinimumHeight());
            iv3.setCompoundDrawables(null, drawable3, null, null);
            iv3.setText("签约直通车");

            iv4.setVisibility(View.VISIBLE);
            Drawable drawable4 = getResources().getDrawable(R.mipmap.wd_hyzx);
            drawable4.setBounds(0, 0, drawable4.getMinimumWidth(), drawable4.getMinimumHeight());
            iv4.setCompoundDrawables(null, drawable4, null, null);
            iv4.setText("VIP中心");


            tv_15.setVisibility(View.VISIBLE);
            tv_2_type.setText("简历管理");
            ll_1.setVisibility(View.VISIBLE);
            tv_3.setText("剩余拨电话数");


            im_3.setVisibility(View.GONE);
            im_3.setImageDrawable(getActivity().getResources().getDrawable(R.mipmap.fabu_zhiwei));
//            company_homepage.setVisibility(View.VISIBLE);
            imageType.setImageResource(R.mipmap.ic_wohuizhang);
            tv_space.setVisibility(View.VISIBLE);
        } else if (UserConfig.getInstance().getRole().equals("2")) {
            ll_4.setVisibility(View.GONE);
            Drawable drawable1 = getResources().getDrawable(R.mipmap.wode_gonghui);
            drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());
            iv1.setCompoundDrawables(null, drawable1, null, null);
            iv1.setText("公会绑定");
            Drawable drawable2 = getResources().getDrawable(R.mipmap.zhubo_bangding);
            drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
            iv2.setCompoundDrawables(null, drawable2, null, null);
            iv2.setText("快速绑定");

            Drawable drawable3 = getResources().getDrawable(R.mipmap.qianyue_zhitongche);
            drawable3.setBounds(0, 0, drawable3.getMinimumWidth(), drawable3.getMinimumHeight());
            iv3.setCompoundDrawables(null, drawable3, null, null);
            iv3.setText("签约直通车");

            iv4.setVisibility(View.INVISIBLE);

//已沟通隐藏
            tv_15.setVisibility(View.GONE);
            ll_1.setVisibility(View.VISIBLE);
            tv_2_type.setText("职位收藏");
            tv_3.setText("我的简历");
            tv_10.setVisibility(View.GONE);
            im_3.setImageDrawable(getActivity().getResources().getDrawable(R.mipmap.wd_wdjl));
            imageType.setImageResource(R.mipmap.ic_wozhubo);
            tv_space.setVisibility(View.VISIBLE);
//            company_homepage.setVisibility(View.GONE);
        } else {
            ll_4.setVisibility(View.GONE);
            tv_15.setVisibility(View.GONE);
            Drawable drawable1 = getResources().getDrawable(R.mipmap.wode_gonghui);
            drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());
            iv1.setCompoundDrawables(null, drawable1, null, null);
            iv1.setText("公会绑定");

            Drawable drawable2 = getResources().getDrawable(R.mipmap.xuncha);
            drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
            iv2.setCompoundDrawables(null, drawable2, null, null);
            iv2.setText("辅导与任务");

            Drawable drawable3 = getResources().getDrawable(R.mipmap.zuzhi);
            drawable3.setBounds(0, 0, drawable3.getMinimumWidth(), drawable3.getMinimumHeight());
            iv3.setCompoundDrawables(null, drawable3, null, null);
            iv3.setText("组织架构");
            iv4.setVisibility(View.INVISIBLE);
            tv_3.setText("职位管理");
            im_3.setImageDrawable(getActivity().getResources().getDrawable(R.mipmap.fabu_zhiwei));
            tv_10.setVisibility(View.GONE);
            ll_1.setVisibility(View.GONE);
            tv_2_type.setText("职位收藏");
            imageType.setImageResource(R.mipmap.ic_woguanliyuan);
            tv_space.setVisibility(View.GONE);
            company_homepage.setVisibility(View.GONE);
        }
        fadingScrollView.setFadingHeightView(getActivity().findViewById(R.id.rl_b));


    }

    private void supportRequestWindowFeature(int featureActionBarOverlay) {
    }

    @Override
    public void onResume() {
        super.onResume();
        nac_layout.setText(UserConfig.getInstance().getNickname() + "");
        if (!NetWorkUtil.isNetworkConnected(getActivity())) {
            rl_net_info.setVisibility(View.VISIBLE);
            layout_network_break.setVisibility(View.GONE);
            ToastUtil.showShort(getResources().getString(R.string.search_not_net));
        } else {
            rl_net_info.setVisibility(View.VISIBLE);
            layout_network_break.setVisibility(View.GONE);
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialogFragment();
            }
            mProgressDialog.show(getActivity().getSupportFragmentManager());
            mPresenter.accountData(UserConfig.getInstance().getMobile(), UserConfig.getInstance().getPwd());
        }
        if (UserConfig.getInstance().getRole().equals("2")) {
            tv21.setText("沟通过");
        } else if (UserConfig.getInstance().getRole().equals("1")) {
            tv21.setText("职位管理");
        }
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.new_fragment_mine;
    }


    @Override
    protected MineFragmentPresenter createPresenter() {
        return new MineFragmentPresenter(getContext(), this);

    }

    @Override
    public void accountData(AccountDataBean mAccountDataBean) {
        IS_VIP = mAccountDataBean.getIs_vip();
        bd_num.setText(mAccountDataBean.getBd() + "");

        if (mAccountDataBean.getFace_status() == 1 && mAccountDataBean.getLicense_status() == 1) {
            tv_company_message.setText("已认证");
            Drawable drawable = getResources().getDrawable(R.mipmap.gsjsyi);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tv_company_message.setCompoundDrawables(drawable, null, null, null);
        } else {
            tv_company_message.setText("未认证");
            Drawable drawable = getResources().getDrawable(R.mipmap.gsjswei);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tv_company_message.setCompoundDrawables(drawable, null, null, null);
        }
//        tv_company_message
        residue_num = mAccountDataBean.getResidue_num() + "";
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        //向mainactivity 发送消息  判断我的模块上面  有没有未读消息小红点
        FourshowEventBean fourshowEventBean = new FourshowEventBean();
        fourshowEventBean.setFromMine(true);
        EventBus.getDefault().post(fourshowEventBean);

        tv_10.setText(mAccountDataBean.getResidue_num() + "");
        String signature = mAccountDataBean.getRegister_info().getSignature();

        //会长有vip图标  是 vip tvInfo显示的是vip天数  没有vip tvInfo显示的是     主播以及管理员 vip图片隐藏  tvInfo显示的是 签名
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
        }
        //tv_15是简历管理未读数量
        if (mAccountDataBean.getIs_read() == 0) {
            tv_15.setVisibility(View.GONE);
        } else {
            tv_15.setVisibility(View.VISIBLE);
            tv_15.setText(mAccountDataBean.getIs_read() + "");
        }

//        tv_2是简历管理总数量
        if (UserConfig.getInstance().getRole().equals("1")) {
            tv_2.setText(mAccountDataBean.getResume_total() + "");
            tv_1.setText(mAccountDataBean.getSend_position_total() + "");
        } else {
            tv_2.setText(mAccountDataBean.getCollect_total() + "");
            tv_1.setText(mAccountDataBean.getIs_linkup_total() + "");
        }
        company_is_perfect = mAccountDataBean.getCompany_is_perfect();
//        公司信息是否完善 1 完善 0不完善
        if (mAccountDataBean.getCompany_is_perfect() == 1) {
            company_message.setText(mAccountDataBean.getCompany_short_name() + "");
        } else {
            company_message.setText("完善公司信息");
        }

        setAvatarImage(mAccountDataBean.getRegister_info().getAvatar_url()
                , mAccountDataBean.getRegister_info().getAvatar_url());

        face_status = mAccountDataBean.getFace_status();
        license_status = mAccountDataBean.getLicense_status();
        if (mAccountDataBean.getFace_status() == 1 && mAccountDataBean.getLicense_status() == 1) {
            tv_state.setText("已认证");
        } else {
            tv_state.setText("未认证");
        }
        if (mAccountDataBean.getMsg_noread_total() == 0) {
            tv_no_readmessage_number.setVisibility(View.GONE);
        } else {
            tv_no_readmessage_number.setVisibility(View.VISIBLE);
            tv_no_readmessage_number.setText(mAccountDataBean.getMsg_noread_total() + "");
        }
        host_collection_num.setText(mAccountDataBean.getAnchor_collect_total());
        info_url = mAccountDataBean.getRegister_info().getInfo_url();
        resume_id = mAccountDataBean.getRegister_info().getResume_id();
        member_time = mAccountDataBean.getRegister_info().getMember_time();
        tvName.setText(mAccountDataBean.getRegister_info().getNickname());


        if (mAccountDataBean.getRegister_info().getSex().equals("M")) {
            imageSex.setImageResource(R.mipmap.ic_wonan);
        } else if (mAccountDataBean.getRegister_info().getSex().equals("F")) {
            imageSex.setImageResource(R.mipmap.ic_wonv);
        } else {
            imageSex.setImageResource(R.mipmap.ic_weizhi);
        }
        switch (mAccountDataBean.getRegister_info().getRegister_role()) {
            case "1":
                imageType.setImageResource(R.mipmap.ic_wohuizhang);
                tv_space.setVisibility(View.VISIBLE);
                break;
            case "2":
                imageType.setImageResource(R.mipmap.ic_wozhubo);
                tv_space.setVisibility(View.VISIBLE);
                break;
            default:
                imageType.setImageResource(R.mipmap.ic_woguanliyuan);
                tv_space.setVisibility(View.GONE);
                break;
        }
        //判断是否有消息
        Log.e("accountData:", "Msg_noread_total = " + mAccountDataBean.getMsg_noread_total());

        if (mAccountDataBean.getImg_url().length() > 0) {
            iv_fuli.setVisibility(View.VISIBLE);
            Glide.with(getActivity()).load(mAccountDataBean.getImg_url()).into(iv_fuli);
        } else {
            iv_fuli.setVisibility(View.GONE);
        }

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
                .placeholder(R.mipmap.bg_touxiang)
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

    String residue_num = "";

    @OnClick({R.id.ll_1, R.id.ll_2, R.id.ll_3, R.id.image_update, R.id.reload, R.id.iv1, R.id.iv2, R.id.iv3, R.id.iv4, R.id.iv8, R.id.iv9, R.id.iv10, R.id.iv11,//R.id.iv14,R.id.iv12,
            R.id.ll_wode_xiaoxi, R.id.ll_wode_shezhi, R.id.iv_fuli, R.id.company_homepage, R.id.ll_4, R.id.ll_company, R.id.ll_bd})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            //会长主播都是已沟通  管理员没有已沟通页面
            case R.id.ll_1:
                if (UserConfig.getInstance().getRole().equals("2")) {
                    startActivity(new Intent(getActivity(), HaveGouTongTitleCollectActivity.class));
                } else if (UserConfig.getInstance().getRole().equals("1")) {
                    startActivity(new Intent(getActivity(), ZhiweiListActivity.class));
//                    startActivity(new Intent(getActivity(), ChairLinkupListActivity.class));
                }
                break;
            case R.id.ll_2:
                if (UserConfig.getInstance().getRole().equals("2")) {
                    //主播跳转职位收藏界面
                    Intent intent9 = new Intent(getActivity(), MyCollectResumeActivity.class);
                    startActivity(intent9);
                } else if (UserConfig.getInstance().getRole().equals("1")) {
//                    //会长跳转主播投递
//                    startActivity(new Intent(getActivity(), AnchorDropListActivity.class));
                    //会长跳转简历管理
                    startActivity(new Intent(getActivity(), ResumeManageActivity.class));
                } else {
                    //管理员跳转职位收藏
                    Intent intent9 = new Intent(getActivity(), MyCollectResumeActivity.class);
                    startActivity(intent9);
                }
                break;
            case R.id.ll_3:
                //主播身份  上传简历获得五元红包只显示一次
                if (UserConfig.getInstance().getRole().equals("2")) {
                    if (UserConfig.getInstance().getIsresume().equals("0")) {
                        startActivity(new Intent(getActivity(), AddInviteActivity.class));
                    } else {
                        startActivity(new Intent(getActivity(), ChangeAnchorDetailActivity.class).putExtra("id", resume_id));
                    }
                } else if (UserConfig.getInstance().getRole().equals("1")) {
                    Intent intent = new Intent(getActivity(), RemainderActivity.class);
                    intent.putExtra("residue_num", residue_num);
                    startActivity(intent);
//                    startActivity(new Intent(getActivity(), SelectPayNumberActivity.class));
                } else {
                    showPopupWindowSevenDays();
                }
                break;
            //个人信息进行修改
            case R.id.image_update:
                if (PointUtils.isFastClick()) {
                    Intent intent2 = new Intent(getActivity(), SettingPersonalDataActivity.class);
                    startActivity(intent2);
                }
                break;

            case R.id.reload:
                if (PointUtils.isFastClick()) {
                    if (!NetWorkUtil.isNetworkConnected(getActivity())) {
                        rl_net_info.setVisibility(View.GONE);
                        layout_network_break.setVisibility(View.VISIBLE);
                        ToastUtil.showShort(getResources().getString(R.string.search_not_net));
                    } else {
                        rl_net_info.setVisibility(View.VISIBLE);
                        layout_network_break.setVisibility(View.GONE);
                        if (mProgressDialog == null) {
                            mProgressDialog = new ProgressDialogFragment();
                        }
                        mProgressDialog.show(getActivity().getSupportFragmentManager());
                        mPresenter.accountData(UserConfig.getInstance().getMobile(), UserConfig.getInstance().getPwd());
                    }
                }
                break;
            case R.id.iv1:
                if (PointUtils.isFastClick()) {
                    if (UserConfig.getInstance().getRole().equals("1")) {
                        //会长跳转公会管理
                        startActivity(new Intent(getActivity(), GuildManagerActivity.class));
                    } else if (UserConfig.getInstance().getRole().equals("2")) {
                        //主播跳转公会绑定
                        ActivityUtils.startActivity(getActivity(), MyGuildActivity.class);
                    } else {
                        //管理跳转公会绑定
                        ActivityUtils.startActivity(getActivity(), MyGuildActivity.class);
                    }
                }
                break;
            case R.id.iv2:
                if (PointUtils.isFastClick()) {
                    if (UserConfig.getInstance().getRole().equals("1")) {
                        //会长跳转主播申请
                        startActivity(new Intent(getActivity(), AnchorChectListActivity.class));
                    } else if (UserConfig.getInstance().getRole().equals("2")) {
                        //主播跳转快速绑定
                        ActivityUtils.startActivity(getActivity(), FastAnchorBindingActivity.class);
                    } else {
                        //管理员跳转辅导与任务
                        ActivityUtils.startActivity(getActivity(), MyManagerActivity.class);
                    }
                }
                break;
            case R.id.iv3:
                if (PointUtils.isFastClick()) {
                    if (UserConfig.getInstance().getRole().equals("1")) {
                        //会长跳转签约直通车
                        startActivity(new Intent(getActivity(), SignActivity.class));
                    } else if (UserConfig.getInstance().getRole().equals("2")) {
                        //会长跳转签约直通车
                        startActivity(new Intent(getActivity(), SignActivity.class));
                    } else {
                        //管理员跳转组织架构
                        ActivityUtils.startActivity(getActivity(), CompamyInfoActivity.class);
                    }
                }
                break;
            //会长跳转vip界面
            case R.id.iv4:
                if (UserConfig.getInstance().getRole().equals("1")) {
                    ActivityUtils.startActivity(getActivity(), VIPActivity.class);
                }
                break;
            //跳转我的收藏界面
            case R.id.iv8:
                if (PointUtils.isFastClick()) {
                    startActivity(new Intent(getActivity(), MyCollectActivity.class));
                }
                break;
            //跳转我的关注界面
            case R.id.iv9:
                if (PointUtils.isFastClick()) {
                    startActivity(new Intent(getActivity(), MyFocusActivity.class));
                }
                break;
            //关于我们
            case R.id.iv10:
                if (PointUtils.isFastClick()) {
                    startActivity(new Intent(getActivity(), AcitivityAboutUs.class));
                }
                break;
            //联系客服
            case R.id.iv11:
                if (PointUtils.isFastClick()) {
                    startActivity(new Intent(getActivity(), MyCustomerServiceActivity.class));
//                    showPopupWindowSevenDays();
                }
                break;
            //设置界面
            case R.id.ll_wode_shezhi:
                if (PointUtils.isFastClick()) {
                    ActivityUtils.startActivity(getActivity(), MySettingActivity.class);
                }
                break;
            //跳转到消息页面
            case R.id.ll_wode_xiaoxi:
                if (PointUtils.isFastClick()) {
                    ActivityUtils.startActivity(getActivity(), MessagesActivityNew.class);
                }
                break;
            //跳转到福利页面
            case R.id.iv_fuli:
                if (PointUtils.isFastClick()) {
                    if (UserConfig.getInstance().getRole().equals("1")) {
                        //会长跳转
                        ActivityUtils.startActivity(getActivity(), WelfareActivity.class);
                    } else if (UserConfig.getInstance().getRole().equals("2")) {
                        //主播跳转
                        ActivityUtils.startActivity(getActivity(), UserBenefitsActivity.class);
                    } else {
                        //管理跳
                    }
                }
                break;
            //跳转到完善公司首页
            case R.id.company_homepage:
                Log.d("Debug", "company_is_perfect的状态为" + company_is_perfect);
                if (PointUtils.isFastClick()) {
                    if (company_is_perfect == 1) {
                        ActivityUtils.startActivity(getActivity(), CompanyHomepageActivity.class);
                    } else {
                        Intent intent = new Intent(getActivity(), AddCompanyInfoActivity2.class);
                        intent.putExtra("IsFirstFabuJob", true);
                        intent.putExtra("IsFromMine", true);
                        startActivity(intent);
                    }
                }
                break;
            //跳转到主播收藏页
            case R.id.ll_4:
                if (PointUtils.isFastClick()) {
                    ActivityUtils.startActivity(getActivity(), HostCollectionActivity.class);
                }
                break;
            //跳转到完善公司首页
            case R.id.ll_company:
                Log.d("Debug", "company_is_perfect的状态为" + company_is_perfect);
                if (PointUtils.isFastClick()) {
                    if (company_is_perfect == 1) {
                        ActivityUtils.startActivity(getActivity(), CompanyHomepageActivity.class);
                    } else {
                        Intent intent = new Intent(getActivity(), AddCompanyInfoActivity2.class);
                        intent.putExtra("IsFirstFabuJob", true);
                        intent.putExtra("IsFromMine", true);
                        startActivity(intent);
                    }
                }

                break;
            //跳转到波豆
            case R.id.ll_bd:
                if (PointUtils.isFastClick()) {
                    ActivityUtils.startActivity(getActivity(), VirtualCoinActivity.class);
                }
                break;
        }
    }

    private PopupWindow mPopWindowSelectdays;

    /**
     * 发布职位弹窗
     */
    private void showPopupWindowSevenDays() {
        //设置contentView
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.add_boss_info, null);
        mPopWindowSelectdays = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, false);
        mPopWindowSelectdays.setContentView(contentView);
//        //设置各个控件的点击响应
        final TextView tv1 = contentView.findViewById(R.id.tv1);
        final TextView tv2 = contentView.findViewById(R.id.tv2);
        TextView tv_phone = contentView.findViewById(R.id.tv_phone);
        TextView tv_weixin = contentView.findViewById(R.id.tv_weixin);
        RelativeLayout rl1 = contentView.findViewById(R.id.rl1);

//        //显示PopupWindow
        tv_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + tv1.getText().toString());
                intent.setData(data);
                startActivity(intent);
                mPopWindowSelectdays.dismiss();
            }
        });
        tv_weixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ClipboardManager clip = (ClipboardManager) GlobalApp.getAppContext().getSystemService(Context.CLIPBOARD_SERVICE);
                clip.getText(); // 粘贴
                clip.setText(tv2.getText().toString() + ""); // 复制
                ToastUtil.showShort("已复制成功");
                mPopWindowSelectdays.dismiss();
            }
        });
        rl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopWindowSelectdays.dismiss();
            }
        });
        View rootview = LayoutInflater.from(getActivity()).inflate(R.layout.new_fragment_boss, null);
        mPopWindowSelectdays.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {   // 不在最前端显示 相当于调用了onPause();

        } else {  // 在最前端显示 相当于调用了onResume();
            Log.d("Debug", "在最前端显示 相当于调用了onResume();");
            nac_layout.setText(UserConfig.getInstance().getNickname() + "");
            if (!NetWorkUtil.isNetworkConnected(getActivity())) {
                rl_net_info.setVisibility(View.VISIBLE);
                layout_network_break.setVisibility(View.GONE);
                ToastUtil.showShort(getResources().getString(R.string.search_not_net));
            } else {
                rl_net_info.setVisibility(View.VISIBLE);
                layout_network_break.setVisibility(View.GONE);
                if (mProgressDialog == null) {
                    mProgressDialog = new ProgressDialogFragment();
                }
                mProgressDialog.show(getActivity().getSupportFragmentManager());
                mPresenter.accountData(UserConfig.getInstance().getMobile(), UserConfig.getInstance().getPwd());
            }
            if (UserConfig.getInstance().getRole().equals("2")) {
                tv21.setText("沟通过");
            } else if (UserConfig.getInstance().getRole().equals("1")) {
                tv21.setText("职位管理");
            }
        }
    }

}