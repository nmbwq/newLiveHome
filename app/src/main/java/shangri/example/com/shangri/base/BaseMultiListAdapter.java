package shangri.example.com.shangri.base;

import android.content.Context;
import android.view.ViewGroup;

import com.aspsine.irecyclerview.universaladapter.abslistview.MultiItemTypeSupport;
import shangri.example.com.shangri.model.bean.response.PageDataBean;
import shangri.example.com.shangri.util.ViewHolder;

import java.util.List;

/**
 *
 * 多种数据类型 基类Adapter
 * @param <T>
 */
public abstract class BaseMultiListAdapter<T> extends BaseListAdapter<T> {

    protected MultiItemTypeSupport<T> mMultiItemTypeSupport;

    public BaseMultiListAdapter(Context context, MultiItemTypeSupport<T> multiItemTypeSupport, List<T> datas) {
        super(context, -1, datas);
        mMultiItemTypeSupport = multiItemTypeSupport;
    }

    public BaseMultiListAdapter(Context context, MultiItemTypeSupport<T> multiItemTypeSupport) {
        super(context, -1);
        mMultiItemTypeSupport = multiItemTypeSupport;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = mMultiItemTypeSupport.getLayoutId(viewType);
        ViewHolder holder = ViewHolder.createViewHolder(mContext, parent, layoutId);
        return holder;
    }

    @Override
    public int getItemViewType(int position) {
        return mMultiItemTypeSupport.getItemViewType(position, mDatas.get(position));
    }

    public abstract void convert(ViewHolder helper, PageDataBean t, List<Object> payloads);
}
