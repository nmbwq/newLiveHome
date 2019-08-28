package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.response.InviteListDataBean;
import shangri.example.com.shangri.model.bean.response.PatrolDataBean;
import shangri.example.com.shangri.ui.view.CircleImageView;
import shangri.example.com.shangri.util.CallBackUtils;
import shangri.example.com.shangri.util.TimeUtil;
import shangri.example.com.shangri.util.ViewHolder;

/**
 * Created by Administrator on 2018/1/3.
 */

public class InviteListAdapter extends BaseListAdapter<InviteListDataBean.BillsBean> {
    private final String mRole;
    private final Map<Integer, Boolean> mList;
    private Context mContext;
    private Animation mLikeAnim;
    private boolean isOpen = true;

    public InviteListAdapter(Context context, int layoutId, List<InviteListDataBean.BillsBean> datas) {
        super(context, layoutId, datas);
        mRole = UserConfig.getInstance().getRole();
        mContext = context;
        mList = new HashMap<>();
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
    }

    @Override
    public void convert(final ViewHolder helper, final InviteListDataBean.BillsBean item) {
        CircleImageView im_photo = helper.getView(R.id.im_photo);
        ImageView im_type = helper.getView(R.id.im_type);
        TextView tv_name = helper.getView(R.id.tv_name);
        TextView tv_phone = helper.getView(R.id.tv_phone);
        TextView tv_number = helper.getView(R.id.tv_number);
        TextView tv_time = helper.getView(R.id.tv_time);
        Glide.with(mContext)
                .load(item.getAvatar_url())
                .placeholder(R.mipmap.bg_touxiang)
                .crossFade()
                .into(im_photo);
//        状态 0-无简历 1-有简历
        if (item.getStatus() == 1) {
            im_type.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.yhfl_ysc));
            tv_name.setTextColor(mContext.getResources().getColor(R.color.text_color_8a4801));
            tv_phone.setTextColor(mContext.getResources().getColor(R.color.text_color_8a4801));
            tv_number.setTextColor(mContext.getResources().getColor(R.color.text_color_8a4801));
            tv_time.setTextColor(mContext.getResources().getColor(R.color.text_color_8a4801));
        } else {
            im_type.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.yhfl_wsc));
            tv_name.setTextColor(mContext.getResources().getColor(R.color.white));
            tv_phone.setTextColor(mContext.getResources().getColor(R.color.white));
            tv_number.setTextColor(mContext.getResources().getColor(R.color.white));
            tv_time.setTextColor(mContext.getResources().getColor(R.color.white));
        }
        tv_name.setText(item.getNickname() + "");
        tv_phone.setText(item.getTelephone() + "");
        tv_number.setText("+" + item.getOperate_num() + "");
        String s = null;
        try {
            s = TimeUtil.longToString(Long.parseLong(item.getCreate_time() + ""), "yyyy-MM-dd HH:mm");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tv_time.setText(s + "");
    }

    //
    @Override
    public void convert(ViewHolder helper, InviteListDataBean.BillsBean pageDataBean, List<Object> payloads) {

    }


}
