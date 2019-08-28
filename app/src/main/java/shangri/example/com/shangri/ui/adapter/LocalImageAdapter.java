package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.util.ViewHolder;


/**
 * 咨询 adapter
 * Created by chengaofu on 2017/6/22.
 */

public class LocalImageAdapter extends BaseListAdapter<String> {

    private Context mContext;
    private Animation mLikeAnim;

    public LocalImageAdapter(Context context, int layoutId, List<String> datas) {
        super(context, layoutId, datas);
        mContext = context;
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
    }

    @Override
    public void convert(ViewHolder helper, String s) {

        ImageView im_image = helper.getView(R.id.im_image);
        if (s.equals("1")) {
            im_image.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.jinse_yezi));
        } else {
            im_image.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.huise_yezi));
        }

    }

    @Override
    public void convert(ViewHolder helper, String s, List<Object> payloads) {

    }


}
