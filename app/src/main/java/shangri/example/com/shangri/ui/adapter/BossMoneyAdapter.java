package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.response.BossMoneyBean;
import shangri.example.com.shangri.model.bean.response.CycleBean;
import shangri.example.com.shangri.util.ViewHolder;


/**
 * 咨询 adapter
 * Created by chengaofu on 2017/6/22.
 */

public class BossMoneyAdapter extends BaseListAdapter<BossMoneyBean> {

    private Context mContext;
    private Animation mLikeAnim;


    public BossMoneyAdapter(Context context, int layoutId, List<BossMoneyBean> datas) {
        super(context, layoutId, datas);
        mContext = context;
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
    }

    @Override
    public void convert(final ViewHolder helper, BossMoneyBean data) { //, List<Object> payloads
        TextView tv_text = helper.getView(R.id.tv_text);
        tv_text.setText(data.getName());
        if (data.isIsfocks()) {
            tv_text.setBackground(mContext.getResources().getDrawable(R.drawable.color_dialog_commit_shape2));
            tv_text.setTextColor(mContext.getResources().getColor(R.color.white));
        } else {
            tv_text.setBackground(mContext.getResources().getDrawable(R.drawable.color_dialog_commit_shape16));
            tv_text.setTextColor(mContext.getResources().getColor(R.color.color_999999));
        }
    }


    @Override
    public void convert(ViewHolder helper, BossMoneyBean data, List<Object> payloads) {
    }


}
