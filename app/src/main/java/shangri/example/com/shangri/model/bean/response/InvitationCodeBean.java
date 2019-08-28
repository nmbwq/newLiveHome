package shangri.example.com.shangri.model.bean.response;

public class InvitationCodeBean {


    /**
     * code : 207516
     */

    private String code;
    //    主播提现判断接口
    private String type;

    private int need_base;
    private int reg_register;
    private int call_count;


    public int getNeed_base() {
        return need_base;
    }

    public void setNeed_base(int need_base) {
        this.need_base = need_base;
    }

    public int getReg_register() {
        return reg_register;
    }

    public void setReg_register(int reg_register) {
        this.reg_register = reg_register;
    }

    public int getCall_count() {
        return call_count;
    }

    public void setCall_count(int call_count) {
        this.call_count = call_count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
