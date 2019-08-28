package shangri.example.com.shangri.model.bean.request;

import java.io.Serializable;

/**
 * Created by mschen on 2017/6/29.
 */

public class LoginCodeBean implements Serializable {
    private String mobile;
    private String code;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
