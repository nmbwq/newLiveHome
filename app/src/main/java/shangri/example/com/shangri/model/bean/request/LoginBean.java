package shangri.example.com.shangri.model.bean.request;

import java.io.Serializable;

/**
 * 登陆
 * Created by chengaofu on 2017/6/21.
 */

public class LoginBean implements Serializable {

    private String telephone;
    private String password;
    private String from;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
