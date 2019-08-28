package shangri.example.com.shangri.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import shangri.example.com.shangri.R;
import shangri.example.com.shangri.ui.view.ZoomImageView;
import shangri.example.com.shangri.util.DownLoad;


/**
 * Created by Administrator on 2016/3/14.
 */
public class ZoomImgActivity extends Activity {

    private TextView mNum;
    private List<String> imgUrl = null;
    private int pageIndex = 0;
    private ArrayList<LinearLayout> mList;
    private Boolean IsRealFilePath = false;
    private ViewPager mViewPager;

    public static final String URL = "url";
    public static final String PAGEINDEX = "pageIndex";
    public static final String ISREALFILEPATH = "IsRealFilePath";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.zhang_activity_image_zoom);
        initView();
        initEvent();
    }

    private void initView() {
        mNum = findViewById(R.id.tv_zoom_number);
        mViewPager = findViewById(R.id.vp_zoom);
    }


    /**
     * 16、获得屏幕宽度
     *
     * @param context
     * @return 屏幕宽度
     */
    @SuppressWarnings("JavaDoc")
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 17、获得屏幕高度
     *
     * @param context
     * @return 屏幕高度
     */
    @SuppressWarnings("JavaDoc")
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    private void initEvent() {
        Intent intent = getIntent();
        if (intent != null) {
            pageIndex = intent.getIntExtra(PAGEINDEX, 0);
            imgUrl = intent.getStringArrayListExtra(URL);
            String oneImg = intent.getStringExtra(URL);
            IsRealFilePath = intent.getBooleanExtra("IsRealFilePath", false);
            if (oneImg != null && oneImg.length() > 0) {
                if (imgUrl == null)
                    imgUrl = new ArrayList<>();
                imgUrl.add(oneImg);
            }
        }
        //下载图片
        if (imgUrl != null && imgUrl.size() > 0) {
            mList = new ArrayList<>();
            for (int i = 0; i < imgUrl.size(); i++) {
                LinearLayout m = new LinearLayout(this);
                m.setGravity(Gravity.CENTER);
                m.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                ZoomImageView zoomImageView = new ZoomImageView(this, this, 0, "");
                zoomImageView.setLayoutParams(new LinearLayout.LayoutParams(getScreenWidth(this), getScreenHeight(this)));
                if (IsRealFilePath) {
                    if (imgUrl.get(i).length() == 0) {
                        continue;
                    }
                    zoomImageView.setImageBitmap(getSDCardImg(imgUrl.get(i)));
                } else {
                    //记载网络图片的方法
                    DownLoad.downLoadPhoto(this, imgUrl.get(i), zoomImageView);
                }
                //加载本地图片
                m.addView(zoomImageView);
                mList.add(m);
            }
        }
        if (mList != null && mList.size() > 1)
            mNum.setText((pageIndex + 1) + "/" + mList.size());
        PagerAdapter adapter = new pagerAdapter(mList);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(pageIndex);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mNum.setText((1 + position) + "/" + mList.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    public static Bitmap getSDCardImg(String imagePath) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
//获取资源图片
        return BitmapFactory.decodeFile(imagePath, opt);
    }

    class pagerAdapter extends PagerAdapter {
        List<LinearLayout> imgs;

        public pagerAdapter(ArrayList<LinearLayout> imgs) {
            this.imgs = imgs;
        }

        @Override
        public int getCount() {
            return imgs.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(imgs.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(imgs.get(position));
            return imgs.get(position);
        }
    }
}
