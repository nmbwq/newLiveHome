package shangri.example.com.shangri.adapter;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.response.CollecBean;
import shangri.example.com.shangri.util.GlideRoundTransform;
import shangri.example.com.shangri.util.ViewHolder;

/**
 * Description:主播收藏
 * Data：2018/11/13-14:57
 * Author: lin
 */
public class HostCollectionAdapter extends BaseListAdapter<CollecBean.Resume.DataBean> {
    private Context mContext;
    private Animation mLikeAnim;
    private onItemClick onItemClick;
    public HostCollectionAdapter(Context context, int layoutId, List<CollecBean.Resume.DataBean> datas,onItemClick onItemClick) {
        super(context, layoutId, datas);
        this.mContext = context;
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
        this.onItemClick = onItemClick;
    }

    @Override
    public void convert(ViewHolder helper, CollecBean.Resume.DataBean dataBean) {
         final int position = helper.getAdapterPosition() ;
        if (dataBean.getGet_resume()!=null){
            helper.setText(R.id.tv_name, dataBean.getGet_resume().getNickname()+"");
        }
        ImageView imageView = helper.getView(R.id.image);
        if (dataBean.getGet_resume_photo_first()!=null){

            Glide.with(mContext)
                    .load(dataBean.getGet_resume_photo_first().getImg_url())
                    .crossFade()
                    .placeholder(R.mipmap.gsjs_mrtb)
                    .into(imageView);
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onItemClick(position);
            }
        });
    }

    @Override
    public void convert(ViewHolder helper, CollecBean.Resume.DataBean dataBean, List<Object> payloads) {

    }

    public interface onItemClick{
        void onItemClick(int position);
    }
}
