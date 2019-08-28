package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.INotificationSideChannel;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.mikephil.charting.formatter.IFillFormatter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.adapter.CommonAdapter;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.response.LookMeCompanyBean;
import shangri.example.com.shangri.model.bean.response.ManagerChectBean;
import shangri.example.com.shangri.model.bean.response.MyAnchorListBeanResponse;
import shangri.example.com.shangri.ui.activity.CompanyHomepageActivityTwo;
import shangri.example.com.shangri.ui.view.CircleImageView;
import shangri.example.com.shangri.util.AndroidInterface.AnchorChectFace;
import shangri.example.com.shangri.util.CornersTransform;
import shangri.example.com.shangri.util.TimeUtil;
import shangri.example.com.shangri.util.ViewHolder;

/**
 * Created by Administrator on 2018/1/3.
 */

public class LookMeCompanyAdapter extends BaseListAdapter<LookMeCompanyBean.CompanyListBean> {
    private Context mContext;
    private Animation mLikeAnim;
    List<LookMeCompanyBean.CompanyListBean> data = new ArrayList<>();
    //    MyAnchoritemAdapter mAdapter;
    AnchorChectFace anchorChectFaces;


    public LookMeCompanyAdapter(Context context, int layoutId, List<LookMeCompanyBean.CompanyListBean> datas, AnchorChectFace anchorChectFace) {
        super(context, layoutId, datas);
        mContext = context;
        data = datas;
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
        anchorChectFaces = anchorChectFace;
    }

    @Override
    public void convert(ViewHolder helper, final LookMeCompanyBean.CompanyListBean bean) {

        ImageView im_photo = helper.getView(R.id.im_photo);
        TextView tv_name = helper.getView(R.id.tv_name);


        TextView tv_company_number = helper.getView(R.id.tv_company_number);
        TextView tv_anchor_number = helper.getView(R.id.tv_anchor_number);

        TextView tv_number = helper.getView(R.id.tv_number);
        TextView tv_time = helper.getView(R.id.tv_time);
        Glide.with(mContext)
                .load(bean.getLogo() + "")
                .placeholder(R.mipmap.bg_touxiang)
                .crossFade()
                .transform(new CornersTransform(mContext, 10))
                .into(im_photo);
        tv_name.setText(bean.getCompany_short_name() + "");
        tv_company_number.setText("规模：" + bean.getCompany_scale());
        tv_anchor_number.setText("旗下主播：" + bean.getAnchor_scale() + "人");

//        类型 1增 2减
        if (bean.getType().equals("1")) {
            tv_number.setText("+" + bean.getOperate_num() + "");
        } else {
            tv_number.setText("-" + bean.getOperate_num() + "");
        }

        String s = null;
        try {
            s = TimeUtil.longToString(Long.parseLong(bean.getView_time() + ""), "yyyy-MM-dd HH:mm");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tv_time.setText(s + "");

        helper.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Debug","传过去的token"+bean.getToken());
                Intent intent = new Intent(mContext, CompanyHomepageActivityTwo.class);
                intent.putExtra("COMPANY_TOKEN",bean.getToken());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public void convert(ViewHolder helper, LookMeCompanyBean.CompanyListBean applysBean, List<Object> payloads) {

    }


}
