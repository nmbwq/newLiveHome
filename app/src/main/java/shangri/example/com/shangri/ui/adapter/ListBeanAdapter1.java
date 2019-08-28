package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.response.WelfareGuildBean;
import shangri.example.com.shangri.model.bean.response.upListBean;
import shangri.example.com.shangri.ui.activity.ChangeAnchorDetailActivity;
import shangri.example.com.shangri.ui.activity.LookAnchorDetailActivity;
import shangri.example.com.shangri.ui.view.CircleImageView;
import shangri.example.com.shangri.util.AndroidInterface.CompanyInterfaceUtils;
import shangri.example.com.shangri.util.BitmapCache;
import shangri.example.com.shangri.util.TimeUtil;
import shangri.example.com.shangri.util.ViewHolder;

/**
 * Created by Administrator on 2018/1/3.
 */

public class ListBeanAdapter1 extends BaseListAdapter<WelfareGuildBean.GuildList> {
    private Context mContext;
    private Animation mLikeAnim;
    List<WelfareGuildBean.GuildList> data = new ArrayList<>();


    public ListBeanAdapter1(Context context, int layoutId, List<WelfareGuildBean.GuildList> datas) {
        super(context, layoutId, datas);
        mContext = context;
        data = datas;
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
    }

    @Override
    public void convert(ViewHolder helper, final WelfareGuildBean.GuildList data) {
        ImageView im_ischeck = helper.getView(R.id.im_ischeck);
        CircleImageView im_image = helper.getView(R.id.im_image);
        TextView tv_name = helper.getView(R.id.tv_name);
        TextView tv_platfrom = helper.getView(R.id.tv_platfrom);
        TextView tv_time = helper.getView(R.id.tv_time);
        Glide.with(mContext)
                .load(data.getAvatar_url())
                .placeholder(R.mipmap.bg_touxiang)
                .crossFade()
                .into(im_image);
//        BitmapCache.getInstance().loadBitmaps(im_image, data.getAvatar_url(), null);
        try {
            tv_time.setText(TimeUtil.longToString(Long.parseLong(data.getCreate_time()), "MM月dd日"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        im_ischeck.setVisibility(View.GONE);
        tv_name.setText(data.getNickname() + "");
        if (data.getType_name().size() > 0) {
            tv_platfrom.setText("求职意向：" + data.getType_name());
        } else {
            tv_platfrom.setText("");
        }

        helper.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, LookAnchorDetailActivity.class).putExtra("id", data.getResume_id() + ""));
            }
        });


    }

    @Override
    public void convert(ViewHolder helper, WelfareGuildBean.GuildList dataBean, List<Object> payloads) {

    }


}
