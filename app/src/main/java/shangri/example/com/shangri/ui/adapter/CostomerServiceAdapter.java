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
import shangri.example.com.shangri.model.bean.response.RequestListBean;
import shangri.example.com.shangri.model.bean.response.companyListBean;
import shangri.example.com.shangri.ui.activity.ActivityAddNewTask;
import shangri.example.com.shangri.util.ViewHolder;

/**
 * Created by Administrator on 2018/1/3.
 */

public class CostomerServiceAdapter extends BaseListAdapter<RequestListBean.QaBean> {
    private Context mContext;
    private Animation mLikeAnim;
    List<RequestListBean.QaBean> data = new ArrayList<>();
    //    MyAnchoritemAdapter mAdapter;


    public CostomerServiceAdapter(Context context, int layoutId, List<RequestListBean.QaBean> datas) {
        super(context, layoutId, datas);
        mContext = context;
        data = datas;
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
    }

    @Override
    public void convert(ViewHolder helper, RequestListBean.QaBean companysBean) {

        TextView tv_name = helper.getView(R.id.tv_name);
        ImageView im_select = helper.getView(R.id.im_select);
        tv_name.setText(companysBean.getQuestion() + "");

    }

    @Override
    public void convert(ViewHolder helper, RequestListBean.QaBean companysBean, List<Object> payloads) {

    }

}
