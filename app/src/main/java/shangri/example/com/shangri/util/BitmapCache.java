package shangri.example.com.shangri.util;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.target.SimpleTarget;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.base.GlobalApp;

/**
 * Created by hasee on 2017/6/25.
 */

public class BitmapCache {
    private static BitmapCache mBitmapCache;
    private RequestManager mRequestManager;

    public static BitmapCache getInstance() {
        if (mBitmapCache == null) {
            mBitmapCache = new BitmapCache();
        }
        return mBitmapCache;
    }

    private BitmapCache() {
        mRequestManager = Glide.with(GlobalApp.getAppContext());
    }

    /**
     * @param imageView
     * @param imageUrl
     */
    public void loadBitmaps(ImageView imageView, String imageUrl, BitmapTransformation transformation) {
        loadBitmaps(imageView, imageUrl, 0, 0, transformation, false);
    }

    public void loadBitmaps(ImageView imageView, String imageUrl, Priority priority, BitmapTransformation transformation) {
        loadBitmaps(imageView, imageUrl, 0, 0, priority, transformation, false);
    }

    public void loadBitmaps(boolean crossFade, ImageView imageView, String imageUrl, BitmapTransformation transformation) {
        loadBitmaps(imageView, imageUrl, 0, 0, transformation, crossFade);
    }

    public void loadBitmaps(ImageView imageView, String imageUrl, int maxWidth,
                            int maxHeight, BitmapTransformation transformation, boolean crossFade) {
        loadBitmaps(imageView, imageUrl, maxWidth, maxHeight, Priority.NORMAL, transformation, crossFade);
    }

    public void loadBitmaps(ImageView imageView, String imageUrl, int maxWidth,
                            int maxHeight, BitmapTransformation transformation) {
        loadBitmaps(imageView, imageUrl, maxWidth, maxHeight, Priority.NORMAL, transformation, false);
    }


    public void loadBitmaps(String imageUrl, SimpleTarget<Bitmap> simpleTarget) {
        if (imageUrl == null) return;
        mRequestManager
                .load(imageUrl)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .priority(Priority.NORMAL)
                .fitCenter()
                .error(R.mipmap.jiazaitu_small)
                .placeholder(R.mipmap.jiazaitu_small)
                .into(simpleTarget);
    }

    /**
     * 加载图片
     *
     * @param imageView
     * @param imageUrl
     * @param maxWidth  图片最大宽度 （单位：dp）
     * @param maxHeight 图片最大高度 （单位：dp）
     */
    public void loadBitmaps(final ImageView imageView, String imageUrl, int maxWidth,
                            int maxHeight, Priority priority, BitmapTransformation transformation, boolean crossFade) {
        if (imageView == null || imageUrl == null) return;
        DrawableRequestBuilder<String> drawableRequestBuilder = mRequestManager
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .priority(priority)
                .fitCenter()
                .error(R.mipmap.bg_touxiang)
                .placeholder(R.mipmap.bg_touxiang);
        drawableRequestBuilder.skipMemoryCache(false);
        if (maxWidth != 0 && maxHeight != 0) {
            drawableRequestBuilder.override(maxWidth, maxHeight);
        }
        if (transformation != null) {
            drawableRequestBuilder.transform(transformation);
        }
        if (crossFade) {
            drawableRequestBuilder.crossFade(600);
        } else {
            drawableRequestBuilder.dontAnimate();
        }
        drawableRequestBuilder.into(imageView);
    }

    /**
     * 删除硬盘缓存
     */
    public void deleteDiskCache() {
        Glide.get(GlobalApp.getAppContext()).clearMemory();
        new Thread() {
            public void run() {
                Glide.get(GlobalApp.getAppContext()).clearDiskCache();
            }

        }.start();
    }

}
