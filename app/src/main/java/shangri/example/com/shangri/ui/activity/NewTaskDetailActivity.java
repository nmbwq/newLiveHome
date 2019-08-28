package shangri.example.com.shangri.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseActivity;
import shangri.example.com.shangri.model.bean.response.NewTaskDataBean;
import shangri.example.com.shangri.presenter.AddTaskPresenter;
import shangri.example.com.shangri.presenter.view.AddTaskView;
import shangri.example.com.shangri.ui.dialog.ProgressDialogFragment;
import shangri.example.com.shangri.ui.view.CircleImageView;

/**
 * 发布任务详情界面
 * Created by admin on 2017/12/22.
 */

public class NewTaskDetailActivity extends BaseActivity<AddTaskView, AddTaskPresenter> implements AddTaskView {
    @BindView(R.id.setting_back)
    ImageView settingBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_send)
    TextView tvSend;
    @BindView(R.id.tv_name)
    TextView tv_name;

    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.mine_profile_image)
    CircleImageView mineProfileImage;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.im_look)
    ImageView imLook;
    @BindView(R.id.tv_guile_name)
    TextView tvGuileName;
    @BindView(R.id.tv_task_theme)
    TextView tvTaskTheme;
    @BindView(R.id.tv_start_time)
    TextView tvStartTime;
    @BindView(R.id.tv_end_time)
    TextView tvEndTime;
    @BindView(R.id.tv_sum_number)
    TextView tvSumNumber;
    @BindView(R.id.tv_my_number)
    TextView tvMyNumber;
    @BindView(R.id.tv_full_number)
    TextView tvFullNumber;
    @BindView(R.id.im_add)
    ImageView imAdd;

    @BindView(R.id.tv_guize)
    TextView tv_guize;
    @BindView(R.id.tv1)
    TextView tv1;

    @BindView(R.id.ll_look)
    RelativeLayout ll_look;
    @BindView(R.id.rl_add)
    RelativeLayout rl_add;


    @BindView(R.id.rl_my_task_finish)
    LinearLayout rl_my_task_finish;
    @BindView(R.id.rl_my_task)
    LinearLayout rl_my_task;
    @BindView(R.id.tv_team_number)
    LinearLayout tv_team_number;


    private ProgressDialogFragment mProgressDialog;
    //chan
    NewTaskDataBean.TasksBean tasksBean = new NewTaskDataBean.TasksBean();

    //参加人数
    int joinSize = 0;
    //未参加人数
    int noJoinSize = 0;
    //完成人数
    int OverSize = 0;
    //未完成人数
    int IngSize = 0;
    //在列表界面返回的task_id
    int id;
    //在消息界面传过来的task_id
    String task_id;

    @Override
    protected int getNormalLayoutId() {
        return R.layout.activity_new_taskdetail;
    }

    @Override
    protected int getErrorLayoutId() {
        return R.layout.activity_new_taskdetail;
    }

    @Override
    protected AddTaskPresenter createPresenter() {
        return new AddTaskPresenter(this, this);
    }

    @Override
    protected void initViewsAndEvents() {
        tasksBean = (NewTaskDataBean.TasksBean) getIntent().getSerializableExtra("Bean");
        if (tasksBean != null) {
            initview(tasksBean);
        } else {
            task_id = getIntent().getStringExtra("task_id");
            mPresenter.taskDetail(task_id);
        }
    }

    private void initview(NewTaskDataBean.TasksBean tasksBean) {
        id = tasksBean.getId();
        task_id=tasksBean.getId()+"";
        Glide.with(NewTaskDetailActivity.this)
                .load(tasksBean.getCreater_avatar() + "")
                .placeholder(R.mipmap.bg_touxiang)
                .crossFade()
                .into(mineProfileImage);
        tv_name.setText(tasksBean.getCreater_name() + "");
        tvGuileName.setText(tasksBean.getGuild_name() + "");
        tvTaskTheme.setText(tasksBean.getTheme() + "");
        tvStartTime.setText(tasksBean.getStart_time() + "");
        tvEndTime.setText(tasksBean.getEnd_time() + "");
        tv_guize.setText(tasksBean.getContent());
        tvSumNumber.setText(tasksBean.getExpect_aims());
        //参加人数
        joinSize = tasksBean.getJoin().size();
        //未参加人数
        noJoinSize = tasksBean.getNo_join().size();
        //完成人数
        OverSize = tasksBean.getOver_list().size();
        //未完成人数
        IngSize = tasksBean.getIng_list().size();


        switch (tasksBean.getTask_status()) {
            case 0:
                tv1.setText("已参加人数：");
                tvNumber.setText(joinSize + "/" + (joinSize + noJoinSize));
                break;
            case 1:
            case 2:
                tv1.setText("已完成人数：");
                tvNumber.setText(OverSize + "/" + (OverSize + IngSize + noJoinSize));
                break;
        }

////        0显示1隐藏
//        if (tasksBean.getHide_expect() == 1) {
//            tvSumNumber.setText("**********");
//        } else {
//            tvSumNumber.setText(tasksBean.getExpect_aims());
//        }

        if (tasksBean.getTarget_value() != null) {
            tvMyNumber.setText(tasksBean.getTarget_value());
        }
        if (tasksBean.getReality_value() != null) {
            tvFullNumber.setText(tasksBean.getReality_value());
        }

        if (UserConfig.getInstance().getRole().equals("1")) {
//            1会长 2管理员
            if (tasksBean.getType() == 1) {
                switch (tasksBean.getTask_status()) {
                    case 0:
                        tvSend.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        tvSend.setVisibility(View.GONE);
                        break;
                    case 2:
                        tvSend.setVisibility(View.GONE);
                        break;
                }
            } else {
                tvSend.setVisibility(View.GONE);
            }
            ll_look.setVisibility(View.VISIBLE);
            rl_add.setVisibility(View.GONE);

            tv_team_number.setVisibility(View.VISIBLE);
            rl_my_task.setVisibility(View.GONE);
            rl_my_task_finish.setVisibility(View.VISIBLE);

        } else if (UserConfig.getInstance().getRole().equals("2")) {
            //        0显示1隐藏
            if (tasksBean.getHide_expect() == 1) {
                tvSumNumber.setText("**********");
            } else {
                tvSumNumber.setText(tasksBean.getExpect_aims());
            }

            tv_team_number.setVisibility(View.VISIBLE);
            rl_my_task.setVisibility(View.VISIBLE);
            rl_my_task_finish.setVisibility(View.VISIBLE);
            tvSend.setVisibility(View.GONE);
            ll_look.setVisibility(View.GONE);
//            任务状态 0未开始 1进行中 2 已结束
            switch (tasksBean.getTask_status()) {
                case 0:
//                    当前主播对任务的状态 0 未参加 1已参加 2未完成 3已完成 （主播模式有）
                case 1:
                    if (tasksBean.getStatus() == 0) {
                        rl_add.setVisibility(View.VISIBLE);
                    } else {
                        rl_add.setVisibility(View.GONE);
                    }
                    break;
                case 2:
                    rl_add.setVisibility(View.GONE);
                    break;
            }

        } else {
            //2管理员发出的  1是会长发出的
            if (tasksBean.getType() == 2) {
                switch (tasksBean.getTask_status()) {
                    case 0:
                        tvSend.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        tvSend.setVisibility(View.GONE);
                        break;
                    case 2:
                        tvSend.setVisibility(View.GONE);
                        break;
                }
                ll_look.setVisibility(View.VISIBLE);
                rl_add.setVisibility(View.GONE);
                tv_team_number.setVisibility(View.VISIBLE);
                rl_my_task.setVisibility(View.GONE);
                rl_my_task_finish.setVisibility(View.VISIBLE);


//                任务状态 0未开始 1进行中 2 已结束
                switch (tasksBean.getTask_status()) {
                    case 0:
//                    当前主播对任务的状态 0 未参加 1已参加 2未完成 3已完成 （主播模式有）
                    case 1:
                        if (tasksBean.getStatus() == 0) {
                            rl_add.setVisibility(View.VISIBLE);
                        } else {
                            rl_add.setVisibility(View.GONE);
                        }
                        break;
                    case 2:
                        rl_add.setVisibility(View.GONE);
                        break;
                }
            } else {
                tvSend.setVisibility(View.GONE);
//                       任务类型 0管理员 1主播
                if (tasksBean.getTask_type() == 0) {
                    //        0显示1隐藏
                    if (tasksBean.getHide_expect() == 1) {
                        tvSumNumber.setText("**********");
                    } else {
                        tvSumNumber.setText(tasksBean.getExpect_aims());
                    }
                    tv_team_number.setVisibility(View.VISIBLE);
                    rl_my_task.setVisibility(View.VISIBLE);
                    rl_my_task_finish.setVisibility(View.VISIBLE);
                    if (tasksBean.getStatus()==0){
                        ll_look.setVisibility(View.GONE);
                    }else {
                        ll_look.setVisibility(View.VISIBLE);
                    }
                    tvSend.setVisibility(View.GONE);

//            任务状态 0未开始 1进行中 2 已结束
                    switch (tasksBean.getTask_status()) {
                        case 0:
//                    当前主播对任务的状态 0 未参加 1已参加 2未完成 3已完成 （主播模式有）
                        case 1:
                            if (tasksBean.getStatus() == 0) {
                                rl_add.setVisibility(View.VISIBLE);
                            } else {
                                rl_add.setVisibility(View.GONE);
                            }
                            break;
                        case 2:
                            rl_add.setVisibility(View.GONE);
                            break;
                    }
                } else {
                    ll_look.setVisibility(View.VISIBLE);
                    rl_add.setVisibility(View.GONE);
                    tv_team_number.setVisibility(View.VISIBLE);
                    rl_my_task.setVisibility(View.GONE);
                    rl_my_task_finish.setVisibility(View.VISIBLE);
                }
            }


//
//            if (tasksBean.getType() == 2) {
//                tvSumNumber.setText(tasksBean.getExpect_aims());
//                switch (tasksBean.getTask_status()) {
//                    case 0:
//                        tvSend.setVisibility(View.VISIBLE);
//                        break;
//                    case 1:
//                        tvSend.setVisibility(View.GONE);
//                        break;
//                    case 2:
//                        tvSend.setVisibility(View.GONE);
//                        break;
//                }
//                rl_add.setVisibility(View.GONE);
//                rl_my_task.setVisibility(View.GONE);
//                rl_my_task_finish.setVisibility(View.GONE);
//
//            } else {
//                tvSend.setVisibility(View.GONE);
//                ll_look.setVisibility(View.GONE);
//                switch (tasksBean.getTask_status()) {
//                    case 0:
//                    case 1:
//                        if (tasksBean.getStatus() == 0) {
//                            rl_add.setVisibility(View.VISIBLE);
//                        } else {
//                            rl_add.setVisibility(View.GONE);
//                        }
//                        break;
//                    case 2:
//                        rl_add.setVisibility(View.GONE);
//                        break;
//                }
//            }
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
        if (mProgressDialog != null && mProgressDialog.isResumed()) {
            mProgressDialog.dismiss();
        }
        mPresenter.taskDetail(task_id);
    }


    //获取详细接口
    @Override
    public void taskDetail(NewTaskDataBean.TasksBean bean) {
        tasksBean = bean;
        initview(bean);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.setting_back, R.id.tv_send, R.id.im_look, R.id.im_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_back:
                finish();
                break;
            case R.id.tv_send:
                Intent intent1 = new Intent(NewTaskDetailActivity.this, ActivityAddNewTask.class).putExtra("flag", true);
                intent1.putExtra("task_id", id + "");
                startActivity(intent1);
                finish();
                break;
            case R.id.im_look:
                Intent intent = new Intent(NewTaskDetailActivity.this, TaskStateActivity.class);
                intent.putExtra("bean", tasksBean);
                startActivity(intent);
                break;
            case R.id.im_add:
                if (mProgressDialog == null) {
                    mProgressDialog = new ProgressDialogFragment();
                }
                mProgressDialog.show(this.getSupportFragmentManager());
                mPresenter.partIn(id + "");

                break;
        }
    }
}
