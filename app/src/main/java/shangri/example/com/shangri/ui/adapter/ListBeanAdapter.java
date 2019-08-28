package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.response.sendResumeBean;
import shangri.example.com.shangri.model.bean.response.upListBean;
import shangri.example.com.shangri.ui.activity.AnchorDropDetailActivity;
import shangri.example.com.shangri.ui.view.CircleImageView;
import shangri.example.com.shangri.util.AndroidInterface.CompanyInterfaceUtils;
import shangri.example.com.shangri.util.ViewHolder;

/**
 * Created by Administrator on 2018/1/3.
 */

public class ListBeanAdapter extends BaseListAdapter<upListBean.ListBean> {
    private Context mContext;
    private Animation mLikeAnim;
    List<upListBean.ListBean> data = new ArrayList<>();


    public ListBeanAdapter(Context context, int layoutId, List<upListBean.ListBean> datas) {
        super(context, layoutId, datas);
        mContext = context;
        data = datas;
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
    }

    @Override
    public void convert(ViewHolder helper, final upListBean.ListBean data) {
        ImageView im_ischeck = helper.getView(R.id.im_ischeck);
        CircleImageView im_image = helper.getView(R.id.im_image);
        TextView tv_name = helper.getView(R.id.tv_name);
        TextView tv_platfrom = helper.getView(R.id.tv_platfrom);
        Glide.with(mContext)
                .load(data.getAvatar_url())
                .placeholder(R.mipmap.bg_touxiang)
                .crossFade()
                .into(im_image);

        im_ischeck.setVisibility(View.GONE);
        tv_name.setText(data.getNickname() + "");
        if (data.getType_name().size() > 0) {
            tv_platfrom.setText("沟通职位：" + data.getType_name());
        } else {
            tv_platfrom.setText("");
        }

        helper.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CompanyInterfaceUtils.apllayFace.sendInfo1(data);
            }
        });

    }

    @Override
    public void convert(ViewHolder helper, upListBean.ListBean dataBean, List<Object> payloads) {

    }


}
