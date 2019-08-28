package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.response.BossDataBean;
import shangri.example.com.shangri.ui.webview.AnchorBossWebView;
import shangri.example.com.shangri.ui.webview.AnchorBossWebView2;
import shangri.example.com.shangri.ui.webview.BossWebView;
import shangri.example.com.shangri.util.StartActivityUtils;
import shangri.example.com.shangri.util.ViewHolder;


/**
 * 咨询 adapter
 * Created by chengaofu on 2017/6/22.
 */

public class BossAdapter1 extends BaseListAdapter<BossDataBean.ListBean.DataBean> implements ListAdapter {

    private Context mContext;
    private Animation mLikeAnim;


    public BossAdapter1(Context context, int layoutId, List<BossDataBean.ListBean.DataBean> datas) {
        super(context, layoutId, datas);
        mContext = context;
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
    }

    @Override
    public void convert(final ViewHolder helper, final BossDataBean.ListBean.DataBean data) { //, List<Object> payloads
        ImageView im_hot = helper.getView(R.id.im_hot);

        ImageView im_is_vip = helper.getView(R.id.im_is_vip);
        //是不是vip
        if (data.getIs_vip() == 1) {
            im_is_vip.setVisibility(View.VISIBLE);
        } else {
            im_is_vip.setVisibility(View.GONE);
        }

        //        是否广告位 1是 0否
        if (data.getIs_ggw() == 1||data.getHot().equals("1")) {
           im_hot.setVisibility(View.VISIBLE);
        } else {
            im_hot.setVisibility(View.GONE);
        }


        ImageView im_is_guanfang = helper.getView(R.id.im_is_guanfang);
//        发布类型 1系统（爬取的）2 官方（手动发布）
        if (data.getPublish_type() == 3) {
            im_is_guanfang.setVisibility(View.VISIBLE);
        } else {
            im_is_guanfang.setVisibility(View.GONE);
        }

        ImageView im_is_phone = helper.getView(R.id.im_is_phone);
//       是否沟通 1是 0否
        if (data.getIs_collect().equals("0")) {
            im_is_phone.setVisibility(View.GONE);
        } else {
            im_is_phone.setVisibility(View.VISIBLE);
        }
        TextView tv_anchor_name = helper.getView(R.id.tv_anchor_name);
        tv_anchor_name.setText( data.getType_name()+"");

        TextView tv_xianxia = helper.getView(R.id.tv_xianxia);
        if (data.getJob_method() != null) {
            tv_xianxia.setVisibility(View.VISIBLE);
            switch (data.getJob_method()) {
                case "1":
                    tv_xianxia.setText("线上");
                    break;
                case "2":
                    tv_xianxia.setText("线下");
                    break;
                case "3":
                    tv_xianxia.setText("线上/线下");
                    break;
            }
        } else {
            tv_xianxia.setVisibility(View.GONE);
        }
        if (tv_xianxia.getText().length() == 0) {
            tv_xianxia.setVisibility(View.GONE);
        }
        TextView tv_week = helper.getView(R.id.tv_week);

        if (data.getSalary_type() != null) {
            tv_week.setVisibility(View.VISIBLE);
            switch (data.getSalary_type()) {
                case "1":
                    tv_week.setText("月结");
                    break;
                case "2":
                    tv_week.setText("周结");
                    break;
                case "3":
                    tv_week.setText("日结");
                    break;
                case "4":
                    tv_week.setText("月结/周结/日结");
                    break;
            }
        } else {
            tv_week.setVisibility(View.GONE);
        }
        if (tv_week.getText().length() == 0) {
            tv_week.setVisibility(View.GONE);
        }
        TextView tv_money = helper.getView(R.id.tv_money);
        tv_money.setText(Integer.parseInt(data.getPay_low()) / 1000 + "K" + " - " + Integer.parseInt(data.getPay_high()) / 1000 + "K");
        TextView tv_plat_name = helper.getView(R.id.tv_plat_name);
        if (data.getPlat_name().size() == 0) {
            tv_plat_name.setVisibility(View.GONE);
        } else {
            tv_plat_name.setVisibility(View.VISIBLE);
            tv_plat_name.setText(data.getPlat_name().get(0).getPlat_name());
        }

        TextView tv_welface = helper.getView(R.id.tv_welface);
        if (data.getWelfare().size() == 0) {
            tv_welface.setVisibility(View.GONE);
        } else {
            tv_welface.setVisibility(View.VISIBLE);
            if (data.getWelfare().size() == 1) {
                tv_welface.setText(data.getWelfare().get(0));
            } else {
                tv_welface.setText(data.getWelfare().get(0) + " | " + data.getWelfare().get(1));
            }
        }
        //底薪最高价和最低价
        TextView tv_low_money = helper.getView(R.id.tv_low_money);
        if (Double.parseDouble(data.getKeep_pay()) == 0) {
            tv_low_money.setVisibility(View.GONE);
        } else {
            if (Double.parseDouble(data.getKeep_pay()) >= 1000) {
                tv_low_money.setText("底薪" + Integer.parseInt(data.getKeep_pay()) / 1000 + "K");
            } else {
                tv_low_money.setText("底薪" + Integer.parseInt(data.getKeep_pay()));
            }
            tv_low_money.setVisibility(View.VISIBLE);
        }
        TextView tv_adress = helper.getView(R.id.tv_adress);
        tv_adress.setText(data.getWork_position());
        TextView tv_company_name = helper.getView(R.id.tv_company_name);
        tv_company_name.setText(data.getCompany());

        helper.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (UserConfig.getInstance().getToken().length() == 0) {
                    StartActivityUtils.startActivity();
                    return;
                }
//                Intent intent;
//                if (UserConfig.getInstance().getRole().equals("2")) {
//                    intent = new Intent(mContext, AnchorBossWebView.class);
//                } else {
//                    intent = new Intent(mContext, BossWebView.class);
//                }
//                Log.d("Debug", "点击的url" + data.getInfo_url());
//                intent.putExtra("url", data.getInfo_url());
//                intent.putExtra("is_colloect", data.getIs_collect());
//                intent.putExtra("id", data.getId());
//                intent.putExtra("phone", data.getContact_phone());
//                intent.putExtra("company", data.getCompany());
//                intent.putExtra("type_name", data.getType_name());
//                intent.putExtra("pay_low", data.getPay_low());
//                intent.putExtra("pay_high", data.getPay_high());
//                intent.putExtra("work_position", data.getWork_position());
//                intent.putExtra("qq", data.getQq());
//                intent.putExtra("weixin", data.getWeixin());
//                intent.putExtra("youxiang", data.getEmail());
//                mContext.startActivity(intent);

                Intent intent = new Intent(mContext, AnchorBossWebView2.class);
                intent.putExtra("need_id", data.getId() + "");
                mContext.startActivity(intent);
            }
        });


    }


    @Override
    public void convert(ViewHolder helper, BossDataBean.ListBean.DataBean data, List<Object> payloads) {
    }


    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int i) {
        return false;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
