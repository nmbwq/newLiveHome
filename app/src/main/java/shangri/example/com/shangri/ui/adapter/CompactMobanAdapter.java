package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.ArrayList;
import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.response.CompactMobanResponse;
import shangri.example.com.shangri.model.bean.response.companyListBean;
import shangri.example.com.shangri.ui.activity.CompactWebView.AddCompactlWebviewOne;
import shangri.example.com.shangri.util.ViewHolder;

/**
 * Created by Administrator on 2018/1/3.
 */

public class CompactMobanAdapter extends BaseListAdapter<CompactMobanResponse.TemplatesBean> {
    private Context mContext;
    private Animation mLikeAnim;
    List<CompactMobanResponse.TemplatesBean> data = new ArrayList<>();
    //    MyAnchoritemAdapter mAdapter;


    public CompactMobanAdapter(Context context, int layoutId, List<CompactMobanResponse.TemplatesBean> datas) {
        super(context, layoutId, datas);
        mContext = context;
        data = datas;
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
    }

    @Override
    public void convert(ViewHolder helper, final CompactMobanResponse.TemplatesBean templatesBean) {

        helper.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, AddCompactlWebviewOne.class).putExtra("table_name", templatesBean.getTable_name()));
            }
        });
        helper.setText(R.id.tv1, templatesBean.getTemplate_name() + "");
        helper.setText(R.id.tv2, templatesBean.getRequired_item() + "");

    }

    @Override
    public void convert(ViewHolder helper, CompactMobanResponse.TemplatesBean templatesBean, List<Object> payloads) {

    }


}
