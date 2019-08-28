package shangri.example.com.shangri.model.bean.request;

/**
 * 修改密码
 * Created by chengaofu on 2017/7/2.
 */

public class UpdatePwdBean extends BaseBeen{


    private String originalpwd;
    private String newpassword;
    private String repeatpwd;


    public String getOriginalpwd() {
        return originalpwd;
    }

    public void setOriginalpwd(String originalpwd) {
        this.originalpwd = originalpwd;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    public String getRepeatpwd() {
        return repeatpwd;
    }

    public void setRepeatpwd(String repeatpwd) {
        this.repeatpwd = repeatpwd;
    }
}
