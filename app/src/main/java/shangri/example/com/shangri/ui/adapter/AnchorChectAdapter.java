package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.adapter.CommonAdapter;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.response.MyAnchorListBeanResponse;
import shangri.example.com.shangri.model.bean.response.anchorChectBean;
import shangri.example.com.shangri.ui.view.CircleImageView;
import shangri.example.com.shangri.ui.view.ListViewForScrollView;
import shangri.example.com.shangri.util.AndroidInterface.AnchorChectFace;
import shangri.example.com.shangri.util.ViewHolder;

/**
 * Created by Administrator on 2018/1/3.
 */

public class AnchorChectAdapter extends BaseListAdapter<anchorChectBean.ApplysBean> {
    private Context mContext;
    private Animation mLikeAnim;
    List<anchorChectBean.ApplysBean> data = new ArrayList<>();
    //    MyAnchoritemAdapter mAdapter;
    AnchorChectFace anchorChectFaces;


    private CommonAdapter<MyAnchorListBeanResponse.GuildsBean.AnchorsBean> mAdapter;

    public AnchorChectAdapter(Context context, int layoutId, List<anchorChectBean.ApplysBean> datas, AnchorChectFace anchorChectFace) {
        super(context, layoutId, datas);
        mContext = context;
        data = datas;
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
        anchorChectFaces = anchorChectFace;
    }

    @Override
    public void convert(ViewHolder helper,final anchorChectBean.ApplysBean applysBean) {
        helper.setText(R.id.tv_name, applysBean.getAnchor_name());
        helper.setText(R.id.tv_platfrom, "直播平台：" + applysBean.getPlatfrom_name());
        helper.setText(R.id.tv_id, "ID：" + applysBean.getAnchor_uid());

        CircleImageView im_image = helper.getView(R.id.im_image);

        Glide.with(mContext)
                .load(applysBean.getAvatar_url())
                .placeholder(R.mipmap.bg_touxiang)
                .crossFade()
                .into(im_image);

        TextView tv_pass = helper.getView(R.id.tv_pass);
        LinearLayout ll_going = helper.getView(R.id.ll_going);
        LinearLayout ll_refuse = helper.getView(R.id.ll_refuse);

        TextView tv_resuse = helper.getView(R.id.tv_resuse);
        TextView tv_commit = helper.getView(R.id.tv_commit);

//        审核状态 0待审核 1审核通过 -1审核不通过
        if (applysBean.getCheck_status().equals("0")) {
            ll_going.setVisibility(View.VISIBLE);
            ll_refuse.setVisibility(View.GONE);
            tv_pass.setVisibility(View.GONE);
        } else if (applysBean.getCheck_status().equals("1")) {
            ll_going.setVisibility(View.GONE);
            ll_refuse.setVisibility(View.GONE);
            tv_pass.setVisibility(View.VISIBLE);
        } else {
            ll_going.setVisibility(View.GONE);
            ll_refuse.setVisibility(View.VISIBLE);
            tv_pass.setVisibility(View.GONE);
            helper.setText(R.id.tv_refuse_text, "理由:" + applysBean.getCheck_mark()+"");
            Log.d("Debug", "拒绝的理由为" + applysBean.getCheck_mark());
        }

        tv_resuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                anchorChectFaces.anchorCheckFace(applysBean.getRegister_guild_id(),"-1","");
            }
        });
        tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                anchorChectFaces.anchorCheckFace(applysBean.getRegister_guild_id(),"1","");
            }
        });
    }

    @Override
    public void convert(ViewHolder helper, anchorChectBean.ApplysBean applysBean, List<Object> payloads) {

    }


}
