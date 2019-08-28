package shangri.example.com.shangri.model.bean.request;

/**
 * 重置密码
 * Created by chengaofu on 2017/6/27.
 */

public class ResetPwdBean {

    private String telephone;
    private String password;
    private String repet_password;
    private String verify_code;
    private String token;

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

    public String getRepet_password() {
        return repet_password;
    }

    public void setRepet_password(String repet_password) {
        this.repet_password = repet_password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getVerify_code() {
        return verify_code;
    }

    public void setVerify_code(String verify_code) {
        this.verify_code = verify_code;
    }
}
