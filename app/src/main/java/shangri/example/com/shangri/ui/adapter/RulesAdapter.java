package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.response.liftRuleBean;
import shangri.example.com.shangri.util.ViewHolder;

/**
 * Created by Administrator on 2018/1/3.
 */

public class RulesAdapter extends BaseListAdapter<liftRuleBean.RuleBean> {
    private final String mRole;
    private final Map<Integer, Boolean> mList;
    private Context mContext;
    private Animation mLikeAnim;
    private boolean isOpen = true;

    public RulesAdapter(Context context, int layoutId, List<liftRuleBean.RuleBean> datas) {
        super(context, layoutId, datas);
        mRole = UserConfig.getInstance().getRole();
        mContext = context;
        mList = new HashMap<>();
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
    }

    @Override
    public void convert(final ViewHolder helper, final liftRuleBean.RuleBean item) {
        TextView tv1 = helper.getView(R.id.tv1);
        TextView tv2 = helper.getView(R.id.tv2);
        TextView tv3 = helper.getView(R.id.tv3);
        tv1.setText(item.getLevel_name() + "");
        tv2.setText(item.getInvite_register_num());
        tv3.setText(item.getInvite_resume_num());
    }

    //
    @Override
    public void convert(ViewHolder helper, liftRuleBean.RuleBean pageDataBean, List<Object> payloads) {

    }


}
