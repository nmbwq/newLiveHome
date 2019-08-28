package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.response.CollectBean;
import shangri.example.com.shangri.model.bean.response.CultivateBean;
import shangri.example.com.shangri.ui.webview.AnchorBossWebView;
import shangri.example.com.shangri.ui.webview.BossWebView;
import shangri.example.com.shangri.util.ViewHolder;


/**
 * 咨询 adapter
 * Created by chengaofu on 2017/6/22.
 */

public class CultivateDialogAdapter extends BaseListAdapter<CultivateBean.CatagoryBean> {

    private Context mContext;
    private Animation mLikeAnim;


    public CultivateDialogAdapter(Context context, int layoutId, List<CultivateBean.CatagoryBean> datas) {
        super(context, layoutId, datas);
        mContext = context;
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
    }

    @Override
    public void convert(final ViewHolder helper, final CultivateBean.CatagoryBean data) { //, List<Object> payloads
        RelativeLayout cl_information = helper.getView(R.id.cl_information);
        TextView tv_line = (TextView) helper.getView(R.id.tv_line);
        TextView tv_name = (TextView) helper.getView(R.id.tv_name);
//        //默认第一个选中状态
//        if (helper.getPosition() == 0) {
//            cl_information.setBackgroundColor(context.getResources().getColor(R.color.color_1b1b1b));
//            tv_line.setVisibility(View.VISIBLE);
//            tv_name.setTextColor(context.getResources().getColor(R.color.color_d0a76c));
//        }
        if (data.isClick()) {
            cl_information.setBackgroundColor(mContext.getResources().getColor(R.color.color_1b1b1b));
            tv_line.setVisibility(View.VISIBLE);
            tv_name.setTextColor(mContext.getResources().getColor(R.color.color_d0a76c));
        } else {
            cl_information.setBackgroundColor(mContext.getResources().getColor(R.color.color_202020));
            tv_line.setVisibility(View.GONE);
            tv_name.setTextColor(mContext.getResources().getColor(R.color.white));
        }
        tv_name.setText(data.getName() + "");
    }


    @Override
    public void convert(ViewHolder helper, CultivateBean.CatagoryBean data, List<Object> payloads) {
    }


}
