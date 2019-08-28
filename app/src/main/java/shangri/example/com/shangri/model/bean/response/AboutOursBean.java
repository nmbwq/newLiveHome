package shangri.example.com.shangri.model.bean.response;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/15.
 */

public class AboutOursBean implements Serializable {


    /**
     * des : 13512348888
     * wx_gzh : 13512348888
     * wx : wx13512345678
     * email : 13512348888
     * tel : 13512345678
     */

    private String des;
    private String wx_gzh;
    private String wx;
    private String email;
    private String tel;

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getWx_gzh() {
        return wx_gzh;
    }

    public void setWx_gzh(String wx_gzh) {
        this.wx_gzh = wx_gzh;
    }

    public String getWx() {
        return wx;
    }

    public void setWx(String wx) {
        this.wx = wx;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

}



