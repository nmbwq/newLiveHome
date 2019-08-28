package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.base.GlobalApp;
import shangri.example.com.shangri.model.bean.response.GuildListBean;
import shangri.example.com.shangri.ui.activity.MyFreagmentActivity;
import shangri.example.com.shangri.ui.dialog.CommontDialog;
import shangri.example.com.shangri.ui.view.CircleImageView;
import shangri.example.com.shangri.util.StringUtil;
import shangri.example.com.shangri.util.ViewHolder;

/**
 * Created by Administrator on 2018/1/3.
 */
public class TeamIncomeListAdapter extends BaseListAdapter<GuildListBean.GuildsBean> {
    private Context mContext;
    private Animation mLikeAnim;
    private String mType;
    private String mTimeType;
    private int mTimeStute;//时间状态，0，昨日，1本月
    int mRole;//1公会长 2主播 3管理员
    private onClickShareListener shareListener;


    public TeamIncomeListAdapter(Context context, int layoutId, List<GuildListBean.GuildsBean> datas, String _type) {
        super(context, layoutId, datas);
        mContext = context;
        mType = _type;
        if (mType.equals("yesterday")) {
            mTimeType = "相较前一日:";
            mTimeStute = 0;
        } else {
            mTimeType = "相较前一月:";
            mTimeStute = 1;
        }
        mRole = Integer.parseInt(UserConfig.getInstance().getRole());
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
    }

    private static final String TAG = "TeamIncomeListAdapter";

    @Override
    public void convert(ViewHolder helper, final GuildListBean.GuildsBean item) {
        GuildListBean.GuildsBean.DataBean data = item.getData();
        //快速公会为1  非快速公会为0
        int type = item.getType();
        Log.i(TAG, "convert: ,data.getName():" + data.getName() + "");

        CircleImageView iv_icon = helper.getView(R.id.iv_icon);
        TextView tv_tutor_count = helper.getView(R.id.tv_tutor_count);
        TextView tv_host_count = helper.getView(R.id.tv_host_count);
        RelativeLayout rl_click = helper.getView(R.id.rl_click);

        //礼物类别名称（一直播和其他的直播取的参数不一样）
        TextView tv_up_type = helper.getView(R.id.tv_up_type);
        TextView tv_up_type_yi = helper.getView(R.id.tv_up_type_yi);

        tv_up_type_yi.setText("(" + data.getName2() + ")");
        tv_up_type.setText("(" + data.getName() + ")");

        //开播时长还是开播人数
        TextView tv_down_type = helper.getView(R.id.tv_down_type);

        helper.setText(R.id.tv_yi_income_name, "礼物收益");
        helper.setText(R.id.tv_income_name, "礼物收益");//收益名称
        helper.setText(R.id.tv_name, item.getGuild_name() + "");//平台名称

//        if (type == 1) {
//            helper.setText(R.id.tv_tutor_count, data.getPre_current().getGift() + "");//收益数量
//            helper.setText(R.id.tv_tutor_pre_count, mTimeType + data.getPre_current().getQian_gift() + " ");
//        } else {
        helper.setText(R.id.tv_tutor_count, data.getCurrent().getGift() + "");//收益数量
        helper.setText(R.id.tv_tutor_pre_count, mTimeType + data.getPre_current().getQian_gift() + "");
//        }
        TextView tv2 = helper.getView(R.id.tv_tutor_pre_count);//收益数量
        if (mTimeStute == 1) {
            tv2.setCompoundDrawables(null, null, null, null);
        } else {
            if (type == 1) {
                setArrow(tv2, data.getPre_current().getQian_gift());//设置箭头方向
            } else {
                setArrow(tv2, data.getCurrent().getQian_gift());//设置箭头方向
            }
        }

        iv_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalApp.Guild_id = item.getGuild_id();
                mContext.startActivity(new Intent(mContext, MyFreagmentActivity.class));
            }
        });
        rl_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalApp.Guild_id = item.getGuild_id();
                mContext.startActivity(new Intent(mContext, MyFreagmentActivity.class));
            }
        });
        tv_tutor_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalApp.Guild_id = item.getGuild_id();
                mContext.startActivity(new Intent(mContext, MyFreagmentActivity.class));
            }
        });
        tv_host_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalApp.Guild_id = item.getGuild_id();
                mContext.startActivity(new Intent(mContext, MyFreagmentActivity.class));

            }
        });
        TextView tvBottomName = helper.getView(R.id.tv_bottom_name);//
        if (mRole == 1) {//会长
//            if (type == 1) {
//                helper.setText(R.id.tv_host_count, data.getPre_current().getAnchor_count() + "");//开播主播
//                helper.setText(R.id.tv_host_pre_count, mTimeType + data.getPre_current().getQian_anchor_count() + " ");
//            } else {
            helper.setText(R.id.tv_host_count, data.getCurrent().getAnchor_count() + "");//开播主播
            helper.setText(R.id.tv_host_pre_count, mTimeType + data.getPre_current().getQian_anchor_count() + " ");
//            }
            TextView tv3 = helper.getView(R.id.tv_host_pre_count);//开播主播
            if (mTimeStute == 1) {
                tv3.setCompoundDrawables(null, null, null, null);
            } else {
                if (type == 1) {
                    setArrow(tv3, data.getPre_current().getQian_anchor_count());
                } else {
                    setArrow(tv3, data.getCurrent().getQian_anchor_count());
                }
            }
            tv_down_type.setText("（人）");
            tvBottomName.setText("开播主播");
        } else if (mRole == 2) {//主播
//            if (type == 1) {
//                helper.setText(R.id.tv_host_count, data.getPre_current().getLive_time() + "");//开播主播
//                helper.setText(R.id.tv_host_pre_count, mTimeType + data.getPre_current().getQian_live_time() + " ");
//            } else {
            helper.setText(R.id.tv_host_count, data.getCurrent().getLive_time() + "");//开播主播
            helper.setText(R.id.tv_host_pre_count, mTimeType + data.getPre_current().getQian_live_time() + " ");
//            }
            TextView tv3 = helper.getView(R.id.tv_host_pre_count);//开播主播
            tvBottomName.setText("开播时长(h)");
            tv_down_type.setText("（时长）");
            if (mTimeStute == 1) {
                tv3.setCompoundDrawables(null, null, null, null);
            } else {
                if (type == 1) {
                    setArrow(tv3, data.getPre_current().getQian_live_time());
                } else {
                    setArrow(tv3, data.getCurrent().getQian_live_time());
                }
            }

        } else if (mRole == 3) {
//            if (type == 1) {
//                helper.setText(R.id.tv_host_count, data.getPre_current().getAnchor_count() + "");//开播主播
//                helper.setText(R.id.tv_host_pre_count, mTimeType + data.getPre_current().getQian_anchor_count() + " ");
//            } else {
                helper.setText(R.id.tv_host_count, data.getCurrent().getAnchor_count() + "");//开播主播
                helper.setText(R.id.tv_host_pre_count, mTimeType + data.getPre_current().getQian_anchor_count() + " ");
//            }

            TextView tv4 = helper.getView(R.id.tv_host_count);//收益数量
            tv_down_type.setText("（人）");
            if (mTimeStute == 1) {
                tv4.setCompoundDrawables(null, null, null, null);
            } else {
                if (type == 1) {
                    setArrow(tv4, data.getPre_current().getQian_anchor_count());
                } else {
                    setArrow(tv4, data.getCurrent().getQian_anchor_count());
                }
            }
        }
        if (data.getCurrent().getDiamond() == null) {//只有YI直播有
            helper.getView(R.id.ll_yi).setVisibility(View.INVISIBLE);
            helper.getView(R.id.ll_yi).setVisibility(View.GONE);
        } else {
            helper.getView(R.id.ll_yi).setVisibility(View.VISIBLE);
            TextView tv1 = helper.getView(R.id.tv_yi_income_pre);//钻石数量
            if (mTimeStute == 1) {
                tv1.setCompoundDrawables(null, null, null, null);
            } else {
                if (type == 1) {
                    setArrow(tv1, data.getPre_current().getQian_diamond());
                } else {
                    setArrow(tv1, data.getCurrent().getQian_diamond());
                }
            }
//            if (type == 1) {
//                helper.setText(R.id.tv_yi_income, data.getPre_current().getDiamond() + "");//yi直播，钻石数量
//                helper.setText(R.id.tv_yi_income_pre, mTimeType + data.getPre_current().getQian_diamond() + " ");
//            } else {
                helper.setText(R.id.tv_yi_income, data.getCurrent().getDiamond() + "");//yi直播，钻石数量
                helper.setText(R.id.tv_yi_income_pre, mTimeType + data.getPre_current().getQian_diamond() + " ");
//            }
        }
        ImageView iv_share = helper.getView(R.id.iv_share);
        if (mTimeStute == 0) {
            iv_share.setVisibility(View.GONE);
        } else {
            iv_share.setVisibility(View.VISIBLE);
        }
        //全网数据分享操作没有
        if (item.getType() == 1) {
            iv_share.setVisibility(View.GONE);
        } else {
            iv_share.setVisibility(View.VISIBLE);
            iv_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    shareListener.onShare(item.getGuild_id());
                }
            });
        }

        showPrompt(helper);
        CircleImageView ivIcon = helper.getView(R.id.iv_icon);
        Glide.with(mContext)
                .load(item.getIcon_url())
                .crossFade().error(R.mipmap.bg_touxiang)
                .into(ivIcon);
    }

    private String mTitle = "";
    private String mContent = "";

    /**
     * 点击问号，提示,需要判断三种身份，两种状态（昨天和上月）
     *
     * @param helper
     */
    private void showPrompt(ViewHolder helper) {
        helper.getView(R.id.iv_wenti_tutor).setOnClickListener(new View.OnClickListener() {//礼物
            @Override
            public void onClick(View v) {
                if (mTimeStute == 0) {//昨日
                    if (mRole == 1) {
                        mTitle = "公会在该直播平台，昨日一天的礼物总收益";
                    } else if (mRole == 2) {
                        mTitle = "您在该直播平台，昨日一天的礼物总收益";
                    } else if (mRole == 3) {
                        mTitle = "该管理员所管理的主播，在该直播平台，昨日一天的礼物总收益";
                    }
                    mContent = "";
                } else {//本月
                    if (mRole == 1) {
                        mTitle = "公会在该直播平台，本月的礼物总收益";
                    } else if (mRole == 2) {
                        mTitle = "您在该直播平台，本月的礼物总收益";
                    } else if (mRole == 3) {
                        mTitle = "该管理员所管理的主播，在该直播平台，本月的礼物总收益";
                    }
                    mContent = " 注意：由于本月不是一个整月，所以这里的数据会根据开播情况而变化";
                }
                CommontDialog.showHuiBaoDialog(mContext, mTitle, mContent);

            }
        });

        helper.getView(R.id.iv_yi_income).setOnClickListener(new View.OnClickListener() { //YI直播独有
            @Override
            public void onClick(View v) {
                if (mTimeStute == 0) {//昨日
                    if (mRole == 1) {
                        mTitle = "公会在该直播平台，昨日一天的礼物总流水";
                    } else if (mRole == 2) {
                        mTitle = "您在该直播平台，昨日一天的礼物总流水";
                    } else if (mRole == 3) {
                        mTitle = "该管理员所管理的主播，昨日一天的礼物总流水";
                    }
                    mContent = "";
                } else {//本月
                    if (mRole == 1) {
                        mTitle = "公会在该直播平台，本月的礼物总流水";
                    } else if (mRole == 2) {
                        mTitle = "您在该直播平台，本月的礼物总流水";
                    } else if (mRole == 3) {
                        mTitle = "该管理员所管理的主播，本月的礼物总流水";
                    }
                    mContent = " 注意：由于本月不是一个整月，所以这里的数据会根据开播情况而变化";
                }
                CommontDialog.showHuiBaoDialog(mContext, mTitle, mContent);

            }
        });

        helper.getView(R.id.iv_wenti_kaibo).
                setOnClickListener(new View.OnClickListener() { //主播开播
                    @Override
                    public void onClick(View v) {
                        if (mTimeStute == 0) {//昨日
                            if (mRole == 1) {
                                mTitle = "公会在该直播平台，昨日一天的开播主播总人数";
                            } else if (mRole == 2) {
                                mTitle = "您在该直播平台，昨日一天的开播总时长";
                            } else if (mRole == 3) {
                                mTitle = "该管理员所管理的主播，昨天一天的开播主播总人数";
                            }
                            mContent = "";
                        } else {//本月
                            if (mRole == 1) {
                                mTitle = "公会在该直播平台，本月的开播主播总人数";
                            } else if (mRole == 2) {
                                mTitle = "您在该直播平台，本月的开播总时长";
                            } else if (mRole == 3) {
                                mTitle = "该管理员所管理的主播，本月的开播主播总人数";
                            }
                            mContent = " 注意：由于本月不是一个整月，所以这里的数据会根据开播情况而变化";
                        }
                        CommontDialog.showHuiBaoDialog(mContext, mTitle, mContent);

                    }
                });

    }

    @Override
    public void convert(ViewHolder helper, GuildListBean.GuildsBean
            anchorBean, List<Object> payloads) {
    }

    /**
     * 设置箭头方向
     *
     * @param tv
     * @param
     * @param
     */
    public void setArrow(TextView tv, String stringNumber) {
        if (stringNumber.length() > 0) {
            StringBuffer number = new StringBuffer("");
            String[] split = stringNumber.split(",");
            for (int i = 0; i < split.length; i++) {
                number.append(split[i]);
            }
            Log.d("Debug", "数值为" + number.toString());
            double i = Double.parseDouble(number.toString());
            if (i > 0) {
                Drawable dwLeft = mContext.getResources().getDrawable(R.mipmap.shangshen);
                dwLeft.setBounds(0, 0, dwLeft.getMinimumWidth(), dwLeft.getMinimumHeight());
                tv.setCompoundDrawables(null, null, dwLeft, null);
            } else if (i == 0) {
                Drawable dwLeft = mContext.getResources().getDrawable(R.mipmap.shangshen);
                dwLeft.setBounds(0, 0, 0, 0);
                tv.setCompoundDrawables(null, null, null, null);
            } else if (0 > i) {
                Drawable dwLeft = mContext.getResources().getDrawable(R.mipmap.xiajiang);
                dwLeft.setBounds(0, 0, dwLeft.getMinimumWidth(), dwLeft.getMinimumHeight());
                tv.setCompoundDrawables(null, null, dwLeft, null);
            }
        } else {
            tv.setCompoundDrawables(null, null, null, null);
        }
    }

    public void setOnShareListener(onClickShareListener onShareListener) {
        this.shareListener = onShareListener;
    }

    public interface onClickShareListener {
        void onShare(String guild_id);
    }
}
