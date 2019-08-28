package shangri.example.com.shangri.model.bean.request;

/**
 * 检验校验码
 * Created by chengaofu on 2017/6/27.
 */

public class CheckCodeBean {
    private String telephone;
    private String verify_code;
    private String type;

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getVerify_code() {
        return verify_code;
    }

    public void setVerify_code(String verify_code) {
        this.verify_code = verify_code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
