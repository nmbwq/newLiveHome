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

import java.util.ArrayList;
import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.model.bean.response.CompanyMainBean;

/**
 * Description:
 * Data：2018/11/8-9:19
 * Author: lin
 */
public class TakeTurnPhotos {
    private List<CompanyMainBean.CompanyPhoto> companyPhotos = new ArrayList<>();
    public TakeTurnPhotos(Context context, List<CompanyMainBean.CompanyPhoto> bannerInfoBeanList,
                     ConvenientBanner convenientBanner, OnItemClickListener mOnItemClickListener) {
        companyPhotos.clear();
        if(bannerInfoBeanList != null){
            companyPhotos.addAll(bannerInfoBeanList);
        }
        convenientBanner.getViewPager().setCanLoop(false);
        convenientBanner.getViewPager().setPageMargin(DensityUtil.dip2px(context, 10));
        convenientBanner.getViewPager().setOffscreenPageLimit(bannerInfoBeanList.size());
        convenientBanner.setPages(new CBViewHolderCreator<TakeTurnPhotos.NetworkImageHolderView>() {
            @Override
            public TakeTurnPhotos.NetworkImageHolderView createHolder() {
                return new TakeTurnPhotos.NetworkImageHolderView();
            }
        }, companyPhotos).setOnItemClickListener(mOnItemClickListener)
                .setPageIndicator(new int[]{
                        R.mipmap.ic_page_indicator,
                        R.mipmap.ic_page_indicator_focused
                }).setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
//                }).setPageIndicatorAlign(bannerInfoBeanList.size()==2?ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_LEFT:ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
        convenientBanner.setPointViewVisible(false);
        convenientBanner.getViewPager().setPageTransformer(true, new ScaleInTransformer());
    }


    class NetworkImageHolderView implements Holder<CompanyMainBean.CompanyPhoto> {
        private ImageView imageView;
        private TextView wordText;

        @Override
        public View createView(Context context) {
            //你可以通过layout文件来创建，也可以像我一样用代码创建，不一定是Image，任何控件都可以进行翻页
            View view = LayoutInflater.from(context).inflate(R.layout.layout_take_turns, null);
            imageView = view.findViewById(R.id.take_turns_image);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            wordText = view.findViewById(R.id.take_turns_title);
            return view;
        }

        @Override
        public void UpdateUI(Context context, int position, CompanyMainBean.CompanyPhoto data) {
            BitmapCache.getInstance().loadBitmaps(imageView, data.getImg_url(), null);
//            wordText.setText("必须只能有一个直接的子类布局。只要在layout中简单设置几个属性就可以轻松实现");
            wordText.setVisibility(View.GONE);
        }
    }

}
