package shangri.example.com.shangri.ui.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.model.bean.response.CultivateBean;
import shangri.example.com.shangri.model.bean.response.anchorRecruitListBean;


/**
 * 导航
 */
public class CultivateAdapter1 extends BaseQuickAdapter<anchorRecruitListBean.ListBean, BaseViewHolder> {

    private Context context;
    ;
    public BaseViewHolder holder;

    public CultivateAdapter1(Context context, int layoutResId, List<anchorRecruitListBean.ListBean> data) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, anchorRecruitListBean.ListBean item) {
        holder = helper;

        ImageView iv_information = helper.getView(R.id.iv_information);
        TextView tv_name = (TextView) helper.getView(R.id.tv_name);
        tv_name.setText(item.getName());
        String type = item.getType();
        String id = item.getId()+"";
        String img_url = item.getImg_url();
        int color = tv_name.getCurrentTextColor();
      if (color== R.color.text_color_little_orange){
          tv_name.setTextColor(ContextCompat.getColor(context, R.color.white));
      }
        tv_name.setTextColor(ContextCompat.getColor(context, R.color.white));
        helper.setText(R.id.tv_name, item.getName());
        Glide.with(context).load(img_url)
                .dontAnimate()
                .placeholder(R.mipmap.bg_touxiang)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()

                .into(iv_information);
        helper.setTextColor(R.id.tv_name, ContextCompat.getColor(context, R.color.white));
        helper.addOnClickListener(R.id.cl_information);
        helper.addOnClickListener(R.id.iv_information);
      /*  helper.getView(R.id.cl_information).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.setTextColor(R.id.tv_name, ContextCompat.getColor(context, R.color.text_color_little_orange));
            }
        });*/


    }


    public void setTextColor() {
        TextView tv_name = holder.getView(R.id.tv_name);

        int color = tv_name.getCurrentTextColor();

            if (color==R.color.white){
                tv_name.setTextColor(ContextCompat.getColor(context, R.color.text_color_little_orange));
            }






    }
}
