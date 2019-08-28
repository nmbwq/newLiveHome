package shangri.example.com.shangri.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;
import com.aspsine.irecyclerview.universaladapter.recyclerview.OnItemClickListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.base.GlobalApp;
import shangri.example.com.shangri.model.bean.event.ResushNumberBean;
import shangri.example.com.shangri.model.bean.request.positionAddBean;
import shangri.example.com.shangri.model.bean.response.BossPlatBean;
import shangri.example.com.shangri.model.bean.response.IsFaceResponse;
import shangri.example.com.shangri.model.bean.response.NewCompanyBean;
import shangri.example.com.shangri.model.bean.response.PositionListBean;
import shangri.example.com.shangri.model.bean.response.changeLightspotBean;
import shangri.example.com.shangri.model.bean.response.welfareListPlatBean;
import shangri.example.com.shangri.presenter.ChairmanRelesePresenter;
import shangri.example.com.shangri.presenter.view.ChairmanReleseView;
import shangri.example.com.shangri.ui.adapter.PlatSelectAdapter;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.view.ChangeAddressPopwindow;
import shangri.example.com.shangri.ui.webview.symbolWebView;
import shangri.example.com.shangri.util.ActivityManager;
import shangri.example.com.shangri.util.KeyBoardUtil;
import shangri.example.com.shangri.util.PointUtils;
import shangri.example.com.shangri.util.ToastUtil;
import shangri.example.com.shangri.wheelviewSelectDays.MyChangeChangeDatePopwindow1;

/**
 * 会长发布职位界面
 * Created by mschen on 2017/6/23.
 */

public class AddJobActivity extends BaseActivity<ChairmanReleseView, ChairmanRelesePresenter> implements ChairmanReleseView {

    @BindView(R.id.setting_back)
    ImageView settingBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_type_name)
    TextView tvTypeName;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.im2)
    ImageView im2;
    @BindView(R.id.tv_plat_text)
    TextView tvPlatText;
    @BindView(R.id.tv_adress_text)
    TextView tvAdressText;
    @BindView(R.id.tv5)
    TextView tv5;
    @BindView(R.id.im_all)
    ImageView imAll;
    @BindView(R.id.im_up)
    ImageView imUp;
    @BindView(R.id.im_down)
    ImageView im_down;
    @BindView(R.id.ll_up)
    LinearLayout llUp;
    @BindView(R.id.ll_address_scope)
    RelativeLayout llAddressScope;
    @BindView(R.id.tv_money_text)
    TextView tvMoneyText;
    @BindView(R.id.tv_low_money_text)
    EditText tvLowMoneyText;
    @BindView(R.id.im_cycle_all)
    ImageView imCycleAll;
    @BindView(R.id.im_month)
    ImageView imMonth;
    @BindView(R.id.im_week)
    ImageView imWeek;
    @BindView(R.id.im_day)
    ImageView imDay;
    @BindView(R.id.tv_describe_text)
    TextView tvDescribeText;
    @BindView(R.id.tv_welfare_text)
    TextView tvWelfareText;
    @BindView(R.id.tv_web)
    TextView tvWeb;
    @BindView(R.id.tv_left)
    TextView tv_left;
    @BindView(R.id.tv_point_text)
    TextView tv_point_text;


    @BindView(R.id.ll_address)
    RelativeLayout ll_address;
    @BindView(R.id.et_zhiwei_name)
    EditText et_zhiwei_name;


    private ProgressDialogFragment mProgressDialog;

    //    职位类型
    private String anchor_type = "";
    //    平台(逗号隔开)
    private String job_plat = "0";
    //工作区域
    private String work_position = "";
    //工作范围 1 线上 2 线下 0全部
    private String job_method = "3";
    //薪资范围(1000-5000)
    private String pay = "";
    //底薪(2000)
    private String keep_pay = "";
    //结算周期 1月结 2周结 3日结 4全部
    private String salary_type = "3";
    //亮点
    private String lightspot = "";
    //详细描述
    private String job_description = "";
    //福利待遇 (逗号隔开)
    private String welfare = "";
    //修改时候需要的id
    String recruit_id = "";

    //职位名称
    String title = "";
    //    剩余发布职位数
    int is_send = 0;

    boolean isFromUpdate;
    PositionListBean.ListBean.DataBean bean;
    //跳转公司完善公司信息需要信息
    String company_name = "";
    String company_description = "";

    String logo = "";
    String company_scale = "";
    String anchor_scale = "";

    //第一次发布职位  没有完善公司信息跳转到这里界面（）
    boolean IsFirstFabuJob = false;

    @Override
    protected ChairmanRelesePresenter createPresenter() {
        return new ChairmanRelesePresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {

        EventBus.getDefault().post(new ResushNumberBean());

        IsFirstFabuJob = getIntent().getBooleanExtra("IsFirstFabuJob", false);

        bean = (PositionListBean.ListBean.DataBean) getIntent().getSerializableExtra("bean");
        isFromUpdate = getIntent().getBooleanExtra("a", false);
        Log.d("Debug", "传过来的boolen类型为" + isFromUpdate);
        if (isFromUpdate) {
            if (IsFirstFabuJob) {
                tvTitle.setText("发布职位（3/3）");
            } else {
                tvTitle.setText("编辑职位");
            }
            anchor_type = bean.getAnchor_type();
            tvTypeName.setText(bean.getType_name());
            job_plat = bean.getJob_plat();
            tvPlatText.setText(bean.getPlat_name().get(0).getPlat_name() + "...");
            work_position = bean.getWork_position();
            tvAdressText.setText(work_position + "");
            et_zhiwei_name.setText(bean.getTitle() + "");
            recruit_id = bean.getId() + "";
            allRefush();
            job_method = bean.getJob_method() + "";
            switch (bean.getJob_method()) {
                case 1:
                    imUp.setImageDrawable(getResources().getDrawable(R.mipmap.xuanzhong_yuan));
                    break;
                case 2:
                    im_down.setImageDrawable(getResources().getDrawable(R.mipmap.xuanzhong_yuan));
                    break;
                case 3:
                    imAll.setImageDrawable(getResources().getDrawable(R.mipmap.xuanzhong_yuan));
                    break;
            }
            pay = bean.getPay_low() + "-" + bean.getPay_high();
            String S = bean.getPay_low() / 1000 + "K" + "-" + bean.getPay_high() / 1000 + "K";
            tvMoneyText.setText(S);
            keep_pay = bean.getKeep_pay() + "";
            tvLowMoneyText.setText(keep_pay);

            salary_type = bean.getSalary_type() + "";
            allRefush1();
            switch (bean.getSalary_type()) {
                case 1:
                    imMonth.setImageDrawable(getResources().getDrawable(R.mipmap.xuanzhong_yuan));
                    break;
                case 2:
                    imWeek.setImageDrawable(getResources().getDrawable(R.mipmap.xuanzhong_yuan));
                    break;
                case 3:
                    imDay.setImageDrawable(getResources().getDrawable(R.mipmap.xuanzhong_yuan));
                    break;
//                case 4:
//                    imCycleAll.setImageDrawable(getResources().getDrawable(R.mipmap.xuanzhong_yuan));
//                    break;
            }
            lightspot = bean.getLightspot();
            Log.d("Debug", "返回的职位亮点是" + lightspot);
            if (lightspot.length() > 0) {
                String[] split = lightspot.split(",");
                tv_point_text.setText(split[0] + "...");
            } else {
                tv_point_text.setText("");
            }
            job_description = bean.getJob_description();
            if (job_description.length() > 5) {
                tvDescribeText.setText(job_description.substring(0, 5) + "...");
            } else {
                tvDescribeText.setText(job_description);
            }
            Log.d("Debug", "返回的职位描述" + job_description);
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < bean.getWelfare().size(); i++) {

                if (sb.length() > 0) {//该步即不会第一位有逗号，也防止最后一位拼接逗号！
                    sb.append(",");
                }
                sb.append(bean.getWelfare().get(i));
            }
            welfare = sb + "";
            tvWelfareText.setText(bean.getWelfare().get(0) + "...");
        } else {
            if (IsFirstFabuJob) {
                tvTitle.setText("发布职位（3/3）");
            } else {
                tvTitle.setText("发布职位");
            }
        }
        mPresenter.platfromType();
        mPresenter.welfareList();
        mPresenter.changeLightspot();
        mPresenter.limitNumber();
        KeyBoardUtil.KeyBoard(AddJobActivity.this, "close");
        String str = "发布职位即表示你已同意遵守" + "<font color='#d0a76c'>《直播之家职位信息发布规则》</font>" + "所有职位会专人进行审核，请仔细阅读发布规则，如有违反可导致信息发布失败。";
        tvWeb.setTextSize(13);
        tvWeb.setText(Html.fromHtml(str));
    }


    @Override
    public void requestFailed(String message) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_add_job_layout;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_add_job_layout;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO: add setContentView(...) invocation
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    @OnClick({R.id.ll_point_select, R.id.tv_left, R.id.ll_up, R.id.setting_back, R.id.ll_type, R.id.tv3, R.id.im2, R.id.ll_platfrom, R.id.ll_address, R.id.ll_all, R.id.im_down, R.id.ll_down, R.id.ll_money, R.id.ll_low_money, R.id.ll_all_cycle, R.id.ll_month, R.id.ll_week, R.id.ll_day, R.id.ll_job_describe, R.id.ll_welfare, R.id.tv_web})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_left:
                KeyBoardUtil.KeyBoard(AddJobActivity.this,"close");
                if (PointUtils.isFastClick()) {
                    if (anchor_type.length() == 0) {
                        ToastUtil.showShort("请选择职位类型");
                        return;
                    }
                    if (et_zhiwei_name.getText().length() == 0) {
                        ToastUtil.showShort("请填写职位名称");
                        return;
                    }
                    if (work_position.length() == 0) {
                        work_position = "";
                    }

                    if (pay.length() == 0) {
                        ToastUtil.showShort("请选择薪资范围");
                        return;
                    }

                    if (lightspot.length() == 0) {
                        ToastUtil.showShort("请选择职位亮点");
                        return;
                    }

                    if (job_description.length() == 0) {
                        ToastUtil.showShort("请填写公司描述");
                        return;
                    }
                    if (welfare.length() == 0) {
                        welfare = "";
                    }
                    if (tvLowMoneyText.getText().length() == 0) {
                        keep_pay = "0";
                    } else {
                        if (tvLowMoneyText.getText().toString().contains("无")) {
                            keep_pay = "0";
                        } else {
                            keep_pay = tvLowMoneyText.getText().toString();
                        }
                    }
                    title = et_zhiwei_name.getText().toString();
                    positionAddBean positionAddBean = new positionAddBean();
                    if (isFromUpdate) {
                        positionAddBean.setType(2 + "");
                        positionAddBean.setRecruit_id(recruit_id);
                    }
                    positionAddBean.setToken(UserConfig.getInstance().getToken());
                    positionAddBean.setAnchor_type(anchor_type);
                    positionAddBean.setJob_plat(job_plat);
                    positionAddBean.setWork_position(work_position);
                    positionAddBean.setJob_method(job_method);
                    positionAddBean.setPay(pay);
                    positionAddBean.setKeep_pay(keep_pay);
                    positionAddBean.setSalary_type(salary_type);
                    positionAddBean.setLightspot(lightspot);
                    positionAddBean.setJob_description(job_description);
                    positionAddBean.setWelfare(welfare);
                    positionAddBean.setTitle(title + "");
                    if (isFromUpdate) {
                        if (mProgressDialog == null) {
                            mProgressDialog = new ProgressDialogFragment();
                        }
                        mProgressDialog.show(AddJobActivity.this.getSupportFragmentManager());
                        mPresenter.updatePosition(positionAddBean);
                    } else {
                        if (is_send > 0) {
                            if (mProgressDialog == null) {
                                mProgressDialog = new ProgressDialogFragment();
                            }
                            mProgressDialog.show(AddJobActivity.this.getSupportFragmentManager());
                            mPresenter.jobAdd(positionAddBean);
                        } else {
                            ToastUtil.showShort("职位发布数量已用完，暂不能发布新的职位");
                        }
                    }
                }
                break;
            case R.id.setting_back:
                finish();
                break;
            //选择亮点
            case R.id.ll_point_select:
                KeyBoardUtil.KeyBoard(AddJobActivity.this,"close");

                showPopupWindow4();
                break;
            case R.id.ll_type:
                KeyBoardUtil.KeyBoard(AddJobActivity.this,"close");

                if (!isFromUpdate) {
                    showPopupWindow2();
                }
                break;
            case R.id.ll_platfrom:
                KeyBoardUtil.KeyBoard(AddJobActivity.this,"close");

                showPopupWindow1();
                break;
            case R.id.ll_address:
                KeyBoardUtil.KeyBoard(AddJobActivity.this,"close");

                if (PointUtils.isFastClick()) {
                    ChangeAddressPopwindow mChangeAddressPopwindow = new ChangeAddressPopwindow(AddJobActivity.this);
                    mChangeAddressPopwindow.setAddress("广东", "深圳", "福田区");
                    mChangeAddressPopwindow.showAtLocation(ll_address, Gravity.BOTTOM, 0, 0);
                    mChangeAddressPopwindow
                            .setAddresskListener(new ChangeAddressPopwindow.OnAddressCListener() {

                                @Override
                                public void onClick(String province, String city, String area) {
                                    // TODO Auto-generated method stub
                                    Log.d("Debug", "返回的地址是" + province + "-" + city + "-" + area);
                                    tvAdressText.setText(province + "-" + city + "-" + area);
                                    work_position = province + "-" + city;
                                }
                            });
                }
                break;
            case R.id.ll_all:
                KeyBoardUtil.KeyBoard(AddJobActivity.this,"close");
                job_method = "3";
                allRefush();
                imAll.setImageDrawable(getResources().getDrawable(R.mipmap.xuanzhong_yuan));
                break;
            case R.id.im_down:

                break;
            case R.id.ll_down:
                KeyBoardUtil.KeyBoard(AddJobActivity.this,"close");

                job_method = "2";
                allRefush();
                im_down.setImageDrawable(getResources().getDrawable(R.mipmap.xuanzhong_yuan));
                break;
            case R.id.ll_up:
                KeyBoardUtil.KeyBoard(AddJobActivity.this,"close");
                job_method = "1";
                allRefush();
                imUp.setImageDrawable(getResources().getDrawable(R.mipmap.xuanzhong_yuan));
                break;
            case R.id.ll_money:
                KeyBoardUtil.KeyBoard(AddJobActivity.this,"close");
                if (PointUtils.isFastClick()) {
                    selectDate1();
                }
                break;
            case R.id.ll_low_money:

                break;
            case R.id.ll_all_cycle:
//                salary_type = "4";
//                allRefush1();
//                imCycleAll.setImageDrawable(getResources().getDrawable(R.mipmap.xuanzhong_yuan));

                break;
            case R.id.ll_month:
                salary_type = "1";
                allRefush1();
                imMonth.setImageDrawable(getResources().getDrawable(R.mipmap.xuanzhong_yuan));
                break;
            case R.id.ll_week:

                salary_type = "2";
                allRefush1();
                imWeek.setImageDrawable(getResources().getDrawable(R.mipmap.xuanzhong_yuan));
                break;
            case R.id.ll_day:

                salary_type = "3";
                allRefush1();
                imDay.setImageDrawable(getResources().getDrawable(R.mipmap.xuanzhong_yuan));
                break;
            case R.id.ll_job_describe:
                KeyBoardUtil.KeyBoard(AddJobActivity.this,"close");
//                ComDescriptionActivity
                Intent intent = new Intent(AddJobActivity.this, ComDescriptionActivity.class);
//                intent.putExtra("lightspot", lightspot);
                intent.putExtra("job_description", job_description);
                startActivityForResult(intent, 1000);
                break;
            case R.id.ll_welfare:
                KeyBoardUtil.KeyBoard(AddJobActivity.this,"close");
                showPopupWindow3();
                break;
            case R.id.tv_web:
                KeyBoardUtil.KeyBoard(AddJobActivity.this,"close");
                if (PointUtils.isFastClick()) {
                    Intent resultIntent5 = new Intent(GlobalApp.getAppContext(), symbolWebView.class);
                    resultIntent5.putExtra("url", "http://www.zhibohome.net/fawu/postJob.html");
                    startActivity(resultIntent5);
                }
                break;
        }
    }


    /**
     * 为了得到传回的数据，必须在前面的Activity中（指MainActivity类）重写onActivityResult方法
     * <p>
     * requestCode 请求码，即调用startActivityForResult()传递过去的值
     * resultCode 结果码，结果码用于标识返回数据来自哪个新Activity
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1000 && resultCode == 1001) {
//            lightspot = data.getStringExtra("lightspot");
            job_description = data.getStringExtra("job_description");
            if (job_description.length() > 5) {
                tvDescribeText.setText(job_description.substring(0, 5) + "...");
            } else {
                tvDescribeText.setText(job_description);
            }
        }
    }

    /**
     * 工作范围 还原
     */
    public void allRefush() {
        imUp.setImageDrawable(getResources().getDrawable(R.mipmap.wei_xuanzhong));
        imAll.setImageDrawable(getResources().getDrawable(R.mipmap.wei_xuanzhong));
        im_down.setImageDrawable(getResources().getDrawable(R.mipmap.wei_xuanzhong));
    }

    /**
     * 结算周期  还原
     */
    public void allRefush1() {
        imDay.setImageDrawable(getResources().getDrawable(R.mipmap.wei_xuanzhong));
        imMonth.setImageDrawable(getResources().getDrawable(R.mipmap.wei_xuanzhong));
        imWeek.setImageDrawable(getResources().getDrawable(R.mipmap.wei_xuanzhong));
        imCycleAll.setImageDrawable(getResources().getDrawable(R.mipmap.wei_xuanzhong));
    }


    @Override
    public void companyAdd(NewCompanyBean resultBean) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    PlatSelectAdapter TypeAdapter = null;
    PlatSelectAdapter TypeAdapter1 = null;
    PlatSelectAdapter TypeAdapter2 = null;
    PlatSelectAdapter TypeAdapter3 = null;
    List<BossPlatBean.PlatfromBean> mNewsList1;
    List<BossPlatBean.PlatfromBean> mNewsList2;
    //福利列表
    List<BossPlatBean.PlatfromBean> mNewsList3;
    //亮点列表
    List<BossPlatBean.PlatfromBean> mNewsList4;
    BossPlatBean BossplatBean;
    welfareListPlatBean welfareListplatBean;

    /**
     * 直播平台、直播类型接口
     *
     * @param mAccountDataBean
     */
    @Override
    public void platfromType(BossPlatBean mAccountDataBean) {
        BossplatBean = mAccountDataBean;
    }

    @Override
    public void welfareList(welfareListPlatBean mAccountDataBean) {
        welfareListplatBean = mAccountDataBean;
    }

    @Override
    public void changeLightspot(changeLightspotBean mAccountDataBean) {
        mNewsList4 = new ArrayList<>();
        for (int i = 0; i < mAccountDataBean.getList().size(); i++) {
            changeLightspotBean.ListBean listBean = mAccountDataBean.getList().get(i);
            BossPlatBean.PlatfromBean platfromBean = new BossPlatBean.PlatfromBean(listBean.getName(), false);
            mNewsList4.add(platfromBean);
        }
    }

    @Override
    public void jobAdd() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        ToastUtil.showShort("添加成功");
        showPopupWindow();
        if (IsFirstFabuJob) {
            ActivityManager.getInstance().finishActivity(AddCompanyInfoActivity2.class);
            ActivityManager.getInstance().finishActivity(CompanyCertificationActivity.class);
        }
    }

    @Override
    public void updatePosition() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        ToastUtil.showShort("修改成功");
        finish();
    }

    @Override
    public void legalIsface(IsFaceResponse resultBean) {

    }

    @Override
    public void limitNumber(IsFaceResponse resultBean) {
        is_send = resultBean.getIs_send();
    }

    //    0是选择薪资  1是选择底薪
    int symbol;

    private void selectDate1() {
        ArrayList<String> arry_years = new ArrayList<String>();
        ArrayList<String> arry_months = new ArrayList<String>();
        ArrayList<String> arry_days = new ArrayList<String>();
        //    0是选择薪资  1是选择底薪
        for (int i = 1; i < 50; i++) {
            arry_months.add(i + "K");
        }

        for (int i = 2; i < 51; i++) {
            arry_days.add(i + "K");
        }

        MyChangeChangeDatePopwindow1 mChangeBirthDialog = new MyChangeChangeDatePopwindow1(this, 0, arry_months, arry_days
        );
        mChangeBirthDialog.showAtLocation(ll_address, Gravity.BOTTOM, 0, 0);
        mChangeBirthDialog.setBirthdayListener(new MyChangeChangeDatePopwindow1.OnBirthListener() {

            @Override
            public void onClick(int symbol, String year, String month, String day) {
                Log.d("Debug", "返回的symbol" + symbol);
                Log.d("Debug", "返回的year" + year);
                Log.d("Debug", "返回的month" + month);
                Log.d("Debug", "返回的day" + day);
                tvMoneyText.setText(month + "-" + day);
                String k = month.replace("K", "");
                String k1 = day.replace("K", "");
                pay = Integer.parseInt(k) * 1000 + "-" + Integer.parseInt(k1) * 1000;
            }
        });
    }

    private static PopupWindow mPopWindow1;

    /**
     * 平台类型
     */
    private void showPopupWindow1() {
        //设置contentView
        final View contentView = LayoutInflater.from(AddJobActivity.this).inflate(R.layout.chairman_add_job_pop, null);
        mPopWindow1 = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindow1.setContentView(contentView);
        TextView tv_type = contentView.findViewById(R.id.tv_type);
        RecyclerView card_recycler = contentView.findViewById(R.id.card_recycler);
        TextView tv_commit = contentView.findViewById(R.id.tv_commit);
        tv_type.setText("选择平台");
        mNewsList1 = new ArrayList<>();
        for (int i = 0; i < BossplatBean.getPlatfrom().size(); i++) {
            mNewsList1.add(BossplatBean.getPlatfrom().get(i));
        }
        if (job_plat.length() > 0) {
            String[] split = job_plat.split(",");
            for (int i = 0; i < split.length; i++) {
                for (int j = 0; j < mNewsList1.size(); j++) {
                    if (mNewsList1.get(j).getId().equals(split[i])) {
                        mNewsList1.get(j).setClick(true);
                    }
                }
            }
        }
        if (TypeAdapter == null) {
            TypeAdapter = new PlatSelectAdapter(AddJobActivity.this, R.layout.item_four, mNewsList1);
            TypeAdapter.openLoadAnimation(new ScaleInAnimation());
        } else {
            TypeAdapter.setData(mNewsList1);
        }

        card_recycler.setLayoutManager(new GridLayoutManager(AddJobActivity.this, 3));
        card_recycler.setAdapter(TypeAdapter);

        TypeAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                Log.d("Debug", "点击的位置" + position);
                if (position == 0) {
                    if (TypeAdapter.get(0).isClick()) {
                        TypeAdapter.get(0).setClick(false);
                    } else {
                        TypeAdapter.get(0).setClick(true);
                        for (int i = 1; i < TypeAdapter.getAll().size(); i++) {
                            TypeAdapter.get(i).setClick(false);
                        }
                    }
                } else {
                    if (TypeAdapter.get(position).isClick()) {
                        TypeAdapter.get(position).setClick(false);
                    } else {
                        TypeAdapter.get(position).setClick(true);
                    }
                    TypeAdapter.get(0).setClick(false);
                }
                TypeAdapter.notifyDataSetChanged();
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });

        tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder sb = new StringBuilder();
                if (TypeAdapter.getAll().get(0).isClick()) {
                    tvPlatText.setText("全平台");
                    if (sb.length() > 0) {//该步即不会第一位有逗号，也防止最后一位拼接逗号！
                        sb.append(",");
                    }
                    sb.append(TypeAdapter.getAll().get(0).getId());
                } else {
                    for (int i = 0; i < TypeAdapter.getAll().size(); i++) {
                        if (TypeAdapter.get(i).isClick()) {
                            if (sb.length() > 0) {//该步即不会第一位有逗号，也防止最后一位拼接逗号！
                                sb.append(",");
                            } else {
                                tvPlatText.setText(TypeAdapter.get(i).getPlat_name() + "...");
                            }
                            sb.append(TypeAdapter.getAll().get(i).getId());
                        }
                    }
                }
                job_plat = sb + "";
                mPopWindow1.dismiss();
            }
        });
        //显示PopupWindow
        View rootview = LayoutInflater.from(AddJobActivity.this).inflate(R.layout.activity_add_job_layout, null);
        mPopWindow1.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


    private static PopupWindow mPopWindow2;

    /**
     * 主播类型
     */
    private void showPopupWindow2() {
        //设置contentView
        final View contentView = LayoutInflater.from(AddJobActivity.this).inflate(R.layout.chairman_add_job_pop, null);
        mPopWindow2 = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindow2.setContentView(contentView);
        TextView tv_type = contentView.findViewById(R.id.tv_type);
        RecyclerView card_recycler = contentView.findViewById(R.id.card_recycler);
        TextView tv_commit = contentView.findViewById(R.id.tv_commit);
        RelativeLayout rl_commit = contentView.findViewById(R.id.rl_commit);
        rl_commit.setVisibility(View.GONE);

        tv_type.setText("选择职位类型");
        mNewsList2 = new ArrayList<>();

        for (int i = 0; i < BossplatBean.getRecuitType().size(); i++) {
            BossPlatBean.RecuitTypeBean recuitTypeBean = BossplatBean.getRecuitType().get(i);
            BossPlatBean.PlatfromBean platfromBean = new BossPlatBean.PlatfromBean(recuitTypeBean.getId(), recuitTypeBean.getType_name(), recuitTypeBean.getStatus(), false);
            mNewsList2.add(platfromBean);
        }
        if (anchor_type.length() > 0) {
            String[] split = anchor_type.split(",");
            for (int i = 0; i < split.length; i++) {
                for (int j = 0; j < mNewsList2.size(); j++) {
                    if (mNewsList2.get(j).getId().equals(split[i])) {
                        mNewsList2.get(j).setClick(true);
                    }
                }
            }
        }
        if (TypeAdapter1 == null) {
            TypeAdapter1 = new PlatSelectAdapter(AddJobActivity.this, R.layout.item_four, mNewsList2);
            TypeAdapter1.openLoadAnimation(new ScaleInAnimation());
        } else {
            TypeAdapter1.setData(mNewsList2);
        }

        card_recycler.setLayoutManager(new GridLayoutManager(AddJobActivity.this, 3));
        card_recycler.setAdapter(TypeAdapter1);

        TypeAdapter1.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                Log.d("Debug", "点击的位置" + position);
                for (int i = 0; i < TypeAdapter1.getAll().size(); i++) {
                    TypeAdapter1.getAll().get(i).setClick(false);
                }
                TypeAdapter1.get(position).setClick(true);
                TypeAdapter1.notifyDataSetChanged();

                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < TypeAdapter1.getAll().size(); i++) {
                    if (TypeAdapter1.get(i).isClick()) {
                        if (sb.length() > 0) {//该步即不会第一位有逗号，也防止最后一位拼接逗号！
                            sb.append(",");
                        }
                        sb.append(TypeAdapter1.getAll().get(i).getId());
                    }
                }
                anchor_type = sb + "";
                if (anchor_type.length() > 0) {
                    for (int i = 0; i < TypeAdapter1.getAll().size(); i++) {
                        if (TypeAdapter1.get(i).isClick()) {
                            tvTypeName.setText(TypeAdapter1.get(i).getPlat_name());
                        }
                    }

                }
                mPopWindow2.dismiss();

            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });

        //显示PopupWindow
        View rootview = LayoutInflater.from(AddJobActivity.this).inflate(R.layout.activity_add_job_layout, null);
        mPopWindow2.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


    private static PopupWindow mPopWindow3;

    /**
     * 福利待遇
     */
    private void showPopupWindow3() {
        //设置contentView
        final View contentView = LayoutInflater.from(AddJobActivity.this).inflate(R.layout.chairman_add_job_pop, null);
        mPopWindow3 = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindow3.setContentView(contentView);
        TextView tv_type = contentView.findViewById(R.id.tv_type);
        RecyclerView card_recycler = contentView.findViewById(R.id.card_recycler);
        TextView tv_commit = contentView.findViewById(R.id.tv_commit);

        tv_type.setText("选择福利待遇");
        mNewsList3 = new ArrayList<>();

        for (int i = 0; i < welfareListplatBean.getList().size(); i++) {
            welfareListPlatBean.ListBean listBean = welfareListplatBean.getList().get(i);
            BossPlatBean.PlatfromBean platfromBean = new BossPlatBean.PlatfromBean(listBean.getId() + "", listBean.getName(), "", false);
            mNewsList3.add(platfromBean);
        }
        if (welfare.length() > 0) {
            String[] split = welfare.split(",");
            for (int i = 0; i < split.length; i++) {
                for (int j = 0; j < mNewsList3.size(); j++) {
                    if (mNewsList3.get(j).getPlat_name().equals(split[i])) {
                        mNewsList3.get(j).setClick(true);
                    }
                }
            }
        }
        if (TypeAdapter2 == null) {
            TypeAdapter2 = new PlatSelectAdapter(AddJobActivity.this, R.layout.item_four, mNewsList3);
            TypeAdapter2.openLoadAnimation(new ScaleInAnimation());
        } else {
            TypeAdapter2.setData(mNewsList3);
        }

        card_recycler.setLayoutManager(new GridLayoutManager(AddJobActivity.this, 3));
        card_recycler.setAdapter(TypeAdapter2);

        TypeAdapter2.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                Log.d("Debug", "点击的位置" + position);
                if (TypeAdapter2.get(position).isClick()) {
                    TypeAdapter2.get(position).setClick(false);
                } else {
                    TypeAdapter2.get(position).setClick(true);
                }
                TypeAdapter2.notifyDataSetChanged();
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });

        tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < TypeAdapter2.getAll().size(); i++) {
                    if (TypeAdapter2.get(i).isClick()) {
                        if (sb.length() > 0) {//该步即不会第一位有逗号，也防止最后一位拼接逗号！
                            sb.append(",");
                        } else {
                            tvWelfareText.setText(TypeAdapter2.get(i).getPlat_name() + "...");
                        }
                        sb.append(TypeAdapter2.getAll().get(i).getPlat_name());
                    }
                }
                welfare = sb + "";
                mPopWindow3.dismiss();
            }
        });
        //显示PopupWindow
        View rootview = LayoutInflater.from(AddJobActivity.this).inflate(R.layout.activity_add_job_layout, null);
        mPopWindow3.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


    private static PopupWindow mPopWindow;

    /**
     *
     */
    private void showPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(AddJobActivity.this).inflate(R.layout.compact_comtent_share, null);
        mPopWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindow.setContentView(contentView);
        //设置各个控件的点击响应
        TextView tv_next = contentView.findViewById(R.id.tv_next);
        TextView tv_content = contentView.findViewById(R.id.tv_content);

        tv_content.setText(
                "会长大大，您已成功提交发布职位申请，我们会在24小时之内审核，请及时关注进度哦");

        tv_next.setText("我知道了");

        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopWindow.dismiss();
                finish();
            }
        });

        //显示PopupWindow
        View rootview = LayoutInflater.from(AddJobActivity.this).inflate(R.layout.bosswebview, null);
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }


    private static PopupWindow mPopWindow4;
//    List<Integer> isClickData = new ArrayList<>();

    /**
     * 职位亮点
     */
    private void showPopupWindow4() {
        //设置contentView
        final View contentView = LayoutInflater.from(AddJobActivity.this).inflate(R.layout.chairman_add_job_pop, null);
        mPopWindow4 = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindow4.setContentView(contentView);
        final TextView tv_type = contentView.findViewById(R.id.tv_type);
        RecyclerView card_recycler = contentView.findViewById(R.id.card_recycler);
        TextView tv_commit = contentView.findViewById(R.id.tv_commit);

        tv_type.setText("职位亮点");
        if (lightspot.length() > 0) {
            String[] split = lightspot.split(",");
            for (int i = 0; i < split.length; i++) {
                for (int j = 0; j < mNewsList4.size(); j++) {
                    if (mNewsList4.get(j).getPlat_name().equals(split[i])) {
                        mNewsList4.get(j).setClick(true);
                    }
                }
            }
        }
        if (TypeAdapter3 == null) {
            TypeAdapter3 = new PlatSelectAdapter(AddJobActivity.this, R.layout.item_four, mNewsList4);
            TypeAdapter3.openLoadAnimation(new ScaleInAnimation());
        } else {
            TypeAdapter3.setData(mNewsList4);
        }
        card_recycler.setLayoutManager(new GridLayoutManager(AddJobActivity.this, 3));
        card_recycler.setAdapter(TypeAdapter3);
        TypeAdapter3.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                //点击选中的  取消掉
                int number = 0;
                for (int i = 0; i < TypeAdapter3.getAll().size(); i++) {
                    if (TypeAdapter3.getAll().get(i).isClick()) {
                        number++;
                    }
                }
                if (number >= 3) {
                    if (TypeAdapter3.get(position).isClick()) {
                        TypeAdapter3.get(position).setClick(false);
                    } else {
                        ToastUtil.showShort("最多选择三个标签");
                    }
                } else {
                    if (TypeAdapter3.get(position).isClick()) {
                        TypeAdapter3.get(position).setClick(false);
                    } else {
                        TypeAdapter3.get(position).setClick(true);
                    }
                }
                TypeAdapter3.notifyDataSetChanged();
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });

        tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder sb = new StringBuilder();
//                StringBuilder sb2 = new StringBuilder();
                for (int i = 0; i < TypeAdapter3.getAll().size(); i++) {
                    if (TypeAdapter3.get(i).isClick()) {
                        if (sb.length() > 0) {//该步即不会第一位有逗号，也防止最后一位拼接逗号！
                            sb.append(",");
                        } else {
                            tv_point_text.setText(TypeAdapter3.get(i).getPlat_name() + "...");
                        }
                        TypeAdapter3.get(i).setClick(false);
                        TypeAdapter3.notifyDataSetChanged();
                        sb.append(TypeAdapter3.getAll().get(i).getPlat_name());
                    }
                }
                lightspot = sb + "";
                mPopWindow4.dismiss();
            }
        });
        //显示PopupWindow
        View rootview = LayoutInflater.from(AddJobActivity.this).inflate(R.layout.activity_add_job_layout, null);
        mPopWindow4.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }

}
