package shangri.example.com.shangri.ui.adapter;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.base.GlobalApp;
import shangri.example.com.shangri.model.bean.event.TellAboutBean;
import shangri.example.com.shangri.model.bean.response.tellAboutResponseBean;
import shangri.example.com.shangri.model.bean.response.transferBean;
import shangri.example.com.shangri.ui.activity.ChangeAnchorDetailActivity;
import shangri.example.com.shangri.ui.activity.CompanyHomepageActivityTwo;
import shangri.example.com.shangri.ui.activity.LookAnchorDetailActivity;
import shangri.example.com.shangri.util.AndroidInterface.AnchorChectFace;
import shangri.example.com.shangri.util.BitmapCache;
import shangri.example.com.shangri.util.CornersTransform;
import shangri.example.com.shangri.util.PointUtils;
import shangri.example.com.shangri.util.TimeUtil;
import shangri.example.com.shangri.util.ToastUtil;
import shangri.example.com.shangri.util.ViewHolder;

/**
 * Created by Administrator on 2018/1/3.
 */

public class TellAboutAdapter extends BaseListAdapter<tellAboutResponseBean.ListBean.DataBean> {
    private Context mContext;
    private Animation mLikeAnim;
    List<tellAboutResponseBean.ListBean.DataBean> data = new ArrayList<>();
    //    MyAnchoritemAdapter mAdapter;
    AnchorChectFace anchorChectFaces;
    transferBean MessInfobean;


    public TellAboutAdapter(Context context, transferBean info, int layoutId, List<tellAboutResponseBean.ListBean.DataBean> datas, AnchorChectFace anchorChectFace) {
        super(context, layoutId, datas);
        mContext = context;
        data = datas;
        MessInfobean = info;
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
        anchorChectFaces = anchorChectFace;
    }

    /**
     * 跟新数据
     *
     * @param message
     */
    public void upDateInfo(transferBean message) {
        MessInfobean = message;
    }

    @Override
    public void convert(ViewHolder helper, final tellAboutResponseBean.ListBean.DataBean bean) {
        RelativeLayout item_boss_head = helper.getView(R.id.item_boss_head);
        RelativeLayout only_content_layout = helper.getView(R.id.only_content_layout);
        RelativeLayout only_jianli_layout = helper.getView(R.id.only_jianli_layout);
        RelativeLayout my_telephoneandwx_layout = helper.getView(R.id.my_telephoneandwx_layout);
        RelativeLayout other_telephoneandwx_layout = helper.getView(R.id.other_telephoneandwx_layout);

        //普通消息控件
        TextView tv_onlycontent_time = helper.getView(R.id.tv_onlycontent_time);
        ImageView my_onlycontent_photo1 = helper.getView(R.id.my_onlycontent_photo1);
        ImageView im_onlycontent_sanjiao1 = helper.getView(R.id.im_onlycontent_sanjiao1);
        TextView tv_text1 = helper.getView(R.id.tv_text1);
        ImageView im_onlycontent_sanjiao2 = helper.getView(R.id.im_onlycontent_sanjiao2);
        ImageView my_onlycontent_photo2 = helper.getView(R.id.my_onlycontent_photo2);
        TextView tv_onlycontent_isreade = helper.getView(R.id.tv_onlycontent_isreade);
        TextView tv_content = helper.getView(R.id.tv_content);
        //图片布局控件
        TextView tv_jianli_time = helper.getView(R.id.tv_jianli_time);
        ImageView my_jinli_photo1 = helper.getView(R.id.my_jinli_photo1);
        ImageView im_jianli_sanjiao1 = helper.getView(R.id.im_jianli_sanjiao1);
        TextView tv_jianli_alertmessage = helper.getView(R.id.tv_jianli_alertmessage);
        ImageView im_jianli_sanjiao2 = helper.getView(R.id.im_jianli_sanjiao2);
        ImageView my_jianli_photo2 = helper.getView(R.id.my_jianli_photo2);
        TextView tv_jianli_isreade = helper.getView(R.id.tv_jianli_isreade);
        final ImageView rl_jinli_image = helper.getView(R.id.rl_jinli_image);
        TextView tv_jianli_name = helper.getView(R.id.tv_jianli_name);
        TextView tv_jianli_text = helper.getView(R.id.tv_jianli_text);
        //我发出的电话以及微信
        TextView tv_myphwx_time = helper.getView(R.id.tv_myphwx_time);
        TextView tv_myphwx_text = helper.getView(R.id.tv_myphwx_text);
        TextView tv_myphwx_isreade = helper.getView(R.id.tv_myphwx_isreade);
        ImageView my_myphwx_photo2 = helper.getView(R.id.my_myphwx_photo2);
        //别人发出的电话以及微信
        TextView tv_otherphwx_time = helper.getView(R.id.tv_otherphwx_time);
        TextView tv_otherphwx_text = helper.getView(R.id.tv_otherphwx_text);
        TextView tv_tlorwx_text = helper.getView(R.id.tv_tlorwx_text);
        TextView tv_otherphwx_isreade = helper.getView(R.id.tv_otherphwx_isreade);
        ImageView my_otherphwx_photo1 = helper.getView(R.id.my_otherphwx_photo1);
//        5职位信息布局控件
        ImageView imHot = helper.getView(R.id.im_hot);
        TextView tvAnchorName = helper.getView(R.id.tv_anchor_name);
        TextView tvXianxia = helper.getView(R.id.tv_xianxia);
        TextView tvWeek = helper.getView(R.id.tv_week);
        TextView tvMoney = helper.getView(R.id.tv_money);
        TextView tvPlatName = helper.getView(R.id.tv_plat_name);
        TextView tvWelface = helper.getView(R.id.tv_welface);
        TextView tvLowMoney = helper.getView(R.id.tv_low_money);
        TextView tvAdress = helper.getView(R.id.tv_adress);
        TextView tvCompanyName = helper.getView(R.id.tv_company_name);
        ImageView imIsGuanfang = helper.getView(R.id.im_is_guanfang);
//        消息类型 1 普通内容 2图片 3电话 4微信 5职位信息
        switch (bean.getMessage_type()) {
            case 1:
            case 6:
            case 7:
                item_boss_head.setVisibility(View.GONE);
                only_content_layout.setVisibility(View.VISIBLE);
                only_jianli_layout.setVisibility(View.GONE);
                my_telephoneandwx_layout.setVisibility(View.GONE);
                other_telephoneandwx_layout.setVisibility(View.GONE);
                tv_onlycontent_time.setText(TimeUtil.getTtime(Long.parseLong(bean.getCreate_time() + "")));
                tv_text1.setText(bean.getContent() + "");
//                是否已读 1未读 2已读
                if (bean.getIs_read() == 1) {
                    tv_onlycontent_isreade.setText("未读");
                } else {
                    tv_onlycontent_isreade.setText("已读");
                }
                //如果一样  说明是本人 右面布局显示 左面布局隐藏
                if (UserConfig.getInstance().getRole().equals(bean.getSend_type() + "")) {
                    my_onlycontent_photo1.setVisibility(View.GONE);
                    my_onlycontent_photo2.setVisibility(View.VISIBLE);
                    im_onlycontent_sanjiao1.setVisibility(View.GONE);
                    im_onlycontent_sanjiao2.setVisibility(View.VISIBLE);
                    tv_text1.setBackground(mContext.getResources().getDrawable(R.drawable.color_dialog_commit_shape2));
                    tv_onlycontent_isreade.setVisibility(View.VISIBLE);
                    //会长身份  下面提示文字显示  主播身份隐藏 会长身份显示会长头像 主播显示主播头像
                    if (UserConfig.getInstance().getRole().equals("1")) {
//                        tv_content.setVisibility(View.VISIBLE);
                        BitmapCache.getInstance().loadBitmaps(my_onlycontent_photo2, MessInfobean.guild_avatar, null);
                        imageClick(my_onlycontent_photo2, true, "");
                    } else {
//                        tv_content.setVisibility(View.GONE);
                        BitmapCache.getInstance().loadBitmaps(my_onlycontent_photo2, MessInfobean.anchor_avatar, null);

                        imageClick(my_onlycontent_photo2, false, "");

                    }
                } else {
                    my_onlycontent_photo1.setVisibility(View.VISIBLE);
                    my_onlycontent_photo2.setVisibility(View.GONE);
                    im_onlycontent_sanjiao1.setVisibility(View.VISIBLE);
                    im_onlycontent_sanjiao2.setVisibility(View.GONE);
                    tv_text1.setBackground(mContext.getResources().getDrawable(R.drawable.color_dialog_cancle_shape15));
                    tv_onlycontent_isreade.setVisibility(View.GONE);
                    if (UserConfig.getInstance().getRole().equals("1")) {
//                        tv_content.setVisibility(View.VISIBLE);
                        BitmapCache.getInstance().loadBitmaps(my_onlycontent_photo1, MessInfobean.anchor_avatar, null);
                        imageClick(my_onlycontent_photo1, false, "");

                    } else {
//                        tv_content.setVisibility(View.GONE);
                        BitmapCache.getInstance().loadBitmaps(my_onlycontent_photo1, MessInfobean.guild_avatar, null);
                        imageClick(my_onlycontent_photo1, true, "");
                    }
                }
                break;
            case 2:
                item_boss_head.setVisibility(View.GONE);
                only_content_layout.setVisibility(View.GONE);
                only_jianli_layout.setVisibility(View.VISIBLE);
                my_telephoneandwx_layout.setVisibility(View.GONE);
                other_telephoneandwx_layout.setVisibility(View.GONE);

                tv_jianli_time.setText(TimeUtil.getTtime(Long.parseLong(bean.getCreate_time() + "")));

                tv_jianli_alertmessage.setText(bean.getContent() + "");
//                是否已读 1未读 2已读
                if (bean.getIs_read() == 1) {
                    tv_jianli_isreade.setText("未读");
                } else {
                    tv_jianli_isreade.setText("已读");
                }
//              if (){
                tv_jianli_name.setText(MessInfobean.anchor_nickname + "");
                Glide.with(mContext)
                        .load(bean.getImg_url() + "")
                        .placeholder(R.mipmap.gongzuo_hao)
                        .transform(new CornersTransform(mContext, 10))
                        .crossFade()
                        .into(rl_jinli_image);
                rl_jinli_image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EventBus.getDefault().post(new TellAboutBean("2"));
                        imageClick(rl_jinli_image, false, bean.getChat_id() + "");

                    }
                });
                //如果一样  说明是本人 右面布局显示 左面布局隐藏
                if (UserConfig.getInstance().getRole().equals(bean.getSend_type() + "")) {
                    my_jinli_photo1.setVisibility(View.GONE);
                    my_jianli_photo2.setVisibility(View.VISIBLE);
                    im_jianli_sanjiao1.setVisibility(View.GONE);
                    im_jianli_sanjiao2.setVisibility(View.VISIBLE);
                    tv_jianli_alertmessage.setBackground(mContext.getResources().getDrawable(R.drawable.color_dialog_commit_shape2));
                    tv_jianli_isreade.setVisibility(View.VISIBLE);
                    if (UserConfig.getInstance().getRole().equals("1")) {
                        tv_jianli_text.setText("对方成功向您发送了简历");
                        BitmapCache.getInstance().loadBitmaps(my_jianli_photo2, MessInfobean.guild_avatar, null);
                        imageClick(my_jianli_photo2, true, "");
                    } else {
                        tv_jianli_text.setText("您已成功向对方发送了简历");
                        BitmapCache.getInstance().loadBitmaps(my_jianli_photo2, MessInfobean.anchor_avatar, null);
                        imageClick(my_jianli_photo2, false, "");
                    }
                } else {
                    my_jinli_photo1.setVisibility(View.VISIBLE);
                    my_jianli_photo2.setVisibility(View.GONE);
                    im_jianli_sanjiao1.setVisibility(View.VISIBLE);
                    im_jianli_sanjiao2.setVisibility(View.GONE);
                    tv_jianli_alertmessage.setBackground(mContext.getResources().getDrawable(R.drawable.color_dialog_cancle_shape15));
                    tv_jianli_isreade.setVisibility(View.GONE);
                    if (UserConfig.getInstance().getRole().equals("1")) {
                        tv_jianli_text.setText("对方成功向您发送了简历");
                        BitmapCache.getInstance().loadBitmaps(my_jinli_photo1, MessInfobean.anchor_avatar, null);
                        imageClick(my_jinli_photo1, false, "");
                    } else {
                        tv_jianli_text.setText("您已成功向对方发送了简历");
                        BitmapCache.getInstance().loadBitmaps(my_jinli_photo1, MessInfobean.guild_avatar, null);
                        imageClick(my_jinli_photo1, true, "");

                    }
                }
                break;
            case 3:
            case 4:
                item_boss_head.setVisibility(View.GONE);
                only_content_layout.setVisibility(View.GONE);
                only_jianli_layout.setVisibility(View.GONE);
                //如果一样  说明是本人 右面布局显示 左面布局隐藏
                if (UserConfig.getInstance().getRole().equals(bean.getSend_type() + "")) {
                    my_telephoneandwx_layout.setVisibility(View.VISIBLE);
                    other_telephoneandwx_layout.setVisibility(View.GONE);
                    tv_myphwx_time.setText(TimeUtil.getTtime(Long.parseLong(bean.getCreate_time() + "")));
//                    是否已读 1未读 2已读
                    if (bean.getIs_read() == 1) {
                        tv_myphwx_isreade.setText("未读");
                    } else {
                        tv_myphwx_isreade.setText("已读");
                    }
                    tv_myphwx_text.setText(bean.getContent() + ":" + bean.getContact_way());
//                    发送人与身份状态一样  会长或是主播发送人 是自己发信息  所以显示自己的头像
                    if (UserConfig.getInstance().getRole().equals("1")) {
                        BitmapCache.getInstance().loadBitmaps(my_myphwx_photo2, MessInfobean.guild_avatar, null);
                        imageClick(my_myphwx_photo2, true, "");
                    } else {
                        BitmapCache.getInstance().loadBitmaps(my_myphwx_photo2, MessInfobean.anchor_avatar, null);
                        imageClick(my_myphwx_photo2, false, "");
                    }
                } else {
                    my_telephoneandwx_layout.setVisibility(View.GONE);
                    other_telephoneandwx_layout.setVisibility(View.VISIBLE);
                    tv_otherphwx_isreade.setVisibility(View.GONE);
                    tv_otherphwx_text.setText(bean.getContent() + ":" + bean.getContact_way());
                    if (bean.getMessage_type() == 3) {
                        tv_tlorwx_text.setText("拨打电话");
                    } else {
                        tv_tlorwx_text.setText("复制微信号");
                    }
                    tv_otherphwx_time.setText(TimeUtil.getTtime(Long.parseLong(bean.getCreate_time() + "")));
                    tv_tlorwx_text.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (bean.getMessage_type() == 3) {
                                Intent intent = new Intent(Intent.ACTION_DIAL);
                                Uri data = Uri.parse("tel:" + bean.getContact_way());
                                intent.setData(data);
                                mContext.startActivity(intent);
                                //调用拨打电话接口 让列表数据刷新
                                EventBus.getDefault().post(new TellAboutBean("1"));
                            } else {
                                ClipboardManager clip = (ClipboardManager) GlobalApp.getAppContext().getSystemService(Context.CLIPBOARD_SERVICE);
                                clip.getText(); // 粘贴
                                clip.setText(bean.getContact_way() + ""); // 复制
                                ToastUtil.showShort("复制成功");
                            }
                        }
                    });
                    // 发送人与身份状态不一样  会长状态或主播状态   是看别人发的信息   所以显示对方的信息
                    if (UserConfig.getInstance().getRole().equals("1")) {
                        BitmapCache.getInstance().loadBitmaps(my_otherphwx_photo1, MessInfobean.anchor_avatar, null);
                        imageClick(my_otherphwx_photo1, false, "");
                    } else {
                        BitmapCache.getInstance().loadBitmaps(my_otherphwx_photo1, MessInfobean.guild_avatar, null);
                        imageClick(my_otherphwx_photo1, true, "");
                    }
                }
                break;
            case 5:
                item_boss_head.setVisibility(View.VISIBLE);
                only_content_layout.setVisibility(View.GONE);
                only_jianli_layout.setVisibility(View.GONE);
                my_telephoneandwx_layout.setVisibility(View.GONE);
                other_telephoneandwx_layout.setVisibility(View.GONE);
                tellAboutResponseBean.ListBean.DataBean.RecruitBean data = bean.getRecruit();
                //        是否广告位 1是 0否
                if (data.getIs_ggw() == 1 || data.getHot().equals("1")) {
                    imHot.setVisibility(View.VISIBLE);
                } else {
                    imHot.setVisibility(View.GONE);
                }
//        发布类型 1系统（爬取的）2 官方（手动发布）
                if (data.getPublish_type().equals("3")) {
                    imIsGuanfang.setVisibility(View.VISIBLE);
                } else {
                    imIsGuanfang.setVisibility(View.GONE);
                }
                switch (data.getJob_method()) {
                    case "1":
                        tvXianxia.setText("线上");
                        break;
                    case "2":
                        tvXianxia.setText("线下");
                        break;
                    case "3":
                        tvXianxia.setText("线上/线下");
                        break;
                }
                switch (data.getSalary_type()) {
                    case "1":
                        tvWeek.setText("月结");
                        break;
                    case "2":
                        tvWeek.setText("周结");
                        break;
                    case "3":
                        tvWeek.setText("日结");
                        break;
                    case "4":
                        tvWeek.setText("月结/周结/日结");
                        break;
                }
                tvMoney.setText(Integer.parseInt(data.getPay_low()) / 1000 + "K" + " - " + Integer.parseInt(data.getPay_high()) / 1000 + "K");
                if (data.getPlat_name().size() == 0) {
                    tvPlatName.setVisibility(View.GONE);
                } else {
                    tvPlatName.setVisibility(View.VISIBLE);
                    tvPlatName.setText(data.getPlat_name().get(0).getPlat_name());
                }

                if (data.getWelfare().size() == 0) {
                    tvWelface.setVisibility(View.GONE);
                } else {
                    tvWelface.setVisibility(View.VISIBLE);
                    if (data.getWelfare().size() == 1) {
                        tvWelface.setText(data.getWelfare().get(0));
                    } else {
                        tvWelface.setText(data.getWelfare().get(0) + " | " + data.getWelfare().get(1));
                    }
                }
                //底薪最高价和最低价
                if (Double.parseDouble(data.getKeep_pay()) == 0) {
                    tvLowMoney.setVisibility(View.GONE);
                } else {
                    if (Double.parseDouble(data.getKeep_pay()) >= 1000) {
                        tvLowMoney.setText("底薪" + Integer.parseInt(data.getKeep_pay()) / 1000 + "K");
                    } else {
                        tvLowMoney.setText("底薪" + Integer.parseInt(data.getKeep_pay()));
                    }
                    tvLowMoney.setVisibility(View.VISIBLE);
                }

                tvAdress.setText(data.getWork_position() + "");
                tvCompanyName.setText(data.getCompany());
                break;
        }

    }

    @Override
    public void convert(ViewHolder helper, tellAboutResponseBean.ListBean.DataBean applysBean, List<Object> payloads) {

    }

    /**
     * 点击用户头像做的操作
     *
     * @param view
     * @param IsSkipCompany
     */

    public void imageClick(ImageView view, final Boolean IsSkipCompany, final String chat_id) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("Debug", "到达点击事件");
                if (PointUtils.isFastClick()) {
                    if (IsSkipCompany) {
                        Intent intent = new Intent(mContext, CompanyHomepageActivityTwo.class);
                        intent.putExtra("COMPANY_TOKEN", MessInfobean.guild_token);
                        mContext.startActivity(intent);
                    } else {
                        Log.d("Debug", "到达跳转的里面");
                        if(UserConfig.getInstance().getRole().equals("1")){
                            if (chat_id.length() > 0) {
                                mContext.startActivity(new Intent(mContext, LookAnchorDetailActivity.class).putExtra("id", MessInfobean.resume_id).putExtra("IsfromTalkabout", true).putExtra("chat_id", chat_id));
                            } else {
                                mContext.startActivity(new Intent(mContext, LookAnchorDetailActivity.class).putExtra("id", MessInfobean.resume_id).putExtra("IsfromTalkabout", false).putExtra("chat_id", chat_id));
                            }
                        }else {
                            mContext.startActivity(new Intent(mContext, ChangeAnchorDetailActivity.class).putExtra("id", MessInfobean.resume_id));
                        }

                    }
                }
            }
        });
    }


}
