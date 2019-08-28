package shangri.example.com.shangri.util.ali;

import android.util.Log;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.DeleteObjectRequest;
import com.alibaba.sdk.android.oss.model.DeleteObjectResult;

/**
 * 管理文件
 * Created by chengaofu on 2017/6/2.
 */

public class ManageObject {

    private OSS mOss;
    private String mBucketName;

    public ManageObject(OSS client, String bucketName){
        this.mOss = client;
        this.mBucketName = bucketName;
    }

    /**
     * 阿里云删除
     * @param objectKey
     */
    public void asyncDeleteObject(String objectKey){
        // 创建删除请求
        DeleteObjectRequest delete = new DeleteObjectRequest(mBucketName, objectKey);
        // 异步删除
        OSSAsyncTask deleteTask = mOss.asyncDeleteObject(delete, new OSSCompletedCallback<DeleteObjectRequest, DeleteObjectResult>() {
            @Override
            public void onSuccess(DeleteObjectRequest request, DeleteObjectResult result) {
                Log.d("asyncCopyAndDelObject", "success!");
            }

            @Override
            public void onFailure(DeleteObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                // 请求异常
                if (clientExcepion != null) {
                    // 本地异常如网络异常等
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常
                    Log.e("ErrorCode", serviceException.getErrorCode());
                    Log.e("RequestId", serviceException.getRequestId());
                    Log.e("HostId", serviceException.getHostId());
                    Log.e("RawMessage", serviceException.getRawMessage());
                }
            }

        });
        deleteTask.waitUntilFinished();
    }



}
