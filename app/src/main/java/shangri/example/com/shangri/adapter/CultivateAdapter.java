package shangri.example.com.shangri.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;


import shangri.example.com.shangri.R;
import shangri.example.com.shangri.model.bean.request.BaseBeen;
import shangri.example.com.shangri.model.bean.response.CultivateBean;
import shangri.example.com.shangri.util.DpPxUtils;


/**
 * 导航
 */
public class CultivateAdapter extends BaseQuickAdapter<CultivateBean.CatagoryBean, BaseViewHolder> {

    private Context context;
    ;
    public BaseViewHolder holder;
    //
    public boolean IsMoreFour;


    public CultivateAdapter(Context context, Boolean IsMoreFour1, int layoutResId, List<CultivateBean.CatagoryBean> data) {
        super(layoutResId, data);
        this.context = context;
        IsMoreFour = IsMoreFour1;
    }

    @Override
    protected void convert(BaseViewHolder helper, CultivateBean.CatagoryBean item) {
        holder = helper;
        RelativeLayout rl_info = (RelativeLayout) helper.getView(R.id.rl_info);
//        rl_info.getLayoutParams取得是父布局属性
        LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) rl_info.getLayoutParams();
//        大于四个数据 item布局变小
        if (IsMoreFour) {
            linearParams.width = DpPxUtils.dip2px(mContext, 80);// 控件的宽强制设成30  数据要转化为px
        } else {
            linearParams.width = DpPxUtils.dip2px(mContext, 90);// 控件的宽强制设成30
        }
        rl_info.setLayoutParams(linearParams); //使设置好的布局参数应用到控件

        ImageView iv_information = helper.getView(R.id.iv_information);
        TextView tv_name = (TextView) helper.getView(R.id.tv_name);
        tv_name.setText(item.getName());
        String type = item.getType();
        String id = item.getId();
        String img_url = item.getImg_url();
        Log.d("Debug","item是否被选中"+item.isClick());
        if (item.isClick()) {
            tv_name.setTextColor(mContext.getResources().getColor(R.color.color_d0a76c));
        } else {
            tv_name.setTextColor(mContext.getResources().getColor(R.color.white));
        }
        Glide.with(context).load(img_url)
                .dontAnimate()
                .placeholder(R.mipmap.kk_mrtb)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .into(iv_information);
        helper.addOnClickListener(R.id.cl_information);
        helper.addOnClickListener(R.id.iv_information);
    }

}
