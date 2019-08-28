package shangri.example.com.shangri.ui.activity;


import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.NewTaskDataBean;
import shangri.example.com.shangri.model.bean.response.TaskDataBean;
import shangri.example.com.shangri.presenter.AddTaskPresenter;
import shangri.example.com.shangri.presenter.view.AddTaskView;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.view.WheelView;
import shangri.example.com.shangri.util.TimeUtil;

/**
 * 主播任务
 * Created by admin on 2017/12/22.
 */

public class ActivityParticipateTask extends BaseActivity<AddTaskView, AddTaskPresenter> implements AddTaskView {
    @BindView(R.id.tv_guild)
    TextView tv_guild;

    @BindView(R.id.head)
    LinearLayout head;


    @BindView(R.id.dialog_year)
    WheelView mWheelYear;
    @BindView(R.id.dialog_month)
    WheelView mWheelMonth;
    @BindView(R.id.dialog_data)
    WheelView mWheelDay;

    @BindView(R.id.dialog_data1)
    WheelView mWheelDay1;
    @BindView(R.id.dialog_data2)
    WheelView mWheelDay2;

    @BindView(R.id.tv_youxiu)
    TextView tv_youxiu;
    @BindView(R.id.tv_buzu)
    TextView tv_buzu;

    @BindView(R.id.et2)
    EditText et2;
    @BindView(R.id.et3)
    EditText et3;
    @BindView(R.id.tv_comment1)
    EditText tv_comment1;

    @BindView(R.id.im_icon1)
    ImageView im_icon1;
    @BindView(R.id.im_icon4)
    ImageView im_icon4;
    @BindView(R.id.im_icon5)
    ImageView im_icon5;

    @BindView(R.id.tv_all)
    TextView tv_all;
    @BindView(R.id.tv_send)
    TextView tv_send;

    @BindView(R.id.tv_edit)
    TextView tv_edit;
    @BindView(R.id.tv_2)
    TextView tv_2;

    @BindView(R.id.tv_title)
    TextView tv_title;


    @BindView(R.id.ll1)
    LinearLayout ll1;

    private String time_type;
    private ProgressDialogFragment mProgressDialog;
    TaskDataBean.TasksBean mTasksBean;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_partivipate_task;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_partivipate_task;
    }

    @Override
    protected AddTaskPresenter createPresenter() {
        return new AddTaskPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        setDataUtil();
        if (getIntent().getStringExtra("type").equals("2")) {
            mTasksBean = (TaskDataBean.TasksBean) getIntent().getSerializableExtra("data");

            tv_title.setText("任务详情");
            im_icon1.setVisibility(View.INVISIBLE);
            tv_guild.setText(mTasksBean.getGuild_name());
            et2.setText(mTasksBean.getTheme());
            et2.setEnabled(false);

            et3.setText(mTasksBean.getContent());
            et3.setEnabled(false);

            try {
                tv_youxiu.setText(TimeUtil.longToString(Long.parseLong(mTasksBean.getStart_time()), "yyyy-MM-dd HH:mm:ss"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            im_icon4.setVisibility(View.INVISIBLE);
            im_icon5.setVisibility(View.INVISIBLE);
            try {
                tv_buzu.setText(TimeUtil.longToString(Long.parseLong(mTasksBean.getEnd_time()), "yyyy-MM-dd HH:mm:ss"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            tv_all.setText(mTasksBean.getExpect_aims());

            if (mTasksBean.getSelf_aims() != null || !TextUtils.isEmpty(mTasksBean.getSelf_aims())) {
                if (!mTasksBean.getSelf_aims().equals("0")){
                    tv_comment1.setText(mTasksBean.getSelf_aims());
                    tv_edit.setVisibility(View.VISIBLE);
                    tv_comment1.setEnabled(false);
                }
                else {
                    tv_comment1.setEnabled(true);
                    tv_edit.setVisibility(View.GONE);
                    ll1.setVisibility(View.GONE);
                }



                //未开始
                try {
                    if (Long.valueOf(mTasksBean.getStart_time()) > TimeUtil.getCurrentTime()) {
                        tv_edit.setVisibility(View.VISIBLE);
                        tv_send.setVisibility(View.VISIBLE);
                    }
                    else if (Long.valueOf(mTasksBean.getStart_time()) < TimeUtil.getCurrentTime() && Long.valueOf(mTasksBean.getEnd_time()) > TimeUtil.getCurrentTime()) {
                        ll1.setVisibility(View.VISIBLE);
                        tv_2.setText(mTasksBean.getTotal_aims());
                        tv_send.setVisibility(View.VISIBLE);
                        tv_edit.setVisibility(View.GONE);
                    }

                    else{
                        ll1.setVisibility(View.VISIBLE);
                        tv_2.setText(mTasksBean.getTotal_aims());
                        tv_send.setVisibility(View.GONE);
                        tv_edit.setVisibility(View.GONE);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    @OnClick({R.id.tv_edit, R.id.rl_guild5, R.id.tv_send, R.id.setting_back, R.id.rl_guild1, R.id.rl_guild4, R.id.time_disms, R.id.time_save})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.time_disms:
                head.setVisibility(View.GONE);
                break;
            case R.id.time_save:
                if (time_type.equals("1")) {
                    tv_youxiu.setText(getYear() + "-" + getMonth() + "-" + getDay() + ":" + getTime());
                    String srdata = getYear() + "-" + getMonth() + "-" + getDay() + ":" + getTime() + ":" + getMin() + ":" + "0";
                    String launchData = String.valueOf(TimeUtil.convertTimeToLong(srdata));
                    Log.e("ddddddddddd", launchData);
                } else if (time_type.equals("2")) {
                    tv_buzu.setText(getYear() + "-" + getMonth() + "-" + getDay() + ":" + getTime());
                    String rr = getYear() + "-" + getMonth() + "-" + getDay() + ":" + getTime() + ":" + getMin() + ":" + "0";
                    String launchData1 = String.valueOf(TimeUtil.convertTimeToLong(rr));
                }

                head.setVisibility(View.GONE);
                break;
            case R.id.setting_back:
                finish();
                break;
            case R.id.tv_edit:
                tv_comment1.setEnabled(true);
                break;

            case R.id.tv_send:
                if (mProgressDialog == null) {
                    mProgressDialog = new ProgressDialogFragment();
                }
                mProgressDialog.show(this.getSupportFragmentManager());
                mPresenter.participate(mTasksBean.getTask_id(), tv_comment1.getText().toString().trim());

                break;
        }
    }

    @Override
    public void requestFailed(String message) {
        if (mProgressDialog != null && mProgressDialog.isResumed()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void mvpExpleme() {
        if (mProgressDialog != null && mProgressDialog.isResumed()) {
            mProgressDialog.dismiss();
        }
        finish();

    }

    @Override
    public void partIn() {

    }

    @Override
    public void taskDetail(NewTaskDataBean.TasksBean bean) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //  如果请求码与返回码等于预期设置的值  则进行后续操作
        if (requestCode == 1 && resultCode == 2) {
            // 获取返回的数据
            String guildid = data.getStringExtra("guildid");
            String content = data.getStringExtra("content");
            tv_guild.setText(content);
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
        if (mWheelDay == null) {
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

    public String getTime() {
        if (mWheelDay1 == null) {
            return null;
        }
        return mWheelDay1.getSelectedText();
    }

    public String getMin() {
        if (mWheelDay2 == null) {
            return null;
        }
        return mWheelDay2.getSelectedText();
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

        mWheelDay1.setData(getDayData1());
        mWheelDay2.setData(getDayData2());

        mWheelYear.setDefault(2);
        mWheelMonth.setDefault(mMonth - 1);
        mWheelDay.setDefault(mDay - 1);
        mWheelDay1.setDefault(time - 1);
        mWheelDay2.setDefault(min - 1);
    }
}
