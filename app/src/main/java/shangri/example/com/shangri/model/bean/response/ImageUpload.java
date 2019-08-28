package shangri.example.com.shangri.model.bean.response;

import shangri.example.com.shangri.model.bean.request.BaseBeen;

/**
 * Created by admin on 2017/12/26.
 */

public class ImageUpload extends BaseBeen {
    private String file_url;
    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }


}
