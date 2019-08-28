package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.response.EncyclopediaHomeBean;
import shangri.example.com.shangri.model.bean.response.PricePackageBean;
import shangri.example.com.shangri.ui.activity.NewLoginUserActivity;
import shangri.example.com.shangri.ui.webview.EncyclopediaActivityWebView;
import shangri.example.com.shangri.util.CornersTransform;
import shangri.example.com.shangri.util.PointUtils;
import shangri.example.com.shangri.util.ViewHolder;


/**
 * 咨询 adapter
 * Created by chengaofu on 2017/6/22.
 */

public class MoneyAdapter extends BaseListAdapter<PricePackageBean.ListBean> {

    private Context mContext;
    private Animation mLikeAnim;

    public MoneyAdapter(Context context, int layoutId, List<PricePackageBean.ListBean> datas) {
        super(context, layoutId, datas);
        mContext = context;
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
    }

    @Override
    public void convert(ViewHolder helper, PricePackageBean.ListBean listBean) {
        TextView tv_one = helper.getView(R.id.tv_one);
        TextView tv_text = helper.getView(R.id.tv_text);
        if (helper.getPosition() == 0) {
            tv_one.setVisibility(View.VISIBLE);
        } else {
            tv_one.setVisibility(View.INVISIBLE);
        }
        tv_text.setText("提现"+listBean.getPrice()+"元");
        if (listBean.isIsselect()) {
            tv_text.setBackground(mContext.getResources().getDrawable(R.mipmap.txsq_xzk));
            tv_text.setTextColor(mContext.getResources().getColor(R.color.color_e3be84));
        } else {
            tv_text.setBackground(mContext.getResources().getDrawable(R.drawable.color_dialog_commit_shape44));
            tv_text.setTextColor(mContext.getResources().getColor(R.color.white));

        }
    }

    @Override
    public void convert(ViewHolder helper, PricePackageBean.ListBean listBean, List<Object> payloads) {

    }

}
