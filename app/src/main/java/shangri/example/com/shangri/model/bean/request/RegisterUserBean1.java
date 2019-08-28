package shangri.example.com.shangri.model.bean.request;

/**
 * 号码注册
 * Created by chengaofu on 2017/6/27.
 */

public class RegisterUserBean1 {
    private String telephone;

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

    public String getRepeatpwd() {
        return repeatpwd;
    }

    public void setRepeatpwd(String repeatpwd) {
        this.repeatpwd = repeatpwd;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String password;
    private String repeatpwd;
    private String token;

}
