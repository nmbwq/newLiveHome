package shangri.example.com.shangri.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.view.View;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import it.sauronsoftware.base64.Base64;

/**
 * <p>
 * BASE64编码解码工具包
 * </p>
 * <p>
 * 依赖javabase64-1.3.1.jar
 * </p>
 *
 * @author IceWee
 * @version 1.0
 * @date 2012-5-19
 */
public class layoutChangeImage {
    /**
     * 截图实现
     */
    public static byte[] CutImage(final View rl_image_text) {
        // 获取图片某布局
        rl_image_text.setDrawingCacheEnabled(true);
        rl_image_text.buildDrawingCache();
        Handler handler = new Handler();
        final byte[][] bytes = new byte[1][1];
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // 要在运行在子线程中
                final Bitmap bmp = rl_image_text.getDrawingCache();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
                bytes[0] = baos.toByteArray();
                // 保存图片
                rl_image_text.destroyDrawingCache(); // 保存过后释放资源 } },100); }

            }
        }, 0);
        return bytes[0];
    }

}
