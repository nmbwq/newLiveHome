package shangri.example.com.shangri.util.ali;

import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import shangri.example.com.shangri.ui.listener.OssUploadListener;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 阿里OSS文件管理
 * Created by chengaofu on 2017/6/2.
 */

public class AliOssManager {

    private String accesskeyid = "LTAIOkoCTVxucTay";
    private String accessSercret = "LdFHh1xGazX7yB28K0Nv7LrUt3DUFQ";
    private String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
    private String bucketName = "liveportal-images";
    private String OSS_PATH = "https://liveportal-images.oss-cn-hangzhou.aliyuncs.com/";
    private static AliOssManager mManager;
    private MultipartUploadFile mUpLoadFile;
    private ManageObject mManageObj;
    private String mDeviceId; //设备ID


    private OssUploadListener mOssUploadListener;

    public static AliOssManager getInstance(Context context) {
        if (mManager == null) {
            synchronized (AliOssManager.class) {
                mManager = new AliOssManager(context);
            }

        }
        return mManager;
    }

    public AliOssManager(Context context) {
        //设备号
        mDeviceId = Settings.System.getString(context.getContentResolver(), Settings.System.ANDROID_ID);
        //初始化OSS对象
        OSS ossClient = initAliOss(context);
        mUpLoadFile = new MultipartUploadFile(ossClient, bucketName);
        mManageObj = new ManageObject(ossClient, bucketName);
    }

    private OSS initAliOss(Context context) {
        OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider(accesskeyid, accessSercret);
        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
        conf.setMaxConcurrentRequest(5); // 最大并发请求书，默认5个
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
        OSS oss = new OSSClient(context, endpoint, credentialProvider, conf);
        return oss;
    }
    /**
     * 需要根据要删除的图片路径 将对应在阿里OSS中的删除掉（目前不知道objectKey）
     *
     * @param objectKey
     * @param deleteUrls
     */
    public void deleteFromAliOss(String objectKey, List<String> deleteUrls) {
        mManageObj.asyncDeleteObject(objectKey); //如何知道这个文件的objectKey
    }


    /**
     * 单个文件上传
     *
     * @param folderName
     * @param newUrl
     */
    public void ossSingleUpload(final String folderName, final String newUrl) {
        if (TextUtils.isEmpty(newUrl)) return;
        File file = new File(newUrl);
        if (null == file || !file.exists()) return;
        // 文件后缀
        String fileSuffix = "";
        if (file.isFile()) {
            // 获取文件后缀名
            fileSuffix = file.getName().substring(file.getName().lastIndexOf("."));
        }
        // 文件标识符objectKey
        final String objectKey = folderName + File.separator + mDeviceId + System.currentTimeMillis() + fileSuffix;

        try {
            mUpLoadFile.asyncMultipartUpload(objectKey, newUrl, new OnAliOssListener() {

                @Override
                public void onSuccess() {
                    String aliPath = OSS_PATH + objectKey;
                    mOssUploadListener.ossSingleUploadSuccess(aliPath);
                }

            });
        } catch (ClientException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private List<String> mAliPaths = new ArrayList<>();

    public void ossMultiUpload(final String folderName, final List<String> newUrls) {
        if (newUrls.size() <= 0) {
            // 文件全部上传完毕，这里编写上传结束的逻辑，如果要在主线程操作，最好用Handler或runOnUiThead做对应逻辑
            return;// 这个return必须有，否则下面报越界异常，原因自己思考下哈
        }
        String url = newUrls.get(0);
        if (TextUtils.isEmpty(url)) {
            newUrls.remove(0);
            // url为空就没必要上传了，这里做的是跳过它继续上传的逻辑。
            ossMultiUpload(folderName, newUrls);
            return;
        }

        File file = new File(url);
        if (null == file || !file.exists()) {
            newUrls.remove(0);
            // 文件为空或不存在就没必要上传了，这里做的是跳过它继续上传的逻辑。
            ossMultiUpload(folderName, newUrls);
            return;
        }
        // 文件后缀
        String fileSuffix = "";
        if (file.isFile()) {
            // 获取文件后缀名
            fileSuffix = file.getName().substring(file.getName().lastIndexOf("."));
        }
        // 文件标识符objectKey
        final String objectKey = folderName + File.separator + mDeviceId + System.currentTimeMillis() + fileSuffix; //fileSuffix;
        // 下面3个参数依次为bucket名，objectKey名，上传文件路径
        try {
            mUpLoadFile.asyncMultipartUpload(objectKey, url, new OnAliOssListener() {

                @Override
                public void onSuccess() {

                    String aliPath = OSS_PATH + objectKey;
                    mAliPaths.add(aliPath);
                    if (newUrls.size() > 0) {
                        newUrls.remove(0);
                        if (newUrls.size() != 0) {
                            ossMultiUpload(folderName, newUrls);// 递归同步效果
                        } else { //返回List
                            mOssUploadListener.ossMultiUploadSuccess(mAliPaths);
                        }
                    }

                }
            });
        } catch (ClientException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setOssUploadListener(OssUploadListener ossUploadListener) {
        this.mOssUploadListener = ossUploadListener;
    }

}
