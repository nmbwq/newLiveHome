package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.ChuandiBean;
import shangri.example.com.shangri.model.bean.response.DetailBean;
import shangri.example.com.shangri.ui.listener.NewsListListener;
import shangri.example.com.shangri.ui.listener.PraiseListener;

import shangri.example.com.shangri.util.TimeUtil;
import shangri.example.com.shangri.util.ViewHolder;

import java.util.List;

/**
 * 主播资讯 adapter
 * Created by chengaofu on 2017/6/22.
 */

public class DetailedAdapter extends BaseListAdapter<DetailBean.DetailDataBean> {

    private Animation mLikeAnim;
    private Context mContext;
    private PraiseListener mPraiseListener;
    private NewsListListener mNewsListListener;

    public DetailedAdapter(Context context, int layoutId, List<DetailBean.DetailDataBean> datas) {
        super(context, layoutId, datas);
        mContext = context;
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
    }

    @Override
    public void convert(final ViewHolder helper, DetailBean.DetailDataBean data) {
        //魅力增长值
        TextView tv_data = helper.getView(R.id.tv_data);
        //开播主播
        TextView tv_charm = helper.getView(R.id.tv_charm);
        //开播时长
        TextView tv_begin = helper.getView(R.id.tv_begin);

        //时间
        TextView tv_Fans = helper.getView(R.id.tv_Fans);
        //粉丝增长
        TextView tv_fensi = helper.getView(R.id.tv_fensi);
        TextView tv_weeknumber = helper.getView(R.id.tv_weeknumber);
        if (Double.parseDouble(data.getGifts())>10000){
            tv_data.setText(Double.parseDouble(data.getGifts())/10000+"w");
        }else {
            tv_data.setText(data.getGifts());
        }
        if (data.getAdded_fans() != null) {
            tv_fensi.setText(data.getAdded_fans());
        } else {
            tv_fensi.setText(data.getIncome_gift());
        }

        tv_begin.setText(data.getLive_time());

        if (data.getGifts() != null) {
            tv_charm.setText(data.getAnchors());
        } else {
            tv_charm.setText(data.getIncome_diamond());
        }

        if (ChuandiBean.Moneytime_slot.equals("week")) {
            tv_weeknumber.setVisibility(View.GONE);
            String weekDate = TimeUtil.getMyDate(data.getDate(), "yyyy-MM-dd", "MM月dd日");
            tv_Fans.setText(weekDate);
        } else if (ChuandiBean.Moneytime_slot.equals("whole_week")) {
            tv_weeknumber.setVisibility(View.VISIBLE);
            String[] strings = stringCut(data.getDate());
            int weekNumByTime = TimeUtil.getWeekNumByTime(strings[0]);
            String weekDate = TimeUtil.getMyDate(strings[0]) + "~" + TimeUtil.getMyDate(strings[1]);
            tv_weeknumber.setText(weekDate);
            tv_Fans.setText("第" + weekNumByTime + "周");
        } else if (ChuandiBean.Moneytime_slot.equals("whole_month")) {
            tv_weeknumber.setVisibility(View.GONE);
            String weekDate = TimeUtil.getMyDate(data.getDate(), "yyyy-MM", "yyyy年MM月");
            tv_Fans.setText(weekDate);
        } else if (ChuandiBean.Moneytime_slot.equals("ziding")) {
            tv_weeknumber.setVisibility(View.GONE);
            String weekDate = TimeUtil.getMyDate(data.getDate(), "yyyy-MM-dd", "MM月dd日");
            tv_Fans.setText(weekDate);
        }
        if (UserConfig.getInstance().getRole().equals("2")) {
            tv_charm.setVisibility(View.GONE);
        } else {
            tv_charm.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void convert(ViewHolder helper, DetailBean.DetailDataBean data, List<Object> payloads) { //item局部刷新

    }

//    public void setPraiseListener(PraiseListener praiseListener) {
//        this.mPraiseListener = praiseListener;
//    }
//
//    public void setNewsListListener(NewsListListener newsListListener) { //列表点击
//        this.mNewsListListener = newsListListener;
//    }

    /**
     * 字符串截取
     */

    public String[] stringCut(String s) {
        String[] aa = s.split("~");
        return aa;
    }

}
