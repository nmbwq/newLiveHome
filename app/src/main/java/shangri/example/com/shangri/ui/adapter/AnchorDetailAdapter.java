package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.content.Intent;
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
import shangri.example.com.shangri.model.bean.response.anchorDetailBean;
import shangri.example.com.shangri.model.bean.response.sendResumeBean;
import shangri.example.com.shangri.ui.activity.AddInviteActivity;
import shangri.example.com.shangri.ui.activity.AnchorDropDetailActivity;
import shangri.example.com.shangri.ui.activity.ChangeAnchorDetailActivity;
import shangri.example.com.shangri.ui.activity.LookAnchorDetailActivity;
import shangri.example.com.shangri.ui.view.CircleImageView;
import shangri.example.com.shangri.util.AndroidInterface.CompanyInterfaceUtils;
import shangri.example.com.shangri.util.CornersTransform;
import shangri.example.com.shangri.util.ViewHolder;

/**
 * Created by Administrator on 2018/1/3.
 */

public class AnchorDetailAdapter extends BaseListAdapter<anchorDetailBean.UserInfoBean> {
    private Context mContext;
    private Animation mLikeAnim;
    List<anchorDetailBean.UserInfoBean> data = new ArrayList<>();


    public AnchorDetailAdapter(Context context, int layoutId, List<anchorDetailBean.UserInfoBean> datas) {
        super(context, layoutId, datas);
        mContext = context;
        data = datas;
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
    }

    @Override
    public void convert(ViewHolder helper, final anchorDetailBean.UserInfoBean data) {
        ImageView im_ischeck = helper.getView(R.id.im_ischeck);
        CircleImageView im_image = helper.getView(R.id.im_image);
        TextView tv_name = helper.getView(R.id.tv_name);
        TextView tv_platfrom = helper.getView(R.id.tv_platfrom);
        Glide.with(mContext)
                .load(data.getAvatar_url())
                .placeholder(R.mipmap.bg_touxiang)
                .crossFade()
                .into(im_image);
        if (data.getIs_read().equals("1")) {
            im_ischeck.setVisibility(View.GONE);
        } else {
            im_ischeck.setVisibility(View.VISIBLE);
        }
        tv_name.setText(data.getNickname() + "");
        tv_platfrom.setText("想和你沟通这个职位，马上去撩一下~");
        helper.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CompanyInterfaceUtils.apllayFace.sendInfo(data);
                mContext.startActivity(new Intent(mContext, LookAnchorDetailActivity.class).putExtra("id", data.getResume_id() + ""));

            }
        });

    }

    @Override
    public void convert(ViewHolder helper, anchorDetailBean.UserInfoBean dataBean, List<Object> payloads) {

    }


}
