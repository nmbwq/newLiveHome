package shangri.example.com.shangri.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.NewTaskDataBean;
import shangri.example.com.shangri.presenter.AddTaskPresenter;
import shangri.example.com.shangri.presenter.view.AddTaskView;
import shangri.example.com.shangri.ui.view.WheelView;
import shangri.example.com.shangri.util.KeyBoardUtil;
import shangri.example.com.shangri.util.PointUtils;
import shangri.example.com.shangri.util.ToastUtil;

/**
 * 任务
 * Created by admin on 2017/12/22.
 */

public class ActivityAddNewTask extends BaseActivity<AddTaskView, AddTaskPresenter> implements AddTaskView, View.OnFocusChangeListener {


    @BindView(R.id.setting_back)
    ImageView settingBack;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.tv_manager)
    TextView tvManager;
    @BindView(R.id.tv_manager_line)
    TextView tvManagerLine;
    @BindView(R.id.rl_manager)
    RelativeLayout rlManager;
    @BindView(R.id.tv_anchor)
    TextView tvAnchor;
    @BindView(R.id.tv_anchor_line)
    TextView tvAnchorLine;
    @BindView(R.id.ll_anchor)
    RelativeLayout llAnchor;
    @BindView(R.id.tv_guild)
    TextView tvGuild;
    @BindView(R.id.im_icon1)
    ImageView imIcon1;
    @BindView(R.id.rl_guild1)
    RelativeLayout rlGuild1;
    @BindView(R.id.et_theme)
    EditText etTheme;
    @BindView(R.id.rl_guild_theme)
    RelativeLayout rlGuildTheme;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.et_guize)
    EditText etGuize;
    @BindView(R.id.rl_guild3)
    RelativeLayout rlGuild3;
    @BindView(R.id.tv_sum_number)
    EditText tvSumNumber;
    @BindView(R.id.ll_comment)
    RelativeLayout llComment;
    @BindView(R.id.im_tash_ishiden)
    ImageView imTashIshiden;
    @BindView(R.id.ll_task_ishide)
    LinearLayout llTaskIshide;
    @BindView(R.id.tv_start_time)
    TextView tvStartTime;
    @BindView(R.id.im_icon4)
    ImageView imIcon4;
    @BindView(R.id.tv_starttime)
    TextView tvStarttime;
    @BindView(R.id.rl_select_starttime)
    RelativeLayout rlSelectStarttime;
    @BindView(R.id.tv_end_time)
    TextView tvEndTime;
    @BindView(R.id.im_icon5)
    ImageView imIcon5;
    @BindView(R.id.tv_endtime)
    TextView tvEndtime;
    @BindView(R.id.rl_select_endtime)
    RelativeLayout rlSelectEndtime;
    @BindView(R.id.tv_alert_text)
    TextView tvAlertText;
    @BindView(R.id.im_icon2)
    ImageView imIcon2;
    @BindView(R.id.rl_alert)
    RelativeLayout rlAlert;
    @BindView(R.id.tv_man_text)
    TextView tvManText;
    @BindView(R.id.im_icon3)
    ImageView imIcon3;
    @BindView(R.id.rl_man)
    RelativeLayout rlMan;

    @BindView(R.id.dialog_year)
    WheelView mWheelYear;
    @BindView(R.id.dialog_month)
    WheelView mWheelMonth;
    @BindView(R.id.dialog_data)
    WheelView mWheelDay;
    @BindView(R.id.head)
    LinearLayout head;
    @BindView(R.id.ll_select)
    LinearLayout ll_select;

    //返回的公会id
    private String guildid = "";
    //1 开始时间 2结束时间
    private String time_type;
    //开始是时间
    String launchData = "";
    //结束时间
    String launchData1 = "";
    //1是管理员  0是主播
    int symbol;
    //默认不隐藏  0不隐藏  1隐藏
    int hide_expect = 0;
    //   0 不提醒 1到期前1天 2到期前2天 3到期前3天
    public static int expire_remind = 1;
    //是否点击选择提醒日期
    boolean isFocus = false;
    private String task_id;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_add_new_task;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_add_new_task;
    }

    @Override
    protected AddTaskPresenter createPresenter() {
        return new AddTaskPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        expire_remind = 1;
        boolean aFalse = getIntent().getBooleanExtra("flag", false);
        Log.d("Debug", "传过来的aFalse" + aFalse);
        if (aFalse) {
            task_id = getIntent().getStringExtra("task_id");
            Log.d("Debug", "里面传过来的task_id" + task_id);
        } else {
            task_id = "";
        }
        Log.d("Debug", "传过来的task_id" + task_id);


        KeyBoardUtil.KeyBoard(ActivityAddNewTask.this, "down");
        setDataUtil();
        isFocus = false;

        if (UserConfig.getInstance().getRole().equals("1")) {
            symbol = 0;
            ll_select.setVisibility(View.VISIBLE);

        } else if (UserConfig.getInstance().getRole().equals("3")) {
            symbol = 1;
            ll_select.setVisibility(View.GONE);

        }

    }

    @Override
    public void onFocusChange(View view, boolean b) {

    }

    @Override
    public void requestFailed(String message) {

    }

    @Override
    public void mvpExpleme() {

    }

    @Override
    public void partIn() {

    }

    @Override
    public void taskDetail(NewTaskDataBean.TasksBean bean) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isFocus) {
            switch (expire_remind) {
                case 0:
                    tvAlertText.setText("不提醒");
                    break;
                case 1:
                    tvAlertText.setText("到期前1天");
                    break;
                case 2:
                    tvAlertText.setText("到期前2天");
                    break;
                case 3:
                    tvAlertText.setText("到期前3天");
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //  如果请求码与返回码等于预期设置的值  则进行后续操作
        if (requestCode == 1 && resultCode == 2) {
            // 获取返回的数据
            guildid = data.getStringExtra("guildid");
            String content = data.getStringExtra("content");
            tvGuild.setText(content);
        }

    }

    @OnClick({R.id.time_disms, R.id.time_save, R.id.setting_back, R.id.rl_manager, R.id.ll_anchor, R.id.rl_guild1, R.id.ll_task_ishide, R.id.rl_select_starttime, R.id.rl_select_endtime, R.id.rl_alert, R.id.rl_man})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_back:
                finish();
                break;
            case R.id.rl_manager:
                symbol = 0;
                tvManager.setTextColor(getResources().getColor(R.color.text_color_task));
                tvManagerLine.setVisibility(View.VISIBLE);
                tvAnchor.setTextColor(getResources().getColor(R.color.text_color_light_black));
                tvAnchorLine.setVisibility(View.GONE);
                break;
            case R.id.ll_anchor:
                symbol = 1;
                tvManager.setTextColor(getResources().getColor(R.color.text_color_light_black));
                tvManagerLine.setVisibility(View.GONE);
                tvAnchor.setTextColor(getResources().getColor(R.color.text_color_task));
                tvAnchorLine.setVisibility(View.VISIBLE);
                break;
            case R.id.rl_guild1:
                if (PointUtils.isFastClick()) {
                    Intent intent1 = new Intent(ActivityAddNewTask.this, ChoiceGuildActivity.class);
                    startActivityForResult(intent1, 1);
                }
                break;
            case R.id.ll_task_ishide:
                if (hide_expect == 0) {
                    imTashIshiden.setImageDrawable(getResources().getDrawable(R.mipmap.xuanzhong));
                    hide_expect = 1;
                } else {
                    imTashIshiden.setImageDrawable(getResources().getDrawable(R.mipmap.xuanzhong1));
                    hide_expect = 0;
                }
                break;
            case R.id.rl_select_starttime:
                time_type = "1";
                head.setVisibility(View.VISIBLE);
                break;
            case R.id.rl_select_endtime:
                time_type = "2";
                head.setVisibility(View.VISIBLE);
                break;
            case R.id.rl_alert:
                isFocus = true;
                startActivity(new Intent(ActivityAddNewTask.this, AlertDateListActivity.class));
                break;
            case R.id.rl_man:
                if (PointUtils.isFastClick()) {
                    if (guildid.length() == 0) {
                        ToastUtil.showShort("请选择公会");
                        return;
                    }
                    if (etTheme.getText().toString().length() == 0) {
                        ToastUtil.showShort("请填写任务主题");
                        return;
                    }
                    if (tvSumNumber.getText().toString().length() == 0) {
                        ToastUtil.showShort("请设置总目标");
                        return;
                    }
                    if (etGuize.getText().toString().length() == 0) {
                        ToastUtil.showShort("请输入任务内容");
                        return;
                    }
                    if (tvStartTime.getText().toString().length() == 0) {
                        ToastUtil.showShort("请选择开始时间");
                        return;
                    }

                    if (tvEndtime.getText().toString().length() == 0) {
                        ToastUtil.showShort("请选择结束时间");
                        return;
                    }

                    Intent intent = new Intent(ActivityAddNewTask.this, SelectManActivity.class);
                    intent.putExtra("guild_id", guildid);
                    intent.putExtra("theme", etTheme.getText().toString() + "");
                    intent.putExtra("content", etGuize.getText().toString() + "");
                    intent.putExtra("start_time", launchData + "");
                    intent.putExtra("end_time", launchData1 + "");
                    intent.putExtra("expect_aims", etGuize.getText().toString() + "");
                    intent.putExtra("hide_expect", hide_expect + "");
                    intent.putExtra("expire_remind", expire_remind + "");
                    intent.putExtra("type", symbol + "");
                    intent.putExtra("number", tvSumNumber.getText().toString() + "");
                    intent.putExtra("task_id", task_id + "");

                    startActivity(intent);
                }
                break;
            case R.id.time_disms:
                head.setVisibility(View.GONE);
                break;
            case R.id.time_save:

                if (time_type.equals("1")) {
                    String month = getMonth();
                    String day = getDay();
                    if (Integer.parseInt(getMonth()) < 10) {
                        month = "0" + getMonth();

                    }
                    if (Integer.parseInt(getDay()) < 10) {
                        day = "0" + getDay();
                    }

                    launchData = getYear() + "-" + month + "-" + day;
                    tvStarttime.setText(launchData);
                    Log.e("Debug", "开始时间为" + launchData);
                } else if (time_type.equals("2")) {
                    if (TextUtils.isEmpty(launchData)) {
                        Toast.makeText(this, "请先选择开始时间", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    String month1 = getMonth();
                    String day1 = getDay();
                    if (Integer.parseInt(getMonth()) < 10) {
                        month1 = "0" + getMonth();

                    }
                    if (Integer.parseInt(getDay()) < 10) {
                        day1 = "0" + getDay();
                    }
                    launchData1 = getYear() + "-" + month1 + "-" + day1;
                    Log.d("Debug", "结束时间为" + launchData1);
                    int result = launchData.compareTo(launchData1);
                    if (result > 0) {
                        Toast.makeText(this, "请大于开始时间", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    tvEndtime.setText(launchData1);
                }


                head.setVisibility(View.GONE);
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

    private String getYear() {
        if (mWheelDay == null) {
            return null;
        }
        return mWheelYear.getSelectedText();
    }

    private String getMonth() {
        if (mWheelMonth == null) {
            return null;
        }
        return mWheelMonth.getSelectedText();
    }

    private String getDay() {
        if (mWheelDay == null) {
            return null;
        }
        return mWheelDay.getSelectedText();
    }


    private void setDataUtil() {
        Calendar c = Calendar.getInstance();//
        Integer mMonth = c.get(Calendar.MONTH) + 1;// 获取当前月份
        Integer mDay = c.get(Calendar.DAY_OF_MONTH);// 获取当日期
        mWheelYear.setData(getYearData());
        mWheelMonth.setData(getMonthData());
        mWheelDay.setData(getDayData());

        mWheelYear.setDefault(2);
        mWheelMonth.setDefault(mMonth - 1);
        mWheelDay.setDefault(mDay - 1);

    }

}
