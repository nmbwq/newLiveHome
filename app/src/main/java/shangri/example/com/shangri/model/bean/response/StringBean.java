package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/6.
 */

public class StringBean implements Serializable {
     public String url;

    public StringBean(String url) {
        this.url = url;
    }

    public StringBean() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
