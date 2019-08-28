package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.response.companyListBean;
import shangri.example.com.shangri.ui.activity.ActivityAddNewTask;
import shangri.example.com.shangri.util.ViewHolder;

/**
 * Created by Administrator on 2018/1/3.
 */

public class AlertDateAdapter extends BaseListAdapter<companyListBean.CompanysBean> {
    private Context mContext;
    private Animation mLikeAnim;
    List<companyListBean.CompanysBean> data = new ArrayList<>();
    //    MyAnchoritemAdapter mAdapter;


    public AlertDateAdapter(Context context, int layoutId, List<companyListBean.CompanysBean> datas) {
        super(context, layoutId, datas);
        mContext = context;
        data = datas;
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
    }

    @Override
    public void convert(ViewHolder helper, companyListBean.CompanysBean companysBean) {

        TextView tv_name = helper.getView(R.id.tv_name);
        ImageView im_select = helper.getView(R.id.im_select);
        tv_name.setText(companysBean.getCompany_name() + "");

        if (helper.getPosition() == ActivityAddNewTask.expire_remind) {
            im_select.setVisibility(View.VISIBLE);
            tv_name.setTextColor(mContext.getResources().getColor(R.color.text_color_task));
        } else {
            im_select.setVisibility(View.GONE);
            tv_name.setTextColor(mContext.getResources().getColor(R.color.text_color_light_black));
        }

    }

    @Override
    public void convert(ViewHolder helper, companyListBean.CompanysBean companysBean, List<Object> payloads) {

    }

}
