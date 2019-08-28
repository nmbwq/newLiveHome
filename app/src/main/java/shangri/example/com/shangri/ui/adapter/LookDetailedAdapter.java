package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.aspsine.irecyclerview.universaladapter.recyclerview.OnItemClickListener;

import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.request.ReqAnchoDetailParams;
import shangri.example.com.shangri.model.bean.request.TopTenBean;
import shangri.example.com.shangri.model.bean.response.DetailBean;
import shangri.example.com.shangri.model.bean.response.LookDetailBean;
import shangri.example.com.shangri.ui.activity.AnchoDetailActivity;
import shangri.example.com.shangri.ui.listener.NewsListListener;
import shangri.example.com.shangri.ui.listener.PraiseListener;
import shangri.example.com.shangri.util.ViewHolder;

/**
 * 主播资讯 adapter
 * Created by chengaofu on 2017/6/22.
 */

public class LookDetailedAdapter extends BaseListAdapter<LookDetailBean.AnchorsBean> {

    private Animation mLikeAnim;
    private Context mContext;
    private PraiseListener mPraiseListener;
    private NewsListListener mNewsListListener;

    public LookDetailedAdapter(Context context, int layoutId, List<LookDetailBean.AnchorsBean> datas) {
        super(context, layoutId, datas);
        mContext = context;
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
    }

    @Override
    public void convert(final ViewHolder helper, LookDetailBean.AnchorsBean data) {

        TextView tv_name = helper.getView(R.id.tv_name);
        TextView tv_gift = helper.getView(R.id.tv_gift);
        TextView tv_livetime = helper.getView(R.id.tv_livetime);
        TextView tv_lncomediamond = helper.getView(R.id.tv_lncomediamond);
        TextView tv_num = helper.getView(R.id.tv_num);
        int position = helper.getPosition();
        tv_name.setText(data.getAnchor_nickname());
        tv_num.setText((position+1) + "");

        if (data.getIncome_gift() == null || data.getIncome_gift().equals("0.00")) {
            tv_gift.setText(data.getIncome_total());
        } else {
            tv_gift.setText(data.getIncome_gift());
        }

        tv_livetime.setText(data.getLive_time());
        if (data.getAdded_fans().equals("0.00") || data.getAdded_fans() == null) {
            tv_lncomediamond.setText(data.getIncome_diamond());
        } else {
            tv_lncomediamond.setText(data.getAdded_fans());
        }
    }

    @Override
    public void convert(ViewHolder helper, LookDetailBean.AnchorsBean data, List<Object> payloads) { //item局部刷新

    }


}
