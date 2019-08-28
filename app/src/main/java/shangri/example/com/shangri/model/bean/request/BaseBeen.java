package shangri.example.com.shangri.model.bean.request;

import java.io.Serializable;

/**
 * Created by admin on 2017/12/22.
 */

public class BaseBeen implements Serializable{
    private String token;
    private String perview;

    public String getPerview() {
        return perview;
    }

    public void setPerview(String perview) {
        this.perview = perview;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
}
