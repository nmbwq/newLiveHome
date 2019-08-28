package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import shangri.example.com.shangri.CardSelectView.SwipeCardBean;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.response.BossDataBean;
import shangri.example.com.shangri.ui.webview.AnchorBossWebView;
import shangri.example.com.shangri.ui.webview.BossWebView;
import shangri.example.com.shangri.util.ViewHolder;


/**
 * 咨询 adapter
 * Created by chengaofu on 2017/6/22.
 */

public class BossCardAdapter extends BaseListAdapter<SwipeCardBean> {

    private Context mContext;
    private Animation mLikeAnim;


    public BossCardAdapter(Context context, int layoutId, List<SwipeCardBean> datas) {
        super(context, layoutId, datas);
        mContext = context;
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
    }

    @Override
    public void convert(final ViewHolder holder, final SwipeCardBean data) { //, List<Object> payloads
//        Glide.with(mContext).load(data.getUrl()).fitCenter().into((ImageView) holder.itemView.findViewById(R.id.image_view));
//        ((TextView) holder.itemView.findViewById(R.id.text_view)).setText(data.getName());
//        TextView viewById = (TextView) holder.itemView.findViewById(R.id.text_view);
//        viewById.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("Debug", "点击事件");
////                callback.toLeft(card_recycler_view);
//            }
//        });
    }


    @Override
    public void convert(ViewHolder helper, SwipeCardBean data, List<Object> payloads) {
    }


}
