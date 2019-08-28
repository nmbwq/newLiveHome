package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.util.ViewHolder;


/**
 * 咨询 adapter
 * Created by chengaofu on 2017/6/22.
 */

public class DetailImageAdapter extends BaseListAdapter<String> {

    private Context mContext;
    private Animation mLikeAnim;
    List<String> data;
    public Boolean isTrueSix = false;

    public DetailImageAdapter(Context context, int layoutId, List<String> datas) {
        super(context, layoutId, datas);
        mContext = context;
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
        data = datas;
    }

    @Override
    public void convert(ViewHolder helper, String s) {
        ImageView view = helper.getView(R.id.im_image);
        Glide.with(mContext)
                .load(s)
                .placeholder(R.mipmap.bg_touxiang)
                .crossFade()
                .into(view);
    }

    @Override
    public void convert(ViewHolder helper, String s, List<Object> payloads) {

    }


}
