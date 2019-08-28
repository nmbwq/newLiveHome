package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.IllegalFormatCodePointException;
import java.util.List;
import java.util.Map;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.event.BrowseEventBean;
import shangri.example.com.shangri.model.bean.response.NewTaskDataBean;
import shangri.example.com.shangri.model.bean.response.PatrolDataBean;
import shangri.example.com.shangri.ui.view.CircleImageView;
import shangri.example.com.shangri.util.AndroidInterface.AlertFace;
import shangri.example.com.shangri.util.CallBackUtils;
import shangri.example.com.shangri.util.DensityUtil;
import shangri.example.com.shangri.util.ToastUtil;
import shangri.example.com.shangri.util.ViewHolder;

/**
 * Created by Administrator on 2018/1/3.
 */

public class NewTaskAdapter extends BaseListAdapter<NewTaskDataBean.TasksBean> {
    private final String mRole;
    private final Map<Integer, Boolean> mList;
    private Context mContext;
    private Animation mLikeAnim;
    private boolean isOpen = true;


    //参加人数
    int joinSize = 0;
    //未参加人数
    int noJoinSize = 0;
    //完成人数
    int OverSize = 0;
    //未完成人数
    int IngSize = 0;

    AlertFace alertFace;


    public NewTaskAdapter(Context context, int layoutId, List<NewTaskDataBean.TasksBean> datas, AlertFace S) {
        super(context, layoutId, datas);
        mRole = UserConfig.getInstance().getRole();
        mContext = context;
        mList = new HashMap<>();
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
        alertFace = S;
    }

    @Override
    public void convert(final ViewHolder helper, final NewTaskDataBean.TasksBean item) {

        CircleImageView im_photo = helper.getView(R.id.im_photo);
        Glide.with(mContext)
                .load(item.getIcon_url() + "")
                .placeholder(R.mipmap.bg_touxiang)
                .crossFade()
                .into(im_photo);
        //公会名称
        helper.setText(R.id.tv_name, item.getGuild_name() + "");
        helper.setText(R.id.tv_task_theme, item.getTheme() + "");
        final TextView tv_task_number = helper.getView(R.id.tv_task_number);
        //撤销的布局
        final RelativeLayout ll_is_chexiao = helper.getView(R.id.ll_is_chexiao);
        final TextView tv_type_chexiao = helper.getView(R.id.tv_type_chexiao);
        final ImageView im_chexiao = helper.getView(R.id.im_chexiao);

        //参加人数
        joinSize = item.getJoin().size() + 0;
        //未参加人数
        noJoinSize = item.getNo_join().size() + 0;
        //完成人数
        OverSize = item.getOver_list().size() + 0;
        //未完成人数
        IngSize = item.getIng_list().size() + 0;
//        进行中显示的时间
        TextView tv_tiem = helper.getView(R.id.tv_tiem);
        LinearLayout ll_comment2 = helper.getView(R.id.ll_comment2);
        TextView tv_type = helper.getView(R.id.tv_type);

        TextView tv_alert = helper.getView(R.id.tv_alert);
        final TextView tv_read_number = helper.getView(R.id.tv_read_number);
        ProgressBar progress = helper.getView(R.id.progress);
        ImageView carimage = helper.getView(R.id.carimage);
        TextView baifenbi = helper.getView(R.id.baifenbi);
        TextView tv_my_or_team = helper.getView(R.id.tv_my_or_team);
        //未开始撤销
        im_chexiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = helper.getPosition();
                Log.d("Debug", "点击的位置是" + position);
                EventBus.getDefault().post(new BrowseEventBean(item.getId() + "", true, position));
            }
        });
        tv_read_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tv_read_number.getText().toString().equals("立即参加")) {
                    Log.d("Debug", "做立即参加操作");
                    alertFace.join(item.getId() + "");
                }
            }
        });

        tv_alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (item.getIs_alert() == 1) {
                    Log.d("Debug", "做提醒操作");
                    alertFace.alert(item.getId() + "");
                }
            }
        });
        //倒计时时间
        tv_tiem.setText(item.getTime_mask() + "");
        //完成百分比
        progress.setProgress(item.getPercent());
        baifenbi.setText(item.getPercent() + "%");
        if (item.getPercent() < 100) {
            int pro1 = (int) (150 * (Double.valueOf(item.getPercent()) / 100));
            int pro = DensityUtil.dip2px(mContext, pro1);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(pro, 0, 0, 0);//4个参数按顺序分别是左上右下
            carimage.setLayoutParams(layoutParams);
        } else {
            int pro = DensityUtil.dip2px(mContext, 150);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(pro, 0, 0, 0);//4个参数按顺序分别是左上右下
            carimage.setLayoutParams(layoutParams);
        }


        tv_task_number.setText(item.getExpect_aims() + "");

//        任务状态 0未开始 1进行中 2 已结束

        switch (item.getTask_status()) {
            case 0:
                helper.setText(R.id.tv_state, "未开始");
                tv_tiem.setVisibility(View.GONE);
                ll_comment2.setVisibility(View.GONE);
                break;
            case 1:
                helper.setText(R.id.tv_state, "进行中");
                tv_tiem.setVisibility(View.VISIBLE);
                ll_comment2.setVisibility(View.VISIBLE);
                break;
            case 2:
                helper.setText(R.id.tv_state, "已结束");
                tv_tiem.setVisibility(View.GONE);
                ll_comment2.setVisibility(View.GONE);
                break;
            case 3:
                helper.setText(R.id.tv_state, "已撤销");
                tv_tiem.setVisibility(View.GONE);
                ll_comment2.setVisibility(View.GONE);
                break;
        }

        if (item.getIs_alert() == 1) {
            tv_alert.setVisibility(View.VISIBLE);
            tv_alert.setText("任务提醒");
        } else if (item.getIs_alert() == 2) {
            tv_alert.setVisibility(View.VISIBLE);
            tv_alert.setText("已提醒");
        } else {
            tv_alert.setVisibility(View.GONE);
        }


        if (UserConfig.getInstance().getRole().equals("1")) {
            //1会长  2管理员
            if (item.getType() == 1) {
                switch (item.getTask_status()) {
                    case 0:
                        //撤销的判断
                        ll_is_chexiao.setVisibility(View.VISIBLE);
                        im_chexiao.setVisibility(View.VISIBLE);
                        tv_type_chexiao.setText("未开始");
                        break;
                    case 3:
                        //撤销的判断
                        ll_is_chexiao.setVisibility(View.VISIBLE);
                        im_chexiao.setVisibility(View.GONE);
                        tv_type_chexiao.setText("已撤销");
                        break;
                    default:
                        ll_is_chexiao.setVisibility(View.GONE);
                        break;
                }
                tv_type.setText("我");
            } else if (item.getType() == 2) {
                ll_is_chexiao.setVisibility(View.GONE);
                tv_type.setText("管理员");
            } else {
                ll_is_chexiao.setVisibility(View.GONE);
                tv_type.setText("数据出错");
            }
            switch (item.getTask_status()) {
                case 0:
                    tv_read_number.setText("已参加  " + joinSize + "/" + (noJoinSize + joinSize));
                    break;
                case 1:
                    tv_read_number.setText("已完成  " + OverSize + "/" + (IngSize + OverSize + noJoinSize));
                    break;
                case 2:
                    tv_read_number.setText("已完成  " + OverSize + "/" + (IngSize + OverSize + noJoinSize));
                    tv_alert.setVisibility(View.GONE);
                    break;
            }


        } else if (UserConfig.getInstance().getRole().equals("2")) {
            if (item.getType() == 1) {
                tv_type.setText("会长");
                ll_is_chexiao.setVisibility(View.GONE);
            } else if (item.getType() == 2) {
                tv_type.setText("管理员");
                ll_is_chexiao.setVisibility(View.GONE);
            } else {
                tv_type.setText("");
            }
            tv_my_or_team.setText("个人任务目标");
            tv_task_number.setText(item.getTarget_value() + "");

            tv_alert.setVisibility(View.GONE);
            switch (item.getTask_status()) {
                case 0:
                case 1:
                    if (item.getStatus() == 0) {
                        tv_read_number.setText("立即参加");
                    } else if (item.getStatus() == 1) {
                        tv_read_number.setText("已参加");
                    }
                    break;
                case 2:
//                    0 未参加 1已参加 2未完成 3已完成
                    switch (item.getStatus()) {
                        case 0:
                            tv_read_number.setText("未参加");
                            break;
                        case 1:
                            tv_read_number.setText("已参加");
                            break;
                        case 2:
                            tv_read_number.setText("未完成");
                            break;
                        case 3:
                            tv_read_number.setText("已完成");
                            break;
                    }
                    break;
            }

            //下面是管理员的操作
        } else {
            if (item.getType() == 1) {
                tv_type.setText("会长");
                ll_is_chexiao.setVisibility(View.GONE);
            } else if (item.getType() == 2) {
                tv_type.setText("我");
                switch (item.getTask_status()) {
                    case 0:
                        //撤销的判断
                        ll_is_chexiao.setVisibility(View.VISIBLE);
                        im_chexiao.setVisibility(View.VISIBLE);
                        tv_type_chexiao.setText("未开始");
                        break;
                    case 3:
                        //撤销的判断
                        ll_is_chexiao.setVisibility(View.VISIBLE);
                        im_chexiao.setVisibility(View.GONE);
                        tv_type_chexiao.setText("已撤销");
                        break;
                    default:
                        ll_is_chexiao.setVisibility(View.GONE);
                        break;
                }
            } else {
                tv_type.setText("数据出错");
            }
//         当前主播对任务的状态 0 未参加 1已参加 2未完成 3已完成 （主播模式有）
            switch (item.getTask_status()) {
                case 0:
                    //2自己发出的  1是会长发出的
                    if (item.getType() == 2) {
                        tv_read_number.setText("已参加  " + joinSize + "/" + (noJoinSize + joinSize));
                        tv_read_number.setVisibility(View.VISIBLE);
                    } else {
//                        任务类型 0管理员 1主播
                        if (item.getTask_type() == 0) {
                            tv_my_or_team.setText("个人任务目标");
                            tv_task_number.setText(item.getTarget_value() + "");
                            switch (item.getStatus()) {
                                case 0:
                                    tv_read_number.setText("立即参加");
                                    break;
                                case 1:
                                    tv_read_number.setText("已参加");
                                    break;
                                case 2:
                                    tv_read_number.setText("未完成");
                                    break;
                                case 3:
                                    tv_read_number.setText("已完成");
                                    break;
                            }
                            tv_alert.setVisibility(View.GONE);
                            tv_read_number.setVisibility(View.VISIBLE);
                        } else {
                            tv_read_number.setVisibility(View.GONE);
                            tv_alert.setVisibility(View.GONE);
                        }

                    }

                    break;
                case 1:
                    //2自己发出的  1是会长发出的
                    if (item.getType() == 2) {
                        tv_read_number.setText("已完成  " + OverSize + "/" + (IngSize + OverSize + noJoinSize));
                        tv_read_number.setVisibility(View.VISIBLE);
                    } else {
//                        任务类型 0管理员 1主播
                        if (item.getTask_type() == 0) {
                            tv_my_or_team.setText("个人任务目标");
                            tv_task_number.setText(item.getTarget_value() + "");
                            switch (item.getStatus()) {
                                case 0:
                                    tv_read_number.setText("立即参加");
                                    break;
                                case 1:
                                    tv_read_number.setText("已参加");
                                    break;
                                case 2:
                                    tv_read_number.setText("未完成");
                                    break;
                                case 3:
                                    tv_read_number.setText("已完成");
                                    break;
                            }
                            tv_alert.setVisibility(View.GONE);
                            tv_read_number.setVisibility(View.VISIBLE);
                        } else {
                            tv_read_number.setVisibility(View.GONE);
                            tv_alert.setVisibility(View.GONE);
                        }
                    }

                    break;
                case 2:
                    tv_alert.setVisibility(View.GONE);
                    //2自己发出的  1是会长发出的
                    if (item.getType() == 2) {
                        tv_read_number.setText("已完成  " + OverSize + "/" + (IngSize + OverSize + noJoinSize));
                        tv_read_number.setVisibility(View.VISIBLE);
                    } else {
//                        任务类型 0管理员 1主播
                        if (item.getTask_type() == 0) {
                            tv_my_or_team.setText("个人任务目标");
                            tv_task_number.setText(item.getTarget_value() + "");
                            switch (item.getStatus()) {
                                case 0:
                                    tv_read_number.setText("立即参加");
                                    break;
                                case 1:
                                    tv_read_number.setText("已参加");
                                    break;
                                case 2:
                                    tv_read_number.setText("未完成");
                                    break;
                                case 3:
                                    tv_read_number.setText("已完成");
                                    break;
                            }
                            tv_read_number.setVisibility(View.VISIBLE);
                        } else {
                            tv_read_number.setVisibility(View.GONE);
                        }
                    }
                    break;
            }
        }
        //撤销订单  显示横线上面撤销布局  并且 提醒查看布局隐藏
        switch (item.getTask_status()) {
            case 3:
                //撤销的判断
                ll_is_chexiao.setVisibility(View.VISIBLE);
                im_chexiao.setVisibility(View.GONE);
                tv_type_chexiao.setText("已撤销");
                tv_alert.setVisibility(View.GONE);
                //如果自己发的要显示参加人数  此控件 主播身份  隐藏 会长身份 显示   管理员 1 自己发的  显示 2.会长发的 1.发给自己隐藏 2发给自己的主播 隐藏
                tv_read_number.setVisibility(View.GONE);
                break;
        }

    }

    @Override
    public void convert(ViewHolder helper, NewTaskDataBean.TasksBean tasksBean, List<Object> payloads) {

    }


}
