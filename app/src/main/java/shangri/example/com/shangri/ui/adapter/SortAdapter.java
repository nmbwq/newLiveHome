package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;

import com.bumptech.glide.request.target.SimpleTarget;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.model.bean.response.SortModel;
import shangri.example.com.shangri.ui.view.RoundImageView;


import java.util.List;


/**
 * @author J 适配器
 */
public class SortAdapter extends BaseAdapter implements SectionIndexer {
    private final int iconshow;   //0头像不显示1显示
    private List<SortModel> list = null;
    private Context mContext;
    private boolean isNeedCheck;

    public boolean isNeedCheck() {
        return isNeedCheck;
    }

    public void setNeedCheck(boolean isNeedCheck) {
        this.isNeedCheck = isNeedCheck;
    }

    public SortAdapter(Context mContext, List<SortModel> list, int i) {
        iconshow = i;
        this.mContext = mContext;
        this.list = list;
    }

    public void updateListView(List<SortModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.list.size();
    }

    public Object getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup arg2) {
        ViewHolder viewHolder = null;
        final SortModel mContent = list.get(position);
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.guild_list_item, null);

            viewHolder.tvTitle = view
                    .findViewById(R.id.tv_user_item_name);
            viewHolder.tv_id = view.findViewById(R.id.tv_id);
            viewHolder.icon = view
                    .findViewById(R.id.iv_user_item_icon);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        SortModel model = list.get(position);
        viewHolder.tvTitle.setText(model.getName());
//		Bitmap bitmap = getHttpBitmap(url);
//		viewHolder.icon.setImageBitmap(bitmap);

        final ViewHolder finalViewHolder = viewHolder;
        if (iconshow == 1) {
            finalViewHolder.icon.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(model.getIconUrl()).asBitmap().into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    finalViewHolder.icon.setImageBitmap(resource);
                }
            });
        } else if (iconshow == 0) {
            finalViewHolder.icon.setVisibility(View.GONE);
        } else if (iconshow == 2) {
            finalViewHolder.icon.setVisibility(View.GONE);
            viewHolder.tv_id.setVisibility(View.VISIBLE);
            viewHolder.tv_id.setText("ID:" + model.getId());
        }
        return view;

    }

    public void addData(List<SortModel> sourceDateList) {
        list = sourceDateList;
        notifyDataSetChanged();
    }

    final static class ViewHolder {
        TextView tv_id;
        TextView tvTitle;
        RoundImageView icon;
    }

    /**
     * 得到首字母的ascii值
     */
    public int getSectionForPosition(int position) {
        return list.get(position).getSortLetters().charAt(0);
    }

    public int getPositionForSection(int section) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = list.get(i).getSortLetters();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }

        return -1;
    }

    public String getAlpha(String str) {
        String sortStr = str.trim().substring(0, 1).toUpperCase();
        if (sortStr.matches("[A-Z]")) {
            return sortStr;
        } else {
            return "#";
        }
    }

    @Override
    public Object[] getSections() {
        return null;
    }
}