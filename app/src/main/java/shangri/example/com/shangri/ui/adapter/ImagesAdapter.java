package shangri.example.com.shangri.ui.adapter;


import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.ui.listener.ImagesAddListener;
import shangri.example.com.shangri.util.BitmapCache;

import java.util.List;


/**
 * chengaofu
 * Created by chengaofu on 2017/7/2.
 */

public class ImagesAdapter extends BaseAdapter {

    private Context mContext;
    private ImagesAddListener onClickAddListener;
    private List<String> mUrls;

    public ImagesAdapter(Context context, List<String> urls, ImagesAddListener onClickAddListener) {
        mContext = context;
        mUrls = urls;
        this.onClickAddListener = onClickAddListener;
        mUrls.add("");
    }

    @Override
    public int getCount() {
        return mUrls.size() > 4 ? 4 : mUrls.size();
    }

    @Override
    public String getItem(int position) {
        return mUrls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_grid_photo, parent, false);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.picSelector = convertView.findViewById(R.id.item_choose);
        holder.picImg = convertView.findViewById(R.id.item_pic);
        holder.deleteImg = convertView.findViewById(R.id.item_pic_delete);
        final String url = mUrls.get(position);

        //显示图片
        if (TextUtils.isEmpty(url)) {
            holder.picSelector.setVisibility(View.VISIBLE);
            holder.picImg.setVisibility(View.GONE);
            holder.deleteImg.setVisibility(View.GONE);
        } else {
            holder.picSelector.setVisibility(View.GONE);
            holder.picImg.setVisibility(View.VISIBLE);
            holder.deleteImg.setVisibility(View.VISIBLE);
            BitmapCache.getInstance().loadBitmaps(holder.picImg, url, null);
        }


        holder.picSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //再次选择图片
                if (TextUtils.isEmpty(url)) {
                    if (onClickAddListener != null) {
                        onClickAddListener.add(position);
                    }
                } else {
                    //放大查看图片
//                    BigImagePagerActivity.startImagePagerActivity((Activity) mContext, getData(), position);
                }
            }
        });


        //删除按钮
        holder.deleteImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = mUrls.get(position);
                mUrls.remove(url);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    static class Holder {
        LinearLayout picSelector;
        ImageView picImg;
        ImageView deleteImg;

    }
}
