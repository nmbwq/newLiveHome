package shangri.example.com.shangri.util;
import com.google.gson.Gson;

import java.io.File;

import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2017/3/31.
 * do what to json 转换
 */

public class GsonFrom<T> {
    //图片上传 单张、多张
    public static MultipartBody.Part[] updataImages(List<String> paths, String path) {
        if (paths != null) {
            MultipartBody.Part[] filetest = new MultipartBody.Part[paths.size()];
            for (int i = 0; i < paths.size(); i++) {
                File file = new File(paths.get(i));
                String pathname = file.getName();
                String prefix = pathname.substring(pathname.lastIndexOf(".") + 1);
                RequestBody requestFile = RequestBody.create(MediaType.parse("image/" + prefix), file);
                filetest[i] = MultipartBody.Part.createFormData("img", pathname, requestFile);
            }
            return filetest;
        } else if (path != null) {
            MultipartBody.Part[] filetest = new MultipartBody.Part[1];
            File file = new File(path);
            String pathname = file.getName();
            String prefix = pathname.substring(pathname.lastIndexOf(".") + 1);
            RequestBody requestFile = RequestBody.create(MediaType.parse("image/" + prefix), file);
            filetest[0] = MultipartBody.Part.createFormData("img", pathname, requestFile);
            return filetest;
        }
        return null;
    }

    public static RequestBody sendNormalRare(Map map) {

        Gson gson = new Gson();

        String strEntity = gson.toJson(map);

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"), strEntity);

        return body;
    }


}
