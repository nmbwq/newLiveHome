package shangri.example.com.shangri.model.bean.response;

import shangri.example.com.shangri.model.bean.request.BaseBeen;

/**
 * Created by admin on 2017/12/22.
 */

public class ForgetBean extends BaseBeen{
    private String telephone;
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
