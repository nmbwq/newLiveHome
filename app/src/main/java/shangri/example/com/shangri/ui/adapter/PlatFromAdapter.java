package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.response.EncyclopediaHomeBean;
import shangri.example.com.shangri.ui.activity.NewLoginUserActivity;
import shangri.example.com.shangri.ui.webview.EncyclopediaActivityWebView;
import shangri.example.com.shangri.util.CornersTransform;
import shangri.example.com.shangri.util.PointUtils;
import shangri.example.com.shangri.util.ViewHolder;


/**
 * 咨询 adapter
 * Created by chengaofu on 2017/6/22.
 */

public class PlatFromAdapter extends BaseListAdapter<EncyclopediaHomeBean.PlatfromBean> {

    private Context mContext;
    private Animation mLikeAnim;

    public PlatFromAdapter(Context context, int layoutId, List<EncyclopediaHomeBean.PlatfromBean> datas) {
        super(context, layoutId, datas);
        mContext = context;
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
    }

    /**
     * id : 1
     * platfrom_name : 平台名称
     * introduction : 一句话描4
     * location_tag : 最具影响力
     * cover_url : http://ilivehome-image-bucket.oss-cn-qingdao.aliyuncs.com/wiki/2018-04-19/5ad805cd6db5c.png
     * show_location : 1
     * platfrom_url : http://test.ilivehome.net/api/read/wiki/web/1/type/1
     */

    private String id;
    private String platfrom_name;
    private String introduction;
    private String location_tag;
    private String cover_url;
    private String show_location;
    private String platfrom_url;


    @Override
    public void convert(ViewHolder helper, final EncyclopediaHomeBean.PlatfromBean platfromBean) {

        TextView tv_symbol = helper.getView(R.id.tv_symbol);
        if (platfromBean.getLocation_tag().length() == 0) {
            tv_symbol.setVisibility(View.INVISIBLE);
        } else {
            tv_symbol.setVisibility(View.VISIBLE);
        }

        helper.setText(R.id.tv_symbol, platfromBean.getLocation_tag() + "");
        helper.setText(R.id.tv_name, platfromBean.getPlatfrom_name() + "");
        helper.setText(R.id.tv_info, platfromBean.getIntroduction() + "");
        ImageView view = helper.getView(R.id.im_image);
        Glide.with(mContext)
                .load(platfromBean.getCover_url())
                .placeholder(R.mipmap.bg_touxiang)
                .crossFade()
                .transform(new CornersTransform(mContext, 20))
                .into(view);



            helper.getConvertView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (PointUtils.isFastClick()) {

                        if (TextUtils.isEmpty(UserConfig.getInstance().getToken())) {
                            mContext.startActivity(new Intent(mContext, NewLoginUserActivity.class));
                        }else {
                            Intent intent = new Intent(mContext, EncyclopediaActivityWebView.class);
                            intent.putExtra("type", 1);
                            intent.putExtra("id", platfromBean.getId());
                            intent.putExtra("url", platfromBean.getPlatfrom_url());
                            intent.putExtra("imageurl", platfromBean.getCover_url());
                            intent.putExtra("is_collect", platfromBean.getPlatfrom_collect());
                            intent.putExtra("title", platfromBean.getPlatfrom_name());
                            mContext.startActivity(intent);
                        }

                    }
                }
            });


    }

    @Override
    public void convert(ViewHolder helper, EncyclopediaHomeBean.PlatfromBean platfromBean, List<Object> payloads) {

    }

}
