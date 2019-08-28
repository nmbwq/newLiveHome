package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.response.PositionListBean;
import shangri.example.com.shangri.model.bean.response.sendResumeBean;
import shangri.example.com.shangri.ui.activity.AnchorDropDetailActivity;
import shangri.example.com.shangri.ui.webview.ZhiWeiWebView;
import shangri.example.com.shangri.util.ToastUtil;
import shangri.example.com.shangri.util.ViewHolder;

/**
 * Created by Administrator on 2018/1/3.
 */

public class SendResumeAdapter extends BaseListAdapter<sendResumeBean.ResumeBean> {
    private Context mContext;
    private Animation mLikeAnim;
    List<sendResumeBean.ResumeBean> data = new ArrayList<>();


    public SendResumeAdapter(Context context, int layoutId, List<sendResumeBean.ResumeBean> datas) {
        super(context, layoutId, datas);
        mContext = context;
        data = datas;
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
    }

    @Override
    public void convert(ViewHolder helper, final sendResumeBean.ResumeBean data) {

        ImageView im_hot = helper.getView(R.id.im_hot);
        if (data.getHot() == 1) {
            im_hot.setVisibility(View.VISIBLE);
        } else {
            im_hot.setVisibility(View.GONE);
        }

        helper.setText(R.id.tv_anchor_name, data.getTitle() + "");
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

        TextView tv_noread = helper.getView(R.id.tv_noread);
        if (data.getIsNotRead() == 0) {
            tv_noread.setText("");
            tv_noread.setBackground(null);
        } else {
            tv_noread.setVisibility(View.VISIBLE);
            tv_noread.setBackground(mContext.getResources().getDrawable(R.mipmap.tuo));
            tv_noread.setText(data.getIsNotRead()+"");
        }
        TextView tv_sum = helper.getView(R.id.tv_sum);
        tv_sum.setText(data.getSend_number() + "");

        helper.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, AnchorDropDetailActivity.class);
                Log.d("Debug", "传递钱的recruit_id" + data.getId());
                Log.d("Debug", "传递钱的recruit_id" + data.getType_name());
                intent.putExtra("type_name", data.getType_name() + "");
                intent.putExtra("recruit_id", data.getId() + "");
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public void convert(ViewHolder helper, sendResumeBean.ResumeBean dataBean, List<Object> payloads) {

    }


}
