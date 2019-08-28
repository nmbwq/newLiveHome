package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.formatter.IFillFormatter;

import java.util.ArrayList;
import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.response.CompactMobanResponse;
import shangri.example.com.shangri.model.bean.response.upgradePackageBean;
import shangri.example.com.shangri.ui.activity.CompactWebView.AddCompactlWebviewOne;
import shangri.example.com.shangri.util.ViewHolder;

/**
 * 升级职位栏
 */

public class PostbarAdapter extends BaseListAdapter<upgradePackageBean.ListBean> {
    private Context mContext;
    private Animation mLikeAnim;
    List<upgradePackageBean.ListBean> data = new ArrayList<>();


    public PostbarAdapter(Context context, int layoutId, List<upgradePackageBean.ListBean> datas) {
        super(context, layoutId, datas);
        mContext = context;
        data = datas;
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
    }

    @Override
    public void convert(ViewHolder helper, final upgradePackageBean.ListBean templatesBean) {

        LinearLayout ll_bg = helper.getView(R.id.ll_bg);
        TextView title = helper.getView(R.id.title);
        TextView message = helper.getView(R.id.message);
        if (templatesBean.isClick()) {
            ll_bg.setBackground(mContext.getResources().getDrawable(R.mipmap.zwl_xz));
            title.setTextColor(mContext.getResources().getColor(R.color.color_d0a76c));
            message.setTextColor(mContext.getResources().getColor(R.color.color_d0a76c));
        } else {
            ll_bg.setBackground(mContext.getResources().getDrawable(R.mipmap.zwl_wxz));
            title.setTextColor(mContext.getResources().getColor(R.color.white));
            message.setTextColor(mContext.getResources().getColor(R.color.color_999999));
        }
        title.setText(templatesBean.getHold_time() + "小时");
        message.setText(templatesBean.getHf_bd() + "波豆");
    }

    @Override
    public void convert(ViewHolder helper, upgradePackageBean.ListBean templatesBean, List<Object> payloads) {

    }


}
