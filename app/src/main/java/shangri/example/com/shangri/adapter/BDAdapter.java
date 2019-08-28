package shangri.example.com.shangri.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Date;
import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.response.VirtualCoinBean;
import shangri.example.com.shangri.ui.fragment.NewMineFragment;
import shangri.example.com.shangri.util.DateUtil;
import shangri.example.com.shangri.util.ViewHolder;

/**
 * Description:
 * Data：2018/11/30-14:42
 * Author: lin
 */
public class BDAdapter extends BaseListAdapter<VirtualCoinBean.Packages> {
    private Context mContext;
    OnItemClick onItemClick;
    public BDAdapter(Context context, int layoutId, List<VirtualCoinBean.Packages> datas,OnItemClick onItemClick) {
        super(context, layoutId, datas);
        mContext = context;
        this.onItemClick = onItemClick;
    }

    @Override
    public void convert(ViewHolder helper, final VirtualCoinBean.Packages packages) {
        final int position = helper.getAdapterPosition();
        final TextView title = helper.getView(R.id.title);
        final TextView message = helper.getView(R.id.message);
        final LinearLayout ll_bg = helper.getView(R.id.ll_bg);
        title.setText(packages.getCoin_num()+"波豆"+"");


        String price = "";
        if (packages.getIs_sales().equals("1")){
            if (NewMineFragment.IS_VIP == 1){
                price = packages.getPrice_sales_vip();
            }else {
                price = packages.getPrice_sales();
            }
        }else {
            if (NewMineFragment.IS_VIP == 1){
                price = packages.getPrice_vip();
            }else {
                price = packages.getPrice();
            }
        }
        message.setText("¥"+price);
        if (packages.isChecked()){
            ll_bg.setBackgroundResource(R.mipmap.bdye_xzk);
            title.setTextColor(mContext.getResources().getColor(R.color.text_color_light_yellow));
            message.setTextColor(mContext.getResources().getColor(R.color.text_color_light_yellow));
        }else {
            ll_bg.setBackgroundResource(R.drawable.color_dialog_cancle_shape101);
            title.setTextColor(mContext.getResources().getColor(R.color.white));
            message.setTextColor(mContext.getResources().getColor(R.color.alpha_75_white));
        }
        ll_bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onClick(position);
            }
        });
    }

    @Override
    public void convert(ViewHolder helper, VirtualCoinBean.Packages packages, List<Object> payloads) {

    }


    public interface OnItemClick{
        void onClick(int position);
    }
}
