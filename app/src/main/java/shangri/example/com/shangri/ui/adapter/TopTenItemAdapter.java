package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.request.TopTenBean;
import shangri.example.com.shangri.ui.listener.NewsListListener;
import shangri.example.com.shangri.ui.listener.PraiseListener;
import shangri.example.com.shangri.util.ViewHolder;

import java.util.List;

/**
 * 主播资讯 adapter
 * Created by chengaofu on 2017/6/22.
 */

public class TopTenItemAdapter extends BaseListAdapter<TopTenBean.QuickDataBean.DataBeanXX.DataBeanX> {

    private Animation mLikeAnim;
    private Context mContext;
    private PraiseListener mPraiseListener;
    private NewsListListener mNewsListListener;

    private RecyclerView mNewsAnchorRecycler;

    public TopTenItemAdapter(Context context, int layoutId, List<TopTenBean.QuickDataBean.DataBeanXX.DataBeanX> datas) {
        super(context, layoutId, datas);
        mContext = context;
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
    }

    @Override
    public void convert(final ViewHolder helper, TopTenBean.QuickDataBean.DataBeanXX.DataBeanX data) {

        TextView tv_data = helper.getView(R.id.tv_data);
        TextView tv_charm = helper.getView(R.id.tv_charm);
        TextView tv_Fans = helper.getView(R.id.tv_Fans);
        ImageView im1 = helper.getView(R.id.im1);


        tv_data.setText(String.valueOf(helper.getPosition()+1));
        tv_charm.setText(String.valueOf(data.getAnchor_name()));
        tv_Fans.setText(String.valueOf(data.getCount()));
        switch (helper.getPosition()) {
            case 0:
                im1.setVisibility(View.VISIBLE);
                tv_data.setVisibility(View.GONE);
                im1.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.diyi));
                break;
            case 1:
                im1.setVisibility(View.VISIBLE);
                tv_data.setVisibility(View.GONE);
                im1.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.dier));
                break;
            case 2:
                im1.setVisibility(View.VISIBLE);
                tv_data.setVisibility(View.GONE);
                im1.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.disan));
                break;
            default:
                im1.setVisibility(View.GONE);
                tv_data.setVisibility(View.VISIBLE);
                break;

        }

    }

    @Override
    public void convert(ViewHolder helper, TopTenBean.QuickDataBean.DataBeanXX.DataBeanX data, List<Object> payloads) { //item局部刷新

    }

//    public void setPraiseListener(PraiseListener praiseListener) {
//        this.mPraiseListener = praiseListener;
//    }
//
//    public void setNewsListListener(NewsListListener newsListListener) { //列表点击
//        this.mNewsListListener = newsListListener;
//    }

}
