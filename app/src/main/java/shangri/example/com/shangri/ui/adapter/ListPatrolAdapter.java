package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.response.PatrolDataBean;
import shangri.example.com.shangri.ui.view.CircleImageView;
import shangri.example.com.shangri.util.CallBackUtils;
import shangri.example.com.shangri.util.ViewHolder;

/**
 * Created by Administrator on 2018/1/3.
 */

public class ListPatrolAdapter extends BaseListAdapter<PatrolDataBean.InspectsBean> {
    private final String mRole;
    private final Map<Integer, Boolean> mList;
    private Context mContext;
    private Animation mLikeAnim;
    private boolean isOpen = true;

    public ListPatrolAdapter(Context context, int layoutId, List<PatrolDataBean.InspectsBean> datas) {
        super(context, layoutId, datas);
        mRole = UserConfig.getInstance().getRole();
        mContext = context;
        mList = new HashMap<>();
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
    }

    @Override
    public void convert(final ViewHolder helper, final PatrolDataBean.InspectsBean item) {
        CircleImageView im_photo = helper.getView(R.id.im_photo);
        Glide.with(mContext)
                .load(item.getAvatar_url())
                .placeholder(R.mipmap.bg_touxiang)
                .crossFade()
                .into(im_photo);
        //主播名称
        helper.setText(R.id.tv_name, item.getAnchor_name() + "");
        //公会名称
        helper.setText(R.id.tv_gognhui_name, "公会名称：" + item.getGuild_name() + "");
        //辅导日期
        helper.setText(R.id.tv_time, "辅导日期：" + item.getInspect_date() + "");
        //谁发出的
        TextView tv_type = helper.getView(R.id.tv_type);


        TextView tv_read_number = helper.getView(R.id.tv_read_number);
        //提醒查看
        TextView tv_hint = helper.getView(R.id.tv_hint);
        if (UserConfig.getInstance().getRole().equals("1")) {
//            if (ReadSize != 0) {
            //已读未读个数
            int ReadSize = item.getRead().size();
            int noReadSize = item.getNo_read().size();
            tv_read_number.setText("已读 " + ReadSize + "/" + (ReadSize + noReadSize));
            tv_read_number.setTextColor(mContext.getResources().getColor(R.color.text_color_light_black));
//            } else {
//                tv_read_number.setText("未读");
//                tv_read_number.setTextColor(mContext.getResources().getColor(R.color.text_color_light_black));
//            }
            tv_hint.setVisibility(View.VISIBLE);
            if (item.getIs_alert() == 1) {
                tv_hint.setText("提醒查看");
                tv_hint.setTextColor(mContext.getResources().getColor(R.color.text_color_task));
            } else if (item.getIs_alert() == 2) {
                tv_hint.setText("已提醒");
                tv_hint.setTextColor(mContext.getResources().getColor(R.color.text_color_task));
            } else {
                tv_hint.setVisibility(View.GONE);
            }
            //1是会长  2是管理员
            if (item.getType() == 1) {
                tv_type.setText("我");
            } else {
                tv_type.setText("管理员");
            }

        } else if (UserConfig.getInstance().getRole().equals("2")) {
            //0未读 1已读
            if (item.getIs_read() == 0) {
                tv_read_number.setText("未读");
                tv_read_number.setTextColor(mContext.getResources().getColor(R.color.text_color_light_black));
            } else {
                tv_read_number.setText("已读");
                tv_read_number.setTextColor(mContext.getResources().getColor(R.color.text_color_task));
            }
            tv_hint.setVisibility(View.GONE);
            //1是会长  2是管理员
            if (item.getType() == 1) {
                tv_type.setText("会长");
            } else {
                tv_type.setText("管理员");
            }

        } else {
            //已读未读个数
            int ReadSize = item.getRead().size();
            int noReadSize = item.getNo_read().size();
            tv_read_number.setText("已读 " + ReadSize + "/" + (ReadSize + noReadSize));
            tv_read_number.setTextColor(mContext.getResources().getColor(R.color.text_color_light_black));
            tv_hint.setVisibility(View.VISIBLE);
            if (item.getIs_alert() == 1) {
                tv_hint.setText("提醒查看");
                tv_hint.setTextColor(mContext.getResources().getColor(R.color.text_color_task));
            } else if (item.getIs_alert() == 2) {
                tv_hint.setText("已提醒");
                tv_hint.setTextColor(mContext.getResources().getColor(R.color.text_color_task));
            } else {
                tv_hint.setVisibility(View.GONE);
            }
            //1是会长  2是管理员
            if (item.getType() == 1) {
                tv_type.setText("会长");
            } else {
                tv_type.setText("我");
            }

        }
        if (item.getIs_alert() == 1) {
            tv_hint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CallBackUtils.mCallBack.doSomething(item.getId() + "");
                }
            });

        }


    }

    private void setOpen(int position, RelativeLayout rl_kuang, TextView tv_comment, TextView tv_open, FrameLayout fl_bottom, LinearLayout ll_bottom) {

//        RelativeLayout.LayoutParams para;
//        para = (RelativeLayout.LayoutParams) rl_kuang.getLayoutParams();
//        if (mList.get(position)==true){
//            tv_open.setText("收起");
//            fl_bottom.setVisibility(View.GONE);
//            ll_bottom.setVisibility(View.VISIBLE);
//            para.height=LinearLayout.LayoutParams.WRAP_CONTENT;
//            rl_kuang.setPadding(10,0,10,30);
//            rl_kuang.setLayoutParams(para);
//            mList.put(position,false);
//        }
//        else {
//            fl_bottom.setVisibility(View.VISIBLE);
//            ll_bottom.setVisibility(View.GONE);
//            tv_open.setText("展开更多");
//            para.height=DensityUtil.dip2px(mContext,300);
//            rl_kuang.setPadding(10,0,10,0);
//            rl_kuang.setLayoutParams(para);
//            mList.put(position,true);
//        }
    }

    //
    @Override
    public void convert(ViewHolder helper, PatrolDataBean.InspectsBean pageDataBean, List<Object> payloads) {

    }

    public interface onClick {
        void doSomething(String id);
    }


}
