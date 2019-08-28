package shangri.example.com.shangri.model.bean.request;

/**
 * 获取验证码
 * Created by chengaofu on 2017/6/27.
 */

public class GetSMSVerificationCodeBean {
    private String telephone;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
