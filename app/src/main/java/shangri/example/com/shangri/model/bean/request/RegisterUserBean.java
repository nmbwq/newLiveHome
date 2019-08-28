package shangri.example.com.shangri.model.bean.request;

/**
 * 号码注册
 * Created by chengaofu on 2017/6/27.
 */

public class RegisterUserBean {
    private String telephone;
    private String repet_password;
    private String password;
    private String verify_code;
    private String wxinfo_id;
    private String register_from;
    private String invitation_code;

    public String getInvitation_code() {
        return invitation_code;
    }

    public void setInvitation_code(String invitation_code) {
        this.invitation_code = invitation_code;
    }

    public String getRegister_from() {
        return register_from;
    }

    public void setRegister_from(String register_from) {
        this.register_from = register_from;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getRepet_password() {
        return repet_password;
    }

    public void setRepet_password(String repet_password) {
        this.repet_password = repet_password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerify_code() {
        return verify_code;
    }

    public void setVerify_code(String verify_code) {
        this.verify_code = verify_code;
    }

    public String getWxinfo_id() {
        return wxinfo_id;
    }

    public void setWxinfo_id(String wxinfo_id) {
        this.wxinfo_id = wxinfo_id;
    }
}
