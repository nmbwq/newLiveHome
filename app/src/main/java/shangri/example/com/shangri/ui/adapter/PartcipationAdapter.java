package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fyales.tagcloud.library.TagBaseAdapter;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.response.MyTaskBean;
import shangri.example.com.shangri.model.bean.response.ParticipationTaskBean;
import shangri.example.com.shangri.util.DensityUtil;
import shangri.example.com.shangri.util.TimeUtil;
import shangri.example.com.shangri.util.ViewHolder;

/**
 * Created by Administrator on 2018/1/3.
 */

public class PartcipationAdapter extends BaseListAdapter<ParticipationTaskBean.AnchorsBean> {
    private final String mRole;
    private final Map<Integer, Boolean> mList;
    private Context mContext;
    private Animation mLikeAnim;
    private TagBaseAdapter mAdapter, mAdapter1;
    private boolean isOpen = true;

    public PartcipationAdapter(Context context, int layoutId, List<ParticipationTaskBean.AnchorsBean> datas) {
        super(context, layoutId, datas);
        mRole = UserConfig.getInstance().getRole();
        mContext = context;
        mList = new HashMap<>();
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
    }

    @Override
    public void convert(final ViewHolder helper, ParticipationTaskBean.AnchorsBean pageDataBean) {

        TextView tv_name_guild = helper.getView(R.id.tv_name_guild);

        TextView tv_text2 = helper.getView(R.id.tv_text2);
        TextView tv_comment2 = helper.getView(R.id.tv_comment2);


        TextView baifenbi = helper.getView(R.id.baifenbi);
        ProgressBar progress = helper.getView(R.id.progress);
        ImageView carimage = helper.getView(R.id.carimage);


        tv_name_guild.setText(pageDataBean.getAnchor_name());
        tv_text2.setText(pageDataBean.getSelf_aims());
        tv_comment2.setText(String.valueOf(pageDataBean.getTotal_aims()));

        double p = Double.valueOf(pageDataBean.getTotal_aims()) / Double.valueOf(pageDataBean.getSelf_aims());
        int p1 = (int) p;
        progress.setProgress(p1);
        baifenbi.setText(p1 + "%");
        int pro1 = (int) (180 * (Double.valueOf(p1) / 100));
        int pro = DensityUtil.dip2px(mContext, pro1);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(pro, 0, 0, 0);//4个参数按顺序分别是左上右下
        carimage.setLayoutParams(layoutParams);
    }

    @Override
    public void convert(ViewHolder helper, ParticipationTaskBean.AnchorsBean pageDataBean, List<Object> payloads) {

    }
}
