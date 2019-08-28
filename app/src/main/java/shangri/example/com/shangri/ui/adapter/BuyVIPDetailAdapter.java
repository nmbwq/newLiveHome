package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.chad.library.adapter.base.BaseViewHolder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.response.BuyVIPDetailBean;
import shangri.example.com.shangri.util.ViewHolder;

/**
 * Description:
 * Data：2018/11/7-11:16
 * Author: lin
 */
public class BuyVIPDetailAdapter extends BaseListAdapter<BuyVIPDetailBean.Order.DataBean> {
    private Animation mLikeAnim;
    private Context context;

    public BuyVIPDetailAdapter(Context context, int layoutId, List<BuyVIPDetailBean.Order.DataBean> datas) {
        super(context, layoutId, datas);
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
        this.context = context;
    }

    @Override
    public void convert(ViewHolder helper, BuyVIPDetailBean.Order.DataBean dataBean) {
        String pay_type = dataBean.getPay_method();
        switch (pay_type) {
            case "2":
                helper.setImageResource(R.id.iv_pay, R.mipmap.gmmx_wx);
                break;
            case "1":
                helper.setImageResource(R.id.iv_pay, R.mipmap.gmmx_zfb);
                break;
            case "3":
                helper.setImageResource(R.id.iv_pay, R.mipmap.gmmx_pg);
                break;
        }


        helper.setText(R.id.tv_frequency, dataBean.getPackage_name() + " : " + dataBean.getPay_price() + "元");
        helper.setText(R.id.tv_current_time, stampToDate(dataBean.getCreate_time()));
        helper.setText(R.id.tv_figure, "- " + "¥" + dataBean.getPay_price());
    }

    @Override
    public void convert(ViewHolder helper, BuyVIPDetailBean.Order.DataBean dataBean, List<Object> payloads) {

    }

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s) {
        String res = "";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Long value = Long.valueOf(s) * 1000;
        Date date = new Date(value);
        try {
            res = simpleDateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }
}
