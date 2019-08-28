package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.fyales.tagcloud.library.TagBaseAdapter;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.response.TaskDataBean;
import shangri.example.com.shangri.ui.view.RoundImageView;
import shangri.example.com.shangri.util.DateDistance;
import shangri.example.com.shangri.util.DensityUtil;
import shangri.example.com.shangri.util.TimeUtil;
import shangri.example.com.shangri.util.ViewHolder;

/**
 * Created by Administrator on 2018/1/3.
 */

public class CompletionSituationAdapter extends BaseListAdapter<TaskDataBean.TasksBean> {
    private final String mRole;
    private final Map<Integer, Boolean> mList;
    private Context mContext;
    private Animation mLikeAnim;
    private TagBaseAdapter mAdapter, mAdapter1;
    private boolean isOpen = true;

    public CompletionSituationAdapter(Context context, int layoutId, List<TaskDataBean.TasksBean> datas) {
        super(context, layoutId, datas);
        mRole = UserConfig.getInstance().getRole();
        mContext = context;
        mList = new HashMap<>();
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
    }

    @Override
    public void convert(final ViewHolder helper, TaskDataBean.TasksBean pageDataBean) {


        final RoundImageView iv_user_item_icon = helper.getView(R.id.iv_user_item_icon);
        TextView tv_name = helper.getView(R.id.tv_name);
        TextView tv_data = helper.getView(R.id.tv_data);
        TextView tv_name_guild = helper.getView(R.id.tv_name_guild);
        TextView tv_guize = helper.getView(R.id.tv_guize);
        TextView tv_text2 = helper.getView(R.id.tv_text2);
        TextView tv_comment2 = helper.getView(R.id.tv_comment2);
        TextView tv6 = helper.getView(R.id.tv6);
        TextView tv7 = helper.getView(R.id.tv7);
        TextView tv9 = helper.getView(R.id.tv9);

        TextView baifenbi = helper.getView(R.id.baifenbi);

        ProgressBar progress = helper.getView(R.id.progress);
        ImageView carimage = helper.getView(R.id.carimage);

        TextView tv5 = helper.getView(R.id.tv5);
        TextView tv4 = helper.getView(R.id.tv4);


        tv_name.setText("发给: " + pageDataBean.getGuild_name());
        try {
            tv_data.setText(TimeUtil.longToString(Long.parseLong(pageDataBean.getCreate_time()), "yyyy-MM-dd HH:mm:ss"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Glide.with(mContext).load(pageDataBean.getIcon_url()).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                iv_user_item_icon.setImageBitmap(resource);
            }
        });
        tv_name_guild.setText(pageDataBean.getTheme());
        tv_guize.setText(pageDataBean.getContent());
        tv_text2.setText(pageDataBean.getExpect_aims());
        tv_comment2.setText(pageDataBean.getTotal_aims());
        try {
            tv6.setText("开始时间: " + TimeUtil.longToString(Long.parseLong(pageDataBean.getStart_time()), "yyyy-MM-dd HH:mm:ss"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            tv7.setText("结束时间: " + TimeUtil.longToString(Long.parseLong(pageDataBean.getEnd_time()), "yyyy-MM-dd HH:mm:ss"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tv9.setText(pageDataBean.getStart_time() + "人参加");


        double p = Double.valueOf(pageDataBean.getTotal_aims()) / Double.valueOf(pageDataBean.getExpect_aims());
        int p1 = (int) p;
        progress.setProgress(p1);
        baifenbi.setText(p1 + "%");
        int pro1 = (int) (180 * (Double.valueOf(p1) / 100));
        int pro = DensityUtil.dip2px(mContext, pro1);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(pro, 0, 0, 0);//4个参数按顺序分别是左上右下
        carimage.setLayoutParams(layoutParams);

        tv5.setText(DateDistance.getDistanceTime(pageDataBean.getStart_time(), pageDataBean.getEnd_time()));
        if (Long.valueOf(pageDataBean.getStart_time()) < System.currentTimeMillis()) {
            tv4.setText("预发布");
        } else if (Long.valueOf(pageDataBean.getStart_time()) > System.currentTimeMillis() && Long.valueOf(pageDataBean.getEnd_time()) > System.currentTimeMillis()) {
            tv4.setText("进行中");
        } else {
            tv4.setText("已结束");
        }


    }

    @Override
    public void convert(ViewHolder helper, TaskDataBean.TasksBean pageDataBean, List<Object> payloads) {

    }
}
