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

public class ImageAdapter extends BaseListAdapter<String> {

    private Context mContext;
    private Animation mLikeAnim;
    List<String> data;
    public Boolean isTrueSix = false;

    public ImageAdapter(Context context, int layoutId, List<String> datas) {
        super(context, layoutId, datas);
        mContext = context;
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
        data = datas;
    }

    @Override
    public void convert(ViewHolder helper, String s) {
        ImageView view = helper.getView(R.id.im_image);

        if (data.size() == 7) {
            data.remove(data.get(data.size() - 1));
            isTrueSix = true;
            Log.d("bug", "到达这里适配器这里");
        }

        if (data.size() == 1) {
            Glide.with(mContext)
                    .load("")
                    .placeholder(R.mipmap.xuanqu)
                    .crossFade()
                    .into(view);
        } else if (data.size() == 6) {
            Log.d("bug", "到达数据为6的这里");
            if (isTrueSix) {
                Log.d("bug", "isTrueSix true的操作");
                //本地文件
                File file = new File(s);
                //加载图片
                Glide.with(mContext).load(file).into(view);
            } else {
                Log.d("bug", "isTrueSix false的操作");
                if (helper.getPosition() == data.size() - 1) {
                    Glide.with(mContext)
                            .load("")
                            .placeholder(R.mipmap.xuanqu)
                            .crossFade()
                            .into(view);
                } else {
                    //本地文件
                    File file = new File(s);
                    //加载图片
                    Glide.with(mContext).load(file).into(view);
                }
            }

        } else {
            if (helper.getPosition() == data.size() - 1) {
                Glide.with(mContext)
                        .load("")
                        .placeholder(R.mipmap.xuanqu)
                        .crossFade()
                        .into(view);
            } else {
                //本地文件
                File file = new File(s);
                //加载图片
                Glide.with(mContext).load(file).into(view);
            }

        }

    }

    @Override
    public void convert(ViewHolder helper, String s, List<Object> payloads) {

    }


}
