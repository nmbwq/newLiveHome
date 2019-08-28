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
import shangri.example.com.shangri.util.PointUtils;
import shangri.example.com.shangri.util.ViewHolder;

/**
 * Created by Administrator on 2018/1/3.
 */

public class platfromListAdapter extends BaseListAdapter<EncyclopediaList.PlatfromBean> {
    private Context mContext;
    private Animation mLikeAnim;

    public platfromListAdapter(Context context, int layoutId, List<EncyclopediaList.PlatfromBean> datas) {
        super(context, layoutId, datas);
        mContext = context;
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
    }

    @Override
    public void convert(ViewHolder helper, final EncyclopediaList.PlatfromBean platfromBean) {

        if (platfromBean.getLocation_tag() != null) {
            if (platfromBean.getLocation_tag().length() > 0) {
                helper.setText(R.id.tv_symbol, "#" + platfromBean.getLocation_tag() + "");
            }
        }else {
            helper.setText(R.id.tv_symbol,  "");
        }
        helper.setText(R.id.tv_name, platfromBean.getPlatfrom_name() + "");
        helper.setText(R.id.tv_context, platfromBean.getIntroduction() + "");
        ImageView im_image = helper.getView(R.id.im_image);

        Glide.with(mContext)
                .load(platfromBean.getCover_url())
                .placeholder(R.mipmap.ic_moren)
                .crossFade()
                .transform(new CornersTransform(mContext, 20))
                .into(im_image);
        helper.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (PointUtils.isFastClick()) {
                    Intent intent = new Intent(mContext, EncyclopediaActivityWebView.class);
                    intent.putExtra("type", 1);
                    intent.putExtra("id", platfromBean.getId());
                    intent.putExtra("url", platfromBean.getPlatfrom_url());
                    intent.putExtra("is_collect", platfromBean.getPlatfrom_collect());
                    intent.putExtra("title", platfromBean.getPlatfrom_name());
                    intent.putExtra("imageurl", platfromBean.getCover_url());
                    mContext.startActivity(intent);
                }
            }
        });
    }

    @Override
    public void convert(ViewHolder helper, EncyclopediaList.PlatfromBean platfromBean, List<Object> payloads) {

    }

}
