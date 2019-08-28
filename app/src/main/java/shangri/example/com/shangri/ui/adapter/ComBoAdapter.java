package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.aspsine.irecyclerview.animation.ScaleInAnimation;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.response.companyListBean;
import shangri.example.com.shangri.model.bean.response.legalMypagerBean;
import shangri.example.com.shangri.util.TimeUtil;
import shangri.example.com.shangri.util.ViewHolder;

/**
 * Created by Administrator on 2018/1/3.
 */

public class ComBoAdapter extends BaseListAdapter<legalMypagerBean.PayOrderBean> {
    private Context mContext;
    private Animation mLikeAnim;
    List<legalMypagerBean.PayOrderBean> data = new ArrayList<>();
    //    MyAnchoritemAdapter mAdapter;
    List<String> ListCount = new ArrayList<>();
    LocalImageAdapter localImageAdapter;


    public ComBoAdapter(Context context, int layoutId, List<legalMypagerBean.PayOrderBean> datas) {
        super(context, layoutId, datas);
        mContext = context;
        data = datas;
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
    }

    @Override
    public void convert(ViewHolder helper, final legalMypagerBean.PayOrderBean payOrderBean) {
        RecyclerView recyclerView = helper.getView(R.id.news_entertain_irv_zhubo);
        ImageView im_finish = helper.getView(R.id.im_finish);
        TextView tv_canuse_number = helper.getView(R.id.tv_canuse_number);
        TextView tv_sum_number = helper.getView(R.id.tv_sum_number);
        tv_sum_number.setText("(共" + payOrderBean.getPackage_type() + "次)");
        Log.d("Debug", "已使用的参数" + payOrderBean.getResidue_num());
        tv_canuse_number.setText(payOrderBean.getResidue_num() + "次");
        try {
            String endtime = TimeUtil.longToString(Long.parseLong(payOrderBean.getCreate_time()), "yyyy-MM-dd");
            helper.setText(R.id.tv_time, endtime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (payOrderBean.getResidue_num() == 0) {
            im_finish.setVisibility(View.VISIBLE);
        } else {
            im_finish.setVisibility(View.GONE);
        }
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 10));
//        页面叶子显示用过的集合
        ListCount.clear();
        for (int i = 0; i < 10; i++) {
            if (payOrderBean.getResidue_num() > 0) {
                if (i < payOrderBean.getResidue_num()) {
                    String a = "1";
                    ListCount.add(a);
                } else {
                    String a = "0";
                    ListCount.add(a);
                }
            } else {
                String a = "0";
                ListCount.add(a);
            }
        }
        localImageAdapter = new LocalImageAdapter(mContext, R.layout.item_location_image, ListCount);
        localImageAdapter.openLoadAnimation(new ScaleInAnimation());
        recyclerView.setAdapter(localImageAdapter);
    }

    @Override
    public void convert(ViewHolder helper, legalMypagerBean.PayOrderBean payOrderBean, List<Object> payloads) {

    }

}
