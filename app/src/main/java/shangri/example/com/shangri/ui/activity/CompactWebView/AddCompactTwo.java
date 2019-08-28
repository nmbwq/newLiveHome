package shangri.example.com.shangri.ui.activity.CompactWebView;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.text.Editable;
import android.text.TextWatcher;
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

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.ChairmanSignResponse;
import shangri.example.com.shangri.model.bean.response.CompactDetailResponse;
import shangri.example.com.shangri.model.bean.response.CompactListResponse;
import shangri.example.com.shangri.model.bean.response.CompactMobanResponse;
import shangri.example.com.shangri.model.bean.response.IsFaceResponse;
import shangri.example.com.shangri.model.bean.response.WebDates;
import shangri.example.com.shangri.model.bean.response.pdfResponse;
import shangri.example.com.shangri.presenter.CompactListPresenter;
import shangri.example.com.shangri.presenter.view.CompactlistView;
import shangri.example.com.shangri.ui.activity.LegalPayActivity;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.view.WheelView;
import shangri.example.com.shangri.util.TimeUtil;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 添加合同第二个界面
 * Created by admin on 2017/12/22.
 */

public class AddCompactTwo extends BaseActivity<CompactlistView, CompactListPresenter> implements CompactlistView {
    @BindView(R.id.setting_back)
    ImageView settingBack;
    @BindView(R.id.et_compact_name)
    EditText etCompactName;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.et_beizhu)
    EditText etBeizhu;
    @BindView(R.id.et_phone)
    EditText et_phone;
    @BindView(R.id.text_number)
    TextView textNumber;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.im_delete_name)
    ImageView imDeleteName;
    @BindView(R.id.im2)
    ImageView im2;
    @BindView(R.id.rl_selet_time)
    RelativeLayout rlSeletTime;
    @BindView(R.id.im_delete_phone)
    ImageView imDeletePhone;
    @BindView(R.id.ll_info)
    LinearLayout llInfo;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    @BindView(R.id.time_disms)
    TextView timeDisms;
    @BindView(R.id.time_save)
    TextView timeSave;
    @BindView(R.id.dialog_year)
    WheelView mWheelYear;
    @BindView(R.id.dialog_month)
    WheelView mWheelMonth;
    @BindView(R.id.dialog_data)
    WheelView mWheelDay;
    @BindView(R.id.head)
    LinearLayout head;
    @BindView(R.id.tv_name1)
    TextView tv_name1;
    @BindView(R.id.tv_name2)
    TextView tv_name2;

    private ProgressDialogFragment mProgressDialog;

    //上个界面传递过阿里的数据
    WebDates bean;
    //获取当前时间
    String launchData = "";
    String webString;
    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_addcompacttwo_layout;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_addcompacttwo_layout;
    }

    @Override
    protected CompactListPresenter createPresenter() {
        return new CompactListPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        bean = (WebDates) getIntent().getSerializableExtra("bean");
        webString = getIntent().getStringExtra("WebString");
        if (bean != null) {
            et_phone.setText(bean.getY_telephone() + "");
            if (bean.getY_name().length() > 0) {
                tv_name1.setText(bean.getY_name().substring(0, 1));
                if (bean.getY_name().length() > 1) {
                    tv_name2.setText(bean.getY_name().substring(1, bean.getY_name().length()));
                }
            }
        }
        setDataUtil();
        etBeizhu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (etBeizhu.getText().toString().length() <= 130) {
                    textNumber.setText(etBeizhu.getText().toString().length() + "");
                } else {
                    ToastUtil.showShort("超出规定字数，暂不能输入");
                }
            }
        });

        try {
            launchData = TimeUtil.longToString(TimeUtil.getCurrentTime(), "yyyy-MM-dd");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tvTime.setText(launchData);

    }

    @Override
    public void requestFailed(String message) {
        if (mProgressDialog != null && mProgressDialog.isResumed()) {
            mProgressDialog.dismiss();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.time_disms, R.id.time_save, R.id.setting_back, R.id.tv_commit, R.id.im_delete_name, R.id.rl_selet_time, R.id.im_delete_phone, R.id.ll_info})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_back:
                finish();
                break;
            case R.id.im_delete_name:
                etCompactName.setText("");
                break;
            case R.id.rl_selet_time:
                head.setVisibility(View.VISIBLE);
                break;
            case R.id.im_delete_phone:
                et_phone.setText("");
                break;
            case R.id.ll_info:
                break;
            case R.id.tv_commit:
                if (etCompactName.getText().toString().length() == 0) {
                    ToastUtil.showShort("请填写合同名称");
                    return;
                }
                if (tvTime.getText().toString().length() == 0) {
                    ToastUtil.showShort("请选择截止日期");
                    return;
                }
                if (et_phone.getText().toString().length() == 0) {
                    ToastUtil.showShort("请填写签署人电话");
                    return;
                }
                if (mProgressDialog == null) {
                    mProgressDialog = new ProgressDialogFragment();
                }
                mProgressDialog.show(this.getSupportFragmentManager());
                mPresenter.signNumber();
                break;
            case R.id.time_disms:
                head.setVisibility(View.GONE);
                break;
            case R.id.time_save:
                head.setVisibility(View.GONE);
                String month = getMonth();
                String day = getDay();
                if (Integer.parseInt(getMonth()) < 10) {
                    month = "0" + getMonth();

                }
                if (Integer.parseInt(getDay()) < 10) {
                    day = "0" + getDay();
                }
                tvTime.setText(getYear() + "-" + month + "-" + day);
                break;
        }
    }


    private ArrayList<String> getYearData() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 2020; i > 2015; i--) {
            list.add(String.valueOf(i));
        }
        return list;
    }

    private ArrayList<String> getMonthData() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            list.add(String.valueOf(i));
        }
        return list;
    }

    private ArrayList<String> getDayData() {
        //ignore condition
        ArrayList<String> list = new ArrayList<>();
        for (int i = 1; i < 32; i++) {
            list.add(String.valueOf(i));
        }
        return list;
    }

    private ArrayList<String> getDayData1() {
        //ignore condition
        ArrayList<String> list = new ArrayList<>();
        for (int i = 1; i < 25; i++) {
            list.add(String.valueOf(i));
        }
        return list;
    }

    private ArrayList<String> getDayData2() {
        //ignore condition
        ArrayList<String> list = new ArrayList<>();
        for (int i = 1; i < 60; i++) {
            list.add(String.valueOf(i));
        }
        return list;
    }


    public String getYear() {
        if (mWheelYear == null) {
            return null;
        }
        return mWheelYear.getSelectedText();
    }

    public String getMonth() {
        if (mWheelMonth == null) {
            return null;
        }
        return mWheelMonth.getSelectedText();
    }

    public String getDay() {
        if (mWheelDay == null) {
            return null;
        }
        return mWheelDay.getSelectedText();
    }

    private void setDataUtil() {
        Calendar c = Calendar.getInstance();//
        Integer mYear = c.get(Calendar.YEAR); // 获取当前年份
        Integer mMonth = c.get(Calendar.MONTH) + 1;// 获取当前月份
        Integer mDay = c.get(Calendar.DAY_OF_MONTH);// 获取当日期


        int time = c.get(Calendar.HOUR_OF_DAY);
        int min = c.get(Calendar.MINUTE);
        mWheelYear.setData(getYearData());
        mWheelMonth.setData(getMonthData());
        mWheelDay.setData(getDayData());

        mWheelYear.setDefault(2);
        mWheelMonth.setDefault(mMonth - 1);
        mWheelDay.setDefault(mDay - 1);

    }


    @Override
    public void legalGuildContract(CompactListResponse resultBean) {

    }

    @Override
    public void legalDetail(CompactDetailResponse resultBean) {

    }

    @Override
    public void templateList(CompactMobanResponse resultBean) {

    }

    @Override
    public void legalIsface(IsFaceResponse resultBean) {

    }

    @Override
    public void legalTemplate(IsFaceResponse resultBean) {

    }

    @Override
    public void legalContract_signature(ChairmanSignResponse resultBean) {

    }

    @Override
    public void legalContractVerify() {

    }

    @Override
    public void legalanchorVerify() {

    }

    @Override
    public void ontractVerifyCode() {

    }

    @Override
    public void legalTxcontractPush() {

    }

    @Override
    public void legalAnchor_signature(ChairmanSignResponse resultBean) {

    }


    @Override
    public void legalTxcontract() {

    }

    @Override
    public void legalContractRepeal(ChairmanSignResponse resultBean) {

    }



    @Override
    public void legalContractBase(pdfResponse resultBean) {

    }

    @Override
    public void signNumber(pdfResponse resultBean) {
        if (mProgressDialog != null && mProgressDialog.isResumed()) {
            mProgressDialog.dismiss();
        }
        if (Integer.parseInt(resultBean.getResidue()) > 0) {
            bean.title = etCompactName.getText().toString() + "";
            bean.end_time =TimeUtil.convertTimeToLong1(tvTime.getText().toString()) / 1000 + "";
            if (etBeizhu.getText().toString().length() == 0) {
                bean.note = "";
            } else {
                bean.note = etBeizhu.getText().toString() + "";
            }
            bean.y_push = et_phone.getText().toString() + "";
            startActivity(new Intent(AddCompactTwo.this, CompactSignWebview.class).putExtra("bean", bean).putExtra("IsFromOther",false).putExtra("webString",webString));
        } else {
            showPopupWindow();
        }
    }


    private PopupWindow mPopWindow;

    /**
     * 主播拒签操作
     */
    private void showPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(AddCompactTwo.this).inflate(R.layout.compact_comtent, null);
        mPopWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        mPopWindow.setContentView(contentView);
        //设置各个控件的点击响应
        TextView tv_content = contentView.findViewById(R.id.tv_content);
        TextView tv_next = contentView.findViewById(R.id.tv_next);
        ImageView im_delete = contentView.findViewById(R.id.im_delete);

        im_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopWindow.dismiss();
            }
        });
        tv_content.setText("您的签署次数已用完，请购买");
        tv_next.setText("去购买");
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddCompactTwo.this, LegalPayActivity.class));
                mPopWindow.dismiss();
            }
        });
        //显示PopupWindow
        View rootview = LayoutInflater.from(AddCompactTwo.this).inflate(R.layout.activity_addcompacttwo_layout, null);
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }
}
