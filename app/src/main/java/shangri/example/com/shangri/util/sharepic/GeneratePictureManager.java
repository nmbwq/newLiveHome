package shangri.example.com.shangri.util.sharepic;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;

/**
 * Created by HomgWu on 2017/11/29.
 */

public class GeneratePictureManager {
    private static GeneratePictureManager sManager;
    private HandlerThread mHandlerThread;
    private Handler mWorkHandler;
    private Handler mMainHandler;


    private GeneratePictureManager() {
        mHandlerThread = new HandlerThread(GeneratePictureManager.class.getSimpleName());
        mHandlerThread.start();
        mWorkHandler = new Handler(mHandlerThread.getLooper());
        mMainHandler = new Handler(Looper.getMainLooper());
    }

    public static GeneratePictureManager getInstance() {
        if (sManager == null) {
            synchronized (GeneratePictureManager.class) {
                if (sManager == null) {
                    sManager = new GeneratePictureManager();
                }
            }
        }
        return sManager;
    }

    public void generate(final GenerateModel generateModel, final OnGenerateListener listener) {
        mWorkHandler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    generateModel.startPrepare(listener);
                } catch (final Exception e) {
                    e.printStackTrace();
                    if (listener != null) {
                        GeneratePictureManager.this.postResult(new Runnable() {
                            @Override
                            public void run() {
                                listener.onGenerateFinished(e, null);
                            }
                        });
                    }
                }
            }
        });
    }

    private void postResult(Runnable runnable) {
        mMainHandler.post(runnable);
    }

    public void prepared(final GenerateModel generateModel, final OnGenerateListener listener) {
        mWorkHandler.post(new Runnable() {
            @Override
            public void run() {
                View view = generateModel.getView();
                Exception exception = null;
                Bitmap bitmap = null;
                try {
                    bitmap = GeneratePictureManager.this.createBitmap(view);
                    String savePath = generateModel.getSavePath();
                    if (!TextUtils.isEmpty(savePath)) {
                        if (!BitmapUtil.saveImage(bitmap, savePath)) {
                            exception = new RuntimeException("pic save failed");
                        }
                    }
                } catch (Exception e) {
                    exception = e;
                    e.printStackTrace();
                }
                if (listener != null) {
                    final Exception exception1 = exception;
                    final Bitmap bitmap1 = bitmap;
                    mMainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.onGenerateFinished(exception1, bitmap1);
                        }
                    });
                }
            }
        });
    }

    /**
     * 生成Bitmap
     */
    private Bitmap createBitmap(View view) {
        int widthSpec = View.MeasureSpec.makeMeasureSpec(view.getLayoutParams().width, View.MeasureSpec.EXACTLY);
        int heightSpec = View.MeasureSpec.makeMeasureSpec(view.getLayoutParams().height, View.MeasureSpec.EXACTLY);
        view.measure(widthSpec, heightSpec);
        int measureWidth = view.getMeasuredWidth();
        int measureHeight = view.getMeasuredHeight();
        view.layout(0, 0, measureWidth, measureHeight);
        int width = view.getWidth();
        int height = view.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }
    public interface OnGenerateListener {

        void onGenerateFinished(Throwable throwable, Bitmap bitmap);

    }
}
