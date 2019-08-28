package shangri.example.com.shangri.model.bean.request;

/**
 * Created by admin on 2017/12/22.
 */

public class SofwwareUserPresenterBeen extends BaseBeen{
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    private String role;
    private String register_id;

    public String getRegister_id() {
        return register_id;
    }

    public void setRegister_id(String register_id) {
        this.register_id = register_id;
    }
}
