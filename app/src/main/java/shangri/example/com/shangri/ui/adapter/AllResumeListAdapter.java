package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.adapter.CommonAdapter;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.response.AllListBean;
import shangri.example.com.shangri.model.bean.response.MyAnchorListBeanResponse;
import shangri.example.com.shangri.model.bean.response.anchorChectBean;
import shangri.example.com.shangri.ui.activity.AddInviteActivity;
import shangri.example.com.shangri.ui.activity.HostCollectionActivity;
import shangri.example.com.shangri.ui.activity.LookAnchorDetailActivity;
import shangri.example.com.shangri.ui.activity.TellAboutListActivity;
import shangri.example.com.shangri.util.AndroidInterface.AnchorChectFace;
import shangri.example.com.shangri.util.AndroidInterface.CompanyInterfaceUtils;
import shangri.example.com.shangri.util.PointUtils;
import shangri.example.com.shangri.util.ViewHolder;

/**
 * Created by Administrator on 2018/1/3.
 */

public class AllResumeListAdapter extends BaseListAdapter<AllListBean.ListBean> {
    private Context mContext;
    private Animation mLikeAnim;
    List<AllListBean.ListBean> data = new ArrayList<>();


    public AllResumeListAdapter(Context context, int layoutId, List<AllListBean.ListBean> datas) {
        super(context, layoutId, datas);
        mContext = context;
        data = datas;
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
    }

    @Override
    public void convert(ViewHolder helper, final AllListBean.ListBean applysBean) {
        helper.setText(R.id.tv_name, applysBean.getNickname() + "");
        if (applysBean.getType_name().size() > 0) {
            helper.setText(R.id.tv_type, "# " + applysBean.getType_name().get(0) + "");
        } else {
            helper.setText(R.id.tv_type, "");
        }
        TextView tv_robhime_number = helper.getView(R.id.tv_robhime_number);

        LinearLayout ll_ba = helper.getView(R.id.ll_ba);


        if (applysBean.getRob_num() > 0) {
            tv_robhime_number.setText(applysBean.getRob_num() + "人正在抢ta");
            ll_ba.setBackground(mContext.getResources().getDrawable(R.mipmap.zbtj_rsdi));
        } else {
            tv_robhime_number.setText("快来抢我");
            ll_ba.setBackground(mContext.getResources().getDrawable(R.mipmap.weiqiangdi));
        }
        ImageView im_image = helper.getView(R.id.im_image);
        Glide.with(mContext)
                .load(applysBean.getPhoto_first() + "")
                .placeholder(R.mipmap.zbzp_zwtdi)
                .crossFade()
                .into(im_image);
        TextView tv_rob = helper.getView(R.id.tv_rob);
        tv_rob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PointUtils.isFastClick()) {
                    //        是否已抢 0否 1已抢
                    if (applysBean.getIs_rob() > 0) {
                        //已抢  直接跳转到约聊界面
                        //        约聊ID 为0时不跳转约聊界面
                        Intent intent = new Intent(mContext, TellAboutListActivity.class);
                        intent.putExtra("chat_id", applysBean.getChat_id() + "");
                        mContext.startActivity(intent);
                    } else {
                        CompanyInterfaceUtils.anchorChectFace.robhim(applysBean);
                    }
                }
            }
        });

//        helper.getConvertView().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (PointUtils.isFastClick()) {
//                    Intent intent = new Intent(mContext, LookAnchorDetailActivity.class);
//                    intent.putExtra("id", applysBean.getId() + "");
//                    intent.putExtra("IsFromAnchorClloect", true);
//                    mContext.startActivity(intent);
//                }
//
//            }
//        });


    }

    @Override
    public void convert(ViewHolder helper, AllListBean.ListBean applysBean, List<Object> payloads) {

    }


}
