package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.fyales.tagcloud.library.TagBaseAdapter;
import com.fyales.tagcloud.library.TagCloudLayout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.response.MyPlatfromList;
import shangri.example.com.shangri.model.bean.response.PatrolDataBean;
import shangri.example.com.shangri.ui.view.RoundImageView;
import shangri.example.com.shangri.util.DensityUtil;
import shangri.example.com.shangri.util.ViewHolder;

/**
 * Created by Administrator on 2018/1/3.
 */

public class MyPlatfromAdapter extends BaseListAdapter<MyPlatfromList.PlatfromsBean> {
    private final String mRole;
    private final Map<Integer, Boolean> mList;
    private Context mContext;
    private Animation mLikeAnim;
    private TagBaseAdapter mAdapter, mAdapter1;
    private boolean isOpen = true;

    public MyPlatfromAdapter(Context context, int layoutId, List<MyPlatfromList.PlatfromsBean> datas) {
        super(context, layoutId, datas);
        mRole = UserConfig.getInstance().getRole();
        mContext = context;
        mList = new HashMap<>();
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
    }

    @Override
    public void convert(final ViewHolder helper, MyPlatfromList.PlatfromsBean pageDataBean) {
        final ImageView iv_consulation_item = helper.getView(R.id.iv_consulation_item);
        TextView tv_consultaton_titel = helper.getView(R.id.tv_consultaton_titel);
        TextView tv_increase = helper.getView(R.id.tv_increase);
        TextView tv_id = helper.getView(R.id.tv_id);

        tv_consultaton_titel.setText(pageDataBean.getGuild_name());
        tv_increase.setText(pageDataBean.getPlatfrom_name());

        if (UserConfig.getInstance().getRole().equals("1")) {
            tv_id.setText("主播人数 " + pageDataBean.getAnchor_content());
        } else {
            tv_id.setText("ID " + pageDataBean.getAnchor_content());
        }

        Glide.with(mContext)
                .load(pageDataBean.getCover_url())
                .placeholder(R.mipmap.ic_moren)
                .crossFade()
                .into(iv_consulation_item);
    }


    @Override
    public void convert(ViewHolder helper, MyPlatfromList.PlatfromsBean pageDataBean, List<Object> payloads) {

    }
}
