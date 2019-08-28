package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.response.BuyDetailBean;
import shangri.example.com.shangri.util.CornersTransform;
import shangri.example.com.shangri.util.ViewHolder;

/**
 * Description:
 * Data：2018/11/7-11:16
 * Author: lin
 */
public class ImageMuchAdapter extends BaseListAdapter<String> {
    private Animation mLikeAnim;

    public ImageMuchAdapter(Context context, int layoutId, List<String> datas) {
        super(context, layoutId, datas);
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
    }

    @Override
    public void convert(ViewHolder helper, String detailBean) {
        final ImageView im_photo1 = helper.getView(R.id.im_photo1);
//          .dontAnimate()  glide 默认加载会有淡入淡出的效果  该方法是去掉动画效果 直接显示图片
        Glide.with(mContext)
                .load(detailBean)
                .transform(new CornersTransform(mContext, 10))
                .crossFade()
                .dontAnimate()
                .into(new SimpleTarget<GlideDrawable>() { // 加上这段代码 可以解决(列表item中布局图片加载慢的问题)
                          @Override
                          public void onResourceReady(GlideDrawable resource,
                                                      GlideAnimation<? super GlideDrawable> glideAnimation) {
                              im_photo1.setImageDrawable(resource); //显示图片
                          }
                      }
                );
    }

    @Override
    public void convert(ViewHolder helper, String detailBean, List<Object> payloads) {

    }
}
