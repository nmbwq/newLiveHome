package shangri.example.com.shangri.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.LayoutDirection;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.response.CompanyMainBean;
import shangri.example.com.shangri.ui.activity.CompanyHomepageActivity;
import shangri.example.com.shangri.util.GlideRoundTransform;
import shangri.example.com.shangri.util.ViewHolder;

/**
 * Description:显示平台
 * Data：2018/11/7-18:08
 * Author: lin
 */
public class PlatFormAdapter extends BaseListAdapter<CompanyMainBean.CompanyPlatfrom> {
    Context mContext;


    public PlatFormAdapter(Context context, int layoutId, List<CompanyMainBean.CompanyPlatfrom> datas) {
        super(context, layoutId, datas);
        this.mContext = context;
    }

    @Override
    public void convert(ViewHolder helper, CompanyMainBean.CompanyPlatfrom companyPlatfrom) {
        final int position = helper.getAdapterPosition();
        ImageView iv_delete = helper.getView(R.id.iv_delete);
        if (!mContext.getClass().equals(CompanyHomepageActivity.class)){
            iv_delete.setVisibility(View.GONE);
        }else {
            iv_delete.setVisibility(View.VISIBLE);
        }
        helper.setText(R.id.tv_title, companyPlatfrom.getPlat_name());
        ImageView imageView = helper.getView(R.id.iv_icon);
        Glide.with(mContext)
                .load(companyPlatfrom.getPlat_logo())
                .crossFade()
                .placeholder(R.mipmap.gsjs_mrtb)
                .transform(new GlideRoundTransform(mContext, 10))
                .into(imageView);
        iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onItemClick(position);
            }
        });
    }

    @Override
    public void convert(ViewHolder helper, CompanyMainBean.CompanyPlatfrom companyPlatfrom, List<Object> payloads) {

    }

    StarAdapter.onClick onClick;

    public StarAdapter.onClick getOnClick() {
        return onClick;
    }

    public void setOnClick(StarAdapter.onClick onClick) {
        this.onClick = onClick;
    }

}
