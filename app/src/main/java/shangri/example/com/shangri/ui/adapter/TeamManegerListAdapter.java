package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.base.GlobalApp;
import shangri.example.com.shangri.model.bean.response.GuildListBean;
import shangri.example.com.shangri.model.bean.response.RespAdminBean;
import shangri.example.com.shangri.ui.activity.MainActivity;
import shangri.example.com.shangri.ui.dialog.CommontDialog;
import shangri.example.com.shangri.ui.view.CircleImageView;
import shangri.example.com.shangri.util.StringUtil;
import shangri.example.com.shangri.util.ViewHolder;

/**
 * Created by Administrator on 2018/1/3. 团队管理
 */
public class TeamManegerListAdapter extends BaseListAdapter<RespAdminBean.AdminsBean> {
    private Context mContext;
    private Animation mLikeAnim;
    private String mType;
    private String mTimeType;
    private int mTimeStute;//时间状态，0，昨日，1本月


    public TeamManegerListAdapter(Context context, int layoutId, List<RespAdminBean.AdminsBean> datas, String _type) {
        super(context, layoutId, datas);
        mContext = context;
        mType = _type;
        if (mType.equals("yesterday")) {
            mTimeType = "前日:";
            mTimeStute = 0;
        } else {
            mTimeType = "上个月:";
            mTimeStute = 1;
        }
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
    }

    private static final String TAG = "TeamIncomeListAdapter";

    @Override
    public void convert(ViewHolder helper, final RespAdminBean.AdminsBean item) {
        RelativeLayout rl_click = helper.getView(R.id.rl_click);
        helper.setText(R.id.tv_name, item.getNickname() + "");//平台名称
        Log.d("Debug", "管理员的名称为" + item.getNickname());
        helper.setText(R.id.tv_income_name, "辅导次数");
        helper.setText(R.id.tv_tutor_count, item.getInspect_num() + "");//辅导次数
        helper.setText(R.id.tv_tutor_pre_count, mTimeType + item.getPre_inspect_num() + "");
        TextView tv2 = helper.getView(R.id.tv_tutor_count);
        if (mTimeStute == 1) {
            tv2.setCompoundDrawables(null, null, null, null);
        } else {
            setArrow(tv2, item.getInspect_num() + "", item.getPre_inspect_num() + "");
        }
        helper.setText(R.id.tv_host_count, item.getInspect_anchors() + "");//辅导主播数
        helper.setText(R.id.tv_host_pre_count, mTimeType + item.getPre_inspect_anchors() + "");
        TextView tv3 = helper.getView(R.id.tv_host_count);

        if (mTimeStute == 1) {
            tv3.setCompoundDrawables(null, null, null, null);
        } else {
            setArrow(tv3, item.getInspect_anchors(), item.getInspect_anchors());
        }
        showPrompt(helper);
        CircleImageView ivIcon = helper.getView(R.id.iv_icon);
        Glide.with(mContext)
                .load(item.getAvatar_url())
                .crossFade().error(R.mipmap.bg_touxiang)
                .into(ivIcon);

        rl_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalApp.Guild_id = item.getId();
                mContext.startActivity(new Intent(mContext, MainActivity.class).putExtra("type", 3));
            }
        });

    }

    @Override
    public void convert(ViewHolder helper, RespAdminBean.AdminsBean adminsBean, List<Object> payloads) {

    }

    private void showPrompt(ViewHolder helper) {
        helper.getView(R.id.iv_wenti_tutor).setOnClickListener(new View.OnClickListener() {//礼物
            @Override
            public void onClick(View v) {
                CommontDialog.showTeamManegerDialog(mContext, mTimeStute, R.id.iv_wenti_tutor);
            }
        });
        helper.getView(R.id.iv_wenti_kaibo).setOnClickListener(new View.OnClickListener() { //主播开播
            @Override
            public void onClick(View v) {
                CommontDialog.showTeamManegerDialog(mContext, mTimeStute, R.id.iv_wenti_kaibo);
            }
        });
    }

    /**
     * 设置箭头方向
     *
     * @param tv
     * @param num1
     * @param num2
     */
    public void setArrow(TextView tv, String num1, String num2) {

        int n1 = StringUtil.string2num(num1);
        int n2 = StringUtil.string2num(num2);
        Log.i(TAG, "setArrow: n1:" + n1 + "---------n2:" + n2);
        if (n1 > n2) {
            Drawable dwLeft = mContext.getResources().getDrawable(R.mipmap.shangshen);
            dwLeft.setBounds(0, 0, dwLeft.getMinimumWidth(), dwLeft.getMinimumHeight());
            tv.setCompoundDrawables(null, null, dwLeft, null);
        } else if (n1 == n2) {
            Drawable dwLeft = mContext.getResources().getDrawable(R.mipmap.shangshen);
            dwLeft.setBounds(0, 0, 0, 0);
            tv.setCompoundDrawables(null, null, null, null);
        } else if (n1 < n2) {
            Drawable dwLeft = mContext.getResources().getDrawable(R.mipmap.xiajiang);
            dwLeft.setBounds(0, 0, dwLeft.getMinimumWidth(), dwLeft.getMinimumHeight());
            tv.setCompoundDrawables(null, null, dwLeft, null);
        }
    }
}
