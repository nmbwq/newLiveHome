package shangri.example.com.shangri.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bigkoo.convenientbanner.transformer.ScaleInTransformer;
import shangri.example.com.shangri.R;
import shangri.example.com.shangri.model.bean.response.BannerInfoBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 轮廓图
 * Created by chengaofu on 2017/4/27.
 *  1104436107@qq.com
 */

public class TakeTurns {
    private List<BannerInfoBean> mBannerInfoBeanList = new ArrayList<>();
    public TakeTurns(Context context, List<BannerInfoBean> bannerInfoBeanList,
                     ConvenientBanner convenientBanner, OnItemClickListener mOnItemClickListener) {
        mBannerInfoBeanList.clear();
        if(bannerInfoBeanList != null){
            if(bannerInfoBeanList.size() > 3){
                mBannerInfoBeanList.addAll(bannerInfoBeanList.subList(0,3));
            }else{
                mBannerInfoBeanList.addAll(bannerInfoBeanList);
            }

        }
        convenientBanner.getViewPager().setPageMargin(DensityUtil.dip2px(context, 10));
        convenientBanner.getViewPager().setOffscreenPageLimit(3);

        convenientBanner.setPages(new CBViewHolderCreator<TakeTurns.NetworkImageHolderView>() {
            @Override
            public TakeTurns.NetworkImageHolderView createHolder() {
                return new TakeTurns.NetworkImageHolderView();
            }
        }, mBannerInfoBeanList).setOnItemClickListener(mOnItemClickListener)
                .setPageIndicator(new int[]{
                        R.mipmap.ic_page_indicator,
                        R.mipmap.ic_page_indicator_focused
                }).setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
        convenientBanner.setPointViewVisible(false);
        convenientBanner.getViewPager().setPageTransformer(true, new ScaleInTransformer());
    }


    class NetworkImageHolderView implements Holder<BannerInfoBean> {
        private ImageView imageView;
        private TextView wordText;

        @Override
        public View createView(Context context) {
            //你可以通过layout文件来创建，也可以像我一样用代码创建，不一定是Image，任何控件都可以进行翻页

            View view = LayoutInflater.from(context).inflate(R.layout.layout_take_turns, null);
            imageView = view.findViewById(R.id.take_turns_image);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            wordText = view.findViewById(R.id.take_turns_title);
            return view;
        }

        @Override
        public void UpdateUI(Context context,int position, BannerInfoBean data) {
            BitmapCache.getInstance().loadBitmaps(imageView, data.getPicture_url(), null);
//            wordText.setText("必须只能有一个直接的子类布局。只要在layout中简单设置几个属性就可以轻松实现");
            wordText.setText(data.getTitle());
        }
    }


}
