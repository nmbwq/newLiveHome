package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.response.EncyclopediaList;
import shangri.example.com.shangri.ui.webview.EncyclopediaActivityWebView;
import shangri.example.com.shangri.util.CornersTransform;
import shangri.example.com.shangri.util.ViewHolder;
import shangri.example.com.shangri.util.PointUtils;

/**
 * Created by Administrator on 2018/1/3.
 */

public class anchorListAdapter extends BaseListAdapter<EncyclopediaList.AnchorBean> {
    private Context mContext;
    private Animation mLikeAnim;

    public anchorListAdapter(Context context, int layoutId, List<EncyclopediaList.AnchorBean> datas) {
        super(context, layoutId, datas);
        mContext = context;
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
    }


    @Override
    public void convert(ViewHolder helper, final EncyclopediaList.AnchorBean anchorBean) {
        helper.setText(R.id.tv_text, anchorBean.getAnchor_name() + "");
        helper.setText(R.id.tv_id, anchorBean.getGuild_id() + "");
        helper.setText(R.id.tv_plat_name, anchorBean.getPlatfrom_name() + "");
        ImageView im_image = helper.getView(R.id.im_image);
        ImageView im_plat_image = helper.getView(R.id.im_plat_image);
        List<String> tags = anchorBean.getTags();
        if (tags.size()>0){
           switch (tags.size()){
               case 1:
                   helper.setText(R.id.tv_symbol1, "#"+tags.get(0) + "");
                   break;
               case 2:
                   helper.setText(R.id.tv_symbol1, "#"+tags.get(0) + "");
                   helper.setText(R.id.tv_symbol2, "#"+tags.get(1) + "");
                   break;
               case 3:
                   helper.setText(R.id.tv_symbol1, "#"+tags.get(0) + "");
                   helper.setText(R.id.tv_symbol2, "#"+tags.get(1) + "");
                   helper.setText(R.id.tv_symbol3, "#"+tags.get(2) + "");
                   break;

           }

        }
        Glide.with(mContext)
                .load(anchorBean.getCover_url())
                .placeholder(R.mipmap.ic_moren)
                .crossFade()
                .into(im_image);
        Glide.with(mContext)
                .load(anchorBean.getPlatfrom_url())
                .placeholder(R.mipmap.ic_moren)
                .crossFade()
                .transform(new CornersTransform(mContext,5))
                .into(im_plat_image);
        helper.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (PointUtils.isFastClick()) {
                    Intent intent = new Intent(mContext, EncyclopediaActivityWebView.class);
                    intent.putExtra("type", 3);
                    intent.putExtra("id", anchorBean.getId());
                    intent.putExtra("url", anchorBean.getAnchor_url());
                    intent.putExtra("is_collect", anchorBean.getAnchor_collect());
                    intent.putExtra("title", anchorBean.getAnchor_name());
                    intent.putExtra("imageurl", anchorBean.getCover_url());
                    mContext.startActivity(intent);
                }
            }
        });
    }

    @Override
    public void convert(ViewHolder helper, EncyclopediaList.AnchorBean anchorBean, List<Object> payloads) {

    }
}
