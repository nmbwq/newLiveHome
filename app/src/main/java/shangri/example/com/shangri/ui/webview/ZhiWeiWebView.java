package shangri.example.com.shangri.ui.webview;

import android.app.AlertDialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;
import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.api.RxApi;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.base.GlobalApp;
import shangri.example.com.shangri.model.bean.response.BannerHomeLookBean;
import shangri.example.com.shangri.model.bean.response.NewCompanyBean;
import shangri.example.com.shangri.model.bean.response.PositionListBean;
import shangri.example.com.shangri.model.bean.response.RecruitDetailBean;
import shangri.example.com.shangri.model.bean.response.ResumeIndexBean;
import shangri.example.com.shangri.presenter.view.CultivatLikePresenter;
import shangri.example.com.shangri.presenter.view.CultivateLikeView;
import shangri.example.com.shangri.ui.activity.AddJobActivity;
import shangri.example.com.shangri.ui.activity.CompanyHomepageActivityTwo;
import shangri.example.com.shangri.ui.activity.UpgradePostBarActivity;
import shangri.example.com.shangri.ui.adapter.PlatSelect1Adapter;
import shangri.example.com.shangri.ui.dialog.BossShareCommontDialog;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.util.CornersTransform;
import shangri.example.com.shangri.util.NetWorkUtil;
import shangri.example.com.shangri.util.TimeUtil;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * Created by chengaofu on 2016/12/16 0016.
 */
public class ZhiWeiWebView extends BaseActivity<CultivateLikeView, CultivatLikePresenter> implements CultivateLikeView {


    String url = "";
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.tv_text)
    TextView tvText;
    @BindView(R.id.tv_text1)
    TextView tvText1;
    @BindView(R.id.tv_update)
    TextView tv_update;
    @BindView(R.id.ll_time)
    LinearLayout ll_time;
    @BindView(R.id.tv_time_hour)
    TextView tv_time_hour;
    @BindView(R.id.tv_time_minutes)
    TextView tv_time_minutes;
    @BindView(R.id.im_delete)
    ImageView imDelete;
    @BindView(R.id.im_update)
    ImageView im_update;
    @BindView(R.id.im_share)
    ImageView im_share;
    @BindView(R.id.tv_exit)
    TextView tvExit;
    @BindView(R.id.ll_bottom)
    RelativeLayout llBottom;
    @BindView(R.id.reload)
    Button reload;
    @BindView(R.id.Im_text)
    ImageView ImText;
    @BindView(R.id.rl_info)
    RelativeLayout rlInfo;

    @BindView(R.id.layout_network_break)
    LinearLayout mNetBreakLayout;
    PositionListBean.ListBean.DataBean bean;
    //1 正常 2审核中 3审核失败 4已关闭 5删除 请求接口的状态
    int type;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;


    @BindView(R.id.im_hot)
    ImageView imHot;
    @BindView(R.id.tv_type_name)
    TextView tvTypeName;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_plat_name)
    TextView tvPlatName;
    @BindView(R.id.tv_createtime)
    TextView tvCreatetime;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.rv_partol)
    RecyclerView rvPartol;
    @BindView(R.id.tv_description)
    TextView tvDescription;
    @BindView(R.id.im_company_photo)
    ImageView imCompanyPhoto;
    @BindView(R.id.tv_company_name)
    TextView tvCompanyName;
    @BindView(R.id.tv_company_number)
    TextView tvCompanyNumber;
    @BindView(R.id.tv_low_money)
    TextView tv_low_money;
    @BindView(R.id.tv_is_guanfang)
    ImageView tv_is_guanfang;
    @BindView(R.id.tv_leixing)
    TextView tv_leixing;


    private ProgressDialogFragment mProgressDialog;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.zhiwei_bosswebview;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.zhiwei_bosswebview;
    }

    @Override
    protected CultivatLikePresenter createPresenter() {
        return new CultivatLikePresenter(this, this);
    }

    public void request() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialogFragment();
        }
        mProgressDialog.show(ZhiWeiWebView.this.getSupportFragmentManager());
        mPresenter.jobStatus(bean.getId() + "", type + "");
    }

    @Override
    protected void initViewsAndEvents() {
        bean = (PositionListBean.ListBean.DataBean) getIntent().getSerializableExtra("bean");
        url = bean.getInfo_url();
        String str = "数量" + bean.getAnchor_send_resume();
        tv1.setTextSize(12);
        tv1.setText(Html.fromHtml(str));
        String str1 = "查看" + bean.getCheck_number_real();
        tv2.setTextSize(12);
        tv2.setText(Html.fromHtml(str1));
        String str2 = "分享" + bean.getShare_number();
        tv3.setTextSize(12);
        tv3.setText(Html.fromHtml(str2));
//        1 正常 2审核中 3审核失败 4已关闭 5已下架
        switch (bean.getStatus()) {
            case 1:
                im_update.setVisibility(View.VISIBLE);
                ll_time.setVisibility(View.GONE);
                im_share.setVisibility(View.VISIBLE);
                tvText.setText("增加职位曝光量，招揽更多主播");
                tvText1.setText("职位栏功能现已更新，可戳升级了解细则");
                tvExit.setText("关闭职位");
                break;
            case 3:
                im_update.setVisibility(View.GONE);
                im_share.setVisibility(View.GONE);
                tvText.setText("职位审核未通过");
                if (bean.getCheck_mark() == null) {
                    tvText1.setText("原因：");
                } else {
                    tvText1.setText("原因：" + bean.getCheck_mark() + "");
                }
                tvExit.setText("重新发布");
                tv_update.setVisibility(View.GONE);
                ll_time.setVisibility(View.GONE);
                break;
            case 4:
                im_update.setVisibility(View.VISIBLE);
                im_share.setVisibility(View.GONE);
                tvText.setText("增加职位曝光量，招揽更多主播");
                tvText1.setText("职位栏功能现已更新，可戳升级了解细则");
                tvExit.setText("开启职位");
                tv_update.setVisibility(View.VISIBLE);
                ll_time.setVisibility(View.GONE);
                break;
            case 5:
                im_update.setVisibility(View.GONE);
                im_share.setVisibility(View.GONE);
                tvText.setText("职位已下架");
                if (bean.getCheck_mark() == null) {
                    tvText1.setText("原因：");
                } else {
                    tvText1.setText("原因：" + bean.getDown_des() + "");
                }
                tvExit.setText("重新发布");
                tv_update.setVisibility(View.GONE);
                ll_time.setVisibility(View.GONE);
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void requestFailed(String message) {

    }

    @Override
    public void praise() {

    }

    @Override
    public void collect() {

    }

    @Override
    public void consultShare() {

    }

    //点击跳转公司 所传的token参数
    String Companytoken = "";
    PlatSelect1Adapter TypeAdapter1 = null;
    List<String> mNewsList2 = new ArrayList<>();

    @Override
    public void getRecruitDetail(RecruitDetailBean resultBean) {


        RecruitDetailBean.XcxBean xxx = resultBean.getXcx().get(0);
        Companytoken = xxx.getToken();
        if (xxx.getIs_ggw() == 1 || xxx.getHot().equals("1")) {
            imHot.setVisibility(View.VISIBLE);
        } else {
            imHot.setVisibility(View.GONE);
        }
        //        发布类型 1系统（爬取的）3 官方（手动发布）
        if (xxx.getPublish_type().equals("3")) {
            tv_is_guanfang.setVisibility(View.VISIBLE);
        } else {
            tv_is_guanfang.setVisibility(View.GONE);
        }
        tv_leixing.setText(xxx.getType_name());

        if (xxx.getTitle().length() > 0) {
            tvTypeName.setText(xxx.getTitle());
        } else {
            tvTypeName.setText(xxx.getType_name());
        }
        //底薪最高价和最低价
        if (Double.parseDouble(xxx.getKeep_pay()) == 0) {
            tv_low_money.setVisibility(View.GONE);
        } else {
            if (Double.parseDouble(xxx.getKeep_pay()) >= 1000) {
                tv_low_money.setText("底薪" + Integer.parseInt(xxx.getKeep_pay()) / 1000 + "K");
            } else {
                tv_low_money.setText("底薪" + Integer.parseInt(xxx.getKeep_pay()));
            }
            tv_low_money.setVisibility(View.VISIBLE);
        }

        tvMoney.setText(Integer.parseInt(xxx.getPay_low()) / 1000 + "K" + " - " + Integer.parseInt(xxx.getPay_high()) / 1000 + "K");
        tvPlatName.setText(xxx.getPlat_name() + "");
        try {
            tvCreatetime.setText("发布时间：" + TimeUtil.longToString(Long.parseLong(xxx.getPublish_time() + ""), "yyyy.MM.dd"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (xxx.getWork_position() == null) {
            tvAddress.setText(xxx.getCompanyInfo().getCompany_name() + "");
        } else {
            tvAddress.setText(xxx.getWork_position() + "    " + xxx.getCompanyInfo().getCompany_name() + "");
        }
        tvDescription.setText(xxx.getJob_description() + "");
        tvCompanyName.setText(xxx.getCompanyInfo().getCompany_name() + "");
        tvCompanyNumber.setText(xxx.getCompanyInfo().getCompany_scale() + "." + xxx.getCompanyInfo().getAnchor_scale() + "位主播");
        Glide.with(this)
                .load(xxx.getCompanyInfo().getLogo())
                .placeholder(R.mipmap.tianjia_tupian)
                .transform(new CornersTransform(this, 10))
                .crossFade()
                .into(imCompanyPhoto);

        if (mNewsList2.size() > 0) {
            mNewsList2.clear();
        }
        if (xxx.getJob_method().length() > 0) {
            String[] split = xxx.getJob_method().split(",");
            for (int i = 0; i < split.length; i++) {
                //            工作方式 1 线上 2 线下 3全部
                if (split[i].equals("1")) {
                    mNewsList2.add("线上");
                } else if (split[i].equals("2")) {
                    mNewsList2.add("线下");
                } else if (split[i].equals("3")) {
                    mNewsList2.add("全部");
                }
            }
        }
        if (xxx.getSalary_type().length() > 0) {
            String[] split2 = xxx.getJob_method().split(",");
//            结算方式 1月结 2周结 3日结 4全部
            for (int i = 0; i < split2.length; i++) {
                if (split2[i].equals("1")) {
                    mNewsList2.add("月结");
                } else if (split2[i].equals("2")) {
                    mNewsList2.add("周结");
                } else if (split2[i].equals("3")) {
                    mNewsList2.add("日结");
                } else if (split2[i].equals("4")) {
                    mNewsList2.add("全部");
                }
            }
        }
        if (xxx.getWelfare() != null) {
            if (xxx.getWelfare().length() > 0) {
                String[] split1 = xxx.getWelfare().split(",");
                for (int i = 0; i < split1.length; i++) {
                    mNewsList2.add(split1[i]);
                }
            }
        }
        TypeAdapter1 = new PlatSelect1Adapter(this, R.layout.item_four1, mNewsList2);
        TypeAdapter1.openLoadAnimation(new ScaleInAnimation());
        rvPartol.setLayoutManager(new GridLayoutManager(this, 4));
        rvPartol.setAdapter(TypeAdapter1);
        //显示倒计时还是显示升级布局
        if (resultBean.getXcx().get(0).getStatus().equals("1")) {
            if (Long.parseLong(resultBean.getExpire_time() + "") > 0) {
                try {
                    long time = Long.parseLong(resultBean.getExpire_time() + "") - TimeUtil.getCurrentTime();
                    ll_time.setVisibility(View.VISIBLE);
                    tv_update.setVisibility(View.GONE);
                    int hours = (int) (time / 60 / 60);
                    int minutes = (int) time / 60 % 60;
                    if (hours > 9) {
                        tv_time_hour.setText(hours + "时");
                    } else {
                        tv_time_hour.setText("0" + hours + "时");
                    }
                    if (minutes > 9) {
                        tv_time_minutes.setText(minutes + "分");
                    } else {
                        tv_time_minutes.setText("0" + minutes + "分");
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            } else {
                tv_update.setVisibility(View.VISIBLE);
                ll_time.setVisibility(View.GONE);
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getRecruitDetail(bean.getId() + "");
    }

    @Override
    public void jobStatus() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        switch (type) {
            case 1:
                ToastUtil.showShort("开启职位成功");
                finish();
                break;
            case 4:
                ToastUtil.showShort("关闭职位成功");
                finish();
                break;
            case 5:
                ToastUtil.showShort("删除职位成功");
                finish();
                break;
        }
    }

    @Override
    public void comment() {

    }

    @Override
    public void upAnchor() {

    }

    @Override
    public void residueNumber() {

    }

    @Override
    public void companyAdd(NewCompanyBean resultBean) {

    }

    @Override
    public void commentNum(BannerHomeLookBean resultBean) {

    }

    @Override
    public void resumeDetail(ResumeIndexBean resultBean) {

    }


    @OnClick({R.id.rl_company_info, R.id.reload, R.id.tv_update, R.id.webview_back, R.id.im_update, R.id.im_share, R.id.im_delete, R.id.tv_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_update:
                Intent intent = new Intent(this, UpgradePostBarActivity.class);
                intent.putExtra("recruit_id", bean.getId() + "");
                startActivity(intent);
                break;
            //点击公司跳转
            case R.id.rl_company_info:
                Intent intent1 = new Intent(this, CompanyHomepageActivityTwo.class);
                intent1.putExtra("COMPANY_TOKEN", Companytoken);
                startActivity(intent1);
                break;
            case R.id.reload:
                break;
            case R.id.webview_back:
                finish();
                break;
            case R.id.im_update:
                startActivity(new Intent(this, AddJobActivity.class).putExtra("bean", bean).putExtra("a", true));
                finish();
                break;
            case R.id.im_share:
                showDialog();
                break;
            case R.id.im_delete:
                type = 5;
                showPopupWindow1();
                break;
            case R.id.tv_exit:
                switch (bean.getStatus()) {
                    case 1:
//                        ToastUtil.showShort("关闭操作");
                        type = 4;
                        request();
                        break;
                    case 3:
//                        ToastUtil.showShort("重新发布");
                        startActivity(new Intent(this, AddJobActivity.class).putExtra("bean", bean).putExtra("a", true));
                        finish();
                        break;
                    case 4:
                        type = 1;
                        request();
//                        ToastUtil.showShort("开启职位");
                        break;
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
        View contentView = LayoutInflater.from(ZhiWeiWebView.this).inflate(R.layout.add_boss_info, null);
        mPopWindowSelectdays = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, false);
        mPopWindowSelectdays.setContentView(contentView);
//        //设置各个控件的点击响应
        final TextView tv1 = contentView.findViewById(R.id.tv1);
        final TextView tv2 = contentView.findViewById(R.id.tv2);
        TextView tv_phone = contentView.findViewById(R.id.tv_phone);
        TextView tv_weixin = contentView.findViewById(R.id.tv_weixin);
        TextView tv_content = contentView.findViewById(R.id.tv_content);

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
        View rootview = LayoutInflater.from(ZhiWeiWebView.this).inflate(R.layout.new_fragment_boss, null);
        mPopWindowSelectdays.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }

    //分享弹窗
    AlertDialog alertDialog;

    public void showDialog() {
        String tvcontext = "薪资：" + bean.getPay_low() / 1000 + "k" + "-" + bean.getPay_high() / 1000 + "k" + "\n" + "区域:" + bean.getWork_position();
        String title = "【" + "直播之家" + "】" + bean.getCompany() + "热招" + bean.getType_name();
        alertDialog = BossShareCommontDialog.ShareDialog(ZhiWeiWebView.this, url + "?share=1", tvcontext, title, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
    }


    private static PopupWindow mPopWindow1;

    /**
     * 拨打电话
     */
    private void showPopupWindow1() {
        //设置contentView
        View contentView = LayoutInflater.from(ZhiWeiWebView.this).inflate(R.layout.compact_add_gonghui2, null);
        mPopWindow1 = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindow1.setContentView(contentView);
        //设置各个控件的点击响应
        TextView tv_next = contentView.findViewById(R.id.tv_next);
        TextView tv_cancle = contentView.findViewById(R.id.tv_cancle);
        TextView tv_content = contentView.findViewById(R.id.tv_content);
        tv_content.setText("您确定要删除该职位么？删除后，该职位将不在职位列表中出现。");

        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopWindow1.dismiss();
            }
        });
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                request();
                mPopWindow1.dismiss();
            }
        });
        //显示PopupWindow
        View rootview = LayoutInflater.from(ZhiWeiWebView.this).inflate(R.layout.bosswebview, null);
        mPopWindow1.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }

}

