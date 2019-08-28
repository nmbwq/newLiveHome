package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.response.CompactListResponse;
import shangri.example.com.shangri.model.bean.response.MessageResonse;
import shangri.example.com.shangri.ui.activity.CompactDetail;
import shangri.example.com.shangri.ui.activity.CompactWebView.CompactDetailWebview;
import shangri.example.com.shangri.ui.activity.CompactWebView.CompactSignWebview;
import shangri.example.com.shangri.util.ViewHolder;

/**
 * Created by Administrator on 2018/1/3.
 */

public class CompactAdapter extends BaseListAdapter<CompactListResponse.ContractsBean> {
    private Context mContext;
    private Animation mLikeAnim;
    int  type=0;

    public CompactAdapter(Context context, int layoutId, List<CompactListResponse.ContractsBean> datas,int type) {
        super(context, layoutId, datas);
        mContext = context;
        type=type;
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
    }

    @Override
    public void convert(ViewHolder helper, final CompactListResponse.ContractsBean contractsBean) {
        helper.setText(R.id.tv_title, contractsBean.getTitle() + "");
        helper.setText(R.id.tv_time, contractsBean.getCreate_date() + "");
        helper.setText(R.id.tv_name, contractsBean.getNickname() + "");
        TextView tv_type = helper.getView(R.id.tv_type);
        switch (contractsBean.getStatus()) {
//            1已签署 2拒签 3待签 4已撤销 5已失效 6草稿
            case 1:
                tv_type.setText("已签署");
                break;
            case 2:
                tv_type.setText("已拒签");
                break;
            case 3:
                tv_type.setText("待签");
                break;
            case 4:
                tv_type.setText("已撤销");
                break;
            case 5:
                tv_type.setText("已失效");
                break;
            case 6:
                tv_type.setText("草稿");
                break;
        }
        helper.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (contractsBean.getStatus() == 6) {
                    mContext.startActivity(new Intent(mContext, CompactSignWebview.class).putExtra("bean", contractsBean).putExtra("IsFromOther",true));
                } else {
                    mContext.startActivity(new Intent(mContext, CompactDetailWebview.class).putExtra("bean", contractsBean).putExtra("type",type));
                }

            }
        });

    }

    @Override
    public void convert(ViewHolder helper, CompactListResponse.ContractsBean contractsBean, List<Object> payloads) {

    }


}
