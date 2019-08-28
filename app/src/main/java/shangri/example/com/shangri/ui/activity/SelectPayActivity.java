package shangri.example.com.shangri.ui.activity;


import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.text.ParseException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.alipay.AliPayTools;
import shangri.example.com.shangri.alipay.OnRequestListener;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.WeiXInpay.WeChatPayInfo;
import shangri.example.com.shangri.model.bean.event.BrowseEventBean;
import shangri.example.com.shangri.model.bean.response.DaysPriceBean;
import shangri.example.com.shangri.model.bean.response.LegalPayResponseTaskBean;
import shangri.example.com.shangri.model.bean.response.PayResponseTaskBean;
import shangri.example.com.shangri.model.bean.response.timeBean;
import shangri.example.com.shangri.presenter.PayPresenter;
import shangri.example.com.shangri.presenter.view.PayView;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.view.CircleImageView;
import shangri.example.com.shangri.util.BitmapCache;
import shangri.example.com.shangri.util.PointUtils;
import shangri.example.com.shangri.util.TimeUtil;
import shangri.example.com.shangri.util.ToastUtil;
import shangri.example.com.shangri.weixin.WXPay;

/**
 *选择支付
 * Created by admin on 2017/12/22.
 */

public class SelectPayActivity extends BaseActivity<PayView, PayPresenter> implements PayView {


    @BindView(R.id.setting_back)
    ImageView settingBack;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.im_icon)
    CircleImageView imIcon;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.tv_2)
    TextView tv2;
    @BindView(R.id.tv_3)
    TextView tv3;
    @BindView(R.id.tv_4)
    TextView tv4;
    @BindView(R.id.ll_1)
    LinearLayout ll1;
    @BindView(R.id.tv_5)
    TextView tv5;
    @BindView(R.id.tv_6)
    TextView tv6;
    @BindView(R.id.tv_7)
    TextView tv7;
    @BindView(R.id.tv_8)
    TextView tv8;
    @BindView(R.id.ll_2)
    LinearLayout ll2;
    @BindView(R.id.tv_9)
    TextView tv9;
    @BindView(R.id.tv_10)
    TextView tv10;
    @BindView(R.id.tv_11)
    TextView tv11;
    @BindView(R.id.tv_12)
    TextView tv12;
    @BindView(R.id.ll_3)
    LinearLayout ll3;
    @BindView(R.id.im_1)
    ImageView im1;
    @BindView(R.id.weixin_select)
    ImageView weixinSelect;
    @BindView(R.id.rl_weixin)
    RelativeLayout rlWeixin;
    @BindView(R.id.im_2)
    ImageView im2;
    @BindView(R.id.alipay_select)
    ImageView alipaySelect;
    @BindView(R.id.rl_alipay)
    RelativeLayout rlAlipay;
    @BindView(R.id.tv_exit)
    TextView tvExit;
    //支付方式2支付宝1微信 3ios内购
    int pay_type = 1;
    //1:一个月 3:三个月 6:六个月
    String package_type = "6";

    String sixMoney = "";
    String threeMoney = "";
    String oneMoney = "";
    String price = "";

    private ProgressDialogFragment mProgressDialog;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_selectpay_layout;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_selectpay_layout;
    }

    @Override
    protected PayPresenter createPresenter() {
        return new PayPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        tvName.setText(UserConfig.getInstance().getUserName() + "");
        BitmapCache.getInstance().loadBitmaps(imIcon, UserConfig.getInstance().getAvatar(), null);
        String time = getIntent().getStringExtra("time");
        if (time.length() == 0) {
            tvTime.setText("您还不是我们的vip宝宝哦");
        } else {
            tvTime.setText("到期时间：" + time);
        }
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialogFragment();
        }
        mProgressDialog.show(this.getSupportFragmentManager());
        mPresenter.orderGuild();
    }


    @Override
    public void requestFailed(String message) {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.setting_back, R.id.ll_1, R.id.ll_2, R.id.ll_3, R.id.rl_weixin, R.id.rl_alipay, R.id.tv_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_back:
                finish();
                break;
            case R.id.ll_1:
                if (PointUtils.isFastClick()) {
                    refushColors();
                    tv1.setTextColor(getResources().getColor(R.color.color_d0a76c));
                    tv2.setTextColor(getResources().getColor(R.color.color_d0a76c));
                    tv3.setTextColor(getResources().getColor(R.color.color_d0a76c));
                    tv4.setTextColor(getResources().getColor(R.color.color_d0a76c));
                    ll1.setBackground(getResources().getDrawable(R.mipmap.tuijian_huang));
                    ll2.setBackground(getResources().getDrawable(R.mipmap.meixuan_taocan));
                    ll3.setBackground(getResources().getDrawable(R.mipmap.meixuan_taocan));
                    price = sixMoney;
                    package_type = "6";
                }
                break;
            case R.id.ll_2:
                if (PointUtils.isFastClick()) {
                    refushColors();
                    tv5.setTextColor(getResources().getColor(R.color.color_d0a76c));
                    tv6.setTextColor(getResources().getColor(R.color.color_d0a76c));
                    tv7.setTextColor(getResources().getColor(R.color.color_d0a76c));
                    tv8.setTextColor(getResources().getColor(R.color.color_d0a76c));
                    ll1.setBackground(getResources().getDrawable(R.mipmap.tuijian_meixuanzhong));
                    ll2.setBackground(getResources().getDrawable(R.mipmap.xuan_taocan));
                    ll3.setBackground(getResources().getDrawable(R.mipmap.meixuan_taocan));
                    price = threeMoney;
                    package_type = "3";
                }
                break;
            case R.id.ll_3:
                if (PointUtils.isFastClick()) {
                    refushColors();
                    tv9.setTextColor(getResources().getColor(R.color.color_d0a76c));
                    tv10.setTextColor(getResources().getColor(R.color.color_d0a76c));
                    tv11.setTextColor(getResources().getColor(R.color.color_d0a76c));
                    tv12.setTextColor(getResources().getColor(R.color.color_d0a76c));
                    ll1.setBackground(getResources().getDrawable(R.mipmap.tuijian_meixuanzhong));
                    ll2.setBackground(getResources().getDrawable(R.mipmap.meixuan_taocan));
                    ll3.setBackground(getResources().getDrawable(R.mipmap.xuan_taocan));
                    price = oneMoney;
                    package_type = "1";
                }
                break;
            case R.id.rl_weixin:
                if (PointUtils.isFastClick()) {
                    weixinSelect.setImageDrawable(getResources().getDrawable(R.mipmap.xuanzhong_3));
                    alipaySelect.setImageDrawable(getResources().getDrawable(R.mipmap.wei_xuanzhong));
                    pay_type = 1;
                }
                break;
            case R.id.rl_alipay:
                if (PointUtils.isFastClick()) {
                    weixinSelect.setImageDrawable(getResources().getDrawable(R.mipmap.wei_xuanzhong));
                    alipaySelect.setImageDrawable(getResources().getDrawable(R.mipmap.xuanzhong_3));
                    pay_type = 2;
                }
                break;
            case R.id.tv_exit:
                if (PointUtils.isFastClick()) {
                    Log.d("Debug", "package_type" + package_type);
                    mPresenter.getorderInfo(package_type, price, pay_type + "");
                }
                break;
        }
    }

    /**
     *
     */
    public void refushColors() {
        tv1.setTextColor(getResources().getColor(R.color.color_999999));
        tv2.setTextColor(getResources().getColor(R.color.color_999999));
        tv3.setTextColor(getResources().getColor(R.color.color_999999));
        tv4.setTextColor(getResources().getColor(R.color.color_999999));
        tv5.setTextColor(getResources().getColor(R.color.color_999999));
        tv6.setTextColor(getResources().getColor(R.color.color_999999));
        tv7.setTextColor(getResources().getColor(R.color.color_999999));
        tv8.setTextColor(getResources().getColor(R.color.color_999999));
        tv9.setTextColor(getResources().getColor(R.color.color_999999));
        tv10.setTextColor(getResources().getColor(R.color.color_999999));
        tv11.setTextColor(getResources().getColor(R.color.color_999999));
        tv12.setTextColor(getResources().getColor(R.color.color_999999));
    }

    /**
     * 微信支付
     */
    private void weChatPay(WeChatPayInfo.DataBean data) {
        String wx_appid = data.getAppid();
//        String wx_appid = "wx030de8785dbafd41";
        //替换为自己的appid
        WXPay.init(getApplicationContext(), wx_appid);      //要在支付前调用
        WXPay.getInstance().doPay(data, new WXPay.WXPayResultCallBack() {
            @Override
            public void onSuccess() {
                ToastUtil.showShort("支付成功");
                EventBus.getDefault().post(new BrowseEventBean());
                mPresenter.memberLate();
//                finish();
            }

            @Override
            public void onError(int error_code) {
                switch (error_code) {
                    case WXPay.NO_OR_LOW_WX:
                        Toast.makeText(getApplication(), "未安装微信或微信版本过低", Toast.LENGTH_SHORT).show();
                        break;
                    case WXPay.ERROR_PAY_PARAM:
                        Toast.makeText(getApplication(), "参数错误", Toast.LENGTH_SHORT).show();
                        break;
                    case WXPay.ERROR_PAY:
                        Toast.makeText(getApplication(), "支付失败", Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplication(), "支付取消", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 支付宝
     */

    String alipay = "";

    public void alipay() {
        AliPayTools.aliPay(SelectPayActivity.this, alipay,//商品描述详情 (用于显示在 支付宝 的交易记录里)
                new OnRequestListener() {
                    @Override
                    public void onSuccess(String s) {
                        EventBus.getDefault().post(new BrowseEventBean());
                        mPresenter.memberLate();
                        ToastUtil.showShort("支付成功");
                    }

                    @Override
                    public void onError(String s) {
                        ToastUtil.showShort("支付失败");
                    }
                });
    }

    /**
     * 获取订单信息
     *
     * @param resultBean
     */
    @SuppressWarnings("JavaDoc")
    @Override
    public void getorderInfo(PayResponseTaskBean resultBean) {
        if (pay_type == 1) {
            WeChatPayInfo.DataBean dataBean = new WeChatPayInfo.DataBean();
            dataBean.setAppid(resultBean.getAppid());
            dataBean.setPartnerid(resultBean.getPartnerid());
            dataBean.setPrepayid(resultBean.getPrepayid());
            dataBean.setNoncestr(resultBean.getNoncestr());
            dataBean.setTimestamp(resultBean.getTimestamp() + "");
            dataBean.setSign(resultBean.getSign());
            weChatPay(dataBean);
        } else {
            alipay = resultBean.getAlipay();
            alipay();
        }
    }

    @Override
    public void getLegalInfo(LegalPayResponseTaskBean resultBean) {

    }

    @Override
    public void recruitPay_package(LegalPayResponseTaskBean resultBean) {

    }

    /**
     * 获取每个月的价格
     *
     * @param resultBean
     */
    @SuppressWarnings("JavaDoc")
    @Override
    public void orderGuild(DaysPriceBean resultBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        if (resultBean.getPricesix() != null && resultBean.getPricesix().length() > 0) {
            tv2.setText(PointUtils.KeepPoint1(Double.parseDouble(resultBean.getPricesix()), 2) + "");
            sixMoney = resultBean.getPricesix();
            tv4.setText("仅需" + PointUtils.KeepPoint1(Double.parseDouble(resultBean.getPricesix()) / 6, 2) + "元/每月");
            price = sixMoney;
        }
        if (resultBean.getPricethree() != null && resultBean.getPricethree().length() > 0) {
            tv6.setText(PointUtils.KeepPoint1(Double.parseDouble(resultBean.getPricethree()), 2) + "");
            tv8.setText("仅需" + PointUtils.KeepPoint1(Double.parseDouble(resultBean.getPricethree()) / 3, 2) + "元/每月");
            threeMoney = resultBean.getPricethree();
        }

        if (resultBean.getPriceone() != null && resultBean.getPriceone().length() > 0) {
            tv10.setText(PointUtils.KeepPoint1(Double.parseDouble(resultBean.getPriceone()), 2) + "");
            tv12.setText("仅需" + PointUtils.KeepPoint1(Double.parseDouble(resultBean.getPriceone()) / 1, 2) + "元/每月");
            oneMoney = resultBean.getPriceone();
        }
    }

    @Override
    public void memberLate(timeBean shareBean) {
        if (shareBean.getMember().getMember_time() != null) {
            try {
                String time = TimeUtil.longToString(Long.parseLong(shareBean.getMember().getMember_time()), "yyyy.MM.dd");
                tvTime.setText("到期时间：" + time);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            showPopupWindowSelect(shareBean.getMember().getMember_time());
        } else {
            Log.d("Debug", "返回到期时间为空");
        }

    }


    private PopupWindow mPopWindowSelect;
    String endtime;

    /**
     * 购买成功弹窗
     */
    private void showPopupWindowSelect(String s) {
        //设置contentView
        View contentView = LayoutInflater.from(SelectPayActivity.this).inflate(R.layout.layout_rl_select1, null);
        mPopWindowSelect = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindowSelect.setContentView(contentView);
//        //设置各个控件的点击响应
        ImageView im_close = contentView.findViewById(R.id.im_close);
        TextView tv_commit = contentView.findViewById(R.id.tv_commit);
        TextView tv_time = contentView.findViewById(R.id.tv_time);

        try {
            endtime = TimeUtil.longToString(Long.parseLong(s), "yyyy.MM.dd");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (s.length() > 0) {
            tv_time.setText("到期时间：" + endtime);
        } else {
            tv_time.setText("到期时间：");
        }
//        //显示PopupWindow
        im_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopWindowSelect.dismiss();
            }
        });
        tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopWindowSelect.dismiss();
            }
        });
        View rootview = LayoutInflater.from(SelectPayActivity.this).inflate(R.layout.activity_patrol, null);
        mPopWindowSelect.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


}
