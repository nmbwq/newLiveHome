package shangri.example.com.shangri.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.response.CompanyMainBean;
import shangri.example.com.shangri.ui.activity.CompanyHomepageActivity;
import shangri.example.com.shangri.ui.view.RoundImageView;
import shangri.example.com.shangri.util.ViewHolder;

/**
 * Description:
 * Data：2018/11/7-18:12
 * Author: lin
 */
public class StarAdapter extends BaseListAdapter<CompanyMainBean.CompanyAnchor> {
    Context mContext;
    public StarAdapter(Context context, int layoutId, List<CompanyMainBean.CompanyAnchor> datas) {
        super(context, layoutId, datas);
        this.mContext = context;
    }

    @Override
    public void convert(ViewHolder helper, CompanyMainBean.CompanyAnchor companyAnchor) {
        final int position = helper.getAdapterPosition();
        helper.setText(R.id.tv_title, companyAnchor.getAnchor_name());
        ImageView iv_delete = helper.getView(R.id.iv_delete);
        if (!mContext.getClass().equals(CompanyHomepageActivity.class)){
            iv_delete.setVisibility(View.GONE);
        }else {
            iv_delete.setVisibility(View.VISIBLE);
        }
        final RoundImageView imageView = helper.getView(R.id.iv_icon);
//        Glide.with(mContext).load(companyAnchor.getAnchor_logo()).into(imageView);
        Glide.with(mContext).load(companyAnchor.getAnchor_logo()).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                imageView.setImageBitmap(resource);
            }
        });
        iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onItemClick(position);
            }
        });
    }

    @Override
    public void convert(ViewHolder helper, CompanyMainBean.CompanyAnchor companyAnchor, List<Object> payloads) {

    }
    onClick onClick;
    public interface onClick{
        void onItemClick(int pos);
    }

    public StarAdapter.onClick getOnClick() {
        return onClick;
    }

    public void setOnClick(StarAdapter.onClick onClick) {
        this.onClick = onClick;
    }
}
