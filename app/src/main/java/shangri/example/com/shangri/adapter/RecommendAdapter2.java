package shangri.example.com.shangri.adapter;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.response.WelfareGuildBean;
import shangri.example.com.shangri.model.bean.response.anchorDetailBean;
import shangri.example.com.shangri.util.DateUtil;
import shangri.example.com.shangri.util.TimeUtil;
import shangri.example.com.shangri.util.ViewHolder;

/**
 * Description:小播推荐
 * Data：2018/11/14-17:58
 * Author: lin
 */
public class RecommendAdapter2 extends BaseListAdapter<anchorDetailBean.UserInfoBean> {
    private Context mContext;
    private Animation mLikeAnim;
    private onItemClick onItemClick;

    public RecommendAdapter2(Context context, int layoutId, List<anchorDetailBean.UserInfoBean> datas, onItemClick onItemClick) {
        super(context, layoutId, datas);
        this.mContext = context;
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
        this.onItemClick = onItemClick;
    }

    @Override
    public void convert(ViewHolder helper, anchorDetailBean.UserInfoBean resumes) {
        final int position = helper.getAdapterPosition();
        if (resumes != null) {
            helper.setText(R.id.tv_name, resumes.getNickname() + "");
//            try {
//                helper.setText(R.id.time, TimeUtil.longToString(Long.parseLong(resumes.getCreate_time()), "MM-dd") + "");
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
        }
        ImageView imageView = helper.getView(R.id.image);
        ImageView iv_heart = helper.getView(R.id.iv_heart);
        iv_heart.setVisibility(View.GONE);
        if (resumes.getImg_url() != null) {
            Glide.with(mContext)
                    .load(resumes.getImg_url())
                    .crossFade()
                    .placeholder(R.mipmap.gsjs_mrtb)
                    .into(imageView);
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onItemClick(position);
            }
        });
    }

    @Override
    public void convert(ViewHolder helper, anchorDetailBean.UserInfoBean resumes, List<Object> payloads) {

    }

    public interface onItemClick {
        void onItemClick(int position);
    }
}
