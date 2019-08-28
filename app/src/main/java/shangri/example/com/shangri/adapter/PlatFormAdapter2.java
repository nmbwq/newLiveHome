package shangri.example.com.shangri.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.BaseListAdapter;
import shangri.example.com.shangri.model.bean.response.BossPlatBean;
import shangri.example.com.shangri.model.bean.response.CompanyMainBean;
import shangri.example.com.shangri.ui.view.RoundImageView;
import shangri.example.com.shangri.util.GlideRoundTransform;
import shangri.example.com.shangri.util.ViewHolder;

/**
 * Description:添加平台
 * Data：2018/11/9-11:27
 * Author: lin
 */
public class PlatFormAdapter2 extends BaseListAdapter<BossPlatBean.PlatfromBean> {
    Context mContext;
    IsCheck isCheckItem;

    public PlatFormAdapter2(Context context, int layoutId, List<BossPlatBean.PlatfromBean> datas,IsCheck isCheck) {
        super(context, layoutId, datas);
        this.mContext = context;
        this.isCheckItem = isCheck;
    }

    @Override
    public void convert(shangri.example.com.shangri.util.ViewHolder helper, BossPlatBean.PlatfromBean companyPlatfrom) {
        final int position = helper.getAdapterPosition();
        helper.setText(R.id.title, companyPlatfrom.getPlat_name());
        final RoundImageView imageView = helper.getView(R.id.icon);
        CheckBox isCheck = helper.getView(R.id.is_check);
        if (companyPlatfrom.getLogo().length()>0){
            Glide.with(mContext).load(companyPlatfrom.getLogo()).asBitmap().into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    imageView.setImageBitmap(resource);

                }
            });
        }
        isCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isCheckItem.getPosition(position,isChecked);
            }
        });
//        Glide.with(mContext).load(companyPlatfrom.getPlat_logo()).into(imageView);
    }

    @Override
    public void convert(ViewHolder helper, BossPlatBean.PlatfromBean companyPlatfrom, List<Object> payloads) {

    }

    public interface IsCheck{
        void getPosition(int pos,boolean isCheck);
    }

}
