package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.formatter.IFillFormatter;

import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.response.wantListBean;
import shangri.example.com.shangri.ui.activity.TellAboutListActivity;
import shangri.example.com.shangri.ui.view.CansCrollRecycle.RecyclerViewPager;
import shangri.example.com.shangri.util.AndroidInterface.CompanyInterfaceUtils;
import shangri.example.com.shangri.util.PointUtils;
import shangri.example.com.shangri.util.ToastUtil;
import shangri.example.com.shangri.util.ViewHolder;


/**
 * 咨询 adapter
 * Created by chengaofu on 2017/6/22.
 */

public class ChairLookJob extends BaseListAdapter<wantListBean.ResumesBean> {

    private Context mContext;
    private Animation mLikeAnim;
    private RightToLeft rightToLeft;
    private final int FIRST_STATE = -1;

    int imageposition = 1;

    public ChairLookJob(Context context, int layoutId, List<wantListBean.ResumesBean> datas, RightToLeft rightToLeft) {
        super(context, layoutId, datas);
        mContext = context;
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
        this.rightToLeft = rightToLeft;
    }

    @Override
    public void convert(final ViewHolder helper, final wantListBean.ResumesBean data) { //, List<Object> payloads
        final int position = helper.getAdapterPosition();
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        final int height = wm.getDefaultDisplay().getHeight();
//
//        Log.d("Debug", "返回屏幕的高度为" + DpPxUtils.px2dip(mContext, height));
        LinearLayout ll_info = (LinearLayout) helper.getView(R.id.ll_info);
        LinearLayout ll_select = (LinearLayout) helper.getView(R.id.ll_select);
        LinearLayout ll_rob_number = (LinearLayout) helper.getView(R.id.ll_rob_number);

        final ImageView iv_collect = helper.getView(R.id.iv_collect);
        LinearLayout ll_refush = (LinearLayout) helper.getView(R.id.ll_refush);
        LinearLayout rl_click_collect = (LinearLayout) helper.getView(R.id.rl_click_collect);
        LinearLayout ll_click_yuliao = (LinearLayout) helper.getView(R.id.ll_click_yuliao);

//        抢ta人数 0显示 ‘快来抢我’; >0 显示 ‘{rob_num}人正在抢ta’
        final TextView tv_rob_num = (TextView) helper.getView(R.id.tv_rob_num);
        if (data.getRob_num() > 0) {
            tv_rob_num.setText(data.getRob_num() + "人正在抢ta");
        } else {
            tv_rob_num.setText("快来抢我");
        }


        final ImageView im_qiangta = (ImageView) helper.getView(R.id.im_qiangta);
        final TextView tv_qiangta = (TextView) helper.getView(R.id.tv_qiangta);
        if (data.getIs_rob() > 0) {
            tv_qiangta.setText("已抢");
            im_qiangta.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.zbzp_yqzt));
        } else {
            tv_qiangta.setText("抢ta");
            im_qiangta.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.zbzp_qta));
        }


        final TextView tv_active_status = (TextView) helper.getView(R.id.tv_active_status);
        if (data.getActive_status().length() > 0) {
            tv_active_status.setVisibility(View.VISIBLE);
            tv_active_status.setText(data.getActive_status());
        } else {
            tv_active_status.setVisibility(View.GONE);
        }
        final TextView tv_photo_positon = (TextView) helper.getView(R.id.tv_photo_positon);
        RecyclerViewPager recy_image = (RecyclerViewPager) helper.getView(R.id.recy_image);
        if (data.getImg_url().size() > 0) {
            tv_photo_positon.setVisibility(View.VISIBLE);
            tv_photo_positon.setText("1/" + data.getImg_url().size());
        } else {
            tv_photo_positon.setVisibility(View.GONE);
        }

        recy_image.addOnPageChangedListener(new RecyclerViewPager.OnPageChangedListener() {
            @Override
            public void OnPageChanged(int oldPosition, int newPosition) {
                Log.d("Debug", oldPosition + "现在的位置是" + newPosition);
                if (oldPosition == FIRST_STATE && newPosition == FIRST_STATE) {
                    if (data.getImg_url().size() <= 1 && position == 0) {
                        Log.d("Debug", data.getImg_url().size() + "现在的位置是" + position);
                        rightToLeft.goTo(0, 1);
                    }
                } else {
                    tv_photo_positon.setText(newPosition + 1 + "/" + data.getImg_url().size());
                    rightToLeft.goTo(oldPosition, newPosition);
                }
            }

        });
//        if (data.getImg_url().size() > 0) {
        LinearLayoutManager layout = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        layout.setItemPrefetchEnabled(true);
        recy_image.setLayoutManager(layout);
        //设置每次滑动一张图片   true为滑动一张  false 快速滑动 可以滑动过好几张图片
        recy_image.setSinglePageFling(true);
        ImageMuchAdapter imageMuchAdapter;
        imageMuchAdapter = new ImageMuchAdapter(mContext, R.layout.look_image_item, data.getImg_url());
        recy_image.setAdapter(imageMuchAdapter);
        imageMuchAdapter.setData(data.getImg_url());
//        }
        TextView tv_name = helper.getView(R.id.tv_name);
        tv_name.setText(data.getNickname() + "");

        TextView tv_state = helper.getView(R.id.tv_state);
        if (data.getWanted_status_name().length() > 0) {
            tv_state.setVisibility(View.VISIBLE);
            tv_state.setText(data.getWanted_status_name());
        } else {
            tv_state.setVisibility(View.GONE);
        }
        TextView tv_age = helper.getView(R.id.tv_age);
        tv_age.setText(data.getAge() + "岁");
        ImageView im_sex = helper.getView(R.id.im_sex);
        if (data.getSex() == 1) {
            im_sex.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.nan));
        } else {
            im_sex.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.nv));
        }
        TextView tv_jingyan = helper.getView(R.id.tv_jingyan);
        tv_jingyan.setText(data.getLive_age() + "年直播经验");

        final TextView tv_type = helper.getView(R.id.tv_type);
        final TextView tv_type1 = helper.getView(R.id.tv_type1);
        final TextView tv_type2 = helper.getView(R.id.tv_type2);
        if (data.getType_name().size() == 0) {
            tv_type.setVisibility(View.GONE);
            tv_type1.setVisibility(View.GONE);
            tv_type2.setVisibility(View.GONE);
        } else if (data.getType_name().size() == 1) {
            tv_type.setText(data.getType_name().get(0) + "");
            tv_type.setVisibility(View.VISIBLE);
            tv_type1.setVisibility(View.GONE);
            tv_type2.setVisibility(View.GONE);
        } else if (data.getType_name().size() == 2) {
            tv_type.setVisibility(View.VISIBLE);
            tv_type1.setVisibility(View.VISIBLE);
            tv_type2.setVisibility(View.GONE);
            tv_type.setText(data.getType_name().get(0) + "");
            tv_type1.setText(data.getType_name().get(1) + "");
        } else {
            tv_type.setVisibility(View.VISIBLE);
            tv_type1.setVisibility(View.VISIBLE);
            tv_type2.setVisibility(View.VISIBLE);
            tv_type.setText(data.getType_name().get(0) + "");
            tv_type1.setText(data.getType_name().get(1) + "");
            tv_type2.setText(data.getType_name().get(2) + "");
        }
        TextView tv_money = helper.getView(R.id.tv_money);
        if (data.getPay_low() == 0 && data.getPay_high() == 0) {
            tv_money.setText("期望底薪：待议");
        } else {
            tv_money.setText("期望底薪：" + data.getPay_low() / 1000 + "k" + "-" + data.getPay_high() / 1000 + "k");
        }
        ImageView tv_liu_image = helper.getView(R.id.tv_liu_image);
        TextView tv_is_liu = helper.getView(R.id.tv_is_liu);
        //没有电话显示  预留电话   有电话显示拨打电话
        if (data.getIs_sign() == 1) {
            tv_is_liu.setVisibility(View.VISIBLE);
            tv_liu_image.setVisibility(View.VISIBLE);
            if (data.getTelephone().length() == 0) {
                tv_is_liu.setText("留电话");
                tv_liu_image.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.liudianhuah));
            } else {
                tv_is_liu.setText("拨电话");
                tv_liu_image.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.bodianhuah));
            }
        } else {
            tv_is_liu.setVisibility(View.VISIBLE);
            tv_liu_image.setVisibility(View.VISIBLE);
            if (data.getTelephone().length() == 0) {
                tv_is_liu.setText("留电话");
                tv_liu_image.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.liudianhua));
            } else {
                tv_is_liu.setText("拨电话");
                tv_liu_image.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.bodianhua));
            }
        }
//        1已签约 2未签约
        TextView tv1 = helper.getView(R.id.tv1);
        if (data.getIs_sign() == 1) {
            tv1.setVisibility(View.VISIBLE);
            tv_active_status.setVisibility(View.GONE);
            ll_rob_number.setVisibility(View.GONE);
        } else {
            tv1.setVisibility(View.GONE);
            tv_active_status.setVisibility(View.VISIBLE);
            ll_rob_number.setVisibility(View.VISIBLE);
        }
//        1已沟通 2未沟通  （新需求  拨打电话以及留电去掉  现在已沟通图标隐藏）
        TextView tv2 = helper.getView(R.id.tv2);
        if (data.getIs_linkup() == 1) {
            tv2.setVisibility(View.GONE);
        } else {
            tv2.setVisibility(View.GONE);
        }
        LinearLayout ll_click_phone = helper.getView(R.id.ll_click_phone);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) ll_info.getLayoutParams();
//获取当前控件的布局对象
        params.height = height - 75;//设置当前控件布局的高度
        ll_info.setLayoutParams(params);

        //点击拨打电话或是留电话
        ll_click_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (PointUtils.isFastClick()) {
                    if (data.getIs_sign() == 1) {
                        ToastUtil.showShort("该主播已签约公会");
                    } else {
                        CompanyInterfaceUtils.callPhoneFace.TakePhone(helper.getPosition(), data.getTelephone() + "", data.getNickname() + "", data.getTelephone() + "", data.getRegister_id() + "", data.getId() + "", data.getIs_linkup() + "");
                    }
                }
            }
        });
        //点击刷新
        ll_refush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (PointUtils.isFastClick()) {
                    CompanyInterfaceUtils.callPhoneFace.refush();
                }
            }
        });
        //点击约聊
        ll_click_yuliao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (PointUtils.isFastClick()) {
                    CompanyInterfaceUtils.callPhoneFace.yueliao(data.getId() + "", data.getIs_chat(), data);
                }
            }
        });
        if (data.getIs_collect() == 0) {
            iv_collect.setImageResource(R.mipmap.d11);
        } else {
            iv_collect.setImageResource(R.mipmap.c17);
        }
        //点击收藏
        rl_click_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (PointUtils.isFastClick()) {
                    if (data.getIs_collect() == 0) {
//                        iv_collect.setImageResource(R.mipmap.c17);
                        iv_collect.setImageResource(R.drawable.anim_collect);
                        AnimationDrawable animation1 = (AnimationDrawable) iv_collect.getDrawable();
                        animation1.start();
                    } else {
//                        iv_collect.setImageResource(R.mipmap.d11);
                        iv_collect.setImageResource(R.drawable.anim_cancel_collect);
                        AnimationDrawable animation1 = (AnimationDrawable) iv_collect.getDrawable();
                        animation1.start();
                    }
                    CompanyInterfaceUtils.callPhoneFace.Takecllock(helper.getAdapterPosition() + "", data.getIs_collect(), data.getId() + "");
                }
            }
        });

    }

    @Override
    public void convert(ViewHolder helper, wantListBean.ResumesBean data, List<Object> payloads) {
    }


    public interface RightToLeft {
        void goTo(int oldPos, int newPos);
    }
}
