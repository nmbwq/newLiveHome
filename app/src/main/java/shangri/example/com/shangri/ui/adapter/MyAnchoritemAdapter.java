package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.ArrayList;
import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.response.MyAnchorListBeanResponse;
import shangri.example.com.shangri.util.ViewHolder;

/**
 * Created by Administrator on 2018/1/3.
 */

public class MyAnchoritemAdapter extends BaseListAdapter<MyAnchorListBeanResponse.GuildsBean.AnchorsBean> {
    private Context mContext;
    private Animation mLikeAnim;
    List<MyAnchorListBeanResponse.GuildsBean.AnchorsBean> data = new ArrayList<>();

    public MyAnchoritemAdapter(Context context, int layoutId, List<MyAnchorListBeanResponse.GuildsBean.AnchorsBean> datas) {
        super(context, layoutId, datas);
        mContext = context;
        data = datas;
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
    }

    @Override
    public void convert(ViewHolder helper, MyAnchorListBeanResponse.GuildsBean.AnchorsBean anchorsBean) {
        Log.d("Debug", "到达适配器里面");
    }

    @Override
    public void convert(ViewHolder helper, MyAnchorListBeanResponse.GuildsBean.AnchorsBean anchorsBean, List<Object> payloads) {

    }

}
