package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.response.CollectBean;
import shangri.example.com.shangri.model.bean.response.InterlocutionBean;
import shangri.example.com.shangri.ui.listener.NewsListListener;
import shangri.example.com.shangri.ui.listener.PraiseListener;
import shangri.example.com.shangri.util.ViewHolder;


/**
 * 咨询 adapter
 * Created by chengaofu on 2017/6/22.
 */

public class NewInterLocutionAdapter extends BaseListAdapter<CollectBean.CollectsBean> {

    private Context mContext;
    private Animation mLikeAnim;
    private PraiseListener mPraiseListener;
    private NewsListListener mNewsListListener;

    public NewInterLocutionAdapter(Context context, int layoutId, List<CollectBean.CollectsBean> datas) {
        super(context, layoutId, datas);
        mContext = context;
        mLikeAnim = AnimationUtils.loadAnimation(context, R.anim.anim_like);
    }

    @Override
    public void convert(final ViewHolder helper, CollectBean.CollectsBean data) { //, List<Object> payloads
        TextView tv_title = helper.getView(R.id.tv_title);
        TextView tv_data = helper.getView(R.id.tv_data);
        TextView tv_charm = helper.getView(R.id.tv_charm);
        TextView tv_Fans = helper.getView(R.id.tv_Fans);
        TextView tv_collect = helper.getView(R.id.tv_collect);

        final TextView tv_buttom = helper.getView(R.id.tv_buttom);
        final LinearLayout ll_contetn = helper.getView(R.id.ll_contetn);

        TextView tv_content = helper.getView(R.id.tv_content);

        tv_title.setText("      "+data.getQuestion());
        tv_data.setText(String.valueOf(data.getBrowse_amount()));
        tv_charm.setText(String.valueOf(data.getGood_amount()));
        tv_Fans.setText(String.valueOf(data.getBad_amount()));
        tv_content.setText(String.valueOf(data.getAnswer()));
        tv_collect.setText(String.valueOf(data.getCollect_amount()));

        Drawable ic_youyong1= mContext.getResources().getDrawable(R.mipmap.ic_youyong1);
        ic_youyong1.setBounds(0, 0, ic_youyong1.getMinimumWidth(), ic_youyong1.getMinimumHeight());

        Drawable ic_youyong2= mContext.getResources().getDrawable(R.mipmap.ic_youyong2);
        ic_youyong2.setBounds(0, 0, ic_youyong2.getMinimumWidth(), ic_youyong2.getMinimumHeight());

        if (data.getIsgood()==0){
            tv_charm.setCompoundDrawables(ic_youyong1,null,null,null);
            tv_charm.setTextColor(ContextCompat.getColor(mContext,R.color.text_color_little_black));

        }
        else {
            tv_charm.setCompoundDrawables(ic_youyong2,null,null,null);
            tv_charm.setTextColor(ContextCompat.getColor(mContext,R.color.text_color_little_orange));

        }


        Drawable ic_meiyong3 = mContext.getResources().getDrawable(R.mipmap.wenda_shoucang1);
        ic_meiyong3.setBounds(0, 0, ic_meiyong3.getMinimumWidth(), ic_meiyong3.getMinimumHeight());

        Drawable ic_meiyong4 = mContext.getResources().getDrawable(R.mipmap.wenda_shoucang2);
        ic_meiyong4.setBounds(0, 0, ic_meiyong4.getMinimumWidth(), ic_meiyong4.getMinimumHeight());


        if (data.getRegister_collect() == 0) {
            tv_collect.setCompoundDrawables(ic_meiyong3, null, null, null);
            tv_collect.setTextColor(ContextCompat.getColor(mContext, R.color.text_color_little_black));

        } else {
            tv_collect.setCompoundDrawables(ic_meiyong4, null, null, null);
            tv_collect.setTextColor(ContextCompat.getColor(mContext, R.color.text_color_little_orange));

        }

        Drawable ic_meiyong1= mContext.getResources().getDrawable(R.mipmap.ic_meiyong1);
        ic_meiyong1.setBounds(0, 0, ic_meiyong1.getMinimumWidth(), ic_meiyong1.getMinimumHeight());

        Drawable ic_meiyong2= mContext.getResources().getDrawable(R.mipmap.ic_meiyong2);
        ic_meiyong2.setBounds(0, 0, ic_meiyong2.getMinimumWidth(), ic_meiyong2.getMinimumHeight());

        if (data.getIsbad()==0){
            tv_Fans.setCompoundDrawables(ic_meiyong1,null,null,null);
            tv_Fans.setTextColor(ContextCompat.getColor(mContext,R.color.text_color_little_black));

        }
        else {
            tv_Fans.setCompoundDrawables(ic_meiyong2,null,null,null);
            tv_Fans.setTextColor(ContextCompat.getColor(mContext,R.color.text_color_little_orange));

        }
        tv_buttom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ll_contetn.getVisibility()==View.VISIBLE){
                    ll_contetn.setVisibility(View.GONE);
                    tv_buttom.setText("查看答案");
                }
                else {
                    ll_contetn.setVisibility(View.VISIBLE);
                    tv_buttom.setText("收起答案");
                }
            }
        });

        helper.getView(R.id.tv_charm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mPraiseListener != null) {
                    mPraiseListener.onPraise(getPosition(helper));
                }

            }
        });

        /**
         * 收藏操作
         */
        helper.getView(R.id.tv_collect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mPraiseListener != null) {
                    mPraiseListener.onColltion(getPosition(helper));
                }

            }
        });


        helper.getView(R.id.tv_Fans).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNewsListListener.onItemClickListener(helper.getAdapterPosition());
            }
        });
    }

    @Override
    public void convert(ViewHolder helper, CollectBean.CollectsBean data, List<Object> payloads) {
        TextView tv_charm = helper.getView(R.id.tv_charm);
        TextView tv_Fans = helper.getView(R.id.tv_Fans);

        tv_charm.setText(String.valueOf(data.getGood_amount()));
        tv_Fans.setText(String.valueOf(data.getBad_amount()));

        Drawable ic_youyong1= mContext.getResources().getDrawable(R.mipmap.ic_youyong1);
        ic_youyong1.setBounds(0, 0, ic_youyong1.getMinimumWidth(), ic_youyong1.getMinimumHeight());
        Drawable ic_youyong2= mContext.getResources().getDrawable(R.mipmap.ic_youyong2);
        ic_youyong2.setBounds(0, 0, ic_youyong2.getMinimumWidth(), ic_youyong2.getMinimumHeight());

        Drawable ic_meiyong1= mContext.getResources().getDrawable(R.mipmap.ic_meiyong1);
        ic_meiyong1.setBounds(0, 0, ic_meiyong1.getMinimumWidth(), ic_meiyong1.getMinimumHeight());

        Drawable ic_meiyong2= mContext.getResources().getDrawable(R.mipmap.ic_meiyong2);
        ic_meiyong2.setBounds(0, 0, ic_meiyong2.getMinimumWidth(), ic_meiyong2.getMinimumHeight());

        if (data.getIsgood()==0){
            tv_charm.setCompoundDrawables(ic_youyong1,null,null,null);
            tv_charm.setTextColor(ContextCompat.getColor(mContext,R.color.text_color_little_black));

        }
        else {
            tv_charm.setCompoundDrawables(ic_youyong2,null,null,null);
            tv_charm.setTextColor(ContextCompat.getColor(mContext,R.color.text_color_little_orange));

        }

        if (data.getIsbad()==0){
            tv_Fans.setCompoundDrawables(ic_meiyong1,null,null,null);
            tv_Fans.setTextColor(ContextCompat.getColor(mContext,R.color.text_color_little_black));

        }
        else {
            tv_Fans.setCompoundDrawables(ic_meiyong2,null,null,null);
            tv_Fans.setTextColor(ContextCompat.getColor(mContext,R.color.text_color_little_orange));

        }
    }






    public void setPraiseListener(PraiseListener praiseListener) {
        this.mPraiseListener = praiseListener;
    }

    public void setNewsListListener(NewsListListener newsListListener) { //列表点击
        this.mNewsListListener = newsListListener;
    }


}
