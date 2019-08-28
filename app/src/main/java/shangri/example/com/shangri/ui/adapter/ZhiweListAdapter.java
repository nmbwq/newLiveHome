package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;
import com.github.mikephil.charting.formatter.IFillFormatter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.response.PositionListBean;
import shangri.example.com.shangri.model.bean.response.legalMypagerBean;
import shangri.example.com.shangri.ui.webview.AnchorBossWebView;
import shangri.example.com.shangri.ui.webview.AnchorBossWebView2;
import shangri.example.com.shangri.ui.webview.BossWebView;
import shangri.example.com.shangri.ui.webview.ZhiWeiWebView;
import shangri.example.com.shangri.util.TimeUtil;
import shangri.example.com.shangri.util.ToastUtil;
import shangri.example.com.shangri.util.ViewHolder;

/**
 * Created by Administrator on 2018/1/3.
 */

public class ZhiweListAdapter extends BaseListAdapter<PositionListBean.ListBean.DataBean> {
    private Context mContext;
    private Animation mLikeAnim;
    List<PositionListBean.ListBean.DataBean> data = new ArrayList<>();


    public ZhiweListAdapter(Context context, int layoutId, final List<PositionListBean.ListBean.DataBean> datas) {
        super(context, layoutId, datas);
        mContext = context;
        data = datas;
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
    }

    @Override
    public void convert(final ViewHolder helper, final PositionListBean.ListBean.DataBean data) {

        ImageView im_hot = helper.getView(R.id.im_hot);
        if (data.getHot() == 1) {
            im_hot.setVisibility(View.VISIBLE);
        } else {
            im_hot.setVisibility(View.GONE);
        }
//        是否广告位 1是 0否
        if (data.getIs_ggw() == 1 || data.getHot() == 1) {
            im_hot.setVisibility(View.VISIBLE);
        } else {
            im_hot.setVisibility(View.GONE);
        }
        if (data.getTitle().length() > 0) {
            helper.setText(R.id.tv_anchor_name, data.getTitle());
        } else {
            helper.setText(R.id.tv_anchor_name, data.getType_name());
        }


        TextView tv_xianxia = helper.getView(R.id.tv_xianxia);
        switch (data.getJob_method()) {
            case 1:
                tv_xianxia.setText("线上");
                break;
            case 2:
                tv_xianxia.setText("线下");
                break;
            case 3:
                tv_xianxia.setText("线上/线下");
                break;
        }
        TextView tv_week = helper.getView(R.id.tv_week);
        switch (data.getSalary_type()) {
            case 1:
                tv_week.setText("月结");
                break;
            case 2:
                tv_week.setText("周结");
                break;
            case 3:
                tv_week.setText("日结");
                break;
            case 4:
                tv_week.setText("月结/周结/日结");
                break;
        }

        TextView tv_plat_name = helper.getView(R.id.tv_plat_name);
        if (data.getPlat_name().size() == 0) {
            tv_plat_name.setVisibility(View.GONE);
        } else {
            tv_plat_name.setVisibility(View.VISIBLE);
            tv_plat_name.setText(data.getPlat_name().get(0).getPlat_name());
        }

        TextView tv_welface = helper.getView(R.id.tv_welface);
        if (data.getWelfare().size() == 0) {
            tv_welface.setVisibility(View.GONE);
        } else {
            tv_welface.setVisibility(View.VISIBLE);
            if (data.getWelfare().size() == 1) {
                tv_welface.setText(data.getWelfare().get(0));
            } else {
                tv_welface.setText(data.getWelfare().get(0) + " | " + data.getWelfare().get(1));
            }
        }
        TextView tv_state = helper.getView(R.id.tv_state);
        LinearLayout ll_time = helper.getView(R.id.ll_time);
        TextView tv_time_hour = helper.getView(R.id.tv_time_hour);
        TextView tv_time_minutes = helper.getView(R.id.tv_time_minutes);

        switch (data.getStatus()) {
            case 1:
                if (data.getExpire_time() != null) {
                    if (Integer.parseInt(data.getExpire_time()) > 0) {
                        ll_time.setVisibility(View.VISIBLE);
                        tv_state.setVisibility(View.GONE);
                        int hours = (int) (data.getTime() / 60 / 60);
                        int minutes = (int) data.getTime() / 60 % 60;
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
                    } else {
                        ll_time.setVisibility(View.GONE);
                        tv_state.setVisibility(View.VISIBLE);
                        tv_state.setTextColor(mContext.getResources().getColor(R.color.color_d0a76c));
                        tv_state.setText("升级职位栏");
                    }
                }
                break;
            case 2:
                tv_state.setVisibility(View.VISIBLE);
                ll_time.setVisibility(View.GONE);
                tv_state.setTextColor(mContext.getResources().getColor(R.color.color_d0a76c));
                tv_state.setText("审核中");

                break;
            case 3:
                tv_state.setVisibility(View.VISIBLE);
                ll_time.setVisibility(View.GONE);
                tv_state.setTextColor(mContext.getResources().getColor(R.color.color_db3334));
                tv_state.setText("审核失败");
                break;
            case 4:
                tv_state.setVisibility(View.VISIBLE);
                ll_time.setVisibility(View.GONE);
                tv_state.setTextColor(mContext.getResources().getColor(R.color.color_666666));
                tv_state.setText("已关闭");
                break;
            case 5:
                tv_state.setVisibility(View.VISIBLE);
                ll_time.setVisibility(View.GONE);
                tv_state.setTextColor(mContext.getResources().getColor(R.color.color_db3334));
                tv_state.setText("已下架");
                break;
        }
        helper.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (UserConfig.getInstance().getRole().equals("2")) {
                    Log.e("AnchorBossWebView2", "onClick: id = " + data.getId());
                    mContext.startActivity(new Intent(mContext, AnchorBossWebView2.class).putExtra("need_id", data.getId() + ""));
                } else {
                    if (data.getStatus() == 2) {
                        ToastUtil.showShort("该职位正在审核中");
                    } else {
                        mContext.startActivity(new Intent(mContext, ZhiWeiWebView.class).putExtra("bean", data));
                    }
                }
            }
        });
    }

    @Override
    public void convert(ViewHolder helper, PositionListBean.ListBean.DataBean dataBean, List<Object> payloads) {

    }


    public String getTimeFromInt(long time) {
        if (time <= 0) {
            return "已结束";
        }
        long day = time / (1 * 60 * 60 * 24);
        long hour = time / (1 * 60 * 60) % 24;
        long minute = time / (1 * 60) % 60;
        long second = time / (1) % 60;
        return "还剩：" + day + "天" + hour + "小时" + minute + "分" + second + "秒";
    }


}
