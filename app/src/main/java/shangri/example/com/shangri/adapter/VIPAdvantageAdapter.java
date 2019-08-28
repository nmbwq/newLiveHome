package shangri.example.com.shangri.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.response.VIPAdvantageExplain;
import shangri.example.com.shangri.util.ViewHolder;

/**
 * Description:
 * Data：2018/11/22-11:47
 * Author: lin
 */
public class VIPAdvantageAdapter extends BaseListAdapter<VIPAdvantageExplain.Advantages> {
    private Context mContext;
    OnItemClick onItemClick;
    public VIPAdvantageAdapter(Context context, int layoutId, List<VIPAdvantageExplain.Advantages> datas,OnItemClick onItemClick) {
        super(context, layoutId, datas);
        mContext = context;
        this.onItemClick = onItemClick;
    }

    @Override
    public void convert(ViewHolder helper, VIPAdvantageExplain.Advantages advantageExplain) {
        final int position = helper.getAdapterPosition();
//        helper.setText(R.id.title, advantageExplain.getName());
        final TextView title = helper.getView(R.id.title);
        title.setText(advantageExplain.getName());
        final ImageView imageView = helper.getView(R.id.icon);
        Glide.with(mContext).load(advantageExplain.getImage()).placeholder(R.mipmap.hyzx_yzzbtj).into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onClick(position);
            }
        });
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onClick(position);
            }
        });
//        Glide.with(mContext).load(advantageExplain.getImage()).asBitmap().into(new SimpleTarget<Bitmap>() {
//            @Override
//            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//                imageView.setImageBitmap(resource);
//            }
//        });
    }

    @Override
    public void convert(ViewHolder helper, VIPAdvantageExplain.Advantages vipAdvantageExplain, List<Object> payloads) {

    }

    public interface OnItemClick{
        void onClick(int position);
    }
}
