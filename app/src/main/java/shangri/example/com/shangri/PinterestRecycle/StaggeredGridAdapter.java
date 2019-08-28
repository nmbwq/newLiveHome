package shangri.example.com.shangri.PinterestRecycle;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;


import java.util.ArrayList;
import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.model.bean.response.BossDataBean;
import shangri.example.com.shangri.model.bean.response.anchorChectBean;
import shangri.example.com.shangri.ui.activity.MainActivity;
import shangri.example.com.shangri.ui.fragment.NewBossFragment;

/**
 * Created by android on 2016/6/4.
 */
public class StaggeredGridAdapter extends RecyclerView.Adapter<StaggeredGridHolder> {
    private View view;
    private List<Integer> heights;
    private LayoutInflater mInflater;
    private OnItemClickLitener mOnItemClickLitener;

    List<BossDataBean.ListBean.DataBean> data = new ArrayList<>();


    public void setmDatas(List<BossDataBean.ListBean.DataBean> mDatas) {
        this.data = mDatas;
        notifyDataSetChanged();
    }

    public void addmDatas(List<BossDataBean.ListBean.DataBean> mDatas) {
        if (this.data == null) {
            this.data = mDatas;
            Log.d("Debug", "到达1");
        } else {
            Log.d("Debug", "传过来的长度" + mDatas.size());
//            for (int i = 0; i <mDatas.size() ; i++) {
//                this.data.add(mDatas.get(i));
//            }
            this.data.addAll(mDatas);
            Log.d("Debug", "到达2");
        }
        Log.d("Debug", "返回的数据长度为" + data.size());
        notifyDataSetChanged();
    }


    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public StaggeredGridAdapter(Context context, List<BossDataBean.ListBean.DataBean> datas) {
        mInflater = LayoutInflater.from(context);
        this.data = datas;
        heights = new ArrayList<Integer>();
    }

    @Override
    public StaggeredGridHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //利用反射将item的布局加载出来
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_sg_item, null);
        //new一个我们的ViewHolder，findViewById操作都在LinearHolder的构造方法中进行了
        return new StaggeredGridHolder(view);
    }

    @Override
    public void onBindViewHolder(final StaggeredGridHolder holder, int position) {
        LayoutParams layoutParams = holder.sg_item.getLayoutParams();
        if (position == 0) {
            layoutParams.height = 300;
        } else {
            layoutParams.height = 200;
        }

        holder.sg_item.setText(data.get(position).getId());
        holder.sg_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int pos = holder.getLayoutPosition();
//                mOnItemClickLitener.onItemClick(holder.itemView, pos);
            }
        });
//        holder.sg_item.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                int pos = holder.getLayoutPosition();
//                mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
//                return false;
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}

