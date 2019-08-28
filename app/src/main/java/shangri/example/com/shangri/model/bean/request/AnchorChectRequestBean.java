package shangri.example.com.shangri.model.bean.request;

/**
 * Created by Administrator on 2018/3/28.
 */

public class AnchorChectRequestBean extends BaseBeen {
    public String register_guild_id;
    public String check_status;
    public String check_mark;

    public String getRegister_guild_id() {
        return register_guild_id;
    }

    public void setRegister_guild_id(String register_guild_id) {
        this.register_guild_id = register_guild_id;
    }

    public String getCheck_status() {
        return check_status;
    }

    public void setCheck_status(String check_status) {
        this.check_status = check_status;
    }

    public String getCheck_mark() {
        return check_mark;
    }

    public void setCheck_mark(String check_mark) {
        this.check_mark = check_mark;
    }
}
