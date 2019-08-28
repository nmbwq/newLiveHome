package shangri.example.com.shangri.ui.crosswiseListview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.model.bean.response.EncyclopediaHomeBean;


public class HorizontalListViewAdapter extends BaseAdapter {
	List<EncyclopediaHomeBean.GuildBean> mNewsListOne;
	private Context mContext;
	private LayoutInflater mInflater;
	Bitmap iconBitmap;
	private int selectIndex = -1;

	public HorizontalListViewAdapter(Context context, List<EncyclopediaHomeBean.GuildBean> mNewsListOne){
		this.mContext = context;
		this.mNewsListOne = mNewsListOne;

		mInflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);//LayoutInflater.from(mContext);
	}
	@Override
	public int getCount() {
		return mNewsListOne.size();
	}
	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder;
		if(convertView==null){
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.item_gongghui, null);
			holder.im_image= convertView.findViewById(R.id.im_image);
			holder.tv_name= convertView.findViewById(R.id.tv_name);
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder)convertView.getTag();
		}
		if(position == selectIndex){
			convertView.setSelected(true);
		}else{
			convertView.setSelected(false);
		}

//		holder.mTitle.setText(mTitles[position]);
//		iconBitmap = getPropThumnail(mIconIDs[position]);
//		holder.mImage.setImageBitmap(iconBitmap);

		Glide.with(mContext)
				.load(mNewsListOne.get(position).getCover_url())
				.placeholder(R.mipmap.bg_touxiang)
				.crossFade()
				.into(holder.im_image);
		holder.tv_name.setText(mNewsListOne.get(position).getGuild_name());

		return convertView;
	}

	private static class ViewHolder {
		private TextView tv_name ;
		private ImageView im_image;
	}

	public void setSelectIndex(int i){
		selectIndex = i;
	}
}