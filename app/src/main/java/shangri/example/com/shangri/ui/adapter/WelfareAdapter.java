package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
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
import shangri.example.com.shangri.model.bean.response.liftRuleBean;
import shangri.example.com.shangri.ui.view.CircleImageView;
import shangri.example.com.shangri.util.TimeUtil;
import shangri.example.com.shangri.util.ViewHolder;

/**
 * Created by Administrator on 2018/1/3.
 */

public class WelfareAdapter extends BaseListAdapter<liftRuleBean.WelfareBean> {
    private final String mRole;
    private final Map<Integer, Boolean> mList;
    private Context mContext;
    private Animation mLikeAnim;
    private boolean isFrominvite1;

    public WelfareAdapter(Context context, int layoutId, List<liftRuleBean.WelfareBean> datas,Boolean  isFrominvite) {
        super(context, layoutId, datas);
        mRole = UserConfig.getInstance().getRole();
        mContext = context;
        isFrominvite1=isFrominvite;
        mList = new HashMap<>();
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
    }

    @Override
    public void convert(final ViewHolder helper, final liftRuleBean.WelfareBean item) {
        TextView tv1 = helper.getView(R.id.tv1);
        TextView tv2 = helper.getView(R.id.tv2);
        TextView tv3 = helper.getView(R.id.tv3);
        TextView tv4 = helper.getView(R.id.tv4);
        //来自邀请规则界面 tv4隐藏
        if (isFrominvite1){
            tv4.setVisibility(View.GONE);
        }
        tv1.setText(item.getLevel_name()+"");
        tv2.setText(item.getInvite_register()+"");
        tv3.setText(item.getInvite_resume());
        tv4.setText(item.getResume_viewed()+"");
    }

    //
    @Override
    public void convert(ViewHolder helper, liftRuleBean.WelfareBean pageDataBean, List<Object> payloads) {

    }


}
