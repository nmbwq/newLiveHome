package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.model.bean.response.LabelDataBean;

import java.util.HashMap;
import java.util.List;

/**
 * @author fyales
 * @since 8/26/15.
 */
public class TagLabelAdapter extends BaseAdapter {

    private Context mContext;
    private List<LabelDataBean.TagsBean> mList;
//    private HashMap<Integer, Boolean> mselectList;

    public TagLabelAdapter(Context context, List<LabelDataBean.TagsBean> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
//        return mList==null?0:mList.size();
        return mList.size();
    }

    @Override
    public LabelDataBean.TagsBean getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.tagview, null);
            holder = new ViewHolder();
            holder.tagBtn = convertView.findViewById(R.id.tag_btn);
            holder.ll_tag = convertView.findViewById(R.id.ll_tag);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final LabelDataBean.TagsBean TagsBean = getItem(position);
        holder.tagBtn.setText(TagsBean.getContent());

        if (mList.get(position).getSelect() == true) {
            holder.ll_tag.setBackgroundResource(R.drawable.juxing14);
            holder.tagBtn.setTextColor(ContextCompat.getColor(mContext, R.color.item_tagview_color));
        } else {
            holder.ll_tag.setBackgroundResource(R.drawable.juxing13);
            holder.tagBtn.setTextColor(ContextCompat.getColor(mContext, R.color.color_999999));
        }

//        holder.ll_tag.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        return convertView;
    }

//    public void addData( List<LabelDataBean.TagsBean> list, HashMap<Integer, Boolean> selectList) {
//        mList = list;
//        notifyDataSetChanged();
//    }

//    public void selectChanged(HashMap<Integer, Boolean> selectList) {
//        mList = selectList;
//        notifyDataSetChanged();
//
//    }

    public void addData(List<LabelDataBean.TagsBean> tags) {
        mList = tags;
        notifyDataSetChanged();
    }

    static class ViewHolder {
        TextView tagBtn;
        LinearLayout ll_tag;
    }
}
