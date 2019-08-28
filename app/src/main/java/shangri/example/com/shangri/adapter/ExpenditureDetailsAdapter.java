package shangri.example.com.shangri.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.model.bean.response.DetailsBean;

public class ExpenditureDetailsAdapter extends BaseQuickAdapter<DetailsBean.BillsBean, BaseViewHolder> {
    private Context context;

    public ExpenditureDetailsAdapter(Context context, int layoutResId, @Nullable List<DetailsBean.BillsBean> data) {
        super(layoutResId, data);

        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, DetailsBean.BillsBean item) {
        TextView tv_cause = helper.getView(R.id.tv_cause);

        String status = item.getStatus();

        ImageView im_is_expire = helper.getView(R.id.im_is_expire);
//        是否过期 1是 0否
        if (item.getIs_expire() == 1) {
            im_is_expire.setVisibility(View.VISIBLE);
        } else {
            im_is_expire.setVisibility(View.GONE);
        }

        if ("3".equals(status)) {
            tv_cause.setVisibility(View.VISIBLE);
        } else {
            tv_cause.setVisibility(View.GONE);
        }
        switch (status) {
            case "0":
                String type = item.getType();
                if ("1".equals(type)) {
                    type = "+ ";
                } else if ("2".equals(type)) {
                    type = "- ";
                }
                if (!TextUtils.isEmpty(item.getNickname())) {
                    helper.setText(R.id.tv_title, item.getTitle() + " - " + item.getNickname());
                } else {
                    if (!TextUtils.isEmpty(item.getCompany())) {
                        helper.setText(R.id.tv_title, item.getTitle() + " - " + item.getCompany());
                    } else {
                        helper.setText(R.id.tv_title, item.getTitle());
                    }
                }
                helper.setTextColor(R.id.tv_status, context.getResources().getColor(R.color.white));
                helper.setText(R.id.tv_status, type + item.getOperate_num());
                helper.setText(R.id.tv_time, stampToDate(item.getCreate_time()));
                break;

            case "1":
                helper.setText(R.id.tv_title, item.getTitle() + " " + "  - ¥ " + item.getOperate_num());
                helper.setTextColor(R.id.tv_status, context.getResources().getColor(R.color.text_color_pass));
                helper.setText(R.id.tv_status, "提现中");
                helper.setText(R.id.tv_time, stampToDate(item.getCreate_time()));
                break;

            case "2":
                helper.setText(R.id.tv_title, item.getTitle() + " " + "  - ¥ " + item.getOperate_num());
                helper.setTextColor(R.id.tv_status, context.getResources().getColor(R.color.text_color_success));
                helper.setText(R.id.tv_status, "提现成功");
                helper.setText(R.id.tv_time, stampToDate(item.getCreate_time()));
                break;

            case "3":
                helper.setText(R.id.tv_title, item.getTitle() + " " + "  - ¥ " + item.getOperate_num());
                helper.setTextColor(R.id.tv_status, context.getResources().getColor(R.color.text_color_error));
                helper.setText(R.id.tv_status, "提现失败");
                helper.setText(R.id.tv_time, stampToDate(item.getCreate_time()));
                if (!TextUtils.isEmpty(item.getStatus_des())) {
                    helper.setText(R.id.tv_cause, "失败原因：" + item.getStatus_des());
                }

                break;
        }


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
