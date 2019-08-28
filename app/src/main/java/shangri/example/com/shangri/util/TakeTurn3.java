package shangri.example.com.shangri.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner2;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bigkoo.convenientbanner.transformer.ScaleInTransformer;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.UserConfig;
import shangri.example.com.shangri.model.bean.response.VIPCardBean;
import shangri.example.com.shangri.ui.view.RoundImageView;

/**
 * VIP轮廓图
 */

public class TakeTurn3 {
    private List<VIPCardBean.Packages> packagesList = new ArrayList<>();
    Context mContext;
    onClickBuy onClickBuy;

    public TakeTurn3(Context context, List<VIPCardBean.Packages> bannerInfoBeanList,
                     ConvenientBanner2 convenientBanner, OnItemClickListener mOnItemClickListener, onClickBuy onClickBuy) {
        this.onClickBuy = onClickBuy;
        mContext = context;
        packagesList.clear();
        if (bannerInfoBeanList != null) {
            packagesList.addAll(bannerInfoBeanList);
        }
        convenientBanner.getViewPager().setPageMargin(DensityUtil.dip2px(context, 10));
        convenientBanner.getViewPager().setOffscreenPageLimit(3);

        convenientBanner.setPages(new CBViewHolderCreator<TakeTurn3.NetworkImageHolderView>() {
            @Override
            public TakeTurn3.NetworkImageHolderView createHolder() {
                return new TakeTurn3.NetworkImageHolderView();
            }
        }, packagesList).setOnItemClickListener(mOnItemClickListener)
                .setPageIndicator(new int[]{
                        R.mipmap.ic_page_indicator,
                        R.mipmap.ic_page_indicator_focused
                }).setPageIndicatorAlign(ConvenientBanner2.PageIndicatorAlign.CENTER_HORIZONTAL);
        convenientBanner.setPointViewVisible(false);
        convenientBanner.getViewPager().setPageTransformer(true, new ScaleInTransformer());
    }


    class NetworkImageHolderView implements Holder<VIPCardBean.Packages> {
        private ImageView image1;
        private RoundImageView iv_head;
        private TextView tv_type;
        private TextView price;
        private TextView time;
        private TextView go_buy;
        private TextView price_old;

        @Override
        public View createView(Context context) {
            View view = LayoutInflater.from(context).inflate(R.layout.layout_take_turns3, null);
            image1 = view.findViewById(R.id.image1);
            iv_head = view.findViewById(R.id.iv_head);
            tv_type = view.findViewById(R.id.tv_type);
            price = view.findViewById(R.id.price);
            time = view.findViewById(R.id.time);
            price_old = view.findViewById(R.id.price_old);
            go_buy = view.findViewById(R.id.go_buy);
            return view;
        }

        @Override
        public void UpdateUI(Context context, final int position, VIPCardBean.Packages data) {
            if (data.getIs_sales().equals("1")) {
                image1.setVisibility(View.VISIBLE);
                price.setText(data.getPrice_sales() + "");
            } else {
                image1.setVisibility(View.GONE);
                price.setText(data.getPrice() + "");
            }
            Glide.with(context).load(UserConfig.getInstance().getAvatar()).asBitmap().into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    iv_head.setImageBitmap(resource);
                }
            });
            tv_type.setText(data.getName() + "");
            time.setText(data.getValid_month() + "");
            if (data.getExpire_time() == null || data.getExpire_time().isEmpty()) {
                go_buy.setText("立即购买");
                time.setVisibility(View.INVISIBLE);
            } else {
                time.setVisibility(View.VISIBLE);
                go_buy.setText("马上续费");
                long time1 = new Date().getTime();
                long time2 = Long.parseLong(data.getExpire_time())*1000;
                if (time1 > time2) {
                    time.setText("已到期");
                } else {
                    time.setText(DateUtil.getTime1(time2+"") + "到期");
                }
            }

            if (data.getIs_sales().equals("1")) {
                price_old.setVisibility(View.VISIBLE);
                String str1 = "仅需" + data.getPrice_sales()+"元";
                String str2 = "(原价" + data.getPrice() + "元)";
                price.setText(str1);
                price_old.setText(str2);
                price_old.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
            } else {
                price_old.setVisibility(View.GONE);
                price.setText(data.getPrice());
            }
            go_buy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickBuy.onClick(position);
                }
            });

        }
    }

    public interface onClickBuy {
        void onClick(int position);
    }

}
