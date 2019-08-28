package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.response.CollectBean;
import shangri.example.com.shangri.model.bean.response.NewTaskDataBean;
import shangri.example.com.shangri.ui.listener.NewsListListener;
import shangri.example.com.shangri.ui.listener.PraiseListener;
import shangri.example.com.shangri.ui.view.CircleImageView;
import shangri.example.com.shangri.util.DensityUtil;
import shangri.example.com.shangri.util.ViewHolder;


/**
 * 咨询 adapter
 * Created by chengaofu on 2017/6/22.
 */

public class TaskStatePeopleAdapter extends BaseListAdapter<NewTaskDataBean.TasksBean.NoJoinBean> {

    private Context mContext;
    private Animation mLikeAnim;
    String Title;

    public TaskStatePeopleAdapter(Context context, int layoutId, List<NewTaskDataBean.TasksBean.NoJoinBean> datas, String title) {
        super(context, layoutId, datas);
        mContext = context;
        Title = title;
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
    }

    @Override
    public void convert(ViewHolder helper, NewTaskDataBean.TasksBean.NoJoinBean noJoinBean) {

        CircleImageView im_image = helper.getView(R.id.im_image);
        helper.setText(R.id.tv_name, noJoinBean.getNickname() + "");

        Glide.with(mContext)
                .load(noJoinBean.getAvatar_url() + "")
                .placeholder(R.mipmap.bg_touxiang)
                .crossFade()
                .into(im_image);
        TextView tv_nostart_number = helper.getView(R.id.tv_nostart_number);
        LinearLayout ll_start_info = helper.getView(R.id.ll_start_info);
        ProgressBar progress = helper.getView(R.id.progress);
        ImageView carimage = helper.getView(R.id.carimage);
        TextView baifenbi = helper.getView(R.id.baifenbi);
        if (Title.contains("已参加") || Title.contains("未参加")) {
            tv_nostart_number.setVisibility(View.VISIBLE);
            tv_nostart_number.setText("任务目标：" + noJoinBean.getTarget_value() + "");
            ll_start_info.setVisibility(View.GONE);

        } else {
            tv_nostart_number.setVisibility(View.GONE);
            ll_start_info.setVisibility(View.VISIBLE);

            helper.setText(R.id.tv_task_number, "任务目标：" + noJoinBean.getTarget_value() + "");
            helper.setText(R.id.tv_full_number, "已完成：" + noJoinBean.getReality_value() + "");
            //完成百分比
            progress.setProgress(noJoinBean.getPercent());
            baifenbi.setText(noJoinBean.getPercent() + "%");
            if (noJoinBean.getPercent() < 100) {
                int pro1 = (int) (150 * (Double.valueOf(noJoinBean.getPercent()) / 100));
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
        }


    }

    @Override
    public void convert(ViewHolder helper, NewTaskDataBean.TasksBean.NoJoinBean noJoinBean, List<Object> payloads) {

    }


}
