package shangri.example.com.shangri.ui.listener;

import java.util.List;

/**
 * 上传Oss
 * Created by chengaofu on 2017/7/2.
 */

public interface OssUploadListener {

    void ossSingleUploadSuccess(String alipath); //单个文件上传成功
    void ossMultiUploadSuccess(List<String> alipaths); //多个文件上传成功

}
