package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.response.BdBills;
import shangri.example.com.shangri.model.bean.response.BuyVIPDetailBean;
import shangri.example.com.shangri.util.ViewHolder;

/**
 * Description:
 * Data：2018/11/7-11:16
 * Author: lin
 */
public class BdBillsAdapter extends BaseListAdapter<BdBills.Bills> {
    private Animation mLikeAnim;
    private Context context;

    public BdBillsAdapter(Context context, int layoutId, List<BdBills.Bills> datas) {
        super(context, layoutId, datas);
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
        this.context = context;
    }

    @Override
    public void convert(ViewHolder helper, BdBills.Bills dataBean) {

        switch (dataBean.getCategory()) {
//            类型 1购买 2拨电话 3购买合同 4兑换 5留电话 6注册好礼
            case "1":
                switch (dataBean.getPay_type()) {
                    case "2":
                        helper.setImageResource(R.id.iv_pay, R.mipmap.gmmx_wx);
                        break;
                    case "1":
                        helper.setImageResource(R.id.iv_pay, R.mipmap.gmmx_zfb);
                        break;
                    case "3":
                    case "4":
                        helper.setImageResource(R.id.iv_pay, R.mipmap.gmmx_pg);
                        break;
                }
                break;
            case "2":
            case "3":
            case "4":
                helper.setImageResource(R.id.iv_pay, R.mipmap.mx_bdh);
                break;
            case "5":
                helper.setImageResource(R.id.iv_pay, R.mipmap.mx_ldh);
                break;
            case "6":
                helper.setImageResource(R.id.iv_pay, R.mipmap.mx_zchl);
                break;
            case "7":
                helper.setImageResource(R.id.iv_pay, R.mipmap.mx_rzhl);
                break;
            case "8":
                helper.setImageResource(R.id.iv_pay, R.mipmap.mx_mrdl);
                break;
            default:
                helper.setImageResource(R.id.iv_pay, R.mipmap.mx_xfbd);
                break;
        }
        //时间
        helper.setText(R.id.tv_current_time, stampToDate(dataBean.getCreate_time()));
        //Category状态为1 购买 title文字 显示购买波豆加上钱数（状态都是 + 号）
        if (dataBean.getCategory().equals("1")) {
            helper.setText(R.id.tv_frequency, "购买波豆" + " : ¥ " + dataBean.getMoney());
            helper.setText(R.id.tv_figure, "+ " + dataBean.getOperate_num());
        } else {
            //title文字显示  是 dataBean.getTitle()+姓名
            if (dataBean.getCategory().equals("2") || dataBean.getCategory().equals("5")) {
                //            2拨电话 5留电话 拼接getNickname
                helper.setText(R.id.tv_frequency, dataBean.getTitle() + " - " + dataBean.getNickname());
            } else {
                helper.setText(R.id.tv_frequency, dataBean.getTitle());
            }
//            增减类型 1增 2减
            String type = dataBean.getType();
            if (type.equals("1")) {
                helper.setText(R.id.tv_figure, "+ " + dataBean.getOperate_num());
            } else {
                helper.setText(R.id.tv_figure, "- " + dataBean.getOperate_num());
            }
        }

    }

    @Override
    public void convert(ViewHolder helper, BdBills.Bills dataBean, List<Object> payloads) {

    }

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s) {
        String res = "";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm");
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
