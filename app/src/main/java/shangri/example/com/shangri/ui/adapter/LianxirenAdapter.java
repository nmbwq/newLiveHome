package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.model.bean.response.BossDataBean;
import shangri.example.com.shangri.ui.webview.AnchorBossWebView;
import shangri.example.com.shangri.ui.webview.AnchorBossWebView2;
import shangri.example.com.shangri.ui.webview.BossWebView;
import shangri.example.com.shangri.util.StartActivityUtils;

public class LianxirenAdapter extends BaseAdapter {

    private List<BossDataBean.ListBean.DataBean> lxrs;
    private Context ctx;
    private LayoutInflater mInflater;

    public LianxirenAdapter(Context context, List<BossDataBean.ListBean.DataBean> lxrs) {
        ctx = context;
        this.lxrs = lxrs;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return lxrs.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return lxrs.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final BossDataBean.ListBean.DataBean data = lxrs.get(position);
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_boss, null);
            viewHolder = new ViewHolder();
            viewHolder.im_hot = convertView.findViewById(R.id.im_hot);
            viewHolder.im_is_vip = convertView.findViewById(R.id.im_is_vip);
            viewHolder.im_is_guanfang = convertView.findViewById(R.id.im_is_guanfang);
            viewHolder.im_is_phone = convertView.findViewById(R.id.im_is_phone);
            viewHolder.tv_anchor_name = convertView.findViewById(R.id.tv_anchor_name);
            viewHolder.tv_xianxia = convertView.findViewById(R.id.tv_xianxia);
            viewHolder.tv_week = convertView.findViewById(R.id.tv_week);
            viewHolder.tv_money = convertView.findViewById(R.id.tv_money);
            viewHolder.tv_plat_name = convertView.findViewById(R.id.tv_plat_name);
            viewHolder.tv_welface = convertView.findViewById(R.id.tv_welface);
            viewHolder.tv_low_money = convertView.findViewById(R.id.tv_low_money);
            viewHolder.tv_adress = convertView.findViewById(R.id.tv_adress);
            viewHolder.tv_company_name = convertView.findViewById(R.id.tv_company_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Debug", "点击的位置是" + position);

                if (UserConfig.getInstance().getToken().length() == 0) {
                    StartActivityUtils.startActivity();
                    return;
                }
                Intent intent = new Intent(ctx, AnchorBossWebView2.class);
                intent.putExtra("need_id", data.getId() + "");
                ctx.startActivity(intent);
            }
        });
//        是否广告位 1是 0否
        if (data.getIs_ggw() == 1 || data.getHot().equals("1")) {
            viewHolder.im_hot.setVisibility(View.VISIBLE);
        } else {
            viewHolder.im_hot.setVisibility(View.GONE);
        }
        //是不是vip
        if (data.getIs_vip() == 1) {
            viewHolder.im_is_vip.setVisibility(View.VISIBLE);
        } else {
            viewHolder.im_is_vip.setVisibility(View.GONE);
        }


//        发布类型 1系统（爬取的）3 官方（手动发布）
        if (data.getPublish_type() == 3) {
            viewHolder.im_is_guanfang.setVisibility(View.VISIBLE);
        } else {
            viewHolder.im_is_guanfang.setVisibility(View.GONE);
        }

//       是否沟通 1是 0否
        if (data.getIs_collect().equals("0")) {
            viewHolder.im_is_phone.setVisibility(View.GONE);
        } else {
            viewHolder.im_is_phone.setVisibility(View.VISIBLE);
        }
        if (data.getTitle().length() > 0) {
            viewHolder.tv_anchor_name.setText(data.getTitle() + "");
        } else {
            viewHolder.tv_anchor_name.setText(data.getType_name() + "");
        }
        if (data.getJob_method() != null) {
            viewHolder.tv_xianxia.setVisibility(View.VISIBLE);
            switch (data.getJob_method()) {
                case "1":
                    viewHolder.tv_xianxia.setText("线上");
                    break;
                case "2":
                    viewHolder.tv_xianxia.setText("线下");
                    break;
                case "3":
                    viewHolder.tv_xianxia.setText("线上/线下");
                    break;
            }
        } else {
            viewHolder.tv_xianxia.setVisibility(View.GONE);
        }
        if (viewHolder.tv_xianxia.getText().length() == 0) {
            viewHolder.tv_xianxia.setVisibility(View.GONE);
        }

        if (data.getSalary_type() != null) {
            viewHolder.tv_week.setVisibility(View.VISIBLE);
            switch (data.getSalary_type()) {
                case "1":
                    viewHolder.tv_week.setText("月结");
                    break;
                case "2":
                    viewHolder.tv_week.setText("周结");
                    break;
                case "3":
                    viewHolder.tv_week.setText("日结");
                    break;
                case "4":
                    viewHolder.tv_week.setText("月结/周结/日结");
                    break;
            }
        } else {
            viewHolder.tv_week.setVisibility(View.GONE);
        }
        if (viewHolder.tv_week.getText().length() == 0) {
            viewHolder.tv_week.setVisibility(View.GONE);
        }
        viewHolder.tv_money.setText(Integer.parseInt(data.getPay_low()) / 1000 + "K" + " - " + Integer.parseInt(data.getPay_high()) / 1000 + "K");
        if (data.getPlat_name().size() == 0) {
            viewHolder.tv_plat_name.setVisibility(View.GONE);
        } else {
            viewHolder.tv_plat_name.setVisibility(View.VISIBLE);
            viewHolder.tv_plat_name.setText(data.getPlat_name().get(0).getPlat_name());
        }

        if (data.getWelfare().size() == 0) {
            viewHolder.tv_welface.setVisibility(View.GONE);
        } else {
            viewHolder.tv_welface.setVisibility(View.VISIBLE);
            if (data.getWelfare().size() == 1) {
                viewHolder.tv_welface.setText(data.getWelfare().get(0));
            } else {
                viewHolder.tv_welface.setText(data.getWelfare().get(0) + " | " + data.getWelfare().get(1));
            }
        }
        //底薪最高价和最低价
        if (Double.parseDouble(data.getKeep_pay()) == 0) {
            viewHolder.tv_low_money.setVisibility(View.GONE);
        } else {
            if (Double.parseDouble(data.getKeep_pay()) >= 1000) {
                viewHolder.tv_low_money.setText("底薪" + Integer.parseInt(data.getKeep_pay()) / 1000 + "K");
            } else {
                viewHolder.tv_low_money.setText("底薪" + Integer.parseInt(data.getKeep_pay()));
            }
            viewHolder.tv_low_money.setVisibility(View.VISIBLE);
        }
        if (data.getWork_position().length() > 0) {
            viewHolder.tv_adress.setVisibility(View.VISIBLE);
            viewHolder.tv_adress.setText(data.getWork_position());
        } else {
            viewHolder.tv_adress.setVisibility(View.GONE);
        }

        viewHolder.tv_company_name.setText(data.getCompany());

//        viewHolder.name.setText(lxr.getName());
        return convertView;
    }

    public void addAll(List<BossDataBean.ListBean.DataBean> elements) {
        lxrs.addAll(elements);
        notifyDataSetChanged();
    }

    public void addAllAt(int location, List<BossDataBean.ListBean.DataBean> elements) {
        lxrs.addAll(location, elements);
        notifyDataSetChanged();
    }

    public List<BossDataBean.ListBean.DataBean> getAll() {
        return lxrs;
    }

    public void setData(List<BossDataBean.ListBean.DataBean> elements) {
        lxrs = elements;
        notifyDataSetChanged();

    }

    static class ViewHolder {
        public ImageView im_hot;
        public ImageView im_is_vip;
        public ImageView im_is_guanfang;
        public ImageView im_is_phone;
        public TextView tv_xianxia;
        public TextView tv_anchor_name;
        public TextView tv_week;
        public TextView tv_money;
        public TextView tv_plat_name;
        public TextView tv_welface;
        public TextView tv_low_money;
        public TextView tv_adress;
        public TextView tv_company_name;
    }
}
