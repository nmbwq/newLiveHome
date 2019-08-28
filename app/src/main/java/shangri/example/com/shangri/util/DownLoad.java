package shangri.example.com.shangri.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import shangri.example.com.shangri.R;


public class DownLoad {

    static Bitmap b = null;
    public static ExecutorService poolPic = Executors.newFixedThreadPool(3);
    // private Bitmap bitmap;
    // 软引用,使用HashMap存放,软引用.get（）,获取数据
    // SoftReference<Bitmap> softReference = new SoftReference<Bitmap>(bitmap);
    static HashMap<String, SoftReference<Bitmap>> sofeCachePic = new HashMap<String, SoftReference<Bitmap>>();
    // Map缓存
    public static HashMap<String, Bitmap> cacheHashMap = new HashMap<String, Bitmap>();
    // 为lrucache分配程序最大运行内存的1/8,
    private static LruCache<String, Bitmap> lruCachePic = new LruCache<String, Bitmap>(
            (int) (Runtime.getRuntime().maxMemory() / 8)) {
        // 计算添加的数据大小，默认返回值1
        @Override
        protected int sizeOf(String key, Bitmap value) {
            // 得到bitmap每一行所得byte
            return value.getRowBytes() * value.getHeight();
        }

        // 获得被挤掉的对象，evicted判断是否被挤出来
        @Override
        protected void entryRemoved(boolean evicted, String key,
                                    Bitmap oldValue, Bitmap newValue) {
            if (evicted) {
                // 数据被挤出来了，放到软引用中
                SoftReference<Bitmap> soft = new SoftReference<Bitmap>(oldValue);
                sofeCachePic.put(key, soft);
            }
            super.entryRemoved(evicted, key, oldValue, newValue);

        }

    };

    public static long getBitmapsize(Bitmap bitmap) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
            return bitmap.getByteCount();
        }
        // Pre HC-MR1
        return bitmap.getRowBytes() * bitmap.getHeight();
    }

    public static void downLoadPhoto(Context context, String photoUrl,
                                     ImageView image, ImageView image2) {

        // image.setImageResource(R.drawable.imagedefault);
        photoUrl =  photoUrl;
        image.setTag(photoUrl);
        Bitmap bitmap = lruCachePic.get(photoUrl);
        // 从lrucache取图片
        if (bitmap != null) {
            // long bitmapsize = getBitmapsize(bitmap);
            // if (bitmapsize / 1024 < 50) {
            // downLoadBitmap(context, photoUrl, null, image);
            // } else {
            image2.setVisibility(View.GONE);
            image.setVisibility(View.VISIBLE);
            image.setImageBitmap(bitmap);
            // }

            return;
        }

        // 从软引用取图片
        SoftReference<Bitmap> sofe = sofeCachePic.get(photoUrl);
        if (sofe != null) {
            bitmap = sofe.get();
            if (bitmap != null) {
                // long bitmapsize = getBitmapsize(bitmap);
                // if (bitmapsize / 1024 < 50) {
                // downLoadBitmap(context, photoUrl, null, image);
                // } else {
                image2.setVisibility(View.GONE);
                image.setVisibility(View.VISIBLE);
                image.setImageBitmap(bitmap);
                lruCachePic.put(photoUrl, bitmap);
                // }
                // Utils.getSmallBitmap(bitmap, 500, 500);

                return;
            }
        }
        // 本地取图片

        File file = getImagePath(context, photoUrl);
        if (file != null && file.exists()) {
            bitmap = BitmapFactory.decodeFile(file.getPath());
            if (bitmap != null) {
                // long bitmapsize = getBitmapsize(bitmap);
                // if (bitmapsize / 1024 < 50) {
                // downLoadBitmap(context, photoUrl, null, image);
                // } else {
                image2.setVisibility(View.GONE);
                image.setVisibility(View.VISIBLE);
                file.setLastModified(System.currentTimeMillis());
                // Utils.getSmallBitmap(bitmap, 500, 500);
                image.setImageBitmap(bitmap);
                lruCachePic.put(photoUrl, bitmap);
                // }

            }

            return;
        }

        downLoadBitmap(context, photoUrl, null, image, null);
    }

    /**
     *
     * @param context
     * @param photoUrl
     * @param image
     */
    public static void downLoadPhoto(Context context, String photoUrl,
                                     ImageView image) {
        downLoadPhoto(context, photoUrl, image, -1, null);
    }

    /**
     * 下载图片没有下载中的图片
     * @param context
     * @param photoUrl
     * @param image
     */
    public static void downLoadPhotoNoDef(Context context, String photoUrl,
                                          ImageView image) {

        // image.setImageResource(R.drawable.imagedefault);
        photoUrl =  photoUrl;
        image.setTag(photoUrl);
        Bitmap bitmap = lruCachePic.get(photoUrl);
        // 从lrucache取图片
        if (bitmap != null) {
            // long bitmapsize = getBitmapsize(bitmap);
            // if (bitmapsize / 1024 < 50) {
            // downLoadBitmap(context, photoUrl, null, image);
            // } else {
//			image2.setVisibility(View.GONE);
            image.setVisibility(View.VISIBLE);
            image.setImageBitmap(bitmap);
            // }

            return;
        }

        // 从软引用取图片
        SoftReference<Bitmap> sofe = sofeCachePic.get(photoUrl);
        if (sofe != null) {
            bitmap = sofe.get();
            if (bitmap != null) {
                // long bitmapsize = getBitmapsize(bitmap);
                // if (bitmapsize / 1024 < 50) {
                // downLoadBitmap(context, photoUrl, null, image);
                // } else {
//				image2.setVisibility(View.GONE);
                image.setVisibility(View.VISIBLE);
                image.setImageBitmap(bitmap);
                lruCachePic.put(photoUrl, bitmap);
                // }
                // Utils.getSmallBitmap(bitmap, 500, 500);

                return;
            }
        }
        // 本地取图片

        File file = getImagePath(context, photoUrl);
        if (file != null && file.exists()) {
            bitmap = BitmapFactory.decodeFile(file.getPath());
            if (bitmap != null) {
                // long bitmapsize = getBitmapsize(bitmap);
                // if (bitmapsize / 1024 < 50) {
                // downLoadBitmap(context, photoUrl, null, image);
                // } else {
//				image2.setVisibility(View.GONE);
                image.setVisibility(View.VISIBLE);
                file.setLastModified(System.currentTimeMillis());
                // Utils.getSmallBitmap(bitmap, 500, 500);
                image.setImageBitmap(bitmap);
                lruCachePic.put(photoUrl, bitmap);
                // }
            }
            return;
        }

        downLoadBitmap(context, photoUrl, null, image, null);
    }

    /**
     * 默认的图片下载方法
     *
     * @param context
     * @param photoUrl
     * @param image
     */
    public static void downLoadPhoto1(Context context, String photoUrl,
                                      ImageView image) {
        image.setImageResource(R.mipmap.logon_icon);
        photoUrl =  photoUrl;
        image.setTag(photoUrl);
        Bitmap bitmap = lruCachePic.get(photoUrl);
        // 从lrucache取图片
        // if (bitmap != null) {
        // image.setImageBitmap(bitmap);
        // return;
        // }

        // 从软引用取图片
        SoftReference<Bitmap> sofe = sofeCachePic.get(photoUrl);
        if (sofe != null) {
            bitmap = sofe.get();
            if (bitmap != null) {
                image.setImageBitmap(bitmap);
                lruCachePic.put(photoUrl, bitmap);
                return;
            }

        }
        // 本地取图片

        File file = getImagePath(context, photoUrl);
        if (file != null && file.exists()) {
            bitmap = BitmapFactory.decodeFile(file.getPath());
            if (bitmap != null) {
                file.setLastModified(System.currentTimeMillis());
                image.setImageBitmap(bitmap);
                lruCachePic.put(photoUrl, bitmap);
            }
            return;
        }

        downLoadBitmap(context, photoUrl, null, image, null);

    }

    public static void downLoadPhoto(Context context, String photoUrl,
                                     final ImageView image, int defaultId, final Animation animation,
                                     int flag) {
        photoUrl =  photoUrl;
//        if (defaultId == -1) {
//            image.setImageResource(R.drawable.logo);
//        } else {
//            image.setImageResource(defaultId);
//        }
        image.setTag(photoUrl);
        if (flag == 1) {
            downLoadBitmap(context, photoUrl, null, image, animation);
        } else {

            Bitmap bitmap = lruCachePic.get(photoUrl);
            // 从lrucache取图片
            if (bitmap != null) {
                if (animation != null) {
                    image.startAnimation(animation);
                }
                image.setImageBitmap(bitmap);
                return;
            }

            // 从软引用取图片
            SoftReference<Bitmap> sofe = sofeCachePic.get(photoUrl);
            if (sofe != null) {
                bitmap = sofe.get();
                if (bitmap != null) {
                    if (animation != null) {
                        image.startAnimation(animation);
                    }
                    image.setImageBitmap(bitmap);
                    lruCachePic.put(photoUrl, bitmap);
                    return;
                }

            }
            // 本地取图片

            File file = getImagePath(context, photoUrl);
            if (file != null && file.exists()) {
                bitmap = BitmapFactory.decodeFile(file.getPath());
                if (bitmap != null) {
                    if (animation != null) {
                        image.startAnimation(animation);
                    }
                    file.setLastModified(System.currentTimeMillis());
                    image.setImageBitmap(bitmap);
                    lruCachePic.put(photoUrl, bitmap);
                }
                return;
            }

            downLoadBitmap(context, photoUrl, null, image, animation);
        }
    }

    public static void downLoadPhoto2(Context context, String photoUrl,
                                      final ImageView image, int defaultId, final Animation animation,
                                      int flag) {
        photoUrl =  photoUrl;
        Log.d("Debug", photoUrl);
//		if (defaultId == -1) {
//			image.setImageResource(R.drawable.zhang_logo);
//		} else {
//			image.setImageResource(defaultId);
//		}
        image.setTag(photoUrl);
        if (flag == 1) {
            downLoadBitmap(context, photoUrl, null, image, animation);
        } else {

            Bitmap bitmap = lruCachePic.get(photoUrl);
            // 从lrucache取图片
            if (bitmap != null) {
                if (animation != null) {
                    image.startAnimation(animation);
                }
                image.setImageBitmap(bitmap);
                return;
            }

            // 从软引用取图片
            SoftReference<Bitmap> sofe = sofeCachePic.get(photoUrl);
            if (sofe != null) {
                bitmap = sofe.get();
                if (bitmap != null) {
                    if (animation != null) {
                        image.startAnimation(animation);
                    }
                    image.setImageBitmap(bitmap);
                    lruCachePic.put(photoUrl, bitmap);
                    return;
                }

            }
            // 本地取图片

            File file = getImagePath(context, photoUrl);
            if (file != null && file.exists()) {
                bitmap = BitmapFactory.decodeFile(file.getPath());
                if (bitmap != null) {
                    if (animation != null) {
                        image.startAnimation(animation);
                    }
                    file.setLastModified(System.currentTimeMillis());
                    image.setImageBitmap(bitmap);
                    lruCachePic.put(photoUrl, bitmap);
                }
                return;
            }

            downLoadBitmap(context, photoUrl, null, image, animation);
        }
    }

    /**
     * 提供自定义默认图片资源ID的下载方式,仅针对不包含高清图片的下载
     *
     * @param context
     * @param photoUrl
     * @param image
     * @param defaultId 默认图片Id
     * @param animation
     */
    public static void downLoadPhoto(Context context, String photoUrl,
                                     final ImageView image, int defaultId, final Animation animation) {
        downLoadPhoto(context, photoUrl, image, defaultId, animation, -1);
        // if (defaultId == -1) {
        // image.setImageResource(R.drawable.liu_imagedefault);
        // } else {
        // image.setImageResource(defaultId);
        // }
        // photoUrl =  photoUrl;
        // image.setTag(photoUrl);
        // Bitmap bitmap = lruCachePic.get(photoUrl);
        // // 从lrucache取图片
        // if (bitmap != null) {
        // if (animation != null) {
        // image.startAnimation(animation);
        // }
        // image.setImageBitmap(bitmap);
        // return;
        // }
        //
        // // 从软引用取图片
        // SoftReference<Bitmap> sofe = sofeCachePic.get(photoUrl);
        // if (sofe != null) {
        // bitmap = sofe.get();
        // if (bitmap != null) {
        // if (animation != null) {
        // image.startAnimation(animation);
        // }
        // image.setImageBitmap(bitmap);
        // lruCachePic.put(photoUrl, bitmap);
        // return;
        // }
        //
        // }
        // // 本地取图片
        //
        // File file = getImagePath(context, photoUrl);
        // if (file != null && file.exists()) {
        // bitmap = BitmapFactory.decodeFile(file.getPath());
        // if (bitmap != null) {
        // if (animation != null) {
        // image.startAnimation(animation);
        // }
        // file.setLastModified(System.currentTimeMillis());
        // image.setImageBitmap(bitmap);
        // lruCachePic.put(photoUrl, bitmap);
        // }
        // return;
        // }
        //
        // downLoadBitmap(context, photoUrl, null, image, animation);

    }

    /**
     * 下载高清图片
     *
     * @param context
     * @param photoUrl
     * @param image
     * @param imageView2
     * @param defaultId  默认图片Id
     */
    public static void downLoadPhotoHd(Context context, String photoUrl,
                                       ImageView image, ImageView imageView2, int defaultId) {
        // photoUrl =  photoUrl;
        // imageView2.setImageResource(R.drawable.imagedefault);
        imageView2.setTag(photoUrl);
        Bitmap bitmap = lruCachePic.get(photoUrl);

        // 从lrucache取图片
        if (bitmap != null) {
            image.setVisibility(View.GONE);
            imageView2.setVisibility(View.VISIBLE);
            imageView2.setImageBitmap(bitmap);
            return;
        }

        // 从软引用取图片
        SoftReference<Bitmap> sofe = sofeCachePic.get(photoUrl);
        if (sofe != null) {
            bitmap = sofe.get();
            if (bitmap != null) {
                image.setVisibility(View.GONE);
                imageView2.setVisibility(View.VISIBLE);
                imageView2.setImageBitmap(bitmap);
                lruCachePic.put(photoUrl, bitmap);
                return;
            }
        }
        // 本地取图片

        File file = getImagePath(context, photoUrl);
        if (file != null && file.exists()) {
            bitmap = BitmapFactory.decodeFile(file.getPath());
            if (bitmap != null) {
                file.setLastModified(System.currentTimeMillis());
                image.setVisibility(View.GONE);
                imageView2.setVisibility(View.VISIBLE);
                imageView2.setImageBitmap(bitmap);
                lruCachePic.put(photoUrl, bitmap);
            }

            return;
        }

        downLoadBitmap(context, photoUrl, image, imageView2, null);
    }

    private static File getImagePath(Context context, String photoUrl) {
        File root = getCacheDir(context);
        String[] spilt = photoUrl.split("/");
        String name = spilt[spilt.length - 1];

        return new File(root, name);
    }

    public static long getCacheSize(Context context) {
        long leng = 0;
        File cacheDir = getCacheDir(context);
        File[] listFiles = cacheDir.listFiles();
        for (int i = 0; i < listFiles.length; i++) {
            leng += listFiles[i].length();
        }
        return leng;

    }

    public static File getCacheDir(Context context) {
        File root = null;
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            // Environment.getExternalStorageDirectory();
            // mnt/sdcade/android/data/包名/cache
            // 用户卸载app，自动删除文件夹，包名/cache
            root = context.getExternalCacheDir();
        } else {
            root = context.getCacheDir();
        }
        return root;
    }

    // private static Bitmap downLoadBitmap(final Context context,
    // final String photoUrl) {
    // poolPic.execute(new Runnable() {
    //
    // @Override
    // public void run() {
    //
    // try {
    // URL url = new URL(photoUrl);
    // HttpURLConnection connection = (HttpURLConnection) url
    // .openConnection();
    // connection.setReadTimeout(10000);
    // connection.setRequestMethod("GET");
    // connection.connect();
    // if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
    // InputStream is = connection.getInputStream();
    // Bitmap bitmap = BitmapFactory.decodeStream(is);
    // // if (imageView2 == null) {
    // // String[] spit = photoUrl.split("/");
    // // String name = spit[spit.length - 1];
    // // saveToSDCade(context, name, bitmap);
    // // return;
    // // }
    //
    // // cacheHashMap.put(photoUrl, bitmap);
    // // 将下载好的图片保存到本地SD卡
    // File file = getImagePath(context, photoUrl);
    // FileOutputStream bos = new FileOutputStream(file);
    // bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
    // bos.close();
    // lruCachePic.put(photoUrl, bitmap);
    // // deleteCacheImageFile(context);
    // b = bitmap;
    // is.close();
    // }
    // } catch (MalformedURLException e) {
    // e.printStackTrace();
    // } catch (IOException e) {
    // e.printStackTrace();
    // } finally {
    //
    // }
    // }
    // });
    // return b;
    // }

    public static void downLoadBitmap(final Context context,
                                      final String photoUrl, final ImageView image,
                                      final ImageView imageView2, final Animation animation) {

        final Handler handler = new Handler();
        poolPic.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(photoUrl);
                    HttpURLConnection connection = (HttpURLConnection) url
                            .openConnection();
                    connection.setReadTimeout(5000);
                    connection.setRequestMethod("GET");
                    connection.connect();
                    if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        InputStream is = connection.getInputStream();
                        final Bitmap bitmap = BitmapFactory.decodeStream(is);
                        // if (imageView2 == null) {
                        // String[] spit = photoUrl.split("/");
                        // String name = spit[spit.length - 1];
                        // saveToSDCade(context, name, bitmap);
                        // return;
                        // }
                        if (bitmap == null) {
                            return;
                        }
                        // cacheHashMap.put(photoUrl, bitmap);
                        // 将下载好的图片保存到本地SD卡
                        File file = getImagePath(context, photoUrl);
                        FileOutputStream bos = new FileOutputStream(file);

                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
                        bos.close();
                        lruCachePic.put(photoUrl, bitmap);
                        // deleteCacheImageFile(context);

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                if (imageView2.getTag().toString()
                                        .equals(photoUrl)) {
                                    if (image == null) {
                                        imageView2.setVisibility(View.VISIBLE);
                                    } else {
                                        image.setVisibility(View.GONE);
                                        imageView2.setVisibility(View.VISIBLE);
                                    }
                                    if (animation != null) {
                                        imageView2.startAnimation(animation);
                                    }
                                    imageView2.setImageBitmap(bitmap);
                                }

                            }
                        });
                        is.close();
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {

                }
            }
        });

    }

    public static void deleteAllCacheImageFile(Context context) {
        File fileDir = getCacheDir(context);
        File[] listFiles = fileDir.listFiles();
        for (int i = 0; i < listFiles.length; i++) {
            listFiles[i].delete();
        }
    }

    /**
     * 保存的路径是Environment
     * .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
     * /mnt/sdcard/Photo/图片名
     *
     * @param context 保存的图片Url，作为从lrucache取数据的键
     */

    public static void saveToSDCade(Context context, String name, Bitmap bitmap) {

        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            // Date currtime = Calendar.getInstance().getTime();
            File pictureDir = Environment
                    .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            // Log.i("Text", pictureDir.getAbsolutePath() + name + "SD保存路径");
            File[] lisFiles = pictureDir.listFiles();
            for (int i = 0; i < lisFiles.length; i++) {
                if (lisFiles[i].getName().equals(name)) {
                    // Toast.makeText(context, "图片已保存成功!", Toast.LENGTH_SHORT)
                    // .show();
                    return;
                }
            }

            File file = new File(pictureDir, name);
            FileOutputStream boStream = null;
            try {
                boStream = (new FileOutputStream(file));
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, boStream);

                boStream.close();
                // Toast.makeText(context, "保存成功:" + file.getAbsolutePath(),
                // Toast.LENGTH_SHORT).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static Bitmap downLoadPhoto(Context context, String photoUrl) {
        photoUrl =  photoUrl;
        Bitmap bitmap = lruCachePic.get(photoUrl);
        // 从lrucache取图片
        if (bitmap != null) {
            // long bitmapsize = getBitmapsize(bitmap);
            // if (bitmapsize / 1024 < 50) {
            // downLoadBitmap(context, photoUrl, null, image);
            // } else {

            // }
            return bitmap;
        }

        // 从软引用取图片
        SoftReference<Bitmap> sofe = sofeCachePic.get(photoUrl);
        if (sofe != null) {
            bitmap = sofe.get();
            if (bitmap != null) {
                // long bitmapsize = getBitmapsize(bitmap);
                // if (bitmapsize / 1024 < 50) {
                // downLoadBitmap(context, photoUrl, null, image);
                // } else {
                lruCachePic.put(photoUrl, bitmap);
                // }
                // Utils.getSmallBitmap(bitmap, 500, 500);

                Log.d("Debug:", "二级");
                return bitmap;
            }
        }
        // 本地取图片

        File file = getImagePath(context, photoUrl);
        if (file != null && file.exists()) {
            bitmap = BitmapFactory.decodeFile(file.getPath());
            if (bitmap != null) {
                // long bitmapsize = getBitmapsize(bitmap);
                // if (bitmapsize / 1024 < 50) {
                // downLoadBitmap(context, photoUrl, null, image);
                // } else {
                file.setLastModified(System.currentTimeMillis());
                // Utils.getSmallBitmap(bitmap, 500, 500);
                lruCachePic.put(photoUrl, bitmap);
                // }

            }
            Log.d("Debug:", "本地");

            return bitmap;
        }

        Log.d("Debug:", "去下载");
        return downLoadBitmap(context, photoUrl);
    }

    private static Bitmap downLoadBitmap(final Context context,
                                         final String photoUrl) {
        poolPic.execute(new Runnable() {

            @Override
            public void run() {

                try {
                    URL url = new URL(photoUrl);
                    HttpURLConnection connection = (HttpURLConnection) url
                            .openConnection();
                    connection.setReadTimeout(10000);
                    connection.setRequestMethod("GET");
                    connection.connect();
                    if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        InputStream is = connection.getInputStream();
                        Bitmap bitmap = BitmapFactory.decodeStream(is);
                        // if (imageView2 == null) {
                        // String[] spit = photoUrl.split("/");
                        // String name = spit[spit.length - 1];
                        // saveToSDCade(context, name, bitmap);
                        // return;
                        // }

                        // cacheHashMap.put(photoUrl, bitmap);
                        // 将下载好的图片保存到本地SD卡
                        File file = getImagePath(context, photoUrl);
                        FileOutputStream bos = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
                        bos.close();
                        lruCachePic.put(photoUrl, bitmap);
                        // deleteCacheImageFile(context);
                        b = bitmap;
                        is.close();
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {

                }
            }
        });
        return b;
    }

}
