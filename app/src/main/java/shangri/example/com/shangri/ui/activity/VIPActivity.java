package shangri.example.com.shangri.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.ConvenientBanner2;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.adapter.VIPAdvantageAdapter;
import shangri.example.com.shangri.alipay.AliPayTools;
import shangri.example.com.shangri.alipay.OnRequestListener;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.WeiXInpay.WeChatPayInfo;
import shangri.example.com.shangri.model.bean.event.BrowseEventBean;
import shangri.example.com.shangri.model.bean.response.AccountDataBean;
import shangri.example.com.shangri.model.bean.response.PayResponseTaskBean;
import shangri.example.com.shangri.model.bean.response.VIPAdvantageBean;
import shangri.example.com.shangri.model.bean.response.VIPAdvantageExplain;
import shangri.example.com.shangri.model.bean.response.VIPBannerBean;
import shangri.example.com.shangri.model.bean.response.VIPCardBean;
import shangri.example.com.shangri.presenter.VIPPresenter;
import shangri.example.com.shangri.presenter.view.VIPView;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.popupwindow.MessagePopubWindow;
import shangri.example.com.shangri.ui.popupwindow.SelectPayPopubWindow;
import shangri.example.com.shangri.ui.webview.MessagesWebView;
import shangri.example.com.shangri.util.HeadTakeTurn1;
import shangri.example.com.shangri.util.TakeTurn3;
import shangri.example.com.shangri.util.ToastUtil;
import shangri.example.com.shangri.util.PointUtils;
import shangri.example.com.shangri.weixin.WXPay;

/**
 * VIP首页
 */
public class VIPActivity extends BaseActivity<VIPView, VIPPresenter> implements VIPView {

    @BindView(R.id.back)
    ImageView back;

    @BindView(R.id.iv_image)
    ConvenientBanner bannerPager;

    @BindView(R.id.detail)
    TextView detail;

    @BindView(R.id.rl_renzheng)
    RelativeLayout rl_renzheng;
    @BindView(R.id.vip_card)
    ConvenientBanner2 vip_card;

    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.rl_card)
    RelativeLayout rl_card;


    VIPAdvantageAdapter mAdapter;
    List<VIPAdvantageExplain.Advantages> mList = new ArrayList<>();
    private ProgressDialogFragment mProgressDialog;

    List<ImageView> imageViews = new ArrayList<>();

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_vip;
    }

    @Override
    public void VIPBanner(VIPBannerBean bannerBean) {
        final List<VIPBannerBean.Banner> banners = bannerBean.getBanner();
        if (banners.size() > 0) {
            bannerPager.setVisibility(View.VISIBLE);
            //不设置 下面代码 和百科效果一样（显示三个item） 设置过 只显示一个item
            bannerPager.setMargin();
            if (banners.size() > 1) {
                //开始自动翻页
                if (bannerPager != null) {
                    bannerPager.startTurning(3000);
                }
                bannerPager.setCanLoop(true);
//                rl_card.setPadding(0, 0, 0, 0);
            } else {
                //停止翻页
                if (bannerPager != null) {
                    bannerPager.stopTurning();
                }
                bannerPager.setCanLoop(false);
//                rl_card.setPadding(100, 0, 100, 0);
            }
        } else {
            bannerPager.setVisibility(View.GONE);
        }
        //滚动图
        new HeadTakeTurn1(VIPActivity.this, banners, bannerPager, new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.d("Debug", "点击事件");
                if (PointUtils.isFastClick()) {
                    Log.d("Debug", "点击事件点击的地址是" + banners.get(position).getTo_url());
                    if (banners.get(position).getTo_url().length() > 0) {
                        Intent intent = new Intent(VIPActivity.this, MessagesWebView.class);
                        intent.putExtra("url", banners.get(position).getTo_url());
                        startActivity(intent);
                    }
                }
            }
        });

    }


    @Override
    public void getVIPPackagesList(final VIPCardBean bean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }

        if (bean.getPackages().size() > 1) {
            vip_card.setCanLoop(true);
        } else {
            vip_card.setMargin();
            vip_card.setCanLoop(false);
        }

        new TakeTurn3(this, bean.getPackages(), vip_card, new OnItemClickListener() {
            @Override
            public void onItemClick(final int position) {
            }
        }, new TakeTurn3.onClickBuy() {
            @Override
            public void onClick(final int position) {
                new SelectPayPopubWindow(VIPActivity.this, "", new SelectPayPopubWindow.OnSelector() {
                    @Override
                    public void onSelector(int payType) {
                        pay_type = payType;
                        if (PointUtils.isFastClick()) {
                            if (mProgressDialog == null) {
                                mProgressDialog = new ProgressDialogFragment();
                            }
                            mProgressDialog.show(getSupportFragmentManager());
                            /**
                             * package_id  套餐ID
                             * package_duration  VIP时长（月） 默认1
                             * pay_price  支付金额
                             * pay_method  支付方式
                             */
                            mPresenter.VIPPay(bean.getPackages().get(position).getId(), bean.getPackages().get(position).getValid_month(),
                                    bean.getPackages().get(position).getIs_sales().equals("1") ? bean.getPackages().get(position).getPrice_sales() : bean.getPackages().get(position).getPrice() + "", payType + "");
                        }
                    }
                });
            }
        });
    }

    int pay_type = 2;

    @Override
    public void VIPPay(PayResponseTaskBean resultBean) {
        isRefresh = true;
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        if (pay_type == 2) {
            WeChatPayInfo.DataBean dataBean = new WeChatPayInfo.DataBean();
            dataBean.setAppid(resultBean.getAppid());
            dataBean.setPartnerid(resultBean.getPartnerid());
            dataBean.setPrepayid(resultBean.getPrepayid());
            dataBean.setNoncestr(resultBean.getNoncestr());
            dataBean.setTimestamp(resultBean.getTimestamp() + "");
            dataBean.setSign(resultBean.getSign());
            weChatPay(dataBean);
        } else {
            alipay = resultBean.getPay();
            alipay();
        }
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
                isRefresh = true;
                ToastUtil.showShort("支付成功");
                EventBus.getDefault().post(new BrowseEventBean());
                //支付成功刷新 套餐列表
                mPresenter.getVIPPackagesList();
//                finish();
            }

            @Override
            public void onError(int error_code) {
                isRefresh = true;
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

        AliPayTools.aliPay(this, alipay,//商品描述详情 (用于显示在 支付宝 的交易记录里)
                new OnRequestListener() {
                    @Override
                    public void onSuccess(String s) {
                        //支付成功刷新 套餐列表
                        mPresenter.getVIPPackagesList();
                        EventBus.getDefault().post(new BrowseEventBean());
                        isRefresh = true;
                        ToastUtil.showShort("支付成功");
                    }

                    @Override
                    public void onError(String s) {
                        isRefresh = true;
                        ToastUtil.showShort("支付失败");
                    }
                });
    }

    @Override
    public void getVIPAdvantage(VIPAdvantageBean bean) {

    }

    @Override
    public void accountData(AccountDataBean mAccountDataBean) {
        if (mAccountDataBean.getFace_status() == 1 && mAccountDataBean.getLicense_status() == 1) {
            rl_renzheng.setVisibility(View.GONE);
        } else {
            rl_renzheng.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void getVIPAdvantageExplain(VIPAdvantageExplain bean) {
        mList.clear();
        mAdapter.addAll(bean.getAdvantages());
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_vip;
    }

    @Override
    protected VIPPresenter createPresenter() {
        return new VIPPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialogFragment();
        }
        mProgressDialog.show(getSupportFragmentManager());
        mPresenter.VIPBanner();
        mPresenter.getVIPPackagesList();
        mPresenter.getVIPAdvantageExplain();
        recycleView.setLayoutManager(new GridLayoutManager(this, 2));
        mAdapter = new VIPAdvantageAdapter(this, R.layout.item_vip_explain, mList, new VIPAdvantageAdapter.OnItemClick() {
            @Override
            public void onClick(int position) {
                MessagePopubWindow window = new MessagePopubWindow(VIPActivity.this, mList.get(position).getName(), mList.get(position).getDetail());
                window.show(mList.get(position).getName(), mList.get(position).getDetail());
            }
        });
        recycleView.setAdapter(mAdapter);
//        mPresenter.getVIPPackagesList();
//        mPresenter.getVIPAdvantageExplain();

    }

    @Override
    public void requestFailed(String message) {

    }

    @OnClick({R.id.back, R.id.rl_renzheng, R.id.detail})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.detail:
                //购买明细
                startActivity(new Intent(this, BuyVIPDetailActivity.class));
                break;
            case R.id.rl_renzheng:
                //去认证
                isRefresh = true;
                startActivity(new Intent(this, CompanyCertificationActivity.class));
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    boolean isRefresh = false;

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.accountData();
        if (isRefresh) {
            mPresenter.getVIPPackagesList();
            mPresenter.getVIPAdvantageExplain();
            isRefresh = !isRefresh;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //停止翻页
        if (bannerPager != null) {
            bannerPager.stopTurning();
        }
    }
}
